package net.alexwells.roomery.mechanic.room.world

import net.minecraft.world.biome.Biome
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder

// todo: per-room biomes
class RoomeryBiome : Biome(
    Biome.Builder()
        .surfaceBuilder(SurfaceBuilder.NOPE, SurfaceBuilder.STONE_STONE_GRAVEL_CONFIG)
        .precipitation(RainType.NONE)
        .category(Category.NONE)
        .depth(0.1F)
        .scale(0.2F)
        .temperature(0.5F)
        .downfall(0.5F)
        .waterColor(4159204)
        .waterFogColor(329011)
        .parent(null)
)
