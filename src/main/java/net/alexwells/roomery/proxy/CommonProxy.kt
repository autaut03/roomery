package net.alexwells.roomery.proxy

import net.alexwells.roomery.mechanic.roomholder.RoomHolderBlock
import net.alexwells.roomery.mechanic.roomcard.RoomCardItem
import net.alexwells.roomery.mechanic.roomholder.RoomHolderTileEntity
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock
import net.minecraft.util.ResourceLocation
import net.minecraftforge.event.RegistryEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.registry.GameRegistry

import net.alexwells.roomery.Constants.MOD_ID

abstract class CommonProxy {
    @SubscribeEvent
    fun registerBlocks(event: RegistryEvent.Register<Block>) {
        event.registry.register(RoomHolderBlock)
        GameRegistry.registerTileEntity(RoomHolderTileEntity::class.java, ResourceLocation(MOD_ID, RoomHolderBlock.NAME))
    }

    @SubscribeEvent
    fun registerItems(event: RegistryEvent.Register<Item>) {
        event.registry.register(RoomCardItem)

        event.registry.register(ItemBlock(RoomHolderBlock).setRegistryName(MOD_ID, RoomHolderBlock.NAME))
    }
}
