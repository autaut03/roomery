package net.alexwells.roomery.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public abstract class ContainerBase extends Container {
    public static final int NAME_SPACING = 10;
    public static final int PLAYER_INVENTORY_HOTBAR_SPACING = 4;
    public static final int SLOT_SIZE = 18;
    public static final int INVENTORY_BORDER = 7;
    public static final int PLAYER_INVENTORY_HEIGHT = PLAYER_INVENTORY_HOTBAR_SPACING + SLOT_SIZE * 4 + INVENTORY_BORDER;
    public static final int MINIMAL_INVENTORY_WIDTH = INVENTORY_BORDER * 2 + SLOT_SIZE * 9;

    private final int width;
    private final int height;
    private boolean hasPlayerSlots = false;

    public ContainerBase(int width, int height)
    {
        this.width = Math.max(width, MINIMAL_INVENTORY_WIDTH);
        this.height = height;
    }

    protected void addPlayerInventorySlots(InventoryPlayer playerInventory) {
        this.hasPlayerSlots = true;

        // Magic const +1 fixes slots' positions. For unknown reason they are always 1px to the left and up.
        int xPlayerInv = (width - SLOT_SIZE * 9) / 2 + 1;
        int yPlayerInv = height - PLAYER_INVENTORY_HEIGHT + 1;

        for (int i = 0; i < 3; ++i)
        {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, xPlayerInv + j * SLOT_SIZE, yPlayerInv + i * SLOT_SIZE));
            }
        }

        for (int k = 0; k < 9; ++k)
        {
            this.addSlotToContainer(new Slot(playerInventory, k, xPlayerInv + k * SLOT_SIZE, yPlayerInv + SLOT_SIZE * 3 + PLAYER_INVENTORY_HOTBAR_SPACING));
        }
    }

    // Taken from CoFH Core. Thanks guys.
    // Your licensing is great. I promise I'm not a jerk.
    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index) {
        Slot slot = this.inventorySlots.get(index);

        if(slot == null || !slot.getHasStack()) {
            return ItemStack.EMPTY;
        }

        ItemStack stackInSlot = slot.getStack();
        ItemStack returnStack = stackInSlot.copy();

        if (!performMerge(index, stackInSlot)) {
            return ItemStack.EMPTY;
        }

        slot.onSlotChange(stackInSlot, returnStack);

        if (stackInSlot.getCount() <= 0) {
            slot.putStack(ItemStack.EMPTY);
        } else {
            slot.putStack(stackInSlot);
        }

        if (stackInSlot.getCount() == returnStack.getCount()) {
            return ItemStack.EMPTY;
        }

        slot.onTake(player, stackInSlot);

        return returnStack;
    }

    protected int getLastNonPlayerInvIndex() {
        return inventorySlots.size() - (hasPlayerSlots ? 36 : 0);
    }

    protected boolean performMerge(int slotIndex, ItemStack stack) {

        int invBase = getLastNonPlayerInvIndex();
        int invFull = inventorySlots.size();

        if (slotIndex < invBase) {
            return mergeItemStack(stack, invBase, invFull, true);
        }
        return mergeItemStack(stack, 0, invBase, false);
    }

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return !player.isSpectator();
    }
}
