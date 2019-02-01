package net.alexwells.roomery.tile;

import net.alexwells.roomery.item.RoomCardItem;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class RoomHolderTileEntity extends TileEntity {
    private RoomCardItem roomCard;

    @Nullable
    public Item getRoomCard()
    {
        return this.roomCard;
    }

    //  todo: Whut?
    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState) {
        return super.shouldRefresh(world, pos, oldState, newState);
    }
}
