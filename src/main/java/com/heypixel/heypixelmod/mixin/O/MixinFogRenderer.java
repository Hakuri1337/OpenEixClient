package com.heypixel.heypixelmod.mixin.O;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.AntiBlindnessModule;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({FogRenderer.class})
public class MixinFogRenderer {
   @Redirect(
      method = {"setupColor"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/entity/LivingEntity;hasEffect(Lnet/minecraft/world/effect/MobEffect;)Z",
         ordinal = 0
      )
   )
   private static boolean onSetupColor(LivingEntity var0, MobEffect var1) {
      return (var1 != MobEffects.BLINDNESS || !EixClient.a().g().a(AntiBlindnessModule.class).m()) && var0.hasEffect(var1);
   }

   @Redirect(
      method = {"setupFog(Lnet/minecraft/client/Camera;Lnet/minecraft/client/renderer/FogRenderer$FogMode;FZF)V"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/entity/LivingEntity;hasEffect(Lnet/minecraft/world/effect/MobEffect;)Z"
      )
   )
   private static boolean onSetupFog(LivingEntity var0, MobEffect var1) {
      return (var1 != MobEffects.BLINDNESS || !EixClient.a().g().a(AntiBlindnessModule.class).m()) && var0.hasEffect(var1);
   }
}
