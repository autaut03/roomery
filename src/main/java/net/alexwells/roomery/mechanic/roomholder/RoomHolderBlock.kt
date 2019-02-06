package net.alexwells.roomery.mechanic.roomholder

import net.alexwells.roomery.MOD_ID
import net.alexwells.roomery.util.WorldUtils
import net.minecraft.block.*
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.BlockItemUseContext
import net.minecraft.item.ItemStack
import net.minecraft.state.BooleanProperty
import net.minecraft.state.DirectionProperty
import net.minecraft.state.StateContainer
import net.minecraft.state.properties.BlockStateProperties
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.NonNullList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockReader
import net.minecraft.world.World

object RoomHolderBlock : Block(Block.Builder
        .create(Material.IRON)
        .hardnessAndResistance(2f)
        .sound(SoundType.METAL)
) {
    const val NAME = "room_holder"

    // Sadly, we have to declare properties inside another object.
    // This is because if you declare them directly, Block()'s
    // constructor will call createBlockState(), which
    // will try to access these properties and get null.
    // These won't be initialized before Block()'s constructor
    // ends what it does.
    private object Properties {
        val FACING: DirectionProperty = BlockStateProperties.FACING
        val ACTIVE: BooleanProperty = BooleanProperty.create("active")
    }

    init {
        setRegistryName(MOD_ID, NAME)
        defaultState = stateContainer.baseState
                .with(Properties.FACING, EnumFacing.NORTH)
                .with(Properties.ACTIVE, false)
    }

    override fun fillStateContainer(builder: StateContainer.Builder<Block, IBlockState>) {
        super.fillStateContainer(builder)

        builder.add(Properties.FACING, Properties.ACTIVE)
    }

    override fun hasTileEntity(state: IBlockState?) = true

    override fun createTileEntity(state: IBlockState?, world: IBlockReader?) = RoomHolderTileEntity()

    /*override fun getActualState(state: IBlockState, world: IBlockAccess, pos: BlockPos): IBlockState {
        val tile = WorldUtils.getTileEntity<RoomHolderTileEntity>(world, pos)

        return state.withProperty(Properties.ACTIVE, tile != null && tile.isActive())
    }*/

    override fun getStateForPlacement(context: BlockItemUseContext): IBlockState? {
        return defaultState.blockState.with(Properties.FACING, context.nearestLookingDirection.opposite)
    }

    override fun getDrops(state: IBlockState, drops: NonNullList<ItemStack>, world: World, pos: BlockPos, fortune: Int) {
        super.getDrops(state, drops, world, pos, fortune)

        val tile = WorldUtils.getTileEntity<RoomHolderTileEntity>(world, pos)

        if (tile == null || tile.roomCard == null) {
            return
        }

        drops.add(tile.roomCard)
    }

    override fun onBlockActivated(state: IBlockState, world: World, pos: BlockPos, player: EntityPlayer, hand: EnumHand, side: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
        // If sneaking, let the block be placed
        if (player.isSneaking) {
            return false
        }

        // If world is client sided, don't place a block
        if (world.isRemote) {
            return true
        }

        if(WorldUtils.getTileEntity<RoomHolderTileEntity>(world, pos) == null) {
            return false
        }

        // todo
        //GuiUtils.openGui(player, GuiEnum.ROOM_HOLDER, world, pos)

        return true
    }
}
