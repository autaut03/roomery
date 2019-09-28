package net.alexwells.roomery.mechanic.roomholder

import net.alexwells.roomery.common.SideEnum
import net.alexwells.roomery.common.TileEntityBase
import net.alexwells.roomery.mechanic.room.Room
import net.alexwells.roomery.mechanic.room.RoomManager
import net.alexwells.roomery.mechanic.roomcard.RoomCardItem
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.container.INamedContainerProvider
import net.minecraft.item.ItemStack
import net.minecraft.nbt.CompoundNBT
import net.minecraft.network.NetworkManager
import net.minecraft.network.play.server.SUpdateTileEntityPacket
import net.minecraft.util.Direction
import net.minecraft.util.text.ITextComponent
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.common.util.LazyOptional
import net.minecraftforge.items.CapabilityItemHandler
import net.minecraftforge.items.ItemStackHandler

class RoomHolderTileEntity : TileEntityBase(RoomHolderTileType), INamedContainerProvider, ICapabilityProvider {
    val itemHandler: ItemStackHandler = object : ItemStackHandler(1) {
        override fun onContentsChanged(slot: Int) {
            super.onContentsChanged(slot)
            markDirty()
            updateWithRoomManager()
            world!!.setBlockState(pos, world!!.getBlockState(pos).with(RoomHolderBlock.Properties.ACTIVE, isActive()))

            /*if (slot == ROOM_CARD_SLOT) {
                // Here we should tell the client that this block has changed in some way
                // client will receive this and update block state.
                sendUpdatePacket()

                if (isActive()) {
                    roomReference = RoomManager.get(RoomCardItem.getId(roomCard!!)!!)
                }
            }*/
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

    var room: Room? = null

    override fun setWorld(world: World) {
        super.setWorld(world)

        updateWithRoomManager()
    }

    private fun findRoom(): Room? {
        if (world == null || world!!.isRemote) {
            return null
        }

        val id = if (roomCard != null) RoomCardItem.getId(roomCard!!) else null

        if (id == null) {
            return null
        }

        return RoomManager.get(id)
    }

    private fun isActive() = roomCard?.item is RoomCardItem

    override fun useDefaultUpdatePacket() = true

    override fun read(compound: CompoundNBT) {
        this.itemHandler.deserializeNBT(compound.getCompound(ITEM_HANDLER_TAG))

        super.read(compound)

        updateWithRoomManager()
    }

    override fun write(compound: CompoundNBT): CompoundNBT {
        compound.put(ITEM_HANDLER_TAG, this.itemHandler.serializeNBT())

        return super.write(compound)
    }

    override fun onDataPacket(net: NetworkManager, pkt: SUpdateTileEntityPacket) {
        super.onDataPacket(net, pkt)

        world!!.setBlockState(pos, world!!.getBlockState(pos).with(RoomHolderBlock.Properties.ACTIVE, isActive()))
    }

    override fun remove() {
        super.remove()

        updateWithRoomManager()
    }

    private fun updateWithRoomManager() {
        if (world == null || world!!.isRemote) {
            return
        }

        // First of all, check if this was called after the entity was removed.
        // In that case we'll also notify the Room instance of this.
        if (removed && this.room != null) {
            this.room!!.setHolder(null)
            this.room = null

            return
        }

        val room = findRoom() ?: return

        // Notify the Room about new holder (assuming it won't do any harm if the same instance was provided).
        this.room = room
        room.setHolder(this)
    }

    fun <T : Any?> getOutCapability(cap: Capability<T>, direction: Direction?): LazyOptional<T> {
        if (world == null || world!!.isRemote || direction == null) {
            return LazyOptional.empty()
        }

        val insetPos = pos.offset(direction)
        val tile = world!!.getTileEntity(insetPos)

        if (tile !is ICapabilityProvider) {
            return LazyOptional.empty()
        }

        return tile.getCapability(cap, direction.opposite)
    }

    override fun <T : Any?> getCapability(cap: Capability<T>, direction: Direction?): LazyOptional<T> {
        val side = getSide(direction)

        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && side == SideEnum.FRONT) {
            return LazyOptional.of { itemHandler as T }
        }

        val room = findRoom() ?: return LazyOptional.empty()

        return room.getConnectorCapability(cap, side)
    }

    fun getDirection(): Direction {
        return this.blockState.get(RoomHolderBlock.Properties.FACING)
    }

    fun getSide(direction: Direction?): SideEnum {
        return if (direction != null)
            SideEnum.fromDirection(direction, getDirection())
        else
            SideEnum.FRONT
    }

    /**
     * Create a container for this room holder.
     */
    override fun createMenu(
        windowId: Int,
        playerInventory: PlayerInventory,
        player: PlayerEntity
    ): RoomHolderContainer {
        return RoomHolderContainer(windowId, playerInventory, this)
    }

    /**
     * Get display name for this room holder/it's container.
     */
    override fun getDisplayName(): ITextComponent = RoomHolderBlock.nameTextComponent

    companion object {
        const val ITEM_HANDLER_TAG = "ItemHandler"
        const val ROOM_CARD_SLOT = 0
    }
}
