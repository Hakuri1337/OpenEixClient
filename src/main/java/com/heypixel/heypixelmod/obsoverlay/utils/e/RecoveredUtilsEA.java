package com.heypixel.heypixelmod.obsoverlay.utils.e;

import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplS;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.PostProcessModule;
import com.heypixel.heypixelmod.obsoverlay.utils.d.a.RecoveredUtilsDAA;
import com.heypixel.heypixelmod.obsoverlay.utils.e.a.RecoveredUtilsEAA;
import com.heypixel.heypixelmod.obsoverlay.utils.e.c.RecoveredUtilsECA;
import com.mojang.blaze3d.platform.Window;
import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.ClipMode;
import io.github.humbleui.skija.FilterTileMode;
import io.github.humbleui.skija.Font;
import io.github.humbleui.skija.FontMetrics;
import io.github.humbleui.skija.Image;
import io.github.humbleui.skija.ImageFilter;
import io.github.humbleui.skija.Paint;
import io.github.humbleui.skija.PaintMode;
import io.github.humbleui.skija.Path;
import io.github.humbleui.skija.Shader;
import io.github.humbleui.skija.SurfaceOrigin;
import io.github.humbleui.types.Point;
import io.github.humbleui.types.RRect;
import io.github.humbleui.types.Rect;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.ForgeRegistries;

public class RecoveredUtilsEA {
   public static final Minecraft a = Minecraft.getInstance();
   private static final RecoveredUtilsECA b = new RecoveredUtilsECA();
   private static final ImageFilter c = ImageFilter.makeBlur(1.0F, 1.0F, FilterTileMode.DECAL);
   private static final ImageFilter d = ImageFilter.makeBlur(2.5F, 2.5F, FilterTileMode.DECAL);
   private static final Paint e = new Paint();
   private static final Paint f = new Paint();
   private static final Paint g = new Paint();
   private static final Pattern h = Pattern.compile("§.");
   private static final List<RecoveredUtilsEA.InnerB> i = new ArrayList<>();
   private static final int[] j = new int[]{
      -16777216, -16777046, -16733696, -16733526, -5636096, -5635926, -5614336, -5592406, -11184811, -11184641, -11141291, -11141121, -43691, -43521, -171, -1
   };

   private static Paint b(Color var0) {
      Paint var1 = e;
      var1.setMode(PaintMode.FILL);
      var1.setARGB(var0.getAlpha(), var0.getRed(), var0.getGreen(), var0.getBlue());
      return var1;
   }

   private static Paint a(Color var0, float var1) {
      Paint var2 = f;
      var2.setMode(PaintMode.STROKE);
      var2.setStrokeWidth(var1);
      var2.setARGB(var0.getAlpha(), var0.getRed(), var0.getGreen(), var0.getBlue());
      return var2;
   }

   private static String a(String var0) {
      return var0 != null && var0.indexOf(167) >= 0 ? h.matcher(var0).replaceAll("") : var0;
   }

   private static void g() {
      if (!i.isEmpty()) {
         Paint var0 = new Paint();

         for (RecoveredUtilsEA.InnerB var2 : i) {
            var0.setColor(var2.d.getRGB());
            var0.setImageFilter(var2.f > 1.5F ? d : c);
            if (var2.g) {
               a(e(), var2.a, var2.b, var2.c, var2.e, var0);
            } else {
               e().drawString(var2.a, var2.b, var2.c, var2.e, var0);
            }
         }

         var0.close();
         i.clear();
      }
   }

   public static void a() {
      g();
   }

   public static void a(float var0, float var1, float var2, float var3, Color var4) {
      e().drawRect(Rect.makeXYWH(var0, var1, var2, var3), b(var4));
   }

   public static void a(float var0, float var1, float var2, Color var3) {
      e().drawCircle(var0, var1, var2, b(var3));
   }

   public static void b(float var0, float var1, float var2, float var3, Color var4) {
      e().drawCircle(var0, var1, var2, a(var4, var3));
   }

   public static void a(float var0, float var1, float var2, float var3, float var4, Color var5) {
      e().drawRRect(RRect.makeXYWH(var0, var1, var2, var3, var4), b(var5));
   }

   public static void a(float var0, float var1, float var2, float var3, float var4, float var5, float var6, float var7, Color var8) {
      float[] var9 = new float[]{var4, var4, var5, var5, var6, var6, var7, var7};
      e().drawRRect(RRect.makeComplexXYWH(var0, var1, var2, var3, var9), b(var8));
   }

   public static void a(float var0, float var1, float var2, float var3) {
      Window var4 = Minecraft.getInstance().getWindow();
      Path var5 = new Path();
      var5.addRect(Rect.makeXYWH(var0, var1, var2, var3));
      c();
      e().clipPath(var5, ClipMode.INTERSECT, true);
      a(
         RecoveredUtilsDAA.b.b(),
         0.0F,
         0.0F,
         (float)var4.getWidth() / (float)var4.getGuiScale(),
         (float)var4.getHeight() / (float)var4.getGuiScale(),
         1.0F,
         SurfaceOrigin.BOTTOM_LEFT
      );
      d();
   }

   public static void a(float var0, float var1, float var2, float var3, float var4) {
      Window var5 = Minecraft.getInstance().getWindow();
      Path var6 = new Path();
      var6.addRRect(RRect.makeXYWH(var0, var1, var2, var3, var4));
      c();
      e().clipPath(var6, ClipMode.INTERSECT, true);
      a(
         RecoveredUtilsDAA.b.b(),
         0.0F,
         0.0F,
         (float)var5.getWidth() / (float)var5.getGuiScale(),
         (float)var5.getHeight() / (float)var5.getGuiScale(),
         1.0F,
         SurfaceOrigin.BOTTOM_LEFT
      );
      d();
   }

   public static void b(float var0, float var1, float var2, float var3, float var4) {
      Window var5 = Minecraft.getInstance().getWindow();
      Path var6 = new Path();
      var6.addRRect(RRect.makeXYWH(var0, var1, var2, var3, var4));
      c();
      e().clipPath(var6, ClipMode.INTERSECT, true);
      a(
         RecoveredUtilsDAA.a.b(),
         0.0F,
         0.0F,
         (float)var5.getWidth() / (float)var5.getGuiScale(),
         (float)var5.getHeight() / (float)var5.getGuiScale(),
         1.0F,
         SurfaceOrigin.BOTTOM_LEFT
      );
      d();
   }

   public static void b() {
      Window var0 = Minecraft.getInstance().getWindow();
      a(
         RecoveredUtilsDAA.a.b(),
         0.0F,
         0.0F,
         (float)var0.getWidth() / (float)var0.getGuiScale(),
         (float)var0.getHeight() / (float)var0.getGuiScale(),
         1.0F,
         SurfaceOrigin.BOTTOM_LEFT
      );
   }

   public static void c(float var0, float var1, float var2, float var3, float var4) {
      Paint var5 = g;
      var5.setMode(PaintMode.FILL);
      var5.setARGB(120, 0, 0, 0);
      var5.setImageFilter(d);
      c();
      a(var0, var1, var2, var3, var4, ClipMode.DIFFERENCE);
      e().drawRRect(RRect.makeXYWH(var0, var1, var2, var3, var4), var5);
      d();
   }

   public static void b(float var0, float var1, float var2, float var3, float var4, Color var5) {
      Paint var6 = g;
      var6.setMode(PaintMode.FILL);
      var6.setARGB(var5.getAlpha(), var5.getRed(), var5.getGreen(), var5.getBlue());
      var6.setImageFilter(d);
      c();
      a(var0, var1, var2, var3, var4, ClipMode.DIFFERENCE);
      e().drawRRect(RRect.makeXYWH(var0, var1, var2, var3, var4), var6);
      d();
   }

   public static void a(float var0, float var1, float var2, float var3, float var4, float var5, Color var6) {
      float var7 = var5 / 2.0F;
      Path var8 = new Path();
      var8.addRRect(RRect.makeXYWH(var0 + var7, var1 + var7, var2 - var5, var3 - var5, var4 - var7));
      e().drawPath(var8, a(var6, var5));
      var8.close();
   }

   public static void a(String var0, float var1, float var2, float var3, float var4) {
      var0 = "/assets/heypixel/vcx6svvqmet8/" + var0;
      if (b.a(var0)) {
         e().drawImageRect(b.b(var0), Rect.makeXYWH(var1, var2, var3, var4));
      }
   }

   public static void a(int var0, float var1, float var2, float var3, float var4, float var5, SurfaceOrigin var6) {
      if (b.a(var0, var3, var4, var6)) {
         Paint var7 = new Paint();
         var7.setAlpha((int)(255.0F * var5));
         e().drawImageRect(b.a(var0), Rect.makeXYWH(var1, var2, var3, var4), var7);
      }
   }

   public static void a(int var0, float var1, float var2, float var3, float var4, float var5) {
      a(var0, var1, var2, var3, var4, var5, SurfaceOrigin.TOP_LEFT);
   }

   public static void a(File var0, float var1, float var2, float var3, float var4) {
      if (b.a(var0)) {
         e().drawImageRect(b.b(var0.getName()), Rect.makeXYWH(var1, var2, var3, var4));
      }
   }

   public static void a(int var0, float var1, float var2, float var3, float var4, SurfaceOrigin var5) {
      if (b.a(var0, var3, var4, var5)) {
         e().drawImageRect(b.a(var0), Rect.makeXYWH(var1, var2, var3, var4));
      }
   }

   public static void a(int var0, float var1, float var2, float var3, float var4) {
      a(var0, var1, var2, var3, var4, SurfaceOrigin.TOP_LEFT);
   }

   public static void b(int var0, float var1, float var2, float var3, float var4, float var5) {
      Path var6 = new Path();
      var6.addRRect(RRect.makeXYWH(var1, var2, var3, var4, var5));
      c();
      e().clipPath(var6, ClipMode.INTERSECT, true);
      a(var0, var1, var2, var3, var4);
      d();
   }

   public static void a(String var0, float var1, float var2, float var3, float var4, float var5) {
      Path var6 = new Path();
      var6.addRRect(RRect.makeXYWH(var1, var2, var3, var4, var5));
      c();
      e().clipPath(var6, ClipMode.INTERSECT, true);
      a(var0, var1, var2, var3, var4);
      d();
   }

   public static void a(File var0, float var1, float var2, float var3, float var4, float var5) {
      Path var6 = new Path();
      var6.addRRect(RRect.makeXYWH(var1, var2, var3, var4, var5));
      c();
      e().clipPath(var6, ClipMode.INTERSECT, true);
      a(var0, var1, var2, var3, var4);
      d();
   }

   public static void a(int var0, float var1, float var2, float var3, float var4, float var5, float var6, SurfaceOrigin var7) {
      Path var8 = new Path();
      var8.addRRect(RRect.makeXYWH(var1, var2, var3, var4, var5));
      c();
      e().clipPath(var8, ClipMode.INTERSECT, true);
      a(var0, var1, var2, var3, var4, var6, var7);
      d();
   }

   public static void a(int var0, float var1, float var2, float var3, float var4, float var5, float var6) {
      a(var0, var1, var2, var3, var4, var5, var6, SurfaceOrigin.TOP_LEFT);
   }

   public static void a(AbstractClientPlayer var0, float var1, float var2, float var3, float var4, float var5) {
      if (var0 != null) {
         a(var0.getSkinTextureLocation(), var1, var2, var3, var4, var5);
      }
   }

   public static void a(ResourceLocation var0, float var1, float var2, float var3, float var4, float var5) {
      if (var0 != null) {
         AbstractTexture var6 = Minecraft.getInstance().getTextureManager().getTexture(var0);
         var6.bind();
         int var7 = var6.getId();
         if (var7 != -1 && b.a(var7, 64.0F, 64.0F, SurfaceOrigin.TOP_LEFT)) {
            Path var8 = new Path();
            var8.addRRect(RRect.makeXYWH(var1, var2, var3, var4, var5));
            Rect var9 = Rect.makeXYWH(8.0F, 8.0F, 8.0F, 8.0F);
            Rect var10 = Rect.makeXYWH(40.0F, 8.0F, 8.0F, 8.0F);
            Rect var11 = Rect.makeXYWH(var1, var2, var3, var4);
            c();
            e().clipPath(var8, ClipMode.INTERSECT, true);
            Image var12 = b.a(var7);
            if (var12 != null) {
               e().drawImageRect(var12, var9, var11, null, false);
               e().drawImageRect(var12, var10, var11, null, false);
            }

            d();
         }
      }
   }

   public static void a(AbstractClientPlayer var0, float var1, float var2, float var3) {
      if (var0 != null) {
         String var4 = var0.getSkinTextureLocation().getPath();
         if (b.a(var4)) {
            Rect var5 = Rect.makeXYWH(8.0F, 8.0F, 8.0F, 8.0F);
            Rect var6 = Rect.makeXYWH(40.0F, 8.0F, 8.0F, 8.0F);
            Rect var7 = Rect.makeXYWH(20.0F, 20.0F, 8.0F, 12.0F);
            Rect var8 = Rect.makeXYWH(20.0F, 36.0F, 8.0F, 12.0F);
            Rect var9 = Rect.makeXYWH(36.0F, 52.0F, 4.0F, 12.0F);
            Rect var10 = Rect.makeXYWH(52.0F, 52.0F, 4.0F, 12.0F);
            Rect var11 = Rect.makeXYWH(44.0F, 20.0F, 4.0F, 12.0F);
            Rect var12 = Rect.makeXYWH(44.0F, 36.0F, 4.0F, 12.0F);
            Rect var13 = Rect.makeXYWH(20.0F, 52.0F, 4.0F, 12.0F);
            Rect var14 = Rect.makeXYWH(4.0F, 52.0F, 4.0F, 12.0F);
            Rect var15 = Rect.makeXYWH(4.0F, 20.0F, 4.0F, 12.0F);
            Rect var16 = Rect.makeXYWH(4.0F, 36.0F, 4.0F, 12.0F);
            c();
            a(var1, var2, var3);
            e().drawImageRect(b.b(var4), var5, Rect.makeXYWH(var1 + var9.getWidth(), var2, var5.getWidth(), var5.getHeight()), null, false);
            e().drawImageRect(b.b(var4), var6, Rect.makeXYWH(var1 + var9.getWidth(), var2, var6.getWidth(), var6.getHeight()), null, false);
            e().drawImageRect(b.b(var4), var7, Rect.makeXYWH(var1 + var9.getWidth(), var2 + var5.getHeight(), var7.getWidth(), var7.getHeight()), null, false);
            e().drawImageRect(b.b(var4), var8, Rect.makeXYWH(var1 + var9.getWidth(), var2 + var6.getHeight(), var8.getWidth(), var8.getHeight()), null, false);
            e().drawImageRect(b.b(var4), var9, Rect.makeXYWH(var1, var2 + var5.getHeight(), var9.getWidth(), var9.getHeight()), null, false);
            e().drawImageRect(b.b(var4), var10, Rect.makeXYWH(var1, var2 + var6.getHeight(), var10.getWidth(), var10.getHeight()), null, false);
            e()
               .drawImageRect(
                  b.b(var4),
                  var11,
                  Rect.makeXYWH(var1 + var9.getWidth() + var7.getWidth(), var2 + var5.getHeight(), var11.getWidth(), var11.getHeight()),
                  null,
                  false
               );
            e()
               .drawImageRect(
                  b.b(var4),
                  var12,
                  Rect.makeXYWH(var1 + var10.getWidth() + var8.getWidth(), var2 + var6.getHeight(), var12.getWidth(), var12.getHeight()),
                  null,
                  false
               );
            e()
               .drawImageRect(
                  b.b(var4),
                  var13,
                  Rect.makeXYWH(var1 + var9.getWidth(), var2 + var5.getHeight() + var7.getHeight(), var13.getWidth(), var13.getHeight()),
                  null,
                  false
               );
            e()
               .drawImageRect(
                  b.b(var4),
                  var14,
                  Rect.makeXYWH(var1 + var10.getWidth(), var2 + var6.getHeight() + var8.getHeight(), var14.getWidth(), var14.getHeight()),
                  null,
                  false
               );
            e()
               .drawImageRect(
                  b.b(var4),
                  var15,
                  Rect.makeXYWH(var1 + var9.getWidth() + var13.getWidth(), var2 + var5.getHeight() + var7.getHeight(), var15.getWidth(), var15.getHeight()),
                  null,
                  false
               );
            e()
               .drawImageRect(
                  b.b(var4),
                  var16,
                  Rect.makeXYWH(var1 + var10.getWidth() + var14.getWidth(), var2 + var6.getHeight() + var8.getHeight(), var16.getWidth(), var16.getHeight()),
                  null,
                  false
               );
            d();
         }
      }
   }

   public static void b(float var0, float var1, float var2, float var3, float var4, float var5, Color var6) {
      e().drawArc(var0 - var2, var1 - var2, var0 + var2, var1 + var2, var3 - 90.0F, var4, false, a(var6, var5));
   }

   public static void c(float var0, float var1, float var2, float var3, float var4, Color var5) {
      Paint var6 = a(var5, var4);
      var6.setAntiAlias(true);
      e().drawLine(var0, var1, var2, var3, var6);
   }

   public static void a(float var0, float var1, float var2, float var3, float var4, Color var5, Color var6) {
      long var7 = System.nanoTime();
      double var9 = 6.0E-10;
      double var11 = (double)var7 * var9 % (Math.PI * 2);
      float var13 = Math.max(var2, var3);
      Path var14 = new Path();
      var14.addRRect(RRect.makeXYWH(var0, var1, var2, var3, var4));
      float var15 = var0 + var2 / 2.0F - var13 / 2.0F * (float)Math.cos(var11);
      float var16 = var1 + var3 / 2.0F - var13 / 2.0F * (float)Math.sin(var11);
      float var17 = var0 + var2 / 2.0F + var13 / 2.0F * (float)Math.cos(var11);
      float var18 = var1 + var3 / 2.0F + var13 / 2.0F * (float)Math.sin(var11);
      int var19 = io.github.humbleui.skija.Color.makeARGB(var5.getAlpha(), var5.getRed(), var5.getGreen(), var5.getBlue());
      int var20 = io.github.humbleui.skija.Color.makeARGB(var6.getAlpha(), var6.getRed(), var6.getGreen(), var6.getBlue());
      int var21 = io.github.humbleui.skija.Color.makeARGB(
         var5.getAlpha(), (var5.getRed() + var6.getRed()) / 2, (var5.getGreen() + var6.getGreen()) / 2, (var5.getBlue() + var6.getBlue()) / 2
      );
      Paint var22 = new Paint();
      var22.setShader(
         Shader.makeLinearGradient(new Point(var15, var16), new Point(var17, var18), new int[]{var19, var21, var20}, new float[]{0.0F, 0.5F, 1.0F})
      );
      e().drawPath(var14, var22);
   }

   public static void a(Path var0, ClipMode var1, boolean var2) {
      e().clipPath(var0, var1, var2);
   }

   public static void a(Path var0) {
      e().clipPath(var0, ClipMode.INTERSECT, true);
   }

   public static void a(float var0, float var1, float var2, float var3, float var4, ClipMode var5) {
      Path var6 = new Path();
      var6.addRRect(RRect.makeXYWH(var0, var1, var2, var3, var4));
      a(var6, var5, true);
   }

   public static void a(float var0, float var1, float var2, float var3, float var4, float var5, float var6, float var7) {
      float[] var8 = new float[]{var4, var4, var5, var5, var6, var6, var7, var7};
      Path var9 = new Path();
      var9.addRRect(RRect.makeComplexXYWH(var0, var1, var2, var3, var8));
      a(var9, ClipMode.INTERSECT, true);
   }

   public static void d(float var0, float var1, float var2, float var3, float var4) {
      a(var0, var1, var2, var3, var4, ClipMode.INTERSECT);
   }

   private static int a(char var0) {
      if (var0 >= '0' && var0 <= '9') {
         return var0 - 48;
      } else if (var0 >= 'a' && var0 <= 'f') {
         return var0 - 97 + 10;
      } else {
         return var0 >= 65 && var0 <= 70 ? var0 - 65 + 10 : -1;
      }
   }

   public static float a(String var0, Font var1) {
      if (var0 != null && !var0.isEmpty()) {
         float var2 = 0.0F;
         StringBuilder var3 = new StringBuilder();

         for (int var4 = 0; var4 < var0.length(); var4++) {
            char var5 = var0.charAt(var4);
            if (var5 == 167 && var4 + 1 < var0.length()) {
               var4++;
            } else {
               var3.append(var5);
            }
         }

         return var1.measureTextWidth(var3.toString());
      } else {
         return 0.0F;
      }
   }

   private static void a(Canvas var0, String var1, float var2, float var3, Font var4, Paint var5) {
      float var6 = var2;
      int var7 = var5.getColor();

      for (int var8 = 0; var8 < var1.length(); var8++) {
         char var9 = var1.charAt(var8);
         if (var9 == 167 && var8 + 1 < var1.length()) {
            char var14 = var1.charAt(var8 + 1);
            int var15 = a(var14);
            if (var15 != -1) {
               var5.setColor(j[var15]);
            } else if (var14 == 'r') {
               var5.setColor(var7);
            }

            var8++;
         } else {
            int var10 = var8;

            StringBuilder var11;
            for (var11 = new StringBuilder(); var10 < var1.length(); var10++) {
               char var12 = var1.charAt(var10);
               if (var12 == 167 && var10 + 1 < var1.length()) {
                  break;
               }

               var11.append(var12);
            }

            String var16 = var11.toString();
            var0.drawString(var16, var6, var3, var4, var5);
            var6 += var4.measureTextWidth(var16);
            var8 = var10 - 1;
         }
      }

      var5.setColor(var7);
   }

   public static void a(String var0, float var1, float var2, Color var3, Font var4) {
      if (var0 != null && !var0.isEmpty() && var4 != null) {
         Rect var5 = var4.measureText(a(var0));
         float var6 = var1 - var5.getLeft();
         float var7 = var2 - var5.getTop();
         if (PostProcessModule.s()) {
            i.add(new RecoveredUtilsEA.InnerB(var0, var6, var7, var3, var4, 1.0F, true));
         }

         Paint var8 = a(var3);
         a(e(), var0, var6, var7, var4, var8);
         var8.close();
      }
   }

   public static void b(String var0, float var1, float var2, Color var3, Font var4) {
      Rect var5 = var4.measureText(var0);
      float var6 = var1 - var5.getLeft();
      float var7 = var2 - var5.getTop();
      if (PostProcessModule.s()) {
         i.add(new RecoveredUtilsEA.InnerB(var0, var6, var7, var3, var4, 1.0F, false));
      }

      e().drawString(var0, var6, var7, var4, a(var3));
      e().drawString(var0, var1 - var5.getLeft() - var5.getWidth() / 2.0F, var2 - var5.getTop(), var4, a(var3));
   }

   public static void c(String var0, float var1, float var2, Color var3, Font var4) {
      FontMetrics var5 = var4.getMetrics();
      Rect var6 = var4.measureText(var0);
      float var7 = var2 + (var5.getAscent() - var5.getDescent()) / 2.0F - var5.getAscent();
      float var8 = var1 - var6.getLeft();
      if (PostProcessModule.s()) {
         i.add(new RecoveredUtilsEA.InnerB(var0, var8, var7, var3, var4, 1.0F, false));
      }

      e().drawString(var0, var8, var7, var4, a(var3));
   }

   public static void d(String var0, float var1, float var2, Color var3, Font var4) {
      Rect var5 = var4.measureText(var0);
      FontMetrics var6 = var4.getMetrics();
      float var7 = var1 - var5.getLeft() - var5.getWidth() / 2.0F;
      float var8 = var2 + (var6.getAscent() - var6.getDescent()) / 2.0F - var6.getAscent();
      if (PostProcessModule.s()) {
         i.add(new RecoveredUtilsEA.InnerB(var0, var7, var8, var3, var4, 1.0F, false));
      }

      e().drawString(var0, var7, var8, var4, a(var3));
   }

   public static Rect b(String var0, Font var1) {
      return var1.measureText(var0);
   }

   public static void e(String var0, float var1, float var2, Color var3, Font var4) {
      Rect var5 = var4.measureText(var0);
      float var6 = var1 - var5.getLeft();
      float var7 = var2 - var5.getTop();
      if (PostProcessModule.s()) {
         i.add(new RecoveredUtilsEA.InnerB(var0, var6, var7, var3, var4, 2.5F, false));
      }
   }

   public static String a(String var0, Font var1, float var2) {
      if (var0 != null && !var0.isEmpty()) {
         float var3 = b("...", var1).getWidth();
         if (var2 <= var3) {
            return "";
         } else if (b(var0, var1).getWidth() <= var2) {
            return var0;
         } else {
            int var4 = 0;
            int var5 = var0.length();

            while (var4 < var5) {
               int var6 = var4 + var5 + 1 >>> 1;
               if (b(var0.substring(0, var6), var1).getWidth() <= var2 - var3) {
                  var4 = var6;
               } else {
                  var5 = var6 - 1;
               }
            }

            return var0.substring(0, var4) + "...";
         }
      } else {
         return "";
      }
   }

   public static Paint a(Color var0) {
      Paint var1 = new Paint();
      var1.setARGB(var0.getAlpha(), var0.getRed(), var0.getGreen(), var0.getBlue());
      return var1;
   }

   public static void c() {
      e().save();
   }

   public static void d() {
      e().restore();
   }

   public static void a(float var0) {
      e().scale(var0, var0);
   }

   public static void a(float var0, float var1, float var2) {
      e().translate(var0, var1);
      e().scale(var2, var2);
      e().translate(-var0, -var1);
   }

   public static void e(float var0, float var1, float var2, float var3, float var4) {
      float var5 = var0 + var2 / 2.0F;
      float var6 = var1 + var3 / 2.0F;
      e().translate(var5, var6);
      e().scale(var4, var4);
      e().translate(-var5, -var6);
   }

   public static void a(float var0, float var1) {
      e().translate(var0, var1);
   }

   public static void f(float var0, float var1, float var2, float var3, float var4) {
      float var5 = var0 + var2 / 2.0F;
      float var6 = var1 + var3 / 2.0F;
      e().translate(var5, var6);
      e().rotate(var4);
      e().translate(-var5, -var6);
   }

   public static void a(int var0) {
      Paint var1 = new Paint();
      var1.setAlpha(var0);
      e().saveLayer(null, var1);
   }

   public static Canvas e() {
      return RecoveredUtilsEAA.a();
   }

   public static RecoveredUtilsECA f() {
      return b;
   }

   public static void a(TextureAtlasSprite var0, float var1, float var2, float var3, float var4) {
      if (var0 != null) {
         ResourceLocation var5 = var0.atlasLocation();
         AbstractTexture var6 = a.getTextureManager().getTexture(var5);
         int var7 = var6.getId();
         a(var7, var1, var2, var3, var4);
      }
   }

   public static void a(MobEffect var0, float var1, float var2, float var3, float var4) {
      ResourceLocation var5 = ForgeRegistries.MOB_EFFECTS.getKey(var0);
      if (var5 != null) {
         ResourceLocation var6 = new ResourceLocation(var5.getNamespace(), "textures/mob_effect/" + var5.getPath() + ".png");
         AbstractTexture var7 = a.getTextureManager().getTexture(var6);
         int var8 = var7.getId();
         a(var8, var1, var2, var3, var4);
      }
   }

   public static class InnerA {
      @EventTarget(
         a = 1
      )
      public void onShader(RecoveredEventsImplS var1) {
         RecoveredUtilsEA.g();
      }
   }

   private static class InnerB {
      String a;
      float b;
      float c;
      Color d;
      Font e;
      float f;
      boolean g;

      public InnerB(String var1, float var2, float var3, Color var4, Font var5, float var6, boolean var7) {
         this.a = var1;
         this.b = var2;
         this.c = var3;
         this.d = var4;
         this.e = var5;
         this.f = var6;
         this.g = var7;
      }
   }
}
