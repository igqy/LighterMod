package com.igqy.lighter;

import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.igqy.lighter.items.ItemFire;
import com.igqy.lighter.items.ItemLighter;

//Main item class
public class Items {
	
	//Adds the lighter item to the game registry.
	@GameRegistry.ObjectHolder("lighter:itemlighter")
	public static ItemLighter itemlighter;
	
	@GameRegistry.ObjectHolder("lighter:itemfire")
	public static ItemFire itemfire;
	
	//Method to initialize the models of items. Called in ClientProxy.class
	@SideOnly(Side.CLIENT)
	public static void initModels() {
		itemlighter.initModel();
		itemfire.initModel();
	}
	
}