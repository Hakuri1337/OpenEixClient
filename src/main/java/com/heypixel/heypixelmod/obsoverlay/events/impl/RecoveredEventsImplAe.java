package com.heypixel.heypixelmod.obsoverlay.events.impl;

import com.heypixel.heypixelmod.obsoverlay.events.api.events.Event;

public class RecoveredEventsImplAe implements Event {
   private float a;

   public RecoveredEventsImplAe(float var1) {
      this.a = var1;
   }

   public float a() {
      return this.a;
   }

   public void a(float var1) {
      this.a = var1;
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else {
         return !(var1 instanceof RecoveredEventsImplAe var2) ? false : var2.a(this) && Float.compare(this.a(), var2.a()) == 0;
      }
   }

   protected boolean a(Object var1) {
      return var1 instanceof RecoveredEventsImplAe;
   }

   @Override
   public int hashCode() {
      byte var1 = 59;
      byte var2 = 1;
      return var2 * 59 + Float.floatToIntBits(this.a());
   }

   @Override
   public String toString() {
      return "EventUpdateFoV(fov=" + this.a() + ")";
   }
}
