package com.heypixel.heypixelmod.obsoverlay.modules.impl.render;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.c.RecoveredCB;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAB;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.EventRunTicks;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplS;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.a.KillAuraModule;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAg;
import com.heypixel.heypixelmod.obsoverlay.utils.e.RecoveredUtilsEA;
import com.heypixel.heypixelmod.obsoverlay.utils.e.b.RecoveredUtilsEBC;
import io.github.humbleui.skija.ClipMode;
import io.github.humbleui.skija.Font;
import io.github.humbleui.skija.Paint;
import io.github.humbleui.skija.Path;
import io.github.humbleui.types.RRect;
import io.github.humbleui.types.Rect;
import java.awt.Color;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.phys.EntityHitResult;

@ModuleInfo(
   a = "TargetHUD",
   b = "目标状态显示",
   c = "Display target info.",
   d = ModuleCategory.RENDER
)
public class TargetHUDModule extends ClientModule {
   private final RecoveredUtilsAg c = new RecoveredUtilsAg(20.0F);
   private final RecoveredUtilsAg d = new RecoveredUtilsAg(255.0F);
   private final RecoveredUtilsAg e = new RecoveredUtilsAg(0.0F);
   private final RecoveredUtilsAg f = new RecoveredUtilsAg(20.0F);
   private final RecoveredDAB g = RecoveredDD.a(this, "Position").e(500.0F).f(200.0F).a().f();
   private final RecoveredDAA h = RecoveredDD.a(this, "HurtFlash").a(true).a().b();
   private final RecoveredDAA i = RecoveredDD.a(this, "ShowAbsorption").a(true).a().b();
   private AbstractClientPlayer j;

   @EventTarget
   public void onRenderSkia(RecoveredEventsImplS var1) {
      if (a.screen instanceof RecoveredCB) {
         this.j = a.player;
         this.d.c = 255.0F;
      }

      this.d.a(true);
      if (!(this.d.c < 1.0F)) {
         this.c.a(true);
         this.e.a(true);
         this.f.a(true);
         float var2 = this.g.n();
         float var3 = this.g.o();
         float var4 = 240.0F;
         float var5 = 40.0F;
         Font var6 = RecoveredUtilsEBC.a(10.0F);
         Font var7 = RecoveredUtilsEBC.b(10.0F);
         float var8 = var7.measureTextWidth("\ue87d");
         String var9 = this.j.getName().getString();
         float var10 = RecoveredUtilsEA.a(var9, var6);
         String var11 = String.format("%.1f / 20", this.c.c);
         float var12 = RecoveredUtilsEA.a(var11, var6);
         float var13 = var3 + 11.0F;
         float var14 = var10 + 10.0F;
         float var15 = var2 + 44.0F + var14 + var8 + 4.0F + var12;
         float var16 = 160.0F;
         float var17 = var16;
         float var18 = 46.0F + var16;
         float var19 = Math.max(var4, var18 + 10.0F);

         try (Paint var20 = new Paint()) {
            var20.setAlpha((int)this.d.c);
            RecoveredUtilsEA.e().saveLayer(Rect.makeXYWH(var2 - 14.0F, var3 - 14.0F, var19 + 28.0F, var5 + 28.0F), var20);
            RecoveredUtilsEA.c(var2, var3, var19, var5, 8.0F);
            RecoveredUtilsEA.a(var2, var3, var19, var5, 8.0F);
            RecoveredUtilsEA.a(var2, var3, var19, var5, 8.0F, new Color(0, 0, 0, 70));
            RecoveredUtilsEA.a(this.j, var2 + 8.0F, var3 + 6.0F, 28.0F, 28.0F, 4.0F);
            if (this.e.c > 1.0F) {
               RecoveredUtilsEA.a(var2 + 8.0F, var3 + 6.0F, 28.0F, 28.0F, 4.0F, new Color(255, 60, 60, (int)this.e.c));
            }

            RecoveredUtilsEA.a(var9, var2 + 44.0F, var13, new Color(255, 255, 255), var6);
            RecoveredUtilsEA.a("\ue87d", var2 + 44.0F + var14, var13 - 0.5F, new Color(215, 215, 215), var7);
            RecoveredUtilsEA.a(var11, var2 + 44.0F + var14 + var8 + 4.0F, var13, new Color(215, 215, 215), var6);
            Path var21 = new Path();
            var21.addRRect(RRect.makeXYWH(var2 + 44.0F, var3 + 24.0F, var16, 5.5F, 0.8F));
            RecoveredUtilsEA.c();
            RecoveredUtilsEA.e().clipPath(var21, ClipMode.INTERSECT, true);
            RecoveredUtilsEA.a(var2 + 44.0F, var3 + 24.0F, var16, 5.5F, new Color(0, 0, 0, 75));
            float var22 = var16 / 20.0F * this.c.c;
            float var23 = (float)Math.min(255, (int)(255.0F - this.c.c / 20.0F * 190.0F));
            float var24 = (float)Math.min(255, (int)(70.0F + this.c.c / 20.0F * 185.0F));
            RecoveredUtilsEA.a(var2 + 44.0F, var3 + 24.0F, var22, 5.5F, new Color((int)var23, (int)var24, 35, 225));
            RecoveredUtilsEA.d();
            var21.close();
            if (this.i.m() && this.f.c > 0.1F) {
               Path var25 = new Path();
               var25.addRRect(RRect.makeXYWH(var2 + 44.0F, var3 + 31.0F, var17, 4.0F, 0.6F));
               RecoveredUtilsEA.c();
               RecoveredUtilsEA.e().clipPath(var25, ClipMode.INTERSECT, true);
               RecoveredUtilsEA.a(var2 + 44.0F, var3 + 31.0F, var17, 4.0F, new Color(0, 0, 0, 65));
               float var26 = var17 / 8.0F * this.f.c;
               RecoveredUtilsEA.a(var2 + 44.0F, var3 + 31.0F, var26, 4.0F, new Color(255, 215, 70, 235));
               RecoveredUtilsEA.d();
               var25.close();
            }

            RecoveredUtilsEA.d();
         }

         this.g.c(var19);
         this.g.d(var5);
      }
   }

   @EventTarget
   public void onPreTick(EventRunTicks var1) {
      if (a.player != null && var1.type() == RecoveredEventsApiAA.PRE) {
         AbstractClientPlayer var3;
         label33: {
            KillAuraModule var2 = EixClient.a().g().a(KillAuraModule.class);
            var3 = null;
            if (a.hitResult instanceof EntityHitResult var4 && var4.getEntity() instanceof AbstractClientPlayer var5) {
               var3 = var5;
               break label33;
            }

            if (var2.m() && var2.c instanceof AbstractClientPlayer var6) {
               var3 = var6;
            }
         }

         if (var3 != null) {
            this.j = var3;
            this.d.a = 255.0F;
            this.c.a = this.j.getHealth();
            this.f.a = this.j.getAbsorptionAmount();
            this.e.a = this.h.m() && this.j.hurtTime > 0 ? 140.0F : 0.0F;
         } else {
            this.d.a = 0.0F;
            this.e.a = 0.0F;
            this.f.a = 0.0F;
            this.j = null;
         }
      }
   }
}
