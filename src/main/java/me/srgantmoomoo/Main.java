package me.srgantmoomoo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.srgantmoomoo.bartab.backend.event.EventProcessor;
import me.srgantmoomoo.bartab.backend.proxy.CommonProxy;
import me.srgantmoomoo.bartab.command.CommandManager;
import me.zero.alpine.EventBus;
import me.zero.alpine.EventManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

/*
 * Written by @SrgantMooMoo on 11/17/20.
 */

@Mod(modid = Reference.MOD_ID, name = Reference.NAME, version = Reference.VERSION)
public class Main {
	
	public static CommandManager commandManager;
	//public static SaveLoad saveLoad;
	public static EventProcessor eventProcessor;
	
	public static final Logger log = LogManager.getLogger("bartab");
	public static final EventBus EVENT_BUS = new EventManager();
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.COMMON_PROXY_CLASS)
	public static CommonProxy proxy;
	
	@Instance
	public static Main instance;
	
	public Main() {
		instance = this;
	}
	
	public static Main getInstance() {
		return instance;
	}
	
	@EventHandler
	public void init (FMLInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);

		eventProcessor = new EventProcessor();
		log.info("event system initialized.");

		commandManager = new CommandManager();
		log.info("command system initialized.");
		
		//saveLoad = new SaveLoad();
		//log.info("configs initialized.");
		
		log.info(Reference.NAME + "initialization finished.");
	}
}