package net.alexwells.roomery.util

import net.alexwells.roomery.MOD_ID
import net.minecraft.util.ResourceLocation

fun createResource(path: String): ResourceLocation = ResourceLocation(MOD_ID, path)
fun createGuiResource(path: String): ResourceLocation = createResource("textures/gui/$path.png")
