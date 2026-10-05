package com.heypixel.heypixelmod.obsoverlay.utils;

import com.heypixel.heypixelmod.obsoverlay.utils.f.RecoveredUtilsFB;
import net.minecraft.client.Minecraft;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.ClipContext.Block;
import net.minecraft.world.level.ClipContext.Fluid;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class RecoveredUtilsY {
   private static final Minecraft a = Minecraft.getInstance();

   public static boolean a() {
      return a.options.keyUp.isDown() || a.options.keyDown.isDown() || a.options.keyLeft.isDown() || a.options.keyRight.isDown();
   }

   public static int b() {
      return a.player.hasEffect(MobEffects.MOVEMENT_SPEED) ? a.player.getEffect(MobEffects.MOVEMENT_SPEED).getAmplifier() + 1 : 0;
   }

   public static Vec3 a(RecoveredUtilsFB var0) {
      float var1 = (float)Math.cos((double)(-var0.a() * (float) (Math.PI / 180.0) - (float) Math.PI));
      float var2 = (float)Math.sin((double)(-var0.a() * (float) (Math.PI / 180.0) - (float) Math.PI));
      float var3 = (float)(-Math.cos((double)(-var0.b() * (float) (Math.PI / 180.0))));
      float var4 = (float)Math.sin((double)(-var0.b() * (float) (Math.PI / 180.0)));
      return new Vec3((double)(var2 * var3), (double)var4, (double)(var1 * var3));
   }

   public static HitResult a(double var0, float var2, float var3) {
      if (a.player != null && a.level != null) {
         Vec3 var4 = a.player.getEyePosition(1.0F);
         Vec3 var5 = a(new RecoveredUtilsFB(var2, var3));
         Vec3 var6 = var4.add(var5.x * var0, var5.y * var0, var5.z * var0);
         return a.level.clip(new ClipContext(var4, var6, Block.OUTLINE, Fluid.NONE, a.player));
      } else {
         return null;
      }
   }
}
