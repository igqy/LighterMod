package com.igqy.lighter.renderer;

import com.igqy.lighter.Lighter;
import com.igqy.lighter.entity.EntityLightFromLighter;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderFireProjectile extends Render<EntityLightFromLighter>{
	ResourceLocation FIRE_PROJECTILE = new ResourceLocation(Lighter.MODID, "textures/items/fireprojectile.png");
	
	public RenderFireProjectile(RenderManager renderManager) {
		super(renderManager);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityLightFromLighter entity) {
		return FIRE_PROJECTILE;
	}
}