package net.alexwells.roomery.mechanic.roomholder

import net.alexwells.roomery.common.gui.ContainerBase
import net.alexwells.roomery.common.gui.GuiConstants.INVENTORY_BORDER
import net.alexwells.roomery.common.gui.GuiConstants.NAME_SPACING
import net.alexwells.roomery.common.gui.GuiConstants.SLOT_SIZE
import net.minecraft.entity.player.PlayerInventory
import net.minecraftforge.items.SlotItemHandler

class RoomHolderContainer(
    windowId: Int,
    playerInventory: PlayerInventory,
    tile: RoomHolderTileEntity
) : ContainerBase(RoomHolderContainerType, windowId, 176, 114 + 6 * 18) {
    init {
        addSlot(
            SlotItemHandler(
                tile.itemHandler,
                RoomHolderTileEntity.ROOM_CARD_SLOT,
                INVENTORY_BORDER + 1,
                INVENTORY_BORDER + NAME_SPACING + 5 * SLOT_SIZE + 1
            )
        )
        addPlayerInventorySlots(playerInventory)
    }
}
