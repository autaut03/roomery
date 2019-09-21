package net.alexwells.roomery

import java.util.function.Supplier
import net.alexwells.kottle.FMLKotlinModLoadingContext
import net.alexwells.roomery.mechanic.roomcard.RoomCardItem
import net.alexwells.roomery.proxy.ClientProxy
import net.alexwells.roomery.proxy.CommonProxy
import net.alexwells.roomery.proxy.ServerProxy
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemStack
import net.minecraftforge.fml.DistExecutor
import net.minecraftforge.fml.common.Mod

const val MOD_ID = "roomery"

@Mod(MOD_ID)
object Roomery {
    private val proxy: CommonProxy = DistExecutor.runForDist<CommonProxy>(
        { Supplier { ClientProxy() } },
        { Supplier { ServerProxy() } }
    )

    val itemGroup = object : ItemGroup(MOD_ID) {
        override fun createIcon() = ItemStack(RoomCardItem)
    }

    init {
        FMLKotlinModLoadingContext.get().modEventBus.register(proxy)
    }
}
