package com.igqy.lighter;

import com.igqy.lighter.entity.EntityLightFromLighter;
import com.igqy.lighter.items.ItemFire;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

public class Entities {
	
	public static int entityId = 300;
	
	public static void register() {
		registerRenders();
		registerEntities();
	}

	
	private static void registerEntities() {
		EntityRegistry.registerModEntity(new ResourceLocation("fireprojectile"), EntityLightFromLighter.class, "projectile_fire", entityId++, Lighter.instance, 64, 1, true);
	}

	@SuppressWarnings("deprecation")
	private static void registerRenders() {
		RenderingRegistry.registerEntityRenderingHandler(EntityLightFromLighter.class, new RenderSnowball<EntityLightFromLighter>(Minecraft.getMinecraft().getRenderManager(), ItemFire.ITEM_FIRE, Minecraft.getMinecraft().getRenderItem()));
	}
}