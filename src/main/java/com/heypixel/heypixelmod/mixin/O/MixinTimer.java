package com.heypixel.heypixelmod.mixin.O;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import net.minecraft.client.Timer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({Timer.class})
public class MixinTimer {
   @Shadow
   public float partialTick;
   @Shadow
   public float tickDelta;
   @Shadow
   private long lastMs;
   @Final
   @Shadow
   private float msPerTick;

   @Inject(
      method = {"advanceTime"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void beginRenderTickHook(long var1, CallbackInfoReturnable<Integer> var3) {
      if (EixClient.c != 1.0F) {
         this.tickDelta = (float)(var1 - this.lastMs) / this.msPerTick * EixClient.c;
         this.lastMs = var1;
         this.partialTick = this.partialTick + this.tickDelta;
         int var4 = (int)this.partialTick;
         this.partialTick -= (float)var4;
         var3.setReturnValue(var4);
      }
   }
}
