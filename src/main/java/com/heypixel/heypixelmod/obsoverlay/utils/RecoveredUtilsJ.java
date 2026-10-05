package com.heypixel.heypixelmod.obsoverlay.utils;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.api.events.Event;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplE;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplU;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.client.event.RenderGuiEvent.Post;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RecoveredUtilsJ {
   @SubscribeEvent
   public void a(Post var1) {
   }

   @SubscribeEvent
   public void a(ClientChatEvent var1) {
      RecoveredEventsImplE var2 = new RecoveredEventsImplE(var1.getMessage());
      EixClient.a().b().a((Event)var2);
      if (var2.a()) {
         var1.setCanceled(true);
      }
   }

   @EventTarget
   public void onMotion(RecoveredEventsImplK var1) {
      if (var1.b() == RecoveredEventsApiAA.PRE && Minecraft.getInstance().player.tickCount <= 1) {
         EixClient.a().b().a((Event)(new RecoveredEventsImplU()));
      }
   }
}
