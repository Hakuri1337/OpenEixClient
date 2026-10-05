package com.heypixel.heypixelmod.mixin.O;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.events.api.events.Event;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplF;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplI;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplV;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.AntiNauseaModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.FullBrightModule;
import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({LivingEntity.class})
public abstract class MixinLivingEntity extends Entity {
   public MixinLivingEntity(EntityType<?> var1, Level var2) {
      super(var1, var2);
   }

   @Redirect(
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/entity/LivingEntity;getYRot()F",
         opcode = 182,
         ordinal = 0
      ),
      method = {"jumpFromGround"}
   )
   private float modifyJumpYaw(LivingEntity var1) {
      if (var1 != Minecraft.getInstance().player) {
         return var1.getYRot();
      } else {
         RecoveredEventsImplI var2 = new RecoveredEventsImplI(var1.getYRot());
         EixClient.a().b().a((Event)var2);
         return var2.a();
      }
   }

   @Redirect(
      method = {"travel"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/entity/LivingEntity;getXRot()F"
      )
   )
   private float hookModifyFallFlyingPitch(LivingEntity var1) {
      if (var1 != Minecraft.getInstance().player) {
         return var1.getXRot();
      } else {
         RecoveredEventsImplF var2 = new RecoveredEventsImplF(var1.getXRot());
         EixClient.a().b().a((Event)var2);
         return var2.a();
      }
   }

   @Inject(
      method = {"hasEffect"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void hasEffect(MobEffect var1, CallbackInfoReturnable<Boolean> var2) {
      LivingEntity var3 = (LivingEntity)(Object)this;
      if (var3 == Minecraft.getInstance().player) {
         FullBrightModule var4 = EixClient.a().g().a(FullBrightModule.class);
         if (var1 == MobEffects.NIGHT_VISION && var4.m()) {
            var2.setReturnValue(true);
            var2.cancel();
         }

         AntiNauseaModule var5 = EixClient.a().g().a(AntiNauseaModule.class);
         if (var1 == MobEffects.CONFUSION && var5.m()) {
            var2.setReturnValue(false);
            var2.cancel();
         }
      }
   }

   @Redirect(
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/entity/LivingEntity;getYRot()F"
      ),
      method = {"tickHeadTurn"}
   )
   private float modifyHeadYaw(LivingEntity var1) {
      if (var1 == Minecraft.getInstance().player) {
         RecoveredEventsImplV var2 = new RecoveredEventsImplV(var1.getYRot(), 0.0F, 0.0F, 0.0F);
         EixClient.a().b().a((Event)var2);
         return var2.a();
      } else {
         return var1.getYRot();
      }
   }
}
