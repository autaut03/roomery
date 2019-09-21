package net.alexwells.roomery.mechanic.room.world

import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.ChunkPos
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World
import net.minecraft.world.dimension.Dimension
import net.minecraft.world.dimension.DimensionType
import net.minecraft.world.gen.ChunkGenerator

class RoomeryDimension(world: World, type: DimensionType) : Dimension(world, type) {
    /**
     * TODO: check depreciation notice of Forge.
     */
    override fun createChunkGenerator(): ChunkGenerator<*> = RoomeryChunkGenerator(world)

    // TODO: depending on room card's settings
    override fun calculateCelestialAngle(worldTime: Long, partialTicks: Float): Float = 1f

    // todo
    override fun findSpawn(p_206920_1_: ChunkPos, checkValid: Boolean): BlockPos? = null

    override fun findSpawn(p_206921_1_: Int, p_206921_2_: Int, checkValid: Boolean): BlockPos? = null
    override fun canRespawnHere(): Boolean = false

    /**
     * Definitely a surface world (with day/night cycle).
     */
    override fun isSurfaceWorld(): Boolean = true

    /**
     * We definitely don't want fog in there, as it's a void dimension.
     */
    override fun doesXZShowFog(x: Int, z: Int): Boolean = false

    /**
     * TODO: what is this? It's disabled, isn't it?
     */
    override fun getFogColor(p_76562_1_: Float, p_76562_2_: Float): Vec3d = Vec3d(0.2, 0.03, 0.03)
}
