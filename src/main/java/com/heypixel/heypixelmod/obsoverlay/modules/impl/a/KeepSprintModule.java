package com.heypixel.heypixelmod.obsoverlay.modules.impl.a;

import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplB;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;

@ModuleInfo(
   a = "KeepSprint",
   b = "保持疾跑",
   c = "Maintain a sprinting state while attacking.",
   d = ModuleCategory.COMBAT
)
public class KeepSprintModule extends ClientModule {
   @EventTarget
   public void onAttackSlowdown(RecoveredEventsImplB var1) {
      var1.a(true);
   }
}
