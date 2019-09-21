package net.alexwells.roomery.mechanic.room.world

import net.minecraft.entity.EntityClassification
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IWorld
import net.minecraft.world.World
import net.minecraft.world.biome.Biome
import net.minecraft.world.chunk.IChunk
import net.minecraft.world.gen.ChunkGenerator
import net.minecraft.world.gen.GenerationStage
import net.minecraft.world.gen.Heightmap
import net.minecraft.world.gen.WorldGenRegion
import net.minecraft.world.gen.feature.IFeatureConfig
import net.minecraft.world.gen.feature.structure.Structure
import net.minecraft.world.gen.feature.template.TemplateManager

class RoomeryChunkGenerator(world: IWorld) : ChunkGenerator<RoomeryGenerationSettings>(
    world,
    RoomeryBiomeProvider(),
    RoomeryGenerationSettings()
) {
    /**
     * TODO: make this configurable on per-room basis.
     */
    override fun getPossibleCreatures(type: EntityClassification, pos: BlockPos): MutableList<Biome.SpawnListEntry> =
        ArrayList()

    /**
     * Ground starts at 0.
     */
    override fun getGroundHeight(): Int = 0

    /**
     * Max height is 128 as it's highly unlikely that 128 from bottom to the top won't be enough.
     */
    override fun getMaxHeight(): Int = 128

    /**
     * Seed for void world is always the same.
     */
    override fun getSeed(): Long = 0

    /**
     * No world generation, basically.
     */
    override fun makeBase(p0: IWorld, p1: IChunk) {}

    override fun generateSurface(p0: IChunk) {}
    override fun decorate(region: WorldGenRegion) {}
    override fun carve(p_222538_1_: IChunk, p_222538_2_: GenerationStage.Carving) {}

    /**
     * No structures.
     */
    override fun initStructureStarts(
        p_222533_1_: IChunk,
        p_222533_2_: ChunkGenerator<*>,
        p_222533_3_: TemplateManager
    ) {
    }

    override fun generateStructureStarts(p_222528_1_: IWorld, p_222528_2_: IChunk) {}
    override fun <C : IFeatureConfig?> getStructureConfig(
        p_202087_1_: Biome,
        p_202087_2_: Structure<C>
    ): C? = null

    override fun findNearestStructure(p0: World, p1: String, p2: BlockPos, p3: Int, p4: Boolean): BlockPos? = null
    override fun hasStructure(p0: Biome, p1: Structure<out IFeatureConfig>): Boolean = false
    override fun func_222529_a(p0: Int, p1: Int, p2: Heightmap.Type): Int = 0
}
