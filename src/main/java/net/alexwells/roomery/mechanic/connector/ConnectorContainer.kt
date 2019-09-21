package net.alexwells.roomery.mechanic.connector

import net.alexwells.roomery.common.gui.ContainerBase
import net.minecraft.entity.player.PlayerInventory

class ConnectorContainer(
    windowId: Int,
    playerInventory: PlayerInventory,
    tile: ConnectorTileEntity
) : ContainerBase(ConnectorContainerType, windowId, 176, 114 + 6 * 18) {
    init {
        addPlayerInventorySlots(playerInventory)
    }
}
