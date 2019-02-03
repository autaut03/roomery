package net.alexwells.roomery;

import net.alexwells.roomery.gui.GuiHandler;
import net.alexwells.roomery.proxy.CommonProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;

@Mod(modid = Roomery.MOD_ID, name = Roomery.NAME, version = Roomery.VERSION)
public class Roomery
{
    public static final String MOD_ID = "roomery";
    public static final String NAME = "Roomery";
    public static final String VERSION = "1.0.0";

    @SidedProxy(clientSide = "net.alexwells.roomery.proxy.ClientProxy", serverSide = "net.alexwells.roomery.proxy.ServerProxy")
    public static CommonProxy proxy;

    private static Roomery instance;

    public Roomery() {
        instance = this;
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(proxy);

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    }

    public static Roomery instance() {
        return instance;
    }
}
