package net.alexwells.roomery.mechanic.roomholder

import net.alexwells.roomery.Roomery
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock

object RoomHolderBlockItem : ItemBlock(RoomHolderBlock, Item.Properties()
        .group(Roomery.itemGroup)
) {
    init {
        registryName = RoomHolderBlock.registryName
    }
}