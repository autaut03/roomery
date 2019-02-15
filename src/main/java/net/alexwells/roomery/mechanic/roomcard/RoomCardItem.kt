package net.alexwells.roomery.mechanic.roomcard

import net.alexwells.roomery.Reference
import net.minecraft.item.Item

object RoomCardItem : Item(Item.Properties()
        .maxStackSize(16)
        .group(Reference.itemGroup)
) {
    init {
        registryName = Reference.createResource("room_card")
    }
}
