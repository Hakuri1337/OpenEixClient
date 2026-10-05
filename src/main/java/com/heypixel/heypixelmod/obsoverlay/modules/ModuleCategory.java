package com.heypixel.heypixelmod.obsoverlay.modules;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.ClickGUIModule;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsL;

public enum ModuleCategory {
   COMBAT("Combat", "战斗", RecoveredUtilsL.b),
   MOVEMENT("Movement", "移动", RecoveredUtilsL.c),
   RENDER("Render", "视觉", RecoveredUtilsL.d),
   MISC("Misc", "杂项", RecoveredUtilsL.e);

   private final String e;
   private final String f;
   private final String g;

   private ModuleCategory(String var3, String var4, String var5) {
      this.e = var3;
      this.f = var4;
      this.g = var5;
   }

   public String a() {
      EixClient var1 = EixClient.a();
      if (var1 != null && var1.g() != null) {
         ClickGUIModule var2 = var1.g().a(ClickGUIModule.class);
         if (var2 != null && var2.c != null) {
            return var2.c.l().equals("English") ? this.e : this.f;
         } else {
            return this.e;
         }
      } else {
         return this.e;
      }
   }

   public String b() {
      return this.g;
   }
}
