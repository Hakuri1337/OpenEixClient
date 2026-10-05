package com.heypixel.heypixelmod.mixin.O;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({Screen.class})
public abstract class MixinScreen {
   @Inject(
      method = {"renderBackground(Lnet/minecraft/client/gui/GuiGraphics;)V"},
      at = {@At("HEAD")},
      cancellable = true,
      require = 0
   )
   private void cancelBackground(GuiGraphics var1, CallbackInfo var2) {
      var2.cancel();
   }
}
