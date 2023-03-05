package net.cyanmarine.shriekercounter;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShriekerCounter implements ModInitializer {
    public static final GameRules.Key<GameRules.BooleanRule> SHOULD_MESSAGE_ON_CHANGE = GameRuleRegistry.register("shriekerNotifyOnChange", GameRules.Category.MISC, GameRuleFactory.createBooleanRule(true));
    public static final String MOD_ID = "shriekercounter";
    public static Identifier getId(String name) { return new Identifier(MOD_ID, name); }

    public static Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() { LOGGER.info("Shrieker Counter installed"); }
}
