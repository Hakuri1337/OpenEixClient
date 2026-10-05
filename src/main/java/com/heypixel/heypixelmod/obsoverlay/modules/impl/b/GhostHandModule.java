package com.heypixel.heypixelmod.obsoverlay.modules.impl.b;

import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplD;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsE;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsG;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

@ModuleInfo(
   a = "GhostHand",
   b = "鬼头",
   c = "Allows interacting with blocks through walls",
   d = ModuleCategory.MISC
)
public class GhostHandModule extends ClientModule {
   private static final Set<Block> c = new HashSet<>();
   private final Minecraft d = Minecraft.getInstance();

   @EventTarget
   public void onClick(RecoveredEventsImplD var1) {
      if (this.d.options.keyUse.isDown() && this.p()) {
         var1.a(true);
      }
   }

   public boolean p() {
      if (this.d.player != null && this.d.level != null) {
         Vec3 var1 = this.d.player.getEyePosition(1.0F);
         Vec3 var2 = this.d.player.getViewVector(1.0F);
         Vec3 var3 = var1.add(var2.scale(4.5));
         ChestBlockEntity var4 = null;
         BlockHitResult var5 = null;
         double var6 = Double.MAX_VALUE;

         for (BlockEntity var10 : (ArrayList<BlockEntity>)RecoveredUtilsG.a().collect(Collectors.toCollection(ArrayList::new))) {
            double var11;
            Optional var13;
            ChestBlockEntity var14;
            AABB var15;
            if (var10 instanceof ChestBlockEntity
               && (var15 = this.a(var14 = (ChestBlockEntity)var10)) != null
               && (var13 = var15.clip(var1, var3)).isPresent()
               && (var11 = ((Vec3)var13.get()).distanceTo(var1)) < var6) {
               var6 = var11;
               var4 = var14;
               var5 = new BlockHitResult((Vec3)var13.get(), Direction.UP, var14.getBlockPos(), false);
            }
         }

         if (var4 != null && var5 != null) {
            this.d.gameMode.useItemOn(this.d.player, InteractionHand.MAIN_HAND, var5);
            this.d.player.swing(InteractionHand.MAIN_HAND);
            return true;
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private AABB a(ChestBlockEntity var1) {
      BlockState var3 = var1.getBlockState();
      if (!var3.hasProperty(ChestBlock.TYPE)) {
         return null;
      } else {
         ChestType var4 = var3.getValue(ChestBlock.TYPE);
         if (var4 == ChestType.LEFT) {
            return null;
         } else {
            BlockPos var5 = var1.getBlockPos();
            AABB var6 = RecoveredUtilsE.a(var5);
            BlockPos var2;
            if (var4 != ChestType.SINGLE && RecoveredUtilsE.c(var2 = var5.relative(ChestBlock.getConnectedDirection(var3)))) {
               AABB var7 = RecoveredUtilsE.a(var2);
               var6 = var6.minmax(var7);
            }

            return var6;
         }
      }
   }

   private void a(BlockPos var1, Direction var2) {
      if (this.d.gameMode != null) {
         this.d.gameMode.useItemOn(this.d.player, InteractionHand.MAIN_HAND, new BlockHitResult(Vec3.atCenterOf(var1), var2, var1, false));
      }

      if (this.d.player != null) {
         this.d.player.swing(InteractionHand.MAIN_HAND);
      }
   }

   static {
      c.add(Blocks.CHEST);
      c.add(Blocks.ENDER_CHEST);
      c.add(Blocks.TRAPPED_CHEST);
      c.add(Blocks.SHULKER_BOX);
   }
}
