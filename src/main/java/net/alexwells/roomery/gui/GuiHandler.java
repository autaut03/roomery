package net.alexwells.roomery.gui;

import net.alexwells.roomery.mechanic.roomholder.RoomHolderGui;
import net.alexwells.roomery.mechanic.roomholder.RoomHolderContainer;
import net.alexwells.roomery.util.WorldUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler {
    @Nullable
    @Override
    public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if(id == GuiEnum.ROOM_HOLDER.id) {
            return new RoomHolderContainer(player.inventory, WorldUtils.getTileEntity(world, new BlockPos(x, y, z)));
        }

        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
        if(id == GuiEnum.ROOM_HOLDER.id) {
            return new RoomHolderGui(player.inventory, WorldUtils.getTileEntity(world, new BlockPos(x, y, z)));
        }

        return null;
    }
}
