package com.heypixel.heypixelmod.obsoverlay.music.a;

import java.util.Locale;

public final class RecoveredMusicAI {
   private final long id;
   private final String name;
   private final String artist;
   private final String album;
   private final String cover;
   private final long durationMillis;
   private final boolean free;
   private final int copyright;

   public RecoveredMusicAI(long var1, String var3, String var4, String var5, String var6, long var7, boolean var9, int var10) {
      this.id = var1;
      this.name = var3 == null ? "" : var3;
      this.artist = var4 == null ? "" : var4;
      this.album = var5 == null ? "" : var5;
      this.cover = var6 == null ? "" : var6;
      this.durationMillis = var7;
      this.free = var9;
      this.copyright = var10;
   }

   public long a() {
      return this.id;
   }

   public String b() {
      return this.name;
   }

   public String c() {
      return this.artist;
   }

   public String d() {
      return this.album;
   }

   public String e() {
      return this.cover;
   }

   public long f() {
      return this.durationMillis;
   }

   public boolean g() {
      return this.free;
   }

   public int h() {
      return this.copyright;
   }

   public boolean i() {
      return this.copyright != 0;
   }

   public String j() {
      return this.artist.isEmpty() ? this.name : this.artist + " - " + this.name;
   }

   public static long a(String var0) {
      if (var0 != null && !var0.isBlank()) {
         String[] var1 = var0.trim().split(":");

         try {
            long var2 = 0L;

            for (String var7 : var1) {
               var2 = var2 * 60L + Long.parseLong(var7.trim());
            }

            return var2 * 1000L;
         } catch (NumberFormatException var8) {
            return -1L;
         }
      } else {
         return -1L;
      }
   }

   public String k() {
      if (this.durationMillis <= 0L) {
         return "--:--";
      } else {
         long var1 = this.durationMillis / 1000L;
         return String.format(Locale.ROOT, "%02d:%02d", var1 / 60L, var1 % 60L);
      }
   }

   @Override
   public String toString() {
      return this.j();
   }
}
