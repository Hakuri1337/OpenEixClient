package com.heypixel.heypixelmod.obsoverlay.events.impl;

import com.heypixel.heypixelmod.obsoverlay.events.api.events.Event;
import net.minecraft.world.entity.Entity;

public class RecoveredEventsImplO implements Event {
   public Entity a;
   public float b;
   public float c;

   public RecoveredEventsImplO(Entity var1, float var2, float var3) {
      this.a = var1;
      this.b = var2;
      this.c = var3;
   }

   public Entity a() {
      return this.a;
   }

   public void a(Entity var1) {
      this.a = var1;
   }

   public float b() {
      return this.b;
   }

   public void a(float var1) {
      this.b = var1;
   }

   public float c() {
      return this.c;
   }

   public void b(float var1) {
      this.c = var1;
   }
}
