package net.alexwells.roomery.mechanic.roomholder

import net.alexwells.roomery.MOD_ID
import net.minecraft.tileentity.TileEntityType
import java.util.function.Supplier

object RoomHolderTileType : TileEntityType<RoomHolderTileEntity>(Supplier { RoomHolderTileEntity() }, null) {
    init {
        registryName = RoomHolderBlock.registryName
    }
}