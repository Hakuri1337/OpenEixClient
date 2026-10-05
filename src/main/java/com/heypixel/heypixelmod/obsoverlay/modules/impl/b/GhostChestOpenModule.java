package com.heypixel.heypixelmod.obsoverlay.modules.impl.b;

import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.game.ServerboundUseItemOnPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.block.entity.BarrelBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

@ModuleInfo(
   a = "GhostChestOpen",
   b = "隔空开箱",
   c = "Opens containers through walls",
   d = ModuleCategory.MISC
)
public class GhostChestOpenModule extends ClientModule {
   private int c = 0;
   private boolean d = false;
   private boolean e = false;
   private int f = 0;

   @EventTarget
   public void onMotion(RecoveredEventsImplK var1) {
      if (var1.b() == RecoveredEventsApiAA.PRE) {
         if (a.player != null && a.level != null && a.getConnection() != null) {
            if (a.screen != null) {
               this.e = false;
               this.f = 0;
               this.d = true;
            } else if (this.e) {
               this.f++;
               if (this.f > 20) {
                  this.e = false;
                  this.f = 0;
                  this.d = false;
               }
            } else if (!a.options.keyUse.isDown()) {
               this.d = false;
            } else if (!this.d) {
               BlockPos var2 = this.p();
               if (var2 != null) {
                  Vec3 var3 = a.player.getEyePosition(1.0F);
                  Vec3 var4 = Vec3.atCenterOf(var2);
                  Vec3 var5 = var4.subtract(var3).normalize();
                  Direction var6 = Direction.getNearest(var5.x, var5.y, var5.z);
                  BlockHitResult var7 = new BlockHitResult(var4, var6, var2, false);
                  a.getConnection().send(new ServerboundUseItemOnPacket(InteractionHand.MAIN_HAND, var7, this.c++));
                  this.d = true;
                  this.e = true;
                  this.f = 0;
               }
            }
         }
      }
   }

   private BlockPos p() {
      double var1 = 4.5;
      Vec3 var3 = a.player.getEyePosition(1.0F);
      Vec3 var4 = a.player.getViewVector(1.0F);

      for (double var5 = 0.1; var5 <= var1; var5 += 0.1) {
         Vec3 var7 = var3.add(var4.scale(var5));
         BlockPos var8 = BlockPos.containing(var7);
         BlockEntity var9 = a.level.getBlockEntity(var8);
         if ((var9 instanceof ChestBlockEntity || var9 instanceof ShulkerBoxBlockEntity || var9 instanceof BarrelBlockEntity)
            && a.player.distanceToSqr(Vec3.atCenterOf(var8)) <= var1 * var1) {
            return var8;
         }
      }

      return null;
   }
}
