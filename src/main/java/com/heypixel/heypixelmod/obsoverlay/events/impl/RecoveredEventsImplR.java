package com.heypixel.heypixelmod.obsoverlay.events.impl;

import com.heypixel.heypixelmod.obsoverlay.events.api.events.Event;
import net.minecraft.network.chat.Component;

public class RecoveredEventsImplR implements Event {
   private Component a;

   public RecoveredEventsImplR(Component var1) {
      this.a = var1;
   }

   public Component a() {
      return this.a;
   }

   public void a(Component var1) {
      this.a = var1;
   }
}
