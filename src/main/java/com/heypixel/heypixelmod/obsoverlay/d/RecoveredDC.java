package com.heypixel.heypixelmod.obsoverlay.d;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.a.RecoveredAA;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAB;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAE;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAF;
import java.util.function.Supplier;

public abstract class RecoveredDC {
   private final RecoveredDA a;
   private final String b;
   private final Supplier<Boolean> c;

   protected RecoveredDC(RecoveredDA var1, String var2, Supplier<Boolean> var3) {
      this.a = var1;
      this.b = var2;
      this.c = var3;
      EixClient.a().d().a(this);
   }

   public abstract RecoveredDF a();

   public RecoveredDAA b() {
      throw new RecoveredAA();
   }

   public RecoveredDAC c() {
      throw new RecoveredAA();
   }

   public RecoveredDAF d() {
      throw new RecoveredAA();
   }

   public RecoveredDAE e() {
      throw new RecoveredAA();
   }

   public RecoveredDAB f() {
      throw new RecoveredAA();
   }

   public RecoveredDAD g() {
      throw new RecoveredAA();
   }

   public boolean h() {
      return this.c == null || this.c.get();
   }

   public RecoveredDA i() {
      return this.a;
   }

   public String j() {
      return this.b;
   }

   public Supplier<Boolean> k() {
      return this.c;
   }
}
