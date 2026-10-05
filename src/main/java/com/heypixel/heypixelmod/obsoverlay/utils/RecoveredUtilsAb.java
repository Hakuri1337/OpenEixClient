package com.heypixel.heypixelmod.obsoverlay.utils;

import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.ClipContext.Block;
import net.minecraft.world.level.ClipContext.Fluid;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class RecoveredUtilsAb {
   private static final Minecraft a = Minecraft.getInstance();

   public static Vec3 a(float var0, float var1) {
      float var2 = var0 * (float) (Math.PI / 180.0);
      float var3 = -var1 * (float) (Math.PI / 180.0);
      float var4 = Mth.cos(var3);
      float var5 = Mth.sin(var3);
      float var6 = Mth.cos(var2);
      float var7 = Mth.sin(var2);
      return new Vec3((double)(var5 * var6), (double)(-var7), (double)(var4 * var6));
   }

   public static HitResult a(double var0, boolean var2, float var3, float var4) {
      Vec3 var5 = new Vec3(a.player.getX(), a.player.getY() + 1.62, a.player.getZ());
      Vec3 var6 = a(var4, var3);
      Vec3 var7 = var5.add(var6.x * var0, var6.y * var0, var6.z * var0);
      return a.player.level().clip(new ClipContext(var5, var7, Block.OUTLINE, var2 ? Fluid.ANY : Fluid.NONE, a.player));
   }

   public static HitResult a(Vec3 var0, Vec3 var1, boolean var2, boolean var3, boolean var4, Entity var5) {
      Block var6;
      if (var3) {
         var6 = Block.COLLIDER;
      } else {
         var6 = var4 ? Block.VISUAL : Block.OUTLINE;
      }

      Fluid var7 = var2 ? Fluid.ANY : Fluid.NONE;
      ClipContext var8 = new ClipContext(var0, var1, var6, var7, var5);
      return a.level.clip(var8);
   }

   public static EntityHitResult a(AABB var0, Vec3 var1, Vec3 var2) {
      Optional<Vec3> var3 = var0.clip(var1, var2);
      return var3.<EntityHitResult>map(var0x -> new EntityHitResult(null, var0x)).orElse(null);
   }
}
