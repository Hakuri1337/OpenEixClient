package com.heypixel.heypixelmod.obsoverlay.events.impl;

import com.heypixel.heypixelmod.obsoverlay.events.api.events.Event;
import com.mojang.blaze3d.vertex.PoseStack;

public class RecoveredEventsImplP implements Event {
   private final float a;
   private final PoseStack b;

   public RecoveredEventsImplP(float var1, PoseStack var2) {
      this.a = var1;
      this.b = var2;
   }

   public float a() {
      return this.a;
   }

   public PoseStack b() {
      return this.b;
   }
}
