package com.heypixel.heypixelmod.obsoverlay.utils.f;

import lombok.Generated;

public class RecoveredUtilsFD {
   public double a;
   public double b;
   public double c;

   public RecoveredUtilsFD(double var1, double var3, double var5) {
      this.a = var1;
      this.b = var3;
      this.c = var5;
   }

   public RecoveredUtilsFD a(double var1, double var3, double var5) {
      return new RecoveredUtilsFD(this.a + var1, this.b + var3, this.c + var5);
   }

   public RecoveredUtilsFD a(RecoveredUtilsFD var1) {
      return this.a(var1.a, var1.b, var1.c);
   }

   public RecoveredUtilsFD b(double var1, double var3, double var5) {
      return this.a(-var1, -var3, -var5);
   }

   public RecoveredUtilsFD b(RecoveredUtilsFD var1) {
      return this.a(-var1.a, -var1.b, -var1.c);
   }

   public double a() {
      return Math.sqrt(this.a * this.a + this.b * this.b + this.c * this.c);
   }

   public double b() {
      return this.a;
   }

   public double c() {
      return this.b;
   }

   public double d() {
      return this.c;
   }

   public RecoveredUtilsFD a(double var1) {
      return new RecoveredUtilsFD(this.a * var1, this.b * var1, this.c * var1);
   }

   public double c(RecoveredUtilsFD var1) {
      return Math.sqrt(Math.pow(var1.a - this.a, 2.0) + Math.pow(var1.b - this.b, 2.0) + Math.pow(var1.c - this.c, 2.0));
   }

   @Override
   public boolean equals(Object var1) {
      return !(var1 instanceof RecoveredUtilsFD var2)
         ? false
         : Math.floor(this.a) == Math.floor(var2.a) && Math.floor(this.b) == Math.floor(var2.b) && Math.floor(this.c) == Math.floor(var2.c);
   }

   @Generated
   public void b(double var1) {
      this.a = var1;
   }

   @Generated
   public void c(double var1) {
      this.b = var1;
   }

   @Generated
   public void d(double var1) {
      this.c = var1;
   }
}
