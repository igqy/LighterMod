package com.igqy.lighter.proxy;

import com.igqy.lighter.Items;
import com.igqy.lighter.input.InputHandler;
import com.igqy.lighter.input.KeyBindings;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

//Initializes any client side events
@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends MainProxy {
	
	@Override
	public void preInit(FMLPreInitializationEvent e) {
		super.preInit(e);
	}
	
	@Override
	public void init(FMLInitializationEvent e) {
		super.init(e);
		
		//Registers InputHandler.class
		MinecraftForge.EVENT_BUS.register(new InputHandler());
		
		//Initializes the key binds in KeyBindings.class
		KeyBindings.init();
	}
	
	//Registers the models for items
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		Items.initModels();
	}
}