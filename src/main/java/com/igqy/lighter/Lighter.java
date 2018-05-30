package com.igqy.lighter;

/*
 * Welcome to my APCSA project, Lighter!
 */

import org.apache.logging.log4j.Logger;

import com.igqy.lighter.proxy.MainProxy;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

/* Defines the mod characteristics and whether or not it should
 * use the mcmod.info file located in /src/main/resources 
 * */
@Mod(modid = Lighter.MODID, 
	 name = Lighter.MODNAME, 
	 version = Lighter.MODVERSION,
	 useMetadata = true)

//Main mod class
public class Lighter {
	
	//variables used above to define ID, name and version.
	public static final String MODID = "lighter";
	public static final String MODNAME = "Lighter";
	public static final String MODVERSION = "0.0.1";
	
	//Defines what proxy classes the mod uses
	@SidedProxy(clientSide = "com.igqy.lighter.proxy.ClientProxy",
				serverSide = "com.igqy.lighter.proxy.ServerProxy")
    public static MainProxy proxy;
	
	//Tells Forge to run this main mod class
	@Mod.Instance
	public static Lighter instance;
	
	public static Logger logger;
	
	/*
	 * When the mod is loaded, it must initialize the necessary classes in order to be recognized by Forge.
	 * Here, it initializes the proxy classes mentioned above.
	 */
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		proxy.preInit(event);
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent e) {
		proxy.init(e);
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent e) {
		proxy.postInit(e);
	}
}