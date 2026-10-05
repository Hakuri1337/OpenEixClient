package com.heypixel.heypixelmod.obsoverlay.events.impl;

import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.api.events.a.RecoveredEventsApiEventsAA;

public class RecoveredEventsImplK extends RecoveredEventsApiEventsAA {
   private final RecoveredEventsApiAA b;
   private double c;
   private double d;
   private double e;
   private float f;
   private float g;
   private boolean h;

   public RecoveredEventsImplK(RecoveredEventsApiAA var1, float var2, float var3) {
      this.b = var1;
      this.f = var2;
      this.g = var3;
   }

   public RecoveredEventsImplK(RecoveredEventsApiAA var1, double var2, double var4, double var6, float var8, float var9, boolean var10) {
      this.b = var1;
      this.c = var2;
      this.d = var4;
      this.e = var6;
      this.f = var8;
      this.g = var9;
      this.h = var10;
   }

   public RecoveredEventsApiAA b() {
      return this.b;
   }

   public double c() {
      return this.c;
   }

   public void a(double var1) {
      this.c = var1;
   }

   public double d() {
      return this.d;
   }

   public void b(double var1) {
      this.d = var1;
   }

   public double e() {
      return this.e;
   }

   public void c(double var1) {
      this.e = var1;
   }

   public float f() {
      return this.f;
   }

   public void a(float var1) {
      this.f = var1;
   }

   public float g() {
      return this.g;
   }

   public void b(float var1) {
      this.g = var1;
   }

   public boolean h() {
      return this.h;
   }

   public void b(boolean var1) {
      this.h = var1;
   }
}
