package net.alexwells.roomery.proxy

import net.alexwells.roomery.Registry
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.eventbus.api.SubscribeEvent

class ClientProxy : CommonProxy() {
    @SubscribeEvent
    fun registerModels(event: ModelRegistryEvent) {
        Registry.items.forEach(this::addItemModel)
        Registry.blocks.forEach { addItemModel(Item.getItemFromBlock(it.block)) }
    }

    private fun addItemModel(item: Item) {
        //ModelLoader.setCustomModelResourceLocation(item, 0, ModelResourceLocation(item.registryName!!, "inventory"))
    }
}
