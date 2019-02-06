package net.alexwells.roomery.proxy

import net.alexwells.roomery.Registry
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraftforge.event.RegistryEvent
import net.minecraft.tileentity.TileEntityType
import net.minecraftforge.eventbus.api.SubscribeEvent

abstract class CommonProxy {
    @SubscribeEvent
    fun registerBlocks(event: RegistryEvent.Register<Block>) {
        Registry.blocks.forEach { event.registry.register(it) }
    }

    @SubscribeEvent
    fun registerTiles(event: RegistryEvent.Register<TileEntityType<*>>) {
        Registry.tiles.forEach { event.registry.register(it) }
    }

    @SubscribeEvent
    fun registerItems(event: RegistryEvent.Register<Item>) {
        Registry.items.forEach { event.registry.register(it) }
    }
}
