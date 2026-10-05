package com.heypixel.heypixelmod.obsoverlay.events.impl;

import com.heypixel.heypixelmod.obsoverlay.events.api.events.a.RecoveredEventsApiEventsAA;
import net.minecraft.network.protocol.Packet;

public class RecoveredEventsImplN extends RecoveredEventsApiEventsAA {
   private Packet<?> b;

   public RecoveredEventsImplN(Packet<?> var1) {
      this.b = var1;
   }

   public Packet<?> b() {
      return this.b;
   }

   public void a(Packet<?> var1) {
      this.b = var1;
   }
}
