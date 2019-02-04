package net.alexwells.roomery.mechanic.roomholder

import net.alexwells.roomery.Constants.MOD_ID
import net.alexwells.roomery.Roomery
import net.alexwells.roomery.gui.GuiEnum
import net.alexwells.roomery.util.GuiUtils
import net.alexwells.roomery.util.WorldUtils
import net.minecraft.block.*
import net.minecraft.block.material.Material
import net.minecraft.block.properties.PropertyBool
import net.minecraft.block.properties.PropertyDirection
import net.minecraft.block.state.BlockStateContainer
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.EnumFacing
import net.minecraft.util.EnumHand
import net.minecraft.util.NonNullList
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess
import net.minecraft.world.World

object RoomHolderBlock : Block(Material.IRON) {
    const val NAME = "room_holder"

    // Sadly, we have to declare properties inside another object.
    // This is because if you declare them directly, Block()'s
    // constructor will call createBlockState(), which
    // will try to access these properties and get null.
    // These won't be initialized before Block()'s constructor
    // ends what it does.
    private object Properties {
        val FACING: PropertyDirection = PropertyDirection.create("facing")
        val ACTIVE: PropertyBool = PropertyBool.create("active")
    }

    init {
        setHardness(2f)
        setRegistryName(MOD_ID, NAME)
        translationKey = registryName.toString()
        creativeTab = Roomery.creativeTab
        defaultState = blockState.baseState
                .withProperty(Properties.FACING, EnumFacing.NORTH)
                .withProperty(Properties.ACTIVE, false)
    }

    override fun createBlockState(): BlockStateContainer {
        return BlockStateContainer(this, Properties.FACING, Properties.ACTIVE)
    }

    override fun getMetaFromState(state: IBlockState): Int {
        return state.getValue(Properties.FACING).index
    }

    override fun getStateFromMeta(meta: Int): IBlockState {
        return defaultState.withProperty(Properties.FACING, EnumFacing.byIndex(meta))
    }

    override fun getActualState(state: IBlockState, world: IBlockAccess, pos: BlockPos): IBlockState {
        val tile = WorldUtils.getTileEntity<RoomHolderTileEntity>(world, pos)

        return state.withProperty(Properties.ACTIVE, tile != null && tile.isActive)
    }

    override fun getStateForPlacement(world: World, pos: BlockPos, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float, meta: Int, placer: EntityLivingBase, hand: EnumHand?): IBlockState {
        return defaultState.withProperty(Properties.FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer))
    }

    override fun hasTileEntity(p_hasTileEntity_1_: IBlockState?): Boolean {
        return true
    }

    override fun createTileEntity(world: World, state: IBlockState): TileEntity? {
        return RoomHolderTileEntity()
    }

    override fun getDrops(drops: NonNullList<ItemStack>, world: IBlockAccess, pos: BlockPos, state: IBlockState, fortune: Int) {
        super.getDrops(drops, world, pos, state, fortune)

        val tile = WorldUtils.getTileEntity<RoomHolderTileEntity>(world, pos)

        if (tile == null || tile.roomCard == null) {
            return
        }

        drops.add(tile.roomCard)
    }

    override fun onBlockActivated(world: World, pos: BlockPos, state: IBlockState, player: EntityPlayer, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean {
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

        GuiUtils.openGui(player, GuiEnum.ROOM_HOLDER, world, pos)

        return true
    }
}
