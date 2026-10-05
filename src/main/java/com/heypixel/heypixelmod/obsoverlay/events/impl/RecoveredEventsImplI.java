package com.heypixel.heypixelmod.obsoverlay.events.impl;

import com.heypixel.heypixelmod.obsoverlay.events.api.events.Event;

public class RecoveredEventsImplI implements Event {
   private float a;

   public RecoveredEventsImplI(float var1) {
      this.a = var1;
   }

   public float a() {
      return this.a;
   }

   public void a(float var1) {
      this.a = var1;
   }
}
