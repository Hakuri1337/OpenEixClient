package com.heypixel.heypixelmod.obsoverlay.music;

import java.io.File;
import java.util.Locale;
import javax.sound.sampled.AudioFileFormat;

public class RecoveredMusicD {
   private final File file;
   private String title;
   private String artist;
   private long durationMillis = -1L;
   private boolean probed;

   public RecoveredMusicD(File var1) {
      this.file = var1;
      this.title = c(var1.getName());
      this.artist = "";
   }

   public File a() {
      return this.file;
   }

   public String b() {
      return this.title != null && !this.title.isEmpty() ? this.title : c(this.file.getName());
   }

   public void a(String var1) {
      this.title = var1;
   }

   public String c() {
      return this.artist == null ? "" : this.artist;
   }

   public void b(String var1) {
      this.artist = var1;
   }

   public long d() {
      return this.durationMillis;
   }

   public void a(long var1) {
      this.durationMillis = var1;
   }

   public boolean e() {
      return this.durationMillis > 0L;
   }

   public String f() {
      return this.file.getAbsolutePath();
   }

   public synchronized void g() {
      if (!this.probed) {
         this.probed = true;
         AudioFileFormat var1 = RecoveredMusicA.b(this.file);
         if (var1 != null) {
            long var2 = RecoveredMusicA.a(var1);
            if (var2 > 0L) {
               this.durationMillis = var2;
            }

            String var4 = a(RecoveredMusicA.a(var1, "title"), RecoveredMusicA.a(var1, "title_sort"));
            String var5 = a(RecoveredMusicA.a(var1, "author"), RecoveredMusicA.a(var1, "artist"), RecoveredMusicA.a(var1, "album_artist"));
            if (var4 != null) {
               this.title = var4;
            }

            if (var5 != null) {
               this.artist = var5;
            }
         }

         String var6 = c(this.file.getName());
         int var3 = var6.indexOf(" - ");
         if (var3 > 0) {
            if (this.artist == null || this.artist.isEmpty()) {
               this.artist = var6.substring(0, var3).trim();
            }

            if (this.title == null || this.title.isEmpty() || this.title.equals(var6)) {
               this.title = var6.substring(var3 + 3).trim();
            }
         }
      }
   }

   private static String a(String... var0) {
      for (String var4 : var0) {
         if (var4 != null && !var4.isEmpty()) {
            return var4;
         }
      }

      return null;
   }

   private static String c(String var0) {
      int var1 = var0.lastIndexOf(46);
      return var1 > 0 ? var0.substring(0, var1) : var0;
   }

   public String h() {
      String var1 = this.c();
      return !var1.isEmpty() ? var1 + " - " + this.b() : this.b();
   }

   public String i() {
      String var1 = this.file.getName();
      int var2 = var1.lastIndexOf(46);
      return var2 >= 0 ? var1.substring(var2 + 1).toLowerCase(Locale.ROOT) : "";
   }

   public static String b(long var0) {
      if (var0 < 0L) {
         return "--:--";
      } else {
         long var2 = var0 / 1000L;
         long var4 = var2 / 60L;
         long var6 = var2 % 60L;
         return String.format(Locale.ROOT, "%d:%02d", var4, var6);
      }
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else {
         return var1 instanceof RecoveredMusicD var2 ? this.file.getAbsolutePath().equals(var2.file.getAbsolutePath()) : false;
      }
   }

   @Override
   public int hashCode() {
      return this.file.getAbsolutePath().hashCode();
   }

   @Override
   public String toString() {
      return this.h();
   }
}
