package com.heypixel.heypixelmod.obsoverlay.d.a;

import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDA;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDC;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDF;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class RecoveredDAF extends RecoveredDC {
   private final String a;
   private final Consumer<RecoveredDC> b;
   private String c;

   public RecoveredDAF(RecoveredDA var1, String var2, String var3, Consumer<RecoveredDC> var4, Supplier<Boolean> var5) {
      super(var1, var2, var5);
      this.b = var4;
      this.a = var3;
      this.c = var3;
   }

   @Override
   public RecoveredDF a() {
      return RecoveredDF.STRING;
   }

   @Override
   public RecoveredDAF d() {
      return this;
   }

   public String l() {
      return this.a;
   }

   public Consumer<RecoveredDC> m() {
      return this.b;
   }

   public String n() {
      return this.c;
   }

   public void a(String var1) {
      if (var1 != null) {
         this.c = var1;
         if (this.b != null) {
            this.b.accept(this);
         }
      }
   }
}
