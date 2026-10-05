package com.heypixel.heypixelmod.mixin.O;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.HUDModule;
import com.heypixel.heypixelmod.obsoverlay.utils.e.RecoveredUtilsEA;
import com.heypixel.heypixelmod.obsoverlay.utils.e.a.RecoveredUtilsEAA;
import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({AbstractContainerScreen.class})
public abstract class MixinAbstractContainerScreen {
   @Shadow
   protected int leftPos;
   @Shadow
   protected int topPos;
   @Shadow
   protected int imageWidth;
   @Shadow
   protected int imageHeight;

   @Shadow
   protected abstract void renderBg(GuiGraphics var1, float var2, int var3, int var4);

   @Inject(
      method = {"render"},
      at = {@At("HEAD")}
   )
   private void renderSkia(GuiGraphics var1, int var2, int var3, float var4, CallbackInfo var5) {
      HUDModule var6 = EixClient.a().g().a(HUDModule.class);
      if (var6 != null && var6.m() && var6.r.m()) {
         try {
            RecoveredUtilsEAA.a(var2x -> {
               RecoveredUtilsEA.c();
               RecoveredUtilsEA.a((float)Minecraft.getInstance().getWindow().getGuiScale());
               float var3x = (float)this.leftPos;
               float var4x = (float)this.topPos;
               float var5x = (float)this.imageWidth;
               float var6x = (float)this.imageHeight;
               float var7 = var6.u.q();
               if (var6.t.m()) {
                  RecoveredUtilsEA.c(var3x, var4x, var5x, var6x, var7);
               }

               if (var6.s.m()) {
                  RecoveredUtilsEA.a(var3x, var4x, var5x, var6x, var7);
               }

               RecoveredUtilsEA.a(var3x, var4x, var5x, var6x, var7, new Color(18, 18, 18, (int)var6.v.q()));
               AbstractContainerMenu var8x = ((AbstractContainerScreen)(Object)this).getMenu();
               if (var8x != null) {
                  Color var9 = new Color(25, 25, 25, (int)var6.w.q());

                  for (Slot var11 : var8x.slots) {
                     float var12 = var3x + (float)var11.x;
                     float var13 = var4x + (float)var11.y;
                     RecoveredUtilsEA.a(var12, var13, 16.0F, 16.0F, 4.0F, var9);
                  }
               }

               RecoveredUtilsEA.d();
            });
         } catch (Throwable var8) {
            var8.printStackTrace();
         }
      }
   }

   @Redirect(
      method = {"render"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/screens/inventory/AbstractContainerScreen;renderBg(Lnet/minecraft/client/gui/GuiGraphics;FII)V"
      )
   )
   private void redirectRenderBg(AbstractContainerScreen<?> var1, GuiGraphics var2, float var3, int var4, int var5) {
      HUDModule var6 = EixClient.a().g().a(HUDModule.class);
      if (var6 == null || !var6.m() || !var6.r.m() || var1 instanceof InventoryScreen) {
         this.renderBg(var2, var3, var4, var5);
      }
   }
}
