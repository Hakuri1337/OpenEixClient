package com.heypixel.heypixelmod.obsoverlay.utils;

public class RecoveredUtilsA {
   public static int a;

   public static float a(float var0, float var1, float var2) {
      float var3 = (float)a * (var2 / 1000.0F);
      if (var0 < var1) {
         if (var0 + var3 < var1) {
            var0 += var3;
         } else {
            var0 = var1;
         }
      } else if (var0 - var3 > var1) {
         var0 -= var3;
      } else {
         var0 = var1;
      }

      return var0;
   }
}
