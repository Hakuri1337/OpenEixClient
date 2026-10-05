package com.heypixel.heypixelmod.obsoverlay.music.nowplaying;

import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsV;
import java.util.Locale;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public final class RecoveredMusicNowplayingC {
   private static volatile RecoveredMusicNowplayingC instance;
   private final RecoveredMusicNowplayingE smtc = new RecoveredMusicNowplayingE();
   private volatile RecoveredMusicNowplayingB cached = RecoveredMusicNowplayingB.NONE;
   private volatile boolean running;
   private volatile boolean neteaseEnabled = true;
   private volatile boolean otherPlayersEnabled = true;
   private volatile boolean smtcEnabled = false;
   private volatile ScheduledFuture<?> task;
   private volatile String status = "未启动";
   private volatile String lastSongName = "";
   private final RecoveredMusicNowplayingD clock = new RecoveredMusicNowplayingD();

   private RecoveredMusicNowplayingC() {
   }

   public static RecoveredMusicNowplayingC a() {
      if (instance == null) {
         synchronized (RecoveredMusicNowplayingC.class) {
            if (instance == null) {
               instance = new RecoveredMusicNowplayingC();
            }
         }
      }

      return instance;
   }

   public synchronized void b() {
      if (!this.running) {
         this.running = true;
         if (this.smtcEnabled) {
            this.smtc.g();
         }

         this.task = RecoveredUtilsV.b(this::l, 0L, 1000L, TimeUnit.MILLISECONDS);
      }
   }

   public synchronized void c() {
      this.running = false;
      ScheduledFuture var1 = this.task;
      this.task = null;
      if (var1 != null) {
         var1.cancel(false);
      }

      this.smtc.h();
      this.cached = RecoveredMusicNowplayingB.NONE;
      this.status = "已停止";
      this.n();
   }

   public boolean d() {
      return this.running;
   }

   public void e() {
      if (!this.running) {
         this.b();
      }
   }

   public void a(boolean var1) {
      this.smtcEnabled = var1;
      if (this.running) {
         if (var1) {
            this.smtc.g();
         } else {
            this.smtc.h();
         }
      }
   }

   public void b(boolean var1) {
      this.neteaseEnabled = var1;
   }

   public void c(boolean var1) {
      this.otherPlayersEnabled = var1;
   }

   public boolean f() {
      return this.smtcEnabled;
   }

   public boolean g() {
      return this.neteaseEnabled;
   }

   public boolean h() {
      return this.otherPlayersEnabled;
   }

   public RecoveredMusicNowplayingB i() {
      return this.cached;
   }

   public String j() {
      if (!this.running) {
         return "未启动";
      } else {
         StringBuilder var1 = new StringBuilder();
         boolean var2 = WindowTitleBridge.a() && WindowTitleBridge.c();
         if (!this.neteaseEnabled) {
            var1.append("网易云识别: 已关闭");
         } else if (var2) {
            var1.append("网易云已运行");
            int var3 = RecoveredMusicNowplayingA.c();
            if (var3 > 0) {
               var1.append(" · 本地队列 ").append(var3).append(" 首（可直取歌曲 ID）");
            } else {
               var1.append(" · 未读到本地播放队列，将回退到搜索");
            }
         } else {
            var1.append("未检测到网易云进程");
         }

         if (this.smtcEnabled) {
            var1.append("\nSMTC: ");
            if (this.smtc.a() && this.smtc.c()) {
               var1.append("已连接");
            } else if (!this.smtc.b()) {
               var1.append("不可用").append(this.smtc.d() == null ? "" : "（" + this.smtc.d() + "）");
            } else {
               var1.append("启动中");
            }
         }

         RecoveredMusicNowplayingB var4 = this.cached;
         if (!var4.k()) {
            var1.append("\n当前: ").append(var4.o()).append(" [").append(var4.i()).append("]");
            if (var4.j() > 0L) {
               var1.append(" id=").append(var4.j());
            }

            var1.append(var4.g() ? " 播放中" : " 已暂停");
         } else {
            var1.append("\n当前: 未检测到正在播放的歌曲");
         }

         return var1.toString();
      }
   }

   public String k() {
      return this.lastSongName;
   }

   private void l() {
      if (this.running) {
         try {
            RecoveredMusicNowplayingB var1 = this.m();
            this.cached = var1.k() ? RecoveredMusicNowplayingB.NONE : this.a(var1);
            this.lastSongName = this.cached.b();
         } catch (Throwable var2) {
         }
      }
   }

   private RecoveredMusicNowplayingB m() {
      RecoveredMusicNowplayingB var1 = this.smtcEnabled ? this.smtc.e() : RecoveredMusicNowplayingB.NONE;
      if (this.neteaseEnabled || this.otherPlayersEnabled) {
         for (WindowTitleBridge.InnerB var3 : WindowTitleBridge.b()) {
            if (var3.e()) {
               if (this.neteaseEnabled) {
                  return this.a(var3, var1);
               }
            } else if (this.otherPlayersEnabled) {
               RecoveredMusicNowplayingB var4 = new RecoveredMusicNowplayingB(
                  var3.a(), var3.b(), var3.c(), "", 0L, -1L, true, System.currentTimeMillis(), "窗口标题"
               );
               if (!var1.k() && var1.a().toLowerCase(Locale.ROOT).contains(var3.a().replace(".exe", ""))) {
                  var4 = var4.a(var1.g()).c(var1.f()).a(var1.e());
               }

               return var4;
            }
         }
      }

      return !var1.k() ? var1 : RecoveredMusicNowplayingB.NONE;
   }

   private RecoveredMusicNowplayingB a(WindowTitleBridge.InnerB var1, RecoveredMusicNowplayingB var2) {
      String var3 = var1.b();
      String var4 = var1.c();
      long var5 = -1L;
      long var7 = -1L;
      RecoveredMusicNowplayingA.InnerA var9 = RecoveredMusicNowplayingA.a(var3, var4);
      if (var9 != null) {
         var5 = var9.a();
         var7 = var9.d();
         if (var4.isEmpty() && !var9.c().isEmpty()) {
            var4 = var9.c();
         }

         if (!var9.b().isEmpty()) {
            var3 = var9.b();
         }
      }

      boolean var10 = true;
      if (!var2.k()) {
         String var11 = var2.a().toLowerCase(Locale.ROOT);
         boolean var12 = var11.contains("cloudmusic") || var11.contains("netease");
         if (var12) {
            var10 = var2.g();
         }

         if (var7 <= 0L) {
            var7 = var2.f();
         }
      }

      return new RecoveredMusicNowplayingB("cloudmusic.exe", var3, var4, "", 0L, var7, var10, System.currentTimeMillis(), var5 > 0L ? "网易云本地" : "网易云标题", var5);
   }

   private void n() {
      this.clock.c();
   }

   private RecoveredMusicNowplayingB a(RecoveredMusicNowplayingB var1) {
      return this.clock.a(var1);
   }
}
