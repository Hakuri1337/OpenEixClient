package com.heypixel.heypixelmod.obsoverlay.modules.impl.b;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.c.RecoveredCD;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAB;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAE;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAF;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplS;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.HUDModule;
import com.heypixel.heypixelmod.obsoverlay.music.RecoveredMusicB;
import com.heypixel.heypixelmod.obsoverlay.music.RecoveredMusicC;
import com.heypixel.heypixelmod.obsoverlay.music.RecoveredMusicD;
import com.heypixel.heypixelmod.obsoverlay.music.lyric.RecoveredMusicLyricB;
import com.heypixel.heypixelmod.obsoverlay.music.nowplaying.RecoveredMusicNowplayingC;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsV;
import com.heypixel.heypixelmod.obsoverlay.utils.e.RecoveredUtilsEA;
import com.heypixel.heypixelmod.obsoverlay.utils.e.b.RecoveredUtilsEBC;
import io.github.humbleui.skija.Font;
import java.awt.Color;
import java.io.File;
import net.minecraft.client.Minecraft;

@ModuleInfo(
   a = "MusicPlayer",
   b = "音乐播放器",
   c = "播放本地音乐文件 (MP3 / OGG / WAV / AIFF)",
   d = ModuleCategory.MISC
)
public class MusicPlayerModule extends ClientModule {
   public RecoveredDAA c = RecoveredDD.a(this, "Show HUD").a(true).a().b();
   public RecoveredDAE d = RecoveredDD.a(this, "HUD Style").a("Full", "Compact").a(0).a(() -> this.c.m()).a().e();
   public RecoveredDAA e = RecoveredDD.a(this, "Background").a(true).a(() -> this.c.m()).a().b();
   public RecoveredDAA f = RecoveredDD.a(this, "Progress Bar").a(true).a(() -> this.c.m()).a().b();
   public RecoveredDAA g = RecoveredDD.a(this, "Show Time").a(true).a(() -> this.c.m()).a().b();
   public RecoveredDAA h = RecoveredDD.a(this, "Equalizer Icon").a(true).a(() -> this.c.m()).a().b();
   public RecoveredDAC i = RecoveredDD.a(this, "Volume").a(70.0F).d(1.0F).b(0.0F).c(100.0F).a(var0 -> RecoveredMusicC.a().a(var0.c().q() / 100.0F)).a().c();
   public RecoveredDAE j = RecoveredDD.a(this, "Loop Mode")
      .a("Off", "All", "One")
      .a(1)
      .a(var0 -> RecoveredMusicC.a().a(RecoveredMusicC.InnerA.values()[var0.e().o()]))
      .a()
      .e();
   public RecoveredDAA k = RecoveredDD.a(this, "Shuffle").a(false).a(var0 -> RecoveredMusicC.a().a(var0.b().m())).a().b();
   public RecoveredDAF l = RecoveredDD.a(this, "Music Folder").a(r()).a(var0 -> RecoveredMusicC.a().a(var0.d().n())).a().d();
   public RecoveredDAB m = RecoveredDD.a(this, "Position").e(10.0F).f(220.0F).a().f();
   public RecoveredDAA n = RecoveredDD.a(this, "Netease Sync").a(true).a(var1 -> this.q()).a().b();
   public RecoveredDAA o = RecoveredDD.a(this, "Other Players").a(true).a(var1 -> this.q()).a().b();
   public RecoveredDAA p = RecoveredDD.a(this, "SMTC Sync").a(false).a(var1 -> this.q()).a().b();
   public RecoveredDAA q = RecoveredDD.a(this, "Fetch Lyrics").a(true).a(var0 -> RecoveredMusicLyricB.a().a(var0.b().m())).a().b();
   public RecoveredDAC r = RecoveredDD.a(this, "Lyric Offset (ms)")
      .a(0.0F)
      .d(50.0F)
      .b(-5000.0F)
      .c(5000.0F)
      .a(() -> this.q.m())
      .a(var0 -> RecoveredMusicLyricB.a().a((long)var0.c().q()))
      .a()
      .c();
   public RecoveredDAD s = RecoveredDD.a(this, "Play / Pause Key").b(91).a().g();
   public RecoveredDAD t = RecoveredDD.a(this, "Next Key").b(93).a().g();
   public RecoveredDAD u = RecoveredDD.a(this, "Previous Key").b(92).a().g();
   public RecoveredDAD v = RecoveredDD.a(this, "Volume Up Key").b(61).a().g();
   public RecoveredDAD w = RecoveredDD.a(this, "Volume Down Key").b(45).a().g();
   public RecoveredDAD x = RecoveredDD.a(this, "Playlist Key").b(260).a().g();

   private static String r() {
      String var0 = System.getenv("APPDATA");
      File var1 = var0 != null && !var0.isBlank() ? new File(var0, "EixClient") : new File(System.getProperty("user.home"), "EixClient");
      return new File(var1, "music").getAbsolutePath();
   }

   public static void p() {
      Minecraft.getInstance().setScreen(new RecoveredCD());
   }

   @Override
   protected void c() {
      super.c();
      this.b(0);
   }

   @Override
   public void d() {
      try {
         RecoveredMusicC var1 = RecoveredMusicC.a();
         var1.b();
         RecoveredUtilsV.a(var1::l);
         this.q();
      } catch (Throwable var2) {
      }
   }

   @Override
   public void e() {
      RecoveredMusicNowplayingC.a().c();
   }

   public void q() {
      try {
         RecoveredMusicNowplayingC var1 = RecoveredMusicNowplayingC.a();
         var1.b(this.n.m());
         var1.c(this.o.m());
         var1.a(this.p.m());
         RecoveredMusicLyricB.a().a(this.q.m());
         RecoveredMusicLyricB.a().a((long)this.r.q());
         boolean var2 = this.n.m() || this.o.m() || this.p.m();
         if (this.m() && var2) {
            var1.e();
         } else {
            var1.c();
         }
      } catch (Throwable var3) {
      }
   }

   @EventTarget
   public void onRenderSkia(RecoveredEventsImplS var1) {
      if (this.c.m()) {
         try {
            this.s();
         } catch (Throwable var3) {
         }
      }
   }

   private void s() {
      RecoveredMusicC var1 = RecoveredMusicC.a();
      RecoveredMusicB var2 = var1.c();
      RecoveredMusicD var3 = var1.h();
      float var4 = this.m.n();
      float var5 = this.m.o();
      boolean var6 = this.d.a("Compact");
      float var7 = var6 ? this.b(var4, var5, var1, var2, var3) : this.a(var4, var5, var1, var2, var3);
      this.m.c(var7);
      this.m.d(var6 ? 20.0F : 54.0F);
   }

   private float a(float var1, float var2, RecoveredMusicC var3, RecoveredMusicB var4, RecoveredMusicD var5) {
      float var6 = 190.0F;
      float var7 = 54.0F;
      float var8 = 10.0F;
      Color var9 = t();
      boolean var10 = var4.c() == RecoveredMusicB.InnerA.PLAYING;
      if (this.e.m()) {
         RecoveredUtilsEA.c(var1, var2, var6, var7, 6.0F);
         RecoveredUtilsEA.a(var1, var2, var6, var7, 6.0F);
         RecoveredUtilsEA.a(var1, var2, var6, var7, 6.0F, new Color(0, 0, 0, 120));
      }

      float var11 = var1 + var8;
      if (this.h.m()) {
         this.a(var1 + var8, var2 + 8.0F, 14.0F, var9, var10);
         var11 = var1 + var8 + 20.0F;
      }

      float var12 = this.g.m() ? 62.0F : 6.0F;
      float var13 = var6 - (var11 - var1) - var8 - var12;
      Font var14 = RecoveredUtilsEBC.a(12.0F);
      Font var15 = RecoveredUtilsEBC.a(9.0F);
      if (var5 == null) {
         String var20 = var3.k() ? "扫描音乐中..." : "没有音乐文件";
         RecoveredUtilsEA.a(RecoveredUtilsEA.a(var20, var14, var13), var11, var2 + 9.0F, new Color(255, 255, 255, 190), var14);
         RecoveredUtilsEA.a("放入 " + a(var3.d()), var11, var2 + 26.0F, new Color(170, 170, 170), var15);
         return var6;
      } else {
         String var16 = RecoveredUtilsEA.a(var5.b(), var14, var13);
         RecoveredUtilsEA.a(var16, var11, var2 + 9.0F, Color.WHITE, var14);
         String var17 = var5.c().isEmpty() ? "未知艺术家" : var5.c();
         RecoveredUtilsEA.a(RecoveredUtilsEA.a(var17, var15, var13), var11, var2 + 27.0F, new Color(175, 175, 175), var15);
         if (this.g.m()) {
            String var18 = RecoveredMusicD.b(var4.k()) + " / " + RecoveredMusicD.b(var4.j());
            float var19 = RecoveredUtilsEA.a(var18, var15);
            RecoveredUtilsEA.a(var18, var1 + var6 - var8 - var19, var2 + 27.0F, new Color(200, 200, 200), var15);
         }

         if (this.f.m()) {
            this.a(var1 + var8, var2 + 42.0F, var6 - var8 * 2.0F, 3.0F, var4, var9);
         }

         return var6;
      }
   }

   private float b(float var1, float var2, RecoveredMusicC var3, RecoveredMusicB var4, RecoveredMusicD var5) {
      float var6 = 20.0F;
      float var7 = 6.0F;
      Color var8 = t();
      boolean var9 = var4.c() == RecoveredMusicB.InnerA.PLAYING;
      Font var10 = RecoveredUtilsEBC.a(11.0F);
      String var11;
      if (var5 == null) {
         var11 = var3.k() ? "扫描音乐中..." : "没有音乐文件";
      } else {
         String var12 = var5.c().isEmpty() ? "" : var5.c() + " - ";
         var11 = var12 + var5.b();
      }

      if (var4.c() == RecoveredMusicB.InnerA.PAUSED) {
         var11 = "[暂停] " + var11;
      }

      String var18 = "";
      if (this.g.m() && var5 != null) {
         var18 = RecoveredMusicD.b(var4.k()) + "/" + RecoveredMusicD.b(var4.j());
      }

      float var13 = this.h.m() ? 16.0F : 0.0F;
      float var14 = var18.isEmpty() ? 0.0F : RecoveredUtilsEA.a(var18, var10) + 6.0F;
      float var15 = RecoveredUtilsEA.a(var11, var10);
      float var16 = var7 + var13 + var15 + var14 + var7;
      var16 = Math.max(var16, 90.0F);
      if (this.e.m()) {
         RecoveredUtilsEA.a(var1, var2, var16, var6, 4.0F);
         RecoveredUtilsEA.a(var1, var2, var16, var6, 4.0F, new Color(0, 0, 0, 120));
      }

      float var17 = var1 + var7;
      if (this.h.m()) {
         this.a(var17, var2 + 4.0F, 12.0F, var8, var9);
         var17 += var13;
      }

      RecoveredUtilsEA.a(var11, var17, var2 + 5.0F, Color.WHITE, var10);
      if (!var18.isEmpty()) {
         RecoveredUtilsEA.a(var18, var1 + var16 - var7 - RecoveredUtilsEA.a(var18, var10), var2 + 5.0F, new Color(190, 190, 190), var10);
      }

      if (this.f.m() && var5 != null) {
         this.a(var1 + var7, var2 + var6 - 2.5F, var16 - var7 * 2.0F, 1.5F, var4, var8);
      }

      return var16;
   }

   private void a(float var1, float var2, float var3, float var4, RecoveredMusicB var5, Color var6) {
      long var7 = var5.j();
      long var9 = var5.k();
      float var11 = var7 > 0L ? Math.max(0.0F, Math.min(1.0F, (float)var9 / (float)var7)) : 0.0F;
      float var12 = var4 / 2.0F;
      RecoveredUtilsEA.a(var1, var2, var3, var4, var12, new Color(255, 255, 255, 45));
      if (var11 > 0.0F) {
         RecoveredUtilsEA.a(var1, var2, Math.max(var4, var3 * var11), var4, var12, var6);
         float var13 = var1 + var3 * var11;
         RecoveredUtilsEA.a(var13, var2 + var12, var12 + 1.0F, Color.WHITE);
      }
   }

   private void a(float var1, float var2, float var3, Color var4, boolean var5) {
      byte var6 = 4;
      float var7 = var3 * 0.16F;
      float var8 = (var3 - var7 * (float)(var6 - 1)) / (float)var6;
      double var9 = (double)System.currentTimeMillis() / 220.0;

      for (int var11 = 0; var11 < var6; var11++) {
         float var12;
         if (var5) {
            var12 = (float)(0.35 + 0.65 * Math.abs(Math.sin(var9 + (double)var11 * 0.9)));
         } else {
            var12 = 0.25F + (float)var11 * 0.05F;
         }

         float var13 = Math.max(2.0F, var3 * var12);
         float var14 = var1 + (float)var11 * (var8 + var7);
         float var15 = var2 + (var3 - var13) / 2.0F;
         RecoveredUtilsEA.a(var14, var15, var8, var13, var8 / 2.0F, var4);
      }
   }

   private static Color t() {
      try {
         HUDModule var0 = EixClient.a().g().a(HUDModule.class);
         if (var0 != null) {
            return HUDModule.p();
         }
      } catch (Throwable var1) {
      }

      return new Color(102, 255, 209);
   }

   private static String a(File var0) {
      if (var0 == null) {
         return "";
      } else {
         String var1 = var0.getAbsolutePath();
         return var1.length() > 42 ? "..." + var1.substring(var1.length() - 39) : var1;
      }
   }
}
