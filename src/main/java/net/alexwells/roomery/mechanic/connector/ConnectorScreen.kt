package net.alexwells.roomery.mechanic.connector

import net.alexwells.roomery.common.gui.ContainerScreen
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.util.text.ITextComponent

class ConnectorScreen(
    container: ConnectorContainer,
    playerInventory: PlayerInventory,
    title: ITextComponent
) : ContainerScreen<ConnectorContainer>(container, playerInventory, title) {
    init {
        this.passEvents = false
        this.ySize = 114 + 6 * 18
    }
}
