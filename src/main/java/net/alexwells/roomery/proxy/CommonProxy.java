package net.alexwells.roomery.proxy;

import net.alexwells.roomery.Roomery;
import net.alexwells.roomery.block.RoomHolderBlock;
import net.alexwells.roomery.holder.BlockHolder;
import net.alexwells.roomery.item.RoomCardItem;
import net.alexwells.roomery.tile.RoomHolderTileEntity;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {
    @SubscribeEvent
    public void registerBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().register(new RoomHolderBlock());
        GameRegistry.registerTileEntity(RoomHolderTileEntity.class, new ResourceLocation(Roomery.MOD_ID, RoomHolderBlock.NAME));
    }

    @SuppressWarnings("ConstantConditions")
    @SubscribeEvent
    public void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new RoomCardItem());

        event.getRegistry().register(new ItemBlock(BlockHolder.roomHolder).setRegistryName(Roomery.MOD_ID, RoomHolderBlock.NAME));
    }
}
