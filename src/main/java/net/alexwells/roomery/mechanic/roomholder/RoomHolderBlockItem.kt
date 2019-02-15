package net.alexwells.roomery.mechanic.roomholder

import net.alexwells.roomery.Reference
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock

        .group(Reference.itemGroup)
object RoomHolderBlockItem : ItemBlock(RoomHolderBlock, Item.Properties()
) {
    init {
        registryName = RoomHolderBlock.registryName
    }
}