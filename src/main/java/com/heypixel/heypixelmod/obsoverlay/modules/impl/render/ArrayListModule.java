package com.heypixel.heypixelmod.obsoverlay.modules.impl.render;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplS;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleManager;
import com.heypixel.heypixelmod.obsoverlay.utils.e.RecoveredUtilsEA;
import com.heypixel.heypixelmod.obsoverlay.utils.e.b.RecoveredUtilsEBC;
import com.heypixel.heypixelmod.obsoverlay.utils.renderer.RecoveredUtilsRendererB;
import io.github.humbleui.skija.Font;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ModuleInfo(
   a = "ArrayList",
   b = "模块列表",
   c = "显示你打开的模块",
   d = ModuleCategory.RENDER
)
public class ArrayListModule extends ClientModule {
   private static final float h = 5.0F;
   private static final float i = 5.0F;
   public RecoveredDAA c = RecoveredDD.a(this, "Pretty Module Name").a(var0 -> ClientModule.b = true).a(false).a().b();
   public RecoveredDAA d = RecoveredDD.a(this, "Hide Render Modules").a(var0 -> ClientModule.b = true).a(true).a().b();
   public RecoveredDAA e = RecoveredDD.a(this, "Sync Color").a(true).a().b();
   public RecoveredDAC f = RecoveredDD.a(this, "Speed").b(1.0F).c(15.0F).a(4.0F).d(0.5F).a(() -> this.e.m()).a().c();
   public RecoveredDAC g = RecoveredDD.a(this, "ArrayList Size").a(10.0F).d(1.0F).b(1.0F).c(32.0F).a().c();
   private List<ClientModule> j;
   private final Map<ClientModule, String> k = new HashMap<>();
   private final Map<ClientModule, Float> l = new HashMap<>();
   private float m = -1.0F;
   private long n = 0L;
   private static final long o = 200L;

   @EventTarget
   public void onSkia(RecoveredEventsImplS var1) {
      Font var2 = RecoveredUtilsEBC.a(this.g.q());
      float var3 = this.g.q();
      float var4 = var3 * 1.35F;
      float var5 = var3 * 0.18F;
      float var6 = var3 * 0.32F;
      long var7 = System.currentTimeMillis();
      if ((ClientModule.b || this.m != var3) && var7 - this.n > 200L) {
         this.a(var2);
         this.m = var3;
         ClientModule.b = false;
         this.n = var7;
      }

      float var9 = 5.0F;
      RecoveredUtilsEA.a("EixClient GV1.0", 5.0F, var9, new Color(255, 255, 255), var2);
      var9 += var4 + var6;

      for (ClientModule var11 : this.j) {
         if (var11.m()) {
            String var12 = this.k.getOrDefault(var11, var11.h());
            Color var13;
            if (this.e.m()) {
               var13 = RecoveredUtilsRendererB.a((int)this.f.q(), (int)var9, new Color(120, 255, 160), new Color(255, 255, 140), false);
            } else {
               var13 = new Color(140, 255, 180);
            }

            RecoveredUtilsEA.a(var12, 5.0F, var9, var13, var2);
            var9 += var4 + var5;
         }
      }
   }

   private void a(Font var1) {
      ModuleManager var2 = EixClient.a().g();
      this.j = new ArrayList<>();
      this.k.clear();
      this.l.clear();

      for (ClientModule var4 : var2.a()) {
         if (var4.m() && (!this.d.m() || var4.l() != ModuleCategory.RENDER)) {
            String var5 = this.a(var4);
            float var6 = RecoveredUtilsEA.a(var5, var1);
            this.j.add(var4);
            this.k.put(var4, var5);
            this.l.put(var4, var6);
         }
      }

      this.j.sort((var1x, var2x) -> {
         float var3 = this.l.getOrDefault(var1x, 0.0F);
         float var4x = this.l.getOrDefault(var2x, 0.0F);
         return Float.compare(var4x, var3);
      });
   }

   public String a(ClientModule var1) {
      String var2 = this.c.m() ? var1.i() : var1.h();
      return var2 + (var1.k() == null ? "" : " §7" + var1.k());
   }
}
