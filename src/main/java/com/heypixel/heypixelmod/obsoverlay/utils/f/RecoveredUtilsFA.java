package com.heypixel.heypixelmod.obsoverlay.utils.f;

import lombok.Generated;

public final class RecoveredUtilsFA {
   public double a;
   public double b;

   public RecoveredUtilsFA() {
   }

   public RecoveredUtilsFA(double var1, double var3) {
      this.a = var1;
      this.b = var3;
   }

   public RecoveredUtilsFA a(double var1, double var3) {
      return new RecoveredUtilsFA(this.a + var1, this.b + var3);
   }

   public RecoveredUtilsFA a(RecoveredUtilsFA var1) {
      return this.a(var1.a, var1.b);
   }

   @Generated
   public double a() {
      return this.a;
   }

   @Generated
   public double b() {
      return this.b;
   }

   @Generated
   public void a(double var1) {
      this.a = var1;
   }

   @Generated
   public void b(double var1) {
      this.b = var1;
   }
}
