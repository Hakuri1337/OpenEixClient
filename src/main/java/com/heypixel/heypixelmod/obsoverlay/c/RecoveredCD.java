package com.heypixel.heypixelmod.obsoverlay.c;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.HUDModule;
import com.heypixel.heypixelmod.obsoverlay.music.RecoveredMusicB;
import com.heypixel.heypixelmod.obsoverlay.music.RecoveredMusicC;
import com.heypixel.heypixelmod.obsoverlay.music.RecoveredMusicD;
import com.heypixel.heypixelmod.obsoverlay.music.a.RecoveredMusicAB;
import com.heypixel.heypixelmod.obsoverlay.music.a.RecoveredMusicAD;
import com.heypixel.heypixelmod.obsoverlay.music.a.RecoveredMusicAG;
import com.heypixel.heypixelmod.obsoverlay.music.a.RecoveredMusicAH;
import com.heypixel.heypixelmod.obsoverlay.music.a.RecoveredMusicAI;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAd;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsV;
import com.heypixel.heypixelmod.obsoverlay.utils.e.RecoveredUtilsEA;
import com.heypixel.heypixelmod.obsoverlay.utils.e.a.RecoveredUtilsEAA;
import com.heypixel.heypixelmod.obsoverlay.utils.e.b.RecoveredUtilsEBC;
import io.github.humbleui.skija.Font;
import io.github.humbleui.skija.Paint;
import io.github.humbleui.skija.Path;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class RecoveredCD extends Screen {
   private static final float a = 440.0F;
   private static final float b = 300.0F;
   private static final float c = 40.0F;
   private static final float d = 62.0F;
   private static final float e = 22.0F;
   private static final float f = 150.0F;
   private final RecoveredMusicC g = RecoveredMusicC.a();
   private final List<RecoveredMusicD> h = new ArrayList<>();
   private RecoveredCD.InnerB i = RecoveredCD.InnerB.LOCAL;
   private String j = "";
   private float k = 0.0F;
   private float l = 0.0F;
   private int m = -1;
   private int n = -1;
   private String o = "";
   private final List<RecoveredMusicAI> p = new ArrayList<>();
   private volatile boolean q = false;
   private volatile String r = "";
   private volatile String s = "";
   private float t = 0.0F;
   private float u = 0.0F;
   private int v = -1;
   private float w = 0.0F;
   private boolean x = false;
   private boolean y = false;
   private float z = -1.0F;
   private float A;
   private float B;
   private float C;

   public RecoveredCD() {
      super(Component.literal("Music Player"));
   }

   protected void a() {
      this.g.b();
      this.d();
   }

   private void d() {
      this.h.clear();
      String var1 = this.j.toLowerCase(Locale.ROOT).trim();

      for (RecoveredMusicD var3 : this.g.e()) {
         if (var1.isEmpty() || var3.h().toLowerCase(Locale.ROOT).contains(var1)) {
            this.h.add(var3);
         }
      }

      this.n = this.g.f();
   }

   private float e() {
      return this.B + 40.0F;
   }

   private float f() {
      return this.e() + this.C + 4.0F;
   }

   private float g() {
      return this.A + 10.0F;
   }

   private float h() {
      return 420.0F;
   }

   public void a(GuiGraphics var1, int var2, int var3, float var4) {
      this.A = ((float)this.width - 440.0F) / 2.0F;
      this.B = ((float)this.height - 300.0F) / 2.0F;
      this.C = 194.0F;
      if (this.g.f() != this.n) {
         this.d();
      }

      RecoveredUtilsEAA.a(var3x -> {
         RecoveredUtilsEA.c();
         RecoveredUtilsEA.a((float)Minecraft.getInstance().getWindow().getGuiScale());
         this.a(var2, var3);
         RecoveredUtilsEA.d();
      });
      super.render(var1, var2, var3, var4);
   }

   private void a(int var1, int var2) {
      RecoveredMusicB var3 = this.g.c();
      Color var4 = m();
      Font var5 = RecoveredUtilsEBC.a(14.0F);
      Font var6 = RecoveredUtilsEBC.a(11.0F);
      Font var7 = RecoveredUtilsEBC.a(9.0F);
      RecoveredUtilsEA.c(this.A, this.B, 440.0F, 300.0F, 8.0F);
      RecoveredUtilsEA.a(this.A, this.B, 440.0F, 300.0F, 8.0F);
      RecoveredUtilsEA.a(this.A, this.B, 440.0F, 300.0F, 8.0F, new Color(14, 14, 16, 228));
      float var8 = 24.0F;
      this.i();
      File var9 = RecoveredMusicAB.a().b();
      if (var9 != null) {
         RecoveredUtilsEA.a(var9, this.A + 10.0F, this.B + 7.0F, var8, var8, 5.0F);
      } else {
         RecoveredUtilsEA.a(this.A + 10.0F, this.B + 7.0F, var8, var8, 5.0F, new Color(255, 255, 255, 20));
      }

      float var10 = this.A + 10.0F + var8 + 8.0F;
      RecoveredUtilsEA.a("音乐播放器", var10, this.B + 8.0F, Color.WHITE, var5);
      float var11 = RecoveredUtilsEA.a("音乐播放器", var5);
      String var12 = this.i == RecoveredCD.InnerB.ONLINE ? (this.r.isEmpty() ? "在线解析" : this.r) : this.h.size() + " / " + this.g.f() + " 首";
      RecoveredUtilsEA.a(RecoveredUtilsEA.a(var12, var7, 150.0F), var10 + 6.0F + var11, this.B + 12.0F, new Color(165, 165, 165), var7);
      float var13 = this.A + 440.0F - 12.0F - 150.0F;
      this.a(var13 - 48.0F, this.B + 9.0F, var6, var7, var4);
      this.a(var13, this.B + 9.0F, 150.0F, var6);
      RecoveredUtilsEA.a(this.A + 10.0F, this.B + 40.0F - 6.0F, 420.0F, 1.0F, new Color(255, 255, 255, 25));
      this.m = -1;
      this.v = -1;
      if (this.i == RecoveredCD.InnerB.ONLINE) {
         this.k();
         this.b(var1, var2, var6, var7, var4);
      } else {
         this.j();
         this.a(var1, var2, var6, var7, var4);
      }

      this.a(var1, var2, var6, var7, var4, var3);
   }

   private void a(float var1, float var2, Font var3, Font var4, Color var5) {
      boolean var6 = this.i == RecoveredCD.InnerB.ONLINE;
      float var7 = 44.0F;
      RecoveredUtilsEA.a(var1, var2, var7, 16.0F, 8.0F, var6 ? new Color(var5.getRed(), var5.getGreen(), var5.getBlue(), 150) : new Color(255, 255, 255, 24));
      String var8 = var6 ? "在线" : "本地";
      RecoveredUtilsEA.a(var8, var1 + (var7 - RecoveredUtilsEA.a(var8, var4)) / 2.0F, var2 + 4.0F, Color.WHITE, var4);
   }

   private boolean a(double var1, double var3) {
      float var5 = this.A + 440.0F - 12.0F - 150.0F;
      return RecoveredUtilsAd.a((int)var1, (int)var3, var5 - 48.0F, this.B + 9.0F, var5 - 4.0F, this.B + 25.0F);
   }

   private void i() {
      RecoveredMusicAI var1 = this.g.o();
      if (var1 != null) {
         RecoveredMusicAB.a().a(var1.b(), var1.c(), var1.a());
      } else {
         RecoveredMusicD var2 = this.g.h();
         if (var2 == null) {
            RecoveredMusicAB.a().d();
         } else {
            RecoveredMusicAB.a().a(var2.b(), var2.c(), -1L);
         }
      }
   }

   private void j() {
      float var1 = (float)this.h.size() * 22.0F;
      float var2 = Math.max(0.0F, var1 - this.C);
      if (this.l > var2) {
         this.l = var2;
      }

      if (this.l < 0.0F) {
         this.l = 0.0F;
      }

      this.k = this.k + (this.l - this.k) * 0.35F;
   }

   private void a(int var1, int var2, Font var3, Font var4, Color var5) {
      float var6 = this.g();
      float var7 = this.e();
      float var8 = this.h();
      if (this.h.isEmpty()) {
         String var23 = this.g.k() ? "正在扫描音乐文件夹..." : "没有找到音乐文件";
         String var24 = this.g.k() ? "" : "把音频放入 " + this.g.d().getAbsolutePath();
         float var25 = var6 + var8 / 2.0F;
         RecoveredUtilsEA.a(var23, var25 - RecoveredUtilsEA.a(var23, var3) / 2.0F, var7 + this.C / 2.0F - 12.0F, new Color(190, 190, 190), var3);
         if (!var24.isEmpty()) {
            RecoveredUtilsEA.a(
               RecoveredUtilsEA.a(var24, var4, var8 - 20.0F),
               var25 - RecoveredUtilsEA.a(RecoveredUtilsEA.a(var24, var4, var8 - 20.0F), var4) / 2.0F,
               var7 + this.C / 2.0F + 6.0F,
               new Color(140, 140, 140),
               var4
            );
         }
      } else {
         RecoveredUtilsEA.c();
         RecoveredUtilsEA.d(var6, var7, var8, this.C, 4.0F);
         RecoveredMusicD var9 = this.g.h();
         RecoveredMusicB var10 = this.g.c();
         boolean var11 = var10.c() == RecoveredMusicB.InnerA.PLAYING;

         for (int var12 = 0; var12 < this.h.size(); var12++) {
            float var13 = var7 + (float)var12 * 22.0F - this.k;
            if (!(var13 + 22.0F < var7) && !(var13 > var7 + this.C)) {
               RecoveredMusicD var14 = this.h.get(var12);
               boolean var15 = !this.g.n() && var9 != null && var14.equals(var9);
               boolean var16 = (float)var1 >= var6 && (float)var1 <= var6 + var8 && (float)var2 >= var13 && (float)var2 <= var13 + 22.0F;
               if (var16) {
                  this.m = var12;
               }

               if (var15) {
                  RecoveredUtilsEA.a(var6, var13, var8, 20.0F, 4.0F, new Color(var5.getRed(), var5.getGreen(), var5.getBlue(), 55));
               } else if (var16) {
                  RecoveredUtilsEA.a(var6, var13, var8, 20.0F, 4.0F, new Color(255, 255, 255, 18));
               }

               String var17 = String.format(Locale.ROOT, "%02d", var12 + 1);
               RecoveredUtilsEA.a(var17, var6 + 6.0F, var13 + 6.0F, new Color(150, 150, 150), var4);
               float var18 = var6 + 26.0F;
               float var19 = 42.0F;
               float var20 = var8 - (var18 - var6) - var19 - 10.0F;
               String var21 = (var15 && var11 ? "> " : "") + var14.h();
               RecoveredUtilsEA.a(RecoveredUtilsEA.a(var21, var3, var20), var18, var13 + 4.0F, var15 ? Color.WHITE : new Color(225, 225, 225), var3);
               String var22 = var14.e() ? RecoveredMusicD.b(var14.d()) : "--:--";
               RecoveredUtilsEA.a(var22, var6 + var8 - 8.0F - RecoveredUtilsEA.a(var22, var4), var13 + 6.0F, new Color(150, 150, 150), var4);
            }
         }

         RecoveredUtilsEA.d();
         float var26 = (float)this.h.size() * 22.0F;
         if (var26 > this.C) {
            float var27 = this.C / var26;
            float var28 = Math.max(20.0F, this.C * var27);
            float var29 = var26 - this.C;
            float var30 = var29 > 0.0F ? this.k / var29 * (this.C - var28) : 0.0F;
            RecoveredUtilsEA.a(var6 + var8 - 3.0F, var7 + var30, 3.0F, var28, 1.5F, new Color(255, 255, 255, 60));
         }
      }
   }

   private void a(int var1, int var2, Font var3, Font var4, Color var5, RecoveredMusicB var6) {
      float var7 = this.g();
      float var8 = this.f();
      float var9 = this.h();
      long var10 = var6.j();
      long var12 = this.z >= 0.0F && var10 > 0L ? (long)(this.z * (float)var10) : var6.k();
      float var14 = var10 > 0L ? Math.max(0.0F, Math.min(1.0F, (float)var12 / (float)var10)) : 0.0F;
      float var15 = var8 + 6.0F;
      RecoveredUtilsEA.a(var7, var15, var9, 4.0F, 2.0F, new Color(255, 255, 255, 40));
      RecoveredUtilsEA.a(var7, var15, Math.max(4.0F, var9 * var14), 4.0F, 2.0F, var5);
      RecoveredUtilsEA.a(var7 + var9 * var14, var15 + 2.0F, 4.0F, Color.WHITE);
      RecoveredUtilsEA.a(RecoveredMusicD.b(var12), var7, var15 + 11.0F, new Color(190, 190, 190), var4);
      String var16 = RecoveredMusicD.b(var10);
      RecoveredUtilsEA.a(var16, var7 + var9 - RecoveredUtilsEA.a(var16, var4), var15 + 11.0F, new Color(190, 190, 190), var4);
      float var17 = var8 + 28.0F;
      float var18 = var7 + var9 / 2.0F;
      boolean var19 = var6.c() == RecoveredMusicB.InnerA.PLAYING;
      this.a(var18 - 48.0F, var17, 24.0F, var1, var2, var5, this::b);
      this.a(var18 - 15.0F, var17, 30.0F, var1, var2, var5, (var2x, var3x) -> {
         if (var19) {
            this.a(var2x, var3x, Color.WHITE);
         } else {
            this.a(var2x, var3x);
         }
      });
      this.a(var18 + 24.0F, var17, 24.0F, var1, var2, var5, this::c);
      String var20 = "循环: " + this.g.j().a();
      float var21 = RecoveredUtilsEA.a(var20, var4) + 16.0F;
      this.a(var7, var17 + 4.0F, var21, this.g.j() != RecoveredMusicC.InnerA.OFF ? var5 : new Color(255, 255, 255, 30), var20, var4);
      String var22 = "随机";
      float var23 = RecoveredUtilsEA.a(var22, var4) + 16.0F;
      this.a(var7 + var21 + 6.0F, var17 + 4.0F, var23, this.g.i() ? var5 : new Color(255, 255, 255, 30), var22, var4);
      float var24 = var6.i();
      float var25 = var7 + var9 - 98.0F;
      float var26 = 70.0F;
      RecoveredUtilsEA.a("音量", var25 - 26.0F, var17 + 3.0F, new Color(190, 190, 190), var4);
      RecoveredUtilsEA.a(var25, var17 + 8.0F, var26, 4.0F, 2.0F, new Color(255, 255, 255, 40));
      RecoveredUtilsEA.a(var25, var17 + 8.0F, Math.max(2.0F, var26 * var24), 4.0F, 2.0F, var5);
      RecoveredUtilsEA.a(var25 + var26 * var24, var17 + 10.0F, 4.0F, Color.WHITE);
      String var27 = Math.round(var24 * 100.0F) + "%";
      RecoveredUtilsEA.a(var27, var7 + var9 - RecoveredUtilsEA.a(var27, var4), var17 + 3.0F, new Color(190, 190, 190), var4);
   }

   private void a(float var1, float var2, float var3, Font var4) {
      boolean var5 = this.i == RecoveredCD.InnerB.ONLINE;
      String var6 = var5 ? this.o : this.j;
      boolean var7 = var5 || !this.j.isEmpty();
      RecoveredUtilsEA.a(var1, var2, var3, 16.0F, 4.0F, new Color(255, 255, 255, var5 ? 26 : 16));
      if (var5 && System.currentTimeMillis() % 1000L < 500L) {
         RecoveredUtilsEA.a(var1 + var3 - 2.0F, var2 + 2.0F, 1.0F, 12.0F, new Color(255, 255, 255, 160));
      }

      String var8;
      Color var9;
      if (var6.isEmpty()) {
         var8 = var5 ? "粘贴链接/ID/歌名，回车" : "搜索... (F5 重新扫描)";
         var9 = new Color(140, 140, 140);
      } else {
         var8 = var6;
         var9 = Color.WHITE;
      }

      RecoveredUtilsEA.a(RecoveredUtilsEA.a(var8, var4, var3 - 12.0F), var1 + 6.0F, var2 + 4.0F, var9, var4);
      if (var7 && !var6.isEmpty()) {
         float var10 = var1 + 6.0F + RecoveredUtilsEA.a(RecoveredUtilsEA.a(var8, var4, var3 - 12.0F), var4);
         RecoveredUtilsEA.a(Math.min(var10, var1 + var3 - 5.0F), var2 + 3.0F, 1.0F, 10.0F, new Color(255, 255, 255, 170));
      }
   }

   private void k() {
      float var1 = (float)this.p.size() * 22.0F;
      float var2 = Math.max(0.0F, var1 - this.C);
      if (this.u > var2) {
         this.u = var2;
      }

      if (this.u < 0.0F) {
         this.u = 0.0F;
      }

      this.t = this.t + (this.u - this.t) * 0.35F;
      this.w++;
   }

   private void l() {
      String var1 = this.o.trim();
      if (!var1.isEmpty() && !this.q) {
         this.q = true;
         this.s = "解析中…";
         this.r = "";
         this.p.clear();
         this.u = 0.0F;
         RecoveredUtilsV.a(() -> {
            try {
               RecoveredMusicAG var2 = RecoveredMusicAG.a();
               String[] var8 = RecoveredMusicAD.b(var1);
               String var5;
               List var9;
               if (!var8[1].isEmpty() && !"song".equals(var8[0])) {
                  RecoveredMusicAH var10 = var2.e(var1);
                  var9 = var10.h();
                  var5 = var10.a().a() + "：" + var10.c();
               } else if (!var8[1].isEmpty()) {
                  RecoveredMusicAI var6 = var2.b(Long.parseLong(var8[1]));
                  var9 = var6 == null ? List.of() : List.of(var6);
                  var5 = var9.isEmpty() ? "单曲" : "单曲：" + var6.b();
               } else {
                  var9 = var2.d(var1);
                  var5 = "搜索：" + var1;
               }

               ArrayList var11 = new ArrayList(var9);
               a(() -> {
                  this.p.clear();
                  this.p.addAll(var11);
                  this.r = var5 + " (" + var11.size() + ")";
                  this.s = var11.isEmpty() ? "没有找到结果" : "";
                  this.q = false;
                  if (!var11.isEmpty()) {
                     this.g.a(var11);
                  }
               });
            } catch (Throwable var7) {
               String var3 = var7.getMessage();
               String var4 = var3 != null && !var3.isBlank() ? var3 : var7.getClass().getSimpleName();
               a(() -> {
                  this.q = false;
                  this.s = "解析失败：" + var4;
               });
            }
         });
      }
   }

   private static void a(Runnable var0) {
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

   private void a(RecoveredMusicAI var1) {
      this.s = "开始下载：" + var1.b();
      RecoveredUtilsV.a(() -> {
         try {
            File var2 = RecoveredMusicAG.a().a(var1, (RecoveredMusicAG.InnerB)null);
            a(() -> {
               this.s = "已下载：" + var2.getName();
               this.g.l();
            });
         } catch (Throwable var5) {
            String var3 = var5.getMessage();
            String var4 = var3 != null && !var3.isBlank() ? var3 : var5.getClass().getSimpleName();
            a(() -> this.s = "下载失败：" + var4);
         }
      });
   }

   private void b(int var1, int var2, Font var3, Font var4, Color var5) {
      float var6 = this.g();
      float var7 = this.e();
      float var8 = this.h();
      if (this.p.isEmpty()) {
         float var24 = var6 + var8 / 2.0F;
         String var26 = this.q ? "正在解析…" : (this.s.isEmpty() ? "粘贴歌曲 / 歌单 / 专辑链接或 ID" : this.s);
         String var28 = this.q ? "" : (this.s.isEmpty() ? "也可以直接输入歌名搜索，回车执行" : "");
         Color var30 = !this.s.startsWith("解析失败") && !this.s.startsWith("没有找到") ? new Color(190, 190, 190) : new Color(240, 130, 130);
         RecoveredUtilsEA.a(var26, var24 - RecoveredUtilsEA.a(var26, var3) / 2.0F, var7 + this.C / 2.0F - 12.0F, var30, var3);
         if (!var28.isEmpty()) {
            RecoveredUtilsEA.a(var28, var24 - RecoveredUtilsEA.a(var28, var4) / 2.0F, var7 + this.C / 2.0F + 6.0F, new Color(140, 140, 140), var4);
         }

         if (this.q) {
            int var32 = (int)(this.w / 12.0F % 3.0F);

            for (int var34 = 0; var34 < 3; var34++) {
               float var35 = var24 - 12.0F + (float)var34 * 12.0F;
               float var36 = var7 + this.C / 2.0F + 26.0F;
               int var37 = var34 == var32 ? 220 : 70;
               RecoveredUtilsEA.a(var35, var36, 2.2F, new Color(255, 255, 255, var37));
            }
         }
      } else {
         RecoveredUtilsEA.c();
         RecoveredUtilsEA.d(var6, var7, var8, this.C, 4.0F);
         RecoveredMusicAI var9 = this.g.o();

         for (int var10 = 0; var10 < this.p.size(); var10++) {
            float var11 = var7 + (float)var10 * 22.0F - this.t;
            if (!(var11 + 22.0F < var7) && !(var11 > var7 + this.C)) {
               RecoveredMusicAI var12 = this.p.get(var10);
               boolean var13 = var9 != null && var9.a() == var12.a();
               boolean var14 = (float)var1 >= var6 && (float)var1 <= var6 + var8 && (float)var2 >= var11 && (float)var2 <= var11 + 22.0F;
               if (var14) {
                  this.v = var10;
               }

               if (var13) {
                  RecoveredUtilsEA.a(var6, var11, var8, 20.0F, 4.0F, new Color(var5.getRed(), var5.getGreen(), var5.getBlue(), 55));
               } else if (var14) {
                  RecoveredUtilsEA.a(var6, var11, var8, 20.0F, 4.0F, new Color(255, 255, 255, 18));
               }

               String var15 = String.format(Locale.ROOT, "%02d", var10 + 1);
               RecoveredUtilsEA.a(var15, var6 + 6.0F, var11 + 6.0F, new Color(150, 150, 150), var4);
               float var16 = var6 + 26.0F;
               float var17 = 78.0F;
               float var18 = var8 - (var16 - var6) - var17 - 10.0F;
               String var19 = (var13 ? "> " : "") + var12.b() + " - " + var12.c();
               RecoveredUtilsEA.a(RecoveredUtilsEA.a(var19, var3, var18), var16, var11 + 4.0F, var13 ? Color.WHITE : new Color(225, 225, 225), var3);
               String var20 = var12.k();
               RecoveredUtilsEA.a(var20, var6 + var8 - 8.0F - RecoveredUtilsEA.a(var20, var4), var11 + 6.0F, new Color(150, 150, 150), var4);
               String var21 = var12.i() ? "版权" : (var12.g() ? "免费" : "VIP");
               Color var22 = var12.i() ? new Color(230, 120, 120) : (var12.g() ? new Color(130, 200, 150) : new Color(220, 190, 120));
               float var23 = var6 + var8 - 12.0F - RecoveredUtilsEA.a(var20, var4) - 26.0F;
               RecoveredUtilsEA.a(var21, var23, var11 + 6.0F, var22, var4);
            }
         }

         RecoveredUtilsEA.d();
         float var25 = (float)this.p.size() * 22.0F;
         if (var25 > this.C) {
            float var27 = this.C / var25;
            float var29 = Math.max(20.0F, this.C * var27);
            float var31 = var25 - this.C;
            float var33 = var31 > 0.0F ? this.t / var31 * (this.C - var29) : 0.0F;
            RecoveredUtilsEA.a(var6 + var8 - 3.0F, var7 + var33, 3.0F, var29, 1.5F, new Color(255, 255, 255, 60));
         }
      }
   }

   private void a(float var1, float var2, float var3, int var4, int var5, Color var6, RecoveredCD.InnerA var7) {
      boolean var8 = RecoveredUtilsAd.a(var4, var5, var1, var2, var1 + var3, var2 + var3);
      Color var9 = var8 ? new Color(var6.getRed(), var6.getGreen(), var6.getBlue(), 90) : new Color(255, 255, 255, 22);
      RecoveredUtilsEA.a(var1, var2, var3, var3, 6.0F, var9);
      var7.paint(var1 + var3 / 2.0F, var2 + var3 / 2.0F);
   }

   private void a(float var1, float var2, float var3, Color var4, String var5, Font var6) {
      RecoveredUtilsEA.a(var1, var2, var3, 16.0F, 8.0F, var4);
      RecoveredUtilsEA.a(var5, var1 + 8.0F, var2 + 4.0F, Color.WHITE, var6);
   }

   private void a(float var1, float var2) {
      this.a(var1 - 4.0F, var2 - 6.0F, var1 - 4.0F, var2 + 6.0F, var1 + 6.0F, var2, Color.WHITE);
   }

   private void a(float var1, float var2, Color var3) {
      RecoveredUtilsEA.a(var1 - 5.0F, var2 - 6.0F, 3.5F, 12.0F, 1.0F, var3);
      RecoveredUtilsEA.a(var1 + 1.5F, var2 - 6.0F, 3.5F, 12.0F, 1.0F, var3);
   }

   private void b(float var1, float var2) {
      this.a(var1 - 2.0F, var2 - 6.0F, var1 - 2.0F, var2 + 6.0F, var1 + 5.0F, var2, Color.WHITE);
      RecoveredUtilsEA.a(var1 - 6.0F, var2 - 6.0F, 2.5F, 12.0F, Color.WHITE);
   }

   private void c(float var1, float var2) {
      this.a(var1 + 2.0F, var2 - 6.0F, var1 + 2.0F, var2 + 6.0F, var1 - 5.0F, var2, Color.WHITE);
      RecoveredUtilsEA.a(var1 + 3.5F, var2 - 6.0F, 2.5F, 12.0F, Color.WHITE);
   }

   private void a(float var1, float var2, float var3, float var4, float var5, float var6, Color var7) {
      try (
         Path var8 = new Path();
         Paint var9 = RecoveredUtilsEA.a(var7);
      ) {
         var8.moveTo(var1, var2);
         var8.lineTo(var3, var4);
         var8.lineTo(var5, var6);
         var8.closePath();
         var9.setAntiAlias(true);
         RecoveredUtilsEA.e().drawPath(var8, var9);
      }
   }

   public boolean a(double var1, double var3, int var5) {
      if (this.a(var1, var3)) {
         this.i = this.i == RecoveredCD.InnerB.LOCAL ? RecoveredCD.InnerB.ONLINE : RecoveredCD.InnerB.LOCAL;
         this.l = 0.0F;
         this.u = 0.0F;
         return true;
      } else {
         float var6 = this.g();
         float var7 = this.e();
         float var8 = this.h();
         if (var1 >= (double)var6 && var1 <= (double)(var6 + var8) && var3 >= (double)var7 && var3 <= (double)(var7 + this.C)) {
            if (this.i == RecoveredCD.InnerB.ONLINE) {
               if (this.v >= 0 && this.v < this.p.size()) {
                  RecoveredMusicAI var16 = this.p.get(this.v);
                  if (var5 == 1) {
                     this.a(var16);
                  } else {
                     this.g.b(this.v);
                  }

                  return true;
               } else {
                  return true;
               }
            } else {
               if (var5 == 0 && this.m >= 0 && this.m < this.h.size()) {
                  this.g.a(this.h.get(this.m));
               }

               return true;
            }
         } else if (var5 != 0) {
            return super.mouseClicked(var1, var3, var5);
         } else {
            float var9 = this.f();
            float var10 = var6 + var8 / 2.0F;
            float var11 = var9 + 28.0F;
            if (RecoveredUtilsAd.a((int)var1, (int)var3, var6, var9, var6 + var8, var9 + 14.0F)) {
               this.x = true;
               this.a(var1, var6, var8);
               return true;
            } else if (RecoveredUtilsAd.a((int)var1, (int)var3, var10 - 48.0F, var11, var10 - 24.0F, var11 + 24.0F)) {
               this.g.t();
               return true;
            } else if (RecoveredUtilsAd.a((int)var1, (int)var3, var10 - 15.0F, var11, var10 + 15.0F, var11 + 30.0F)) {
               this.g.m();
               return true;
            } else if (RecoveredUtilsAd.a((int)var1, (int)var3, var10 + 24.0F, var11, var10 + 48.0F, var11 + 24.0F)) {
               this.g.s();
               return true;
            } else {
               Font var12 = RecoveredUtilsEBC.a(9.0F);
               float var13 = RecoveredUtilsEA.a("循环: " + this.g.j().a(), var12) + 16.0F;
               if (RecoveredUtilsAd.a((int)var1, (int)var3, var6, var11 + 4.0F, var6 + var13, var11 + 20.0F)) {
                  RecoveredMusicC.InnerA[] var17 = RecoveredMusicC.InnerA.values();
                  this.g.a(var17[(this.g.j().ordinal() + 1) % var17.length]);
                  return true;
               } else {
                  float var14 = RecoveredUtilsEA.a("随机", var12) + 16.0F;
                  if (RecoveredUtilsAd.a((int)var1, (int)var3, var6 + var13 + 6.0F, var11 + 4.0F, var6 + var13 + 6.0F + var14, var11 + 20.0F)) {
                     this.g.a(!this.g.i());
                     return true;
                  } else {
                     float var15 = var6 + var8 - 98.0F;
                     if (RecoveredUtilsAd.a((int)var1, (int)var3, var15 - 6.0F, var11, var15 + 74.0F, var11 + 20.0F)) {
                        this.y = true;
                        this.a(var1, var15);
                        return true;
                     } else {
                        return super.mouseClicked(var1, var3, var5);
                     }
                  }
               }
            }
         }
      }
   }

   public boolean a(double var1, double var3, int var5, double var6, double var8) {
      float var10 = this.g();
      float var11 = this.h();
      if (this.x) {
         this.a(var1, var10, var11);
         return true;
      } else if (this.y) {
         this.a(var1, var10 + var11 - 98.0F);
         return true;
      } else {
         return super.mouseDragged(var1, var3, var5, var6, var8);
      }
   }

   public boolean b(double var1, double var3, int var5) {
      if (this.x) {
         this.x = false;
         long var6 = this.g.c().j();
         if (var6 > 0L && this.z >= 0.0F) {
            this.g.c().a((long)(this.z * (float)var6));
         }

         this.z = -1.0F;
         return true;
      } else if (this.y) {
         this.y = false;
         return true;
      } else {
         return super.mouseReleased(var1, var3, var5);
      }
   }

   private void a(double var1, float var3, float var4) {
      float var5 = (float)((var1 - (double)var3) / (double)var4);
      this.z = Math.max(0.0F, Math.min(1.0F, var5));
   }

   private void a(double var1, float var3) {
      float var4 = (float)((var1 - (double)var3) / 70.0);
      this.g.a(Math.max(0.0F, Math.min(1.0F, var4)));
   }

   public boolean a(double var1, double var3, double var5) {
      if (this.i == RecoveredCD.InnerB.ONLINE) {
         this.u -= (float)var5 * 24.0F;
         float var8 = Math.max(0.0F, (float)this.p.size() * 22.0F - this.C);
         this.u = Math.max(0.0F, Math.min(var8, this.u));
         return true;
      } else {
         this.l -= (float)var5 * 24.0F;
         float var7 = Math.max(0.0F, (float)this.h.size() * 22.0F - this.C);
         this.l = Math.max(0.0F, Math.min(var7, this.l));
         return true;
      }
   }

   public boolean a(int var1, int var2, int var3) {
      if (var1 == 256) {
         this.b();
         return true;
      } else if (var1 == 258) {
         this.i = this.i == RecoveredCD.InnerB.LOCAL ? RecoveredCD.InnerB.ONLINE : RecoveredCD.InnerB.LOCAL;
         return true;
      } else if (var1 == 294) {
         this.g.l();
         this.d();
         return true;
      } else if (var1 == 259) {
         if (this.i == RecoveredCD.InnerB.ONLINE) {
            if (!this.o.isEmpty()) {
               this.o = this.o.substring(0, this.o.length() - 1);
            }
         } else if (!this.j.isEmpty()) {
            this.j = this.j.substring(0, this.j.length() - 1);
            this.d();
         }

         return true;
      } else if (var1 != 257 && var1 != 335) {
         if (var1 == 32) {
            this.g.m();
            return true;
         } else if (var1 == 265) {
            this.l -= 22.0F;
            this.u -= 22.0F;
            return true;
         } else if (var1 == 264) {
            this.l += 22.0F;
            this.u += 22.0F;
            return true;
         } else if (var1 == 86 && (var3 & 2) != 0 && this.i == RecoveredCD.InnerB.ONLINE) {
            try {
               String var4 = Minecraft.getInstance().keyboardHandler.getClipboard();
               if (var4 != null && !var4.isBlank()) {
                  this.o = this.o + var4.trim();
               }
            } catch (Throwable var5) {
            }

            return true;
         } else {
            return super.keyPressed(var1, var2, var3);
         }
      } else {
         if (this.i == RecoveredCD.InnerB.ONLINE) {
            this.l();
         } else {
            this.g.m();
         }

         return true;
      }
   }

   public boolean a(char var1, int var2) {
      if (var1 >= ' ' && var1 != 127) {
         if (this.i == RecoveredCD.InnerB.ONLINE) {
            if (this.o.length() < 200) {
               this.o = this.o + var1;
            }
         } else {
            this.j = this.j + var1;
            this.d();
         }

         return true;
      } else {
         return super.charTyped(var1, var2);
      }
   }

   public void b() {
      this.g.l();
      super.onClose();
   }

   public boolean c() {
      return false;
   }

   private static Color m() {
      try {
         HUDModule var0 = EixClient.a().g().a(HUDModule.class);
         if (var0 != null) {
            return HUDModule.p();
         }
      } catch (Throwable var1) {
      }

      return new Color(102, 255, 209);
   }

   private interface InnerA {
      void paint(float var1, float var2);
   }

   private static enum InnerB {
      LOCAL,
      ONLINE;
   }
}
