package net.alexwells.roomery.mechanic.roomholder

import net.alexwells.roomery.Reference.MOD_ID
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.entity.player.InventoryPlayer
import net.minecraft.util.ResourceLocation

class RoomHolderGui(
        private val playerInventory: InventoryPlayer,
        private val tile: RoomHolderTileEntity
) : GuiContainer(RoomHolderContainer(playerInventory, tile)) {
    private val GUI_TEXTURE = ResourceLocation(MOD_ID, "textures/gui/container/room_holder.png")

    init {
        this.allowUserInput = false
        this.ySize = 114 + 6 * 18
    }

    /**
     * Draws the screen and all the components in it.
     */
    override fun render(mouseX: Int, mouseY: Int, partialTicks: Float) {
        drawDefaultBackground()
        super.render(mouseX, mouseY, partialTicks)
        renderHoveredToolTip(mouseX, mouseY)
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    override fun drawGuiContainerForegroundLayer(mouseX: Int, mouseY: Int) {
        //this.fontRenderer.drawString(tile.displayName.string, 8f, 6f, 4210752)
        this.fontRenderer.drawString(playerInventory.displayName.string, 8f, (this.ySize - 96 + 2).toFloat(), 4210752)
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    override fun drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int) {
        GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f)

        // Draw background GUI
        this.mc.textureManager.bindTexture(GUI_TEXTURE)
        this.drawTexturedModalRect(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize)
    }
}
