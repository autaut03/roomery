package net.alexwells.roomery.mechanic.roomholder

import java.util.function.Supplier
import net.minecraft.tileentity.TileEntityType

object RoomHolderTileType : TileEntityType<RoomHolderTileEntity>(Supplier { RoomHolderTileEntity() }, setOf(RoomHolderBlock), null) {
    init {
        registryName = ROOM_HOLDER_RESOURCE
    }
}
