package net.alexwells.roomery

import com.google.common.collect.ImmutableSet
import net.alexwells.roomery.mechanic.roomcard.RoomCardItem
import net.alexwells.roomery.mechanic.roomconfigurator.RoomConfiguratorBlock
import net.alexwells.roomery.mechanic.roomholder.RoomHolderBlock
import net.alexwells.roomery.mechanic.roomholder.RoomHolderTileEntity
import net.alexwells.roomery.mechanic.roomholder.RoomHolderTileType
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.tileentity.TileEntity
import net.minecraft.tileentity.TileEntityType
import java.util.function.Supplier
import kotlin.reflect.KClass

object Registry {
    data class BlockDTO(
            val block: Block,
            val hasItem: Boolean = true,
            val itemBuilder: Item.Builder? = null
    )

    val blocks: ImmutableSet<BlockDTO> = ImmutableSet.of(
            BlockDTO(RoomHolderBlock),
            BlockDTO(RoomConfiguratorBlock)
    )

    val tiles: ImmutableSet<TileEntityType<*>> = ImmutableSet.of(
            RoomHolderTileType
    )

    val items: ImmutableSet<Item> = ImmutableSet.of(
            RoomCardItem
    )
}