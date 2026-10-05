package com.heypixel.heypixelmod.mixin.O;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.HUDModule;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({InventoryScreen.class})
public class MixinInventoryScreen {
   @Redirect(
      method = {"renderBg"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/GuiGraphics;blit(Lnet/minecraft/resources/ResourceLocation;IIIIII)V"
      )
   )
   private void redirectBlit(GuiGraphics var1, ResourceLocation var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      HUDModule var9 = EixClient.a().g().a(HUDModule.class);
      if (var9 == null || !var9.m() || !var9.r.m()) {
         var1.blit(var2, var3, var4, var5, var6, var7, var8);
      }
   }
}
