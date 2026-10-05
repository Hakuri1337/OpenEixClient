package com.heypixel.heypixelmod.obsoverlay.modules.impl.c;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.EventRunTicks;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplAd;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplL;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsF;

@ModuleInfo(
   a = "GrimLowHop",
   b = "严峻的嘻嘻哈哈",
   c = "Movement speed adjustments with Grim modes",
   d = ModuleCategory.MOVEMENT
)
public class GrimLowHopModule extends ClientModule {
   private final RecoveredDAA c = RecoveredDD.a(this, "Logging").a(false).a().b();
   private final RecoveredDAC d = RecoveredDD.a(this, "Start Tick").a(2.0F).b(0.0F).c(10.0F).d(1.0F).a().c();
   private final RecoveredDAC e = RecoveredDD.a(this, "Skip Ticks").a(2.0F).b(1.0F).c(10.0F).d(1.0F).a().c();
   private final RecoveredDAC f = RecoveredDD.a(this, "Ticks").a(3.0F).b(1.0F).c(10.0F).d(1.0F).a().c();
   private int g;
   private boolean h;
   private boolean i;

   private void b(String var1) {
      if (this.c.m()) {
         RecoveredUtilsF.a(var1);
      }
   }

   @Override
   public void d() {
      this.g = 0;
      this.h = false;
   }

   @Override
   public void e() {
      this.g = 0;
      this.h = false;
   }

   @EventTarget
   public void onPreTick(EventRunTicks var1) {
      if (a.player != null && var1.type() == RecoveredEventsApiAA.PRE) {
         if ((float)this.g >= this.d.q() && !this.h) {
            EixClient.d = (int)((float)EixClient.d + this.e.q());
            this.h = true;
            this.i = false;
         }
      }
   }

   @EventTarget
   public void onUpdate(RecoveredEventsImplAd var1) {
      if (a.player.onGround()) {
         this.g = 0;
         this.h = false;
      } else {
         this.g++;
      }

      if (this.h && !this.i) {
         this.i = true;

         for (int var2 = 0; var2 < (int)this.f.q(); var2++) {
            a.player.tick();
         }
      }
   }

   @EventTarget
   public void onMoveInput(RecoveredEventsImplL var1) {
      if (a.player != null && a.level != null) {
         var1.a(true);
      }
   }
}
