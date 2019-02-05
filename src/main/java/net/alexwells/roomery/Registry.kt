package net.alexwells.roomery

import com.google.common.collect.ImmutableSet
import net.alexwells.roomery.mechanic.roomcard.RoomCardItem
import net.alexwells.roomery.mechanic.roomconfigurator.RoomConfiguratorBlock
import net.alexwells.roomery.mechanic.roomholder.RoomHolderBlock
import net.alexwells.roomery.mechanic.roomholder.RoomHolderTileEntity
import net.minecraft.block.Block
import net.minecraft.item.Item
import net.minecraft.tileentity.TileEntity
import kotlin.reflect.KClass

object Registry {
    data class BlockDTO(val block: Block, val tile: KClass<out TileEntity>? = null, val hasItem: Boolean = true)

    val blocks: ImmutableSet<BlockDTO> = ImmutableSet.of(
            BlockDTO(RoomHolderBlock, RoomHolderTileEntity::class),
            BlockDTO(RoomConfiguratorBlock)
    )

    val items: ImmutableSet<Item> = ImmutableSet.of(
            RoomCardItem
    )
}