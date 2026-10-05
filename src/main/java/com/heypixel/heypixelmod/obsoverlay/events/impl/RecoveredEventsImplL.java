package com.heypixel.heypixelmod.obsoverlay.events.impl;

import com.heypixel.heypixelmod.obsoverlay.events.api.events.Event;

public class RecoveredEventsImplL implements Event {
   public static Object a;
   public static Object b;
   private float c;
   private float d;
   private boolean e;
   private boolean f;
   private double g;

   public RecoveredEventsImplL(float var1, float var2, boolean var3, boolean var4, double var5) {
      this.c = var1;
      this.d = var2;
      this.e = var3;
      this.f = var4;
      this.g = var5;
   }

   public float a() {
      return this.c;
   }

   public void a(float var1) {
      this.c = var1;
   }

   public float b() {
      return this.d;
   }

   public void b(float var1) {
      this.d = var1;
   }

   public boolean c() {
      return this.e;
   }

   public void a(boolean var1) {
      this.e = var1;
   }

   public boolean d() {
      return this.f;
   }

   public void b(boolean var1) {
      this.f = var1;
   }

   public double e() {
      return this.g;
   }

   public void a(double var1) {
      this.g = var1;
   }
}
