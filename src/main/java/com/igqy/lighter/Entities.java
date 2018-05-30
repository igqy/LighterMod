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

	//Registers the entity, so Forge recognizes it as one.
	private static void registerEntities() {
		EntityRegistry.registerModEntity(new ResourceLocation("itemfire"), EntityLightFromLighter.class, "projectile_fire", entityId++, Lighter.instance, 64, 1, true);
	}

	/* Don't really know what deprecated methods mean, but after suppressing the warning, the method seemed to work just fine.
	 * 
	 * Registers the renderer and which entity it should act upon.
	 */
	@SuppressWarnings("deprecation")
	private static void registerRenders() {
		RenderingRegistry.registerEntityRenderingHandler(EntityLightFromLighter.class, new RenderSnowball<EntityLightFromLighter>(Minecraft.getMinecraft().getRenderManager(), ItemFire.ITEM_FIRE, Minecraft.getMinecraft().getRenderItem()));
	}
}