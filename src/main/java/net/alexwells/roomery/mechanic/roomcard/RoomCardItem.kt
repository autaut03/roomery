package net.alexwells.roomery.mechanic.roomcard

import net.minecraft.item.Item
import net.alexwells.roomery.MOD_ID

object RoomCardItem : Item(Item.Builder()
        .maxStackSize(16)
) {
    const val NAME = "room_card"

    init {
        setRegistryName(MOD_ID, NAME)
    }
}
