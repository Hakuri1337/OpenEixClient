package com.heypixel.heypixelmod.obsoverlay.c;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.PostProcessModule;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAd;
import com.heypixel.heypixelmod.obsoverlay.utils.d.a.RecoveredUtilsDAA;
import com.heypixel.heypixelmod.obsoverlay.utils.e.RecoveredUtilsEA;
import com.heypixel.heypixelmod.obsoverlay.utils.e.a.RecoveredUtilsEAA;
import com.heypixel.heypixelmod.obsoverlay.utils.e.b.RecoveredUtilsEBC;
import io.github.humbleui.skija.Font;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.OptionsScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.client.gui.screens.worldselection.SelectWorldScreen;
import net.minecraft.network.chat.Component;

public class RecoveredCC extends Screen {
   private static final Minecraft a = Minecraft.getInstance();
   private final List<RecoveredCC.InnerA> b = new ArrayList<>();
   private final List<RecoveredCC.InnerB> c = new ArrayList<>();
   private final Random d = new Random();

   public RecoveredCC() {
      super(Component.nullToEmpty("EixClient"));
   }

   protected void a() {
      if (this.b.isEmpty()) {
         this.b.add(new RecoveredCC.InnerA("单人游戏", () -> a.setScreen(new SelectWorldScreen(this))));
         this.b.add(new RecoveredCC.InnerA("多人游戏", () -> a.setScreen(new JoinMultiplayerScreen(this))));
         this.b.add(new RecoveredCC.InnerA("游戏菜单", () -> a.setScreen(new OptionsScreen(this, a.options))));
         this.b.add(new RecoveredCC.InnerA("退出游戏", () -> a.close()));
      }

      if (this.c.isEmpty()) {
         for (int var1 = 0; var1 < 80; var1++) {
            this.c
               .add(
                  new RecoveredCC.InnerB(
                     this.d.nextFloat() * (float)this.width, this.d.nextFloat() * (float)this.height, this.c(), this.c(), 2.0F + this.d.nextFloat() * 2.0F
                  )
               );
         }
      }
   }

   public void b() {
      for (RecoveredCC.InnerB var2 : this.c) {
         var2.a = var2.a + var2.c;
         var2.b = var2.b + var2.d;
         if (var2.a < -10.0F || var2.a > (float)this.width + 10.0F || var2.b < -10.0F || var2.b > (float)this.height + 10.0F) {
            var2.a = this.d.nextFloat() * (float)this.width;
            var2.b = this.d.nextFloat() * (float)this.height;
            var2.c = this.c();
            var2.d = this.c();
         }
      }
   }

   public void a(GuiGraphics var1, int var2, int var3, float var4) {
      PostProcessModule var5 = EixClient.a().g().a(PostProcessModule.class);
      int var6 = var5 != null ? var5.p() : 0;
      float var7 = 160.0F;
      float var8 = 24.0F;
      float var9 = 10.0F;
      float var10 = 18.0F;
      float var11 = 56.0F;
      float var12 = var7 + var10 * 2.0F;
      float var13 = (float)this.b.size() * (var8 + var9) - var9 + var11 + var10;
      float var14 = (float)this.width / 2.0F - var12 / 2.0F;
      float var15 = (float)this.height / 2.0F - var13 / 2.0F;
      float var16 = 10.0F;
      RecoveredUtilsEAA.a(var1x -> {
         RecoveredUtilsEA.c();
         RecoveredUtilsEA.a((float)a.getWindow().getGuiScale());
         RecoveredUtilsEA.a("mainmenu/cat.png", 0.0F, 0.0F, (float)this.width, (float)this.height);

         for (RecoveredCC.InnerB var3x : this.c) {
            RecoveredUtilsEA.a(var3x.a, var3x.b, var3x.e / 2.0F, new Color(255, 255, 255, 80));
         }

         RecoveredUtilsEA.d();
      });
      if (var6 > 0) {
         RecoveredUtilsDAA.a.a(var6);
      }

      RecoveredUtilsEAA.a(var13x -> {
         RecoveredUtilsEA.c();
         RecoveredUtilsEA.a((float)a.getWindow().getGuiScale());
         RecoveredUtilsEA.c(var14, var15, var12, var13, var16);
         if (var6 > 0) {
            RecoveredUtilsEA.b(var14, var15, var12, var13, var16);
         }

         RecoveredUtilsEA.a(var14, var15, var12, var13, var16, new Color(0, 0, 0, 90));
         this.a(var14, var15);
         float var14x = var15 + var11;
         Font var15x = RecoveredUtilsEBC.a(16.0F);

         for (RecoveredCC.InnerA var17 : this.b) {
            var17.c = (float)this.width / 2.0F - var7 / 2.0F;
            var17.d = var14x;
            var17.e = var7;
            var17.f = var8;
            var17.a(var2, var3, var15x);
            var14x += var8 + var9;
         }

         RecoveredUtilsEA.a();
         RecoveredUtilsEA.d();
      });
      super.render(var1, var2, var3, var4);
   }

   public boolean a(double var1, double var3, int var5) {
      if (var5 == 0) {
         for (RecoveredCC.InnerA var7 : this.b) {
            if (var7.a((int)var1, (int)var3)) {
               var7.b.run();
               return true;
            }
         }
      }

      return super.mouseClicked(var1, var3, var5);
   }

   private void a(float var1, float var2) {
      String var3 = "EixClient";
      int var4 = Math.min(5, var3.length());
      String var5 = var3.substring(0, var4);
      String var6 = var3.substring(var4);
      Color var7 = new Color(120, 180, 255, 255);
      Color var8 = new Color(255, 255, 255, 255);
      Font var9 = RecoveredUtilsEBC.a(28.0F);
      float var10 = RecoveredUtilsEA.a(var5, var9);
      float var11 = RecoveredUtilsEA.a(var6, var9);
      float var12 = var10 + var11;
      float var13 = (float)this.width / 2.0F - var12 / 2.0F;
      float var14 = var2 + 18.0F;
      RecoveredUtilsEA.a(var5, var13, var14, var7, var9);
      RecoveredUtilsEA.a(var6, var13 + var10, var14, var8, var9);
   }

   private float c() {
      float var1 = 0.3F + this.d.nextFloat() * 0.6F;
      return this.d.nextBoolean() ? var1 : -var1;
   }

   private static class InnerA {
      private final String a;
      private final Runnable b;
      private float c;
      private float d;
      private float e;
      private float f;

      private InnerA(String var1, Runnable var2) {
         this.a = var1;
         this.b = var2;
      }

      private void a(int var1, int var2, Font var3) {
         boolean var4 = this.a(var1, var2);
         Color var5 = var4 ? new Color(30, 30, 35, 170) : new Color(20, 20, 25, 140);
         RecoveredUtilsEA.a(this.c, this.d, this.e, this.f, 6.0F, var5);
         RecoveredUtilsEA.d(this.a, this.c + this.e / 2.0F, this.d + this.f / 2.0F, new Color(255, 255, 255, 230), var3);
      }

      private boolean a(int var1, int var2) {
         return RecoveredUtilsAd.b(var1, var2, this.c, this.d, this.e, this.f);
      }
   }

   private static class InnerB {
      private float a;
      private float b;
      private float c;
      private float d;
      private final float e;

      private InnerB(float var1, float var2, float var3, float var4, float var5) {
         this.a = var1;
         this.b = var2;
         this.c = var3;
         this.d = var4;
         this.e = var5;
      }
   }
}
