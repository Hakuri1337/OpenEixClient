package com.heypixel.heypixelmod.obsoverlay.modules.impl.render;

import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAB;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplS;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.utils.e.RecoveredUtilsEA;
import com.heypixel.heypixelmod.obsoverlay.utils.e.b.RecoveredUtilsEBC;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

@ModuleInfo(
   a = "Scoreboard",
   b = "计分板",
   c = "Modifies the scoreboard",
   d = ModuleCategory.RENDER
)
public class ScoreboardModule extends ClientModule {
   public RecoveredDAA c = RecoveredDD.a(this, "Hide Red Score").a(true).a().b();
   public RecoveredDAA d = RecoveredDD.a(this, "Font").a(false).a().b();
   public RecoveredDAC e = RecoveredDD.a(this, "X Offset").a(0.0F).d(1.0F).b(-300.0F).c(300.0F).a().c();
   public RecoveredDAC f = RecoveredDD.a(this, "Down").a(120.0F).d(1.0F).b(0.0F).c(300.0F).a().c();
   private float h = Float.MAX_VALUE;
   private float i = Float.MAX_VALUE;
   private float j = Float.MIN_VALUE;
   private float k = Float.MIN_VALUE;
   private float l = Float.MAX_VALUE;
   private float m = Float.MAX_VALUE;
   private float n = Float.MIN_VALUE;
   private float o = Float.MIN_VALUE;
   private boolean p = false;
   private boolean q = false;
   private int r = 0;
   private int s = -1;
   private boolean t = false;
   private boolean u = false;
   private float v = 0.0F;
   private float w = 0.0F;
   private boolean x = false;
   private float y = 0.0F;
   private float z = 0.0F;
   private final List<ScoreboardModule.InnerA> A = new ArrayList<>();
   public RecoveredDAB g = RecoveredDD.a(this, "Position").e(0.0F).f(120.0F).a(var1 -> {
      if (!this.t) {
         RecoveredDAB var2 = var1.f();
         if (!this.q) {
            if (this.x) {
               this.t = true;
               this.e.a(var2.n() - this.y);
               this.f.a(var2.o() - this.z);
               this.t = false;
               this.u = false;
            } else {
               this.v = var2.n();
               this.w = var2.o();
               this.u = true;
            }
         } else {
            this.t = true;
            this.e.a(var2.n() - this.l);
            this.f.a(var2.o() - this.m);
            this.t = false;
         }
      }
   }).a().f();

   public void p() {
      this.r++;
      this.h = Float.MAX_VALUE;
      this.i = Float.MAX_VALUE;
      this.j = Float.MIN_VALUE;
      this.k = Float.MIN_VALUE;
      this.l = Float.MAX_VALUE;
      this.m = Float.MAX_VALUE;
      this.n = Float.MIN_VALUE;
      this.o = Float.MIN_VALUE;
      this.p = false;
      this.q = false;
      this.A.clear();
      if (!this.u) {
         this.q();
      }
   }

   private void r() {
      if (this.u && this.q) {
         float var1 = this.e.q();
         float var2 = this.f.q();
         float var3 = this.v - this.l;
         float var4 = this.w - this.m;
         float var5 = var3 - var1;
         float var6 = var4 - var2;
         this.t = true;
         this.e.a(var3);
         this.f.a(var4);
         this.t = false;
         if (this.p) {
            this.h += var5;
            this.j += var5;
            this.i += var6;
            this.k += var6;
         }

         this.u = false;
      }
   }

   public void q() {
      if (!this.t) {
         float var1 = this.e.q();
         float var2 = this.f.q();
         if (this.q) {
            var1 += this.l;
            var2 += this.m;
         } else if (this.x) {
            var1 += this.y;
            var2 += this.z;
         }

         if (this.g.n() != var1 || this.g.o() != var2) {
            this.t = true;
            this.g.a(var1, var2);
            this.t = false;
         }
      }
   }

   public void a(float var1, float var2, int var3, int var4) {
      if (this.m()) {
         float var5 = this.e.q();
         float var6 = this.f.q();
         float var7 = (float)var3 - 2.0F;
         float var8 = (float)var4 - 2.0F;
         float var9 = (float)var3 + var1 + 2.0F;
         float var10 = (float)var4 + var2 + 2.0F;
         float var11 = var7 + var5;
         float var12 = var8 + var6;
         float var13 = var9 + var5;
         float var14 = var10 + var6;
         this.l = Math.min(this.l, var7);
         this.m = Math.min(this.m, var8);
         this.n = Math.max(this.n, var9);
         this.o = Math.max(this.o, var10);
         this.q = true;
         this.x = true;
         this.y = this.l;
         this.z = this.m;
         this.h = Math.min(this.h, var11);
         this.i = Math.min(this.i, var12);
         this.j = Math.max(this.j, var13);
         this.k = Math.max(this.k, var14);
         this.p = true;
         float var15 = this.j - this.h;
         float var16 = this.k - this.i;
         if (var15 > 0.0F && var16 > 0.0F) {
            this.g.c(var15);
            this.g.d(var16);
         }

         if (var15 > 0.0F && var16 > 0.0F) {
            this.t = true;
            this.g.a(this.h, this.i);
            this.t = false;
         }
      }
   }

   public void a(String var1, int var2, int var3, int var4, float var5) {
      if (this.m()) {
         this.A.add(new ScoreboardModule.InnerA(var1, var2, var3, var4, var5));
      }
   }

   public void a(int var1, int var2, int var3, int var4) {
      if (this.m()) {
         float var5 = this.e.q();
         float var6 = this.f.q();
         float var7 = (float)Math.min(var1, var3) + var5;
         float var8 = (float)Math.min(var2, var4) + var6;
         float var9 = (float)Math.max(var1, var3) + var5;
         float var10 = (float)Math.max(var2, var4) + var6;
         float var11 = (float)Math.min(var1, var3);
         float var12 = (float)Math.min(var2, var4);
         float var13 = (float)Math.max(var1, var3);
         float var14 = (float)Math.max(var2, var4);
         this.l = Math.min(this.l, var11);
         this.m = Math.min(this.m, var12);
         this.n = Math.max(this.n, var13);
         this.o = Math.max(this.o, var14);
         this.q = true;
         this.x = true;
         this.y = this.l;
         this.z = this.m;
         this.h = Math.min(this.h, var7);
         this.i = Math.min(this.i, var8);
         this.j = Math.max(this.j, var9);
         this.k = Math.max(this.k, var10);
         this.p = true;
         float var15 = this.j - this.h;
         float var16 = this.k - this.i;
         if (var15 > 0.0F && var16 > 0.0F) {
            this.g.c(var15);
            this.g.d(var16);
         }

         if (var15 > 0.0F && var16 > 0.0F) {
            this.t = true;
            this.g.a(this.h, this.i);
            this.t = false;
         }
      }
   }

   @EventTarget
   public void onRenderSkia(RecoveredEventsImplS var1) {
      if (this.m()) {
         if (this.p && this.s != this.r) {
            this.r();
            float var2 = this.j - this.h;
            float var3 = this.k - this.i;
            if (!(var2 <= 0.0F) && !(var3 <= 0.0F)) {
               RecoveredUtilsEA.c(this.h, this.i, var2, var3, 5.0F);
               RecoveredUtilsEA.a(this.h, this.i, var2, var3, 5.0F);
               RecoveredUtilsEA.a(this.h, this.i, var2, var3, 5.0F, new Color(0, 0, 0, 60));
               if (this.d.m()) {
                  float var4 = this.e.q();
                  float var5 = this.f.q();

                  for (ScoreboardModule.InnerA var7 : this.A) {
                     RecoveredUtilsEA.a(var7.a, (float)var7.b + var4, (float)var7.c + var5, new Color(var7.d, true), RecoveredUtilsEBC.a(var7.e));
                  }
               }

               this.s = this.r;
            }
         }
      }
   }

   private static class InnerA {
      private final String a;
      private final int b;
      private final int c;
      private final int d;
      private final float e;

      private InnerA(String var1, int var2, int var3, int var4, float var5) {
         this.a = var1;
         this.b = var2;
         this.c = var3;
         this.d = var4;
         this.e = var5;
      }
   }
}
