package net.alexwells.roomery.mechanic.roomconfigurator

import net.alexwells.roomery.util.createResource
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.SoundType
import net.minecraft.block.material.Material
import net.minecraft.item.BlockItemUseContext
import net.minecraft.state.DirectionProperty
import net.minecraft.state.StateContainer
import net.minecraft.state.properties.BlockStateProperties
import net.minecraft.util.Direction

object RoomConfiguratorBlock : Block(
    Block.Properties
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
        registryName = createResource("room_configurator")
        defaultState = stateContainer.baseState
            .with(Properties.FACING, Direction.NORTH)
    }

    override fun fillStateContainer(builder: StateContainer.Builder<Block, BlockState>) {
        super.fillStateContainer(builder)

        builder.add(Properties.FACING)
    }

    override fun getStateForPlacement(context: BlockItemUseContext): BlockState? {
        return defaultState.blockState.with(Properties.FACING, context.nearestLookingDirection.opposite)
    }
}
