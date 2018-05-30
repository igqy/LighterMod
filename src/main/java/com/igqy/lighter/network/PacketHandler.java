package com.igqy.lighter.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;


//Handles the packets sent from the client to the server. Needed for key inputs.
public class PacketHandler {
	
	private static int packetId = 0;
	
	public static SimpleNetworkWrapper INSTANCE = null;
	
	public PacketHandler() {
		
	}
	
	public static int nextID() {
		return packetId++;
	}
	
	public static void registerMessages(String channelName) {
		INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(channelName);
		registerMessages();
	}
	
	public static void registerMessages() {
		INSTANCE.registerMessage(SetFirePacketSend.MessageHandler.class, SetFirePacketSend.class, nextID(), Side.SERVER);
		INSTANCE.registerMessage(ChangeStatePacketSend.MessageHandler.class, ChangeStatePacketSend.class, nextID(), Side.SERVER);
	}
}