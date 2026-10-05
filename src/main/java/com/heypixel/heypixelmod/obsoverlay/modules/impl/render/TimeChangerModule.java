package com.heypixel.heypixelmod.obsoverlay.modules.impl.render;

import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplM;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import net.minecraft.network.protocol.game.ClientboundSetTimePacket;

@ModuleInfo(
   a = "TimeChanger",
   b = "时间更改器",
   c = "Change the time of the world",
   d = ModuleCategory.RENDER
)
public class TimeChangerModule extends ClientModule {
   RecoveredDAC c = RecoveredDD.a(this, "World Time").a(8000.0F).d(1.0F).b(0.0F).c(24000.0F).a().c();

   @EventTarget
   public void onMotion(RecoveredEventsImplK var1) {
      if (var1.b() == RecoveredEventsApiAA.PRE) {
         a.level.setDayTime((long)this.c.q());
      }
   }

   @EventTarget
   public void onPacket(RecoveredEventsImplM var1) {
      if (var1.c() instanceof ClientboundSetTimePacket) {
         var1.a(true);
      }
   }
}
