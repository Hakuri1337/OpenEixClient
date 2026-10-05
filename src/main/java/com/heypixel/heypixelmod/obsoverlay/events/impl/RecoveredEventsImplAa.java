package com.heypixel.heypixelmod.obsoverlay.events.impl;

import com.heypixel.heypixelmod.obsoverlay.events.api.events.Event;

public class RecoveredEventsImplAa implements Event {
   private boolean a;

   public RecoveredEventsImplAa(boolean var1) {
      this.a = var1;
   }

   public boolean a() {
      return this.a;
   }

   public void a(boolean var1) {
      this.a = var1;
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else {
         return !(var1 instanceof RecoveredEventsImplAa var2) ? false : var2.a(this) && this.a() == var2.a();
      }
   }

   protected boolean a(Object var1) {
      return var1 instanceof RecoveredEventsImplAa;
   }

   @Override
   public int hashCode() {
      byte var1 = 59;
      byte var2 = 1;
      return var2 * 59 + (this.a() ? 79 : 97);
   }

   @Override
   public String toString() {
      return "EventStayingOnGroundSurface(stay=" + this.a() + ")";
   }
}
