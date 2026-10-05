package com.heypixel.heypixelmod.obsoverlay.events.api.events;

public abstract class RecoveredEventsApiEventsB implements Event {
   private boolean a;

   protected RecoveredEventsApiEventsB() {
   }

   public void a() {
      this.a = true;
   }

   public boolean b() {
      return this.a;
   }
}
