package net.cyanmarine.shriekercounter.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.cyanmarine.shriekercounter.ShriekerCounter;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.util.Identifier;

import static net.cyanmarine.shriekercounter.client.ShriekerCounterClient.*;

public class HudRenderer {
    private static final Identifier COUNTER = ShriekerCounter.getId("textures/gui/counter.png");

    public static void register() {
        HudRenderCallback.EVENT.register((matrixStack, tickDelta) -> {
            if (!config.isActive()) return;
            MinecraftClient client = MinecraftClient.getInstance();
            if (config.showWhenZero() || warningLevel > 0) {
                int x = config.getPosition().getX();
                int y = config.getPosition().getY();

                int width = (int) (70 * config.getScale());
                int height = (int) (30 * config.getScale());

                int window_width = client.getWindow().getScaledWidth();
                int window_height = client.getWindow().getScaledHeight();

                String[] position = config.getPosition().getAnchor().toString().split("_");

                if (position[0].equals("CENTER")) y = window_height / 2 - height / 2 + config.getPosition().getY();
                else if (position[0].equals("BOTTOM")) y = window_height - height - config.getPosition().getY();

                if (position[1].equals("CENTER")) x = window_width / 2 - width / 2 + config.getPosition().getX();
                else if (position[1].equals("RIGHT")) x = window_width - width - config.getPosition().getX();

                RenderSystem.setShaderTexture(0, COUNTER);
                DrawableHelper.drawTexture(matrixStack, x, y, width, height, 0, 300 * warningLevel, 700, 300, 700, 1500);
            }
        });
    }
}
