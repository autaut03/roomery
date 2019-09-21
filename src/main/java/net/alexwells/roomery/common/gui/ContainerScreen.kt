package net.alexwells.roomery.common.gui

import com.mojang.blaze3d.platform.GlStateManager
import net.alexwells.roomery.util.createGuiResource
import net.minecraft.client.gui.screen.inventory.ContainerScreen
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.inventory.container.Container
import net.minecraft.util.ResourceLocation
import net.minecraft.util.text.ITextComponent

abstract class ContainerScreen<T : Container>(
    container: T,
    private val playerInv: PlayerInventory,
    title: ITextComponent
) :
    ContainerScreen<T>(container, playerInv, title) {
    private val guiTexture: ResourceLocation = createGuiResource(container.type.registryName!!.path)

    /**
     * Draws the screen and all the components in it.
     */
    override fun render(mouseX: Int, mouseY: Int, partialTicks: Float) {
        renderBackground()
        super.render(mouseX, mouseY, partialTicks)
        renderHoveredToolTip(mouseX, mouseY)
    }

    /**
     * Draw the foreground layer for the GuiContainer (everything in front of the items)
     */
    override fun drawGuiContainerForegroundLayer(mouseX: Int, mouseY: Int) {
        this.font.drawString(title.formattedText, 8f, 6f, 4210752)
        this.font.drawString(playerInv.displayName.string, 8f, (this.ySize - 96 + 2).toFloat(), 4210752)
    }

    /**
     * Draws the background layer of this container (behind the items).
     */
    override fun drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int) {
        GlStateManager.color4f(1.0f, 1.0f, 1.0f, 1.0f)

        // Draw background GUI
        this.minecraft!!.textureManager.bindTexture(guiTexture)
        this.blit(this.guiLeft, this.guiTop, 0, 0, this.xSize, this.ySize)
    }
}
