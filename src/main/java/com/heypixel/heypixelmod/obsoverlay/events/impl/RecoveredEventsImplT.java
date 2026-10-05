package com.heypixel.heypixelmod.obsoverlay.events.impl;

import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.api.events.Event;
import net.minecraft.network.chat.Component;

public class RecoveredEventsImplT implements Event {
   private RecoveredEventsApiAA a;
   private Component b;

   public RecoveredEventsImplT(RecoveredEventsApiAA var1, Component var2) {
      this.a = var1;
      this.b = var2;
   }

   public RecoveredEventsApiAA a() {
      return this.a;
   }

   public void a(RecoveredEventsApiAA var1) {
      this.a = var1;
   }

   public Component b() {
      return this.b;
   }

   public void a(Component var1) {
      this.b = var1;
   }
}
