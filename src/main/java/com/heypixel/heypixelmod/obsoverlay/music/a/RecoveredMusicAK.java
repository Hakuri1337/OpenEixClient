package com.heypixel.heypixelmod.obsoverlay.music.a;

public final class RecoveredMusicAK {
   private final long id;
   private final String name;
   private final String cover;
   private final String updateFrequency;

   public RecoveredMusicAK(long var1, String var3, String var4, String var5) {
      this.id = var1;
      this.name = var3 == null ? "" : var3;
      this.cover = var4 == null ? "" : var4;
      this.updateFrequency = var5 == null ? "" : var5;
   }

   public long a() {
      return this.id;
   }

   public String b() {
      return this.name;
   }

   public String c() {
      return this.cover;
   }

   public String d() {
      return this.updateFrequency;
   }
}
