package net.alexwells.roomery.proxy;

import net.alexwells.roomery.holder.BlockHolder;
import net.alexwells.roomery.holder.ItemHolder;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ClientProxy extends CommonProxy {
    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent
    public void registerModels(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BlockHolder.roomHolder), 0, new ModelResourceLocation(BlockHolder.roomHolder.getRegistryName(), "inventory"));

        ModelLoader.setCustomModelResourceLocation(ItemHolder.roomCard, 0, new ModelResourceLocation(ItemHolder.roomCard.getRegistryName(), "inventory"));
    }
}
