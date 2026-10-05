package com.heypixel.heypixelmod.obsoverlay.d;

import java.util.ArrayList;
import java.util.List;

public class RecoveredDE {
   private final List<RecoveredDC> a = new ArrayList<>();

   public void a(RecoveredDC var1) {
      this.a.add(var1);
   }

   public List<RecoveredDC> a(RecoveredDA var1) {
      ArrayList var2 = new ArrayList();

      for (RecoveredDC var4 : this.a) {
         if (var4.i() == var1) {
            var2.add(var4);
         }
      }

      return var2;
   }

   public RecoveredDC a(RecoveredDA var1, String var2) {
      for (RecoveredDC var4 : this.a) {
         if (var4.i() == var1 && var4.j().equals(var2)) {
            return var4;
         }
      }

      return null;
   }

   public List<RecoveredDC> a() {
      return this.a;
   }
}
