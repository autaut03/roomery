package net.alexwells.roomery.mechanic.roomcard

import net.alexwells.roomery.Roomery
import net.alexwells.roomery.util.createResource
import net.minecraft.item.Item
import net.minecraft.item.ItemStack

object RoomCardItem : Item(
    Item.Properties()
        .maxStackSize(16)
        .group(Roomery.itemGroup)
) {
    const val ID_TAG = "Id"

    init {
        registryName = createResource("room_card")
    }

    fun isValid(stack: ItemStack): Boolean = stack.item == this && stack.hasTag() && stack.tag!!.contains(ID_TAG)
    fun getId(stack: ItemStack): String? = if (isValid(stack)) stack.tag!!.getString(ID_TAG) else null
}
