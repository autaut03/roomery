package net.alexwells.roomery.common

import net.minecraft.entity.player.ServerPlayerEntity
import net.minecraft.nbt.CompoundNBT
import net.minecraft.network.NetworkManager
import net.minecraft.network.play.server.SUpdateTileEntityPacket
import net.minecraft.tileentity.TileEntity
import net.minecraft.tileentity.TileEntityType

abstract class TileEntityBase(tileEntityTypeIn: TileEntityType<*>) : TileEntity(tileEntityTypeIn) {
    protected fun sendUpdatePacket() {
        if (world!!.isRemote) {
            return
        }

        val updatePacket = updatePacket ?: return

        for (player in world!!.players) {
            if (Math.hypot(player.posX - pos.x + 0.5, player.posZ - pos.z + 0.5) < 64) {
                (player as ServerPlayerEntity).connection.sendPacket(updatePacket)
            }
        }
    }

    protected open fun useDefaultUpdatePacket() = false

    override fun getUpdatePacket(): SUpdateTileEntityPacket? {
        if (!useDefaultUpdatePacket()) {
            return null
        }

        val tagCompound = CompoundNBT()
        write(tagCompound)

        return SUpdateTileEntityPacket(pos, 1, tagCompound)
    }

    override fun onDataPacket(net: NetworkManager, pkt: SUpdateTileEntityPacket) {
        read(pkt.nbtCompound)
    }
}
