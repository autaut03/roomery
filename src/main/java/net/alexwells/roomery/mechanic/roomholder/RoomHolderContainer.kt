package net.alexwells.roomery.mechanic.roomholder

import net.alexwells.roomery.gui.ContainerBase
import net.alexwells.roomery.gui.GuiConstants.INVENTORY_BORDER
import net.alexwells.roomery.gui.GuiConstants.NAME_SPACING
import net.alexwells.roomery.gui.GuiConstants.SLOT_SIZE
import net.minecraft.entity.player.InventoryPlayer
import net.minecraftforge.items.SlotItemHandler

class RoomHolderContainer(playerInventory: InventoryPlayer, tile: RoomHolderTileEntity) : ContainerBase(176, 114 + 6 * 18) {
    init {
        addSlotToContainer(SlotItemHandler(
                tile.itemHandler,
                RoomHolderTileEntity.ROOM_CARD_SLOT,
                INVENTORY_BORDER + 1,
                INVENTORY_BORDER + NAME_SPACING + 5 * SLOT_SIZE + 1
        ))
        addPlayerInventorySlots(playerInventory)
    }
}
