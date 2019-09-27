package net.alexwells.roomery.common

import net.minecraft.util.Direction
import net.minecraft.util.IStringSerializable

enum class SideEnum(private val namel: String) : IStringSerializable {
    BOTTOM("bottom"),
    TOP("top"),
    FRONT("front"),
    BACK("back"),
    LEFT("left"),
    RIGHT("right");

    override fun getName(): String = namel

    companion object {
        fun fromDirection(direction: Direction, sideableDirection: Direction): SideEnum {
            return when (direction) {
                Direction.UP -> TOP
                Direction.DOWN -> BOTTOM
                else -> when(sideableDirection) {
                    direction -> FRONT
                    direction.opposite -> BACK
                    direction.rotateYCCW() -> LEFT
                    else -> RIGHT
                }
            }
        }
    }
}
