package net.alexwells.roomery.mechanic.roomholder

import net.alexwells.roomery.Reference
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock

object RoomHolderBlockItem : ItemBlock(RoomHolderBlock, Item.Builder()
        .group(Reference.itemGroup)
) {
    init {
        registryName = RoomHolderBlock.registryName
    }
}