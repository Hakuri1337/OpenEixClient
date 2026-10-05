package com.heypixel.heypixelmod.obsoverlay.utils.renderer;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.api.events.Event;
import com.heypixel.heypixelmod.obsoverlay.events.impl.EventRender2D;
import com.heypixel.heypixelmod.obsoverlay.events.impl.EventShader;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAi;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAl;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import it.unimi.dsi.fastutil.ints.IntDoubleImmutablePair;
import net.minecraft.client.Minecraft;

public class RecoveredUtilsRendererA {
   private static final RecoveredUtilsAl a = new RecoveredUtilsAl();
   private static final RecoveredUtilsRendererE[] b = new RecoveredUtilsRendererE[6];
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
   private static RecoveredUtilsRendererI d;
   private static RecoveredUtilsRendererI e;

   public static void a(EventRender2D var0, float var1, int var2) {
      RecoveredUtilsAi.a(false);
      EixClient.a().b().a((Event)(new EventShader(var0.stack(), RecoveredEventsApiAA.BLUR)));
      RecoveredUtilsAi.b(true);
      if (d == null) {
         d = new RecoveredUtilsRendererI("blur.vert", "blur_down.frag");
         e = new RecoveredUtilsRendererI("blur.vert", "blur_up.frag");

         for (int var3 = 0; var3 < b.length; var3++) {
            if (b[var3] == null) {
               b[var3] = new RecoveredUtilsRendererE(1.0 / Math.pow(2.0, (double)var3));
            }
         }
      }

      IntDoubleImmutablePair var8 = c[var2];
      int var4 = var8.leftInt();
      double var5 = var8.rightDouble();
      if (a.a((double)(1000.0F / var1))) {
         RecoveredUtilsRendererH.a(var0.stack());
         a(var0.stack(), b[0], Minecraft.getInstance().getMainRenderTarget().getColorTextureId(), d, var5);

         for (int var7 = 0; var7 < var4; var7++) {
            a(var0.stack(), b[var7 + 1], b[var7].a, d, var5);
         }

         for (int var9 = var4; var9 >= 1; var9--) {
            a(var0.stack(), b[var9 - 1], b[var9].a, e, var5);
         }

         Minecraft.getInstance().getMainRenderTarget().bindWrite(true);
         a.a();
         RecoveredUtilsRendererH.b(var0.stack());
         RecoveredUtilsRendererH.b();
      }

      RenderSystem.bindTexture(b[1].a);
      e.a();
      e.a("uTexture", 0);
      e.a("uHalfTexelSize", 0.5 / (double)b[1].c, 0.5 / (double)b[1].d);
      e.a("uOffset", var5);
      RecoveredUtilsRendererH.b(var0.stack());
      RecoveredUtilsAi.a();
   }

   private static void a(PoseStack var0, RecoveredUtilsRendererE var1, int var2, RecoveredUtilsRendererI var3, double var4) {
      var1.a();
      var1.b();
      var3.a();
      RecoveredUtilsRendererF.q(var2);
      var3.a("uTexture", 0);
      var3.a("uHalfTexelSize", 0.5 / (double)var1.c, 0.5 / (double)var1.d);
      var3.a("uOffset", var4);
      RecoveredUtilsRendererH.b(var0);
   }
}
