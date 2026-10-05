package com.heypixel.heypixelmod.obsoverlay.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.phys.Vec3;

public class RecoveredUtilsAc {
   private final int a;
   private final int b;

   public RecoveredUtilsAc(int var1, int var2) {
      this.a = var1;
      this.b = var2;
   }

   public static RecoveredUtilsAc a(BlockPos var0) {
      return new RecoveredUtilsAc(var0.getX() >> 9 << 9, var0.getZ() >> 9 << 9);
   }

   public static RecoveredUtilsAc a(ChunkPos var0) {
      return new RecoveredUtilsAc(var0.x >> 5 << 9, var0.z >> 5 << 9);
   }

   public RecoveredUtilsAc a() {
      return new RecoveredUtilsAc(-this.a, -this.b);
   }

   public Vec3 b() {
      return new Vec3((double)this.a, 0.0, (double)this.b);
   }

   public BlockPos c() {
      return new BlockPos(this.a, 0, this.b);
   }
}
