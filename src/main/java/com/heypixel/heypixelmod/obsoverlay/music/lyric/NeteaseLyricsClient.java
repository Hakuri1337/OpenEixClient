package com.heypixel.heypixelmod.obsoverlay.music.lyric;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.heypixel.heypixelmod.obsoverlay.b.RecoveredBB;
import com.heypixel.heypixelmod.obsoverlay.music.a.RecoveredMusicAF;
import com.heypixel.heypixelmod.obsoverlay.music.a.RecoveredMusicAG;
import com.heypixel.heypixelmod.obsoverlay.music.a.RecoveredMusicAI;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class NeteaseLyricsClient {
   private static final Logger LOGGER = LogManager.getLogger(NeteaseLyricsClient.class);
   private static final String SEARCH_URL = "https://music.163.com/api/search/get/web?s=%s&type=%d&offset=0&limit=%d";
   private static final String ARTIST_SONGS_URL = "https://music.163.com/api/v1/artist/songs?id=%d&limit=100&offset=0&order=hot";
   private static final String LYRIC_URL = "https://music.163.com/api/song/lyric?id=%d&lv=1&tv=-1";
   private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36";
   private static final String REFERER = "https://music.163.com";
   private static final int MEMORY_CACHE_LIMIT = 64;
   private static final long MIN_REQUEST_INTERVAL_MS = 300L;
   private static final int SEARCH_LIMIT = 15;
   private static final int MIN_ACCEPT_SCORE = 40;
   private static final int MAX_LYRIC_ATTEMPTS = 4;
   private static final String[] VERSION_KEYWORDS = new String[]{
      "翻唱",
      "cover",
      "钢琴版",
      "吉他版",
      "纯音乐",
      "伴奏",
      "remix",
      "女声版",
      "男声版",
      "童声版",
      "片段",
      "剪辑",
      "串烧",
      "清唱",
      "合唱版",
      "电音",
      "加速版",
      "慢速版",
      "现场版",
      "dj版",
      "instrumental",
      "karaoke",
      "8d",
      "beat",
      "改大调",
      "改编"
   };
   private final Map<String, RecoveredMusicLyricC> memoryCache = new ConcurrentHashMap<>();
   private final Map<String, Long> negativeCache = new ConcurrentHashMap<>();
   private final File cacheFolder;
   private volatile long lastRequestAt;

   public NeteaseLyricsClient() {
      File var1 = RecoveredBB.clientFolder != null ? RecoveredBB.clientFolder : new File(System.getProperty("user.home"), "EixClient");
      this.cacheFolder = new File(var1, "lyrics");

      try {
         if (!this.cacheFolder.exists()) {
            this.cacheFolder.mkdirs();
         }
      } catch (Throwable var3) {
      }
   }

   public RecoveredMusicLyricC a(String var1, String var2, long var3) {
      String var5 = LrcParser.b(var1);
      if (var5.isEmpty()) {
         return RecoveredMusicLyricC.EMPTY;
      } else {
         String var6 = var2 == null ? "" : var2.trim();
         String var7 = (var5 + "\u0000" + var6).toLowerCase(Locale.ROOT);
         RecoveredMusicLyricC var8 = this.memoryCache.get(var7);
         if (var8 != null) {
            return var8;
         } else {
            Long var9 = this.negativeCache.get(var7);
            if (var9 != null && System.currentTimeMillis() - var9 < 120000L) {
               return RecoveredMusicLyricC.EMPTY;
            } else {
               RecoveredMusicLyricC var10 = this.d(var7);
               if (var10 != null) {
                  this.a(var7, var10);
                  return var10;
               } else {
                  try {
                     RecoveredMusicLyricC var11 = this.b(var5, var6, var3);
                     if (var11.d()) {
                        this.negativeCache.put(var7, System.currentTimeMillis());
                        return RecoveredMusicLyricC.EMPTY;
                     } else {
                        this.a(var7, var11);
                        this.b(var7, var11);
                        return var11;
                     }
                  } catch (Throwable var12) {
                     LOGGER.debug("获取歌词失败: {} - {}", var5, var6, var12);
                     this.negativeCache.put(var7, System.currentTimeMillis());
                     return RecoveredMusicLyricC.EMPTY;
                  }
               }
            }
         }
      }
   }

   private void a(String var1, RecoveredMusicLyricC var2) {
      if (this.memoryCache.size() > 64) {
         this.memoryCache.clear();
      }

      this.memoryCache.put(var1, var2);
   }

   public RecoveredMusicLyricC a(long var1, String var3, String var4) {
      if (var1 <= 0L) {
         return RecoveredMusicLyricC.EMPTY;
      } else {
         String var5 = "id:" + var1;
         RecoveredMusicLyricC var6 = this.memoryCache.get(var5);
         if (var6 != null) {
            return var6;
         } else {
            RecoveredMusicLyricC var7 = this.d(var5);
            if (var7 != null) {
               this.a(var5, var7);
               return var7;
            } else {
               Long var8 = this.negativeCache.get(var5);
               if (var8 != null && System.currentTimeMillis() - var8 < 120000L) {
                  return RecoveredMusicLyricC.EMPTY;
               } else {
                  try {
                     NeteaseLyricsClient.SongCandidate var9 = new NeteaseLyricsClient.SongCandidate(
                        var1, var3 == null ? "" : var3, var4 == null ? "" : var4, "", -1L
                     );
                     RecoveredMusicLyricC var10 = this.a(var9);
                     if (var10.d()) {
                        this.negativeCache.put(var5, System.currentTimeMillis());
                        return RecoveredMusicLyricC.EMPTY;
                     } else {
                        this.a(var5, var10);
                        this.b(var5, var10);
                        return var10;
                     }
                  } catch (Throwable var11) {
                     LOGGER.debug("按 ID 获取歌词失败: {}", var1, var11);
                     this.negativeCache.put(var5, System.currentTimeMillis());
                     return RecoveredMusicLyricC.EMPTY;
                  }
               }
            }
         }
      }
   }

   private RecoveredMusicLyricC b(String var1, String var2, long var3) throws Exception {
      boolean var5 = !var2.isBlank();
      LinkedHashMap var6 = new LinkedHashMap();
      if (var5) {
         a(var6, this.a(var1 + " " + var2, 1, 15));
         NeteaseLyricsClient.SongCandidate var7 = a(var6, var1, var2, var3);
         if (a(var7, var2)) {
            RecoveredMusicLyricC var8 = this.a(var7);
            if (!var8.d()) {
               return var8;
            }
         }
      }

      a(var6, this.a(var1, 1, 15));
      if (var5 && !a(a(var6, var1, var2, var3), var2)) {
         a(var6, this.a(var2));
      }

      ArrayList<SongCandidate> var11 = new ArrayList<>(var6.values());
      var11.sort((var4, var5x) -> Integer.compare(a(var5x, var1, var2, var3), a(var4, var1, var2, var3)));

      for (int var12 = 0; var12 < Math.min(4, var11.size()); var12++) {
         NeteaseLyricsClient.SongCandidate var9 = (NeteaseLyricsClient.SongCandidate)var11.get(var12);
         if (a(var9, var1, var2, var3) < 40) {
            break;
         }

         RecoveredMusicLyricC var10 = this.a(var9);
         if (!var10.d()) {
            return var10;
         }
      }

      if (!var11.isEmpty()) {
         RecoveredMusicLyricC var13 = this.a((NeteaseLyricsClient.SongCandidate)var11.get(0));
         if (!var13.d()) {
            return var13;
         }
      }

      return RecoveredMusicLyricC.EMPTY;
   }

   private static void a(Map<Long, NeteaseLyricsClient.SongCandidate> var0, List<NeteaseLyricsClient.SongCandidate> var1) {
      for (NeteaseLyricsClient.SongCandidate var3 : var1) {
         var0.putIfAbsent(var3.id, var3);
      }
   }

   private static NeteaseLyricsClient.SongCandidate a(Map<Long, NeteaseLyricsClient.SongCandidate> var0, String var1, String var2, long var3) {
      NeteaseLyricsClient.SongCandidate var5 = null;
      int var6 = Integer.MIN_VALUE;

      for (NeteaseLyricsClient.SongCandidate var8 : var0.values()) {
         int var9 = a(var8, var1, var2, var3);
         if (var9 > var6) {
            var6 = var9;
            var5 = var8;
         }
      }

      return var5;
   }

   private static boolean a(NeteaseLyricsClient.SongCandidate var0, String var1) {
      if (var0 != null && var1 != null && !var1.isBlank()) {
         String var2 = var0.artist.toLowerCase(Locale.ROOT);

         for (String var6 : var1.toLowerCase(Locale.ROOT).split("[/、,，&;；]")) {
            String var7 = var6.trim();
            if (!var7.isEmpty() && var2.contains(var7)) {
               return true;
            }
         }

         return false;
      } else {
         return false;
      }
   }

   private List<NeteaseLyricsClient.SongCandidate> a(String var1, int var2, int var3) throws Exception {
      if (var1 != null && !var1.isBlank()) {
         try {
            List var4 = this.a(var1, var3);
            if (!var4.isEmpty()) {
               return var4;
            }
         } catch (Throwable var5) {
            LOGGER.debug("解析接口搜索失败，回退公有接口: {}", var5.getMessage());
         }

         return this.b(var1, var2, var3);
      } else {
         return List.of();
      }
   }

   private List<NeteaseLyricsClient.SongCandidate> a(String var1, int var2) throws Exception {
      List<RecoveredMusicAI> var3 = RecoveredMusicAG.a().b().a(var1, Math.max(1, Math.min(var2, 50)), 0);
      ArrayList var4 = new ArrayList(var3.size());

      for (RecoveredMusicAI var6 : var3) {
         if (var6.a() > 0L && !var6.b().isBlank()) {
            var4.add(new NeteaseLyricsClient.SongCandidate(var6.a(), var6.b(), var6.c(), var6.d(), var6.f()));
         }
      }

      return var4;
   }

   private List<NeteaseLyricsClient.SongCandidate> b(String var1, int var2, int var3) throws Exception {
      String var4 = String.format(
         "https://music.163.com/api/search/get/web?s=%s&type=%d&offset=0&limit=%d", URLEncoder.encode(var1, StandardCharsets.UTF_8), var2, var3
      );
      String var5 = this.b(var4);
      if (var5 != null && !var5.isBlank()) {
         JsonObject var6 = JsonParser.parseString(var5).getAsJsonObject();
         if (var6.has("result") && !var6.get("result").isJsonNull()) {
            JsonObject var7 = var6.getAsJsonObject("result");
            return var7.has("songs") && var7.get("songs").isJsonArray() ? a(var7.getAsJsonArray("songs")) : List.of();
         } else {
            return List.of();
         }
      } else {
         return List.of();
      }
   }

   private List<NeteaseLyricsClient.SongCandidate> a(String var1) throws Exception {
      try {
         List var2 = this.a(var1, 50);
         if (!var2.isEmpty()) {
            return var2;
         }
      } catch (Throwable var13) {
         LOGGER.debug("解析接口按歌手搜索失败，回退公有接口: {}", var13.getMessage());
      }

      String var14 = String.format(
         "https://music.163.com/api/search/get/web?s=%s&type=%d&offset=0&limit=%d", URLEncoder.encode(var1, StandardCharsets.UTF_8), 100, 3
      );
      String var3 = this.b(var14);
      if (var3 != null && !var3.isBlank()) {
         JsonObject var4 = JsonParser.parseString(var3).getAsJsonObject();
         if (var4.has("result") && !var4.get("result").isJsonNull()) {
            JsonObject var5 = var4.getAsJsonObject("result");
            if (var5.has("artists") && var5.get("artists").isJsonArray()) {
               long var6 = -1L;
               String var8 = var1.toLowerCase(Locale.ROOT).trim();

               for (JsonElement var10 : var5.getAsJsonArray("artists")) {
                  JsonObject var11 = var10.getAsJsonObject();
                  String var12 = var11.has("name") ? var11.get("name").getAsString() : "";
                  if (var12.toLowerCase(Locale.ROOT).trim().equals(var8)) {
                     var6 = var11.get("id").getAsLong();
                     break;
                  }
               }

               if (var6 < 0L) {
                  return List.of();
               } else {
                  String var15 = this.b(String.format("https://music.163.com/api/v1/artist/songs?id=%d&limit=100&offset=0&order=hot", var6));
                  if (var15 != null && !var15.isBlank()) {
                     JsonObject var16 = JsonParser.parseString(var15).getAsJsonObject();
                     return var16.has("songs") && var16.get("songs").isJsonArray() ? a(var16.getAsJsonArray("songs")) : List.of();
                  } else {
                     return List.of();
                  }
               }
            } else {
               return List.of();
            }
         } else {
            return List.of();
         }
      } else {
         return List.of();
      }
   }

   private static List<NeteaseLyricsClient.SongCandidate> a(JsonArray var0) {
      ArrayList var1 = new ArrayList(var0.size());

      for (JsonElement var3 : var0) {
         try {
            JsonObject var4 = var3.getAsJsonObject();
            long var5 = var4.get("id").getAsLong();
            String var7 = var4.has("name") ? var4.get("name").getAsString() : "";
            long var8 = var4.has("duration") ? var4.get("duration").getAsLong() : -1L;
            StringBuilder var10 = new StringBuilder();
            if (var4.has("artists") && var4.get("artists").isJsonArray()) {
               for (JsonElement var12 : var4.getAsJsonArray("artists")) {
                  JsonObject var13 = var12.getAsJsonObject();
                  if (var10.length() > 0) {
                     var10.append('/');
                  }

                  var10.append(var13.has("name") ? var13.get("name").getAsString() : "");
               }
            }

            String var15 = "";
            if (var4.has("album") && var4.get("album").isJsonObject()) {
               JsonObject var16 = var4.getAsJsonObject("album");
               var15 = var16.has("name") ? var16.get("name").getAsString() : "";
            }

            if (!var7.isEmpty()) {
               var1.add(new NeteaseLyricsClient.SongCandidate(var5, var7, var10.toString(), var15, var8));
            }
         } catch (Throwable var14) {
         }
      }

      return var1;
   }

   private RecoveredMusicLyricC a(NeteaseLyricsClient.SongCandidate var1) throws Exception {
      try {
         RecoveredMusicAF var2 = RecoveredMusicAG.a().d(var1.id);
         if (var2 != null && !var2.d()) {
            RecoveredMusicLyricC var3 = LrcParser.a(var2.a(), var2.b(), var1.name, var1.artist);
            if (!var3.d()) {
               return var3;
            }
         }
      } catch (Throwable var6) {
         LOGGER.debug("解析接口取歌词失败，回退公有接口: {}", var6.getMessage());
      }

      String var7 = this.b(String.format("https://music.163.com/api/song/lyric?id=%d&lv=1&tv=-1", var1.id));
      if (var7 != null && !var7.isBlank()) {
         JsonObject var8 = JsonParser.parseString(var7).getAsJsonObject();
         String var4 = a(var8, "lrc");
         String var5 = a(var8, "tlyric");
         if (var4.isBlank()) {
            var4 = a(var8, "klyric");
         }

         return var4.isBlank() ? RecoveredMusicLyricC.EMPTY : LrcParser.a(var4, var5, var1.name, var1.artist);
      } else {
         return RecoveredMusicLyricC.EMPTY;
      }
   }

   private static String a(JsonObject var0, String var1) {
      if (var0.has(var1) && var0.get(var1).isJsonObject()) {
         JsonObject var2 = var0.getAsJsonObject(var1);
         return var2.has("lyric") && !var2.get("lyric").isJsonNull() ? var2.get("lyric").getAsString() : "";
      } else {
         return "";
      }
   }

   private static int a(NeteaseLyricsClient.SongCandidate var0, String var1, String var2, long var3) {
      int var5 = 0;
      String var6 = var0.name.toLowerCase(Locale.ROOT).trim();
      String var7 = var1.toLowerCase(Locale.ROOT).trim();
      String var8 = var6.replaceAll("[（(【\\[][^）)】\\]]*[）)】\\]]", "").trim();
      if (var6.equals(var7) || var8.equals(var7)) {
         var5 += 100;
      } else if (var8.contains(var7) || var7.contains(var8)) {
         var5 += 55;
      } else if (var6.contains(var7)) {
         var5 += 45;
      } else {
         var5 += 10;
      }

      boolean var9 = var2 != null && !var2.isBlank();
      if (var9) {
         if (a(var0, var2)) {
            var5 += 50;
         } else {
            var5 -= 30;
         }
      }

      byte var10 = 0;

      for (String var14 : VERSION_KEYWORDS) {
         if (var6.contains(var14)) {
            var10 += 35;
         }
      }

      var5 -= Math.min(70, var10);
      if (var3 > 0L && var0.duration > 0L) {
         long var17 = Math.abs(var3 - var0.duration);
         if (var17 <= 3000L) {
            var5 += 35;
         } else if (var17 <= 8000L) {
            var5 += 18;
         } else if (var17 > 30000L) {
            var5 -= 25;
         }
      }

      return var5;
   }

   private String b(String var1) throws Exception {
      this.b();
      HttpURLConnection var2 = null;

      Object var4;
      try {
         var2 = (HttpURLConnection)new URL(var1).openConnection();
         var2.setRequestMethod("GET");
         var2.setConnectTimeout(6000);
         var2.setReadTimeout(9000);
         var2.setRequestProperty(
            "User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"
         );
         var2.setRequestProperty("Referer", "https://music.163.com");
         var2.setRequestProperty("Accept", "application/json, text/plain, */*");
         var2.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8");
         var2.setInstanceFollowRedirects(true);
         int var3 = var2.getResponseCode();
         if (var3 == 200) {
            try (InputStream var14 = var2.getInputStream()) {
               return new String(var14.readAllBytes(), StandardCharsets.UTF_8);
            }
         }

         LOGGER.debug("网易云接口返回 {}", var3);
         var4 = null;
      } finally {
         if (var2 != null) {
            var2.disconnect();
         }
      }

      return (String)var4;
   }

   private synchronized void b() {
      long var1 = System.currentTimeMillis();
      long var3 = 300L - (var1 - this.lastRequestAt);
      if (var3 > 0L) {
         try {
            Thread.sleep(var3);
         } catch (InterruptedException var6) {
            Thread.currentThread().interrupt();
         }
      }

      this.lastRequestAt = System.currentTimeMillis();
   }

   private File c(String var1) {
      try {
         MessageDigest var2 = MessageDigest.getInstance("MD5");
         byte[] var3 = var2.digest(var1.getBytes(StandardCharsets.UTF_8));
         StringBuilder var4 = new StringBuilder(var3.length * 2);

         for (byte var8 : var3) {
            var4.append(Character.forDigit(var8 >> 4 & 15, 16));
            var4.append(Character.forDigit(var8 & 15, 16));
         }

         return new File(this.cacheFolder, var4 + ".json");
      } catch (Throwable var9) {
         return new File(this.cacheFolder, Integer.toHexString(var1.hashCode()) + ".json");
      }
   }

   private RecoveredMusicLyricC d(String var1) {
      File var2 = this.c(var1);
      if (!var2.isFile()) {
         return null;
      } else {
         try {
            String var3 = Files.readString(var2.toPath(), StandardCharsets.UTF_8);
            JsonObject var4 = JsonParser.parseString(var3).getAsJsonObject();
            String var5 = var4.has("lrc") ? var4.get("lrc").getAsString() : "";
            String var6 = var4.has("tlyric") ? var4.get("tlyric").getAsString() : "";
            String var7 = var4.has("name") ? var4.get("name").getAsString() : "";
            String var8 = var4.has("artist") ? var4.get("artist").getAsString() : "";
            if (var5.isBlank()) {
               return null;
            } else {
               RecoveredMusicLyricC var9 = LrcParser.a(var5, var6, var7, var8);
               return var9.d() ? null : var9;
            }
         } catch (Throwable var10) {
            return null;
         }
      }
   }

   private void b(String var1, RecoveredMusicLyricC var2) {
      try {
         StringBuilder var3 = new StringBuilder();
         StringBuilder var4 = new StringBuilder();

         for (RecoveredMusicLyricA var6 : var2.a()) {
            long var7 = var6.a();
            String var9 = String.format(Locale.ROOT, "[%02d:%02d.%03d]", var7 / 60000L, var7 / 1000L % 60L, var7 % 1000L);
            var3.append(var9).append(var6.b()).append('\n');
            if (var6.d()) {
               var4.append(var9).append(var6.c()).append('\n');
            }
         }

         JsonObject var13 = new JsonObject();
         var13.addProperty("lrc", var3.toString());
         var13.addProperty("tlyric", var4.toString());
         var13.addProperty("name", var2.b());
         var13.addProperty("artist", var2.c());
         var13.addProperty("savedAt", System.currentTimeMillis());

         try (OutputStream var14 = Files.newOutputStream(this.c(var1).toPath())) {
            var14.write(var13.toString().getBytes(StandardCharsets.UTF_8));
         }
      } catch (Throwable var12) {
      }
   }

   public void a() {
      this.memoryCache.clear();
      this.negativeCache.clear();
   }

   private static record SongCandidate(long id, String name, String artist, String album, long duration) {
   }
}
