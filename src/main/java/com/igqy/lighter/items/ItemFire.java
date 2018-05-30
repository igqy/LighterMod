package com.igqy.lighter.items;

import com.igqy.lighter.Lighter;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//Found it easier to create a new item to refer the entity to
public class ItemFire extends Item {
	
	public static Item ITEM_FIRE;
	
	public ItemFire() {
		setRegistryName("itemfire");
		setUnlocalizedName(Lighter.MODID + ".itemfire");
		
		ITEM_FIRE = this;
	}
	
	//Defines the location of the texture.
	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
	}
}