package com.heypixel.heypixelmod.mixin.O;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.HUDModule;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.ChatScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({ChatScreen.class})
public class MixinChatScreen {
   @Inject(
      method = {"render"},
      at = {@At("HEAD")}
   )
   private void beginChatInputRender(GuiGraphics var1, int var2, int var3, float var4, CallbackInfo var5) {
      HUDModule var6 = EixClient.a().g().a(HUDModule.class);
      if (var6 != null) {
         var6.s();
      }
   }

   @Redirect(
      method = {"render"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V",
         ordinal = 0
      ),
      require = 0
   )
   private void redirectChatInputBackground(GuiGraphics var1, int var2, int var3, int var4, int var5, int var6) {
      HUDModule var7 = EixClient.a().g().a(HUDModule.class);
      if (var7 != null && var7.m() && var7.q.m()) {
         var7.b(var2, var3, var4, var5);
      } else {
         var1.fill(var2, var3, var4, var5, var6);
      }
   }

   @Inject(
      method = {"init"},
      at = {@At("TAIL")}
   )
   private void onChatScreenInit(CallbackInfo var1) {
      HUDModule var2 = EixClient.a().g().a(HUDModule.class);
      if (var2 != null) {
         var2.t();
      }
   }

   @Inject(
      method = {"removed"},
      at = {@At("HEAD")}
   )
   private void onChatScreenRemoved(CallbackInfo var1) {
      HUDModule var2 = EixClient.a().g().a(HUDModule.class);
      if (var2 != null) {
         var2.u();
      }
   }
}
