package net.alexwells.roomery.proxy

import net.alexwells.roomery.Registry
import net.alexwells.roomery.mechanic.room.world.ROOMERY_DIMENSION_RESOURCE
import net.alexwells.roomery.mechanic.room.world.RoomeryDimensionType
import net.minecraft.block.Block
import net.minecraft.inventory.container.ContainerType
import net.minecraft.item.Item
import net.minecraft.tileentity.TileEntityType
import net.minecraft.world.dimension.DimensionType
import net.minecraftforge.common.DimensionManager
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.event.world.RegisterDimensionsEvent
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

    @SubscribeEvent
    fun registerContainers(event: RegistryEvent.Register<ContainerType<*>>) {
        Registry.containers.forEach { event.registry.register(it) }
    }

    @SubscribeEvent
    fun registerVanillaDimension(event: RegisterDimensionsEvent) {
        if (DimensionType.byName(ROOMERY_DIMENSION_RESOURCE) == null) {
            DimensionManager.registerDimension(ROOMERY_DIMENSION_RESOURCE, RoomeryDimensionType, null, true)
        }
    }
}
