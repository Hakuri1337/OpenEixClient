package com.heypixel.heypixelmod.obsoverlay.modules.impl.c;

import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplAc;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplL;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;

@ModuleInfo(
   a = "FastWeb",
   b = "快速蜘蛛网",
   d = ModuleCategory.MOVEMENT,
   c = "Allows you to walk faster on cobwebs"
)
public class FastWebModule extends ClientModule {
   public final RecoveredDAC c = RecoveredDD.a(this, "Ground Multiplier").a(0.66F).b(0.0F).c(1.0F).d(0.001F).a().c();
   private int d = -1;
   private int e = -100;
   private int f = -1;
   private float g = 0.0F;
   private float h = 0.0F;
   private boolean i = false;
   private boolean j = false;

   @EventTarget
   public void onMoveInput(RecoveredEventsImplL var1) {
      if (a.player != null) {
         int var2 = a.player.tickCount;
         if (var2 == this.d) {
            this.f = var2;
            this.g = var1.a();
            this.h = var1.b();
            this.i = var1.c();
            this.j = var1.d();
            if (a.player.onGround()) {
               if (var1.d()) {
                  this.e = a.player.tickCount;
               }

               if (var1.d() || a.player.tickCount - this.e <= 1) {
                  var1.a(false);
               }
            }
         }
      }
   }

   @EventTarget
   public void onMotion(RecoveredEventsImplK var1) {
      if (a.player != null) {
         if (var1.b() == RecoveredEventsApiAA.PRE) {
            int var2 = a.player.tickCount;
            if (var2 == this.d) {
               Vec3 var3 = a.player.getDeltaMovement();
               boolean var4 = a.player.onGround();
               float var5 = 0.0F;
               float var6 = 0.0F;
               boolean var7 = false;
               boolean var8 = false;
               if (a.player.input != null) {
                  var5 = a.player.input.forwardImpulse;
                  var6 = a.player.input.leftImpulse;
                  var7 = a.player.input.jumping;
                  var8 = a.player.input.shiftKeyDown;
               } else if (this.f == var2) {
                  var5 = this.g;
                  var6 = this.h;
                  var7 = this.i;
                  var8 = this.j;
               }

               if (var5 != 0.0F && var6 != 0.0F) {
                  var5 *= 0.707F;
                  var6 *= 0.707F;
               }

               double var9;
               double var11;
               if (var4) {
                  var9 = var3.x;
                  var11 = var3.z;
               } else {
                  var9 = 0.0;
                  var11 = 0.0;
                  if (var5 != 0.0F || var6 != 0.0F) {
                     double var13 = 0.14122;
                     float var15 = a.player.getYRot();
                     double var16 = Math.toRadians((double)var15);
                     var9 = (-Math.sin(var16) * (double)var5 + Math.cos(var16) * (double)var6) * var13;
                     var11 = (Math.cos(var16) * (double)var5 + Math.sin(var16) * (double)var6) * var13;
                  }
               }

               double var18;
               if (var4) {
                  var18 = var3.y;
               } else if (var7 && var8) {
                  var18 = 0.0;
               } else if (var7) {
                  var18 = 0.06222;
               } else if (var8) {
                  var18 = -0.18777F;
               } else {
                  var18 = 0.0;
               }

               a.player.setDeltaMovement(var9, var18, var11);
               a.player.fallDistance = 0.0F;
            }
         }
      }
   }

   @EventTarget
   public void onStuck(RecoveredEventsImplAc var1) {
      if (a.player != null) {
         if (var1.b().getBlock() == Blocks.COBWEB) {
            this.d = a.player.tickCount;
            Vec3 var2 = var1.c();
            if (a.player.onGround()) {
               double var3 = (double)this.c.q();
               double var5 = var2.x + (1.0 - var2.x) * var3;
               double var7 = var2.z + (1.0 - var2.z) * var3;
               var1.a(new Vec3(var5, var2.y, var7));
            } else {
               var1.a(new Vec3(1.0, 1.0, 1.0));
            }
         }
      }
   }
}
