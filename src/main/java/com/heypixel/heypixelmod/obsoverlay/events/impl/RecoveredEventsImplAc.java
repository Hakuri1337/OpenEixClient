package com.heypixel.heypixelmod.obsoverlay.events.impl;

import com.heypixel.heypixelmod.obsoverlay.events.api.events.a.RecoveredEventsApiEventsAA;
import java.util.Objects;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class RecoveredEventsImplAc extends RecoveredEventsApiEventsAA {
   private BlockState b;
   private Vec3 c;

   public RecoveredEventsImplAc(BlockState var1, Vec3 var2) {
      this.b = var1;
      this.c = var2;
   }

   public BlockState b() {
      return this.b;
   }

   public void a(BlockState var1) {
      this.b = var1;
   }

   public Vec3 c() {
      return this.c;
   }

   public void a(Vec3 var1) {
      this.c = var1;
   }

   @Override
   public String toString() {
      return "EventStuckInBlock(state=" + this.b() + ", stuckSpeedMultiplier=" + this.c() + ")";
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (var1 instanceof RecoveredEventsImplAc var2) {
         if (!var2.a(this)) {
            return false;
         } else {
            BlockState var3 = this.b();
            BlockState var4 = var2.b();
            if (Objects.equals(var3, var4)) {
               Vec3 var5 = this.c();
               Vec3 var6 = var2.c();
               return Objects.equals(var5, var6);
            } else {
               return false;
            }
         }
      } else {
         return false;
      }
   }

   protected boolean a(Object var1) {
      return var1 instanceof RecoveredEventsImplAc;
   }

   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      BlockState var3 = this.b();
      var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
      Vec3 var4 = this.c();
      return var2 * 59 + (var4 == null ? 43 : var4.hashCode());
   }
}
