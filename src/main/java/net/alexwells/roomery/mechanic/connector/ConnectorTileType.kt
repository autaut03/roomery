package net.alexwells.roomery.mechanic.connector

import java.util.function.Supplier
import net.minecraft.tileentity.TileEntityType

object ConnectorTileType : TileEntityType<ConnectorTileEntity>(Supplier { ConnectorTileEntity() }, setOf(ConnectorBlock), null) {
    init {
        registryName = CONNECTOR_RESOURCE
    }
}
