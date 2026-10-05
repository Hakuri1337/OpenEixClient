package com.heypixel.heypixelmod.obsoverlay.modules.impl.b;

import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplH;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplU;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAl;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsF;
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket;

@ModuleInfo(
   a = "Helper",
   b = "助手",
   c = "Bed wars Info helper",
   d = ModuleCategory.MISC
)
public class HelperModule extends ClientModule {
   RecoveredUtilsAl c = new RecoveredUtilsAl();
   RecoveredUtilsAl d = new RecoveredUtilsAl();
   private int e;
   private int f = 1;
   private boolean g = false;

   @EventTarget
   public void onMotion(RecoveredEventsImplK var1) {
      if (var1.b() == RecoveredEventsApiAA.PRE) {
         if (this.c.a(30000.0) && this.g) {
            this.e++;
            RecoveredUtilsF.a("第" + this.e + "波钻石刷新");
            this.c.a();
         }

         if (this.d.a(60000.0) && this.g) {
            this.f++;
            RecoveredUtilsF.a("第" + this.f + "波绿宝石刷新");
            this.d.a();
         }
      }
   }

   @EventTarget
   public void onRespawn(RecoveredEventsImplU var1) {
      this.e = 0;
      this.f = 0;
   }

   @EventTarget(
      a = 0
   )
   public void onPacket(RecoveredEventsImplH var1) {
      try {
         if (a.player != null && !var1.a() && var1.b() instanceof ClientboundSystemChatPacket var2) {
            String var5 = var2.content().getString();
            if (var5.contains("游戏准备开始")) {
               this.c.a();
               this.d.a();
               this.g = true;
            }

            if (var5.contains("游戏结束")) {
               this.g = false;
            }
         }
      } catch (Exception var4) {
         var4.printStackTrace();
      }
   }
}
