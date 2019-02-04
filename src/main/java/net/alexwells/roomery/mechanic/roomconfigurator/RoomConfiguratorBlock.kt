package net.alexwells.roomery.mechanic.roomconfigurator

import net.alexwells.roomery.Constants.MOD_ID
import net.alexwells.roomery.Roomery
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyDirection
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class RoomConfiguratorBlock : Block(Material.IRON) {
    init {

        setHardness(2f)
        setRegistryName(MOD_ID, NAME)
        translationKey = registryName.toString()
        creativeTab = Roomery.creativeTab
        defaultState = blockState.baseState
                .withProperty(FACING, EnumFacing.NORTH)
    }

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, FACING)
    }

    override fun getMetaFromState(state: IBlockState): Int {
        return state.getValue(FACING).index
    }

    override fun getStateFromMeta(meta: Int): IBlockState {
        return defaultState.withProperty(FACING, EnumFacing.byIndex(meta))
    }

    override fun getStateForPlacement(world: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, meta: Int, placer: EntityLivingBase, hand: EnumHand?): IBlockState {
        return defaultState.withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer))
    }

    companion object {
        const val NAME = "room_configurator"

        val FACING = PropertyDirection.create("facing")
    }
}
