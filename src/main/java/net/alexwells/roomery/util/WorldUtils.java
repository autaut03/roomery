package net.alexwells.roomery.util;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class WorldUtils {
    public static <T extends TileEntity> T getTileEntity(IBlockAccess world, BlockPos pos) {
        TileEntity tileEntity = world.getTileEntity(pos);

        try {
            return (T) tileEntity;
        } catch (ClassCastException ignored) {

        }

        return null;
    }
}
