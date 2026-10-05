package com.heypixel.heypixelmod.obsoverlay.modules.impl.c;

import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.mojang.blaze3d.platform.InputConstants;

@ModuleInfo(
   a = "SafeWalk",
   b = "安全行走",
   c = "Prevents you from falling off blocks",
   d = ModuleCategory.MOVEMENT
)
public class SafeWalkModule extends ClientModule {
   public static boolean a(float var0) {
      return !a.level
         .getCollisions(a.player, a.player.getBoundingBox().move(0.0, -0.5, 0.0).inflate((double)(-var0), 0.0, (double)(-var0)))
         .iterator()
         .hasNext();
   }

   @EventTarget
   public void onMotion(RecoveredEventsImplK var1) {
      if (var1.b() == RecoveredEventsApiAA.PRE) {
         a.options.keyShift.setDown(a.player.onGround() && a(0.3F));
      }
   }

   @Override
   public void e() {
      boolean var1 = InputConstants.isKeyDown(a.getWindow().getWindow(), a.options.keyShift.getKey().getValue());
      a.options.keyShift.setDown(var1);
   }
}
