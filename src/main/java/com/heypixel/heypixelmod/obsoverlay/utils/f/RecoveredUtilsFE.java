package com.heypixel.heypixelmod.obsoverlay.utils.f;

import lombok.Generated;

public class RecoveredUtilsFE {
   public int a;
   public int b;
   public int c;

   public RecoveredUtilsFE(int var1, int var2, int var3) {
      this.a = var1;
      this.b = var2;
      this.c = var3;
   }

   public RecoveredUtilsFE a(int var1, int var2, int var3) {
      return new RecoveredUtilsFE(this.a + var1, this.b + var2, this.c + var3);
   }

   public RecoveredUtilsFE a(RecoveredUtilsFE var1) {
      return this.a(var1.a, var1.b, var1.c);
   }

   public RecoveredUtilsFE b(int var1, int var2, int var3) {
      return this.a(-var1, -var2, -var3);
   }

   public RecoveredUtilsFE b(RecoveredUtilsFE var1) {
      return this.a(-var1.a, -var1.b, -var1.c);
   }

   public double a() {
      return Math.sqrt((double)(this.a * this.a + this.b * this.b + this.c * this.c));
   }

   public int b() {
      return this.a;
   }

   public int c() {
      return this.b;
   }

   public int d() {
      return this.c;
   }

   public RecoveredUtilsFD a(double var1) {
      return new RecoveredUtilsFD((double)this.a * var1, (double)this.b * var1, (double)this.c * var1);
   }

   public double c(RecoveredUtilsFE var1) {
      return Math.sqrt(Math.pow((double)(var1.a - this.a), 2.0) + Math.pow((double)(var1.b - this.b), 2.0) + Math.pow((double)(var1.c - this.c), 2.0));
   }

   @Override
   public boolean equals(Object var1) {
      return !(var1 instanceof RecoveredUtilsFD var2)
         ? false
         : (double)this.a == Math.floor(var2.a) && (double)this.b == Math.floor(var2.b) && (double)this.c == Math.floor(var2.c);
   }

   @Generated
   public void a(int var1) {
      this.a = var1;
   }

   @Generated
   public void b(int var1) {
      this.b = var1;
   }

   @Generated
   public void c(int var1) {
      this.c = var1;
   }
}
