package com.heypixel.heypixelmod.obsoverlay.utils.renderer;

import net.minecraft.client.Minecraft;

public class RecoveredUtilsRendererE {
   public int a;
   public double b = 1.0;
   public int c;
   public int d;
   private int e;

   public RecoveredUtilsRendererE(double var1) {
      this.b = var1;
      this.e();
   }

   public RecoveredUtilsRendererE() {
      this.e();
   }

   private void e() {
      this.e = RecoveredUtilsRendererF.d();
      this.a();
      this.a = RecoveredUtilsRendererF.c();
      RecoveredUtilsRendererF.q(this.a);
      RecoveredUtilsRendererF.h();
      RecoveredUtilsRendererF.c(3553, 10242, 33071);
      RecoveredUtilsRendererF.c(3553, 10243, 33071);
      RecoveredUtilsRendererF.c(3553, 10241, 9729);
      RecoveredUtilsRendererF.c(3553, 10240, 9729);
      Minecraft var1 = Minecraft.getInstance();
      this.c = (int)((double)var1.getWindow().getWidth() * this.b);
      this.d = (int)((double)var1.getWindow().getHeight() * this.b);
      RecoveredUtilsRendererF.a(3553, 0, 6408, this.c, this.d, 0, 6408, 5121, null);
      RecoveredUtilsRendererF.a(36160, 36064, 3553, this.a, 0);
      this.c();
   }

   public void a() {
      RecoveredUtilsRendererF.j(this.e);
   }

   public void b() {
      RecoveredUtilsRendererF.a(0, 0, this.c, this.d);
   }

   public void c() {
      Minecraft.getInstance().getMainRenderTarget().bindWrite(false);
   }

   public void d() {
      RecoveredUtilsRendererF.e(this.e);
      RecoveredUtilsRendererF.d(this.a);
      this.e();
   }
}
