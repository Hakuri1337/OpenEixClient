package com.heypixel.heypixelmod.obsoverlay.modules.impl.render;

import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;

@ModuleInfo(
   a = "FullBright",
   b = "光明",
   c = "Make your world brighter.",
   d = ModuleCategory.RENDER
)
public class FullBrightModule extends ClientModule {
   public RecoveredDAC c = RecoveredDD.a(this, "Brightness").a(1.0F).d(0.1F).b(0.0F).c(1.0F).a().c();
}
