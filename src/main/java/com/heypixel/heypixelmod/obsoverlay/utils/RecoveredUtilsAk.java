package com.heypixel.heypixelmod.obsoverlay.utils;

import java.util.ArrayList;
import java.util.List;

public class RecoveredUtilsAk {
   private static final List<RecoveredUtilsAk> a = new ArrayList<>();
   private int b = 0;

   public RecoveredUtilsAk() {
      a.add(this);
   }

   public static void a() {
      for (RecoveredUtilsAk var1 : a) {
         var1.b++;
      }
   }

   public boolean a(int var1) {
      return this.b >= var1;
   }

   public boolean a(float var1) {
      return (float)this.b >= var1;
   }

   public void b() {
      this.b = 0;
   }
}
