package net.alexwells.roomery.mechanic.roomholder

import net.alexwells.roomery.common.TileEntityBase
import net.alexwells.roomery.mechanic.room.Room
import net.alexwells.roomery.mechanic.room.RoomsData
import net.alexwells.roomery.mechanic.roomcard.RoomCardItem
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.container.INamedContainerProvider
import net.minecraft.item.ItemStack
import net.minecraft.nbt.CompoundNBT
import net.minecraft.network.NetworkManager
import net.minecraft.network.play.server.SUpdateTileEntityPacket
import net.minecraft.util.text.ITextComponent
import net.minecraftforge.items.ItemStackHandler

class RoomHolderTileEntity : TileEntityBase(RoomHolderTileType), INamedContainerProvider {
    private var roomReference: Room? = null

    val itemHandler: ItemStackHandler = object : ItemStackHandler(1) {
        override fun onContentsChanged(slot: Int) {
            super.onContentsChanged(slot)
            markDirty()

            if (slot == ROOM_CARD_SLOT) {
                // Here we should tell the client that this block has changed in some way
                // client will receive this and update block state.
                sendUpdatePacket()

                if (isActive()) {
                    roomReference = RoomsData.getById(world!!, RoomCardItem.getId(roomCard!!)!!)
                }
            }
        }

        override fun isItemValid(slot: Int, stack: ItemStack): Boolean {
            return if (slot == ROOM_CARD_SLOT && RoomCardItem.isValid(stack)) {
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

    private fun isActive() = roomCard?.item is RoomCardItem

    override fun useDefaultUpdatePacket() = true

    override fun read(compound: CompoundNBT) {
        this.itemHandler.deserializeNBT(compound.getCompound(ITEM_HANDLER_TAG))

        super.read(compound)
    }

    override fun write(compound: CompoundNBT): CompoundNBT {
        compound.put(ITEM_HANDLER_TAG, this.itemHandler.serializeNBT())

        return super.write(compound)
    }

    override fun onDataPacket(net: NetworkManager, pkt: SUpdateTileEntityPacket) {
        super.onDataPacket(net, pkt)

        world!!.setBlockState(pos, world!!.getBlockState(pos).with(RoomHolderBlock.Properties.ACTIVE, isActive()))
    }

    override fun createMenu(
        windowId: Int,
        playerInventory: PlayerInventory,
        player: PlayerEntity
    ): RoomHolderContainer {
        return RoomHolderContainer(windowId, playerInventory, this)
    }

    override fun getDisplayName(): ITextComponent = RoomHolderBlock.nameTextComponent

    companion object {
        const val ITEM_HANDLER_TAG = "ItemHandler"
        const val ROOM_CARD_SLOT = 0
    }
}
