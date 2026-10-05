package com.heypixel.heypixelmod.obsoverlay.d.a;

import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDA;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDC;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDF;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class RecoveredDAB extends RecoveredDC {
   private final float a;
   private final float b;
   private final Consumer<RecoveredDC> c;
   private float d;
   private float e;
   private float f;
   private float g;

   public RecoveredDAB(RecoveredDA var1, String var2, float var3, float var4, Consumer<RecoveredDC> var5, Supplier<Boolean> var6) {
      super(var1, var2, var6);
      this.a = var3;
      this.b = var4;
      this.d = var3;
      this.e = var4;
      this.c = var5;
   }

   @Override
   public RecoveredDF a() {
      return RecoveredDF.DRAG;
   }

   @Override
   public RecoveredDAB f() {
      return this;
   }

   public float l() {
      return this.a;
   }

   public float m() {
      return this.b;
   }

   public float n() {
      return this.d;
   }

   public void a(float var1) {
      this.d = var1;
      if (this.c != null) {
         this.c.accept(this);
      }
   }

   public float o() {
      return this.e;
   }

   public void b(float var1) {
      this.e = var1;
      if (this.c != null) {
         this.c.accept(this);
      }
   }

   public float p() {
      return this.f;
   }

   public void c(float var1) {
      this.f = var1;
   }

   public float q() {
      return this.g;
   }

   public void d(float var1) {
      this.g = var1;
   }

   public void a(float var1, float var2) {
      this.d = var1;
      this.e = var2;
      if (this.c != null) {
         this.c.accept(this);
      }
   }
}
