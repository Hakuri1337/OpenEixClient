package com.heypixel.heypixelmod.obsoverlay.utils.a;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public final class RecoveredUtilsAA {
   private static final Random a = new Random(System.nanoTime());
   private static final double b = 0.07;
   private static final double c = 0.13;

   private RecoveredUtilsAA() {
   }

   private static double b() {
      ThreadLocalRandom var0 = ThreadLocalRandom.current();
      return (var0.nextDouble() + var0.nextDouble() + var0.nextDouble()) / 3.0 - 0.5;
   }

   public static long a(double var0) {
      if (var0 <= 0.0) {
         var0 = 1.0;
      }

      double var2 = 1000.0 / var0;
      double var4 = var2 * (1.0 + b() * 0.55);
      double var6 = a.nextDouble();
      if (var6 < 0.07) {
         var4 += var2 * (0.35 + a.nextDouble() * 0.85);
      } else if (var6 > 0.87) {
         var4 -= var2 * (0.15 + a.nextDouble() * 0.2);
      }

      return Math.max(1L, (long)var4);
   }

   public static int a(int var0) {
      if (var0 <= 0) {
         return 0;
      } else {
         double var1 = (double)var0 + b() * 1.6;
         if (a.nextDouble() < 0.1) {
            var1 += 1.0 + a.nextDouble();
         }

         return Math.max(0, (int)Math.round(var1));
      }
   }

   public static boolean b(double var0) {
      return a.nextDouble() < var0;
   }

   public static float a(float var0, float var1) {
      return (float)((double)var0 * (1.0 + b() * (double)var1 * 2.0));
   }

   public static double a(double var0, double var2) {
      return var0 + a.nextDouble() * (var2 - var0);
   }

   public static int a(int var0, int var1) {
      return var1 <= var0 ? var0 : var0 + a.nextInt(var1 - var0 + 1);
   }

   public static Random a() {
      return a;
   }
}
