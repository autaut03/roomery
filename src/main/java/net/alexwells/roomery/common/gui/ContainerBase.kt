package net.alexwells.roomery.common.gui

import net.alexwells.roomery.common.gui.GuiConstants.MINIMAL_INVENTORY_WIDTH
import net.alexwells.roomery.common.gui.GuiConstants.PLAYER_INVENTORY_HEIGHT
import net.alexwells.roomery.common.gui.GuiConstants.PLAYER_INVENTORY_HOTBAR_SPACING
import net.alexwells.roomery.common.gui.GuiConstants.SLOT_SIZE
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.container.Container
import net.minecraft.inventory.container.ContainerType
import net.minecraft.inventory.container.Slot
import net.minecraft.item.ItemStack

abstract class ContainerBase(
    type: ContainerType<*>,
    windowId: Int,
    width: Int,
    private val height: Int
) : Container(type, windowId) {
    private val width: Int = Math.max(width, MINIMAL_INVENTORY_WIDTH)
    private var hasPlayerSlots = false

    private val lastNonPlayerInvIndex: Int
        get() = inventorySlots.size - if (hasPlayerSlots) 36 else 0

    protected fun addPlayerInventorySlots(playerInventory: PlayerInventory) {
        this.hasPlayerSlots = true

        // Magic const +1 fixes slots' positions. For unknown reason they are always 1px to the left and up.
        val xPlayerInv = (width - SLOT_SIZE * 9) / 2 + 1
        val yPlayerInv = height - PLAYER_INVENTORY_HEIGHT + 1

        for (i in 0..2) {
            for (j in 0..8) {
                this.addSlot(
                    Slot(
                        playerInventory,
                        j + i * 9 + 9,
                        xPlayerInv + j * SLOT_SIZE,
                        yPlayerInv + i * SLOT_SIZE
                    )
                )
            }
        }

        for (k in 0..8) {
            this.addSlot(
                Slot(
                    playerInventory,
                    k,
                    xPlayerInv + k * SLOT_SIZE,
                    yPlayerInv + SLOT_SIZE * 3 + PLAYER_INVENTORY_HOTBAR_SPACING
                )
            )
        }
    }

    // Taken from CoFH Core. Thanks guys.
    // Your licensing is great. I promise I'm not a jerk.
    override fun transferStackInSlot(player: PlayerEntity, index: Int): ItemStack {
        val slot = this.inventorySlots[index]

        if (slot == null || !slot.hasStack) {
            return ItemStack.EMPTY
        }

        val stackInSlot = slot.stack
        val returnStack = stackInSlot.copy()

        if (!performMerge(index, stackInSlot)) {
            return ItemStack.EMPTY
        }

        slot.onSlotChange(stackInSlot, returnStack)

        if (stackInSlot.count <= 0) {
            slot.putStack(ItemStack.EMPTY)
        } else {
            slot.putStack(stackInSlot)
        }

        if (stackInSlot.count == returnStack.count) {
            return ItemStack.EMPTY
        }

        slot.onTake(player, stackInSlot)

        return returnStack
    }

    protected fun performMerge(slotIndex: Int, stack: ItemStack): Boolean {

        val invBase = lastNonPlayerInvIndex
        val invFull = inventorySlots.size

        return if (slotIndex < invBase) {
            mergeItemStack(stack, invBase, invFull, true)
        } else mergeItemStack(stack, 0, invBase, false)
    }

    override fun canInteractWith(player: PlayerEntity) = !player.isSpectator
}
