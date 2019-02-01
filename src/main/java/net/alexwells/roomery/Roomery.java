package net.alexwells.roomery;

import net.alexwells.roomery.proxy.CommonProxy;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Roomery.MOD_ID, name = Roomery.NAME, version = Roomery.VERSION)
public class Roomery
{
    public static final String MOD_ID = "roomery";
    public static final String NAME = "Roomery";
    public static final String VERSION = "1.0.0";

    @SidedProxy(clientSide = "net.alexwells.roomery.proxy.ClientProxy", serverSide = "net.alexwells.roomery.proxy.ServerProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        MinecraftForge.EVENT_BUS.register(proxy);
    }
}
