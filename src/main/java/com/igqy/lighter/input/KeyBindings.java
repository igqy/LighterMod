package com.igqy.lighter.input;

import net.minecraftforge.fml.relauncher.SideOnly;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;

//Configurable keybinds.
@SideOnly(Side.CLIENT)
public class KeyBindings {

    public static KeyBinding setFire;
    public static KeyBinding changeState;

    public static void init() {
        setFire = new KeyBinding("key.setfire", Keyboard.KEY_R, "key.categories.lighter");
        changeState = new KeyBinding("key.changestates", Keyboard.KEY_N, "key.categories.lighter");
        ClientRegistry.registerKeyBinding(setFire);
        ClientRegistry.registerKeyBinding(changeState);
    }
}