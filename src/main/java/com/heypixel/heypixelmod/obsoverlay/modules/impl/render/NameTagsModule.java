package com.heypixel.heypixelmod.obsoverlay.modules.impl.render;

import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplP;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplS;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.b.TeamsModule;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAa;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsM;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsS;
import com.heypixel.heypixelmod.obsoverlay.utils.e.RecoveredUtilsEA;
import com.heypixel.heypixelmod.obsoverlay.utils.e.b.RecoveredUtilsEBC;
import com.heypixel.heypixelmod.obsoverlay.utils.f.RecoveredUtilsFB;
import io.github.humbleui.skija.Font;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

@ModuleInfo(
   a = "NameTags",
   b = "名称标签",
   d = ModuleCategory.RENDER,
   c = "Renders name tags"
)
public class NameTagsModule extends ClientModule {
   private final Map<Entity, RecoveredUtilsFB> f = new ConcurrentHashMap<>();
   public RecoveredDAC c = RecoveredDD.a(this, "Scale").a(0.3F).d(0.01F).b(0.1F).c(1.0F).a().c();
   public RecoveredDAA d = RecoveredDD.a(this, "Blur").a(true).a().b();
   public RecoveredDAA e = RecoveredDD.a(this, "Bloom").a(true).a().b();
   private static final float g = 10.0F;
   private static final float h = 8.0F;
   private static final float i = 16.0F;
   private static final Color j = new Color(0, 0, 0, 100);
   private static final Color k = new Color(200, 200, 200);
   private static final Color l = new Color(255, 85, 85);
   private final List<String> m = new ArrayList<>();

   @EventTarget
   public void update(RecoveredEventsImplP var1) {
      try {
         this.a(var1.a());
      } catch (Exception var3) {
      }
   }

   @EventTarget
   public void onRenderSkia(RecoveredEventsImplS var1) {
      for (Entry var3 : this.f.entrySet()) {
         if (var3.getKey() != a.player) {
            Object var5 = var3.getKey();
            if (var5 instanceof Player) {
               Player var4 = (Player)var5;
               float var26 = var4.getHealth();
               if (var26 > 20.0F) {
                  var4.setHealth(20.0F);
               }

               RecoveredUtilsFB var6 = (RecoveredUtilsFB)var3.getValue();
               float var7 = 6.0F * this.c.q();
               float var8 = 6.0F * this.c.q();
               float var9 = 4.0F * this.c.q();
               float var10 = 3.0F * this.c.q();
               this.m.clear();
               if (TeamsModule.a(var4)) {
                  this.m.add("§aTeam");
               }

               if (RecoveredUtilsM.a((Entity)var4)) {
                  this.m.add("§aFriend");
               }

               String var11 = String.join(" ", this.m);
               String var12 = var4.getName().getString();
               String var13 = String.valueOf(Math.round(var26));
               if (var4.getAbsorptionAmount() > 0.0F) {
                  var13 = var13 + "+" + Math.round(var4.getAbsorptionAmount());
               }

               float var14 = 14.0F * this.c.q();
               Font var15 = RecoveredUtilsEBC.a(var14);
               Font var16 = RecoveredUtilsEBC.b(var14);
               float var17 = var11.isEmpty() ? 0.0F : RecoveredUtilsEA.a(var11, var15) + var7 * 2.0F;
               float var18 = RecoveredUtilsEA.a(var12, var15) + var7 * 2.0F;
               float var19 = var16.measureTextWidth("\ue87d");
               float var20 = RecoveredUtilsEA.a(var13, var15) + var19 + var7 * 2.0F + var10;
               float var21 = var18 + var20 + (var17 > 0.0F ? var17 + var9 : 0.0F) + var9;
               float var22 = var14 + var7 * 1.5F;
               float var23 = var6.a - var21 / 2.0F;
               float var24 = var6.b - var22 / 2.0F;
               float var25 = var23;
               if (!var11.isEmpty()) {
                  this.a(var23, var24, var17, var22, var8, var7, var10, var11, var15, Color.WHITE, null, null);
                  var25 = var23 + var17 + var9;
               }

               this.a(var25, var24, var18, var22, var8, var7, var10, var12, var15, Color.WHITE, null, null);
               var25 += var18 + var9;
               this.a(var25, var24, var20, var22, var8, var7, var10, var13, var15, k, "\ue87d", var16);
            }
         }
      }
   }

   private float a(double var1) {
      double var3 = Math.max(8.0, Math.min(var1, 16.0));
      return (float)((double)(10.0F * this.c.q()) / var3);
   }

   private void a(
      float var1, float var2, float var3, float var4, float var5, float var6, float var7, String var8, Font var9, Color var10, String var11, Font var12
   ) {
      if (this.e.m()) {
         RecoveredUtilsEA.c(var1, var2, var3, var4, var5);
      }

      if (this.d.m()) {
         RecoveredUtilsEA.a(var1, var2, var3, var4, var5);
      }

      RecoveredUtilsEA.a(var1, var2, var3, var4, var5, j);
      float var13 = var2 + var4 / 2.0F - var9.getMetrics().getCapHeight() / 2.0F;
      float var14 = var1 + var6;
      RecoveredUtilsEA.a(var8, var14, var13, var10, var9);
      if (var11 != null && var12 != null) {
         float var15 = RecoveredUtilsEA.a(var8, var9);
         RecoveredUtilsEA.a(var11, var14 + var15 + var7, var13, l, var12);
      }
   }

   private void a(float var1) {
      this.f.clear();

      for (Entity var3 : a.level.entitiesForRendering()) {
         if (var3 instanceof Player && !var3.getName().getString().startsWith("CIT-")) {
            double var4 = RecoveredUtilsS.b(var3.xo, var3.getX(), (double)var1);
            double var6 = RecoveredUtilsS.b(var3.yo, var3.getY(), (double)var1) + (double)var3.getBbHeight() + 0.5;
            double var8 = RecoveredUtilsS.b(var3.zo, var3.getZ(), (double)var1);
            RecoveredUtilsFB var10 = RecoveredUtilsAa.a(var4, var6, var8, var1);
            var10.b(var10.b() - 2.0F);
            this.f.put(var3, var10);
         }
      }
   }
}
