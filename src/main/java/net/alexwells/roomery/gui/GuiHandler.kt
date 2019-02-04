package net.alexwells.roomery.gui

import net.alexwells.roomery.mechanic.roomholder.RoomHolderGui
import net.alexwells.roomery.mechanic.roomholder.RoomHolderContainer
import net.alexwells.roomery.util.WorldUtils
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.IGuiHandler

class GuiHandler : IGuiHandler {
    override fun getServerGuiElement(id: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
        return if (id == GuiEnum.ROOM_HOLDER.id) {
            RoomHolderContainer(player.inventory, WorldUtils.getTileEntity(world, BlockPos(x, y, z))!!)
        } else null
    }

    override fun getClientGuiElement(id: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Any? {
        return if (id == GuiEnum.ROOM_HOLDER.id) {
            RoomHolderGui(player.inventory, WorldUtils.getTileEntity(world, BlockPos(x, y, z))!!)
        } else null
    }
}
