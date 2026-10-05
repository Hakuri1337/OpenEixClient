package com.heypixel.heypixelmod.obsoverlay.events.impl;

import com.heypixel.heypixelmod.obsoverlay.events.api.events.Event;
import net.minecraft.network.protocol.Packet;

public class RecoveredEventsImplW implements Event {
   private Packet<?> a;

   public RecoveredEventsImplW(Packet<?> var1) {
      this.a = var1;
   }

   public Packet<?> a() {
      return this.a;
   }

   public void a(Packet<?> var1) {
      this.a = var1;
   }
}
