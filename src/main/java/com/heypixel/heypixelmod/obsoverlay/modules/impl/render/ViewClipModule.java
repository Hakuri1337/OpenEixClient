package com.heypixel.heypixelmod.obsoverlay.modules.impl.render;

import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.impl.EventRender2D;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAg;
import net.minecraft.client.CameraType;

@ModuleInfo(
   a = "ViewClip",
   b = "视角穿墙",
   c = "Allows you to see through blocks",
   d = ModuleCategory.RENDER
)
public class ViewClipModule extends ClientModule {
   public RecoveredDAC c = RecoveredDD.a(this, "Scale").b(0.5F).c(2.0F).a(1.0F).d(0.01F).a().c();
   public RecoveredDAA d = RecoveredDD.a(this, "Animation").a(true).a().b();
   public RecoveredDAC e = RecoveredDD.a(this, "Animation Speed").b(0.01F).c(0.5F).a(0.3F).d(0.01F).a(() -> this.d.m()).a().c();
   public RecoveredUtilsAg f = new RecoveredUtilsAg(100.0F);
   CameraType g;

   @EventTarget
   public void onRender(EventRender2D var1) {
      if (this.g != a.options.getCameraType()) {
         this.g = a.options.getCameraType();
         if (this.g == CameraType.FIRST_PERSON || this.g == CameraType.THIRD_PERSON_BACK) {
            this.f.c = 0.0F;
         }
      }

      this.f.b = this.e.q();
      this.f.a(true);
   }
}
