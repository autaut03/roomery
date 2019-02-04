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

object RoomConfiguratorBlock : Block(Material.IRON) {
    const val NAME = "room_configurator"

    // Sadly, we have to declare properties inside another object.
    // This is because if you declare them directly, Block()'s
    // constructor will call createBlockState(), which
    // will try to access these properties and get null.
    // These won't be initialized before Block()'s constructor
    // ends what it does.
    private object Properties {
        val FACING: PropertyDirection = PropertyDirection.create("facing")
    }

    init {
        setHardness(2f)
        setRegistryName(MOD_ID, NAME)
        translationKey = registryName.toString()
        creativeTab = Roomery.creativeTab
        defaultState = blockState.baseState
                .withProperty(Properties.FACING, EnumFacing.NORTH)
    }

    override fun createBlockState() = BlockStateContainer(this, Properties.FACING)

    override fun getMetaFromState(state: IBlockState) = state.getValue(Properties.FACING).index

    override fun getStateFromMeta(meta: Int) = defaultState.withProperty(Properties.FACING, EnumFacing.byIndex(meta))

    override fun getStateForPlacement(world: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, meta: Int, placer: EntityLivingBase, hand: EnumHand?): IBlockState {
        return defaultState.withProperty(Properties.FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer))
    }
}
