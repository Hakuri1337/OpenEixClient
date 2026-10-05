package com.heypixel.heypixelmod.obsoverlay.events.api.events.a;

import com.heypixel.heypixelmod.obsoverlay.events.api.events.Event;
import com.heypixel.heypixelmod.obsoverlay.events.api.events.RecoveredEventsApiEventsA;

public abstract class RecoveredEventsApiEventsAA implements Event, RecoveredEventsApiEventsA {
   public boolean a;

   protected RecoveredEventsApiEventsAA() {
   }

   @Override
   public boolean a() {
      return this.a;
   }

   @Override
   public void a(boolean var1) {
      this.a = var1;
   }
}
