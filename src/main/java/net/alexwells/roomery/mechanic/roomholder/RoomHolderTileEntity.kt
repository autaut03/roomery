package net.alexwells.roomery.mechanic.roomholder

import net.alexwells.roomery.mechanic.roomcard.RoomCardItem
import net.alexwells.roomery.tile.TileEntityBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.inventory.Container
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.NetworkManager
import net.minecraft.network.play.server.SPacketUpdateTileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.text.ITextComponent
import net.minecraft.world.IInteractionObject
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.ItemStackHandler

class RoomHolderTileEntity : TileEntityBase(RoomHolderTileType), ICapabilityProvider, IInteractionObject {
    val itemHandler: ItemStackHandler = object : ItemStackHandler(1) {
        override fun onContentsChanged(slot: Int) {
            super.onContentsChanged(slot)
            markDirty()

            // Here we should tell the client that this block has changed in some way
            // client will receive this and update block state.
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
            } else false
        }

        override fun getSlotLimit(slot: Int): Int {
            return if (slot == ROOM_CARD_SLOT) {
                1
            } else super.getSlotLimit(slot)
        }
    }

    val roomCard: ItemStack?
        get() = itemHandler.getStackInSlot(ROOM_CARD_SLOT)

    fun isActive() = hasValidRoomCard()

    override fun useDefaultUpdatePacket() = true

    private fun hasValidRoomCard() = roomCard?.item is RoomCardItem

    override fun read(compound: NBTTagCompound) {
        this.itemHandler.deserializeNBT(compound.getCompound(ITEM_HANDLER_TAG))

        super.read(compound)
    }

    override fun write(compound: NBTTagCompound): NBTTagCompound {
        compound.put(ITEM_HANDLER_TAG, this.itemHandler.serializeNBT())

        return super.write(compound)
    }

    override fun <T : Any?> getCapability(cap: Capability<T>, side: EnumFacing?): LazyOptional<T> {
        if(cap === CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return LazyOptional.of { this.itemHandler as T }
        }

        return LazyOptional.empty()
    }

    override fun onDataPacket(net: NetworkManager, pkt: SPacketUpdateTileEntity) {
        super.onDataPacket(net, pkt)

        world.setBlockState(pos, world.getBlockState(pos).with(RoomHolderBlock.Properties.ACTIVE, isActive()))
    }

    override fun getName(): ITextComponent = RoomHolderBlock.nameTextComponent
    override fun getGuiID(): String = ROOM_HOLDER_RESOURCE.toString()

    override fun createContainer(playerInventory: InventoryPlayer, playerIn: EntityPlayer): Container {
        return RoomHolderContainer(playerInventory, this)
    }

    override fun hasCustomName(): Boolean = false
    override fun getCustomName(): ITextComponent? = null

    companion object {
        const val ITEM_HANDLER_TAG = "ItemHandler"
        val ROOM_CARD_SLOT = 0
    }
}
