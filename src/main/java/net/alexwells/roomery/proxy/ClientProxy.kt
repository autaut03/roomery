package net.alexwells.roomery.proxy

import net.alexwells.roomery.mechanic.roomcard.RoomCardItem
import net.alexwells.roomery.mechanic.roomholder.RoomHolderBlock
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraftforge.client.event.ModelRegistryEvent
import net.minecraftforge.client.model.ModelLoader
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

class ClientProxy : CommonProxy() {
    @SubscribeEvent
    fun registerModels(event: ModelRegistryEvent) {
        addItemModel(RoomCardItem)

        addItemModel(Item.getItemFromBlock(RoomHolderBlock))
    }

    private fun addItemModel(item: Item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, ModelResourceLocation(item.registryName!!, "inventory"))
    }
}
