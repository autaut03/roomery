package net.alexwells.roomery.proxy

import net.alexwells.roomery.mechanic.roomholder.RoomHolderBlock
import net.alexwells.roomery.mechanic.roomholder.RoomHolderContainer
import net.alexwells.roomery.mechanic.roomholder.RoomHolderGui
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraftforge.fml.ExtensionPoint
import net.minecraftforge.fml.ModLoadingContext
import net.minecraftforge.fml.network.FMLPlayMessages
import java.util.function.Function

class ClientProxy : CommonProxy() {
    init {
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.GUIFACTORY) {
            Function { container -> handleGui(container)  }
        }
    }

    private fun handleGui(container: FMLPlayMessages.OpenContainer): GuiScreen? {
        if (container.id == RoomHolderBlock.registryName) {
            return RoomHolderGui(Minecraft.getInstance().player.openContainer as RoomHolderContainer)
        }

        return null
    }
}
