package com.heypixel.heypixelmod.obsoverlay.music.a;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class RecoveredMusicAD {
   private static final Logger LOGGER = LogManager.getLogger(RecoveredMusicAD.class);
   public static final String DEFAULT_BASE_URL = "https://nextmusic.toubiec.cn";
   public static final String DEFAULT_LEVEL = "lossless";
   public static final String[][] LEVELS = new String[][]{
      {"standard", "标准音质"},
      {"exhigh", "极高音质"},
      {"lossless", "无损音质"},
      {"hires", "Hi-Res音质"},
      {"jyeffect", "高清环绕声"},
      {"sky", "沉浸环绕声"},
      {"dolby", "杜比全景声"},
      {"vivid", "臻音全景声"},
      {"jymaster", "超清母带"}
   };
   private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36";
   private static final int CONNECT_TIMEOUT_MS = 8000;
   private static final int READ_TIMEOUT_MS = 15000;
   private static final int MAX_IP_RETRIES = 5;
   private final RecoveredMusicAC ipPool = new RecoveredMusicAC();
   private volatile String baseUrl = "https://nextmusic.toubiec.cn";

   public RecoveredMusicAC a() {
      return this.ipPool;
   }

   public String b() {
      return this.baseUrl;
   }

   public void a(String var1) {
      if (var1 != null && !var1.isBlank()) {
         String var2 = var1.trim();

         while (var2.endsWith("/")) {
            var2 = var2.substring(0, var2.length() - 1);
         }

         this.baseUrl = var2;
      }
   }

   public JsonObject a(String var1, JsonObject var2) throws IOException {
      IOException var3 = null;

      for (int var4 = 0; var4 < 5; var4++) {
         String var5 = this.ipPool.e();
         JsonObject var6 = var2 == null ? new JsonObject() : var2.deepCopy();
         var6.addProperty("ip", var5);
         var6.addProperty("timestamp", System.currentTimeMillis());

         JsonObject var7;
         try {
            var7 = this.c(var1, var6);
         } catch (IOException var10) {
            var3 = var10;
            LOGGER.debug("接口 {} 请求失败，换 IP 重试", var1, var10);
            continue;
         }

         int var8 = a(var7, "code", -1);
         if (var8 == 200) {
            return var7;
         }

         String var9 = a(var7, "message", "");
         if (var8 != 429) {
            throw new RecoveredMusicAE(var8, var9.isBlank() ? "接口返回 code=" + var8 : var9);
         }

         this.ipPool.c(var5);
         var3 = new RecoveredMusicAE(429, var9.isBlank() ? "请求过于频繁" : var9);
         LOGGER.debug("接口 {} 被限流（ip={}），换 IP 重试：{}", var1, var5, var9);
      }

      throw var3 != null ? var3 : new IOException("接口 " + var1 + " 重试次数用尽");
   }

   public JsonElement b(String var1, JsonObject var2) throws IOException {
      JsonObject var3 = this.a(var1, var2);
      JsonElement var4 = var3.get("data");
      return var4 != null && !var4.isJsonNull() ? var4 : null;
   }

   private JsonObject c(String var1, JsonObject var2) throws IOException {
      HttpURLConnection var3 = null;

      JsonObject var10;
      try {
         URL var4 = new URL(this.baseUrl + var1);
         var3 = (HttpURLConnection)var4.openConnection();
         var3.setRequestMethod("POST");
         var3.setDoOutput(true);
         var3.setConnectTimeout(8000);
         var3.setReadTimeout(15000);
         var3.setRequestProperty("Content-Type", "application/json");
         var3.setRequestProperty("Accept", "application/json, text/plain, */*");
         var3.setRequestProperty(
            "User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"
         );
         var3.setRequestProperty("Origin", this.baseUrl);
         var3.setRequestProperty("Referer", this.baseUrl + "/");
         byte[] var5 = var2.toString().getBytes(StandardCharsets.UTF_8);
         var3.setFixedLengthStreamingMode(var5.length);

         try (OutputStream var6 = var3.getOutputStream()) {
            var6.write(var5);
         }

         int var18 = var3.getResponseCode();
         InputStream var7 = var18 >= 400 ? var3.getErrorStream() : var3.getInputStream();
         if (var7 == null) {
            var7 = var3.getErrorStream();
         }

         String var8 = var7 == null ? "" : new String(var7.readAllBytes(), StandardCharsets.UTF_8);
         if (var8.isBlank()) {
            throw new IOException("接口 " + var1 + " 返回空响应（HTTP " + var18 + "）");
         }

         JsonElement var9 = JsonParser.parseString(var8);
         if (!var9.isJsonObject()) {
            throw new IOException("接口 " + var1 + " 返回了非 JSON 内容");
         }

         var10 = var9.getAsJsonObject();
      } finally {
         if (var3 != null) {
            var3.disconnect();
         }
      }

      return var10;
   }

   public String c() throws IOException {
      JsonObject var1 = this.a("/api/ip", new JsonObject());
      JsonElement var2 = var1.get("data");
      if (var2 != null && var2.isJsonObject()) {
         String var3 = a(var2.getAsJsonObject(), "ip", "");
         if (!var3.isBlank()) {
            this.ipPool.a(var3);
         }

         return var3;
      } else {
         return "";
      }
   }

   public void d() {
      if (this.ipPool.a() == null) {
         try {
            this.c();
         } catch (Throwable var2) {
            LOGGER.debug("获取接口客户端 IP 失败", var2);
         }
      }
   }

   public List<RecoveredMusicAI> a(String var1, int var2, int var3) throws IOException {
      if (var1 != null && !var1.isBlank()) {
         JsonObject var4 = new JsonObject();
         var4.addProperty("keyword", var1.trim());
         var4.addProperty("type", 1);
         var4.addProperty("limit", Math.max(1, Math.min(var2, 100)));
         var4.addProperty("offset", Math.max(0, var3));
         JsonElement var5 = this.b("/api/search", var4);
         if (var5 != null && var5.isJsonObject()) {
            JsonObject var6 = var5.getAsJsonObject();
            JsonElement var7 = var6.get("songs");
            if (var7 == null) {
               var7 = var6.get("result");
            }

            return a(var7);
         } else {
            return List.of();
         }
      } else {
         return List.of();
      }
   }

   public RecoveredMusicAI a(long var1) throws IOException {
      JsonObject var3 = new JsonObject();
      var3.addProperty("id", String.valueOf(var1));
      JsonElement var4 = this.b("/api/getSongInfo", var3);
      return var4 != null && var4.isJsonObject() ? a(var4.getAsJsonObject()) : null;
   }

   public RecoveredMusicAJ a(long var1, String var3, String var4) throws IOException {
      String var5 = var3 != null && !var3.isBlank() ? var3.trim() : "lossless";
      JsonObject var6 = new JsonObject();
      var6.addProperty("id", String.valueOf(var1));
      var6.addProperty("level", var5);
      if ("sky".equalsIgnoreCase(var5) && var4 != null && !var4.isBlank()) {
         var6.addProperty("immerseType", var4.trim());
      }

      JsonElement var7 = this.b("/api/getSongUrl", var6);
      if (var7 != null && var7.isJsonObject()) {
         JsonObject var8 = var7.getAsJsonObject();
         long var9 = a(var8, "id", var1);
         String var11 = a(var8, "url", "");
         String var12 = "";
         JsonElement var13 = var8.get("cookie");
         if (var13 != null && var13.isJsonObject()) {
            var12 = a(var13.getAsJsonObject(), "label", "");
         }

         return new RecoveredMusicAJ(
            var9, var11, a(var8, "level", var5), a(var8, "br", 0), a(var8, "size", 0L), a(var8, "md5", ""), a(var8, "channelLayout", ""), var12
         );
      } else {
         throw new IOException("接口未返回播放地址");
      }
   }

   public RecoveredMusicAF b(long var1) throws IOException {
      JsonObject var3 = new JsonObject();
      var3.addProperty("id", String.valueOf(var1));
      JsonElement var4 = this.b("/api/getSongLyric", var3);
      if (var4 != null && var4.isJsonObject()) {
         JsonObject var5 = var4.getAsJsonObject();
         return new RecoveredMusicAF(a(var5, "lrc", ""), a(var5, "tlyric", ""), a(var5, "yrc", ""));
      } else {
         return RecoveredMusicAF.EMPTY;
      }
   }

   public RecoveredMusicAH c(long var1) throws IOException {
      JsonObject var3 = new JsonObject();
      var3.addProperty("id", String.valueOf(var1));
      JsonElement var4 = this.b("/api/playlist_trackall", var3);
      if (var4 == null) {
         throw new IOException("未找到该歌单");
      } else if (var4.isJsonArray()) {
         return new RecoveredMusicAH(RecoveredMusicAH.InnerA.PLAYLIST, var1, "", "", "", "", var4.getAsJsonArray().size(), a(var4));
      } else {
         JsonObject var5 = var4.getAsJsonObject();
         String var6 = "";
         JsonElement var7 = var5.get("creator");
         if (var7 != null && var7.isJsonObject()) {
            var6 = a(var7.getAsJsonObject(), "name", "");
         }

         List var8 = a(a(var5, "songs", "tracks", "list"));
         return new RecoveredMusicAH(
            RecoveredMusicAH.InnerA.PLAYLIST,
            a(var5, "id", var1),
            a(var5, "name", ""),
            b(var5, "coverImage", "coverImgUrl", "cover", "picUrl"),
            var6,
            a(var5, "description", ""),
            a(var5, "songCount", var8.size()),
            var8
         );
      }
   }

   public RecoveredMusicAH d(long var1) throws IOException {
      JsonObject var3 = new JsonObject();
      var3.addProperty("id", String.valueOf(var1));
      JsonElement var4 = this.b("/api/getAlbum", var3);
      if (var4 == null) {
         throw new IOException("未找到该专辑");
      } else if (var4.isJsonArray()) {
         return new RecoveredMusicAH(RecoveredMusicAH.InnerA.ALBUM, var1, "", "", "", "", var4.getAsJsonArray().size(), a(var4));
      } else {
         JsonObject var5 = var4.getAsJsonObject();
         String var6 = "";
         JsonElement var7 = var5.get("artist");
         if (var7 != null && var7.isJsonObject()) {
            var6 = a(var7.getAsJsonObject(), "name", "");
         }

         List var8 = a(a(var5, "songs", "tracks", "list"));
         return new RecoveredMusicAH(
            RecoveredMusicAH.InnerA.ALBUM,
            a(var5, "id", var1),
            a(var5, "name", ""),
            b(var5, "picUrl", "coverImage", "coverImgUrl", "cover"),
            var6,
            a(var5, "description", ""),
            var8.size(),
            var8
         );
      }
   }

   public List<RecoveredMusicAK> e() throws IOException {
      JsonElement var1 = this.b("/api/toplist", new JsonObject());
      ArrayList var2 = new ArrayList();
      if (var1 != null && var1.isJsonArray()) {
         for (JsonElement var4 : var1.getAsJsonArray()) {
            if (var4.isJsonObject()) {
               JsonObject var5 = var4.getAsJsonObject();
               var2.add(
                  new RecoveredMusicAK(
                     a(var5, "id", -1L), a(var5, "name", ""), b(var5, "coverImgUrl", "cover", "picUrl", "coverImage"), a(var5, "updateFrequency", "")
                  )
               );
            }
         }

         return var2;
      } else {
         return var2;
      }
   }

   public InputStream a(String var1, long var2) throws IOException {
      HttpURLConnection var4 = (HttpURLConnection)new URL(var1).openConnection();
      var4.setRequestMethod("GET");
      var4.setConnectTimeout(8000);
      var4.setReadTimeout(15000);
      var4.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36");
      var4.setInstanceFollowRedirects(true);
      if (var2 > 0L) {
         var4.setRequestProperty("Range", "bytes=" + var2 + "-");
      }

      int var5 = var4.getResponseCode();
      if (var5 != 200 && var5 != 206) {
         var4.disconnect();
         throw new IOException("音频直链返回 HTTP " + var5);
      } else {
         return var4.getInputStream();
      }
   }

   private static List<RecoveredMusicAI> a(JsonElement var0) {
      ArrayList var1 = new ArrayList();
      if (var0 != null && !var0.isJsonNull()) {
         if (var0.isJsonObject()) {
            JsonObject var2 = var0.getAsJsonObject();
            var0 = a(var2, "songs", "tracks", "list", "data");
            if (var0 == null) {
               return var1;
            }
         }

         if (!var0.isJsonArray()) {
            return var1;
         } else {
            JsonArray var6 = var0.getAsJsonArray();
            var1.ensureCapacity(var6.size());

            for (JsonElement var4 : var6) {
               if (var4.isJsonObject()) {
                  RecoveredMusicAI var5 = a(var4.getAsJsonObject());
                  if (var5 != null && var5.a() > 0L) {
                     var1.add(var5);
                  }
               }
            }

            return var1;
         }
      } else {
         return var1;
      }
   }

   private static RecoveredMusicAI a(JsonObject var0) {
      long var1 = a(var0, "id", -1L);
      if (var1 <= 0L) {
         return null;
      } else {
         String var3 = a(var0, "singer", "");
         if (var3.isBlank()) {
            JsonElement var4 = a(var0, new String[]{"artists", "ar"});
            if (var4 != null && var4.isJsonArray()) {
               StringBuilder var5 = new StringBuilder();

               for (JsonElement var7 : var4.getAsJsonArray()) {
                  if (var7.isJsonObject()) {
                     String var8 = a(var7.getAsJsonObject(), "name", "");
                     if (!var8.isBlank()) {
                        if (var5.length() > 0) {
                           var5.append('/');
                        }

                        var5.append(var8);
                     }
                  }
               }

               var3 = var5.toString();
            }
         }

         String var9 = a(var0, "album", "");
         if (var9.isBlank()) {
            JsonElement var10 = var0.get("al");
            if (var10 != null && var10.isJsonObject()) {
               var9 = a(var10.getAsJsonObject(), "name", "");
            }
         }

         String var11 = b(var0, "picimg", "picUrl", "cover", "coverImgUrl", "pic");
         long var12 = RecoveredMusicAI.a(a(var0, "duration", ""));
         if (var12 <= 0L) {
            var12 = a(var0, "dt", -1L);
         }

         return new RecoveredMusicAI(var1, a(var0, "name", ""), var3, var9, var11, var12, a(var0, "free", false), a(var0, "copyright", 0));
      }
   }

   private static JsonElement a(JsonObject var0, String... var1) {
      for (String var5 : var1) {
         JsonElement var6 = var0.get(var5);
         if (var6 != null && !var6.isJsonNull()) {
            return var6;
         }
      }

      return null;
   }

   private static String b(JsonObject var0, String... var1) {
      for (String var5 : var1) {
         JsonElement var6 = var0.get(var5);
         if (var6 != null && var6.isJsonPrimitive()) {
            String var7 = var6.getAsString();
            if (var7 != null && !var7.isBlank()) {
               return var7;
            }
         }
      }

      return "";
   }

   private static String a(JsonObject var0, String var1, String var2) {
      JsonElement var3 = var0.get(var1);
      if (var3 != null && !var3.isJsonNull() && var3.isJsonPrimitive()) {
         try {
            String var4 = var3.getAsString();
            return var4 == null ? var2 : var4;
         } catch (Throwable var5) {
            return var2;
         }
      } else {
         return var2;
      }
   }

   private static int a(JsonObject var0, String var1, int var2) {
      JsonElement var3 = var0.get(var1);
      if (var3 != null && !var3.isJsonNull() && var3.isJsonPrimitive()) {
         try {
            return var3.getAsInt();
         } catch (Throwable var5) {
            return var2;
         }
      } else {
         return var2;
      }
   }

   private static long a(JsonObject var0, String var1, long var2) {
      JsonElement var4 = var0.get(var1);
      if (var4 != null && !var4.isJsonNull() && var4.isJsonPrimitive()) {
         try {
            return var4.getAsLong();
         } catch (Throwable var6) {
            return var2;
         }
      } else {
         return var2;
      }
   }

   private static boolean a(JsonObject var0, String var1, boolean var2) {
      JsonElement var3 = var0.get(var1);
      if (var3 != null && !var3.isJsonNull() && var3.isJsonPrimitive()) {
         try {
            return var3.getAsBoolean();
         } catch (Throwable var5) {
            return var2;
         }
      } else {
         return var2;
      }
   }

   public static String[] b(String var0) {
      if (var0 != null && !var0.isBlank()) {
         String var1 = var0.trim();
         if (var1.matches("\\d{3,}")) {
            return new String[]{"song", var1};
         } else {
            String var2 = "";
            if (var1.contains("playlist")) {
               var2 = "playlist";
            } else if (var1.contains("album")) {
               var2 = "album";
            } else if (var1.contains("song")) {
               var2 = "song";
            }

            Matcher var3 = Pattern.compile("[?&#/]id=(\\d+)").matcher(var1);
            if (var3.find()) {
               return new String[]{var2, var3.group(1)};
            } else {
               var3 = Pattern.compile("/(song|album|playlist)/(\\d+)").matcher(var1);
               return var3.find() ? new String[]{var3.group(1).toLowerCase(Locale.ROOT), var3.group(2)} : new String[]{var2, ""};
            }
         }
      } else {
         return new String[]{"", ""};
      }
   }
}
