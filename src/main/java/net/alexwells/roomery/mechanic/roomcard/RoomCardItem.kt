package net.alexwells.roomery.mechanic.roomcard

import net.alexwells.roomery.Roomery
import net.minecraft.item.Item

object RoomCardItem : Item(Item.Properties()
        .maxStackSize(16)
        .group(Roomery.itemGroup)
) {
    init {
        registryName = Roomery.createResource("room_card")
    }
}
