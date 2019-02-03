package net.alexwells.roomery.mechanic.roomholder;

import net.alexwells.roomery.holder.ItemHolder;
import net.alexwells.roomery.tile.TileEntityBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RoomHolderTileEntity extends TileEntityBase implements ICapabilityProvider {
    private static final String ITEM_HANDLER_TAG = "ItemHandler";
    public static final int ROOM_CARD_SLOT = 0;

    private ItemStackHandler itemHandler;

    public RoomHolderTileEntity() {
        this.itemHandler = new ItemStackHandler(1) {
            @Override
            protected void onContentsChanged(int slot) {
                super.onContentsChanged(slot);
                markDirty();

                // Here we should tell the client that this block has changed in some way
                // client will receive this and trigger world.markBlockRangeForRenderUpdate()
                sendUpdatePacket();
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(!isItemValid(slot, stack)) {
                    return stack;
                }

                return super.insertItem(slot, stack, simulate);
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                if(slot == ROOM_CARD_SLOT) {
                    return stack.getItem() == ItemHolder.roomCard;
                }

                return super.isItemValid(slot, stack);
            }

            @Override
            public int getSlotLimit(int slot) {
                if(slot == ROOM_CARD_SLOT) {
                    return 1;
                }

                return super.getSlotLimit(slot);
            }
        };
    }

    @Override
    protected boolean useDefaultUpdatePacket() {
        return true;
    }

    @Nullable
    public ItemStack getRoomCard()
    {
        return itemHandler.getStackInSlot(ROOM_CARD_SLOT);
    }

    public boolean isActive() {
        return hasValidRoomCard();
    }

    private boolean hasValidRoomCard() {
        return getRoomCard().getItem() == ItemHolder.roomCard;
    }

    public ItemStackHandler getItemHandler() {
        return itemHandler;
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        this.itemHandler.deserializeNBT(compound.getCompoundTag(ITEM_HANDLER_TAG));

        super.readFromNBT(compound);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setTag(ITEM_HANDLER_TAG, this.itemHandler.serializeNBT());

        return super.writeToNBT(compound);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return true;
        }

        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        if(capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return (T) this.getItemHandler();
        }

        return super.getCapability(capability, facing);
    }
}
