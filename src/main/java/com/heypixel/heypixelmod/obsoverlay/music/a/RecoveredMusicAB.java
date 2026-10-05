package com.heypixel.heypixelmod.obsoverlay.music.a;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.heypixel.heypixelmod.obsoverlay.b.RecoveredBB;
import com.heypixel.heypixelmod.obsoverlay.music.RecoveredMusicC;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class RecoveredMusicAB {
   private static final Logger LOGGER = LogManager.getLogger(RecoveredMusicAB.class);
   private static volatile RecoveredMusicAB instance;
   private final Map<String, Long> index = new ConcurrentHashMap<>();
   private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
   private final File indexFile;
   private final AtomicBoolean resolving = new AtomicBoolean(false);
   private volatile String currentKey = "";
   private volatile File currentFile;
   private volatile long currentSongId = -1L;

   private RecoveredMusicAB() {
      File var1 = RecoveredBB.clientFolder != null ? RecoveredBB.clientFolder : new File(System.getProperty("user.home"), "EixClient");
      this.indexFile = new File(var1, "cover_index.json");
      this.e();
   }

   public static RecoveredMusicAB a() {
      if (instance == null) {
         synchronized (RecoveredMusicAB.class) {
            if (instance == null) {
               instance = new RecoveredMusicAB();
            }
         }
      }

      return instance;
   }

   public File b() {
      File var1 = this.currentFile;
      return var1 != null && var1.isFile() ? var1 : null;
   }

   public long c() {
      return this.currentSongId;
   }

   public void a(String var1, String var2, long var3) {
      if (var1 != null && !var1.isBlank()) {
         RecoveredMusicC var5 = RecoveredMusicC.a();
         RecoveredMusicAI var6 = var5.o();
         if (var6 != null) {
            String var8 = "online:" + var6.a();
            if (!var8.equals(this.currentKey)) {
               this.currentKey = var8;
               this.currentSongId = var6.a();
               this.currentFile = RecoveredMusicAG.a().c(var6);
               RecoveredMusicAG.a().a(var6, var1x -> this.currentFile = var1x);
            }
         } else {
            String var7 = a(var1, var2);
            if (!var7.equals(this.currentKey)) {
               this.currentKey = var7;
               this.currentFile = null;
               this.currentSongId = var3;
               this.a(var1, var2, var3, var7);
            }
         }
      } else {
         this.d();
      }
   }

   public void d() {
      this.currentKey = "";
      this.currentFile = null;
      this.currentSongId = -1L;
   }

   private void a(String var1, String var2, long var3, String var5) {
      if (this.resolving.compareAndSet(false, true)) {
         Thread var6 = new Thread(() -> {
            try {
               RecoveredMusicAG var6x = RecoveredMusicAG.a();
               long var7 = var3;
               if (var3 <= 0L) {
                  Long var9 = this.index.get(var5);
                  if (var9 != null) {
                     var7 = var9;
                  } else {
                     RecoveredMusicAI var10 = var6x.a(var1, var2, -1L);
                     if (var10 == null) {
                        return;
                     }

                     var7 = var10.a();
                     this.index.put(var5, var7);
                     this.f();
                  }
               }

               if (var5.equals(this.currentKey)) {
                  RecoveredMusicAI var16 = var6x.b(var7);
                  if (var16 != null && !var16.e().isBlank()) {
                     File var17 = var6x.c().a("song_" + var7, var16.e());
                     if (var17 != null && var5.equals(this.currentKey)) {
                        this.currentFile = var17;
                        this.currentSongId = var7;
                     }
                  }
               }
            } catch (Throwable var14) {
               LOGGER.debug("解析封面失败: {} - {}", var1, var2, var14);
            } finally {
               this.resolving.set(false);
            }
         }, "EixClient-CoverResolve");
         var6.setDaemon(true);
         var6.start();
      }
   }

   private static String a(String var0, String var1) {
      return (var0.trim() + "\u0000" + (var1 == null ? "" : var1.trim())).toLowerCase(Locale.ROOT);
   }

   private void e() {
      if (this.indexFile.isFile()) {
         try (BufferedReader var1 = Files.newBufferedReader(this.indexFile.toPath(), StandardCharsets.UTF_8)) {
            Type var2 = (new TypeToken<Map<String, Long>>() {
            }).getType();
            Map var3 = this.gson.fromJson(var1, var2);
            if (var3 != null) {
               this.index.putAll(var3);
            }
         } catch (Throwable var6) {
            LOGGER.debug("读取封面索引失败", var6);
         }
      }
   }

   private void f() {
      try (BufferedWriter var1 = Files.newBufferedWriter(this.indexFile.toPath(), StandardCharsets.UTF_8)) {
         this.gson.toJson(this.index, var1);
      } catch (Throwable var6) {
         LOGGER.debug("保存封面索引失败", var6);
      }
   }
}
