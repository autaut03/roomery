package net.alexwells.roomery.mechanic.roomholder

import net.alexwells.roomery.mechanic.roomcard.RoomCardItem
import net.alexwells.roomery.tile.TileEntityBase
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.EnumFacing
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.ItemStackHandler

class RoomHolderTileEntity : TileEntityBase(), ICapabilityProvider {

    val itemHandler: ItemStackHandler

    val roomCard: ItemStack?
        get() = itemHandler.getStackInSlot(ROOM_CARD_SLOT)

    val isActive: Boolean
        get() = hasValidRoomCard()

    init {
        this.itemHandler = object : ItemStackHandler(1) {
            override fun onContentsChanged(slot: Int) {
                super.onContentsChanged(slot)
                markDirty()

                // Here we should tell the client that this block has changed in some way
                // client will receive this and trigger world.markBlockRangeForRenderUpdate()
                sendUpdatePacket()
            }

            override fun insertItem(slot: Int, stack: ItemStack, simulate: Boolean): ItemStack {
                return if (!isItemValid(slot, stack)) {
                    stack
                } else super.insertItem(slot, stack, simulate)

            }

            override fun isItemValid(slot: Int, stack: ItemStack): Boolean {
                return if (slot == ROOM_CARD_SLOT && stack.item is RoomCardItem) {
                    true
                    //return stack.
                } else super.isItemValid(slot, stack)

            }

            override fun getSlotLimit(slot: Int): Int {
                return if (slot == ROOM_CARD_SLOT) {
                    1
                } else super.getSlotLimit(slot)

            }
        }
    }

    override fun useDefaultUpdatePacket(): Boolean {
        return true
    }

    private fun hasValidRoomCard(): Boolean {
        return roomCard!!.item is RoomCardItem
    }

    override fun readFromNBT(compound: NBTTagCompound) {
        this.itemHandler.deserializeNBT(compound.getCompoundTag(ITEM_HANDLER_TAG))

        super.readFromNBT(compound)
    }

    override fun writeToNBT(compound: NBTTagCompound): NBTTagCompound {
        compound.setTag(ITEM_HANDLER_TAG, this.itemHandler.serializeNBT())

        return super.writeToNBT(compound)
    }

    override fun hasCapability(capability: Capability<*>, facing: EnumFacing?): Boolean {
        return if (capability === CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            true
        } else super.hasCapability(capability, facing)

    }

    override fun <T> getCapability(capability: Capability<T>, facing: EnumFacing?): T? {
        return if (capability === CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            this.itemHandler as T
        } else super.getCapability(capability, facing)

    }

    companion object {
        const val ITEM_HANDLER_TAG = "ItemHandler"
        val ROOM_CARD_SLOT = 0
    }
}
