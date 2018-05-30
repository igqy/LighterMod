package com.igqy.lighter.proxy;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.igqy.lighter.Entities;
import com.igqy.lighter.items.*;
import com.igqy.lighter.network.PacketHandler;

@Mod.EventBusSubscriber
public class MainProxy {
	
	public void preInit(FMLPreInitializationEvent e) {
		PacketHandler.registerMessages("lighter");
	}
	public void init(FMLInitializationEvent e) {
		Entities.register();
	}
	public void postInit(FMLPostInitializationEvent e) {
	}
	
	//Registers every item. In this case, there is only one.
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(new ItemLighter());
		event.getRegistry().register(new ItemFire());
	}
	
	//Registers every block. I kept this here because I don't know whether or not I want to add a block.
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {

	}
	
}