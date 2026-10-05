package com.heypixel.heypixelmod.obsoverlay.music.nowplaying;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class RecoveredMusicNowplayingA {
   private static final Logger LOGGER = LogManager.getLogger(RecoveredMusicNowplayingA.class);
   private static final String[] CANDIDATE_DIRS = new String[]{"Netease/CloudMusic/webdata/file", "Netease/CloudMusic/webdata", "Netease/CloudMusic"};
   private static final String[] QUEUE_FILE_NAMES = new String[]{
      "playingList", "playinglist", "PlayingList", "playing", "currentPlaying", "playlist", "playlist.json", "queue"
   };
   private static final long CACHE_MILLIS = 2500L;
   private static final int MAX_SNIFF_FILES = 40;
   private static final long MAX_FILE_BYTES = 8388608L;
   private static final String DIR_OVERRIDE_PROPERTY = "eixclient.netease.webdata";
   private static volatile List<RecoveredMusicNowplayingA.InnerA> cachedQueue = List.of();
   private static volatile long lastReadAt;
   private static volatile boolean available = true;

   private RecoveredMusicNowplayingA() {
   }

   public static boolean a() {
      return available && d() != null;
   }

   private static File d() {
      String var0 = System.getProperty("eixclient.netease.webdata");
      if (var0 != null && !var0.isBlank()) {
         File var7 = new File(var0);
         return var7.isDirectory() ? var7 : null;
      } else {
         String var1 = System.getenv("LOCALAPPDATA");
         if (var1 != null && !var1.isBlank()) {
            for (String var5 : CANDIDATE_DIRS) {
               File var6 = new File(var1, var5);
               if (var6.isDirectory()) {
                  return var6;
               }
            }

            return null;
         } else {
            return null;
         }
      }
   }

   public static List<RecoveredMusicNowplayingA.InnerA> b() {
      long var0 = System.currentTimeMillis();
      if (var0 - lastReadAt < 2500L) {
         return cachedQueue;
      } else {
         lastReadAt = var0;
         File var2 = d();
         if (var2 == null) {
            available = false;
            return cachedQueue = List.of();
         } else {
            available = true;
            List var3 = a(var2);
            if (var3.isEmpty()) {
               var3 = b(var2);
            }

            cachedQueue = var3;
            return var3;
         }
      }
   }

   private static List<RecoveredMusicNowplayingA.InnerA> a(File var0) {
      for (String var4 : QUEUE_FILE_NAMES) {
         File var5 = new File(var0, var4);
         if (var5.isFile()) {
            List var6 = c(var5);
            if (!var6.isEmpty()) {
               return var6;
            }
         }
      }

      return List.of();
   }

   private static List<RecoveredMusicNowplayingA.InnerA> b(File var0) {
      File[] var1 = var0.listFiles();
      if (var1 == null) {
         return List.of();
      } else {
         ArrayList<File> var2 = new ArrayList();

         for (File var6 : var1) {
            if (var6.isFile() && var6.length() > 0L && var6.length() <= 8388608L) {
               var2.add(var6);
            }
         }

         var2.sort(Comparator.comparingLong(File::lastModified).reversed());
         int var7 = 0;

         for (File var9 : var2) {
            if (var7++ >= 40) {
               break;
            }

            List var10 = c(var9);
            if (!var10.isEmpty()) {
               LOGGER.debug("网易云播放队列命中文件: {}", var9.getName());
               return var10;
            }
         }

         return List.of();
      }
   }

   private static List<RecoveredMusicNowplayingA.InnerA> c(File var0) {
      try {
         String var1 = new String(Files.readAllBytes(var0.toPath()), StandardCharsets.UTF_8);
         if (var1.isBlank()) {
            return List.of();
         } else {
            if (var1.charAt(0) == '\ufeff') {
               var1 = var1.substring(1);
            }

            if (!var1.contains("\"track\"") && !var1.contains("\"name\"") && !var1.contains("\"id\"")) {
               return List.of();
            } else {
               JsonElement var2 = JsonParser.parseString(var1);
               if (!var2.isJsonObject()) {
                  return List.of();
               } else {
                  JsonObject var3 = var2.getAsJsonObject();
                  JsonArray var4 = null;

                  for (String var8 : new String[]{"list", "tracks", "songs", "data", "queue"}) {
                     if (var3.has(var8) && var3.get(var8).isJsonArray()) {
                        var4 = var3.getAsJsonArray(var8);
                        break;
                     }
                  }

                  if (var4 == null && var3.has("playlist") && var3.get("playlist").isJsonObject()) {
                     JsonObject var10 = var3.getAsJsonObject("playlist");
                     if (var10.has("tracks") && var10.get("tracks").isJsonArray()) {
                        var4 = var10.getAsJsonArray("tracks");
                     }
                  }

                  if (var4 == null) {
                     return List.of();
                  } else {
                     ArrayList var11 = new ArrayList(var4.size());

                     for (JsonElement var13 : var4) {
                        RecoveredMusicNowplayingA.InnerA var14 = a(var13);
                        if (var14 != null) {
                           var11.add(var14);
                        }
                     }

                     return var11;
                  }
               }
            }
         }
      } catch (Throwable var9) {
         return List.of();
      }
   }

   private static RecoveredMusicNowplayingA.InnerA a(JsonElement var0) {
      if (var0 != null && var0.isJsonObject()) {
         try {
            JsonObject var1 = var0.getAsJsonObject();
            JsonObject var2 = var1.has("track") && var1.get("track").isJsonObject() ? var1.getAsJsonObject("track") : var1;
            if (var2.has("id") && var2.get("id").isJsonPrimitive()) {
               long var3 = var2.get("id").getAsLong();
               if (var3 <= 0L) {
                  return null;
               } else {
                  String var5 = var2.has("name") && var2.get("name").isJsonPrimitive() ? var2.get("name").getAsString() : "";
                  long var6 = -1L;

                  for (String var11 : new String[]{"duration", "dt", "durationMs"}) {
                     if (var2.has(var11) && var2.get(var11).isJsonPrimitive()) {
                        try {
                           var6 = var2.get(var11).getAsLong();
                           break;
                        } catch (Throwable var16) {
                        }
                     }
                  }

                  StringBuilder var18 = new StringBuilder();

                  for (String var12 : new String[]{"artists", "ar"}) {
                     if (var2.has(var12) && var2.get(var12).isJsonArray()) {
                        for (JsonElement var14 : var2.getAsJsonArray(var12)) {
                           if (var14.isJsonObject()) {
                              JsonObject var15 = var14.getAsJsonObject();
                              if (var15.has("name") && var15.get("name").isJsonPrimitive()) {
                                 if (var18.length() > 0) {
                                    var18.append('/');
                                 }

                                 var18.append(var15.get("name").getAsString());
                              }
                           }
                        }

                        if (var18.length() > 0) {
                           break;
                        }
                     }
                  }

                  return new RecoveredMusicNowplayingA.InnerA(var3, var5, var18.toString(), var6);
               }
            } else {
               return null;
            }
         } catch (Throwable var17) {
            return null;
         }
      } else {
         return null;
      }
   }

   public static RecoveredMusicNowplayingA.InnerA a(String var0, String var1) {
      if (var0 != null && !var0.isBlank()) {
         List<RecoveredMusicNowplayingA.InnerA> var2 = b();
         if (var2.isEmpty()) {
            return null;
         } else {
            String var3 = a(var0);
            String var4 = a(var1);
            if (!var4.isEmpty()) {
               for (RecoveredMusicNowplayingA.InnerA var6 : var2) {
                  if (a(var6.name).equals(var3) && a(var6.artist).contains(var4)) {
                     return var6;
                  }
               }
            }

            for (RecoveredMusicNowplayingA.InnerA var10 : var2) {
               if (a(var10.name).equals(var3)) {
                  return var10;
               }
            }

            for (RecoveredMusicNowplayingA.InnerA var11 : var2) {
               String var7 = a(var11.name);
               if (!var7.isEmpty() && (var7.contains(var3) || var3.contains(var7)) && Math.abs(var7.length() - var3.length()) <= 12) {
                  return var11;
               }
            }

            return null;
         }
      } else {
         return null;
      }
   }

   private static String a(String var0) {
      if (var0 == null) {
         return "";
      } else {
         StringBuilder var1 = new StringBuilder(var0.length());

         for (char var5 : var0.toCharArray()) {
            if (var5 >= '！' && var5 <= '～') {
               var5 -= 'ﻠ';
            }

            if (!Character.isWhitespace(var5)
               && var5 != '-'
               && var5 != '_'
               && var5 != '('
               && var5 != ')'
               && var5 != '['
               && var5 != ']'
               && var5 != '（'
               && var5 != '）'
               && var5 != 12304
               && var5 != 12305
               && var5 != 183
               && var5 != '\''
               && var5 != '"'
               && var5 != ','
               && var5 != '.'
               && var5 != '，'
               && var5 != 12290) {
               var1.append(Character.toLowerCase(var5));
            }
         }

         return var1.toString().toLowerCase(Locale.ROOT);
      }
   }

   public static int c() {
      return b().size();
   }

   public static final class InnerA {
      private final long id;
      private final String name;
      private final String artist;
      private final long durationMs;

      InnerA(long var1, String var3, String var4, long var5) {
         this.id = var1;
         this.name = var3 == null ? "" : var3;
         this.artist = var4 == null ? "" : var4;
         this.durationMs = var5;
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

      public long d() {
         return this.durationMs;
      }

      @Override
      public String toString() {
         return this.id + " " + this.name + " - " + this.artist;
      }
   }
}
