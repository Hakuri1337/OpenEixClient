package com.heypixel.heypixelmod.obsoverlay.d.a;

import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDA;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDC;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDF;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class RecoveredDAA extends RecoveredDC {
   private final boolean a;
   private final Consumer<RecoveredDC> b;
   private boolean c;

   public RecoveredDAA(RecoveredDA var1, String var2, boolean var3, Consumer<RecoveredDC> var4, Supplier<Boolean> var5) {
      super(var1, var2, var5);
      this.b = var4;
      this.c = this.a = var3;
   }

   @Override
   public RecoveredDF a() {
      return RecoveredDF.BOOLEAN;
   }

   @Override
   public RecoveredDAA b() {
      return this;
   }

   public boolean l() {
      return this.a;
   }

   public boolean m() {
      return this.c;
   }

   public void a(boolean var1) {
      this.c = var1;
      if (this.b != null) {
         this.b.accept(this);
      }
   }
}
