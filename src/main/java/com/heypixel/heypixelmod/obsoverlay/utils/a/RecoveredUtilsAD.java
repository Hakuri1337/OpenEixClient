package com.heypixel.heypixelmod.obsoverlay.utils.a;

import net.minecraft.client.Minecraft;

public final class RecoveredUtilsAD {
   private static final Minecraft a = Minecraft.getInstance();
   private static int b = -1;

   private RecoveredUtilsAD() {
   }

   public static boolean a() {
      if (a.player != null && a.player.isSprinting()) {
         a.player.setSprinting(false);
         b = RecoveredUtilsAA.a(1, 2);
         return true;
      } else {
         return false;
      }
   }

   public static void b() {
      if (b >= 0) {
         if (--b <= 0) {
            b = -1;
            if (a.player != null) {
               boolean var0 = a.options.keySprint.isDown() || a.options.keyUp.isDown() && !a.player.isShiftKeyDown();
               if (var0 && !a.player.isSprinting()) {
                  a.player.setSprinting(true);
               }
            }
         }
      }
   }

   public static boolean c() {
      return b >= 0;
   }

   public static void d() {
      b = -1;
      if (a.player != null) {
         boolean var0 = a.options.keySprint.isDown() || a.options.keyUp.isDown() && !a.player.isShiftKeyDown();
         if (var0 && !a.player.isSprinting()) {
            a.player.setSprinting(true);
         }
      }
   }

   public static void e() {
      b = -1;
   }
}
