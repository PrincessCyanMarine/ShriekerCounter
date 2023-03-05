package net.cyanmarine.shriekercounter.client;

import net.cyanmarine.shriekercounter.ShriekerCounter;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import static net.cyanmarine.shriekercounter.client.ShriekerCounterClient.config;
import static net.cyanmarine.shriekercounter.client.ShriekerCounterClient.configScreen;

public class Keybindings {
    private static final KeyBinding TOGGLE_KEYBINDING = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key." + ShriekerCounter.MOD_ID + ".toggle",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_O,
            "category." + ShriekerCounter.MOD_ID + ".keys"
    ));
    private static boolean alreadyToggled = false;

    private static final KeyBinding CONFIG_KEYBINDING = configScreen != null ? KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key." + ShriekerCounter.MOD_ID + ".config",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_COMMA,
            "category." + ShriekerCounter.MOD_ID + ".keys"
    )) : null;

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            boolean toggle = TOGGLE_KEYBINDING.wasPressed();
            if (toggle != alreadyToggled){
                if (toggle)
                    config.setActive(!config.isActive());
                alreadyToggled = toggle;
            }
            if (CONFIG_KEYBINDING != null && CONFIG_KEYBINDING.wasPressed())
                client.setScreen(configScreen.build(client.currentScreen, config));
        });
    }
}
