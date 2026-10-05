package com.heypixel.heypixelmod.obsoverlay.utils;

import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplL;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;

public class RecoveredUtilsU {
   private static final Minecraft a = Minecraft.getInstance();

   public static void a(RecoveredEventsImplL var0, float var1) {
      float var2 = var0.a();
      float var3 = var0.b();
      if (var2 != 0.0F || var3 != 0.0F) {
         if (a.player != null && var1 == var1 && !Float.isInfinite(var1)) {
            float var4 = Mth.wrapDegrees(var1 - a.player.getYRot());
            if (!(Math.abs(var4) < 1.0E-4F)) {
               double var5 = Math.toRadians((double)(-var4));
               double var7 = Math.cos(var5);
               double var9 = Math.sin(var5);
               float var11 = (float)Math.hypot((double)var2, (double)var3);
               float var12 = (float)((double)var2 * var7 - (double)var3 * var9);
               float var13 = (float)((double)var2 * var9 + (double)var3 * var7);
               float var14 = (float)Math.hypot((double)var12, (double)var13);
               if (var14 > 1.0E-6F) {
                  float var15 = var11 / var14;
                  var12 *= var15;
                  var13 *= var15;
               } else {
                  var12 = var2;
                  var13 = var3;
               }

               var0.a(var12);
               var0.b(var13);
            }
         }
      }
   }

   public static boolean a() {
      return a.player.input.leftImpulse != 0.0F
         || a.player.input.forwardImpulse != 0.0F
         || a.options.keyJump.isDown()
         || a.options.keyLeft.isDown()
         || a.options.keyRight.isDown()
         || a.options.keyUp.isDown()
         || a.options.keyDown.isDown();
   }
}
