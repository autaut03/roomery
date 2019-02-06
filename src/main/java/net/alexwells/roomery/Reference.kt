package net.alexwells.roomery

import net.alexwells.roomery.mechanic.roomcard.RoomCardItem
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraft.util.ResourceLocation

object Reference {
    const val MOD_ID = "roomery"

    val itemGroup = object : ItemGroup(MOD_ID) {
        override fun createIcon() = ItemStack(RoomCardItem)
    }

    fun createResource(path: String): ResourceLocation = ResourceLocation(MOD_ID, path)
}

