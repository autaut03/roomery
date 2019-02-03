package net.alexwells.roomery.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityBase extends TileEntity {
    protected void sendUpdatePacket() {
        if(world.isRemote) {
            return;
        }

        SPacketUpdateTileEntity updatePacket = getUpdatePacket();

        if(updatePacket == null) {
            return;
        }

        for(EntityPlayer player : world.playerEntities) {
            if (Math.hypot(player.posX - pos.getX() + 0.5, player.posZ - pos.getZ() + 0.5) < 64) {
                ((EntityPlayerMP) player).connection.sendPacket(updatePacket);
            }
        }
    }

    protected boolean useDefaultUpdatePacket() {
        return false;
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        if(!useDefaultUpdatePacket()) {
            return null;
        }

        NBTTagCompound tagCompound = new NBTTagCompound();
        writeToNBT(tagCompound);

        return new SPacketUpdateTileEntity(pos, 1, tagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity pkt) {
        readFromNBT(pkt.getNbtCompound());

        world.markBlockRangeForRenderUpdate(pos, pos);
    }
}
