package com.igqy.lighter.items;


import com.igqy.lighter.Lighter;
import com.igqy.lighter.entity.EntityLightFromLighter;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.renderer.ItemMeshDefinition;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

//Class for the Lighter item
public class ItemLighter extends Item {
	
	//Allows the item to be referenced elsewhere in the project
	public static ItemStack ITEM_LIGHTER;
	
	//Constructs the item
	public ItemLighter() {
		
		//sets the universal name to "itemlighter" (this can be changed with a .lang file)
		setRegistryName("itemlighter");
		
		//same as above
		setUnlocalizedName(Lighter.MODID + ".itemlighter");
		
		//sets the maximum amount of items in a stack to 1
		setMaxStackSize(1);
		
		//sets the durability to 300
		setMaxDamage(300);
		
		//initializes the variable above
		ITEM_LIGHTER = new ItemStack(this);
	}
	
	//Registers and sets the two different textures and handles where it is located. (src/main/resources/models/item/)
	@SideOnly(Side.CLIENT)
	public void initModel() {
		ModelResourceLocation onModel = new ModelResourceLocation(getRegistryName() + "on", "inventory");
		ModelResourceLocation offModel = new ModelResourceLocation(getRegistryName() + "off", "inventory");
		
		ModelBakery.registerItemVariants(this, onModel, offModel);
		
		ModelLoader.setCustomMeshDefinition(this, new ItemMeshDefinition() {
			@Override
			public ModelResourceLocation getModelLocation(ItemStack stack) {
				if (isOn(stack)) {
					return onModel;
				} else {
					return offModel;
				}
			}
		});
	}
	
	//Checks if the item is currently set to the "on" texture
	private static boolean isOn(ItemStack stack) {
		return getTagCompoundSafe(stack).hasKey("on");
	}
	
	//Changes the textures
    public static ActionResult<ItemStack> changeStates(World world, EntityPlayer playerIn, EnumHand hand) {
    	ItemStack stack = playerIn.getHeldItem(hand);
        if (!world.isRemote) {
            if (isOn(stack)) {
                getTagCompoundSafe(stack).removeTag("on");
                getTagCompoundSafe(stack).setBoolean("off", true);
            } else {
            	getTagCompoundSafe(stack).removeTag("off");
                getTagCompoundSafe(stack).setBoolean("on", true);
            }
        }
        return new ActionResult<>(EnumActionResult.SUCCESS, stack);
    }
	
    //Gets the tag compound of the item. Used when checking of it is on or off
	private static NBTTagCompound getTagCompoundSafe(ItemStack stack) {
		NBTTagCompound tagCompound = stack.getTagCompound();
		if (tagCompound == null) {
			tagCompound = new NBTTagCompound();
			stack.setTagCompound(tagCompound);
		}
		return tagCompound;
	}
	
	//Launches the light source (snowball)
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		ItemStack itemstack = playerIn.getHeldItem(handIn);
        
		if (!worldIn.isRemote)
        {
        	if (playerIn.getHeldItem(handIn).getTagCompound().hasKey("off")) {
        		return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
        	} else if (!itemstack.getTagCompound().hasKey("off")){
        		worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_BLAZE_SHOOT, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
                EntityLightFromLighter entitylighter = new EntityLightFromLighter(worldIn, playerIn);
                entitylighter.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.7F, 1.5F, 1.0F);
                worldIn.spawnEntity(entitylighter);
        	}
        }
        
		itemstack.damageItem(5, playerIn);
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }
	
	//Sets fire to the surrounding ground.
	public static EnumActionResult setFireR(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing) {
		pos = pos.offset(facing);
        ItemStack itemstack = player.getHeldItem(hand);

        if (!player.canPlayerEdit(pos, facing, itemstack))
        {
            return EnumActionResult.FAIL;
        }
        else
        {
            if (player.getHeldItem(hand).getTagCompound().hasKey("off")) {
            	return EnumActionResult.FAIL;
            }
            
            if (worldIn.isAirBlock(pos) && itemstack.getTagCompound().hasKey("on"))
            {
                worldIn.playSound(player, pos, SoundEvents.ITEM_FIRECHARGE_USE, SoundCategory.BLOCKS, 1.0F, itemRand.nextFloat() * 0.4F + 0.8F);
                worldIn.setBlockState(pos, Blocks.FIRE.getDefaultState(), 11);
                worldIn.setBlockState(pos.add(0,0,1), Blocks.FIRE.getDefaultState(), 11);
                worldIn.setBlockState(pos.add(0,0,-1), Blocks.FIRE.getDefaultState(), 11);
                worldIn.setBlockState(pos.add(1,0,0), Blocks.FIRE.getDefaultState(), 11);
                worldIn.setBlockState(pos.add(-1,0,0), Blocks.FIRE.getDefaultState(), 11);
                worldIn.setBlockState(pos.add(-1,0,-1), Blocks.FIRE.getDefaultState(), 11);
                worldIn.setBlockState(pos.add(-1,0,1), Blocks.FIRE.getDefaultState(), 11);
                worldIn.setBlockState(pos.add(1,0,1), Blocks.FIRE.getDefaultState(), 11);
                worldIn.setBlockState(pos.add(1,0,-1), Blocks.FIRE.getDefaultState(), 11);
            }

            if (player instanceof EntityPlayerMP)
            {
                CriteriaTriggers.PLACED_BLOCK.trigger((EntityPlayerMP)player, pos, itemstack);
            }

            itemstack.damageItem(1, player);
            return EnumActionResult.SUCCESS;
        }
	}
}