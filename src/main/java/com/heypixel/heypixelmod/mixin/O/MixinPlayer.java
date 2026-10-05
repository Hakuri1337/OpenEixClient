package com.heypixel.heypixelmod.mixin.O;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.events.api.events.Event;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplAa;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplB;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplC;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({Player.class})
public abstract class MixinPlayer extends LivingEntity {
   protected MixinPlayer(EntityType<? extends LivingEntity> var1, Level var2) {
      super(var1, var2);
   }

   @Redirect(
      method = {"attack"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/entity/player/Player;getYRot()F"
      )
   )
   private float hookFixRotation(Player var1) {
      RecoveredEventsImplC var2 = new RecoveredEventsImplC(var1.getYRot());
      EixClient.a().b().a((Event)var2);
      return var2.a();
   }

   @Redirect(
      method = {"attack"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/entity/player/Player;setDeltaMovement(Lnet/minecraft/world/phys/Vec3;)V"
      )
   )
   private void hookSetDeltaMovement(Player var1, Vec3 var2) {
      RecoveredEventsImplB var3 = new RecoveredEventsImplB();
      EixClient.a().b().a((Event)var3);
      if (!var3.a()) {
         var1.setDeltaMovement(var2);
      }
   }

   @Redirect(
      method = {"attack"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/entity/player/Player;setSprinting(Z)V"
      )
   )
   private void hookSetSprinting(Player var1, boolean var2) {
      RecoveredEventsImplB var3 = new RecoveredEventsImplB();
      EixClient.a().b().a((Event)var3);
      if (!var3.a()) {
         var1.setSprinting(var2);
      }
   }

   @Inject(
      method = {"isStayingOnGroundSurface"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void isStayingOnGroundSurface(CallbackInfoReturnable<Boolean> var1) {
      RecoveredEventsImplAa var2 = new RecoveredEventsImplAa((Boolean)var1.getReturnValue());
      EixClient.a().b().a((Event)var2);
      var1.setReturnValue(var2.a());
   }
}
