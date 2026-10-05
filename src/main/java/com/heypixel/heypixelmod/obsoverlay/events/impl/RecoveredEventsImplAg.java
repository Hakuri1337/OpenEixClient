package com.heypixel.heypixelmod.obsoverlay.events.impl;

import com.heypixel.heypixelmod.obsoverlay.events.api.events.Event;

public class RecoveredEventsImplAg implements Event {
   private float a;
   private float b;

   public RecoveredEventsImplAg(float var1, float var2) {
      this.a = var1;
      this.b = var2;
   }

   public float a() {
      return this.a;
   }

   public void a(float var1) {
      this.a = var1;
   }

   public float b() {
      return this.b;
   }

   public void b(float var1) {
      this.b = var1;
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (var1 instanceof RecoveredEventsImplAg var2) {
         return !var2.a(this) ? false : Float.compare(this.a(), var2.a()) == 0 && Float.compare(this.b(), var2.b()) == 0;
      } else {
         return false;
      }
   }

   protected boolean a(Object var1) {
      return var1 instanceof RecoveredEventsImplAg;
   }

   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      var2 = var2 * 59 + Float.floatToIntBits(this.a());
      return var2 * 59 + Float.floatToIntBits(this.b());
   }

   @Override
   public String toString() {
      return "EventUseItemRayTrace(yaw=" + this.a() + ", pitch=" + this.b() + ")";
   }
}
