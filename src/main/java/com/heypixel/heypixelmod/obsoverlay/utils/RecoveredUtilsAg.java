package com.heypixel.heypixelmod.obsoverlay.utils;

public class RecoveredUtilsAg {
   public float a;
   public float b = 0.4F;
   public float c;

   public RecoveredUtilsAg(float var1) {
      this.a = var1;
      this.c = var1;
   }

   public RecoveredUtilsAg(float var1, float var2) {
      this.a = var1;
      this.c = var2;
   }

   public RecoveredUtilsAg(float var1, float var2, float var3) {
      this.a = var1;
      this.b = var3;
      this.c = var2;
   }

   public void a(boolean var1) {
      this.c = RecoveredUtilsA.a(this.c, var1 ? this.a : 0.0F, Math.max(10.0F, Math.abs(this.c - (var1 ? this.a : 0.0F)) * 40.0F) * this.b);
   }

   public boolean b(boolean var1) {
      return var1 ? this.c == this.a : this.c == 0.0F;
   }
}
