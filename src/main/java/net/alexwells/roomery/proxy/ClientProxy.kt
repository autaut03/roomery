package net.alexwells.roomery.proxy

import net.alexwells.roomery.mechanic.connector.ConnectorContainerType
import net.alexwells.roomery.mechanic.connector.ConnectorScreen
import net.alexwells.roomery.mechanic.roomholder.RoomHolderContainerType
import net.alexwells.roomery.mechanic.roomholder.RoomHolderScreen
import net.minecraft.client.gui.ScreenManager
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent

class ClientProxy : CommonProxy() {
    @SubscribeEvent
    fun setup(event: FMLCommonSetupEvent) {
        ScreenManager.registerFactory(RoomHolderContainerType) { container, playerInventory, title ->
            RoomHolderScreen(container, playerInventory, title)
        }
        ScreenManager.registerFactory(ConnectorContainerType) { container, playerInventory, title ->
            ConnectorScreen(container, playerInventory, title)
        }
    }
}
