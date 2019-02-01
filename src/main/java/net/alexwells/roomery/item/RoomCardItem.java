package net.alexwells.roomery.item;

import net.alexwells.roomery.Roomery;
import net.minecraft.item.Item;

public class RoomCardItem extends Item {
    public static final String NAME = "room_card";

    public RoomCardItem() {
        super();

        setMaxStackSize(1);
        setRegistryName(Roomery.MOD_ID, NAME);
        setTranslationKey(getRegistryName().toString());
    }
}
