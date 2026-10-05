package com.heypixel.heypixelmod.obsoverlay.utils;

import java.util.Objects;
import java.util.stream.Stream;
import net.minecraft.client.Minecraft;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.chunk.LevelChunk;

public class RecoveredUtilsG {
   private static final Minecraft a = Minecraft.getInstance();

   public static Stream<BlockEntity> a() {
      return b().flatMap(var0 -> var0.getBlockEntities().values().stream());
   }

   public static Stream<LevelChunk> b() {
      int var0 = Math.max(2, a.options.getEffectiveRenderDistance()) + 3;
      int var1 = var0 * 2 + 1;
      ChunkPos var2 = a.player.chunkPosition();
      ChunkPos var3 = new ChunkPos(var2.x - var0, var2.z - var0);
      ChunkPos var4 = new ChunkPos(var2.x + var0, var2.z + var0);
      return Stream.<ChunkPos>iterate(var3, var2x -> {
            int var3x = var2x.x;
            int var4x = var2x.z;
            if (++var3x > var4.x) {
               var3x = var3.x;
               var4x++;
            }

            if (var4x > var4.z) {
               throw new IllegalStateException("Stream limit didn't work.");
            } else {
               return new ChunkPos(var3x, var4x);
            }
         })
         .limit((long)var1 * (long)var1)
         .filter(var0x -> a.level.hasChunk(var0x.x, var0x.z))
         .map(var0x -> a.level.getChunk(var0x.x, var0x.z))
         .filter(Objects::nonNull);
   }
}
