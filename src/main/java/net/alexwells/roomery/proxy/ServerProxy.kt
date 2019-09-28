package net.alexwells.roomery.proxy

import net.alexwells.roomery.Registry
import net.minecraftforge.common.ModDimension
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.eventbus.api.SubscribeEvent

class ServerProxy : CommonProxy() {
    @SubscribeEvent
    fun registerDimensions(event: RegistryEvent.Register<ModDimension>) {
        Registry.dimensions.forEach { event.registry.register(it) }
    }
}
