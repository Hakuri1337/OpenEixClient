package com.heypixel.heypixelmod.obsoverlay.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;

public class RecoveredUtilsK {
   private final float d;
   private final float e;
   private final float f;
   private final Minecraft g = Minecraft.getInstance();
   public double a;
   public double b;
   public double c;
   private double h;
   private double i;
   private double j;
   private float k;

   public RecoveredUtilsK(double var1, double var3, double var5, double var7, double var9, double var11, float var13, float var14, float var15) {
      this.a = var1;
      this.b = var3;
      this.c = var5;
      this.h = var7;
      this.i = var9;
      this.j = var11;
      this.d = var13;
      this.e = var14;
      this.f = var15;
   }

   public RecoveredUtilsK(Player var1) {
      this(
         var1.getX(),
         var1.getY(),
         var1.getZ(),
         var1.getDeltaMovement().x,
         var1.getDeltaMovement().y,
         var1.getDeltaMovement().z,
         var1.getYRot(),
         var1.xxa,
         var1.zza
      );
      float var2 = var1.level().getBlockState(var1.blockPosition()).getBlock().getJumpFactor();
      float var3 = var1.level().getBlockState(var1.getOnPos()).getBlock().getJumpFactor();
      float var4 = 0.42F * ((double)var2 == 1.0 ? var3 : var2) + var1.getJumpBoostPower();
      this.k = var4;
   }

   private void a() {
      float var1 = this.e;
      float var2 = this.f;
      float var3 = var1 * var1 + var2 * var2;
      if (var3 >= 1.0E-4F) {
         var3 = Mth.sqrt(var3);
         if (var3 < 1.0F) {
            var3 = 1.0F;
         }

         float var4 = this.k;
         if (this.g.player.isSprinting()) {
            var4 *= 1.3F;
         }

         var3 = var4 / var3;
         var1 *= var3;
         var2 *= var3;
         float var5 = Mth.sin(this.d * (float) Math.PI / 180.0F);
         float var6 = Mth.cos(this.d * (float) Math.PI / 180.0F);
         this.h += (double)(var1 * var6 - var2 * var5);
         this.j += (double)(var2 * var6 + var1 * var5);
      }

      this.i -= 0.08;
      this.i *= 0.98F;
      this.a = this.a + this.h;
      this.b = this.b + this.i;
      this.c = this.c + this.j;
   }

   private void b() {
      float var1 = this.e * 0.98F;
      float var2 = this.f * 0.98F;
      float var3 = var1 * var1 + var2 * var2;
      if (var3 >= 1.0E-4F) {
         var3 = Mth.sqrt(var3);
         if (var3 < 1.0F) {
            var3 = 1.0F;
         }

         float var4 = this.k;
         if (this.g.player.isSprinting()) {
            var4 *= 1.3F;
         }

         var3 = var4 / var3;
         var1 *= var3;
         var2 *= var3;
         float var5 = Mth.sin(this.d * (float) Math.PI / 180.0F);
         float var6 = Mth.cos(this.d * (float) Math.PI / 180.0F);
         this.h += (double)(var1 * var6 - var2 * var5);
         this.j += (double)(var2 * var6 + var1 * var5);
      }

      this.i -= 0.08;
      this.i *= 0.98F;
      this.a = this.a + this.h;
      this.b = this.b + this.i;
      this.c = this.c + this.j;
      this.h *= 0.91;
      this.j *= 0.91;
   }

   public void a(int var1) {
      for (int var2 = 0; var2 < var1; var2++) {
         this.a();
      }
   }

   public void b(int var1) {
      for (int var2 = 0; var2 < var1; var2++) {
         this.b();
      }
   }
}
