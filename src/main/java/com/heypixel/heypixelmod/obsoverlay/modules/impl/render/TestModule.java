package com.heypixel.heypixelmod.obsoverlay.modules.impl.render;

import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAB;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplS;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.utils.e.RecoveredUtilsEA;
import java.awt.Color;

@ModuleInfo(
   a = "Test",
   b = "测试",
   c = "",
   d = ModuleCategory.RENDER
)
public class TestModule extends ClientModule {
   public RecoveredDAB c = RecoveredDD.a(this, "Position").e(10.0F).f(10.0F).a().f();

   @EventTarget
   public void onRenderSkia(RecoveredEventsImplS var1) {
      RecoveredUtilsEA.c(this.c.n(), this.c.o(), 50.0F, 50.0F, 5.0F);
      RecoveredUtilsEA.a(this.c.n(), this.c.o(), 50.0F, 50.0F, 5.0F);
      RecoveredUtilsEA.a(this.c.n(), this.c.o(), 50.0F, 50.0F, 5.0F, new Color(0, 0, 0, 90));
      this.c.c(50.0F);
      this.c.d(50.0F);
   }
}
