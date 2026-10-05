package com.heypixel.heypixelmod.obsoverlay.music.a;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.heypixel.heypixelmod.obsoverlay.b.RecoveredBB;
import com.heypixel.heypixelmod.obsoverlay.music.RecoveredMusicC;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class RecoveredMusicAG {
   private static final Logger LOGGER = LogManager.getLogger(RecoveredMusicAG.class);
   private static volatile RecoveredMusicAG instance;
   private final RecoveredMusicAD api = new RecoveredMusicAD();
   private final RecoveredMusicAA coverCache = RecoveredMusicAA.a();
   private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
   private final File configFile;
   private volatile String level = "lossless";
   private volatile String immerseType = "";
   private volatile boolean preferLocalIp = true;
   private final List<RecoveredMusicAI> lastResult = new ArrayList<>();
   private volatile String lastResultTitle = "";

   private RecoveredMusicAG() {
      File var1 = RecoveredBB.clientFolder != null ? RecoveredBB.clientFolder : new File(System.getProperty("user.home"), "EixClient");
      this.configFile = new File(var1, "music_api.json");
      this.l();
      this.api.a("https://nextmusic.toubiec.cn");
      this.api.a().a(true);
   }

   public static RecoveredMusicAG a() {
      if (instance == null) {
         synchronized (RecoveredMusicAG.class) {
            if (instance == null) {
               instance = new RecoveredMusicAG();
            }
         }
      }

      return instance;
   }

   public RecoveredMusicAD b() {
      return this.api;
   }

   public RecoveredMusicAA c() {
      return this.coverCache;
   }

   private void l() {
      if (this.configFile.isFile()) {
         try {
            try (BufferedReader var1 = Files.newBufferedReader(this.configFile.toPath(), StandardCharsets.UTF_8)) {
               RecoveredMusicAG.InnerA var2 = this.gson.fromJson(var1, RecoveredMusicAG.InnerA.class);
               if (var2 != null) {
                  if (var2.baseUrl != null && !var2.baseUrl.isBlank()) {
                     this.api.a(var2.baseUrl);
                  }

                  if (var2.level != null && !var2.level.isBlank()) {
                     this.level = var2.level;
                  }

                  if (var2.immerseType != null) {
                     this.immerseType = var2.immerseType;
                  }

                  this.preferLocalIp = var2.preferLocalIp;
                  if (var2.ipPool != null) {
                     for (String var4 : var2.ipPool) {
                        this.api.a().b(var4);
                     }
                  }

                  return;
               }
            }
         } catch (Throwable var7) {
            LOGGER.warn("读取音乐接口配置失败", var7);
         }
      }
   }

   private void m() {
      try {
         RecoveredMusicAG.InnerA var1 = new RecoveredMusicAG.InnerA();
         var1.baseUrl = this.api.b();
         var1.level = this.level;
         var1.immerseType = this.immerseType;
         var1.preferLocalIp = this.preferLocalIp;
         var1.ipPool = this.api.a().c();

         try (BufferedWriter var2 = Files.newBufferedWriter(this.configFile.toPath(), StandardCharsets.UTF_8)) {
            this.gson.toJson(var1, var2);
         }
      } catch (Throwable var7) {
         LOGGER.warn("保存音乐接口配置失败", var7);
      }
   }

   public String d() {
      return this.level;
   }

   public String e() {
      for (String[] var4 : RecoveredMusicAD.LEVELS) {
         if (var4[0].equalsIgnoreCase(this.level)) {
            return var4[1];
         }
      }

      return this.level;
   }

   public boolean a(String var1) {
      if (var1 == null) {
         return false;
      } else {
         String var2 = var1.trim().toLowerCase(Locale.ROOT);

         for (String[] var6 : RecoveredMusicAD.LEVELS) {
            if (var6[0].equalsIgnoreCase(var2) || var6[1].contains(var1.trim())) {
               this.level = var6[0];
               this.m();
               return true;
            }
         }

         return false;
      }
   }

   public String f() {
      return this.immerseType;
   }

   public void b(String var1) {
      this.immerseType = var1 == null ? "" : var1.trim();
      this.m();
   }

   public boolean g() {
      return this.preferLocalIp;
   }

   public void a(boolean var1) {
      this.preferLocalIp = var1;
      if (var1) {
         this.api.a().a(null);
      }

      this.m();
   }

   public void c(String var1) {
      this.api.a().b(var1);
      this.m();
   }

   public void h() {
      this.api.a().d();
      this.m();
   }

   public List<RecoveredMusicAI> d(String var1) throws Exception {
      this.n();
      List var2 = this.api.a(var1, 30, 0);
      this.a("搜索：" + var1, var2);
      return var2;
   }

   public RecoveredMusicAH e(String var1) throws Exception {
      this.n();
      String[] var2 = RecoveredMusicAD.b(var1);
      String var3 = var2[0];
      String var4 = var2[1];
      if (var4.isBlank()) {
         throw new IllegalArgumentException("无法从这段内容里识别出歌单/专辑 ID：" + var1);
      } else {
         long var5 = Long.parseLong(var4);
         RecoveredMusicAH var7;
         if ("album".equals(var3)) {
            var7 = this.api.d(var5);
         } else if ("song".equals(var3)) {
            RecoveredMusicAI var8 = this.api.a(var5);
            List var9 = var8 == null ? List.of() : List.of(var8);
            var7 = new RecoveredMusicAH(
               RecoveredMusicAH.InnerA.PLAYLIST,
               var5,
               var8 == null ? "" : var8.b(),
               var8 == null ? "" : var8.e(),
               var8 == null ? "" : var8.c(),
               "",
               var9.size(),
               var9
            );
            var3 = "playlist";
         } else {
            var7 = this.api.c(var5);
         }

         this.a(var7.a().a() + "：" + var7.c(), var7.h());
         return var7;
      }
   }

   public List<RecoveredMusicAK> i() throws Exception {
      this.n();
      return this.api.e();
   }

   public RecoveredMusicAH a(long var1) throws Exception {
      this.n();
      RecoveredMusicAH var3 = this.api.c(var1);
      this.a("榜单：" + var3.c(), var3.h());
      return var3;
   }

   public RecoveredMusicAI b(long var1) throws Exception {
      this.n();
      return this.api.a(var1);
   }

   public RecoveredMusicAJ a(RecoveredMusicAI var1) throws Exception {
      this.n();
      return this.api.a(var1.a(), this.level, this.immerseType);
   }

   public RecoveredMusicAJ c(long var1) throws Exception {
      this.n();
      return this.api.a(var1, this.level, this.immerseType);
   }

   public InputStream f(String var1) throws Exception {
      return this.api.a(var1, 0L);
   }

   public static String b(RecoveredMusicAI var0) {
      return var0 == null ? "" : "song_" + var0.a();
   }

   public File c(RecoveredMusicAI var1) {
      if (var1 == null) {
         return null;
      } else {
         String var2 = b(var1);
         File var3 = this.coverCache.a(var2);
         if (var3 != null) {
            return var3;
         } else {
            if (!var1.e().isBlank()) {
               this.coverCache.a(var2, var1.e(), null);
            }

            return null;
         }
      }
   }

   public void a(RecoveredMusicAI var1, Consumer<File> var2) {
      if (var1 != null && !var1.e().isBlank()) {
         this.coverCache.a(b(var1), var1.e(), var2);
      }
   }

   public File a(RecoveredMusicAI var1, RecoveredMusicAG.InnerB var2) throws Exception {
      RecoveredMusicAJ var3 = this.a(var1);
      if (var3.i()) {
         throw new IllegalStateException("该歌曲没有可用的播放地址（版权受限）");
      } else {
         RecoveredMusicC var4 = RecoveredMusicC.a();
         File var5 = var4.d();
         if (var5 == null) {
            File var6 = RecoveredBB.clientFolder != null ? RecoveredBB.clientFolder : new File(System.getProperty("user.home"), "EixClient");
            var5 = new File(var6, "music");
         }

         if (!var5.exists()) {
            var5.mkdirs();
         }

         String var22 = h(var1.c() + " - " + var1.b()) + "." + var3.j();
         File var7 = new File(var5, var22);
         File var8 = new File(var5, var22 + ".part");
         long var9 = var3.e() > 0L ? var3.e() : -1L;
         long var11 = 0L;

         try (
            InputStream var13 = this.api.a(var3.b(), 0L);
            OutputStream var14 = Files.newOutputStream(var8.toPath());
         ) {
            byte[] var15 = new byte[65536];

            int var16;
            while ((var16 = var13.read(var15)) > 0) {
               var14.write(var15, 0, var16);
               var11 += (long)var16;
               if (var2 != null) {
                  var2.a(var11, var9);
               }
            }
         } catch (Throwable var21) {
            var8.delete();
            if (var2 != null) {
               var2.a(var21.getMessage() == null ? "下载失败" : var21.getMessage());
            }

            throw var21;
         }

         if (var11 <= 0L) {
            var8.delete();
            throw new IllegalStateException("下载内容为空");
         } else {
            Files.move(var8.toPath(), var7.toPath(), StandardCopyOption.REPLACE_EXISTING);
            this.a(var1, (RecoveredMusicAG.InnerB)null);
            if (var2 != null) {
               var2.a(var7);
            }

            return var7;
         }
      }
   }

   public RecoveredMusicAI a(String var1, String var2, long var3) {
      if (var1 != null && !var1.isBlank()) {
         try {
            this.n();
            String var5 = var2 != null && !var2.isBlank() ? var1 + " " + var2 : var1;
            List var6 = this.api.a(var5, 20, 0);
            return a(var6, var1, var2, var3);
         } catch (Throwable var7) {
            LOGGER.debug("按歌名匹配歌曲失败: {} - {}", var1, var2, var7);
            return null;
         }
      } else {
         return null;
      }
   }

   public RecoveredMusicAF b(String var1, String var2, long var3) {
      try {
         RecoveredMusicAI var5 = this.a(var1, var2, var3);
         if (var5 == null) {
            return null;
         } else {
            RecoveredMusicAF var6 = this.api.b(var5.a());
            return var6.d() ? null : var6;
         }
      } catch (Throwable var7) {
         LOGGER.debug("按歌名获取歌词失败: {} - {}", var1, var2, var7);
         return null;
      }
   }

   public RecoveredMusicAF d(long var1) {
      try {
         this.n();
         RecoveredMusicAF var3 = this.api.b(var1);
         return var3.d() ? null : var3;
      } catch (Throwable var4) {
         LOGGER.debug("按 ID 获取歌词失败: {}", var1, var4);
         return null;
      }
   }

   private static RecoveredMusicAI a(List<RecoveredMusicAI> var0, String var1, String var2, long var3) {
      RecoveredMusicAI var5 = null;
      int var6 = Integer.MIN_VALUE;
      String var7 = g(var1);
      String var8 = var2 == null ? "" : var2.toLowerCase(Locale.ROOT);

      for (RecoveredMusicAI var10 : var0) {
         int var11 = 0;
         String var12 = g(var10.b());
         if (var12.equals(var7)) {
            var11 += 100;
         } else if (var12.contains(var7) || var7.contains(var12)) {
            var11 += 50;
         }

         if (!var8.isBlank()) {
            String var13 = var10.c().toLowerCase(Locale.ROOT);
            boolean var14 = false;

            for (String var18 : var8.split("[/、,，&;；]")) {
               String var19 = var18.trim();
               if (!var19.isEmpty() && var13.contains(var19)) {
                  var14 = true;
                  break;
               }
            }

            var11 += var14 ? 50 : -30;
         }

         for (String var23 : new String[]{"翻唱", "cover", "钢琴版", "纯音乐", "伴奏", "remix", "片段", "剪辑"}) {
            if (var12.contains(var23)) {
               var11 -= 35;
            }
         }

         if (var3 > 0L && var10.f() > 0L && Math.abs(var3 - var10.f()) <= 3000L) {
            var11 += 30;
         }

         if (var11 > var6) {
            var6 = var11;
            var5 = var10;
         }
      }

      return var6 >= 40 ? var5 : null;
   }

   private static String g(String var0) {
      return var0 == null ? "" : var0.toLowerCase(Locale.ROOT).replaceAll("[（(【\\[][^）)】\\]]*[）)】\\]]", "").trim();
   }

   private void a(String var1, List<RecoveredMusicAI> var2) {
      synchronized (this.lastResult) {
         this.lastResult.clear();
         if (var2 != null) {
            this.lastResult.addAll(var2);
         }
      }

      this.lastResultTitle = var1 == null ? "" : var1;
   }

   public List<RecoveredMusicAI> j() {
      synchronized (this.lastResult) {
         return List.copyOf(this.lastResult);
      }
   }

   public String k() {
      return this.lastResultTitle;
   }

   private void n() {
      if (this.preferLocalIp) {
         this.api.d();
      }
   }

   private static String h(String var0) {
      String var1 = var0 == null ? "" : var0;
      var1 = var1.replaceAll("[\\\\/:*?\"<>|]", "_").trim();
      if (var1.isEmpty()) {
         var1 = "unknown";
      }

      return var1.length() > 120 ? var1.substring(0, 120) : var1;
   }

   private static class InnerA {
      String baseUrl = "https://nextmusic.toubiec.cn";
      String level = "lossless";
      String immerseType = "";
      boolean preferLocalIp = true;
      List<String> ipPool = new ArrayList<>();
   }

   public interface InnerB {
      void a(long var1, long var3);

      default void a(File var1) {
      }

      default void a(String var1) {
      }
   }
}
