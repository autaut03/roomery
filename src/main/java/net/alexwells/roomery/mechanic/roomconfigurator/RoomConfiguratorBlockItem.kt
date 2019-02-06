package net.alexwells.roomery.mechanic.roomconfigurator

import net.alexwells.roomery.Reference
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock

object RoomConfiguratorBlockItem : ItemBlock(RoomConfiguratorBlock, Item.Builder()
        .group(Reference.itemGroup)
) {
    init {
        registryName = RoomConfiguratorBlock.registryName
    }
}