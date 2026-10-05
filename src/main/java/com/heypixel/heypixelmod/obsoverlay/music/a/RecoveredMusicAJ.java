package com.heypixel.heypixelmod.obsoverlay.music.a;

import java.util.Locale;

public final class RecoveredMusicAJ {
   private final long id;
   private final String url;
   private final String level;
   private final int bitrate;
   private final long size;
   private final String md5;
   private final String channelLayout;
   private final String cookieLabel;

   public RecoveredMusicAJ(long var1, String var3, String var4, int var5, long var6, String var8, String var9, String var10) {
      this.id = var1;
      this.url = var3 == null ? "" : var3;
      this.level = var4 == null ? "" : var4;
      this.bitrate = var5;
      this.size = var6;
      this.md5 = var8 == null ? "" : var8;
      this.channelLayout = var9 == null ? "" : var9;
      this.cookieLabel = var10 == null ? "" : var10;
   }

   public long a() {
      return this.id;
   }

   public String b() {
      return this.url;
   }

   public String c() {
      return this.level;
   }

   public int d() {
      return this.bitrate;
   }

   public long e() {
      return this.size;
   }

   public String f() {
      return this.md5;
   }

   public String g() {
      return this.channelLayout;
   }

   public String h() {
      return this.cookieLabel;
   }

   public boolean i() {
      return this.url.isBlank();
   }

   public String j() {
      String var1 = this.url.toLowerCase(Locale.ROOT);
      int var2 = var1.indexOf(63);
      if (var2 >= 0) {
         var1 = var1.substring(0, var2);
      }

      int var3 = var1.lastIndexOf(46);
      if (var3 >= 0 && var3 < var1.length() - 1) {
         String var4 = var1.substring(var3 + 1);
         if (var4.length() <= 5) {
            return var4;
         }
      }

      return "mp3";
   }
}
