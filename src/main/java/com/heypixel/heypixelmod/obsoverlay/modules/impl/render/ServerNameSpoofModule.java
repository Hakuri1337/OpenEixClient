package com.heypixel.heypixelmod.obsoverlay.modules.impl.render;

import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplR;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplT;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

@ModuleInfo(
   a = "ServerNameSpoof",
   b = "服务器名字伪装",
   c = "Spoof the server name",
   d = ModuleCategory.RENDER
)
public class ServerNameSpoofModule extends ClientModule {
   @EventTarget
   public void onRenderScoreboard(RecoveredEventsImplR var1) {
      String var2 = var1.a().getString();
      if (var2.contains("布吉岛")) {
         MutableComponent var3 = Component.literal("§e2b2t.gg");
         var3.setStyle(var1.a().getStyle());
         var1.a(var3);
      }
   }

   @EventTarget
   public void onRenderTab(RecoveredEventsImplT var1) {
      String var2 = var1.b().getString();
      if (var2.contains("布吉岛")) {
         if (var1.a() == RecoveredEventsApiAA.HEADER) {
            var1.a(Component.literal("§e2b2t.gg"));
         } else if (var1.a() == RecoveredEventsApiAA.FOOTER) {
            var1.a(Component.literal(""));
         }
      }
   }
}
