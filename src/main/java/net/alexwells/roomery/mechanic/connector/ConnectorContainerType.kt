package net.alexwells.roomery.mechanic.connector

import net.alexwells.roomery.util.getTile
import net.minecraft.client.Minecraft
import net.minecraft.inventory.container.ContainerType
import net.minecraftforge.fml.network.IContainerFactory

object ConnectorContainerType :
    ContainerType<ConnectorContainer>(IContainerFactory<ConnectorContainer> { windowId, playerInventory, extraData ->
        val tile = Minecraft.getInstance().world.getTile<ConnectorTileEntity>(extraData.readBlockPos())
        val player = Minecraft.getInstance().player

        tile!!.createMenu(windowId, playerInventory, player)
    }) {
    init {
        registryName = CONNECTOR_RESOURCE
    }
}
