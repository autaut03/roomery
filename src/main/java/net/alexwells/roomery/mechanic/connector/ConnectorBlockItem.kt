package net.alexwells.roomery.mechanic.connector

import net.alexwells.roomery.Roomery
import net.minecraft.item.BlockItem
import net.minecraft.item.Item

object ConnectorBlockItem : BlockItem(
    ConnectorBlock, Item.Properties()
        .group(Roomery.itemGroup)
) {
    init {
        registryName = CONNECTOR_RESOURCE
    }
}
