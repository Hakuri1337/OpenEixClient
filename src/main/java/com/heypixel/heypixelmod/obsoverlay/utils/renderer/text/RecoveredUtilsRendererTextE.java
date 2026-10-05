package com.heypixel.heypixelmod.obsoverlay.utils.renderer.text;

import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.platform.NativeImage.Format;
import com.mojang.blaze3d.systems.RenderSystem;
import it.unimi.dsi.fastutil.chars.Char2ObjectArrayMap;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.lang.reflect.Field;
import java.nio.IntBuffer;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

class RecoveredUtilsRendererTextE {
   private static final Minecraft i = Minecraft.getInstance();
   final char a;
   final char b;
   final Font c;
   final ResourceLocation d;
   final int e;
   private final Char2ObjectArrayMap<Glyph> j = new Char2ObjectArrayMap<>();
   int f;
   int g;
   boolean h = false;

   public RecoveredUtilsRendererTextE(char var1, char var2, Font var3, ResourceLocation var4, int var5) {
      this.a = var1;
      this.b = var2;
      this.c = var3;
      this.d = var4;
      this.e = var5;
   }

   public static long a(NativeImage var0) {
      return ((com.heypixel.heypixelmod.mixin.O.accessors.NativeImageAccessor)(Object)var0).getPixels();
   }

   public static long b(NativeImage var0) {
      return ((com.heypixel.heypixelmod.mixin.O.accessors.NativeImageAccessor)(Object)var0).getPixels();
   }

   public static void a(ResourceLocation var0, BufferedImage var1) {
      try {
         int var2 = var1.getWidth();
         int var3 = var1.getHeight();
         NativeImage var4 = new NativeImage(Format.RGBA, var2, var3, false);
         long var5 = a(var4);

         try {
            Class.forName("net.minecraft.client.Minecraft").getDeclaredField("instance");
         } catch (ClassNotFoundException | NoSuchFieldException var21) {
            var5 = b(var4);
         }

         IntBuffer var7 = MemoryUtil.memIntBuffer(var5, var4.getWidth() * var4.getHeight());
         boolean var8 = false;
         WritableRaster var9 = var1.getRaster();
         ColorModel var10 = var1.getColorModel();
         int var11 = var9.getNumBands();
         int var12 = var9.getDataBuffer().getDataType();

         Object var13 = switch (var12) {
            case 0 -> new byte[var11];
            case 1 -> new short[var11];
            default -> throw new IllegalArgumentException("Unknown data buffer type: " + var12);
            case 3 -> new int[var11];
            case 4 -> new float[var11];
            case 5 -> new double[var11];
         };

         for (int var14 = 0; var14 < var3; var14++) {
            for (int var15 = 0; var15 < var2; var15++) {
               var9.getDataElements(var15, var14, var13);
               int var16 = var10.getAlpha(var13);
               int var17 = var10.getRed(var13);
               int var18 = var10.getGreen(var13);
               int var19 = var10.getBlue(var13);
               int var20 = var16 << 24 | var19 << 16 | var18 << 8 | var17;
               var7.put(var20);
            }
         }

         DynamicTexture var23 = new DynamicTexture(var4);
         var23.upload();
         RenderSystem.bindTexture(var23.getId());
         GL11.glTexParameteri(3553, 10241, 9729);
         GL11.glTexParameteri(3553, 10240, 9729);
         if (RenderSystem.isOnRenderThread()) {
            i.getTextureManager().register(var0, var23);
         } else {
            RenderSystem.recordRenderCall(() -> i.getTextureManager().register(var0, var23));
         }
      } catch (Throwable var22) {
         var22.printStackTrace();
      }
   }

   public Glyph a(char var1) {
      if (!this.h) {
         this.b();
      }

      return this.j.get(var1);
   }

   public void a() {
      i.getTextureManager().release(this.d);
      this.j.clear();
      this.f = -1;
      this.g = -1;
      this.h = false;
   }

   public boolean b(char var1) {
      return var1 >= this.a && var1 < this.b;
   }

   private Font c(char var1) {
      return this.c;
   }

   public void b() {
      if (!this.h) {
         int var1 = this.b - this.a - 1;
         int var2 = (int)(Math.ceil(Math.sqrt((double)var1)) * 1.5);
         this.j.clear();
         int var3 = 0;
         int var4 = 0;
         int var5 = 0;
         int var6 = 0;
         int var7 = 0;
         int var8 = 0;
         int var9 = 0;
         ArrayList<Glyph> var10 = new ArrayList();
         AffineTransform var11 = new AffineTransform();

         for (FontRenderContext var12 = new FontRenderContext(var11, true, false); var3 <= var1; var4++) {
            char var13 = (char)(this.a + var3);
            Font var14 = this.c(var13);
            Rectangle2D var15 = var14.getStringBounds(String.valueOf(var13), var12);
            int var16 = (int)Math.ceil(var15.getWidth());
            int var17 = (int)Math.ceil(var15.getHeight());
            var3++;
            var5 = Math.max(var5, var7 + var16);
            var6 = Math.max(var6, var8 + var17);
            if (var4 >= var2) {
               var7 = 0;
               var8 += var9 + this.e;
               var4 = 0;
               var9 = 0;
            }

            var9 = Math.max(var9, var17);
            var10.add(new Glyph(var7, var8, var16, var17, var13, this));
            var7 += var16 + this.e;
         }

         BufferedImage var18 = new BufferedImage(Math.max(var5 + this.e, 1), Math.max(var6 + this.e, 1), 2);
         this.f = var18.getWidth();
         this.g = var18.getHeight();
         Graphics2D var19 = var18.createGraphics();
         var19.setColor(new Color(255, 255, 255, 1));
         var19.fillRect(0, 0, this.f, this.g);
         var19.setColor(Color.WHITE);
         var19.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
         var19.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
         var19.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

         for (Glyph var21 : var10) {
            var19.setFont(this.c(var21.value()));
            FontMetrics var22 = var19.getFontMetrics();
            var19.drawString(String.valueOf(var21.value()), var21.u(), var21.v() + var22.getAscent());
            this.j.put(var21.value(), var21);
         }

         a(this.d, var18);
         this.h = true;
      }
   }
}
