package net.alexwells.roomery.mechanic.roomconfigurator

import net.alexwells.roomery.Roomery
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock

object RoomConfiguratorBlockItem : ItemBlock(RoomConfiguratorBlock, Item.Properties()
        .group(Roomery.itemGroup)
) {
    init {
        registryName = RoomConfiguratorBlock.registryName
    }
}