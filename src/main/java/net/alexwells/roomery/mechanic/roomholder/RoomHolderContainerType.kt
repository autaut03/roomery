package net.alexwells.roomery.mechanic.roomholder

import net.alexwells.roomery.util.getTile
import net.minecraft.client.Minecraft
import net.minecraft.inventory.container.ContainerType
import net.minecraftforge.fml.network.IContainerFactory

object RoomHolderContainerType :
    ContainerType<RoomHolderContainer>(IContainerFactory<RoomHolderContainer> { windowId, playerInventory, extraData ->
        val tile = Minecraft.getInstance().world.getTile<RoomHolderTileEntity>(extraData.readBlockPos())
        val player = Minecraft.getInstance().player

        tile!!.createMenu(windowId, playerInventory, player)
    }) {
    init {
        registryName = ROOM_HOLDER_RESOURCE
    }
}
