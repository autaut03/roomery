package net.alexwells.roomery.mechanic.room

import net.minecraft.world.dimension.DimensionType
import net.minecraftforge.fml.server.ServerLifecycleHooks

object RoomManager {
    /**
     * Gets a Room instance by ID. If not found in cache, tries to load it from the disk. Otherwise null.
     */
    fun get(id: String): Room? {
        val name = nameForId(id)

        return storage().getOrCreate(
            { Room(id, name) },
            name
        )
    }

    private fun nameForId(id: String): String = "room_$id"
    private fun storage() = ServerLifecycleHooks.getCurrentServer().getWorld(DimensionType.OVERWORLD).savedData
}
