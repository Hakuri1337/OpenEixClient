package com.heypixel.heypixelmod.obsoverlay.events.api.events.a;

import com.heypixel.heypixelmod.obsoverlay.events.api.events.Event;
import com.heypixel.heypixelmod.obsoverlay.events.api.events.RecoveredEventsApiEventsC;

public abstract class RecoveredEventsApiEventsAB implements Event, RecoveredEventsApiEventsC {
   private final byte a;

   protected RecoveredEventsApiEventsAB(byte var1) {
      this.a = var1;
   }

   @Override
   public byte a() {
      return this.a;
   }
}
