package com.heypixel.heypixelmod.obsoverlay.modules.impl.render;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAB;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplS;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;

@ModuleInfo(
   a = "Notification",
   b = "通知",
   c = "",
   d = ModuleCategory.RENDER
)
public class NotificationModule extends ClientModule {
   public RecoveredDAB c = RecoveredDD.a(this, "Position").e(10.0F).f(50.0F).a().f();

   @EventTarget
   public void onRenderSkia(RecoveredEventsImplS var1) {
      EixClient.a().j().a(var1, this.c);
   }
}
