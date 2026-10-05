package com.heypixel.heypixelmod.obsoverlay.music.nowplaying;

import java.util.Locale;

public final class RecoveredMusicNowplayingB {
   public static final RecoveredMusicNowplayingB NONE = new RecoveredMusicNowplayingB("", "", "", "", 0L, -1L, false, 0L, "");
   private final String appId;
   private final String title;
   private final String artist;
   private final String album;
   private final long positionMs;
   private final long durationMs;
   private final boolean playing;
   private final long capturedAt;
   private final String source;
   private final long songId;

   public RecoveredMusicNowplayingB(String var1, String var2, String var3, String var4, long var5, long var7, boolean var9, long var10, String var12) {
      this(var1, var2, var3, var4, var5, var7, var9, var10, var12, -1L);
   }

   public RecoveredMusicNowplayingB(
      String var1, String var2, String var3, String var4, long var5, long var7, boolean var9, long var10, String var12, long var13
   ) {
      this.appId = a(var1);
      this.title = a(var2);
      this.artist = a(var3);
      this.album = a(var4);
      this.positionMs = Math.max(0L, var5);
      this.durationMs = var7;
      this.playing = var9;
      this.capturedAt = var10 <= 0L ? System.currentTimeMillis() : var10;
      this.source = a(var12);
      this.songId = var13;
   }

   private static String a(String var0) {
      return var0 == null ? "" : var0.trim();
   }

   public String a() {
      return this.appId;
   }

   public String b() {
      return this.title;
   }

   public String c() {
      return this.artist;
   }

   public String d() {
      return this.album;
   }

   public long e() {
      return this.positionMs;
   }

   public long f() {
      return this.durationMs;
   }

   public boolean g() {
      return this.playing;
   }

   public long h() {
      return this.capturedAt;
   }

   public String i() {
      return this.source;
   }

   public long j() {
      return this.songId;
   }

   public boolean k() {
      return this.title.isEmpty();
   }

   public boolean l() {
      return this.durationMs > 0L;
   }

   public long m() {
      if (!this.playing) {
         return this.positionMs;
      } else {
         long var1 = System.currentTimeMillis() - this.capturedAt;
         if (var1 >= 0L && var1 <= 15000L) {
            long var3 = this.positionMs + var1;
            if (this.durationMs > 0L && var3 > this.durationMs) {
               var3 = this.durationMs;
            }

            return var3;
         } else {
            return this.positionMs;
         }
      }
   }

   public String n() {
      return (this.title + "\u0000" + this.artist).toLowerCase(Locale.ROOT);
   }

   public String o() {
      if (this.k()) {
         return "";
      } else {
         return this.artist.isEmpty() ? this.title : this.artist + " - " + this.title;
      }
   }

   public boolean p() {
      String var1 = this.appId.toLowerCase(Locale.ROOT);
      return var1.contains("cloudmusic")
         || var1.contains("netease")
         || var1.contains("qqmusic")
         || var1.contains("kugou")
         || var1.contains("kuwo")
         || var1.contains("spotify")
         || var1.contains("foobar")
         || var1.contains("aimp")
         || var1.contains("music")
         || var1.contains("player");
   }

   public RecoveredMusicNowplayingB a(long var1) {
      return new RecoveredMusicNowplayingB(
         this.appId, this.title, this.artist, this.album, var1, this.durationMs, this.playing, System.currentTimeMillis(), this.source, this.songId
      );
   }

   public RecoveredMusicNowplayingB a(boolean var1) {
      return new RecoveredMusicNowplayingB(
         this.appId, this.title, this.artist, this.album, this.positionMs, this.durationMs, var1, this.capturedAt, this.source, this.songId
      );
   }

   public RecoveredMusicNowplayingB b(long var1) {
      return new RecoveredMusicNowplayingB(
         this.appId, this.title, this.artist, this.album, this.positionMs, this.durationMs, this.playing, this.capturedAt, this.source, var1
      );
   }

   public RecoveredMusicNowplayingB c(long var1) {
      return new RecoveredMusicNowplayingB(
         this.appId, this.title, this.artist, this.album, this.positionMs, var1, this.playing, this.capturedAt, this.source, this.songId
      );
   }

   @Override
   public String toString() {
      return "NowPlaying{"
         + this.appId
         + " | "
         + this.title
         + " - "
         + this.artist
         + " | "
         + this.positionMs
         + "/"
         + this.durationMs
         + " | playing="
         + this.playing
         + " | id="
         + this.songId
         + " | "
         + this.source
         + "}";
   }
}
