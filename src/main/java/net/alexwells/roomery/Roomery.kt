package net.alexwells.roomery

import net.alexwells.roomery.Constants.MOD_ID
import net.alexwells.roomery.Constants.NAME
import net.alexwells.roomery.Constants.UPDATE_JSON_URL
import net.alexwells.roomery.Constants.VERSION
import net.alexwells.roomery.gui.GuiHandler
import net.alexwells.roomery.mechanic.roomcard.RoomCardItem
import net.alexwells.roomery.proxy.CommonProxy
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.ItemStack
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.SidedProxy
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.network.NetworkRegistry

@Mod(
        modid = MOD_ID,
        name = NAME,
        version = VERSION,
        modLanguage = "kotlin",
        modLanguageAdapter = "net.shadowfacts.forgelin.KotlinAdapter",
        dependencies = "required-before:forgelin@1.8.2",
        updateJSON = UPDATE_JSON_URL
)
object Roomery {
    @SidedProxy(clientSide = "net.alexwells.roomery.proxy.ClientProxy", serverSide = "net.alexwells.roomery.proxy.ServerProxy")
    lateinit var proxy: CommonProxy

    val creativeTab = object : CreativeTabs(MOD_ID) {
        override fun createIcon() = ItemStack(RoomCardItem)
    }

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        MinecraftForge.EVENT_BUS.register(proxy)

        NetworkRegistry.INSTANCE.registerGuiHandler(this, GuiHandler())
    }
}