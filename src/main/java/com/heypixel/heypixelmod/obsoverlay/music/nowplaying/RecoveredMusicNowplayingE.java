package com.heypixel.heypixelmod.obsoverlay.music.nowplaying;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsV;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class RecoveredMusicNowplayingE {
   private static final Logger LOGGER = LogManager.getLogger(RecoveredMusicNowplayingE.class);
   private static final String SCRIPT = String.join(
      "\n",
      "$ErrorActionPreference='SilentlyContinue'",
      "[Console]::OutputEncoding=[System.Text.Encoding]::UTF8",
      "$OutputEncoding=[System.Text.Encoding]::UTF8",
      "Add-Type -AssemblyName System.Runtime.WindowsRuntime",
      "$g=([System.WindowsRuntimeSystemExtensions].GetMethods()|Where-Object{$_.Name -eq 'AsTask' -and $_.GetParameters().Count -eq 1 -and $_.GetParameters()[0].ParameterType.Name -eq 'IAsyncOperation`1'})[0]",
      "function Await($op,$t){ $k=$g.MakeGenericMethod($t); $tk=$k.Invoke($null,@($op)); $tk.Wait(-1)|Out-Null; return $tk.Result }",
      "$mt=[Windows.Media.Control.GlobalSystemMediaTransportControlsSessionManager,Windows.Media.Control,ContentType=WindowsRuntime]",
      "$pt=[Windows.Media.Control.GlobalSystemMediaTransportControlsSessionMediaProperties,Windows.Media.Control,ContentType=WindowsRuntime]",
      "$mgr=$null",
      "$sentReady=$false",
      "while($true){",
      "  if($mgr -eq $null){",
      "    try{ $mgr=Await ($mt::RequestAsync()) $mt }catch{ $mgr=$null }",
      "    if($mgr -eq $null){",
      "      [Console]::Out.WriteLine('{\"state\":\"no-manager\"}'); [Console]::Out.Flush()",
      "      Start-Sleep -Milliseconds 2000; continue",
      "    }",
      "  }",
      "  if(-not $sentReady){ [Console]::Out.WriteLine('{\"state\":\"ready\"}'); [Console]::Out.Flush(); $sentReady=$true }",
      "  $o=$null",
      "  try{",
      "    $s=$mgr.GetCurrentSession()",
      "    if($s -ne $null){",
      "      $p=Await ($s.TryGetMediaPropertiesAsync()) $pt",
      "      $pi=$s.GetPlaybackInfo()",
      "      $tl=$s.GetTimelineProperties()",
      "      $pos=[int]$tl.Position.TotalMilliseconds",
      "      $playing=($pi.PlaybackStatus -eq 4)",
      "      if($playing){ $pos=[int]([DateTimeOffset]::Now - $tl.LastUpdatedTime).TotalMilliseconds + $pos }",
      "      $o=[ordered]@{app=$s.SourceAppUserModelId;title=$p.Title;artist=$p.Artist;album=$p.AlbumTitle;pos=$pos;dur=[int]$tl.EndTime.TotalMilliseconds;playing=$playing}",
      "    }",
      "  }catch{ $mgr=$null }",
      "  if($o -ne $null){ $json=$o|ConvertTo-Json -Compress; $isPlaying=$o.playing } else { $json='{\"state\":\"idle\"}'; $isPlaying=$false }",
      "  [Console]::Out.WriteLine($json)",
      "  [Console]::Out.Flush()",
      "  if($isPlaying){ Start-Sleep -Milliseconds 400 } else { Start-Sleep -Milliseconds 1200 }",
      "}"
   );
   private final AtomicBoolean running = new AtomicBoolean(false);
   private final AtomicBoolean supported = new AtomicBoolean(true);
   private volatile Process process;
   private volatile Thread readerThread;
   private volatile Thread watchdogThread;
   private volatile RecoveredMusicNowplayingB latest = RecoveredMusicNowplayingB.NONE;
   private volatile long lastUpdateAt;
   private volatile boolean ready;
   private volatile String lastError;
   private volatile int restartCount;

   public boolean a() {
      return this.running.get();
   }

   public boolean b() {
      return this.supported.get();
   }

   public boolean c() {
      return this.ready;
   }

   public String d() {
      return this.lastError;
   }

   public RecoveredMusicNowplayingB e() {
      return this.latest;
   }

   public long f() {
      return this.lastUpdateAt;
   }

   public synchronized void g() {
      if (!this.running.get()) {
         this.running.set(true);
         this.restartCount = 0;
         RecoveredUtilsV.a(() -> {
            if (this.running.get()) {
               if (!i()) {
                  this.supported.set(false);
                  this.lastError = "未找到 powershell.exe";
                  LOGGER.warn("SMTC 不可用: {}", this.lastError);
                  this.running.set(false);
               } else if (this.running.get()) {
                  this.j();
                  if (this.running.get()) {
                     this.k();
                  }
               }
            }
         });
      }
   }

   public synchronized void h() {
      this.running.set(false);
      this.ready = false;
      this.l();
      Thread var1 = this.watchdogThread;
      this.watchdogThread = null;
      if (var1 != null) {
         var1.interrupt();
      }
   }

   private static boolean i() {
      try {
         Process var0 = new ProcessBuilder("powershell.exe", "-NoProfile", "-NonInteractive", "-Command", "exit 0").redirectErrorStream(true).start();
         boolean var1 = var0.waitFor(6L, TimeUnit.SECONDS);
         if (!var1) {
            var0.destroyForcibly();
            return false;
         } else {
            return var0.exitValue() == 0;
         }
      } catch (Throwable var2) {
         return false;
      }
   }

   private void j() {
      try {
         String var1 = Base64.getEncoder().encodeToString(SCRIPT.getBytes(StandardCharsets.UTF_16LE));
         ProcessBuilder var2 = new ProcessBuilder("powershell.exe", "-NoProfile", "-NonInteractive", "-ExecutionPolicy", "Bypass", "-EncodedCommand", var1);
         var2.redirectErrorStream(false);
         Process var3 = var2.start();
         this.process = var3;
         Thread var4 = new Thread(() -> this.a(var3), "EixClient-SMTC-Reader");
         var4.setDaemon(true);
         this.readerThread = var4;
         var4.start();
         Thread var5 = new Thread(() -> this.b(var3), "EixClient-SMTC-Err");
         var5.setDaemon(true);
         var5.start();
      } catch (Throwable var6) {
         this.lastError = "启动 SMTC 桥失败: " + var6.getMessage();
         LOGGER.warn(this.lastError, var6);
         this.supported.set(false);
         this.running.set(false);
      }
   }

   private void a(Process var1) {
      String var3;
      try (BufferedReader var2 = new BufferedReader(new InputStreamReader(var1.getInputStream(), StandardCharsets.UTF_8))) {
         while ((var3 = var2.readLine()) != null && this.running.get() && this.process == var1) {
            this.a(var3);
         }
      } catch (Throwable var7) {
         if (this.running.get()) {
            this.lastError = "读取 SMTC 输出失败: " + var7.getMessage();
         }
      }
   }

   private void b(Process var1) {
      try (BufferedReader var2 = new BufferedReader(new InputStreamReader(var1.getErrorStream(), StandardCharsets.UTF_8))) {
         int var4 = 0;

         String var3;
         while ((var3 = var2.readLine()) != null && var4 < 40) {
            var4++;
            if (!var3.startsWith("#< CLIXML") && !var3.isBlank()) {
               LOGGER.debug("[SMTC] {}", var3);
            }
         }
      } catch (Throwable var7) {
      }
   }

   private void a(String var1) {
      String var2 = var1.trim();
      if (!var2.isEmpty()) {
         try {
            JsonObject var3 = JsonParser.parseString(var2).getAsJsonObject();
            if (var3.has("state")) {
               String var4 = var3.get("state").getAsString();
               if ("ready".equals(var4)) {
                  this.ready = true;
               } else if ("no-manager".equals(var4)) {
                  this.supported.set(false);
                  this.lastError = "系统媒体控制不可用（SMTC 初始化失败）";
               }

               if (!"ready".equals(var4)) {
                  this.latest = RecoveredMusicNowplayingB.NONE;
                  this.lastUpdateAt = System.currentTimeMillis();
               }

               return;
            }

            this.ready = true;
            this.latest = new RecoveredMusicNowplayingB(
               a(var3, "app"),
               a(var3, "title"),
               a(var3, "artist"),
               a(var3, "album"),
               b(var3, "pos"),
               b(var3, "dur"),
               var3.has("playing") && var3.get("playing").getAsBoolean(),
               System.currentTimeMillis(),
               "SMTC"
            );
            this.lastUpdateAt = System.currentTimeMillis();
         } catch (Throwable var5) {
            LOGGER.debug("解析 SMTC 输出失败: {}", var2);
         }
      }
   }

   private static String a(JsonObject var0, String var1) {
      try {
         return var0.has(var1) && !var0.get(var1).isJsonNull() ? var0.get(var1).getAsString() : "";
      } catch (Throwable var3) {
         return "";
      }
   }

   private static long b(JsonObject var0, String var1) {
      try {
         return var0.has(var1) && !var0.get(var1).isJsonNull() ? var0.get(var1).getAsLong() : -1L;
      } catch (Throwable var3) {
         return -1L;
      }
   }

   private void k() {
      Thread var1 = new Thread(() -> {
         while (this.running.get()) {
            try {
               Thread.sleep(2000L);
            } catch (InterruptedException var4) {
               return;
            }

            if (!this.running.get()) {
               return;
            }

            Process var1x = this.process;
            if (var1x == null || !var1x.isAlive()) {
               if (this.restartCount >= 5) {
                  this.supported.set(false);
                  this.lastError = "SMTC 桥反复退出，已停止重试";
                  this.running.set(false);
                  return;
               }

               this.restartCount++;
               this.l();

               try {
                  Thread.sleep(2200L);
               } catch (InterruptedException var3) {
                  return;
               }

               if (this.running.get()) {
                  this.j();
               }
            }
         }
      }, "EixClient-SMTC-Watchdog");
      var1.setDaemon(true);
      this.watchdogThread = var1;
      var1.start();
   }

   private void l() {
      Process var1 = this.process;
      this.process = null;
      if (var1 != null) {
         try {
            var1.destroy();
         } catch (Throwable var5) {
            try {
               var1.destroyForcibly();
            } catch (Throwable var4) {
            }

            return;
         }

         RecoveredUtilsV.a(() -> {
            try {
               if (var1.isAlive()) {
                  var1.destroyForcibly();
               }
            } catch (Throwable var2) {
            }
         }, 1500L, TimeUnit.MILLISECONDS);
      }
   }
}
