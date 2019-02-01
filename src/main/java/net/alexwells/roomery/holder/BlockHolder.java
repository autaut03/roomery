package net.alexwells.roomery.holder;

import net.alexwells.roomery.Roomery;
import net.alexwells.roomery.block.RoomHolderBlock;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Roomery.MOD_ID)
public class BlockHolder {
    @GameRegistry.ObjectHolder(RoomHolderBlock.NAME)
    public static final RoomHolderBlock roomHolder = null;
}
