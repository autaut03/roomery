package net.alexwells.roomery.util

import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockReader
import org.apache.logging.log4j.LogManager

fun <T : TileEntity> IBlockReader.getTile(pos: BlockPos): T? {
    val tileEntity = this.getTileEntity(pos)

    try {
        return tileEntity as T?
    } catch (ex: ClassCastException) {
        LogManager.getLogger().error("Trying to cast world's tile entity into needed type.")
        LogManager.getLogger().catching(ex)
    }

    return null
}
