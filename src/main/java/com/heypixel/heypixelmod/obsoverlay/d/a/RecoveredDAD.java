package com.heypixel.heypixelmod.obsoverlay.d.a;

import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDA;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDC;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDF;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class RecoveredDAD extends RecoveredDC {
   private final int a;
   private final Consumer<RecoveredDC> b;
   private int c;

   public RecoveredDAD(RecoveredDA var1, String var2, int var3, Consumer<RecoveredDC> var4, Supplier<Boolean> var5) {
      super(var1, var2, var5);
      this.a = var3;
      this.c = var3;
      this.b = var4;
   }

   @Override
   public RecoveredDF a() {
      return RecoveredDF.KEY;
   }

   @Override
   public RecoveredDAD g() {
      return this;
   }

   public int l() {
      return this.a;
   }

   public int m() {
      return this.c;
   }

   public void a(int var1) {
      this.c = var1;
      if (this.b != null) {
         this.b.accept(this);
      }
   }
}
