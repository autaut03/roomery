package net.alexwells.roomery.gui.client;

import net.alexwells.roomery.Roomery;
import net.alexwells.roomery.gui.container.RoomHolderContainer;
import net.alexwells.roomery.tile.RoomHolderTileEntity;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

public class RoomHolderGui extends GuiContainer {
    private static final ResourceLocation GUI_TEXTURE = new ResourceLocation(Roomery.MOD_ID, "textures/gui/container/room_holder.png");

    /** The player inventory bound to this GUI. */
    private final InventoryPlayer playerInventory;
    private final RoomHolderTileEntity tile;

    public RoomHolderGui(InventoryPlayer inventory, RoomHolderTileEntity roomHolderInventory) {
        super(new RoomHolderContainer(inventory, roomHolderInventory));

        this.playerInventory = inventory;
        this.tile = roomHolderInventory;

        this.allowUserInput = false;
        this.ySize = 114 + 6 * 18;
    }

    /**
     * Draws the screen and all the components in it.
     */
    public void drawScreen(int mouseX, int mouseY, float partialTicks)
    {
        this.drawDefaultBackground();
        super.drawScreen(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        //String s = this.tile.getDisplayName().getUnformattedText();
        String s = "Room holder";
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752);
        this.fontRenderer.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        // Draw background GUI
        this.mc.getTextureManager().bindTexture(GUI_TEXTURE);
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize);


    }
}
