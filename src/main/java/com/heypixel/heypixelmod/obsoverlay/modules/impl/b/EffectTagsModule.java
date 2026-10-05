package com.heypixel.heypixelmod.obsoverlay.modules.impl.b;

import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.impl.EventRender2D;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplP;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAa;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAf;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsI;
import com.heypixel.heypixelmod.obsoverlay.utils.f.RecoveredUtilsFB;
import com.heypixel.heypixelmod.obsoverlay.utils.renderer.RecoveredUtilsRendererD;
import java.awt.Color;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.world.entity.Entity;
import java.util.LinkedHashSet;

@ModuleInfo(
   a = "EffectTags",
   b = "效果标签",
   c = "Show the player's effect tags.",
   d = ModuleCategory.MISC
)
public class EffectTagsModule extends ClientModule {
   private final RecoveredDAA c = RecoveredDD.a(this, "Debug").a(false).a().b();
   private final RecoveredDAA d = RecoveredDD.a(this, "Shared").a(true).a().b();
   private final List<EffectTagsModule.InnerA> e = new CopyOnWriteArrayList<>();

   @EventTarget
   public void update(RecoveredEventsImplP var1) {
      try {
         this.a(var1.a());
      } catch (Exception var3) {
      }
   }

   private void a(float var1) {
      this.e.clear();

      for (Entity var3 : a.level.entitiesForRendering()) {
         if (var3 != a.player && var3 instanceof AbstractClientPlayer) {
            double var4 = a(var1, var3.xo, var3.getX());
            double var6 = a(var1, var3.yo, var3.getY()) + (double)var3.getBbHeight();
            double var8 = a(var1, var3.zo, var3.getZ());
            RecoveredUtilsFB var10 = RecoveredUtilsAa.a(var4, var6, var8, var1);
            this.e.add(new EffectTagsModule.InnerA((AbstractClientPlayer)var3, var10, RecoveredUtilsI.a((AbstractClientPlayer)var3)));
         }
      }

      if (this.d.m()) {
         Map<String, RecoveredUtilsAf> var12 = RecoveredUtilsI.a();

         for (RecoveredUtilsAf var14 : var12.values()) {
            double var5 = var14.b();
            double var7 = var14.c() + (double)a.player.getBbHeight();
            double var9 = var14.d();
            RecoveredUtilsFB var11 = RecoveredUtilsAa.a(var5, var7, var9, var1);
            this.e.add(new EffectTagsModule.InnerA(null, var11, Set.of(var14.i())));
         }
      }
   }

   @EventTarget
   public void onRender(EventRender2D var1) {
      for (EffectTagsModule.InnerA var3 : this.e) {
         var1.stack().pushPose();
         double var4 = 0.0;

         for (String var7 : var3.c()) {
            RecoveredUtilsRendererD.a.a(var1.stack(), I18n.get(var7), (double)(var3.b().a + 10.0F), (double)var3.b().b + var4, Color.RED, true, 0.3F);
            var4 += RecoveredUtilsRendererD.a.a(true, 0.3F);
         }

         if (this.c.m() && var3.a() != null) {
            AbstractClientPlayer var10 = var3.a();
            LinkedHashSet<String> var11 = new LinkedHashSet<>();
            var11.add("X: " + var10.getX());
            var11.add("Y: " + var10.getY());
            var11.add("Z: " + var10.getZ());
            var11.add("Ticks: " + var10.tickCount);

            for (String var9 : var11) {
               RecoveredUtilsRendererD.a.a(var1.stack(), var9, (double)(var3.b().a + 10.0F), (double)var3.b().b + var4, Color.RED, true, 0.35F);
               var4 += RecoveredUtilsRendererD.a.a(true, 0.35F);
            }
         }

         var1.stack().popPose();
      }
   }

   public static float a(float var0, float var1, float var2) {
      return var1 + var0 * (var2 - var1);
   }

   public static double a(double var0, double var2, float var4) {
      return var2 + var0 * ((double)var4 - var2);
   }

   public static double a(float var0, double var1, double var3) {
      return var1 + (double)var0 * (var3 - var1);
   }

   private static class InnerA {
      AbstractClientPlayer a;
      RecoveredUtilsFB b;
      Set<String> c;

      public InnerA(AbstractClientPlayer var1, RecoveredUtilsFB var2, Set<String> var3) {
         this.a = var1;
         this.b = var2;
         this.c = var3;
      }

      public AbstractClientPlayer a() {
         return this.a;
      }

      public void a(AbstractClientPlayer var1) {
         this.a = var1;
      }

      public RecoveredUtilsFB b() {
         return this.b;
      }

      public void a(RecoveredUtilsFB var1) {
         this.b = var1;
      }

      public Set<String> c() {
         return this.c;
      }

      public void a(Set<String> var1) {
         this.c = var1;
      }

      @Override
      public boolean equals(Object var1) {
         if (var1 == this) {
            return true;
         } else if (var1 instanceof EffectTagsModule.InnerA var2) {
            if (!var2.a(this)) {
               return false;
            } else {
               AbstractClientPlayer var3 = this.a();
               AbstractClientPlayer var4 = var2.a();
               if (Objects.equals(var3, var4)) {
                  RecoveredUtilsFB var5 = this.b();
                  RecoveredUtilsFB var6 = var2.b();
                  if (Objects.equals(var5, var6)) {
                     Set var7 = this.c();
                     Set var8 = var2.c();
                     return Objects.equals(var7, var8);
                  } else {
                     return false;
                  }
               } else {
                  return false;
               }
            }
         } else {
            return false;
         }
      }

      protected boolean a(Object var1) {
         return var1 instanceof EffectTagsModule.InnerA;
      }

      @Override
      public int hashCode() {
         byte var1 = 59;
         int var2 = 1;
         AbstractClientPlayer var3 = this.a();
         var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
         RecoveredUtilsFB var4 = this.b();
         var2 = var2 * 59 + (var4 == null ? 43 : var4.hashCode());
         Set var5 = this.c();
         return var2 * 59 + (var5 == null ? 43 : var5.hashCode());
      }

      @Override
      public String toString() {
         return "ItemTracker.TargetInfo(player=" + this.a() + ", position=" + this.b() + ", description=" + this.c() + ")";
      }
   }
}
