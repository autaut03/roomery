package net.alexwells.roomery.mechanic.connector

import net.alexwells.roomery.common.SideEnum
import net.alexwells.roomery.common.TileEntityBase
import net.alexwells.roomery.mechanic.room.Room
import net.alexwells.roomery.mechanic.room.RoomManager
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.container.INamedContainerProvider
import net.minecraft.nbt.CompoundNBT
import net.minecraft.util.Direction
import net.minecraft.util.text.ITextComponent
import net.minecraft.world.World
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.ICapabilityProvider
import net.minecraftforge.common.util.LazyOptional

class ConnectorTileEntity : TileEntityBase(ConnectorTileType), INamedContainerProvider, ICapabilityProvider {
    var side: SideEnum = SideEnum.FRONT
        private set

    override fun setWorld(world: World) {
        super.setWorld(world)

        getRoom()?.addConnector(this)
    }

    fun <T : Any?> getOutCapability(cap: Capability<T>): LazyOptional<T> {
        if (world!!.isRemote) {
            return LazyOptional.empty()
        }

        val facing = blockState.get(ConnectorBlock.Properties.FACING)
        val insetPos = pos.offset(facing)
        val tile = world!!.getTileEntity(insetPos)

        if (tile !is ICapabilityProvider) {
            return LazyOptional.empty()
        }

        return tile.getCapability(cap, facing.opposite)
    }

    override fun <T : Any?> getCapability(cap: Capability<T>, direction: Direction?): LazyOptional<T> {
        val room = getRoom() ?: return LazyOptional.empty()

        return room.getHolderCapability(cap, side)
    }

    override fun read(compound: CompoundNBT) {
        super.read(compound)

        getRoom()?.addConnector(this)
    }

    override fun remove() {
        super.remove()

        getRoom()?.removeConnector(this)
    }

    private fun getRoom(): Room? {
        if (world == null || world!!.isRemote) {
            return null
        }

        return RoomManager.get("1")
    }

    override fun createMenu(windowId: Int, playerInventory: PlayerInventory, player: PlayerEntity): ConnectorContainer {
        return ConnectorContainer(windowId, playerInventory, this)
    }

    override fun getDisplayName(): ITextComponent = ConnectorBlock.nameTextComponent
}
