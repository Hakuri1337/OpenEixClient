package com.heypixel.heypixelmod.obsoverlay.music;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.b.RecoveredBB;
import com.heypixel.heypixelmod.obsoverlay.c.a.RecoveredCAA;
import com.heypixel.heypixelmod.obsoverlay.c.a.RecoveredCAB;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplJ;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.b.MusicPlayerModule;
import com.heypixel.heypixelmod.obsoverlay.music.a.RecoveredMusicAG;
import com.heypixel.heypixelmod.obsoverlay.music.a.RecoveredMusicAI;
import com.heypixel.heypixelmod.obsoverlay.music.a.RecoveredMusicAJ;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsV;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RecoveredMusicC {
   private static final Logger log = LogManager.getLogger(RecoveredMusicC.class);
   private static volatile RecoveredMusicC instance;
   private final RecoveredMusicB engine = new RecoveredMusicB();
   private final List<RecoveredMusicD> playlist = new CopyOnWriteArrayList<>();
   private final Deque<Integer> history = new ArrayDeque<>();
   private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
   private final List<RecoveredMusicAI> onlineQueue = new CopyOnWriteArrayList<>();
   private volatile int onlineIndex = -1;
   private volatile RecoveredMusicAI currentOnlineSong;
   private final File stateFile;
   private volatile File musicFolder;
   private volatile int currentIndex = -1;
   private volatile boolean shuffle = false;
   private volatile RecoveredMusicC.InnerA loopMode = RecoveredMusicC.InnerA.ALL;
   private volatile boolean scanning = false;
   private volatile boolean loaded = false;

   private RecoveredMusicC() {
      File var1 = RecoveredBB.clientFolder != null ? RecoveredBB.clientFolder : new File(System.getProperty("user.home"), "EixClient");
      this.musicFolder = new File(var1, "music");
      this.stateFile = new File(var1, "music_player.json");
      this.engine.a(this::x);
   }

   public static RecoveredMusicC a() {
      if (instance == null) {
         synchronized (RecoveredMusicC.class) {
            if (instance == null) {
               instance = new RecoveredMusicC();
            }
         }
      }

      return instance;
   }

   public void b() {
      if (!this.loaded) {
         this.loaded = true;
         this.z();
         this.u();
         RecoveredUtilsV.a(this::l);
      }
   }

   private void u() {
      try {
         if (!this.musicFolder.exists()) {
            boolean var1 = this.musicFolder.mkdirs();
            if (var1) {
               this.v();
            }
         }
      } catch (Throwable var2) {
      }
   }

   private void v() {
      File var1 = new File(this.musicFolder, "把音乐放在这里.txt");
      if (!var1.exists()) {
         String var2 = "EixClient 音乐播放器\n====================\n\n把音频文件直接放进这个文件夹（支持子文件夹）即可，支持的格式：\n  .mp3  .ogg  .wav  .aiff  .aif  .au\n\n游戏内操作：\n  Insert   打开播放列表界面\n  [        播放 / 暂停\n  ]        下一首\n  \\        上一首\n  = / -    音量 + / -\n  或在聊天栏输入 .music 查看命令帮助\n\n修改音乐目录：游戏内打开 ClickGUI -> MusicPlayer -> Music Folder\n";

         try {
            Files.writeString(var1.toPath(), var2, StandardCharsets.UTF_8);
         } catch (Throwable var4) {
         }
      }
   }

   public RecoveredMusicB c() {
      return this.engine;
   }

   public File d() {
      return this.musicFolder;
   }

   public void a(File var1) {
      if (var1 != null) {
         if (this.musicFolder == null || !this.musicFolder.getAbsolutePath().equals(var1.getAbsolutePath())) {
            this.musicFolder = var1;
            this.u();
            this.y();
            RecoveredUtilsV.a(this::l);
         }
      }
   }

   public void a(String var1) {
      if (var1 != null && !var1.isBlank()) {
         this.a(new File(var1.trim()));
      }
   }

   public List<RecoveredMusicD> e() {
      return Collections.unmodifiableList(this.playlist);
   }

   public int f() {
      return this.playlist.size();
   }

   public int g() {
      return this.currentIndex;
   }

   public RecoveredMusicD h() {
      int var1 = this.currentIndex;
      return var1 >= 0 && var1 < this.playlist.size() ? this.playlist.get(var1) : null;
   }

   public boolean i() {
      return this.shuffle;
   }

   public void a(boolean var1) {
      this.shuffle = var1;
      this.y();
   }

   public RecoveredMusicC.InnerA j() {
      return this.loopMode;
   }

   public void a(RecoveredMusicC.InnerA var1) {
      this.loopMode = var1 == null ? RecoveredMusicC.InnerA.ALL : var1;
      this.y();
   }

   public boolean k() {
      return this.scanning;
   }

   public void l() {
      if (!this.scanning) {
         this.scanning = true;

         try {
            this.u();
            ArrayList<RecoveredMusicD> var1 = new ArrayList<>();
            File var2 = this.musicFolder;
            if (var2 != null && var2.isDirectory()) {
               try (Stream<java.nio.file.Path> var3 = Files.walk(var2.toPath())) {
                  var3.filter(var0 -> Files.isRegularFile(var0))
                     .filter(var0 -> RecoveredMusicA.a(var0.getFileName().toString()))
                     .forEach(var1x -> var1.add(new RecoveredMusicD(var1x.toFile())));
               } catch (Throwable var13) {
                  log.warn("扫描音乐目录失败", var13);
               }
            }

            var1.sort((var0, var1x) -> var0.a().getName().compareToIgnoreCase(var1x.a().getName()));
            RecoveredMusicD var15 = this.h();
            this.playlist.clear();
            this.playlist.addAll(var1);
            if (var15 != null) {
               int var4 = this.playlist.indexOf(var15);
               this.currentIndex = var4;
            } else if (this.currentIndex >= this.playlist.size()) {
               this.currentIndex = this.playlist.isEmpty() ? -1 : this.playlist.size() - 1;
            }

            RecoveredUtilsV.a(() -> {
               for (RecoveredMusicD var2x : this.playlist) {
                  try {
                     var2x.g();
                  } catch (Throwable var4x) {
                  }
               }
            });
         } finally {
            this.scanning = false;
         }
      }
   }

   public void a(int var1) {
      if (var1 >= 0 && var1 < this.playlist.size()) {
         this.w();
         RecoveredMusicD var2 = this.playlist.get(var1);
         int var3 = this.currentIndex;
         if (var3 >= 0 && var3 != var1) {
            this.history.push(var3);

            while (this.history.size() > 64) {
               this.history.removeLast();
            }
         }

         this.currentIndex = var1;
         var2.g();
         this.engine.b();
         this.engine.a(var2.a(), var2.d());
         this.b(var2);
         this.y();
      }
   }

   private void b(RecoveredMusicD var1) {
      try {
         EixClient var2 = EixClient.a();
         if (var2 == null || var2.j() == null) {
            return;
         }

         RecoveredCAA var3 = new RecoveredCAA(RecoveredCAB.b, "♪ " + var1.h(), 2500L);
         var2.j().a(var3);
      } catch (Throwable var4) {
      }
   }

   public void a(RecoveredMusicD var1) {
      int var2 = this.playlist.indexOf(var1);
      if (var2 >= 0) {
         this.a(var2);
      }
   }

   public void m() {
      if (this.engine.d()) {
         this.engine.l();
      } else if (this.n() && this.currentOnlineSong != null) {
         this.b(this.onlineIndex);
      } else {
         if (this.playlist.isEmpty()) {
            this.l();
         }

         if (!this.playlist.isEmpty()) {
            if (this.currentIndex >= 0 && this.currentIndex < this.playlist.size()) {
               this.a(this.currentIndex);
            } else {
               this.a(0);
            }
         }
      }
   }

   public boolean n() {
      return this.currentOnlineSong != null;
   }

   public RecoveredMusicAI o() {
      return this.currentOnlineSong;
   }

   public List<RecoveredMusicAI> p() {
      return List.copyOf(this.onlineQueue);
   }

   public int q() {
      return this.onlineIndex;
   }

   public void a(List<RecoveredMusicAI> var1, int var2) {
      if (var1 != null && !var1.isEmpty()) {
         this.onlineQueue.clear();
         this.onlineQueue.addAll(var1);
         this.b(var2);
      }
   }

   public void a(List<RecoveredMusicAI> var1) {
      this.onlineQueue.clear();
      if (var1 != null) {
         this.onlineQueue.addAll(var1);
      }

      RecoveredMusicAI var2 = this.currentOnlineSong;
      this.onlineIndex = -1;
      if (var2 != null) {
         for (int var3 = 0; var3 < this.onlineQueue.size(); var3++) {
            if (this.onlineQueue.get(var3).a() == var2.a()) {
               this.onlineIndex = var3;
               break;
            }
         }
      }
   }

   public void b(int var1) {
      if (var1 >= 0 && var1 < this.onlineQueue.size()) {
         RecoveredMusicAI var2 = this.onlineQueue.get(var1);
         this.onlineIndex = var1;
         this.currentOnlineSong = var2;
         this.a("解析中…", var2.j(), 1500L);
         RecoveredUtilsV.a(() -> this.a(var2));
      }
   }

   private void w() {
      this.currentOnlineSong = null;
      this.onlineIndex = -1;
      this.onlineQueue.clear();
   }

   private void a(RecoveredMusicAI var1) {
      RecoveredMusicAG var2 = RecoveredMusicAG.a();

      try {
         RecoveredMusicAJ var3 = var2.a(var1);
         if (var3.i()) {
            this.a("无法播放", var1.j() + "：没有可用的播放地址（版权受限）", 3500L);
            return;
         }

         if (!b(var3.b())) {
            RecoveredMusicAJ var6 = var2.b().a(var1.a(), "exhigh", null);
            if (!var6.i() && b(var6.b())) {
               var3 = var6;
            }
         }

         if (!b(var3.b())) {
            this.a("无法播放", var1.j() + "：当前档位是 FLAC/AAC，请改用 exhigh 或先下载", 4000L);
            return;
         }

         RecoveredMusicAJ var7 = var3;
         this.engine.b();
         this.engine.a(() -> RecoveredMusicA.a(var2.f(var7.b()), var7.b()), var1.j(), var1.f());
         this.b(var1);
      } catch (Throwable var5) {
         String var4 = var5.getMessage() == null ? var5.getClass().getSimpleName() : var5.getMessage();
         log.warn("在线播放失败: {}", var1.j(), var5);
         this.a("在线播放失败", var4, 3500L);
      }
   }

   private static boolean b(String var0) {
      try {
         boolean var3;
         try (InputStream var1 = RecoveredMusicAG.a().f(var0)) {
            BufferedInputStream var2 = new BufferedInputStream(var1, 4096);
            var3 = RecoveredMusicA.a(RecoveredMusicA.c(var2, var0));
         }

         return var3;
      } catch (Throwable var6) {
         return false;
      }
   }

   private void b(RecoveredMusicAI var1) {
      this.a("♪ 在线播放", var1.j(), 2500L);
   }

   private void a(String var1, String var2, long var3) {
      try {
         EixClient var5 = EixClient.a();
         if (var5 == null || var5.j() == null) {
            return;
         }

         var5.j().a(new RecoveredCAA(RecoveredCAB.b, var1 + " " + var2, var3));
      } catch (Throwable var6) {
      }
   }

   public void r() {
      this.engine.o();
      this.y();
   }

   public void s() {
      this.b(false);
   }

   private void b(boolean var1) {
      if (this.n()) {
         if (!this.onlineQueue.isEmpty()) {
            if (var1 && this.loopMode == RecoveredMusicC.InnerA.ONE) {
               this.b(this.onlineIndex);
            } else {
               int var3;
               if (this.shuffle && this.onlineQueue.size() > 1) {
                  do {
                     var3 = (int)(Math.random() * (double)this.onlineQueue.size());
                  } while (var3 == this.onlineIndex);
               } else {
                  var3 = this.onlineIndex + 1;
               }

               if (var3 >= this.onlineQueue.size()) {
                  if (var1 && this.loopMode == RecoveredMusicC.InnerA.OFF) {
                     this.engine.o();
                     this.onlineIndex = this.onlineQueue.size() - 1;
                     return;
                  }

                  var3 = 0;
               }

               this.b(var3);
            }
         }
      } else if (!this.playlist.isEmpty()) {
         if (var1 && this.loopMode == RecoveredMusicC.InnerA.ONE) {
            this.a(this.currentIndex);
         } else {
            int var2;
            if (this.shuffle && this.playlist.size() > 1) {
               do {
                  var2 = (int)(Math.random() * (double)this.playlist.size());
               } while (var2 == this.currentIndex);
            } else {
               var2 = this.currentIndex + 1;
            }

            if (var2 >= this.playlist.size()) {
               if (var1 && this.loopMode == RecoveredMusicC.InnerA.OFF) {
                  this.engine.o();
                  this.currentIndex = this.playlist.size() - 1;
                  return;
               }

               var2 = 0;
            }

            this.a(var2);
         }
      }
   }

   public void t() {
      if (this.n()) {
         if (!this.onlineQueue.isEmpty()) {
            int var4 = this.onlineIndex - 1;
            if (var4 < 0) {
               var4 = this.onlineQueue.size() - 1;
            }

            this.b(var4);
         }
      } else if (!this.playlist.isEmpty()) {
         if (!this.history.isEmpty()) {
            Integer var1 = this.history.pop();
            if (var1 != null && var1 >= 0 && var1 < this.playlist.size()) {
               int var2 = var1;
               this.currentIndex = -1;
               this.a(var2);
               return;
            }
         }

         int var3 = this.currentIndex - 1;
         if (var3 < 0) {
            var3 = this.playlist.size() - 1;
         }

         this.currentIndex = -1;
         this.a(var3);
      }
   }

   private void x() {
      this.b(true);
   }

   public void a(float var1) {
      this.engine.a(var1);
   }

   @EventTarget
   public void onKey(RecoveredEventsImplJ var1) {
      if (var1.c()) {
         if (Minecraft.getInstance().screen == null) {
            EixClient var2 = EixClient.a();
            if (var2 != null && var2.g() != null) {
               MusicPlayerModule var3 = var2.g().a(MusicPlayerModule.class);
               if (var3 != null) {
                  int var4 = var1.b();
                  if (var4 != 0) {
                     if (var4 == var3.s.m()) {
                        this.m();
                     } else if (var4 == var3.t.m()) {
                        this.s();
                     } else if (var4 == var3.u.m()) {
                        this.t();
                     } else if (var4 == var3.v.m()) {
                        this.engine.a(Math.min(1.0F, this.engine.i() + 0.05F));
                        var3.i.a(this.engine.i() * 100.0F);
                     } else if (var4 == var3.w.m()) {
                        this.engine.a(Math.max(0.0F, this.engine.i() - 0.05F));
                        var3.i.a(this.engine.i() * 100.0F);
                     } else if (var4 == var3.x.m()) {
                        MusicPlayerModule.p();
                     }
                  }
               }
            }
         }
      }
   }

   private void y() {
      RecoveredMusicC.InnerB var1 = new RecoveredMusicC.InnerB();
      var1.index = this.currentIndex;
      var1.shuffle = this.shuffle;
      var1.loop = this.loopMode.name();
      var1.volume = this.engine.i();
      var1.folder = this.musicFolder != null ? this.musicFolder.getAbsolutePath() : null;
      RecoveredMusicD var2 = this.h();
      if (var2 != null) {
         var1.tracks.add(var2.f());
      }

      try (BufferedWriter var3 = Files.newBufferedWriter(this.stateFile.toPath(), StandardCharsets.UTF_8)) {
         this.gson.toJson(var1, var3);
      } catch (Throwable var8) {
         log.warn("保存音乐播放器状态失败", var8);
      }
   }

   private void z() {
      if (this.stateFile.isFile()) {
         try {
            try (BufferedReader var1 = Files.newBufferedReader(this.stateFile.toPath(), StandardCharsets.UTF_8)) {
               Type var2 = (new TypeToken<RecoveredMusicC.InnerB>() {
               }).getType();
               RecoveredMusicC.InnerB var3 = this.gson.fromJson(var1, var2);
               if (var3 != null) {
                  this.shuffle = var3.shuffle;
                  this.engine.a(Math.max(0.0F, Math.min(1.0F, var3.volume)));

                  try {
                     this.loopMode = RecoveredMusicC.InnerA.valueOf(var3.loop == null ? "ALL" : var3.loop.toUpperCase(Locale.ROOT));
                  } catch (IllegalArgumentException var6) {
                     this.loopMode = RecoveredMusicC.InnerA.ALL;
                  }

                  if (var3.folder != null && !var3.folder.isBlank()) {
                     this.musicFolder = new File(var3.folder);
                  }

                  return;
               }
            }
         } catch (Throwable var8) {
            log.warn("读取音乐播放器状态失败", var8);
         }
      }
   }

   public static enum InnerA {
      OFF("Off", "不循环"),
      ALL("All", "列表循环"),
      ONE("One", "单曲循环");

      private final String name;
      private final String cnName;

      private InnerA(String var3, String var4) {
         this.name = var3;
         this.cnName = var4;
      }

      public String a() {
         return this.name;
      }

      public String b() {
         return this.cnName;
      }
   }

   private static class InnerB {
      List<String> tracks = new ArrayList<>();
      int index = -1;
      boolean shuffle = false;
      String loop = "ALL";
      float volume = 0.7F;
      String folder;
   }
}
