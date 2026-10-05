package com.heypixel.heypixelmod.obsoverlay.utils.renderer;

import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsS;
import java.awt.Color;
import java.awt.image.BufferedImage;

public class RecoveredUtilsRendererB {
   public static Color a(int var0) {
      return a(var0, 1.0F);
   }

   public static Color a(int var0, float var1) {
      var1 = Math.min(1.0F, Math.max(0.0F, var1));
      return new Color(var0, var0, var0, (int)(255.0F * var1));
   }

   public static Color a(float[] var0, Color[] var1, float var2) {
      int var3 = 0;
      int[] var4 = new int[2];

      while (var3 < var0.length && var0[var3] <= var2) {
         var3++;
      }

      if (var3 >= var0.length) {
         var3 = var0.length - 1;
      }

      var4[0] = var3 - 1;
      var4[1] = var3;
      float[] var5 = new float[]{var0[var4[0]], var0[var4[1]]};
      Color[] var6 = new Color[]{var1[var4[0]], var1[var4[1]]};
      float var7 = var5[1] - var5[0];
      float var8 = var2 - var5[0];
      float var9 = var8 / var7;
      float var10 = 1.0F - var9;
      float var11 = 1.0F - var10;
      float[] var12 = new float[3];
      float[] var13 = new float[3];
      var6[0].getColorComponents(var12);
      var6[1].getColorComponents(var13);
      float var14 = var12[0] * var10 + var13[0] * var11;
      float var15 = var12[1] * var10 + var13[1] * var11;
      float var16 = var12[2] * var10 + var13[2] * var11;
      if (var14 < 0.0F) {
         var14 = 0.0F;
      } else if (var14 > 255.0F) {
         var14 = 255.0F;
      }

      if (var15 < 0.0F) {
         var15 = 0.0F;
      } else if (var15 > 255.0F) {
         var15 = 255.0F;
      }

      if (var16 < 0.0F) {
         var16 = 0.0F;
      } else if (var16 > 255.0F) {
         var16 = 255.0F;
      }

      return new Color(var14, var15, var16);
   }

   public static Color[] a(Color var0) {
      Color[] var1 = new Color[2];
      float[] var2 = Color.RGBtoHSB(var0.getRed(), var0.getGreen(), var0.getBlue(), null);
      float var3 = 0.083333336F;
      float var4 = var2[0] + var3;
      var1[0] = new Color(Color.HSBtoRGB(var4, var2[1], var2[2]));
      float var5 = var2[0] - var3;
      var1[1] = new Color(Color.HSBtoRGB(var5, var2[1], var2[2]));
      return var1;
   }

   public static Color a() {
      return new Color(Color.HSBtoRGB((float)Math.random(), (float)(0.5 + Math.random() / 2.0), (float)(0.5 + Math.random() / 2.0)));
   }

   public static Color a(float[] var0) {
      float var1;
      float var2;
      float var3;
      if (var0[1] == 0.0F) {
         var3 = 1.0F;
         var2 = 1.0F;
         var1 = 1.0F;
      } else {
         float var4 = (double)var0[2] < 0.5 ? var0[2] * (1.0F + var0[1]) : var0[2] + var0[1] - var0[2] * var0[1];
         float var5 = 2.0F * var0[2] - var4;
         var1 = a(var5, var4, var0[0] + 0.33333334F);
         var2 = a(var5, var4, var0[0]);
         var3 = a(var5, var4, var0[0] - 0.33333334F);
      }

      var1 *= 255.0F;
      var2 *= 255.0F;
      var3 *= 255.0F;
      return new Color((int)var1, (int)var2, (int)var3);
   }

   public static float a(float var0, float var1, float var2) {
      float var3 = var2;
      if (var2 < 0.0F) {
         var3 = var2 + 1.0F;
      }

      if (var3 > 1.0F) {
         var3--;
      }

      if (var3 < 0.16666667F) {
         return var0 + (var1 - var0) * 6.0F * var3;
      } else if (var3 < 0.5F) {
         return var1;
      } else {
         return var3 < 0.6666667F ? var0 + (var1 - var0) * (0.6666667F - var3) * 6.0F : var0;
      }
   }

   public static float[] b(Color var0) {
      float var1 = (float)var0.getRed() / 255.0F;
      float var2 = (float)var0.getGreen() / 255.0F;
      float var3 = (float)var0.getBlue() / 255.0F;
      float var4 = Math.max(Math.max(var1, var2), var3);
      float var5 = Math.min(Math.min(var1, var2), var3);
      float var6 = (var4 + var5) / 2.0F;
      float[] var7 = new float[]{var6, var6, var6};
      if (var4 == var5) {
         var7[0] = var7[1] = 0.0F;
      } else {
         float var8 = var4 - var5;
         var7[1] = (double)var7[2] > 0.5 ? var8 / (2.0F - var4 - var5) : var8 / (var4 + var5);
         if (var4 == var1) {
            var7[0] = (var2 - var3) / var8 + (float)(var2 < var3 ? 6 : 0);
         } else if (var4 == var3) {
            var7[0] = (var3 - var1) / var8 + 2.0F;
         } else if (var4 == var2) {
            var7[0] = (var1 - var2) / var8 + 4.0F;
         }

         var7[0] /= 6.0F;
      }

      return var7;
   }

   public static Color a(Color var0, Color var1, float var2) {
      return new Color(b(var0, var1, 255.0F * var2 / 255.0F));
   }

   public static int b(int var0, float var1) {
      Color var2 = new Color(var0);
      return a(var2, var1).getRGB();
   }

   public static Color a(Color var0, float var1) {
      var1 = Math.min(1.0F, Math.max(0.0F, var1));
      return new Color(var0.getRed(), var0.getGreen(), var0.getBlue(), (int)((float)var0.getAlpha() * var1));
   }

   public static Color b(Color var0, float var1) {
      return new Color(
         Math.max((int)((float)var0.getRed() * var1), 0),
         Math.max((int)((float)var0.getGreen() * var1), 0),
         Math.max((int)((float)var0.getBlue() * var1), 0),
         var0.getAlpha()
      );
   }

   public static Color c(Color var0, float var1) {
      int var2 = var0.getRed();
      int var3 = var0.getGreen();
      int var4 = var0.getBlue();
      int var5 = var0.getAlpha();
      int var6 = (int)(1.0 / (1.0 - (double)var1));
      if (var2 == 0 && var3 == 0 && var4 == 0) {
         return new Color(var6, var6, var6, var5);
      } else {
         if (var2 > 0 && var2 < var6) {
            var2 = var6;
         }

         if (var3 > 0 && var3 < var6) {
            var3 = var6;
         }

         if (var4 > 0 && var4 < var6) {
            var4 = var6;
         }

         return new Color(Math.min((int)((float)var2 / var1), 255), Math.min((int)((float)var3 / var1), 255), Math.min((int)((float)var4 / var1), 255), var5);
      }
   }

   public static Color a(BufferedImage var0, int var1, int var2, int var3) {
      int[] var4 = new int[3];
      int var5 = 0;

      while (var5 < var1) {
         for (int var6 = 0; var6 < var2; var6 += var3) {
            Color var7 = new Color(var0.getRGB(var5, var6));
            var4[0] += var7.getRed();
            var4[1] += var7.getGreen();
            var4[2] += var7.getBlue();
         }

         var5 += var3;
      }

      var5 = var1 * var2 / (var3 * var3);
      return new Color(var4[0] / var5, var4[1] / var5, var4[2] / var5);
   }

   public static Color a(int var0, int var1, float var2, float var3, float var4) {
      int var5 = (int)((System.currentTimeMillis() / (long)var0 + (long)var1) % 360L);
      float var6 = (float)var5 / 360.0F;
      Color var7 = new Color(Color.HSBtoRGB(var6, var2, var3));
      return new Color(var7.getRed(), var7.getGreen(), var7.getBlue(), Math.max(0, Math.min(255, (int)(var4 * 255.0F))));
   }

   public static Color a(int var0, int var1, Color var2, Color var3, boolean var4) {
      int var5 = (int)((System.currentTimeMillis() / (long)var0 + (long)var1) % 360L);
      var5 = (var5 >= 180 ? 360 - var5 : var5) * 2;
      return var4 ? d(var2, var3, (float)var5 / 360.0F) : c(var2, var3, (float)var5 / 360.0F);
   }

   public static int b(Color var0, Color var1, float var2) {
      var2 = Math.min(1.0F, Math.max(0.0F, var2));
      return c(var0, var1, var2).getRGB();
   }

   public static int a(int var0, int var1, float var2) {
      var2 = Math.min(1.0F, Math.max(0.0F, var2));
      Color var3 = new Color(var0);
      Color var4 = new Color(var1);
      return c(var3, var4, var2).getRGB();
   }

   public static Color c(Color var0, Color var1, float var2) {
      var2 = Math.min(1.0F, Math.max(0.0F, var2));
      return new Color(
         RecoveredUtilsS.a(var0.getRed(), var1.getRed(), (double)var2),
         RecoveredUtilsS.a(var0.getGreen(), var1.getGreen(), (double)var2),
         RecoveredUtilsS.a(var0.getBlue(), var1.getBlue(), (double)var2),
         RecoveredUtilsS.a(var0.getAlpha(), var1.getAlpha(), (double)var2)
      );
   }

   public static Color d(Color var0, Color var1, float var2) {
      var2 = Math.min(1.0F, Math.max(0.0F, var2));
      float[] var3 = Color.RGBtoHSB(var0.getRed(), var0.getGreen(), var0.getBlue(), null);
      float[] var4 = Color.RGBtoHSB(var1.getRed(), var1.getGreen(), var1.getBlue(), null);
      Color var5 = Color.getHSBColor(
         RecoveredUtilsS.a(var3[0], var4[0], (double)var2),
         RecoveredUtilsS.a(var3[1], var4[1], (double)var2),
         RecoveredUtilsS.a(var3[2], var4[2], (double)var2)
      );
      return a(var5, (float)RecoveredUtilsS.a(var0.getAlpha(), var1.getAlpha(), (double)var2) / 255.0F);
   }

   public static Color a(int var0, int var1, Color var2, float var3) {
      float[] var4 = Color.RGBtoHSB(var2.getRed(), var2.getGreen(), var2.getBlue(), null);
      int var5 = (int)((System.currentTimeMillis() / (long)var0 + (long)var1) % 360L);
      var5 = (var5 > 180 ? 360 - var5 : var5) + 180;
      Color var6 = new Color(Color.HSBtoRGB(var4[0], var4[1], (float)var5 / 360.0F));
      return new Color(var6.getRed(), var6.getGreen(), var6.getBlue(), Math.max(0, Math.min(255, (int)(var3 * 255.0F))));
   }

   private static float a(int var0, int var1) {
      int var2 = (int)((System.currentTimeMillis() / (long)var1 + (long)var0) % 360L);
      return (float)((var2 > 180 ? 360 - var2 : var2) + 180) / 360.0F;
   }

   public static int[] b(int var0) {
      return new int[]{b(var0, 16), b(var0, 8), b(var0, 0), b(var0, 24)};
   }

   public static int c(int var0) {
      int var1 = b(var0, 0);
      int var2 = b(var0, 8);
      int var3 = b(var0, 16);
      int var4 = b(var0, 24);
      var1 = 255 - var1;
      var2 = 255 - var2;
      var3 = 255 - var3;
      return var1 + (var2 << 8) + (var3 << 16) + (var4 << 24);
   }

   public static Color c(Color var0) {
      return new Color(c(var0.getRGB()));
   }

   private static int b(int var0, int var1) {
      return var0 >> var1 & 0xFF;
   }
}
