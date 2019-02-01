package net.alexwells.roomery.holder;

import net.alexwells.roomery.Roomery;
import net.alexwells.roomery.item.RoomCardItem;
import net.minecraftforge.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Roomery.MOD_ID)
public class ItemHolder {
    @GameRegistry.ObjectHolder(RoomCardItem.NAME)
    public static final RoomCardItem roomCard = null;
}
