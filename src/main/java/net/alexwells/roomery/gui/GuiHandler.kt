package net.alexwells.roomery.gui

import net.alexwells.roomery.mechanic.roomholder.ROOM_HOLDER_RESOURCE
import net.alexwells.roomery.mechanic.roomholder.RoomHolderContainer
import net.alexwells.roomery.mechanic.roomholder.RoomHolderGui
import net.alexwells.roomery.mechanic.roomholder.RoomHolderTileEntity
import net.alexwells.roomery.util.getTile
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraft.inventory.Container
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IInteractionObject
import net.minecraftforge.fml.network.FMLPlayMessages

object GuiHandler {
    fun handleGui(packet: FMLPlayMessages.OpenContainer): GuiScreen? {
        return when(packet.id) {
            ROOM_HOLDER_RESOURCE -> {
                val container = buildContainerForTile<RoomHolderTileEntity, RoomHolderContainer>(packet.additionalData.readBlockPos()) ?: return null

                return RoomHolderGui(container)
            }
            else -> null
        }
    }

    private fun <T, C: Container> buildContainerForTile(pos: BlockPos): C? where T: TileEntity, T: IInteractionObject {
        val tile = Minecraft.getInstance().world.getTile<T>(pos) ?: return null

        val player = Minecraft.getInstance().player

        return tile.createContainer(player.inventory, player) as C
    }
}