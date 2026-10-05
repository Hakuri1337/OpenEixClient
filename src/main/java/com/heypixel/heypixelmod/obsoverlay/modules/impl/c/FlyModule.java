package com.heypixel.heypixelmod.obsoverlay.modules.impl.c;

import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;

@ModuleInfo(
   a = "Fly",
   b = "飞行",
   c = "Double-tap space to fly like creative mode",
   d = ModuleCategory.MOVEMENT
)
public class FlyModule extends ClientModule {
   private final RecoveredDAC c = RecoveredDD.a(this, "Speed").a(1.0F).b(0.1F).c(5.0F).d(0.1F).a().c();
   private boolean d = false;
   private boolean e = false;
   private long f = 0L;

   @EventTarget
   public void onMotion(RecoveredEventsImplK var1) {
      if (var1.b() == RecoveredEventsApiAA.PRE) {
         if (a.player != null) {
            boolean var2 = a.options.keyJump.isDown();
            if (var2 && !this.e) {
               long var3 = System.currentTimeMillis();
               if (var3 - this.f < 300L) {
                  this.d = !this.d;
                  if (!this.d) {
                     a.player.setDeltaMovement(a.player.getDeltaMovement().x, 0.0, a.player.getDeltaMovement().z);
                  }
               }

               this.f = var3;
            }

            this.e = var2;
            if (this.d) {
               double var16 = (double)this.c.q() * 0.1;
               double var5 = (double)a.player.zza;
               double var7 = (double)a.player.xxa;
               float var9 = a.player.getYRot();
               double var10 = 0.0;
               double var12 = 0.0;
               if (var5 != 0.0 || var7 != 0.0) {
                  double var14 = Math.toRadians((double)var9);
                  var10 = (var7 * Math.cos(var14) - var5 * Math.sin(var14)) * var16;
                  var12 = (var5 * Math.cos(var14) + var7 * Math.sin(var14)) * var16;
               }

               double var17 = 0.0;
               if (a.options.keyJump.isDown()) {
                  var17 += var16;
               }

               if (a.options.keyShift.isDown()) {
                  var17 -= var16;
               }

               a.player.setDeltaMovement(var10, var17, var12);
               a.player.fallDistance = 0.0F;
            }
         }
      }
   }
}
