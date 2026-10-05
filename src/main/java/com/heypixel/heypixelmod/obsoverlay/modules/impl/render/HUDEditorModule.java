package com.heypixel.heypixelmod.obsoverlay.modules.impl.render;

import com.heypixel.heypixelmod.obsoverlay.c.RecoveredCB;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;

@ModuleInfo(
   a = "HUDEditor",
   b = "HUD编辑器",
   d = ModuleCategory.RENDER,
   c = "HUD editor screen."
)
public class HUDEditorModule extends ClientModule {
   RecoveredCB c = null;

   @Override
   protected void c() {
      super.c();
      this.b(345);
   }

   @Override
   public void d() {
      if (this.c == null) {
         this.c = new RecoveredCB();
      }

      a.setScreen(this.c);
      this.f();
   }
}
