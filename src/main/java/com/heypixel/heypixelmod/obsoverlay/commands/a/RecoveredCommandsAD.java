package com.heypixel.heypixelmod.obsoverlay.commands.a;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.commands.CommandInfo;
import com.heypixel.heypixelmod.obsoverlay.commands.RecoveredCommandsA;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.b.MusicPlayerModule;
import com.heypixel.heypixelmod.obsoverlay.music.RecoveredMusicC;
import com.heypixel.heypixelmod.obsoverlay.music.RecoveredMusicD;
import com.heypixel.heypixelmod.obsoverlay.music.a.RecoveredMusicAC;
import com.heypixel.heypixelmod.obsoverlay.music.a.RecoveredMusicAD;
import com.heypixel.heypixelmod.obsoverlay.music.a.RecoveredMusicAG;
import com.heypixel.heypixelmod.obsoverlay.music.a.RecoveredMusicAH;
import com.heypixel.heypixelmod.obsoverlay.music.a.RecoveredMusicAI;
import com.heypixel.heypixelmod.obsoverlay.music.a.RecoveredMusicAJ;
import com.heypixel.heypixelmod.obsoverlay.music.a.RecoveredMusicAK;
import com.heypixel.heypixelmod.obsoverlay.music.lyric.RecoveredMusicLyricB;
import com.heypixel.heypixelmod.obsoverlay.music.nowplaying.RecoveredMusicNowplayingB;
import com.heypixel.heypixelmod.obsoverlay.music.nowplaying.RecoveredMusicNowplayingC;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsF;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsV;
import java.io.File;
import java.util.List;
import java.util.Locale;
import net.minecraft.client.Minecraft;

@CommandInfo(
   a = "music",
   b = "Control the built-in music player",
   c = {"m", "bgm"}
)
public class RecoveredCommandsAD extends RecoveredCommandsA {
   @Override
   public void a(String[] var1) {
      RecoveredMusicC var2 = RecoveredMusicC.a();
      var2.b();
      if (var1.length == 0) {
         e();
      } else {
         String var3 = var1[0].toLowerCase(Locale.ROOT);
         switch (var3) {
            case "play":
               if (var1.length < 2) {
                  var2.m();
               } else {
                  Integer var24 = c(var1[1]);
                  if (var24 != null && !var2.p().isEmpty()) {
                     var2.b(var24 - 1);
                     a(var2);
                     return;
                  }

                  String var30 = a(var1, 1);
                  RecoveredMusicD var34 = null;

                  for (RecoveredMusicD var37 : var2.e()) {
                     if (var37.h().toLowerCase(Locale.ROOT).contains(var30.toLowerCase(Locale.ROOT))) {
                        var34 = var37;
                        break;
                     }
                  }

                  if (var34 == null) {
                     RecoveredUtilsF.a("§c没有找到匹配的音乐: " + var30);
                     return;
                  }

                  var2.a(var34);
               }

               a(var2);
               break;
            case "pause":
            case "resume":
            case "toggle":
               var2.m();
               a(var2);
               break;
            case "next":
            case "skip":
               var2.s();
               a(var2);
               break;
            case "prev":
            case "previous":
            case "back":
               var2.t();
               a(var2);
               break;
            case "stop":
               var2.r();
               RecoveredUtilsF.a("§7已停止播放");
               break;
            case "volume":
            case "vol":
               if (var1.length < 2) {
                  RecoveredUtilsF.a("§7当前音量: §b" + Math.round(var2.c().i() * 100.0F) + "%");
                  return;
               }

               try {
                  float var22 = Float.parseFloat(var1[1]);
                  var22 = Math.max(0.0F, Math.min(1.0F, var22 > 1.0F ? var22 / 100.0F : var22));
                  var2.a(var22);
                  MusicPlayerModule var29 = g();
                  if (var29 != null) {
                     var29.i.a(var22 * 100.0F);
                  }

                  RecoveredUtilsF.a("§7音量已设置为 §b" + Math.round(var22 * 100.0F) + "%");
               } catch (NumberFormatException var12) {
                  RecoveredUtilsF.a("§c音量必须是数字 (0-100 或 0.0-1.0)");
               }
               break;
            case "list":
               if (var1.length >= 2) {
                  a(var1[1]);
                  return;
               }

               if (var2.e().isEmpty()) {
                  RecoveredUtilsF.a("§7播放列表为空，音乐目录: §b" + var2.d().getAbsolutePath());
                  return;
               }

               RecoveredUtilsF.a("§7播放列表 §b(" + var2.f() + ")§7:");
               int var21 = Math.min(var2.f(), 10);

               for (int var28 = 0; var28 < var21; var28++) {
                  RecoveredMusicD var33 = var2.e().get(var28);
                  String var35 = var28 == var2.g() ? "§b> " : "§7  ";
                  RecoveredUtilsF.a(false, var35 + (var28 + 1) + ". " + var33.h());
               }

               if (var2.f() > var21) {
                  RecoveredUtilsF.a("§7... 以及另外 " + (var2.f() - var21) + " 首");
               }
               break;
            case "scan":
            case "reload":
               var2.l();
               RecoveredUtilsF.a("§7正在重新扫描音乐目录...");
               break;
            case "open":
            case "gui":
               MusicPlayerModule.p();
               break;
            case "folder":
            case "dir":
               RecoveredUtilsF.a("§7音乐目录: §b" + var2.d().getAbsolutePath());
               break;
            case "status":
            case "info":
               f();
               break;
            case "lyric":
            case "lrc":
               if (var1.length >= 2 && var1[1].equalsIgnoreCase("clear")) {
                  RecoveredMusicLyricB.a().q();
                  RecoveredUtilsF.a("§7歌词缓存已清空");
               } else {
                  RecoveredMusicLyricB var20 = RecoveredMusicLyricB.a();
                  RecoveredUtilsF.a("§7歌词状态: §b" + (var20.l() ? var20.k().e() + " 行" : (var20.m() ? "加载中..." : "无")));
                  if (!var20.d().isEmpty()) {
                     RecoveredUtilsF.a(false, "§b当前: §f" + var20.d());
                  }
               }
               break;
            case "search":
            case "s":
            case "find":
               if (var1.length < 2) {
                  RecoveredUtilsF.a("§c用法: .music search <关键词>");
                  return;
               }

               String var19 = a(var1, 1);
               RecoveredUtilsF.a("§7正在解析接口搜索: §b" + var19 + "§7...");
               a(() -> {
                  try {
                     List var2x = RecoveredMusicAG.a().d(var19);
                     var2.a(var2x);
                     b(() -> {
                        a("搜索「" + var19 + "」", var2x);
                        if (!var2x.isEmpty()) {
                           RecoveredUtilsF.a("§7用 §b.music play <序号> §7播放，§b.music download <序号> §7下载");
                        }
                     });
                  } catch (Throwable var3x) {
                     b(() -> RecoveredUtilsF.a("§c搜索失败: " + a(var3x)));
                  }
               });
               break;
            case "toplist":
            case "tops":
            case "ranklist":
               RecoveredUtilsF.a("§7正在获取榜单列表...");
               a(() -> {
                  try {
                     List var0 = RecoveredMusicAG.a().i();
                     b(() -> {
                        if (var0.isEmpty()) {
                           RecoveredUtilsF.a("§7没有取到榜单");
                        } else {
                           RecoveredUtilsF.a("§7榜单 §b(" + var0.size() + ")§7:");
                           int var1xx = Math.min(var0.size(), 12);

                           for (int var2x = 0; var2x < var1xx; var2x++) {
                              RecoveredMusicAK var3x = (RecoveredMusicAK)var0.get(var2x);
                              RecoveredUtilsF.a(false, "§b  " + var3x.a() + " §7" + var3x.b() + " §8" + var3x.d());
                           }

                           RecoveredUtilsF.a("§7用 §b.music rank <榜单ID> §7载入榜单");
                        }
                     });
                  } catch (Throwable var1x) {
                     b(() -> RecoveredUtilsF.a("§c获取榜单失败: " + a(var1x)));
                  }
               });
               break;
            case "rank":
            case "top":
               if (var1.length < 2) {
                  RecoveredUtilsF.a("§c用法: .music rank <榜单ID>（先 .music toplist 查看）");
                  return;
               }

               b(var1[1]);
               break;
            case "album":
            case "playlist":
            case "pl":
               if (var1.length < 2) {
                  RecoveredUtilsF.a("§c用法: .music " + var3 + " <歌单/专辑ID 或 分享链接>");
                  return;
               }

               a(var1[1]);
               break;
            case "online":
            case "stream":
               if (var1.length < 2) {
                  RecoveredUtilsF.a("§c用法: .music online <序号>（先用 search / list / rank 载入列表）");
                  return;
               }

               Integer var18 = c(var1[1]);
               if (var18 == null || var18 < 1 || var18 > var2.p().size()) {
                  RecoveredUtilsF.a("§c序号无效，当前在线列表共 §b" + var2.p().size() + " §c首");
                  return;
               }

               var2.b(var18 - 1);
               break;
            case "download":
            case "dl":
            case "save":
               if (var1.length < 2) {
                  RecoveredUtilsF.a("§c用法: .music download <序号>");
                  return;
               }

               Integer var17 = c(var1[1]);
               List var27 = var2.p();
               if (var17 == null || var17 < 1 || var17 > var27.size()) {
                  RecoveredUtilsF.a("§c序号无效，当前在线列表共 §b" + var27.size() + " §c首");
                  return;
               }

               a((RecoveredMusicAI)var27.get(var17 - 1));
               break;
            case "cover":
            case "pic":
               RecoveredMusicAI var16 = var2.o();
               if (var16 == null) {
                  RecoveredUtilsF.a("§7当前没有在播的在线歌曲");
                  return;
               }

               a(() -> {
                  File var1x = RecoveredMusicAG.a().c().a(RecoveredMusicAG.b(var16), var16.e());
                  b(() -> RecoveredUtilsF.a(var1x == null ? "§c封面下载失败" : "§7封面已缓存: §b" + var1x.getAbsolutePath()));
               });
               break;
            case "url":
            case "link":
               RecoveredMusicAI var15 = var2.o();
               if (var15 == null) {
                  RecoveredUtilsF.a("§7当前没有在播的在线歌曲");
                  return;
               }

               a(() -> {
                  try {
                     RecoveredMusicAJ var1x = RecoveredMusicAG.a().a(var15);
                     b(() -> RecoveredUtilsF.a(var1x.i() ? "§c这首没有可用的播放地址（版权受限）" : "§7" + var1x.c() + " §8" + var1x.d() / 1000 + "kbps §b" + var1x.b()));
                  } catch (Throwable var2x) {
                     b(() -> RecoveredUtilsF.a("§c取直链失败: " + a(var2x)));
                  }
               });
               break;
            case "level":
            case "quality":
               RecoveredMusicAG var14 = RecoveredMusicAG.a();
               if (var1.length < 2) {
                  RecoveredUtilsF.a("§7当前音质: §b" + var14.e() + " §8(" + var14.d() + ")");
                  StringBuilder var26 = new StringBuilder("§7可选: ");

                  for (String[] var11 : RecoveredMusicAD.LEVELS) {
                     var26.append("§b").append(var11[0]).append("§7(").append(var11[1]).append(") ");
                  }

                  RecoveredUtilsF.a(false, var26.toString());
                  RecoveredUtilsF.a("§8提示: 在线播放只支持 mp3 档位(standard/exhigh)，FLAC 档位请下载");
                  return;
               }

               if (var14.a(var1[1])) {
                  RecoveredUtilsF.a("§7音质已设置为 §b" + var14.e());
               } else {
                  RecoveredUtilsF.a("§c未知音质档位: " + var1[1]);
               }
               break;
            case "ip":
               RecoveredMusicAG var13 = RecoveredMusicAG.a();
               if (var1.length < 2) {
                  RecoveredUtilsF.a("§7接口配额按请求里的 §bip§7 参数统计（每接口 10 次/分钟）");
                  RecoveredUtilsF.a(false, "§7本机IP: §b" + var13.b().a().a());
                  RecoveredUtilsF.a(false, "§7自定义IP: §b" + var13.b().a().c());
                  RecoveredUtilsF.a(false, "§7自动轮换: §b" + var13.b().a().b() + " §7/ 优先本机IP: §b" + var13.g());
                  RecoveredUtilsF.a("§7用法: §b.music ip add <IP> §7| §b.music ip clear §7| §b.music ip auto <on|off>");
                  return;
               }

               String var25 = var1[1].toLowerCase(Locale.ROOT);
               if (var25.equals("add") && var1.length >= 3) {
                  String var31 = var1[2].trim();
                  if (!RecoveredMusicAC.e(var31)) {
                     RecoveredUtilsF.a("§c不是合法的 IPv4 地址: " + var31);
                     return;
                  }

                  var13.c(var31);
                  RecoveredUtilsF.a("§7已加入 IP 池: §b" + var31);
               } else if (var25.equals("clear")) {
                  var13.h();
                  RecoveredUtilsF.a("§7自定义 IP 池已清空");
               } else if (var25.equals("auto") && var1.length >= 3) {
                  boolean var8 = var1[2].equalsIgnoreCase("on") || var1[2].equalsIgnoreCase("true");
                  var13.b().a().a(var8);
                  RecoveredUtilsF.a("§7自动轮换 IP: §b" + var8);
               } else {
                  RecoveredUtilsF.a("§c用法: .music ip add <IP> | clear | auto <on|off>");
               }
               break;
            case "api":
               RecoveredMusicAG var6 = RecoveredMusicAG.a();
               RecoveredUtilsF.a("§7—— 网易云解析接口 ——");
               RecoveredUtilsF.a(false, "§7地址: §b" + var6.b().b());
               RecoveredUtilsF.a(false, "§7音质: §b" + var6.e());
               RecoveredUtilsF.a(false, "§7在线曲目: §b" + var2.p().size() + " §7首");
               RecoveredMusicAI var7 = var2.o();
               if (var7 != null) {
                  RecoveredUtilsF.a(false, "§7在播: §b" + var7.j() + " §8" + var7.k());
               }

               RecoveredUtilsF.a(false, "§7封面缓存目录: §b" + var6.c().b().getAbsolutePath());
               RecoveredUtilsF.a("§7重置本机IP: §b.music api refresh");
               if (var1.length >= 2 && var1[1].equalsIgnoreCase("refresh")) {
                  a(() -> {
                     try {
                        String var1x = var6.b().c();
                        b(() -> RecoveredUtilsF.a("§7接口识别到本机 IP: §b" + var1x));
                     } catch (Throwable var2x) {
                        b(() -> RecoveredUtilsF.a("§c获取失败: " + a(var2x)));
                     }
                  });
               }
               break;
            default:
               e();
         }
      }
   }

   private static void a(String var0) {
      RecoveredUtilsF.a("§7正在解析: §b" + var0);
      a(() -> {
         try {
            RecoveredMusicAH var1 = RecoveredMusicAG.a().e(var0);
            RecoveredMusicC.a().a(var1.h());
            b(() -> {
               a(var1.a().a() + "「" + var1.c() + "」" + (var1.e().isEmpty() ? "" : " §8by " + var1.e()), var1.h());
               if (!var1.h().isEmpty()) {
                  RecoveredUtilsF.a("§7用 §b.music play <序号> §7播放，§b.music download <序号> §7下载");
               }
            });
         } catch (Throwable var2) {
            b(() -> RecoveredUtilsF.a("§c解析失败: " + a(var2)));
         }
      });
   }

   private static void b(String var0) {
      long var1;
      try {
         var1 = Long.parseLong(var0.trim());
      } catch (NumberFormatException var4) {
         RecoveredUtilsF.a("§c榜单 ID 必须是数字");
         return;
      }

      RecoveredUtilsF.a("§7正在载入榜单 §b" + var1 + "§7...");
      a(() -> {
         try {
            RecoveredMusicAH var2 = RecoveredMusicAG.a().a(var1);
            RecoveredMusicC.a().a(var2.h());
            b(() -> {
               a("榜单「" + var2.c() + "」", var2.h());
               if (!var2.h().isEmpty()) {
                  RecoveredUtilsF.a("§7用 §b.music play <序号> §7播放，§b.music download <序号> §7下载");
               }
            });
         } catch (Throwable var3) {
            b(() -> RecoveredUtilsF.a("§c载入榜单失败: " + a(var3)));
         }
      });
   }

   private static void a(RecoveredMusicAI var0) {
      RecoveredUtilsF.a("§7开始下载: §b" + var0.j() + " §8(" + var0.k() + ")");
      a(() -> {
         final int[] var1 = new int[]{-10};

         try {
            File var2 = RecoveredMusicAG.a().a(var0, new RecoveredMusicAG.InnerB() {
               @Override
               public void a(long var1x, long var3) {
                  if (var3 > 0L) {
                     int var5 = (int)(var1x * 100L / var3);
                     if (var5 >= var1[0] + 20) {
                        var1[0] = var5;
                        RecoveredCommandsAD.b(() -> RecoveredUtilsF.a("§7下载中... §b" + var5 + "%"));
                     }
                  }
               }
            });
            b(() -> {
               RecoveredUtilsF.a("§a下载完成: §b" + var2.getName());
               RecoveredMusicC.a().l();
            });
         } catch (Throwable var3) {
            b(() -> RecoveredUtilsF.a("§c下载失败: " + a(var3)));
         }
      });
   }

   private static void a(String var0, List<RecoveredMusicAI> var1) {
      if (var1 != null && !var1.isEmpty()) {
         RecoveredUtilsF.a("§7" + var0 + " §b(" + var1.size() + ")§7:");
         int var2 = Math.min(var1.size(), 12);

         for (int var3 = 0; var3 < var2; var3++) {
            RecoveredMusicAI var4 = (RecoveredMusicAI)var1.get(var3);
            String var5 = var4.i() ? "§8[版权]" : (var4.g() ? "§8[免费]" : "§8[VIP]");
            RecoveredUtilsF.a(false, "§b  " + (var3 + 1) + ". §f" + var4.b() + " §7- " + var4.c() + " §8" + var4.k() + " " + var5);
         }

         if (var1.size() > var2) {
            RecoveredUtilsF.a("§7... 以及另外 " + (var1.size() - var2) + " 首");
         }
      } else {
         RecoveredUtilsF.a("§7" + var0 + ": 没有结果");
      }
   }

   private static void a(RecoveredMusicC var0) {
      if (var0.n()) {
         RecoveredMusicAI var1 = var0.o();

         String var2 = switch (var0.c().c()) {
            case PLAYING -> "播放中";
            case PAUSED -> "已暂停";
            default -> "已停止";
         };
         if (var1 != null) {
            RecoveredUtilsF.a("§7" + var2 + " §8(在线): §b" + var1.j());
            return;
         }
      }

      RecoveredMusicD var3 = var0.h();
      if (var3 == null) {
         RecoveredUtilsF.a("§7没有正在播放的音乐");
      } else {
         String var4 = switch (var0.c().c()) {
            case PLAYING -> "播放中";
            case PAUSED -> "已暂停";
            default -> "已停止";
         };
         RecoveredUtilsF.a("§7" + var4 + ": §b" + var3.h());
      }
   }

   private static void e() {
      RecoveredUtilsF.a("§7§l本地§r §7.music §bplay [关键字|序号] §7/ §bpause §7/ §bnext §7/ §bprev §7/ §bstop");
      RecoveredUtilsF.a("§7.music §bvolume <0-100> §7/ §blist §7/ §bscan §7/ §bopen §7/ §bfolder §7/ §bstatus §7/ §blyric");
      RecoveredUtilsF.a("§7§l在线（解析接口）§r");
      RecoveredUtilsF.a("§7.music §bsearch <关键词> §7—— 搜歌（会员歌曲可播/可下）");
      RecoveredUtilsF.a("§7.music §btoplist §7—— 榜单列表； §brank <ID> §7载入榜单");
      RecoveredUtilsF.a("§7.music §blist <歌单/专辑ID或链接> §7—— 歌单、专辑解析");
      RecoveredUtilsF.a("§7.music §bonline <序号> §7播放 / §bdownload <序号> §7下载到音乐目录");
      RecoveredUtilsF.a("§7.music §blevel [档位] §7/ §bip §7/ §bcover §7/ §burl §7/ §bapi");
   }

   private static void f() {
      RecoveredMusicNowplayingC var0 = RecoveredMusicNowplayingC.a();
      RecoveredMusicLyricB var1 = RecoveredMusicLyricB.a();
      RecoveredUtilsF.a("§7—— 音乐播放器状态 ——");

      for (String var5 : var0.j().split("\n")) {
         RecoveredUtilsF.a(false, "§7" + var5);
      }

      RecoveredMusicNowplayingB var6 = var0.i();
      if (!var6.k()) {
         RecoveredUtilsF.a(false, "§7进度: §f" + RecoveredMusicD.b(var6.m()) + " / " + RecoveredMusicD.b(var6.f()));
      }

      RecoveredUtilsF.a(
         false, "§7歌词: §b" + (var1.l() ? var1.k().e() + " 行" : (var1.m() ? "加载中..." : "无")) + (var1.n().isEmpty() ? "" : " §7(" + var1.n() + ")")
      );
      if (var6.k()) {
         RecoveredUtilsF.a("§7提示: 需要在 ClickGUI 里开启 §bMusicPlayer§7 模块；");
         RecoveredUtilsF.a("§7网易云识别依赖窗口标题，请确保网易云客户端窗口没有被最小化到托盘。");
      }
   }

   private static MusicPlayerModule g() {
      try {
         return EixClient.a().g().a(MusicPlayerModule.class);
      } catch (Throwable var1) {
         return null;
      }
   }

   private static void a(Runnable var0) {
      RecoveredUtilsV.a(var0);
   }

   private static void b(Runnable var0) {
      try {
         Minecraft var1 = Minecraft.getInstance();
         if (var1 != null) {
            var1.execute(var0);
            return;
         }
      } catch (Throwable var2) {
      }

      var0.run();
   }

   private static Integer c(String var0) {
      try {
         return Integer.parseInt(var0.trim());
      } catch (NumberFormatException var2) {
         return null;
      }
   }

   private static String a(Throwable var0) {
      String var1 = var0.getMessage();
      return var1 != null && !var1.isBlank() ? var1 : var0.getClass().getSimpleName();
   }

   private static String a(String[] var0, int var1) {
      StringBuilder var2 = new StringBuilder();

      for (int var3 = var1; var3 < var0.length; var3++) {
         if (var2.length() > 0) {
            var2.append(' ');
         }

         var2.append(var0[var3]);
      }

      return var2.toString();
   }

   @Override
   public String[] b(String[] var1) {
      return new String[]{
         "play",
         "pause",
         "next",
         "prev",
         "stop",
         "volume",
         "list",
         "scan",
         "open",
         "folder",
         "status",
         "lyric",
         "search",
         "toplist",
         "rank",
         "album",
         "online",
         "download",
         "level",
         "ip",
         "cover",
         "url",
         "api"
      };
   }
}
