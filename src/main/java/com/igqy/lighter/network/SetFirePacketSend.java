package com.igqy.lighter.network;

import com.igqy.lighter.items.ItemLighter;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;


//Used when "set fire" key is pressed.
public class SetFirePacketSend implements IMessage {
	
	public SetFirePacketSend() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}
	
	public static class MessageHandler implements IMessageHandler<SetFirePacketSend, IMessage> {

		@Override
		public IMessage onMessage(SetFirePacketSend message, MessageContext ctx) {
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
			return null;
		}
		
		//Calls the setFireR() method in the ItemLighter class
		private void handle(SetFirePacketSend message, MessageContext ctx) {
			EntityPlayerMP playerEntity = ctx.getServerHandler().player;
			BlockPos pos = playerEntity.getPosition();
            World world = playerEntity.getEntityWorld();
            EnumHand handIn = EnumHand.MAIN_HAND;
            EnumFacing facing = EnumFacing.getDirectionFromEntityLiving(pos, playerEntity);
            if (playerEntity.getHeldItem(handIn).isItemEqualIgnoreDurability(ItemLighter.ITEM_LIGHTER)) {
            	ItemLighter.setFireR(playerEntity, world, pos, handIn, facing);
            }
		}
	}
}