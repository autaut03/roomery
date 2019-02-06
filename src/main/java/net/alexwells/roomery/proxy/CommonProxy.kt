package net.alexwells.roomery.proxy

import net.alexwells.roomery.Registry
import net.alexwells.roomery.Roomery
import net.alexwells.roomery.mechanic.roomholder.RoomHolderBlock
import net.alexwells.roomery.mechanic.roomholder.RoomHolderTileEntity
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraftforge.event.RegistryEvent
import net.minecraft.tileentity.TileEntityType
import net.minecraftforge.eventbus.api.SubscribeEvent
import org.apache.logging.log4j.LogManager
import java.util.function.Supplier

abstract class CommonProxy {
    @SubscribeEvent
    fun registerBlocks(event: RegistryEvent.Register<Block>) {
        Registry.blocks.forEach { event.registry.register(it.block) }
    }

    @SubscribeEvent
    fun registerTiles(event: RegistryEvent.Register<TileEntityType<*>>) {
        Registry.tiles.forEach { event.registry.register(it) }
    }

    @SubscribeEvent
    fun registerItems(event: RegistryEvent.Register<Item>) {
        Registry.items.forEach { event.registry.register(it) }

        Registry.blocks.forEach {
            if(it.hasItem) {
                val item = ItemBlock(it.block, it.itemBuilder ?: Item.Builder()/*.group(Roomery.itemGroup)*/)
                        .setRegistryName(it.block.registryName)

                event.registry.register(item)
            }
        }
    }
}
