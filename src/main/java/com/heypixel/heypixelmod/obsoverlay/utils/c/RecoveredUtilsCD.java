package com.heypixel.heypixelmod.obsoverlay.utils.c;

import com.heypixel.heypixelmod.mixin.O.accessors.AccessorEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.HitResult.Type;

public class RecoveredUtilsCD {
   private static final Minecraft a = Minecraft.getInstance();

   public static RecoveredUtilsCB a(Vec3 var0, Vec3 var1) {
      Vec3 var2 = var1.subtract(var0);
      double var3 = Math.hypot(var2.x, var2.z);
      float var5 = (float)(Mth.atan2(var2.z, var2.x) * 180.0F / (float)Math.PI) - 90.0F;
      float var6 = (float)(-(Mth.atan2(var2.y, var3) * 180.0F / (float)Math.PI));
      return new RecoveredUtilsCB(var5, var6);
   }

   public static RecoveredUtilsCB a(Entity var0) {
      return a(
         var0.position()
            .add(
               0.0,
               Math.max(
                  0.0,
                  Math.min(a.player.getY() - var0.getY() + (double)a.player.getEyeHeight(), (var0.getBoundingBox().maxY - var0.getBoundingBox().minY) * 0.9)
               ),
               0.0
            )
      );
   }

   public static RecoveredUtilsCB a(Entity var0, boolean var1, double var2) {
      RecoveredUtilsCB var4 = a(var0);
      HitResult var5 = RecoveredUtilsCA.a(var4, var2, 0.0F);
      if (var1 && (var5 == null || var5.getType() != Type.ENTITY)) {
         AABB var6 = var0.getBoundingBox();
         double var7 = var6.minX;
         double var9 = var6.maxX;
         double var11 = var6.minY;
         double var13 = var6.maxY;
         double var15 = var6.minZ;
         double var17 = var6.maxZ;
         Vec3 var19 = var0.position();

         for (double var20 = 1.0; var20 >= 0.0; var20 -= 0.25 + Math.random() * 0.1) {
            for (double var22 = 1.0; var22 >= -0.5; var22 -= 0.5) {
               for (double var24 = 1.0; var24 >= -0.5; var24 -= 0.5) {
                  double var26 = (var9 - var7) * var22;
                  double var28 = (var13 - var11) * var20;
                  double var30 = (var17 - var15) * var24;
                  Vec3 var32 = var19.add(var26, var28, var30);
                  RecoveredUtilsCB var33 = a(var32);
                  HitResult var34 = RecoveredUtilsCA.a(var33, var2, 0.0F);
                  if (var34 != null && var34.getType() == Type.ENTITY) {
                     return var33;
                  }
               }
            }
         }

         return var4;
      } else {
         return var4;
      }
   }

   public static RecoveredUtilsCB a(BlockPos var0) {
      return a(a.player.getEyePosition(), new Vec3((double)var0.getX(), (double)var0.getY(), (double)var0.getZ()).add(0.5, 0.5, 0.5));
   }

   public static RecoveredUtilsCB a(Vec3 var0) {
      return a(a.player.getEyePosition(), var0);
   }

   public static RecoveredUtilsCB a(Vec3 var0, Direction var1) {
      double var2 = var0.x + 0.5;
      double var4 = var0.y + 0.5;
      double var6 = var0.z + 0.5;
      var2 += (double)var1.getNormal().getX() * 0.5;
      var4 += (double)var1.getNormal().getY() * 0.5;
      var6 += (double)var1.getNormal().getZ() * 0.5;
      return a(new Vec3(var2, var4, var6));
   }

   public static RecoveredUtilsCB a(BlockPos var0, Direction var1) {
      double var2 = (double)var0.getX() + 0.5;
      double var4 = (double)var0.getY() + 0.5;
      double var6 = (double)var0.getZ() + 0.5;
      var2 += (double)var1.getNormal().getX() * 0.5;
      var4 += (double)var1.getNormal().getY() * 0.5;
      var6 += (double)var1.getNormal().getZ() * 0.5;
      return a(new Vec3(var2, var4, var6));
   }

   public static RecoveredUtilsCB a(RecoveredUtilsCB var0) {
      AccessorEntity var1 = (AccessorEntity)a.player;
      RecoveredUtilsCB var2 = new RecoveredUtilsCB(var1.getPreviousYRot(), var1.getPreviousXRot());
      float var3 = (float)(a.options.sensitivity().get() * (1.0 + Math.random() / 1.0E7) * 0.6F + 0.2F);
      double var4 = (double)(var3 * var3 * var3 * 8.0F) * 0.15;
      float var6 = var2.a() + (float)((double)Math.round((double)(var0.a() - var2.a()) / var4) * var4);
      float var7 = var2.b() + (float)((double)Math.round((double)(var0.b() - var2.b()) / var4) * var4);
      return new RecoveredUtilsCB(var6, Mth.clamp(var7, -90.0F, 90.0F));
   }

   public static RecoveredUtilsCB a(RecoveredUtilsCB var0, RecoveredUtilsCB var1) {
      float var2 = (float)(a.options.sensitivity().get() * (1.0 + Math.random() / 1.0E7) * 0.6F + 0.2F);
      double var3 = (double)(var2 * var2 * var2 * 8.0F) * 0.15;
      float var5 = var1.a() + (float)((double)Math.round((double)(var0.a() - var1.a()) / var3) * var3);
      float var6 = var1.b() + (float)((double)Math.round((double)(var0.b() - var1.b()) / var3) * var3);
      return new RecoveredUtilsCB(var5, Mth.clamp(var6, -90.0F, 90.0F));
   }

   public static RecoveredUtilsCB b(RecoveredUtilsCB var0) {
      AccessorEntity var1 = (AccessorEntity)a.player;
      RecoveredUtilsCB var2 = new RecoveredUtilsCB(var1.getPreviousYRot(), var1.getPreviousXRot());
      float var3 = var2.a() + Mth.wrapDegrees(var0.a() - var2.a());
      float var4 = Mth.clamp(var0.b(), -90.0F, 90.0F);
      return new RecoveredUtilsCB(var3, var4);
   }

   public static RecoveredUtilsCB c(RecoveredUtilsCB var0) {
      if (var0 == null) {
         return null;
      } else {
         float var1 = var0.a() + Mth.wrapDegrees(a.player.getYRot() - var0.a());
         float var2 = a.player.getXRot();
         return new RecoveredUtilsCB(var1, var2);
      }
   }

   public static RecoveredUtilsCB a(RecoveredUtilsCB var0, double var1) {
      return a(RecoveredUtilsCC.b, var0, var1);
   }

   public static RecoveredUtilsCB a(RecoveredUtilsCB var0, RecoveredUtilsCB var1, double var2) {
      if (var2 != 0.0) {
         double var4 = (double)Mth.wrapDegrees(var1.a() - var0.a());
         double var6 = (double)(var1.b() - var0.b());
         double var8 = Math.sqrt(var4 * var4 + var6 * var6);
         double var10 = Math.abs(var4 / var8);
         double var12 = Math.abs(var6 / var8);
         double var14 = var2 * var10;
         double var16 = var2 * var12;
         float var18 = (float)Math.max(Math.min(var4, var14), -var14);
         float var19 = (float)Math.max(Math.min(var6, var16), -var16);
         return new RecoveredUtilsCB(var18, var19);
      } else {
         return new RecoveredUtilsCB(0.0F, 0.0F);
      }
   }

   public static RecoveredUtilsCB b(RecoveredUtilsCB var0, double var1) {
      return b(RecoveredUtilsCC.b, var0, var1);
   }

   public static RecoveredUtilsCB b(RecoveredUtilsCB var0, RecoveredUtilsCB var1, double var2) {
      float var4 = var1.a();
      float var5 = var1.b();
      float var6 = var0.a();
      float var7 = var0.b();
      if (var2 != 0.0) {
         RecoveredUtilsCB var8 = a(var1, var2);
         var4 = var6 + var8.a();
         var5 = var7 + var8.b();

         for (int var9 = 0; var9 < 2; var9++) {
            if ((double)(Math.abs(var8.a()) + Math.abs(var8.b())) > 1.0E-4) {
               var4 += (float)((Math.random() - 0.5) / 1000.0);
               var5 += (float)((Math.random() - 0.5) / 1000.0);
            }

            RecoveredUtilsCB var10 = new RecoveredUtilsCB(var4, var5);
            RecoveredUtilsCB var11 = a(var10);
            var4 = a(var6, var11.a());
            var5 = Math.max(-90.0F, Math.min(90.0F, var11.b()));
         }
      }

      return new RecoveredUtilsCB(var4, var5);
   }

   public static double b(Entity var0) {
      Vec3 var1 = a.player.getEyePosition();
      AABB var2 = var0.getBoundingBox();
      double var3 = Mth.clamp(var1.x, var2.minX, var2.maxX);
      double var5 = Mth.clamp(var1.y, var2.minY, var2.maxY);
      double var7 = Mth.clamp(var1.z, var2.minZ, var2.maxZ);
      return Math.sqrt(var1.distanceToSqr(var3, var5, var7));
   }

   public static double a(Player var0, Entity var1) {
      Vec3 var2 = var0.getEyePosition();
      AABB var3 = var1.getBoundingBox();
      double var4 = Mth.clamp(var2.x, var3.minX, var3.maxX);
      double var6 = Mth.clamp(var2.y, var3.minY, var3.maxY);
      double var8 = Mth.clamp(var2.z, var3.minZ, var3.maxZ);
      return Math.sqrt(var2.distanceToSqr(var4, var6, var8));
   }

   public static float a(float var0, float var1) {
      return var0 + Mth.wrapDegrees(var1 - var0);
   }
}
