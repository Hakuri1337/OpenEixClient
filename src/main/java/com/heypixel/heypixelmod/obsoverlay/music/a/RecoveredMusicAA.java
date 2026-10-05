package com.heypixel.heypixelmod.obsoverlay.music.a;

import com.heypixel.heypixelmod.obsoverlay.b.RecoveredBB;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class RecoveredMusicAA {
   private static final Logger LOGGER = LogManager.getLogger(RecoveredMusicAA.class);
   public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36";
   private static volatile RecoveredMusicAA instance;
   private final File folder;
   private final Map<String, File> ready = new ConcurrentHashMap<>();
   private final Set<String> inFlight = ConcurrentHashMap.newKeySet();

   private RecoveredMusicAA() {
      File var1 = RecoveredBB.clientFolder != null ? RecoveredBB.clientFolder : new File(System.getProperty("user.home"), "EixClient");
      this.folder = new File(var1, "covers");

      try {
         if (!this.folder.exists()) {
            this.folder.mkdirs();
         }
      } catch (Throwable var3) {
      }
   }

   public static RecoveredMusicAA a() {
      if (instance == null) {
         synchronized (RecoveredMusicAA.class) {
            if (instance == null) {
               instance = new RecoveredMusicAA();
            }
         }
      }

      return instance;
   }

   public File b() {
      return this.folder;
   }

   public File a(String var1) {
      if (var1 != null && !var1.isBlank()) {
         File var2 = this.ready.get(var1);
         if (var2 != null && var2.isFile()) {
            return var2;
         } else {
            File var3 = this.b(var1);
            if (var3.isFile() && var3.length() > 0L) {
               this.ready.put(var1, var3);
               return var3;
            } else {
               return null;
            }
         }
      } else {
         return null;
      }
   }

   public void a(String var1, String var2, Consumer<File> var3) {
      if (var1 != null && !var1.isBlank() && var2 != null && !var2.isBlank()) {
         File var4 = this.a(var1);
         if (var4 != null) {
            if (var3 != null) {
               var3.accept(var4);
            }
         } else if (this.inFlight.add(var1)) {
            Thread var5 = new Thread(() -> {
               try {
                  File var4x = this.b(var1);
                  if (this.a(var2, var4x)) {
                     this.ready.put(var1, var4x);
                     if (var3 != null) {
                        var3.accept(var4x);
                     }
                  }
               } catch (Throwable var8) {
                  LOGGER.debug("下载封面失败: {}", var2, var8);
               } finally {
                  this.inFlight.remove(var1);
               }
            }, "EixClient-Cover");
            var5.setDaemon(true);
            var5.start();
         }
      }
   }

   public File a(String var1, String var2) {
      if (var1 != null && !var1.isBlank() && var2 != null && !var2.isBlank()) {
         File var3 = this.a(var1);
         if (var3 != null) {
            return var3;
         } else {
            File var4 = this.b(var1);

            try {
               if (this.a(var2, var4)) {
                  this.ready.put(var1, var4);
                  return var4;
               }
            } catch (Throwable var6) {
               LOGGER.debug("下载封面失败: {}", var2, var6);
            }

            return null;
         }
      } else {
         return null;
      }
   }

   private boolean a(String var1, File var2) throws Exception {
      HttpURLConnection var3 = null;

      boolean var16;
      try {
         var3 = (HttpURLConnection)new URL(var1).openConnection();
         var3.setRequestMethod("GET");
         var3.setConnectTimeout(8000);
         var3.setReadTimeout(15000);
         var3.setRequestProperty(
            "User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"
         );
         var3.setInstanceFollowRedirects(true);
         if (var3.getResponseCode() != 200) {
            return false;
         }

         File var4 = new File(var2.getParentFile(), var2.getName() + ".part");

         try (InputStream var5 = var3.getInputStream()) {
            Files.copy(var5, var4.toPath(), StandardCopyOption.REPLACE_EXISTING);
         }

         if (var4.length() > 0L) {
            Files.move(var4.toPath(), var2.toPath(), StandardCopyOption.REPLACE_EXISTING);
            return true;
         }

         var4.delete();
         var16 = false;
      } finally {
         if (var3 != null) {
            var3.disconnect();
         }
      }

      return var16;
   }

   private File b(String var1) {
      String var2 = var1.replaceAll("[^A-Za-z0-9._-]", "_");
      return new File(this.folder, var2 + ".jpg");
   }
}
