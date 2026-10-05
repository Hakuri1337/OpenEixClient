package com.heypixel.heypixelmod.obsoverlay.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class RecoveredUtilsE {
   private static final Minecraft a = Minecraft.getInstance();

   public static AABB a(BlockPos var0) {
      return e(var0).bounds().move(var0);
   }

   private static VoxelShape e(BlockPos var0) {
      return b(var0).getShape(a.level, var0);
   }

   public static BlockState b(BlockPos var0) {
      return a.level.getBlockState(var0);
   }

   public static boolean c(BlockPos var0) {
      return e(var0) != Shapes.empty();
   }

   public static boolean d(BlockPos var0) {
      if (a.level != null && a.player != null) {
         Block var1 = a.level.getBlockState(var0).getBlock();
         return var1 instanceof AirBlock;
      } else {
         return false;
      }
   }
}
