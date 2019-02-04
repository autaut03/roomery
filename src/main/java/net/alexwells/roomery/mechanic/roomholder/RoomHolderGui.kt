package net.alexwells.roomery.mechanic.roomholder

import net.alexwells.roomery.Constants.MOD_ID
import net.alexwells.roomery.Roomery
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.util.ResourceLocation

class RoomHolderGui(
        /** The player inventory bound to this GUI.  */
        private val playerInventory: InventoryPlayer, private val tile: RoomHolderTileEntity) : GuiContainer(RoomHolderContainer(playerInventory, tile)) {

    init {

        this.allowUserInput = false
        this.ySize = 114 + 6 * 18
    }

    /**
     * Draws the screen and all the components in it.
     */
    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        this.drawDefaultBackground()
        super.drawScreen(mouseX, mouseY, partialTicks)
        this.renderHoveredToolTip(mouseX, mouseY)
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    override fun drawGuiContainerForegroundLayer(mouseX: Int, mouseY: Int) {
        //String s = this.tile.getDisplayName().getUnformattedText();
        val s = "Room holder"
        this.fontRenderer.drawString(s, this.xSize / 2 - this.fontRenderer.getStringWidth(s) / 2, 6, 4210752)
        this.fontRenderer.drawString(this.playerInventory.displayName.unformattedText, 8, this.ySize - 96 + 2, 4210752)
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    override fun drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f)

        // Draw background GUI
        this.mc.textureManager.bindTexture(GUI_TEXTURE)
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize)


    }

    companion object {
        private val GUI_TEXTURE = ResourceLocation(MOD_ID, "textures/gui/container/room_holder.png")
    }
}
