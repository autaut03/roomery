package net.alexwells.roomery.mechanic.roomconfigurator

import net.alexwells.roomery.Reference
import net.minecraft.item.Item
import net.minecraft.item.ItemBlock

        .group(Reference.itemGroup)
object RoomConfiguratorBlockItem : ItemBlock(RoomConfiguratorBlock, Item.Properties()
) {
    init {
        registryName = RoomConfiguratorBlock.registryName
    }
}