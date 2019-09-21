package net.alexwells.roomery.common

import net.minecraft.state.EnumProperty

class SideProperty(name: String, values: Collection<SideEnum>) :
    EnumProperty<SideEnum>(name, SideEnum::class.java, values) {
    companion object {
        fun create(name: String): SideProperty = SideProperty(name, SideEnum.values().toMutableSet())
    }
}
