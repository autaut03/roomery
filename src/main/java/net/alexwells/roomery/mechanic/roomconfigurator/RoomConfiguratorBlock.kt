package net.alexwells.roomery.mechanic.roomconfigurator

import net.alexwells.roomery.Reference
import net.minecraft.block.Block
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.item.BlockItemUseContext
import net.minecraft.state.DirectionProperty
import net.minecraft.state.StateContainer
import net.minecraft.state.properties.BlockStateProperties
import net.minecraft.util.EnumFacing

object RoomConfiguratorBlock : Block(Block.Builder
        .create(Material.IRON)
        .hardnessAndResistance(2f)
        .sound(SoundType.METAL)
) {
    // Sadly, we have to declare properties inside another object.
    // This is because if you declare them directly, Block()'s
    // constructor will call createBlockState(), which
    // will try to access these properties and get null.
    // These won't be initialized before Block()'s constructor
    // ends what it does.
    private object Properties {
        val FACING: DirectionProperty = BlockStateProperties.FACING
    }

    init {
        registryName = Reference.createResource("room_configurator")
        defaultState = stateContainer.baseState
                .with(Properties.FACING, EnumFacing.NORTH)
    }

    override fun fillStateContainer(builder: StateContainer.Builder<Block, IBlockState>) {
        super.fillStateContainer(builder)

        builder.add(Properties.FACING)
    }

    override fun getStateForPlacement(context: BlockItemUseContext): IBlockState? {
        return defaultState.blockState.with(Properties.FACING, context.nearestLookingDirection.opposite)
    }
}
