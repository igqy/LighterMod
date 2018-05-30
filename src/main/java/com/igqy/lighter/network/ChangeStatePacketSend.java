package com.igqy.lighter.network;

import com.igqy.lighter.items.ItemLighter;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

//Used when "change state" key is pressed
public class ChangeStatePacketSend implements IMessage {
	public ChangeStatePacketSend() {
	}

	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}
	
	public static class MessageHandler implements IMessageHandler<ChangeStatePacketSend, IMessage> {

		@Override
		public IMessage onMessage(ChangeStatePacketSend message, MessageContext ctx) {
			FMLCommonHandler.instance().getWorldThread(ctx.netHandler).addScheduledTask(() -> handle(message, ctx));
			return null;
		}
		
		//Calls the changeStates() method in the ItemLighter.class
		private void handle(ChangeStatePacketSend message, MessageContext ctx) {
			EntityPlayerMP playerEntity = ctx.getServerHandler().player;
            World world = playerEntity.getEntityWorld();
            EnumHand handIn = EnumHand.MAIN_HAND;
            if (playerEntity.getHeldItem(handIn).isItemEqualIgnoreDurability(ItemLighter.ITEM_LIGHTER)) {
            	ItemLighter.changeStates(world, playerEntity, handIn);
            }
		}
	}
}