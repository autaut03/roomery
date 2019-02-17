package net.alexwells.roomery.proxy

import net.alexwells.roomery.gui.GuiHandler
import net.alexwells.roomery.mechanic.roomholder.*
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
            Function { packet -> GuiHandler.handleGui(packet)  }
        }
    }
}
