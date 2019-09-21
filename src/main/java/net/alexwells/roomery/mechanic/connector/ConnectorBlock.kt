package net.alexwells.roomery.mechanic.connector

import net.alexwells.roomery.mechanic.roomholder.RoomHolderTileEntity
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.world.IBlockReader

object ConnectorBlock : Block(
    Block.Properties
        .create(Material.IRON)
        .hardnessAndResistance(2f)
        .sound(SoundType.METAL)
) {
    init {
        registryName = CONNECTOR_RESOURCE
        defaultState = stateContainer.baseState
    }

    override fun hasTileEntity(state: BlockState?) = true

    override fun createTileEntity(state: BlockState?, world: IBlockReader?) = RoomHolderTileEntity()
}
