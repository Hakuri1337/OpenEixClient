package com.heypixel.heypixelmod.obsoverlay.events.impl;

import com.heypixel.heypixelmod.obsoverlay.events.api.events.Event;

public record EventMouseClick(int key, boolean state) implements Event {
   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (var1 instanceof EventMouseClick var2) {
         return !var2.canEqual(this) ? false : this.key() == var2.key() && this.state() == var2.state();
      } else {
         return false;
      }
   }

   private boolean canEqual(Object var1) {
      return var1 instanceof EventMouseClick;
   }

   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      var2 = var2 * 59 + this.key();
      return var2 * 59 + (this.state() ? 79 : 97);
   }

   @Override
   public String toString() {
      return "EventMouseClick(key=" + this.key() + ", state=" + this.state() + ")";
   }
}
