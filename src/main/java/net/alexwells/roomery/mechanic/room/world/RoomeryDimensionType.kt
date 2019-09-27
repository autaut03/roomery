package net.alexwells.roomery.mechanic.room.world

import java.util.function.BiFunction
import net.alexwells.roomery.util.createResource
import net.minecraft.world.World
import net.minecraft.world.dimension.Dimension
import net.minecraft.world.dimension.DimensionType
import net.minecraftforge.common.ModDimension

object RoomeryDimensionType : ModDimension() {
    init {
        registryName = ROOMERY_DIMENSION_RESOURCE
    }

    val type: DimensionType
        get() = DimensionType.byName(registryName!!)!!

    override fun getFactory(): BiFunction<World, DimensionType, out Dimension> =
        BiFunction { world, type -> RoomeryDimension(world, type) }
}
