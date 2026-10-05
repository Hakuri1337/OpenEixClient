package com.heypixel.heypixelmod.obsoverlay.modules.impl.render;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.c.RecoveredCB;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAB;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAE;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplS;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAg;
import com.heypixel.heypixelmod.obsoverlay.utils.e.RecoveredUtilsEA;
import com.heypixel.heypixelmod.obsoverlay.utils.e.b.RecoveredUtilsEBC;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.platform.InputConstants.Key;
import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.Font;
import io.github.humbleui.types.RRect;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@ModuleInfo(
   a = "KeyBinds",
   b = "按键显示",
   c = "显示所有按键绑定",
   d = ModuleCategory.RENDER
)
public class KeyBindsModule extends ClientModule {
   private static final Color e = new Color(0, 0, 0, 100);
   private final RecoveredDAB f = RecoveredDD.a(this, "Position").e(10.0F).f(80.0F).a().f();
   public RecoveredDAC c = RecoveredDD.a(this, "KeyBinds Size").a(10.0F).d(1.0F).b(6.0F).c(24.0F).a().c();
   public RecoveredDAE d = RecoveredDD.a(this, "Style").a("Style A", "Style B").a(0).a().e();
   private final Map<ClientModule, RecoveredUtilsAg> g = new HashMap<>();
   private final Map<ClientModule, RecoveredUtilsAg> h = new HashMap<>();
   private final Map<ClientModule, RecoveredUtilsAg> i = new HashMap<>();
   private final Map<ClientModule, RecoveredUtilsAg> j = new HashMap<>();
   private final RecoveredUtilsAg k = new RecoveredUtilsAg(100.0F, 100.0F, 0.2F);
   private final RecoveredUtilsAg l = new RecoveredUtilsAg(20.0F, 20.0F, 0.2F);

   @EventTarget
   public void onSkia(RecoveredEventsImplS var1) {
      if (this.d.a("Style A")) {
         this.q();
      } else if (this.d.a("Style B")) {
         this.p();
      }
   }

   private void p() {
      float var1 = this.c.q() * 1.0F;
      float var2 = this.c.q() * 0.9F;
      Font var3 = RecoveredUtilsEBC.a(var1);
      Font var4 = RecoveredUtilsEBC.b(var1);
      Font var5 = RecoveredUtilsEBC.a(var2);
      ArrayList<ClientModule> var6 = new ArrayList<>(EixClient.a().g().a());
      boolean var7 = a.screen instanceof RecoveredCB;
      float var8 = var1 * 1.5F;
      float var9 = var2 * 1.5F;
      float var10 = this.c.q() / 3.0F;
      float var11 = var10 * 0.6F;
      float var12 = var10 * 2.0F;
      Color var13 = new Color(0, 0, 0, 70);
      boolean var14 = this.f.o() < (float)a.getWindow().getGuiScaledHeight() / 2.0F;
      var6.sort((var4x, var5x) -> {
         float var6x = 0.0F;
         if (var4x != this && var4x.o() != 0) {
            float var7x = RecoveredUtilsEA.a(var4x.h(), var5);
            float var8x = RecoveredUtilsEA.a(this.c(var4x.o()), var5) + var10 * 2.0F;
            var6x = var7x + var10 * 2.0F + var10 + var8x;
         }

         float var10x = 0.0F;
         if (var5x != this && var5x.o() != 0) {
            float var11x = RecoveredUtilsEA.a(var5x.h(), var5);
            float var9x = RecoveredUtilsEA.a(this.c(var5x.o()), var5) + var10 * 2.0F;
            var10x = var11x + var10 * 2.0F + var10 + var9x;
         }

         return var14 ? Float.compare(var10x, var6x) : Float.compare(var6x, var10x);
      });
      ArrayList<ClientModule> var15 = new ArrayList();

      for (ClientModule var17 : var6) {
         if (var17 != this && var17.o() != 0 && (var7 || !var17.m())) {
            var15.add(var17);
         }
      }

      String var44 = "Key Bind";
      float var45 = RecoveredUtilsEA.a(var44, var3);
      float var18 = RecoveredUtilsEA.a("\ue312", var4);
      float var19 = var45 + var10 * 4.0F + var18;

      for (ClientModule var21 : var15) {
         String var22 = var21.h();
         String var23 = this.c(var21.o());
         float var24 = RecoveredUtilsEA.a(var22, var5);
         float var25 = RecoveredUtilsEA.a(var23, var5);
         float var26 = var24 + var10 * 4.0F + var25;
         if (var26 > var19) {
            var19 = var26;
         }
      }

      float var46 = var19 + var10 * 2.0F;
      float var47 = var8 + var11 + (float)var15.size() * var9 + var10 * 2.0F;
      this.k.a = var46;
      this.l.a = var47;
      this.k.a(true);
      this.l.a(true);
      float var48 = this.k.c;
      float var49 = this.l.c;
      float var50 = this.f.n();
      float var51 = this.f.o();
      RecoveredUtilsEA.c(var50, var51, var48, var49, var12);
      RecoveredUtilsEA.a(var50, var51, var48, var49, var12);
      RecoveredUtilsEA.a(var50, var51, var48, var49, var12, var13);
      Canvas var52 = RecoveredUtilsEA.e();
      int var27 = var52.save();
      RRect var28 = RRect.makeXYWH(var50, var51, var48, var49, var12);
      var52.clipRRect(var28, true);
      float var29 = var51 + var10 + var8 / 2.0F;
      RecoveredUtilsEA.c(var44, var50 + var10, var29, Color.WHITE, var3);
      RecoveredUtilsEA.c("\ue312", var50 + var48 - var10 - var18, var29, Color.WHITE, var4);
      HashMap var30 = new HashMap();
      float var31 = var10 + var8 + var11;

      for (ClientModule var33 : var15) {
         var30.put(var33, var31);
         var31 += var9;
      }

      for (ClientModule var54 : var6) {
         if (var54 != this && var54.o() != 0) {
            boolean var34 = var15.contains(var54);
            RecoveredUtilsAg var35 = this.i.computeIfAbsent(var54, var0 -> new RecoveredUtilsAg(0.0F, 0.0F, 0.2F));
            RecoveredUtilsAg var36 = this.j.computeIfAbsent(var54, var0 -> new RecoveredUtilsAg(0.0F, 0.0F, 0.2F));
            if (var34) {
               var35.a = (Float)var30.get(var54);
               var36.a = 255.0F;
            } else {
               var35.a = var35.c;
               var36.a = 0.0F;
            }

            var35.a(true);
            var36.a(true);
            float var37 = var36.c;
            if (!(var37 < 1.0F)) {
               float var38 = var35.c;
               float var39 = var51 + var38 + var9 / 2.0F;
               Color var40 = new Color(255, 255, 255, (int)Math.min(255.0F, Math.max(0.0F, var37)));
               String var41 = var54.h();
               String var42 = this.c(var54.o());
               RecoveredUtilsEA.c(var41, var50 + var10, var39, var40, var5);
               float var43 = RecoveredUtilsEA.a(var42, var5);
               RecoveredUtilsEA.c(var42, var50 + var48 - var10 - var43, var39, var40, var5);
            }
         }
      }

      var52.restoreToCount(var27);
      this.f.c(var48);
      this.f.d(var49);
   }

   private void q() {
      float var1 = this.c.q() * 1.0F;
      float var2 = this.c.q() * 0.9F;
      Font var3 = RecoveredUtilsEBC.a(var1);
      Font var4 = RecoveredUtilsEBC.b(var1);
      Font var5 = RecoveredUtilsEBC.a(var2);
      ArrayList<ClientModule> var6 = new ArrayList<>(EixClient.a().g().a());
      boolean var7 = a.screen instanceof RecoveredCB;
      float var8 = var1 * 1.5F;
      float var9 = var2 * 1.5F;
      float var10 = this.c.q() / 3.0F;
      float var11 = var10 * 0.6F;
      boolean var12 = this.f.o() < (float)a.getWindow().getGuiScaledHeight() / 2.0F;
      var6.sort((var4x, var5x) -> {
         float var6x = 0.0F;
         if (var4x != this && var4x.o() != 0) {
            float var7x = RecoveredUtilsEA.a(var4x.h(), var5);
            float var8x = RecoveredUtilsEA.a(this.c(var4x.o()), var5) + var10 * 2.0F;
            var6x = var7x + var10 * 2.0F + var10 + var8x;
         }

         float var10x = 0.0F;
         if (var5x != this && var5x.o() != 0) {
            float var11x = RecoveredUtilsEA.a(var5x.h(), var5);
            float var9x = RecoveredUtilsEA.a(this.c(var5x.o()), var5) + var10 * 2.0F;
            var10x = var11x + var10 * 2.0F + var10 + var9x;
         }

         return var12 ? Float.compare(var10x, var6x) : Float.compare(var6x, var10x);
      });
      String var13 = "Key Binds";
      float var14 = RecoveredUtilsEA.a(var13, var3);
      float var15 = RecoveredUtilsEA.a("\ue312", var4);
      float var16 = var15 + var10 + var14 + var10 * 2.0F;
      float var17 = var16;

      for (ClientModule var19 : var6) {
         if (var19 != this) {
            int var20 = var19.o();
            if (var20 != 0) {
               String var21 = var19.h();
               String var22 = this.c(var20);
               float var23 = RecoveredUtilsEA.a(var21, var5);
               float var24 = RecoveredUtilsEA.a(var22, var5) + var10 * 2.0F;
               float var25 = var23 + var10 * 2.0F;
               float var26 = var25 + var10 + var24;
               if (var26 > var17) {
                  var17 = var26;
               }
            }
         }
      }

      boolean var45 = this.f.n() >= (float)a.getWindow().getGuiScaledWidth() / 2.0F;
      float var46 = this.f.o();
      float var48 = var45 ? this.f.n() + var17 - var16 : this.f.n();
      RecoveredUtilsEA.c(var48, var46, var16, var8, var10);
      RecoveredUtilsEA.a(var48, var46, var16, var8, var10);
      RecoveredUtilsEA.a(var48, var46, var16, var8, var10, e);
      float var49 = var48 + var10;
      float var50 = var49 + var15 + var10;
      float var51 = var46 + var8 / 2.0F;
      RecoveredUtilsEA.c("\ue312", var49, var51, Color.WHITE, var4);
      RecoveredUtilsEA.c(var13, var50, var51, Color.WHITE, var3);
      var46 += var8 + var10 + var11;

      for (ClientModule var53 : var6) {
         if (var53 != this) {
            int var54 = var53.o();
            if (var54 != 0) {
               String var27 = var53.h();
               String var28 = this.c(var54);
               float var29 = RecoveredUtilsEA.a(var27, var5);
               float var30 = RecoveredUtilsEA.a(var28, var5) + var10 * 2.0F;
               float var31 = var29 + var10 * 2.0F;
               float var32 = var31 + var10 + var30;
               float var33 = var45 ? this.f.n() + var17 - var32 : this.f.n();
               float var34 = var45 ? this.f.n() + var17 + var32 : this.f.n() - var32 - var10;
               RecoveredUtilsAg var35 = this.g.get(var53);
               if (var35 == null) {
                  var35 = new RecoveredUtilsAg(var34, var34, 0.2F);
                  this.g.put(var53, var35);
               }

               RecoveredUtilsAg var36 = this.h.get(var53);
               if (var36 == null) {
                  var36 = new RecoveredUtilsAg(var46, var46, 0.2F);
                  this.h.put(var53, var36);
               }

               RecoveredUtilsAg var37 = this.j.get(var53);
               if (var37 == null) {
                  var37 = new RecoveredUtilsAg(0.0F, 0.0F, 0.25F);
                  this.j.put(var53, var37);
               }

               boolean var38 = var7 || !var53.m();
               var35.a = var38 ? var33 : var34;
               var35.a(true);
               var37.a = var38 ? 255.0F : 0.0F;
               var37.a(true);
               if (var38) {
                  var36.a = var46;
                  var36.a(true);
                  var46 += var9 + var10;
               } else {
                  var36.a = var36.c;
                  var36.a(true);
               }

               if (var38 || !var37.b(true) || !var35.b(true)) {
                  float var39 = Math.min(1.0F, var37.c / 255.0F);
                  if (!(var39 <= 0.01F)) {
                     float var40 = var35.c;
                     float var41 = var36.c;
                     float var42 = var40 + var31 + var10;
                     if (var39 > 0.1F) {
                        RecoveredUtilsEA.c(var40, var41, var31, var9, var10);
                        RecoveredUtilsEA.a(var40, var41, var31, var9, var10);
                        RecoveredUtilsEA.c(var42, var41, var30, var9, var10);
                        RecoveredUtilsEA.a(var42, var41, var30, var9, var10);
                     }

                     Color var43 = new Color(0, 0, 0, Math.min(255, Math.max(0, (int)((float)e.getAlpha() * var39))));
                     Color var44 = new Color(255, 255, 255, Math.min(255, Math.max(0, (int)(255.0F * var39))));
                     RecoveredUtilsEA.a(var40, var41, var31, var9, var10, var43);
                     RecoveredUtilsEA.a(var42, var41, var30, var9, var10, var43);
                     RecoveredUtilsEA.a(var27, var40 + var10, var41 + var10, var44, var5);
                     RecoveredUtilsEA.a(var28, var42 + var10, var41 + var10, var44, var5);
                  }
               }
            }
         }
      }

      this.f.c(var17);
      this.f.d(var46);
   }

   private String c(int var1) {
      if (var1 == 0) {
         return "NONE";
      } else if (var1 < 0) {
         return "M" + -var1;
      } else {
         Key var2 = InputConstants.getKey(var1, 0);
         if (var2 == InputConstants.UNKNOWN) {
            return "NONE";
         } else {
            String var3 = var2.getDisplayName().getString();
            return var3 != null && !var3.isEmpty() ? var3.toUpperCase() : "NONE";
         }
      }
   }
}
