package com.heypixel.heypixelmod.obsoverlay.music.a;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

public final class RecoveredMusicAC {
   private static final long COOLDOWN_MS = 60000L;
   private final List<String> custom = new ArrayList<>();
   private final Map<String, Long> blockedUntil = new ConcurrentHashMap<>();
   private volatile String preferred;
   private volatile boolean autoRotate = true;

   public void a(String var1) {
      if (e(var1)) {
         this.preferred = var1.trim();
      }
   }

   public String a() {
      return this.preferred;
   }

   public boolean b() {
      return this.autoRotate;
   }

   public void a(boolean var1) {
      this.autoRotate = var1;
   }

   public void b(String var1) {
      if (e(var1)) {
         String var2 = var1.trim();
         if (!this.custom.contains(var2)) {
            this.custom.add(var2);
         }
      }
   }

   public List<String> c() {
      return List.copyOf(this.custom);
   }

   public void d() {
      this.custom.clear();
   }

   public void c(String var1) {
      if (var1 != null && !var1.isBlank()) {
         this.blockedUntil.put(var1, System.currentTimeMillis() + 60000L);
      }
   }

   public boolean d(String var1) {
      Long var2 = this.blockedUntil.get(var1);
      if (var2 == null) {
         return false;
      } else if (var2 < System.currentTimeMillis()) {
         this.blockedUntil.remove(var1);
         return false;
      } else {
         return true;
      }
   }

   public String e() {
      String var1 = this.preferred;
      if (this.f(var1)) {
         return var1;
      } else {
         for (String var3 : this.custom) {
            if (this.f(var3)) {
               return var3;
            }
         }

         if (this.autoRotate) {
            for (int var4 = 0; var4 < 64; var4++) {
               String var5 = f();
               if (this.f(var5)) {
                  return var5;
               }
            }
         }

         return var1 != null ? var1 : "1.1.1.1";
      }
   }

   private boolean f(String var1) {
      return e(var1) && !this.d(var1);
   }

   public static boolean e(String var0) {
      if (var0 == null) {
         return false;
      } else {
         String var1 = var0.trim();
         String[] var2 = var1.split("\\.", -1);
         if (var2.length != 4) {
            return false;
         } else {
            for (String var6 : var2) {
               if (var6.isEmpty() || var6.length() > 3) {
                  return false;
               }

               for (int var7 = 0; var7 < var6.length(); var7++) {
                  if (!Character.isDigit(var6.charAt(var7))) {
                     return false;
                  }
               }

               if (Integer.parseInt(var6) > 255) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   public static String f() {
      ThreadLocalRandom var0 = ThreadLocalRandom.current();

      int var1;
      int var2;
      int var3;
      int var4;
      do {
         var1 = var0.nextInt(1, 224);
         var2 = var0.nextInt(0, 256);
         var3 = var0.nextInt(0, 256);
         var4 = var0.nextInt(1, 255);
      } while (
         var1 == 10
            || var1 == 127
            || var1 == 0
            || var1 == 172 && var2 >= 16 && var2 <= 31
            || var1 == 192 && var2 == 168
            || var1 == 169 && var2 == 254
            || var1 == 100 && var2 >= 64 && var2 <= 127
      );

      return var1 + "." + var2 + "." + var3 + "." + var4;
   }
}
