package net.alexwells.roomery.mechanic.room.world

import net.minecraft.world.biome.provider.SingleBiomeProvider
import net.minecraft.world.biome.provider.SingleBiomeProviderSettings

class RoomeryBiomeProvider : SingleBiomeProvider(
    SingleBiomeProviderSettings()
        .setBiome(RoomeryBiome())
)
