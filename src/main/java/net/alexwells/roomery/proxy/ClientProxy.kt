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
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(RoomHolderBlock), 0, ModelResourceLocation(RoomHolderBlock.registryName!!, "inventory"))

        ModelLoader.setCustomModelResourceLocation(RoomCardItem, 0, ModelResourceLocation(RoomCardItem.registryName!!, "inventory"))
    }
}
