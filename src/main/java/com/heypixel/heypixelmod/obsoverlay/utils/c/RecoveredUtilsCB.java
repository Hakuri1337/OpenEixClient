package com.heypixel.heypixelmod.obsoverlay.utils.c;

import com.heypixel.heypixelmod.obsoverlay.utils.f.RecoveredUtilsFB;

public class RecoveredUtilsCB {
   public float a;
   public float b;

   public RecoveredUtilsCB(float var1, float var2) {
      this.a = var1;
      this.b = var2;
   }

   public RecoveredUtilsCB(RecoveredUtilsFB var1) {
      this.a = var1.a;
      this.b = var1.b;
   }

   public float a() {
      return this.a;
   }

   public void a(float var1) {
      this.a = var1;
   }

   public float b() {
      return this.b;
   }

   public void b(float var1) {
      this.b = var1;
   }
}
