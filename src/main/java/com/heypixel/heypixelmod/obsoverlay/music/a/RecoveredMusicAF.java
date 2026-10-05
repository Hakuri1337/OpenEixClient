package com.heypixel.heypixelmod.obsoverlay.music.a;

public final class RecoveredMusicAF {
   public static final RecoveredMusicAF EMPTY = new RecoveredMusicAF("", "", "");
   private final String lrc;
   private final String translation;
   private final String yrc;

   public RecoveredMusicAF(String var1, String var2, String var3) {
      this.lrc = var1 == null ? "" : var1;
      this.translation = var2 == null ? "" : var2;
      this.yrc = var3 == null ? "" : var3;
   }

   public String a() {
      return this.lrc;
   }

   public String b() {
      return this.translation;
   }

   public String c() {
      return this.yrc;
   }

   public boolean d() {
      return this.lrc.isBlank();
   }
}
