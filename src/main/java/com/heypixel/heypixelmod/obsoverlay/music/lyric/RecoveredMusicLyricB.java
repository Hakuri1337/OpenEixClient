package com.heypixel.heypixelmod.obsoverlay.music.lyric;

import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsV;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class RecoveredMusicLyricB {
   private static final Logger LOGGER = LogManager.getLogger(RecoveredMusicLyricB.class);
   private static volatile RecoveredMusicLyricB instance;
   private final NeteaseLyricsClient client = new NeteaseLyricsClient();
   private final AtomicBoolean fetching = new AtomicBoolean(false);
   private volatile RecoveredMusicLyricC lyrics = RecoveredMusicLyricC.EMPTY;
   private volatile String trackKey = "";
   private volatile String pendingKey = "";
   private volatile String lastError = "";
   private volatile String currentLine = "";
   private volatile String currentTranslation = "";
   private volatile String nextLine = "";
   private volatile int currentIndex = -1;
   private volatile long currentLineStartMs = -1L;
   private volatile long currentLineEndMs = -1L;
   private volatile long lyricOffsetMs;
   private volatile boolean showTranslation = true;
   private volatile boolean enabled = true;
   private String lastTitle = "";
   private String lastArtist = "";
   private long lastSongId = -1L;
   private long lastComputeAt;
   private long lastComputedPosition = Long.MIN_VALUE;
   private volatile long positionSnapshot = 0L;

   private RecoveredMusicLyricB() {
   }

   public static RecoveredMusicLyricB a() {
      if (instance == null) {
         synchronized (RecoveredMusicLyricB.class) {
            if (instance == null) {
               instance = new RecoveredMusicLyricB();
            }
         }
      }

      return instance;
   }

   public void a(boolean var1) {
      if (this.enabled != var1) {
         this.enabled = var1;
         if (!var1) {
            this.p();
         } else {
            this.trackKey = "";
            this.lastTitle = "";
            this.lastArtist = "";
            this.lastSongId = -1L;
            this.p();
         }
      }
   }

   public boolean b() {
      return this.enabled;
   }

   public void a(long var1) {
      this.lyricOffsetMs = var1;
      this.lastComputedPosition = Long.MIN_VALUE;
   }

   public void b(boolean var1) {
      this.showTranslation = var1;
      this.lastComputedPosition = Long.MIN_VALUE;
   }

   public boolean c() {
      return this.showTranslation;
   }

   public String d() {
      return this.currentLine;
   }

   public String e() {
      return this.currentTranslation;
   }

   public String f() {
      return this.nextLine;
   }

   public int g() {
      return this.currentIndex;
   }

   public long h() {
      return this.currentLineStartMs;
   }

   public long i() {
      return this.currentLineEndMs;
   }

   public float j() {
      long var1 = this.currentLineStartMs;
      long var3 = this.currentLineEndMs;
      if (var1 >= 0L && var3 > var1) {
         float var5 = (float)(this.positionSnapshot - var1) / (float)(var3 - var1);
         if (var5 < 0.0F) {
            return 0.0F;
         } else {
            return var5 > 1.0F ? 1.0F : var5;
         }
      } else {
         return -1.0F;
      }
   }

   public RecoveredMusicLyricC k() {
      return this.lyrics;
   }

   public boolean l() {
      return !this.lyrics.d();
   }

   public boolean m() {
      return this.fetching.get();
   }

   public String n() {
      return this.lastError;
   }

   public String o() {
      return this.trackKey;
   }

   public void p() {
      this.lyrics = RecoveredMusicLyricC.EMPTY;
      this.currentLine = "";
      this.currentTranslation = "";
      this.nextLine = "";
      this.currentIndex = -1;
      this.currentLineStartMs = -1L;
      this.currentLineEndMs = -1L;
      this.lastComputedPosition = Long.MIN_VALUE;
   }

   public void a(String var1, String var2, long var3, long var5) {
      this.a(var1, var2, var3, var5, -1L);
   }

   public void a(String var1, String var2, long var3, long var5, long var7) {
      if (this.enabled) {
         if (var1 != null && !var1.isBlank()) {
            String var9 = var2 == null ? "" : var2;
            if (!var1.equals(this.lastTitle) || !var9.equals(this.lastArtist) || var7 != this.lastSongId) {
               this.lastTitle = var1;
               this.lastArtist = var9;
               this.lastSongId = var7;
               String var10 = (var7 > 0L ? var7 + "\u0000" : "") + (var1 + "\u0000" + var9).toLowerCase(Locale.ROOT);
               if (!var10.equals(this.trackKey)) {
                  this.trackKey = var10;
                  this.p();
                  this.a(var10, var1, var9, var3, var7);
               }
            }

            long var14 = System.currentTimeMillis();
            long var12 = var5 + this.lyricOffsetMs;
            this.positionSnapshot = var12;
            if (var14 - this.lastComputeAt >= 50L || Math.abs(var12 - this.lastComputedPosition) >= 200L) {
               this.lastComputeAt = var14;
               this.lastComputedPosition = var12;
               this.b(var12);
            }
         } else {
            if (!this.trackKey.isEmpty()) {
               this.trackKey = "";
               this.lastTitle = "";
               this.lastArtist = "";
               this.lastSongId = -1L;
               this.p();
            }
         }
      }
   }

   private void b(long var1) {
      RecoveredMusicLyricC var3 = this.lyrics;
      if (var3.d()) {
         this.currentLine = "";
         this.currentTranslation = "";
         this.nextLine = "";
         this.currentIndex = -1;
         this.currentLineStartMs = -1L;
         this.currentLineEndMs = -1L;
      } else {
         int var4 = var3.a(var1);
         if (var4 >= 0 && var4 < var3.a().size()) {
            this.currentLineStartMs = var3.a().get(var4).a();
            RecoveredMusicLyricA var5 = var3.a(var4 + 1);
            this.currentLineEndMs = var5 == null ? -1L : var5.a();
         } else {
            this.currentLineStartMs = -1L;
            this.currentLineEndMs = -1L;
         }

         RecoveredMusicLyricA var7 = var3.a(var4);
         if (var7 == null || var7.e()) {
            RecoveredMusicLyricA var6 = var3.c(var4);
            if (var6 != null) {
               var7 = var6;
            }
         }

         this.currentIndex = var4;
         this.currentLine = var7 == null ? "" : var7.b();
         this.currentTranslation = var7 != null && this.showTranslation ? var7.c() : "";
         RecoveredMusicLyricA var8 = var3.b(var4 + 1);
         this.nextLine = var8 == null ? "" : var8.b();
      }
   }

   private void a(String var1, String var2, String var3, long var4, long var6) {
      if (!var1.equals(this.pendingKey)) {
         this.pendingKey = var1;
         if (this.fetching.compareAndSet(false, true)) {
            RecoveredUtilsV.a(() -> {
               try {
                  RecoveredMusicLyricC var8 = var6 > 0L ? this.client.a(var6, var2, var3) : this.client.a(var2, var3, var4);
                  if (var1.equals(this.trackKey)) {
                     this.lyrics = var8;
                     this.lastError = var8.d() ? "未找到歌词" : "";
                     this.lastComputedPosition = Long.MIN_VALUE;
                     return;
                  }
               } catch (Throwable var12) {
                  LOGGER.debug("拉取歌词异常", var12);
                  this.lastError = "拉取失败";
                  return;
               } finally {
                  this.fetching.set(false);
               }
            });
         }
      }
   }

   public void a(String var1, String var2, long var3) {
      if (var1 != null && !var1.isBlank()) {
         RecoveredUtilsV.a(() -> {
            try {
               this.client.a(var1, var2, var3);
            } catch (Throwable var6) {
            }
         });
      }
   }

   public void q() {
      this.client.a();
   }
}
