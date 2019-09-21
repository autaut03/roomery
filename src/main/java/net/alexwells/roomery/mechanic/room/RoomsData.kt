package net.alexwells.roomery.mechanic.room

import net.minecraft.world.World

object RoomsData {
    fun getById(world: World, id: String): Room? {
        return null
        /*return world.getSavedData<Room>(
                RoomeryDimensionType.type,
                { name -> Room(name) },
                id
        )*/
    }
}
