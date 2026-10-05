package com.heypixel.heypixelmod.obsoverlay.utils;

import net.minecraft.util.Mth;

public final class RecoveredUtilsAl {
   private long a = 0L;
   private long b = -1L;

   public boolean a(long var1) {
      if (this.b() >= var1) {
         this.a();
         return true;
      } else {
         return false;
      }
   }

   public boolean a(float var1) {
      return (float)(System.currentTimeMillis() - this.b) >= var1;
   }

   public boolean a(double var1) {
      return this.a(var1, false);
   }

   public boolean a(double var1, boolean var3) {
      boolean var4 = (double)Mth.clamp((float)(this.c() - this.a), 0.0F, (float)var1) >= var1;
      if (var4 && var3) {
         this.a();
      }

      return var4;
   }

   public void a() {
      this.b = System.currentTimeMillis();
      this.a = this.c();
   }

   public void b(long var1) {
      this.b = System.currentTimeMillis();
      this.a = this.c() + var1;
   }

   public long b() {
      return System.nanoTime() / 1000000L - this.a;
   }

   public long c() {
      return System.nanoTime() / 1000000L;
   }

   public double d() {
      return (double)(this.c() - this.e());
   }

   public long e() {
      return this.a;
   }
}
