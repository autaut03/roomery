package net.alexwells.roomery.gui.container;

import net.alexwells.roomery.tile.RoomHolderTileEntity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.items.SlotItemHandler;

public class RoomHolderContainer extends ContainerBase {
    public static final int WIDTH = 176;
    public static final int HEIGHT = 114 + 6 * 18;

    public RoomHolderContainer(InventoryPlayer playerInventory, RoomHolderTileEntity tile)
    {
        super(176, 114 + 6 * 18);

        addSlotToContainer(new SlotItemHandler(
                tile.getItemHandler(),
                RoomHolderTileEntity.ROOM_CARD_SLOT,
                INVENTORY_BORDER + 1,
                INVENTORY_BORDER + NAME_SPACING + 5 * SLOT_SIZE + 1
        ));
        addPlayerInventorySlots(playerInventory);
    }
}
