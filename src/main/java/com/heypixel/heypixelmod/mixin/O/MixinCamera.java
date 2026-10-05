package com.heypixel.heypixelmod.mixin.O;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.ViewClipModule;
import net.minecraft.client.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({Camera.class})
public class MixinCamera {
   @Inject(
      at = {@At("HEAD")},
      method = {"getMaxZoom"},
      cancellable = true
   )
   private void getMaxZoom(double var1, CallbackInfoReturnable<Double> var3) {
      if (EixClient.a() != null && EixClient.a().g() != null) {
         ViewClipModule var4 = EixClient.a().g().a(ViewClipModule.class);
         if (var4.m()) {
            var3.setReturnValue(var1 * (double)var4.c.q() * (double)var4.f.c / 100.0);
            var3.cancel();
         }
      }
   }
}
