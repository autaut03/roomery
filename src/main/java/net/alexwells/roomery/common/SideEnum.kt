package net.alexwells.roomery.common

import java.lang.RuntimeException
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

    fun facingRelativeTo(facing: Direction): Direction {
        return when (this) {
            FRONT -> facing
            BACK -> facing.opposite
            LEFT -> facing.rotateY().opposite
            RIGHT -> facing.rotateY()
            else -> throw RuntimeException("Can not calculate facingRelative to given facing pointing $facing")
        }
    }
}
