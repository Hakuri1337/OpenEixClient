package com.heypixel.heypixelmod.obsoverlay.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class RecoveredUtilsS {
   public static final double a = Math.PI;
   public static final Random b = new Random();
   static final double c = Math.PI * 2;
   static final float d = (float) Math.PI;
   static final float e = (float) (Math.PI * 2);
   static final double f = Math.PI / 2;
   static final float g = (float) (Math.PI / 2);
   static final double h = Math.PI / 4;
   static final double i = 0.3183098861837907;

   public static float a(float var0) {
      if (var0 > 90.0F) {
         return 90.0F;
      } else {
         return var0 < -90.0F ? -90.0F : var0;
      }
   }

   public static float a(float var0, float var1, float var2, float var3, float var4) {
      return (var0 - var1) / (var2 - var1) * (var4 - var3) + var3;
   }

   public static double a(double var0, double var2, double var4) {
      return var0 < var2 ? var2 : Math.min(var0, var4);
   }

   public static int a(int var0, int var1, int var2) {
      return var0 < var1 ? var1 : Math.min(var0, var2);
   }

   public static <T extends Number> T a(T var0, T var1, T var2) {
      if (var0 instanceof Integer) {
         if (var0.intValue() > var2.intValue()) {
            var0 = var2;
         } else if (var0.intValue() < var1.intValue()) {
            var0 = var1;
         }
      } else if (var0 instanceof Float) {
         if (var0.floatValue() > var2.floatValue()) {
            var0 = var2;
         } else if (var0.floatValue() < var1.floatValue()) {
            var0 = var1;
         }
      } else if (var0 instanceof Double) {
         if (var0.doubleValue() > var2.doubleValue()) {
            var0 = var2;
         } else if (var0.doubleValue() < var1.doubleValue()) {
            var0 = var1;
         }
      } else if (var0 instanceof Long) {
         if (var0.longValue() > var2.longValue()) {
            var0 = var2;
         } else if (var0.longValue() < var1.longValue()) {
            var0 = var1;
         }
      } else if (var0 instanceof Short) {
         if (var0.shortValue() > var2.shortValue()) {
            var0 = var2;
         } else if (var0.shortValue() < var1.shortValue()) {
            var0 = var1;
         }
      } else if (var0 instanceof Byte) {
         if (var0.byteValue() > var2.byteValue()) {
            var0 = var2;
         } else if (var0.byteValue() < var1.byteValue()) {
            var0 = var1;
         }
      }

      return (T)var0;
   }

   public static double a(double var0, double var2) {
      return var0 >= var2 ? var0 : b.nextDouble() * (var2 - var0) + var0;
   }

   public static float a(float var0, float var1) {
      return var0 >= var1 ? var0 : b.nextFloat() * (var1 - var0) + var0;
   }

   public static int a(int var0, int var1) {
      return var1 - var0 <= 0 ? var0 : var0 + new Random().nextInt(var1 - var0);
   }

   public static int b(int var0, int var1) {
      if (var1 <= var0) {
         return var0;
      } else {
         double var2 = (double)(var0 + var1) / 2.0;
         double var4 = (double)(var1 - var0) / 6.0;
         double var6 = var2 + b.nextGaussian() * var4;
         int var8 = (int)Math.round(var6);
         return a(var8, var0, var1);
      }
   }

   public static float b(float var0) {
      float var1 = var0 % 360.0F;
      return var1 < -180.0F ? var1 + 360.0F : (var1 > 180.0F ? var1 - 360.0F : var1);
   }

   public static Double b(double var0, double var2, double var4) {
      return var0 + (var2 - var0) * var4;
   }

   public static float a(float var0, float var1, double var2) {
      return b((double)var0, (double)var1, (double)((float)var2)).floatValue();
   }

   public static int a(int var0, int var1, double var2) {
      return b((double)var0, (double)var1, (double)((float)var2)).intValue();
   }

   public static float a(float var0, float var1, float var2) {
      return var1 + var0 * b(var2 - var1);
   }

   public static double a(double var0, int var2) {
      if (var2 < 0) {
         throw new IllegalArgumentException();
      } else {
         return new BigDecimal(var0).setScale(var2, RoundingMode.HALF_UP).doubleValue();
      }
   }

   public static <T extends Number> int a(T var0) {
      if (!(var0 instanceof Integer) && !(var0 instanceof Long)) {
         String[] var1 = var0.toString().split("\\.");
         if (var1.length == 2) {
            if (var1[1].endsWith("0")) {
               var1[1] = var1[1].substring(0, var1[1].length() - 1);
            }

            return var1[1].length();
         } else {
            return 0;
         }
      } else {
         return 0;
      }
   }

   public static float b(float var0, float var1, float var2) {
      return var0 < var1 ? var1 : Math.min(var0, var2);
   }

   public static int b(int var0, int var1, int var2) {
      return var0 < var1 ? var1 : Math.min(var0, var2);
   }

   public static int a(int var0) {
      int var1 = var0 - 1;
      var1 |= var1 >> 1;
      var1 |= var1 >> 2;
      var1 |= var1 >> 4;
      var1 |= var1 >> 8;
      var1 |= var1 >> 16;
      return var1 + 1;
   }

   public static float b(double var0, double var2) {
      return (float)(Math.log(var2) / Math.log(var0));
   }

   public static double b(double var0, int var2) {
      if (var2 < 0) {
         throw new IllegalArgumentException();
      } else {
         BigDecimal var3 = new BigDecimal(var0);
         var3 = var3.setScale(var2, RoundingMode.HALF_UP);
         return var3.doubleValue();
      }
   }

   public static boolean a(float var0, float var1, float var2, float var3, float var4, float var5) {
      return var0 > var2 && var0 < var4 && var1 > var3 && var1 < var5;
   }

   public static float b(float var0, float var1) {
      double var2 = 3.141592653;
      double var4 = 1.0 / Math.sqrt(2.0 * var2 * (double)(var1 * var1));
      return (float)(var4 * Math.exp((double)(-(var0 * var0)) / (2.0 * (double)(var1 * var1))));
   }

   public static double c(double var0, double var2) {
      double var4 = a(1.0 - var0 * var0);
      double var6 = var2 + (Math.PI / 2);
      double var8 = var6 - (double)((int)(var6 / (Math.PI * 2))) * (Math.PI * 2);
      if (var8 < 0.0) {
         var8 += Math.PI * 2;
      }

      return var8 >= Math.PI ? -var4 : var4;
   }

   public static double a(double var0) {
      return Math.sqrt(var0);
   }
}
