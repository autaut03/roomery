package net.alexwells.roomery.proxy

import net.alexwells.roomery.Registry
import net.alexwells.roomery.Roomery
import net.minecraftforge.common.ModDimension
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.event.server.FMLServerStartingEvent

class ServerProxy : CommonProxy() {
    @SubscribeEvent
    fun registerDimensions(event: RegistryEvent.Register<ModDimension>) {
        Registry.dimensions.forEach { event.registry.register(it) }
    }
}
