package net.alexwells.roomery.tile

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.entity.player.EntityPlayerMP
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.network.NetworkManager
import net.minecraft.network.play.server.SPacketUpdateTileEntity
import net.minecraft.tileentity.TileEntity

abstract class TileEntityBase : TileEntity() {
    protected fun sendUpdatePacket() {
        if (world.isRemote) {
            return
        }

        val updatePacket = updatePacket ?: return

        for (player in world.playerEntities) {
            if (Math.hypot(player.posX - pos.x + 0.5, player.posZ - pos.z + 0.5) < 64) {
                (player as EntityPlayerMP).connection.sendPacket(updatePacket)
            }
        }
    }

    protected open fun useDefaultUpdatePacket(): Boolean {
        return false
    }

    override fun getUpdatePacket(): SPacketUpdateTileEntity? {
        if (!useDefaultUpdatePacket()) {
            return null
        }

        val tagCompound = NBTTagCompound()
        writeToNBT(tagCompound)

        return SPacketUpdateTileEntity(pos, 1, tagCompound)
    }

    override fun onDataPacket(net: NetworkManager, pkt: SPacketUpdateTileEntity) {
        readFromNBT(pkt.nbtCompound)

        world.markBlockRangeForRenderUpdate(pos, pos)
    }
}
