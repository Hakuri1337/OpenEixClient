package com.heypixel.heypixelmod.obsoverlay.events.impl;

import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.api.events.a.RecoveredEventsApiEventsAA;
import net.minecraft.network.chat.Component;

public class RecoveredEventsImplX extends RecoveredEventsApiEventsAA {
   private RecoveredEventsApiAA b;
   private Component c;

   public RecoveredEventsImplX(RecoveredEventsApiAA var1, Component var2) {
      this.b = var1;
      this.c = var2;
   }

   public RecoveredEventsApiAA b() {
      return this.b;
   }

   public void a(RecoveredEventsApiAA var1) {
      this.b = var1;
   }

   public Component c() {
      return this.c;
   }

   public void a(Component var1) {
      this.c = var1;
   }
}
