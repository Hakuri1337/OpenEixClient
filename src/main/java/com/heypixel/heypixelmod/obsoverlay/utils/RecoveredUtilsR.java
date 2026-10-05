package com.heypixel.heypixelmod.obsoverlay.utils;

import net.minecraft.util.Mth;

public class RecoveredUtilsR {
   public static final float a = (float) Math.PI;
   public static final float b = (float) (Math.PI / 180.0);
   public static final float c = 180.0F / (float)Math.PI;
   public static final float[] d = new float[361];
   public static final float[] e = new float[361];

   public static int a(float var0) {
      return (int)(var0 % 360.0F + 360.0F) % 360;
   }

   static {
      for (int var0 = 0; var0 <= 360; var0++) {
         d[var0] = Mth.cos((float)var0 * (float) (Math.PI / 180.0));
         e[var0] = Mth.sin((float)var0 * (float) (Math.PI / 180.0));
      }
   }
}
