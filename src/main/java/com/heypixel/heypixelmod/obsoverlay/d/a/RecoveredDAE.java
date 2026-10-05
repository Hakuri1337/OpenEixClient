package com.heypixel.heypixelmod.obsoverlay.d.a;

import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDA;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDC;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDF;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class RecoveredDAE extends RecoveredDC {
   private final String[] a;
   private final Consumer<RecoveredDC> b;
   private int c;

   public RecoveredDAE(RecoveredDA var1, String var2, String[] var3, int var4, Consumer<RecoveredDC> var5, Supplier<Boolean> var6) {
      super(var1, var2, var6);
      this.b = var5;
      this.a = var3;
      this.c = var4;
   }

   public boolean a(String var1) {
      return this.l().equalsIgnoreCase(var1);
   }

   @Override
   public RecoveredDF a() {
      return RecoveredDF.MODE;
   }

   @Override
   public RecoveredDAE e() {
      return this;
   }

   public String l() {
      return this.a[this.c];
   }

   public String[] m() {
      return this.a;
   }

   public Consumer<RecoveredDC> n() {
      return this.b;
   }

   public int o() {
      return this.c;
   }

   public void a(int var1) {
      this.c = var1;
      if (this.b != null) {
         this.b.accept(this);
      }
   }
}
