package net.alexwells.roomery

import net.alexwells.roomery.Reference.MOD_ID
import net.alexwells.roomery.proxy.ClientProxy
import net.alexwells.roomery.proxy.CommonProxy
import net.alexwells.roomery.proxy.ServerProxy
import net.minecraftforge.fml.DistExecutor
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.javafmlmod.FMLModLoadingContext
import java.util.function.Supplier

@Mod(MOD_ID)
class Roomery {
    val proxy: CommonProxy = DistExecutor.runForDist<CommonProxy>({ Supplier { ClientProxy() } }, { Supplier { ServerProxy() } })

    init {
        //FMLModLoadingContext.get().modEventBus.addListener(this::setup)
        FMLModLoadingContext.get().modEventBus.register(proxy)
    }

    fun setup(event: FMLCommonSetupEvent) {
        //NetworkRegistry.INSTANCE.registerGuiHandler(this, GuiHandler())
    }
}