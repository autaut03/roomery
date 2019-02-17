package net.alexwells.roomery.mechanic.roomholder

import net.minecraft.tileentity.TileEntityType
import java.util.function.Supplier

object RoomHolderTileType : TileEntityType<RoomHolderTileEntity>(Supplier { RoomHolderTileEntity() }, null) {
    init {
        registryName = ROOM_HOLDER_RESOURCE
    }
}