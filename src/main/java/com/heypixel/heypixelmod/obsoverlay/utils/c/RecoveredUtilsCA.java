package com.heypixel.heypixelmod.obsoverlay.utils.c;

import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.ClipContext.Block;
import net.minecraft.world.level.ClipContext.Fluid;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.HitResult.Type;

public final class RecoveredUtilsCA {
   private static final Minecraft a = Minecraft.getInstance();

   public static HitResult a(RecoveredUtilsCB var0, double var1) {
      return a(var0, var1, 0.0F);
   }

   public static boolean a(Entity var0) {
      Minecraft var1 = Minecraft.getInstance();
      if (var1.player != null && var0 != null) {
         double var2 = (double)var1.options.renderDistance().get().intValue() * 16.0;
         RecoveredUtilsCB var4 = RecoveredUtilsCD.a(var0);
         if (Math.abs(Mth.wrapDegrees(var1.player.getYRot() - var4.a())) > (float)var1.options.fov().get().intValue()) {
            return false;
         } else {
            double var5 = (double)var1.player.distanceTo(var0);
            if (!(var5 > 100.0) && var0 instanceof Player) {
               AABB var24 = var0.getBoundingBox();
               double var8 = var24.getXsize();
               double var10 = var24.getYsize();
               double var12 = var24.getZsize();
               Vec3 var14 = var0.position();

               for (double var15 = 1.0; var15 >= -1.0; var15 -= 0.5) {
                  for (double var17 = 1.0; var17 >= -1.0; var17--) {
                     for (double var19 = 1.0; var19 >= -1.0; var19--) {
                        Vec3 var21 = var14.add(var8 * var17, var10 * var15, var12 * var19);
                        RecoveredUtilsCB var22 = RecoveredUtilsCD.a(var21);
                        HitResult var23 = a(var22, var2, 0.2F);
                        if (var23 != null && var23.getType() == Type.ENTITY) {
                           return true;
                        }
                     }
                  }
               }

               return false;
            } else {
               HitResult var7 = a(var4, var2, 0.2F);
               return var7 != null && var7.getType() == Type.ENTITY;
            }
         }
      } else {
         return false;
      }
   }

   public static HitResult a(RecoveredUtilsCB var0, double var1, float var3) {
      return a(var0, var1, var3, a.player);
   }

   public static HitResult a(RecoveredUtilsCB var0, double var1, float var3, Entity var4) {
      Minecraft var5 = Minecraft.getInstance();
      if (var5.level != null && var4 != null) {
         float var6 = var5.getFrameTime();
         Vec3 var7 = var4.getEyePosition(var6);
         Vec3 var8 = Vec3.directionFromRotation(var0.b(), var0.a());
         Vec3 var9 = var7.add(var8.scale(var1));
         BlockHitResult var10 = var5.level.clip(new ClipContext(var7, var9, Block.OUTLINE, Fluid.NONE, var4));
         double var11 = var1;
         if (var10.getType() != Type.MISS) {
            var11 = var10.getLocation().distanceTo(var7);
         }

         Vec3 var13 = var7.add(var8.scale(var1));
         Entity var14 = null;
         Vec3 var15 = null;
         double var16 = var11;
         AABB var18 = var4.getBoundingBox().expandTowards(var8.scale(var1)).inflate(1.0);

         for (Entity var21 : var5.level.getEntities(var4, var18, var0x -> !var0x.isSpectator() && var0x.isPickable())) {
            float var22 = var21.getPickRadius() + var3;
            AABB var23 = var21.getBoundingBox().inflate((double)var22);
            Optional<Vec3> var24 = var23.clip(var7, var13);
            if (var23.contains(var7)) {
               if (var16 >= 0.0) {
                  var14 = var21;
                  var15 = var24.orElse(var7);
                  var16 = 0.0;
               }
            } else if (var24.isPresent()) {
               Vec3 var25 = (Vec3)var24.get();
               double var26 = var7.distanceTo(var25);
               if (var26 < var16 || var16 == 0.0) {
                  if (var21.getRootVehicle() != var4.getRootVehicle() || var21.canRiderInteract()) {
                     var14 = var21;
                     var15 = var25;
                     var16 = var26;
                  } else if (var16 == 0.0) {
                     var14 = var21;
                     var15 = var25;
                  }
               }
            }
         }

         return (HitResult)(var14 == null || !(var16 < var11) && var10.getType() != Type.MISS ? var10 : new EntityHitResult(var14, var15));
      } else {
         return null;
      }
   }

   public static boolean a(RecoveredUtilsCB var0, Direction var1, BlockPos var2, boolean var3) {
      Minecraft var4 = Minecraft.getInstance();
      if (var4.player != null && var4.level != null) {
         Vec3 var5 = Vec3.directionFromRotation(var0.b(), var0.a());
         Vec3 var6 = var4.player.getEyePosition(1.0F);
         double var7 = 4.5;
         Vec3 var9 = var6.add(var5.scale(var7));
         BlockHitResult var10 = var4.level.clip(new ClipContext(var6, var9, Block.OUTLINE, Fluid.NONE, var4.player));
         return var10.getType() == Type.MISS ? false : var10.getBlockPos().equals(var2) && (!var3 || var10.getDirection() == var1);
      } else {
         return false;
      }
   }

   public static boolean a(Direction var0, BlockPos var1, boolean var2) {
      return a(RecoveredUtilsCC.j(), Direction.UP, var1, false);
   }

   public static Boolean a(RecoveredUtilsCB var0, BlockPos var1) {
      return a(var0, Direction.UP, var1, false);
   }

   public static Boolean a(RecoveredUtilsCB var0, BlockPos var1, Direction var2) {
      return a(var0, var2, var1, true);
   }
}
