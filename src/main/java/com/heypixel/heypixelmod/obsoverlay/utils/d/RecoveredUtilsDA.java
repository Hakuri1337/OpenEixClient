package com.heypixel.heypixelmod.obsoverlay.utils.d;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;

public class RecoveredUtilsDA {
   public int a;
   public double b = 1.0;
   public int c;
   public int d;
   private int e;

   public RecoveredUtilsDA(double var1) {
      this.b = var1;
      this.e();
   }

   public RecoveredUtilsDA() {
      this.e();
   }

   private void e() {
      Window var1 = Minecraft.getInstance().getWindow();
      this.e = GlStateManager.glGenFramebuffers();
      this.a();
      this.a = GlStateManager._genTexture();
      RecoveredUtilsDD.e(this.a);
      RecoveredUtilsDD.a();
      RecoveredUtilsDD.b(3553, 10242, 33071);
      RecoveredUtilsDD.b(3553, 10243, 33071);
      RecoveredUtilsDD.b(3553, 10241, 9729);
      RecoveredUtilsDD.b(3553, 10240, 9729);
      this.c = (int)((double)var1.getWidth() * this.b);
      this.d = (int)((double)var1.getHeight() * this.b);
      RecoveredUtilsDD.a(3553, 0, 6407, this.c, this.d, 0, 6407, 5121, null);
      RecoveredUtilsDD.a(36160, 36064, 3553, this.a, 0);
      this.c();
   }

   public void a() {
      GlStateManager._glBindFramebuffer(36160, this.e);
   }

   public void b() {
      RecoveredUtilsDD.a(0, 0, this.c, this.d);
   }

   public void c() {
      Minecraft.getInstance().getMainRenderTarget().bindWrite(false);
   }

   public void d() {
      GlStateManager._glDeleteFramebuffers(this.e);
      GlStateManager._deleteTexture(this.a);
      this.e();
   }
}
