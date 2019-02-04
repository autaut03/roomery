package net.alexwells.roomery.util

import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess

object WorldUtils {
    // todo
    fun <T : TileEntity> getTileEntity(world: IBlockAccess, pos: BlockPos): T? {
        val tileEntity = world.getTileEntity(pos)

        try {
            return tileEntity as T?
        } catch (ignored: ClassCastException) {

        }

        return null
    }
}
