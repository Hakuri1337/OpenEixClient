package com.heypixel.heypixelmod.obsoverlay.d.a;

import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDA;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDC;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDF;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsS;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class RecoveredDAC extends RecoveredDC {
   private final float a;
   private final float b;
   private final float c;
   private final float d;
   private final Consumer<RecoveredDC> e;
   private float f;

   public RecoveredDAC(RecoveredDA var1, String var2, float var3, float var4, float var5, float var6, Consumer<RecoveredDC> var7, Supplier<Boolean> var8) {
      super(var1, var2, var8);
      this.e = var7;
      this.f = this.a = var3;
      this.b = var4;
      this.c = var5;
      this.d = var6;
   }

   @Override
   public RecoveredDF a() {
      return RecoveredDF.FLOAT;
   }

   @Override
   public RecoveredDAC c() {
      return this;
   }

   public float l() {
      return this.a;
   }

   public float m() {
      return this.b;
   }

   public float n() {
      return this.c;
   }

   public float o() {
      return this.d;
   }

   public Consumer<RecoveredDC> p() {
      return this.e;
   }

   public float q() {
      return this.f;
   }

   public void a(float var1) {
      this.f = RecoveredUtilsS.b(var1, this.b, this.c);
      if (this.e != null) {
         this.e.accept(this);
      }
   }
}
