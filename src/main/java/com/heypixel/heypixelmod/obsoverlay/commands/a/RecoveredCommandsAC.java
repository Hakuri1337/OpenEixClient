package com.heypixel.heypixelmod.obsoverlay.commands.a;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.commands.CommandInfo;
import com.heypixel.heypixelmod.obsoverlay.commands.RecoveredCommandsA;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.LanguageSelectScreen;

@CommandInfo(
   a = "language",
   b = "Open language gui.",
   c = {"lang"}
)
public class RecoveredCommandsAC extends RecoveredCommandsA {
   @Override
   public void a(String[] var1) {
      EixClient.a().b().a(new Object() {
         @EventTarget
         public void onMotion(RecoveredEventsImplK var1) {
            if (var1.b() == RecoveredEventsApiAA.PRE) {
               Minecraft.getInstance().setScreen(new LanguageSelectScreen(null, Minecraft.getInstance().options, Minecraft.getInstance().getLanguageManager()));
               EixClient.a().b().b(this);
            }
         }
      });
   }

   @Override
   public String[] b(String[] var1) {
      return new String[0];
   }
}
