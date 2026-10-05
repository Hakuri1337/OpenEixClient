package com.heypixel.heypixelmod.obsoverlay.music;

import com.heypixel.heypixelmod.obsoverlay.music.a.RecoveredMusicAI;
import com.heypixel.heypixelmod.obsoverlay.music.lyric.RecoveredMusicLyricB;
import com.heypixel.heypixelmod.obsoverlay.music.nowplaying.RecoveredMusicNowplayingB;
import com.heypixel.heypixelmod.obsoverlay.music.nowplaying.RecoveredMusicNowplayingC;

public final class RecoveredMusicE {
   private static volatile RecoveredMusicE instance;
   private volatile String title = "";
   private volatile String artist = "";
   private volatile String sourceApp = "";
   private volatile long positionMs;
   private volatile long durationMs;
   private volatile boolean playing;
   private volatile boolean fromLocal;
   private volatile boolean active;
   private volatile long songId = -1L;

   private RecoveredMusicE() {
   }

   public static RecoveredMusicE a() {
      if (instance == null) {
         synchronized (RecoveredMusicE.class) {
            if (instance == null) {
               instance = new RecoveredMusicE();
            }
         }
      }

      return instance;
   }

   public void b() {
      try {
         this.m();
      } catch (Throwable var2) {
         this.title = "";
         this.artist = "";
         this.sourceApp = "";
         this.positionMs = 0L;
         this.durationMs = -1L;
         this.playing = false;
         this.fromLocal = false;
         this.active = false;
         this.songId = -1L;
      }
   }

   private void m() {
      RecoveredMusicC var1 = RecoveredMusicC.a();
      RecoveredMusicB var2 = var1.c();
      RecoveredMusicAI var3 = var1.o();
      if (var2.d() && var3 != null) {
         this.title = var3.b();
         this.artist = var3.c();
         this.sourceApp = "在线播放";
         this.positionMs = var2.k();
         long var6 = var2.j();
         this.durationMs = var6 > 0L ? var6 : var3.f();
         this.playing = var2.c() == RecoveredMusicB.InnerA.PLAYING;
         this.fromLocal = true;
         this.active = true;
         this.songId = var3.a();
      } else {
         RecoveredMusicD var4 = var1.h();
         if (var2.d() && var4 != null) {
            this.title = var4.b();
            this.artist = var4.c();
            this.sourceApp = "本地播放器";
            this.positionMs = var2.k();
            this.durationMs = var2.j();
            this.playing = var2.c() == RecoveredMusicB.InnerA.PLAYING;
            this.fromLocal = true;
            this.active = true;
            this.songId = -1L;
         } else {
            RecoveredMusicNowplayingB var5 = RecoveredMusicNowplayingC.a().i();
            if (var5.k()) {
               this.title = "";
               this.artist = "";
               this.sourceApp = "";
               this.positionMs = 0L;
               this.durationMs = -1L;
               this.playing = false;
               this.fromLocal = false;
               this.active = false;
               this.songId = -1L;
            } else {
               this.title = var5.b();
               this.artist = var5.c();
               this.sourceApp = var5.a();
               this.positionMs = var5.m();
               this.durationMs = var5.f();
               this.playing = var5.g();
               this.fromLocal = false;
               this.active = true;
               this.songId = var5.j();
            }
         }
      }

      RecoveredMusicLyricB.a().a(this.title, this.artist, this.durationMs, this.positionMs, this.songId);
   }

   public boolean c() {
      return this.active;
   }

   public String d() {
      return this.title;
   }

   public String e() {
      return this.artist;
   }

   public String f() {
      return this.sourceApp;
   }

   public long g() {
      return this.positionMs;
   }

   public long h() {
      return this.durationMs;
   }

   public boolean i() {
      return this.playing;
   }

   public boolean j() {
      return this.fromLocal;
   }

   public long k() {
      return this.songId;
   }

   public String l() {
      if (this.title.isEmpty()) {
         return "";
      } else {
         return this.artist.isEmpty() ? this.title : this.artist + " - " + this.title;
      }
   }
}
