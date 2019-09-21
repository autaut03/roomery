package net.alexwells.roomery.mechanic.roomconfigurator

import net.alexwells.roomery.Roomery
import net.minecraft.item.BlockItem
import net.minecraft.item.Item

object RoomConfiguratorBlockItem : BlockItem(
    RoomConfiguratorBlock, Item.Properties()
        .group(Roomery.itemGroup)
) {
    init {
        registryName = RoomConfiguratorBlock.registryName
    }
}
