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

    fun toDirection(sideableDirection: Direction): Direction {
        return when (this) {
            FRONT -> sideableDirection
            BACK -> sideableDirection.opposite
            LEFT -> sideableDirection.rotateY().rotateY().rotateY()
            RIGHT -> sideableDirection.rotateY()
            TOP -> sideableDirection.rotateYCCW()
            BOTTOM -> sideableDirection.rotateYCCW().rotateYCCW().rotateYCCW()
        }
    }

    companion object {
        fun fromDirection(direction: Direction, sideableDirection: Direction): SideEnum {
            return when (direction) {
                Direction.UP -> TOP
                Direction.DOWN -> BOTTOM
                else -> when (sideableDirection) {
                    direction -> FRONT
                    direction.opposite -> BACK
                    direction.rotateYCCW() -> LEFT
                    else -> RIGHT
                }
            }
        }
    }
}
