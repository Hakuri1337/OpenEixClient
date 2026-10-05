package com.heypixel.heypixelmod.obsoverlay.modules.impl.c;

import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;

@ModuleInfo(
   a = "Sprint",
   b = "自动疾跑",
   c = "Automatically sprints",
   d = ModuleCategory.MOVEMENT
)
public class SprintModule extends ClientModule {
   @EventTarget(
      a = 0
   )
   public void onMotion(RecoveredEventsImplK var1) {
      if (var1.b() == RecoveredEventsApiAA.PRE) {
         a.options.keySprint.setDown(true);
         a.options.toggleSprint().set(false);
      }
   }

   @Override
   public void e() {
      a.options.keySprint.setDown(false);
   }
}
