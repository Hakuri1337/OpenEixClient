package com.heypixel.heypixelmod.obsoverlay.utils.renderer.text;

import com.heypixel.heypixelmod.obsoverlay.utils.renderer.RecoveredUtilsRendererG;
import java.awt.Color;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.util.HashMap;
import net.minecraft.ChatFormatting;
import net.minecraft.client.renderer.texture.AbstractTexture;
import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBTTFontinfo;
import org.lwjgl.stb.STBTTPackContext;
import org.lwjgl.stb.STBTTPackedchar;
import org.lwjgl.stb.STBTruetype;
import org.lwjgl.stb.STBTTPackedchar.Buffer;
import org.lwjgl.system.MemoryStack;

public class RecoveredUtilsRendererTextD {
   private final int b;
   private final float c;
   private final float d;
   private final CharData[] e;
   private final int f;
   private final HashMap<String, Double> g = new HashMap<>();
   public AbstractTexture a;

   public RecoveredUtilsRendererTextD(ByteBuffer var1, int var2, int var3, int var4, int var5) {
      this.b = var2;
      this.f = var3;
      STBTTFontinfo var6 = STBTTFontinfo.create();
      STBTruetype.stbtt_InitFont(var6, var1);
      this.e = new CharData[var4 + 1 - var3];
      Buffer var7 = STBTTPackedchar.create(this.e.length);
      ByteBuffer var8 = BufferUtils.createByteBuffer(var5 * var5);
      STBTTPackContext var9 = STBTTPackContext.create();
      STBTruetype.stbtt_PackBegin(var9, var8, var5, var5, 0, 1);
      STBTruetype.stbtt_PackSetOversampling(var9, 2, 2);
      STBTruetype.stbtt_PackFontRange(var9, var1, 0, (float)var2, this.f, var7);
      STBTruetype.stbtt_PackEnd(var9);
      this.a = new RecoveredUtilsRendererTextB(
         var5, var5, var8, RecoveredUtilsRendererTextB.InnerB.A, RecoveredUtilsRendererTextB.InnerA.Linear, RecoveredUtilsRendererTextB.InnerA.Linear
      );
      this.c = STBTruetype.stbtt_ScaleForPixelHeight(var6, (float)var2);

      try (MemoryStack var10 = MemoryStack.stackPush()) {
         IntBuffer var11 = var10.mallocInt(1);
         STBTruetype.stbtt_GetFontVMetrics(var6, var11, null, null);
         this.d = (float)var11.get(0);
      }

      for (int var17 = 0; var17 < this.e.length; var17++) {
         STBTTPackedchar var12 = var7.get(var17);
         float var13 = 1.0F / (float)var5;
         float var14 = 1.0F / (float)var5;
         this.e[var17] = new CharData(
            var12.xoff(),
            var12.yoff(),
            var12.xoff2(),
            var12.yoff2(),
            (float)var12.x0() * var13,
            (float)var12.y0() * var14,
            (float)var12.x1() * var13,
            (float)var12.y1() * var14,
            var12.xadvance()
         );
      }
   }

   public double a(String var1) {
      if (this.g.containsKey(var1)) {
         return this.g.get(var1);
      } else {
         double var2 = 0.0;

         for (int var4 = 0; var4 < var1.length(); var4++) {
            int var5 = var1.charAt(var4) - this.f;
            if (var5 == 167 && var4 + 1 < var1.length()) {
               var4++;
            } else {
               if (var5 >= this.e.length) {
                  var5 = 0;
               }

               CharData var6 = this.e[var5];
               var2 += (double)var6.xAdvance();
            }
         }

         this.g.put(var1, var2);
         return var2;
      }
   }

   public double a() {
      return (double)this.b;
   }

   public double a(RecoveredUtilsRendererG var1, String var2, double var3, double var5, Color var7, double var8, boolean var10) {
      Color var11 = var7;
      var5 += (double)(this.d * this.c) * var8;

      for (int var12 = 0; var12 < var2.length(); var12++) {
         int var13 = var2.charAt(var12) - this.f;
         if (var13 == 167 && var12 + 1 < var2.length()) {
            char var17 = var2.charAt(var12 + 1);
            ChatFormatting var15 = ChatFormatting.getByCode(var17);
            if (var15 != null && var15.isColor() && !var10) {
               var11 = new Color(var15.getColor());
            }

            var12++;
         } else {
            if (var13 >= this.e.length) {
               var13 = 0;
            }

            CharData var14 = this.e[var13];
            var1.a(
               var1.a(var3 + (double)var14.x0() * var8, var5 + (double)var14.y0() * var8).a((double)var14.u0(), (double)var14.v0()).a(var11).b(),
               var1.a(var3 + (double)var14.x0() * var8, var5 + (double)var14.y1() * var8).a((double)var14.u0(), (double)var14.v1()).a(var11).b(),
               var1.a(var3 + (double)var14.x1() * var8, var5 + (double)var14.y1() * var8).a((double)var14.u1(), (double)var14.v1()).a(var11).b(),
               var1.a(var3 + (double)var14.x1() * var8, var5 + (double)var14.y0() * var8).a((double)var14.u1(), (double)var14.v0()).a(var11).b()
            );
            var3 += (double)var14.xAdvance() * var8;
         }
      }

      return var3;
   }
}
