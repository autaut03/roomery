package net.alexwells.roomery.block;

import net.alexwells.roomery.Roomery;
import net.alexwells.roomery.tile.RoomHolderTileEntity;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import javax.annotation.Nullable;

public class RoomHolderBlock extends Block {
    public static final String NAME = "room_holder";

    public static final PropertyDirection FACING = PropertyDirection.create("facing");
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
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        TileEntity tileEntity = worldIn instanceof ChunkCache
                ? ((ChunkCache)worldIn).getTileEntity(pos, Chunk.EnumCreateEntityType.CHECK)
                : worldIn.getTileEntity(pos);

        if (!(tileEntity instanceof RoomHolderTileEntity)) {
            return state.withProperty(ACTIVE, false);
        }

        RoomHolderTileEntity tileRoomHolder = (RoomHolderTileEntity) tileEntity;
        Item item = tileRoomHolder.getRoomCard();

        return state.withProperty(ACTIVE, item != null);
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
    public TileEntity createTileEntity(World p_createTileEntity_1_, IBlockState p_createTileEntity_2_) {
        return new RoomHolderTileEntity();
    }
}
