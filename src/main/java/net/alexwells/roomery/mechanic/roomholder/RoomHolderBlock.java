package net.alexwells.roomery.mechanic.roomholder;

import net.alexwells.roomery.Roomery;
import net.alexwells.roomery.gui.GuiEnum;
import net.alexwells.roomery.util.GuiUtils;
import net.alexwells.roomery.util.WorldUtils;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class RoomHolderBlock extends Block {
    public static final String NAME = "room_holder";

    public static final PropertyDirection FACING = PropertyDirection.create("facing");
    // Changed from RoomHolderTileEntity
    public static final PropertyBool ACTIVE = PropertyBool.create("active");

    public RoomHolderBlock() {
        super(Material.IRON);

        setHardness(2);
        setRegistryName(Roomery.MOD_ID, NAME);
        setTranslationKey(getRegistryName().toString());
        setDefaultState(blockState.getBaseState()
                .withProperty(FACING, EnumFacing.NORTH)
                .withProperty(ACTIVE, false)
        );
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING, ACTIVE);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(FACING).getIndex();
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(FACING, EnumFacing.byIndex(meta));
    }

    @Override
    public IBlockState getActualState(IBlockState state, IBlockAccess world, BlockPos pos)
    {
        RoomHolderTileEntity tile = WorldUtils.getTileEntity(world, pos);

        return state.withProperty(ACTIVE, tile != null && tile.isActive());
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        return getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer));
    }

    @Override
    public boolean hasTileEntity(IBlockState p_hasTileEntity_1_) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new RoomHolderTileEntity();
    }

    @Override
    public void getDrops(NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        super.getDrops(drops, world, pos, state, fortune);

        RoomHolderTileEntity tile = WorldUtils.getTileEntity(world, pos);

        if(tile == null || tile.getRoomCard() == null) {
            return;
        }

        drops.add(tile.getRoomCard());
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        // If sneaking, let the block be placed
        if(player.isSneaking()) {
            return false;
        }

        // If world is client sided, don't place a block
        if(world.isRemote) {
            return true;
        }

        RoomHolderTileEntity tile = WorldUtils.getTileEntity(world, pos);

        if(tile == null) {
            return false;
        }

        GuiUtils.openGui(player, GuiEnum.ROOM_HOLDER, world, pos);

        return true;
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }
}
