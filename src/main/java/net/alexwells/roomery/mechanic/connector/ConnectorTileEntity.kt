package net.alexwells.roomery.mechanic.connector

import net.alexwells.roomery.common.TileEntityBase
import net.alexwells.roomery.mechanic.room.Room
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.container.INamedContainerProvider
import net.minecraft.util.text.ITextComponent

class ConnectorTileEntity : TileEntityBase(ConnectorTileType), INamedContainerProvider {
    private var roomReference: Room? = null

    override fun createMenu(windowId: Int, playerInventory: PlayerInventory, player: PlayerEntity): ConnectorContainer {
        return ConnectorContainer(windowId, playerInventory, this)
    }

    override fun getDisplayName(): ITextComponent = ConnectorBlock.nameTextComponent
}
