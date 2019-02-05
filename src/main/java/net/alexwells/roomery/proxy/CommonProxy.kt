package net.alexwells.roomery.proxy

import net.alexwells.roomery.Registry
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraft.tileentity.TileEntity
import kotlin.reflect.KClass

abstract class CommonProxy {
    @SubscribeEvent
    fun registerBlocks(event: RegistryEvent.Register<Block>) {
        Registry.blocks.forEach {
            event.registry.register(it.block)

            if(it.tile != null) {
                registerTile(it.tile, it.block)
            }
        }
    }

    @SubscribeEvent
    fun registerItems(event: RegistryEvent.Register<Item>) {
        Registry.items.forEach { event.registry.register(it) }

        Registry.blocks.forEach {
            if(it.hasItem) {
                event.registry.register(createBlockItem(it.block))
            }
        }
    }

    private fun createBlockItem(block: Block) = ItemBlock(block).setRegistryName(block.registryName)

    private fun <T : TileEntity> registerTile(tileEntityClass: KClass<T>, block: Block) {
        GameRegistry.registerTileEntity(tileEntityClass.java, block.registryName)
    }
}
