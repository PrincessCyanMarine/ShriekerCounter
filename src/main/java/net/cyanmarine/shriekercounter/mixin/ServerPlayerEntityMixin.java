package net.cyanmarine.shriekercounter.mixin;

import net.cyanmarine.shriekercounter.ShriekerCounter;
import net.cyanmarine.shriekercounter.constants.Channels;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.entity.SculkShriekerWarningManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtOps;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin {
    @Shadow private SculkShriekerWarningManager sculkShriekerWarningManager;

    @Shadow public abstract ServerWorld getWorld();


    @Shadow public abstract void sendMessage(Text message, boolean overlay);

    @Shadow public abstract void playSound(SoundEvent event, SoundCategory category, float volume, float pitch);

    int warningLevel = -1;

    @Inject(method= "tick()V", at = @At("TAIL"))
    private void setWarningLevelInject(CallbackInfo ci) {
        int new_warning_level;
        if (!this.getWorld().isClient() && ((new_warning_level = this.sculkShriekerWarningManager.getWarningLevel()) != warningLevel)) {
            PacketByteBuf buf = PacketByteBufs.create();
            buf.writeInt(new_warning_level);
            ServerPlayNetworking.send((ServerPlayerEntity) (Object) this, Channels.UPDATE_WARNING_LEVEL, buf);
            if (getWorld().getGameRules().getBoolean(ShriekerCounter.SHOULD_MESSAGE_ON_CHANGE)) {
                this.sendMessage(Text.of("Your shrieker count " +( new_warning_level > warningLevel ? "increased" : "decreased" ) + " to "  + new_warning_level), false);
                this.playSound(SoundEvents.BLOCK_NOTE_BLOCK_PLING.value(), SoundCategory.NEUTRAL, 0.3f, ((float) ((PlayerEntity) (Object) this).getRandom().nextBetween(0, 3)) - 1.5f);
            }
            warningLevel = new_warning_level;
        }
    }
}
