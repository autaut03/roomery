package net.alexwells.roomery.util

import net.alexwells.roomery.Roomery
import net.alexwells.roomery.gui.GuiEnum
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

object GuiUtils {
    fun openGui(player: EntityPlayer, guiType: GuiEnum, world: World, pos: BlockPos) {
        openGui(player, guiType, world, pos.x, pos.y, pos.z)
    }

    fun openGui(player: EntityPlayer, guiType: GuiEnum, world: World, x: Int, y: Int, z: Int) {
        player.openGui(Roomery, guiType.id, world, x, y, z)
    }
}
