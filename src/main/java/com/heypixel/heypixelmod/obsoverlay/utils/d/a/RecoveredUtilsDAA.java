package com.heypixel.heypixelmod.obsoverlay.utils.d.a;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.PostProcessModule;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAm;
import com.heypixel.heypixelmod.obsoverlay.utils.d.RecoveredUtilsDA;
import com.heypixel.heypixelmod.obsoverlay.utils.d.RecoveredUtilsDB;
import com.heypixel.heypixelmod.obsoverlay.utils.d.RecoveredUtilsDC;
import com.heypixel.heypixelmod.obsoverlay.utils.d.RecoveredUtilsDD;
import it.unimi.dsi.fastutil.ints.IntDoubleImmutablePair;
import net.minecraft.client.Minecraft;

public class RecoveredUtilsDAA {
   public static final RecoveredUtilsDAA a = new RecoveredUtilsDAA();
   public static final RecoveredUtilsDAA b = new RecoveredUtilsDAA();
   private static final IntDoubleImmutablePair[] c = new IntDoubleImmutablePair[]{
      IntDoubleImmutablePair.of(1, 1.25),
      IntDoubleImmutablePair.of(1, 2.25),
      IntDoubleImmutablePair.of(2, 2.0),
      IntDoubleImmutablePair.of(2, 3.0),
      IntDoubleImmutablePair.of(2, 4.25),
      IntDoubleImmutablePair.of(3, 2.5),
      IntDoubleImmutablePair.of(3, 3.25),
      IntDoubleImmutablePair.of(3, 4.25),
      IntDoubleImmutablePair.of(3, 5.5),
      IntDoubleImmutablePair.of(4, 3.25),
      IntDoubleImmutablePair.of(4, 4.0),
      IntDoubleImmutablePair.of(4, 5.0),
      IntDoubleImmutablePair.of(4, 6.0),
      IntDoubleImmutablePair.of(4, 7.25),
      IntDoubleImmutablePair.of(4, 8.25),
      IntDoubleImmutablePair.of(5, 4.5),
      IntDoubleImmutablePair.of(5, 5.25),
      IntDoubleImmutablePair.of(5, 6.25),
      IntDoubleImmutablePair.of(5, 7.25),
      IntDoubleImmutablePair.of(5, 8.5)
   };
   private static RecoveredUtilsDC d;
   private static RecoveredUtilsDC e;
   private static RecoveredUtilsDC f;
   private final RecoveredUtilsDA[] g = new RecoveredUtilsDA[6];
   private final RecoveredUtilsAm h = new RecoveredUtilsAm();
   private boolean i = true;

   public void a() {
      for (int var1 = 0; var1 < this.g.length; var1++) {
         if (this.g[var1] != null) {
            this.g[var1].d();
         } else {
            this.g[var1] = new RecoveredUtilsDA(1.0 / Math.pow(2.0, (double)var1));
         }
      }
   }

   public void a(int var1) {
      if (d == null) {
         d = new RecoveredUtilsDC("blur.vert", "blur_down.frag");
         e = new RecoveredUtilsDC("blur.vert", "blur_up.frag");
         f = new RecoveredUtilsDC("passthrough.vert", "passthrough.frag");
      }

      if (this.i) {
         for (int var2 = 0; var2 < this.g.length; var2++) {
            if (this.g[var2] == null) {
               this.g[var2] = new RecoveredUtilsDA(1.0 / Math.pow(2.0, (double)var2));
            }
         }

         this.i = false;
      }

      if (EixClient.a().g().a(PostProcessModule.class).r()) {
         if (!this.h.a((long)(1000 / EixClient.a().g().a(PostProcessModule.class).q()))) {
            return;
         }

         this.h.a();
      }

      IntDoubleImmutablePair var7 = c[var1 - 1];
      int var3 = var7.leftInt();
      double var4 = var7.rightDouble();
      RecoveredUtilsDB.a();
      this.a(this.g[0], Minecraft.getInstance().getMainRenderTarget().getColorTextureId(), d, var4);

      for (int var6 = 0; var6 < var3; var6++) {
         this.a(this.g[var6 + 1], this.g[var6].a, d, var4);
      }

      for (int var8 = var3; var8 >= 1; var8--) {
         this.a(this.g[var8 - 1], this.g[var8].a, e, var4);
      }

      Minecraft.getInstance().getMainRenderTarget().bindWrite(true);
      f.a();
      RecoveredUtilsDD.e(this.g[0].a);
      f.a("uTexture", 0);
      RecoveredUtilsDB.c();
   }

   public int b() {
      return this.g[0].a;
   }

   private void a(RecoveredUtilsDA var1, int var2, RecoveredUtilsDC var3, double var4) {
      var1.a();
      var1.b();
      var3.a();
      RecoveredUtilsDD.e(var2);
      var3.a("uTexture", 0);
      var3.a("uHalfTexelSize", 0.5 / (double)var1.c, 0.5 / (double)var1.d);
      var3.a("uOffset", var4);
      RecoveredUtilsDB.b();
   }
}
