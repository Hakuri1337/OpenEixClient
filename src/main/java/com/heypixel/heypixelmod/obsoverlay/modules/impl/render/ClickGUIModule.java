package com.heypixel.heypixelmod.obsoverlay.modules.impl.render;

import com.heypixel.heypixelmod.obsoverlay.c.RecoveredCA;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAE;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;

@ModuleInfo(
   a = "ClickGUI",
   b = "点击用户图形界面",
   d = ModuleCategory.RENDER,
   c = "The ClickGUI"
)
public class ClickGUIModule extends ClientModule {
   public RecoveredDAE c = RecoveredDD.a(this, "Language").a("English", "中文").a(var0 -> ClientModule.b = true).a().e();
   public RecoveredDAE d = RecoveredDD.a(this, "Style").a(new String[]{"Naven"}).a(var0 -> ClientModule.b = true).a().e();
   RecoveredCA e = null;

   @Override
   protected void c() {
      super.c();
      this.b(344);
   }

   @Override
   public void d() {
      if (this.e == null) {
         this.e = new RecoveredCA();
      }

      a.setScreen(this.e);
      this.f();
   }

   public String p() {
      return this.c.l();
   }
}
