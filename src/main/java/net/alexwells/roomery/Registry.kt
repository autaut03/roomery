package net.alexwells.roomery

import com.google.common.collect.ImmutableSet
import net.alexwells.roomery.mechanic.roomcard.RoomCardItem
import net.alexwells.roomery.mechanic.roomconfigurator.RoomConfiguratorBlock
import net.alexwells.roomery.mechanic.roomconfigurator.RoomConfiguratorBlockItem
import net.alexwells.roomery.mechanic.roomholder.RoomHolderBlock
import net.alexwells.roomery.mechanic.roomholder.RoomHolderBlockItem
import net.alexwells.roomery.mechanic.roomholder.RoomHolderTileType
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.tileentity.TileEntityType

object Registry {
    val blocks: ImmutableSet<Block> = ImmutableSet.of(
            RoomHolderBlock,
            RoomConfiguratorBlock
    )

    val tiles: ImmutableSet<TileEntityType<*>> = ImmutableSet.of(
            RoomHolderTileType
    )

    val items: ImmutableSet<Item> = ImmutableSet.of(
            RoomCardItem,
            RoomHolderBlockItem,
            RoomConfiguratorBlockItem
    )
}