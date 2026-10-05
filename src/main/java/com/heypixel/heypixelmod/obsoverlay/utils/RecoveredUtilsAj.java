package com.heypixel.heypixelmod.obsoverlay.utils;

public class RecoveredUtilsAj {
   public static boolean a(String var0) {
      if (var0 != null && !var0.isEmpty()) {
         for (char var4 : var0.toCharArray()) {
            if (var4 > 19968) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }
}
