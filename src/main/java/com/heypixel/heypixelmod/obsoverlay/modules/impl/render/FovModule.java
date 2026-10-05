package com.heypixel.heypixelmod.obsoverlay.modules.impl.render;

import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplAe;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;

@ModuleInfo(
   a = "Fov",
   b = "视场角",
   c = "Change fov.",
   d = ModuleCategory.RENDER
)
public class FovModule extends ClientModule {
   public RecoveredDAC c = RecoveredDD.a(this, "Fov").a(120.0F).d(1.0F).b(1.0F).c(150.0F).a().c();

   @EventTarget
   public void onFovUpdate(RecoveredEventsImplAe var1) {
      var1.a(this.c.q() / 100.0F);
   }
}
