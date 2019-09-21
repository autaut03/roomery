package net.alexwells.roomery.mechanic.room

import net.alexwells.roomery.MOD_ID
import net.alexwells.roomery.common.SideEnum
import net.alexwells.roomery.mechanic.roomholder.RoomHolderBlock
import net.alexwells.roomery.mechanic.roomholder.RoomHolderTileEntity
import net.minecraft.nbt.CompoundNBT
import net.minecraft.world.storage.WorldSavedData
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.util.LazyOptional

class Room(private val id: String) : WorldSavedData(MOD_ID + "_rooms_" + id) {
    private var holder: RoomHolderTileEntity? = null

    fun <T> getOutsideCapability(cap: Capability<T>, side: SideEnum): LazyOptional<T> {
        return holder!!.getCapability(
            cap,
            side.facingRelativeTo(holder!!.blockState.get(RoomHolderBlock.Properties.FACING))
        )
    }

    fun <T> getInsideCapability(cap: Capability<T>, side: SideEnum): LazyOptional<T> {
        // todo
        return LazyOptional.empty()
    }

    override fun read(nbt: CompoundNBT) {
    }

    override fun write(nbt: CompoundNBT): CompoundNBT {

        return nbt
    }
}
