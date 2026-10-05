package com.heypixel.heypixelmod.obsoverlay.modules.impl.render;

import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;

@ModuleInfo(
   a = "PostProcess",
   b = "后处理",
   c = "Post process effects",
   d = ModuleCategory.RENDER
)
public class PostProcessModule extends ClientModule {
   private static PostProcessModule c;
   private final RecoveredDAA d = RecoveredDD.a(this, "FastBlur").a(false).a().b();
   private final RecoveredDAC e = RecoveredDD.a(this, "Blur FPS").d(1.0F).a(90.0F).b(15.0F).c(120.0F).a(this.d::m).a().c();
   private final RecoveredDAC f = RecoveredDD.a(this, "Blur Strength").a(2.0F).b(0.0F).c(19.0F).d(1.0F).a().c();
   private final RecoveredDAA g = RecoveredDD.a(this, "Glow").a(true).a().b();

   public PostProcessModule() {
      c = this;
   }

   @Override
   public void d() {
      this.a(false);
   }

   public int p() {
      return (int)this.f.q();
   }

   public int q() {
      return (int)this.e.q();
   }

   public boolean r() {
      return this.d.m();
   }

   public static boolean s() {
      return c != null && c.g.m();
   }
}
