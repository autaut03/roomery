package net.alexwells.roomery.proxy

import net.alexwells.roomery.mechanic.roomholder.RoomHolderBlock
import net.alexwells.roomery.mechanic.roomholder.RoomHolderContainer
import net.alexwells.roomery.mechanic.roomholder.RoomHolderGui
import net.alexwells.roomery.mechanic.roomholder.RoomHolderTileEntity
import net.alexwells.roomery.util.getTile
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraft.inventory.Container
import net.minecraft.network.PacketBuffer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IInteractionObject
import net.minecraftforge.fml.ExtensionPoint
import net.minecraftforge.fml.ModLoadingContext
import net.minecraftforge.fml.network.FMLPlayMessages
import java.lang.RuntimeException
import java.util.function.Function

class ClientProxy : CommonProxy() {
    init {
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.GUIFACTORY) {
            Function { packet -> handleGui(packet)  }
        }
    }

    private fun handleGui(packet: FMLPlayMessages.OpenContainer): GuiScreen? {
        if (packet.id == RoomHolderBlock.registryName) {
            val container = buildContainerForTile<RoomHolderTileEntity, RoomHolderContainer>(packet.additionalData.readBlockPos()) ?: return null

            return RoomHolderGui(container)
        }

        return null
    }

    private fun <T, C: Container> buildContainerForTile(pos: BlockPos): C? where T: TileEntity, T: IInteractionObject {
        val tile = Minecraft.getInstance().world.getTile<T>(pos) ?: return null

        val player = Minecraft.getInstance().player

        return tile.createContainer(player.inventory, player) as C
    }
}
