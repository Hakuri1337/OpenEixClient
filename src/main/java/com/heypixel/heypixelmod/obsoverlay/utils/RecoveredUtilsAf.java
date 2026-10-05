package com.heypixel.heypixelmod.obsoverlay.utils;

import java.util.Arrays;
import java.util.Objects;

public class RecoveredUtilsAf {
   public String a;
   public double b;
   public double c;
   public double d;
   public double e;
   public double f;
   public double g;
   public double[] h;
   public String[] i;
   public long j;

   public RecoveredUtilsAf(
      String var1, double var2, double var4, double var6, double var8, double var10, double var12, double[] var14, String[] var15, long var16
   ) {
      this.a = var1;
      this.b = var2;
      this.c = var4;
      this.d = var6;
      this.e = var8;
      this.f = var10;
      this.g = var12;
      this.h = var14;
      this.i = var15;
      this.j = var16;
   }

   public String a() {
      return this.a;
   }

   public void a(String var1) {
      this.a = var1;
   }

   public double b() {
      return this.b;
   }

   public void a(double var1) {
      this.b = var1;
   }

   public double c() {
      return this.c;
   }

   public void b(double var1) {
      this.c = var1;
   }

   public double d() {
      return this.d;
   }

   public void c(double var1) {
      this.d = var1;
   }

   public double e() {
      return this.e;
   }

   public void d(double var1) {
      this.e = var1;
   }

   public double f() {
      return this.f;
   }

   public void e(double var1) {
      this.f = var1;
   }

   public double g() {
      return this.g;
   }

   public void f(double var1) {
      this.g = var1;
   }

   public double[] h() {
      return this.h;
   }

   public void a(double[] var1) {
      this.h = var1;
   }

   public String[] i() {
      return this.i;
   }

   public void a(String[] var1) {
      this.i = var1;
   }

   public long j() {
      return this.j;
   }

   public void a(long var1) {
      this.j = var1;
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (var1 instanceof RecoveredUtilsAf var2) {
         if (!var2.a(this)) {
            return false;
         } else if (Double.compare(this.b(), var2.b()) != 0) {
            return false;
         } else if (Double.compare(this.c(), var2.c()) != 0) {
            return false;
         } else if (Double.compare(this.d(), var2.d()) != 0) {
            return false;
         } else if (Double.compare(this.e(), var2.e()) != 0) {
            return false;
         } else if (Double.compare(this.f(), var2.f()) != 0) {
            return false;
         } else if (Double.compare(this.g(), var2.g()) != 0) {
            return false;
         } else if (this.j() != var2.j()) {
            return false;
         } else {
            String var3 = this.a();
            String var4 = var2.a();
            return !Objects.equals(var3, var4) ? false : Arrays.equals(this.h(), var2.h()) && Arrays.deepEquals(this.i(), var2.i());
         }
      } else {
         return false;
      }
   }

   protected boolean a(Object var1) {
      return var1 instanceof RecoveredUtilsAf;
   }

   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      long var3 = Double.doubleToLongBits(this.b());
      var2 = var2 * 59 + (int)(var3 >>> 32 ^ var3);
      long var5 = Double.doubleToLongBits(this.c());
      var2 = var2 * 59 + (int)(var5 >>> 32 ^ var5);
      long var7 = Double.doubleToLongBits(this.d());
      var2 = var2 * 59 + (int)(var7 >>> 32 ^ var7);
      long var9 = Double.doubleToLongBits(this.e());
      var2 = var2 * 59 + (int)(var9 >>> 32 ^ var9);
      long var11 = Double.doubleToLongBits(this.f());
      var2 = var2 * 59 + (int)(var11 >>> 32 ^ var11);
      long var13 = Double.doubleToLongBits(this.g());
      var2 = var2 * 59 + (int)(var13 >>> 32 ^ var13);
      long var15 = this.j();
      var2 = var2 * 59 + (int)(var15 >>> 32 ^ var15);
      String var17 = this.a();
      var2 = var2 * 59 + (var17 == null ? 43 : var17.hashCode());
      var2 = var2 * 59 + Arrays.hashCode(this.h());
      return var2 * 59 + Arrays.deepHashCode(this.i());
   }

   @Override
   public String toString() {
      return "SharedESPData(displayName="
         + this.a()
         + ", posX="
         + this.b()
         + ", posY="
         + this.c()
         + ", posZ="
         + this.d()
         + ", health="
         + this.e()
         + ", maxHealth="
         + this.f()
         + ", absorption="
         + this.g()
         + ", renderPosition="
         + Arrays.toString(this.h())
         + ", tags="
         + Arrays.deepToString(this.i())
         + ", updateTime="
         + this.j()
         + ")";
   }
}
