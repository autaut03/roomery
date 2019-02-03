package net.alexwells.roomery.util;

import net.alexwells.roomery.Roomery;
import net.alexwells.roomery.gui.GuiEnum;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GuiUtils {
    public static void openGui(EntityPlayer player, GuiEnum guiType, World world, BlockPos pos) {
        openGui(player, guiType, world, pos.getX(), pos.getY(), pos.getZ());
    }

    public static void openGui(EntityPlayer player, GuiEnum guiType, World world, int x, int y, int z) {
        player.openGui(Roomery.instance(), guiType.id, world, x, y, z);
    }
}
