package com.heypixel.heypixelmod.obsoverlay.modules.impl.render;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAE;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplP;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplS;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.a.KillAuraModule;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAa;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAg;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsS;
import com.heypixel.heypixelmod.obsoverlay.utils.e.RecoveredUtilsEA;
import com.heypixel.heypixelmod.obsoverlay.utils.f.RecoveredUtilsFB;
import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

@ModuleInfo(
   a = "TargetESP",
   b = "目标ESP",
   c = "Render face overlay",
   d = ModuleCategory.RENDER
)
public class TargetESPModule extends ClientModule {
   private static final float c = 10.0F;
   private static final float d = 6.0F;
   private static final float e = 18.0F;
   private static final float f = 48.0F;
   private final Map<Entity, RecoveredUtilsFB> g = new ConcurrentHashMap<>();
   private final Map<Entity, RecoveredUtilsAg> h = new ConcurrentHashMap<>();
   private final RecoveredDAE i = RecoveredDD.a(this, "Mode").a("Face", "Rectangle").a(0).a().e();
   private final RecoveredDAE j = RecoveredDD.a(this, "Image").a("于哲", "于思礼", "李丹").a(0).a(() -> this.i.a("Face")).a().e();
   private final RecoveredDAC k = RecoveredDD.a(this, "Size").a(48.0F).d(1.0F).b(8.0F).c(128.0F).a().c();
   private final RecoveredDAA l = RecoveredDD.a(this, "Hurt").a(true).a(() -> this.i.a("Face")).a().b();

   @EventTarget
   public void onRender(RecoveredEventsImplP var1) {
      try {
         this.a(var1.a());
      } catch (Exception var3) {
      }
   }

   @EventTarget
   public void onRenderSkia(RecoveredEventsImplS var1) {
      for (Entry<Entity, RecoveredUtilsFB> var3 : this.g.entrySet()) {
         if (var3.getKey() != a.player) {
            Entity target = var3.getKey();
            if (target instanceof Player var4) {
               RecoveredUtilsFB var5 = var3.getValue();
               if (var5.a != Float.MAX_VALUE && var5.b != Float.MAX_VALUE) {
                  if (this.i.a("Rectangle")) {
                     this.a(var4, var5);
                  } else {
                     double var6 = (double)a.player.distanceTo(var4);
                     float var8 = this.a(var6);
                     float var9 = Math.max(6.0F, this.k.q() * var8);
                     float var10 = var5.a - var9 / 2.0F;
                     float var11 = var5.b - var9 / 2.0F;
                     RecoveredUtilsEA.a(this.p(), var10, var11, var9, var9);
                     this.a(var4, var10, var11, var9);
                  }
               }
            }
         }
      }
   }

   private void a(Player var1, float var2, float var3, float var4) {
      RecoveredUtilsAg var5 = this.h.computeIfAbsent(var1, var0 -> new RecoveredUtilsAg(0.0F));
      var5.a = this.l.m() && var1.hurtTime > 0 ? 120.0F : 0.0F;
      var5.a(true);
      if (var5.c > 1.0F) {
         RecoveredUtilsEA.a(var2, var3, var4, var4, 0.0F, new Color(255, 0, 0, (int)var5.c));
      }
   }

   private String p() {
      if (this.j.a("于思礼")) {
         return "yuzhe/ysl.png";
      } else {
         return this.j.a("李丹") ? "yuzhe/lidan.png" : "yuzhe/yuzhe.jpg";
      }
   }

   private String q() {
      return "targetesp/rectangle.png";
   }

   private float a(double var1) {
      double var3 = Math.max(6.0, Math.min(var1, 18.0));
      return (float)(10.0 / var3);
   }

   private void a(float var1) {
      this.g.clear();
      KillAuraModule var2 = EixClient.a().g().a(KillAuraModule.class);
      if (var2 != null && var2.m() && var2.c != null) {
         List<Entity> var3 = var2.p();
         if (var3 != null && !var3.isEmpty()) {
            for (Entity var5 : var3) {
               if (var5 instanceof Player) {
                  RecoveredUtilsFB var6 = this.i.a("Rectangle") ? this.b(var5, var1) : this.a(var5, var1);
                  this.g.put(var5, var6);
               }
            }

            this.h.keySet().retainAll(this.g.keySet());
         } else {
            this.h.clear();
         }
      } else {
         this.h.clear();
      }
   }

   private RecoveredUtilsFB a(Entity var1, float var2) {
      double var3 = RecoveredUtilsS.b(var1.xo, var1.getX(), (double)var2);
      double var5 = RecoveredUtilsS.b(var1.yo, var1.getY(), (double)var2) + (double)var1.getEyeHeight();
      double var7 = RecoveredUtilsS.b(var1.zo, var1.getZ(), (double)var2);
      return RecoveredUtilsAa.a(var3, var5, var7, var2);
   }

   private RecoveredUtilsFB b(Entity var1, float var2) {
      double var3 = RecoveredUtilsS.b(var1.xo, var1.getX(), (double)var2);
      double var5 = RecoveredUtilsS.b(var1.yo, var1.getY(), (double)var2);
      double var7 = RecoveredUtilsS.b(var1.zo, var1.getZ(), (double)var2);
      double var9 = (double)var1.getBbHeight();
      if (var1 instanceof LivingEntity var11 && var11.isBaby()) {
         var9 /= 1.75;
      }

      var9 /= 2.0;
      RecoveredUtilsFB var16 = RecoveredUtilsAa.a(var3, var5, var7, var2);
      RecoveredUtilsFB var12 = RecoveredUtilsAa.a(var3, var5 + var9, var7, var2);
      float var13 = Float.MAX_VALUE;
      float var14 = Float.MAX_VALUE;
      if (var16.a != Float.MAX_VALUE && var16.b != Float.MAX_VALUE) {
         var13 = Math.min(var13, var16.a);
         var14 = Math.min(var14, var16.b);
      }

      if (var12.a != Float.MAX_VALUE && var12.b != Float.MAX_VALUE) {
         var13 = Math.min(var13, var12.a);
         var14 = Math.min(var14, var12.b);
      }

      return var13 == Float.MAX_VALUE ? new RecoveredUtilsFB(Float.MAX_VALUE, Float.MAX_VALUE) : new RecoveredUtilsFB(var13, var14);
   }

   private void a(Player var1, RecoveredUtilsFB var2) {
      float var3 = a.player.distanceTo(var1);
      float var4 = 1.0F - Mth.clamp(Math.abs(var3 - 6.0F) / 60.0F, 0.0F, 0.75F);
      long var5 = System.currentTimeMillis() + 1200L;
      double var7 = Mth.clamp((Math.sin((double)var5 / 150.0) + 1.0) / 2.0 * 30.0, 0.0, 30.0);
      double var9 = Mth.clamp((Math.sin((double)var5 / 500.0) + 1.0) / 2.0, 0.8, 1.0);
      double var11 = Mth.clamp((Math.sin((double)var5 / 1000.0) + 1.0) / 2.0 * 360.0, 0.0, 360.0);
      var11 = 45.0 - (var7 - 15.0) + var11;
      float var13 = 128.0F * var4 * (float)var9;
      float var14 = var2.a - var13 / 2.0F;
      float var15 = var2.b - var13 / 2.0F;
      RecoveredUtilsEA.c();
      RecoveredUtilsEA.f(var14, var15, var13, var13, (float)var11);
      RecoveredUtilsEA.a(this.q(), var14, var15, var13, var13);
      RecoveredUtilsEA.d();
   }
}
