package net.cyanmarine.shriekercounter.client;

import me.lortseam.completeconfig.gui.ConfigScreenBuilder;
import me.lortseam.completeconfig.gui.cloth.ClothConfigScreenBuilder;
import me.lortseam.completeconfig.gui.yacl.YaclScreenBuilder;
import net.cyanmarine.shriekercounter.ShriekerCounter;
import net.cyanmarine.shriekercounter.constants.Channels;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientLoginConnectionEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;

@Environment(EnvType.CLIENT)
public class ShriekerCounterClient implements ClientModInitializer {
    public static int warningLevel = 0;
    public static ModConfig config = new ModConfig();
    public static ConfigScreenBuilder configScreen = FabricLoader.getInstance().isModLoaded("yet-another-config-lib") ? new YaclScreenBuilder() : FabricLoader.getInstance().isModLoaded("cloth-config") ? new ClothConfigScreenBuilder() : null;

    @Override
    public void onInitializeClient() {
        config.load();

        if (configScreen != null)
            ConfigScreenBuilder.setMain(ShriekerCounter.MOD_ID, configScreen);

        ClientLoginConnectionEvents.INIT.register((handler, minecraftClient)->{
            warningLevel = 0;
        });

        ClientPlayNetworking.registerGlobalReceiver(Channels.UPDATE_WARNING_LEVEL, (client, handler, buf, responseSender) -> {
           int newLevel = buf.readInt();
           client.execute(()->{
               warningLevel = newLevel;
           });
        });

        Keybindings.register();
        Commands.register();
        HudRenderer.register();
    }
}
