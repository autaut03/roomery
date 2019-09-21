package net.alexwells.roomery

import com.google.common.collect.ImmutableSet
import net.alexwells.roomery.mechanic.connector.ConnectorBlock
import net.alexwells.roomery.mechanic.connector.ConnectorBlockItem
import net.alexwells.roomery.mechanic.connector.ConnectorContainerType
import net.alexwells.roomery.mechanic.connector.ConnectorTileType
import net.alexwells.roomery.mechanic.room.world.RoomeryDimensionType
import net.alexwells.roomery.mechanic.roomcard.RoomCardItem
import net.alexwells.roomery.mechanic.roomconfigurator.RoomConfiguratorBlock
import net.alexwells.roomery.mechanic.roomconfigurator.RoomConfiguratorBlockItem
import net.alexwells.roomery.mechanic.roomholder.RoomHolderBlock
import net.alexwells.roomery.mechanic.roomholder.RoomHolderBlockItem
import net.alexwells.roomery.mechanic.roomholder.RoomHolderContainerType
import net.alexwells.roomery.mechanic.roomholder.RoomHolderTileType
import net.minecraft.block.Block
import net.minecraft.inventory.container.ContainerType
import net.minecraft.item.Item
import net.minecraft.tileentity.TileEntityType
import net.minecraftforge.common.ModDimension

object Registry {
    val blocks: ImmutableSet<Block> = ImmutableSet.of(
        ConnectorBlock,
        RoomHolderBlock,
        RoomConfiguratorBlock
    )

    val tiles: ImmutableSet<TileEntityType<*>> = ImmutableSet.of(
        ConnectorTileType,
        RoomHolderTileType
    )

    val items: ImmutableSet<Item> = ImmutableSet.of(
        ConnectorBlockItem,
        RoomCardItem,
        RoomHolderBlockItem,
        RoomConfiguratorBlockItem
    )

    val dimensions: ImmutableSet<ModDimension> = ImmutableSet.of(
        RoomeryDimensionType
    )

    val containers: ImmutableSet<ContainerType<*>> = ImmutableSet.of(
        ConnectorContainerType,
        RoomHolderContainerType
    )
}
