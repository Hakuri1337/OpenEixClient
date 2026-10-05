package com.heypixel.heypixelmod.obsoverlay.events.impl;

import com.heypixel.heypixelmod.obsoverlay.events.api.events.a.RecoveredEventsApiEventsAA;

public class RecoveredEventsImplJ extends RecoveredEventsApiEventsAA {
   private final int b;
   private final boolean c;

   public RecoveredEventsImplJ(int var1, boolean var2) {
      this.b = var1;
      this.c = var2;
   }

   public int b() {
      return this.b;
   }

   public boolean c() {
      return this.c;
   }
}
