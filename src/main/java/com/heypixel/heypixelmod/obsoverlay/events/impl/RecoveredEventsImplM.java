package com.heypixel.heypixelmod.obsoverlay.events.impl;

import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.api.events.a.RecoveredEventsApiEventsAA;
import net.minecraft.network.protocol.Packet;

public class RecoveredEventsImplM extends RecoveredEventsApiEventsAA {
   private final RecoveredEventsApiAA b;
   private Packet<?> c;

   public RecoveredEventsImplM(RecoveredEventsApiAA var1, Packet<?> var2) {
      this.b = var1;
      this.c = var2;
   }

   public RecoveredEventsApiAA b() {
      return this.b;
   }

   public Packet<?> c() {
      return this.c;
   }

   public void a(Packet<?> var1) {
      this.c = var1;
   }
}
