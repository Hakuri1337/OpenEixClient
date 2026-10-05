package com.heypixel.heypixelmod.obsoverlay.modules.impl.render;

import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAB;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplS;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAg;
import com.heypixel.heypixelmod.obsoverlay.utils.e.RecoveredUtilsEA;
import com.heypixel.heypixelmod.obsoverlay.utils.e.b.RecoveredUtilsEBC;
import io.github.humbleui.skija.ClipMode;
import io.github.humbleui.skija.Path;
import io.github.humbleui.types.RRect;
import java.awt.Color;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.util.StringUtil;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;

@ModuleInfo(
   a = "EffectHUD",
   b = "效果显示",
   c = "Displays potion effects on the HUD",
   d = ModuleCategory.RENDER
)
public class EffectHUDModule extends ClientModule {
   private final Map<MobEffect, EffectHUDModule.InnerA> c = new ConcurrentHashMap<>();
   private final RecoveredDAB d = RecoveredDD.a(this, "Position").e(10.0F).f(250.0F).a().f();

   @EventTarget
   public void onRender(RecoveredEventsImplS var1) {
      for (MobEffectInstance var3 : a.player.getActiveEffects()) {
         EffectHUDModule.InnerA var4;
         if (this.c.containsKey(var3.getEffect())) {
            var4 = this.c.get(var3.getEffect());
         } else {
            var4 = new EffectHUDModule.InnerA();
            this.c.put(var3.getEffect(), var4);
         }

         var4.d = Math.max(var4.d, var3.getDuration());
         var4.e = var3.getDuration();
         var4.f = var3.getAmplifier();
         var4.g = false;
      }

      int var19 = (int)this.d.o();

      for (Entry var21 : this.c.entrySet()) {
         EffectHUDModule.InnerA var5 = (EffectHUDModule.InnerA)var21.getValue();
         String var6 = this.a((MobEffect)var21.getKey(), var5);
         if (var5.b.c == -1.0F) {
            var5.b.c = (float)var19;
         }

         float var7 = var5.a.c;
         float var8 = var5.b.c;
         String var9 = StringUtil.formatTickDuration(var5.e);
         float var12 = 20.0F;
         float var13 = 20.0F;
         float var14 = var7 + 25.0F;
         float var16 = RecoveredUtilsEA.a(var6, RecoveredUtilsEBC.a(10.0F)) + RecoveredUtilsEA.a(var9, RecoveredUtilsEBC.a(8.0F)) + 12.0F;
         float var17 = 20.0F;
         var5.h = 25.0F + var16;
         var5.g = !a.player.hasEffect((MobEffect)var21.getKey());
         if (var5.g) {
            var5.a.a = -var5.h - 20.0F;
            if (var7 <= -var5.h - 20.0F) {
               this.c.remove(var21.getKey());
            }
         } else {
            var5.c.a = (float)var5.e / (float)var5.d * var5.h;
            if (var5.c.c <= 0.0F) {
               var5.c.c = var5.c.a;
            }

            var5.a.a = this.d.n();
            var5.b.a = (float)var19;
            var5.b.a(true);
         }

         var5.c.a(true);
         var5.a.a(true);
         RecoveredUtilsEA.c(var7, var8, var12, var13, 5.0F);
         RecoveredUtilsEA.a(var7, var8, var12, var13, 5.0F);
         RecoveredUtilsEA.c(var14, var8, var16, var17, 5.0F);
         RecoveredUtilsEA.a(var14, var8, var16, var17, 5.0F);
         Path var18 = new Path();
         var18.addRRect(RRect.makeXYWH(var7, var8, var12, var13, 5.0F));
         var18.addRRect(RRect.makeXYWH(var14, var8, var16, var17, 5.0F));
         RecoveredUtilsEA.c();
         RecoveredUtilsEA.e().clipPath(var18, ClipMode.INTERSECT, true);
         RecoveredUtilsEA.a(var7, var8, var5.h, 30.0F, new Color(0, 0, 0, 50));
         RecoveredUtilsEA.a(var7, var8, var5.c.c, 30.0F, new Color(0, 0, 0, 50));
         RecoveredUtilsEA.d();
         RecoveredUtilsEA.a((MobEffect)var21.getKey(), var7 + 2.0F, var8 + 2.0F, 16.0F, 16.0F);
         RecoveredUtilsEA.a(var6, var14 + 5.0F, var8 + 5.0F, new Color(255, 255, 255), RecoveredUtilsEBC.a(10.0F));
         RecoveredUtilsEA.a(
            var9, var14 + 7.0F + RecoveredUtilsEA.a(var6, RecoveredUtilsEBC.a(10.0F)), var8 + 8.0F, new Color(200, 200, 200), RecoveredUtilsEBC.a(8.0F)
         );
         var19 += 28;
         this.d.c(Math.max(var5.h, this.d.p()));
         this.d.d((float)var19 - this.d.o());
      }
   }

   public String a(MobEffect var1, EffectHUDModule.InnerA var2) {
      String var3 = var1.getDisplayName().getString();
      String var4;
      if (var2.f == 0) {
         var4 = "";
      } else if (var2.f == 1) {
         var4 = " " + I18n.get("enchantment.level.2");
      } else if (var2.f == 2) {
         var4 = " " + I18n.get("enchantment.level.3");
      } else if (var2.f == 3) {
         var4 = " " + I18n.get("enchantment.level.4");
      } else {
         var4 = " " + var2.f;
      }

      return var3 + var4;
   }

   public static class InnerA {
      public RecoveredUtilsAg a = new RecoveredUtilsAg(-60.0F, 0.2F);
      public RecoveredUtilsAg b = new RecoveredUtilsAg(-1.0F, 0.2F);
      public RecoveredUtilsAg c = new RecoveredUtilsAg(-1.0F, 0.2F);
      public int d = -1;
      public int e = 0;
      public int f = 0;
      public boolean g = false;
      public float h;
   }
}
