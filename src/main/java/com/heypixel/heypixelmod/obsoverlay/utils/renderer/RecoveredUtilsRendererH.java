package com.heypixel.heypixelmod.obsoverlay.utils.renderer;

import com.mojang.blaze3d.vertex.PoseStack;

public class RecoveredUtilsRendererH {
   private static RecoveredUtilsRendererG a;

   public static void a() {
      a = new RecoveredUtilsRendererG(RecoveredUtilsRendererC.Triangles, RecoveredUtilsRendererG.InnerA.Vec2);
      a.a();
      a.a(a.a(-1.0, -1.0).b(), a.a(-1.0, 1.0).b(), a.a(1.0, 1.0).b(), a.a(1.0, -1.0).b());
      a.d();
   }

   public static void a(PoseStack var0) {
      a.a(var0);
   }

   public static void b(PoseStack var0) {
      a.b(var0);
   }

   public static void b() {
      a.e();
   }
}
