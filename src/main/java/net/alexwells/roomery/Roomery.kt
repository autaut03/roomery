package net.alexwells.roomery

import net.alexwells.roomery.gui.GuiHandler
import net.alexwells.roomery.mechanic.roomcard.RoomCardItem
import net.alexwells.roomery.proxy.ClientProxy
import net.alexwells.roomery.proxy.CommonProxy
import net.alexwells.roomery.proxy.ServerProxy
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.DistExecutor
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.javafmlmod.FMLModLoadingContext
import java.util.function.Supplier

const val MOD_ID = "roomery"

@Mod(MOD_ID)
class Roomery {
    val proxy = DistExecutor.runForDist<CommonProxy>({ Supplier { ClientProxy() } }, { Supplier { ServerProxy() } })

    /*val itemGroup = object : ItemGroup(MOD_ID) {
        override fun createIcon() = ItemStack(RoomCardItem)
    }*/

    init {
        //FMLModLoadingContext.get().modEventBus.addListener(this::setup)
        FMLModLoadingContext.get().modEventBus.register(proxy)
    }

    fun setup(event: FMLCommonSetupEvent) {
        //MinecraftForge.EVENT_BUS.register(proxy)

        //NetworkRegistry.INSTANCE.registerGuiHandler(this, GuiHandler())
    }
}