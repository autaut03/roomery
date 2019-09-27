package net.alexwells.roomery.mechanic.roomholder

import net.alexwells.roomery.common.gui.ContainerScreen
import net.minecraft.entity.player.PlayerInventory
import net.minecraft.util.text.ITextComponent
import net.minecraftforge.api.distmarker.Dist
import net.minecraftforge.api.distmarker.OnlyIn

class RoomHolderScreen(
    container: RoomHolderContainer,
    playerInventory: PlayerInventory,
    title: ITextComponent
) : ContainerScreen<RoomHolderContainer>(container, playerInventory, title) {
    init {
        this.passEvents = false
        this.ySize = 114 + 6 * 18
    }
}
