package com.heypixel.heypixelmod.obsoverlay.utils.a;

import com.heypixel.heypixelmod.obsoverlay.utils.c.RecoveredUtilsCA;
import com.heypixel.heypixelmod.obsoverlay.utils.c.RecoveredUtilsCB;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public final class RecoveredUtilsAB {
   private static final Minecraft d = Minecraft.getInstance();
   public static final double a = 3.0;
   public static final double b = 4.5;
   public static final double c = 0.06;

   private RecoveredUtilsAB() {
   }

   public static double a(Entity var0) {
      return d.player != null && var0 != null ? a(d.player.getEyePosition(), var0.getBoundingBox()) : Double.MAX_VALUE;
   }

   public static double a(Vec3 var0, AABB var1) {
      double var2 = Mth.clamp(var0.x, var1.minX, var1.maxX);
      double var4 = Mth.clamp(var0.y, var1.minY, var1.maxY);
      double var6 = Mth.clamp(var0.z, var1.minZ, var1.maxZ);
      return Math.sqrt(var0.distanceToSqr(var2, var4, var6));
   }

   public static double a(Vec3 var0, Vec3 var1) {
      return var0.distanceTo(var1);
   }

   public static boolean a(Entity var0, RecoveredUtilsCB var1, double var2) {
      if (d.player != null && d.level != null && var0 != null && var1 != null) {
         double var4 = var2 <= 0.0 ? 3.0 : var2;
         if (a(var0) > var4 - 0.06) {
            return false;
         } else {
            if (RecoveredUtilsCA.a(var1, var4, 0.0F) instanceof EntityHitResult var7 && var7.getEntity() == var0) {
               return true;
            }

            return false;
         }
      } else {
         return false;
      }
   }

   public static boolean a(RecoveredUtilsCB var0, BlockPos var1, Direction var2) {
      if (d.player == null || d.level == null || var1 == null || var0 == null) {
         return false;
      } else if (!RecoveredUtilsCA.a(var0, var2, var1, true)) {
         return false;
      } else {
         Vec3 var3 = d.player.getEyePosition();
         Vec3 var4 = new Vec3((double)var1.getX() + 0.5, (double)var1.getY() + 0.5, (double)var1.getZ() + 0.5);
         Vec3 var5 = new Vec3((double)var2.getStepX() * 0.5, (double)var2.getStepY() * 0.5, (double)var2.getStepZ() * 0.5);
         double var6 = a(var3, var4.add(var5));
         return var6 <= 4.44;
      }
   }

   public static boolean a(BlockPos var0, Direction var1) {
      if (d.player == null) {
         return false;
      } else {
         Vec3 var2 = d.player.getEyePosition();
         Vec3 var3 = new Vec3((double)var0.getX() + 0.5, (double)var0.getY() + 0.5, (double)var0.getZ() + 0.5);
         Vec3 var4 = new Vec3((double)var1.getStepX() * 0.5, (double)var1.getStepY() * 0.5, (double)var1.getStepZ() * 0.5);
         return a(var2, var3.add(var4)) <= 4.44;
      }
   }
}
