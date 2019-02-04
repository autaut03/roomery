package net.alexwells.roomery.mechanic.roomcard

import net.alexwells.roomery.Roomery
import net.minecraft.item.Item

import net.alexwells.roomery.Constants.MOD_ID

object RoomCardItem : Item() {
    const val NAME = "room_card"

    init {
        maxStackSize = 16
        setRegistryName(MOD_ID, NAME)
        translationKey = registryName.toString()
        creativeTab = Roomery.creativeTab
    }
}
