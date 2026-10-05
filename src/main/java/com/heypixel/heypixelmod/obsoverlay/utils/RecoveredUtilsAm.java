package com.heypixel.heypixelmod.obsoverlay.utils;

public class RecoveredUtilsAm {
   private long a = 0L;

   public void a() {
      this.a = System.currentTimeMillis();
   }

   public boolean a(long var1) {
      return System.currentTimeMillis() - this.a >= var1;
   }

   public boolean a(float var1, boolean var2) {
      if ((float)(System.currentTimeMillis() - this.a) >= var1) {
         if (var2) {
            this.a();
         }

         return true;
      } else {
         return false;
      }
   }

   public boolean a(double var1) {
      return (double)(System.currentTimeMillis() - this.a) >= var1;
   }

   public long b() {
      return System.currentTimeMillis() - this.a;
   }
}
