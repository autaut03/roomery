package net.alexwells.roomery.mechanic.roomholder

import net.alexwells.roomery.Roomery
import net.minecraft.item.BlockItem
import net.minecraft.item.Item

object RoomHolderBlockItem : BlockItem(
    RoomHolderBlock, Item.Properties()
        .group(Roomery.itemGroup)
) {
    init {
        registryName = ROOM_HOLDER_RESOURCE
    }
}
