package net.cyanmarine.shriekercounter.client;

import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.cyanmarine.shriekercounter.ShriekerCounter;
import net.cyanmarine.shriekercounter.constants.Anchor;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.text.Text;

import static net.cyanmarine.shriekercounter.client.ShriekerCounterClient.config;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class Commands {
    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> dispatcher.register(literal(ShriekerCounter.MOD_ID)
                .then(literal("anchor").then(
                                literal("top")
                                        .then(literal("left").executes((context)-> config.setAnchor(Anchor.TOP_LEFT)))
                                        .then(literal("center").executes((context)-> config.setAnchor(Anchor.TOP_CENTER)))
                                        .then(literal("right").executes((context)-> config.setAnchor(Anchor.TOP_RIGHT)))
                                        .executes((context)-> {context.getSource().getPlayer().sendMessage(Text.literal("/" + ShriekerCounter.MOD_ID + " position top <top | center | bottom> <left | center | right>")); return 0; })
                        ).then(
                                literal("center")
                                        .then(literal("left").executes((context)-> config.setAnchor(Anchor.CENTER_LEFT)))
                                        .then(literal("center").executes((context)-> config.setAnchor(Anchor.CENTER_CENTER)))
                                        .then(literal("right").executes((context)-> config.setAnchor(Anchor.CENTER_RIGHT)))
                                        .executes((context)-> {context.getSource().getPlayer().sendMessage(Text.literal("/" + ShriekerCounter.MOD_ID + " position top <top | center | bottom> <left | center | right>")); return 0; })
                        ).then(
                                literal("bottom")
                                        .then(literal("left").executes((context)-> config.setAnchor(Anchor.BOTTOM_LEFT)))
                                        .then(literal("center").executes((context)-> config.setAnchor(Anchor.BOTTOM_CENTER)))
                                        .then(literal("right").executes((context)-> config.setAnchor(Anchor.BOTTOM_RIGHT)))                                        .executes((context)-> {context.getSource().getPlayer().sendMessage(Text.literal("/" + ShriekerCounter.MOD_ID + " position top <top | center | bottom> <left | center | right>")); return 0; })
                                        .executes((context)-> {context.getSource().getPlayer().sendMessage(Text.literal("/" + ShriekerCounter.MOD_ID + " position top <top | center | bottom> <left | center | right>")); return 0; })
                        )
                ).then(
                        literal("position").then(
                                argument("x", IntegerArgumentType.integer(-9999, 9999)).then(
                                        argument("y", IntegerArgumentType.integer(-9999, 9999)).executes((context) -> {
                                            config.setX(IntegerArgumentType.getInteger(context, "x"));
                                            config.setY(IntegerArgumentType.getInteger(context, "y"));
                                            return 0;
                                        })
                                )
                        )
                )
                .then(literal("scale").then(
                        argument("value", FloatArgumentType.floatArg(0.0f, 10.0f)).executes((context) -> {
                            config.setScale(FloatArgumentType.getFloat(context, "value"));
                            return 0;
                        }))
                )
                .then(literal("hide").executes((context) -> { config.setActive(false); return 0;}))
                .then(literal("show").executes((context) -> { config.setActive(true); return 0;}))
                .then(literal("show_when_zero").then(
                        argument("value", BoolArgumentType.bool()).executes((context) -> {
                            config.showWhenZero(BoolArgumentType.getBool(context, "value"));
                            return 0;
                        }))
                )
                .executes(context -> {
                    // For versions below 1.19, replace "Text.literal" with "new LiteralText".
                    ClientPlayerEntity player = context.getSource().getPlayer();
                    player.sendMessage(Text.literal(
                            "Commands:" + "\n" +
                                    "/" + ShriekerCounter.MOD_ID + " anchor <top | center | bottom> <left | center | right>" + "\n" +
                                    "/" + ShriekerCounter.MOD_ID + " position <x> <y>" + "\n" +
                                    "/" + ShriekerCounter.MOD_ID + " scale <scale>" + "\n" +
                                    "/" + ShriekerCounter.MOD_ID + " hide" + "\n" +
                                    "/" + ShriekerCounter.MOD_ID + " show" + "\n" +
                                    "/" + ShriekerCounter.MOD_ID + " show_when_zero <true | false>"
                    ));
                    return 1;
                })));
    }
}
