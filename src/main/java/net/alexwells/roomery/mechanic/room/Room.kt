package net.alexwells.roomery.mechanic.room

import net.alexwells.roomery.common.SideEnum
import net.alexwells.roomery.mechanic.connector.ConnectorTileEntity
import net.alexwells.roomery.mechanic.roomholder.RoomHolderBlock
import net.alexwells.roomery.mechanic.roomholder.RoomHolderTileEntity
import net.minecraft.nbt.CompoundNBT
import net.minecraft.util.Direction
import net.minecraft.world.storage.WorldSavedData
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.util.LazyOptional

class Room(private val id: String, name: String) : WorldSavedData(name) {
    private var holder: RoomHolderTileEntity? = null
    private val connectors: MutableMap<SideEnum, ConnectorTileEntity> = HashMap()

    /**
     * If a new holder appears, it won't work. The other one needs to be unloaded first.
     */
    fun setHolder(holder: RoomHolderTileEntity?) {
        if (this.holder != null) {
            return
        }

        this.holder = holder
    }

    /**
     * Notify a room about new loaded connector.
     */
    fun addConnector(connector: ConnectorTileEntity) {
        connectors[connector.side] = connector
    }

    /**
     * Notify a room about the removal of previously loaded connector.
     */
    fun removeConnector(connector: ConnectorTileEntity) {
        connectors.remove(connector.side)
    }

    /**
     * Get a capability instance from inside to outside (from a connector inside the room to a room holder outside).
     */
    fun <T> getHolderCapability(cap: Capability<T>, side: SideEnum): LazyOptional<T> {
        // If for some reason there's not a single holder for this room
        // (say holder was destroyed, but a chunk didn't unload yet),
        // then we'll say that there are no capabilities anymore.
        if (holder == null) {
            return LazyOptional.empty()
        }

        val direction = side.toDirection(getHolderDirection())

        return holder!!.getOutCapability(cap, direction)
    }

    /**
     * Get a capability instance from outside to inside (from a room holder outside to a connector inside the room).
     */
    fun <T : Any?> getConnectorCapability(cap: Capability<T>, direction: Direction?): LazyOptional<T> {
        val side = if (direction != null) SideEnum.fromDirection(direction, getHolderDirection()) else SideEnum.FRONT

        // Can be optimized.
        if (!connectors.contains(side)) {
            return LazyOptional.empty()
        }

        return connectors[side]!!.getOutCapability(cap)
    }

    private fun getHolderDirection(): Direction {
        return holder!!.blockState.get(RoomHolderBlock.Properties.FACING)
    }

    override fun read(nbt: CompoundNBT) {
    }

    override fun write(nbt: CompoundNBT): CompoundNBT {
        return nbt
    }
}
