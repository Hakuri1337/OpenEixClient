package com.heypixel.heypixelmod.obsoverlay.modules.impl.render;

import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;

@ModuleInfo(
   a = "NoRender",
   b = "无渲染",
   c = "Disables rendering",
   d = ModuleCategory.RENDER
)
public class NoRenderModule extends ClientModule {
   public RecoveredDAA c = RecoveredDD.a(this, "Disable Effects").a(true).a().b();
}
