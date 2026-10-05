package com.heypixel.heypixelmod.mixin.O;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.events.api.events.Event;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplAb;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplAc;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplO;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsD;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({Entity.class})
public abstract class MixinEntity {
   @Shadow
   protected Vec3 stuckSpeedMultiplier;

   @Shadow
   public abstract float getViewXRot(float var1);

   @Shadow
   public abstract float getViewYRot(float var1);

   @Shadow
   protected abstract Vec3 calculateViewVector(float var1, float var2);

   @Overwrite
   public final Vec3 getViewVector(float var1) {
      float var2 = this.getViewXRot(var1);
      float var3 = this.getViewYRot(var1);
      Entity var4 = (Entity)(Object)this;
      if (var4 == Minecraft.getInstance().player) {
         RecoveredEventsImplO var5 = new RecoveredEventsImplO(var4, var3, var2);
         EixClient.a().b().a((Event)var5);
         var3 = var5.b;
         var2 = var5.c;
      }

      return this.calculateViewVector(var2, var3);
   }

   @ModifyArg(
      method = {"moveRelative"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/entity/Entity;getInputVector(Lnet/minecraft/world/phys/Vec3;FF)Lnet/minecraft/world/phys/Vec3;",
         ordinal = 0
      ),
      index = 2
   )
   private float modifyYaw(float var1) {
      Entity var2 = (Entity)(Object)this;
      if (Minecraft.getInstance().player != var2) {
         return var1;
      } else {
         RecoveredEventsImplAb var3 = new RecoveredEventsImplAb(var1);
         EixClient.a().b().a((Event)var3);
         return var3.a();
      }
   }

   @Inject(
      method = {"makeStuckInBlock"},
      at = {@At("RETURN")}
   )
   private void makeStuckInBlock(BlockState var1, Vec3 var2, CallbackInfo var3) {
      Entity var4 = (Entity)(Object)this;
      if (Minecraft.getInstance().player == var4) {
         RecoveredEventsImplAc var5 = new RecoveredEventsImplAc(var1, var2);
         EixClient.a().b().a((Event)var5);
         if (var5.a()) {
            this.stuckSpeedMultiplier = Vec3.ZERO;
            return;
         }

         this.stuckSpeedMultiplier = var5.c();
      }
   }

   @Inject(
      method = {"push(Lnet/minecraft/world/entity/Entity;)V"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void push(Entity var1, CallbackInfo var2) {
      if (var1 instanceof RecoveredUtilsD) {
         var2.cancel();
      }
   }
}
