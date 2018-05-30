package com.igqy.lighter.input;

import com.igqy.lighter.network.ChangeStatePacketSend;
import com.igqy.lighter.network.PacketHandler;
import com.igqy.lighter.network.SetFirePacketSend;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

//Sends a packet to the server whenever a certain key is pressed
public class InputHandler {
	
	@SubscribeEvent
	public void setFirePressed(InputEvent.KeyInputEvent e) {
		if (KeyBindings.setFire.isPressed()) {
			PacketHandler.INSTANCE.sendToServer(new SetFirePacketSend());
		}
	}
	
	@SubscribeEvent
	public void changeStatePressed(InputEvent.KeyInputEvent e) {
		if (KeyBindings.changeState.isPressed()) {
			PacketHandler.INSTANCE.sendToServer(new ChangeStatePacketSend());
		}
	}

}