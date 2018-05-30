package com.igqy.lighter.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

//Creates an entity similar to a snowball that sets things on fire. Launched from the lighter item.
public class EntityLightFromLighter extends EntitySnowball {
	
    public EntityLightFromLighter(World worldIn)
    {
        super(worldIn);
    }

    public EntityLightFromLighter(World worldIn, EntityLivingBase throwerIn)
    {
        super(worldIn, throwerIn);
    }

    public EntityLightFromLighter(World worldIn, double x, double y, double z)
    {
        super(worldIn, x, y, z);
    }

    public static void registerFixesSnowball(DataFixer fixer)
    {
        EntityThrowable.registerFixesThrowable(fixer, "Snowball");
    }
    
    //Allows the entity to be rendered on fire.
    @Override
    public boolean canRenderOnFire() {
    	return true;
    }
    
    //This is to define what happens when the entity hits a block or an entity.
    @Override
    protected void onImpact(RayTraceResult result)
    {
        if (result.entityHit != null && !result.entityHit.isEntityEqual(thrower))
        {
            result.entityHit.setFire(10);
            result.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), (float)5);
        }

        if (!this.world.isRemote)
        {
            this.world.setEntityState(this, (byte)3);
            this.setDead();
        }
        
        if (result.entityHit == null) {
        	this.world.setBlockState(getPosition(), Blocks.FIRE.getDefaultState(), 11);
        }
    }
}