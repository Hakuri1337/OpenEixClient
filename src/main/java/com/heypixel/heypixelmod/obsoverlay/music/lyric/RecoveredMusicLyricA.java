package com.heypixel.heypixelmod.obsoverlay.music.lyric;

public final class RecoveredMusicLyricA {
   private final long timeMs;
   private final String text;
   private final String translation;

   public RecoveredMusicLyricA(long var1, String var3, String var4) {
      this.timeMs = var1;
      this.text = var3 == null ? "" : var3;
      this.translation = var4 == null ? "" : var4;
   }

   public long a() {
      return this.timeMs;
   }

   public String b() {
      return this.text;
   }

   public String c() {
      return this.translation;
   }

   public boolean d() {
      return !this.translation.isEmpty();
   }

   public boolean e() {
      return this.text.trim().isEmpty();
   }

   @Override
   public String toString() {
      return "[" + this.timeMs + "]" + this.text;
   }
}
