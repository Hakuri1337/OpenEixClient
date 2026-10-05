package com.heypixel.heypixelmod.mixin.O;

import com.heypixel.heypixelmod.obsoverlay.utils.d.a.RecoveredUtilsDAA;
import com.heypixel.heypixelmod.obsoverlay.utils.e.a.RecoveredUtilsEAA;
import com.mojang.blaze3d.platform.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({Window.class})
public class MixinWindow {
   @Shadow
   private int width;
   @Shadow
   private int height;

   @Inject(
      method = {"onFramebufferResize"},
      at = {@At("HEAD")}
   )
   private void onFramebufferResize(long var1, int var3, int var4, CallbackInfo var5) {
      RecoveredUtilsEAA.a(var3, var4);
      RecoveredUtilsDAA.a.a();
      RecoveredUtilsDAA.b.a();
   }
}
