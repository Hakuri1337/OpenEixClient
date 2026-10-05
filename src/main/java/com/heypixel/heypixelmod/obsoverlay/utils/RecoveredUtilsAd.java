package com.heypixel.heypixelmod.obsoverlay.utils;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexBuffer;
import com.mojang.blaze3d.vertex.BufferBuilder.RenderedBuffer;
import com.mojang.blaze3d.vertex.VertexFormat.Mode;
import java.awt.Color;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.util.FastColor.ARGB32;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

public class RecoveredUtilsAd {
   private static final Minecraft a = Minecraft.getInstance();
   private static final AABB b = new AABB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);

   public static int a(int var0, float var1) {
      int var2 = RecoveredUtilsS.a((int)(var1 * 255.0F), 0, 255) << 24;
      var2 |= RecoveredUtilsS.a(var0 >> 16 & 0xFF, 0, 255) << 16;
      var2 |= RecoveredUtilsS.a(var0 >> 8 & 0xFF, 0, 255) << 8;
      return var2 | RecoveredUtilsS.a(var0 & 0xFF, 0, 255);
   }

   public static void a(PoseStack var0, float var1, float var2, float var3, float var4, float var5, int var6) {
      GL11.glEnable(3042);
      GL11.glBlendFunc(770, 771);
      GL11.glDisable(2929);
      GL11.glDepthMask(false);
      GL11.glEnable(2848);
      RenderSystem.setShader(GameRenderer::getPositionColorShader);
      Matrix4f var7 = var0.last().pose();
      float var8 = (float)(var6 >> 24 & 0xFF) / 255.0F;
      float var9 = (float)(var6 >> 16 & 0xFF) / 255.0F;
      float var10 = (float)(var6 >> 8 & 0xFF) / 255.0F;
      float var11 = (float)(var6 & 0xFF) / 255.0F;
      BufferBuilder var12 = Tesselator.getInstance().getBuilder();
      var12.begin(Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
      var12.vertex(var7, var1, var2, 0.0F).color(var9, var10, var11, var8).endVertex();
      var12.vertex(var7, var1 - var3 / var4, var2 + var3, 0.0F).color(var9, var10, var11, var8).endVertex();
      var12.vertex(var7, var1, var2 + var3 / var5, 0.0F).color(var9, var10, var11, var8).endVertex();
      var12.vertex(var7, var1 + var3 / var4, var2 + var3, 0.0F).color(var9, var10, var11, var8).endVertex();
      var12.vertex(var7, var1, var2, 0.0F).color(var9, var10, var11, var8).endVertex();
      Tesselator.getInstance().end();
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glDisable(3042);
      GL11.glEnable(2929);
      GL11.glDepthMask(true);
      GL11.glDisable(2848);
   }

   public static int a(int var0, float var1, float var2, float var3) {
      float var4 = (float)((System.currentTimeMillis() + (long)var0) % (long)((int)var3)) / var3;
      return Color.HSBtoRGB(var4, var1, var2);
   }

   public static Color b(int var0, float var1, float var2, float var3) {
      float var4 = (float)((System.currentTimeMillis() + (long)var0) % (long)((int)var3)) / var3;
      return Color.getHSBColor(var4, var1, var2);
   }

   public static BlockPos a() {
      Camera var0 = a.getBlockEntityRenderDispatcher().camera;
      return var0.getBlockPosition();
   }

   public static Vec3 b() {
      Camera var0 = a.getBlockEntityRenderDispatcher().camera;
      return var0.getPosition();
   }

   public static RecoveredUtilsAc c() {
      return RecoveredUtilsAc.a(a());
   }

   public static void a(PoseStack var0) {
      a(var0, c());
   }

   public static void a(PoseStack var0, RecoveredUtilsAc var1) {
      Vec3 var2 = var1.b().subtract(b());
      var0.translate(var2.x, var2.y, var2.z);
   }

   public static void a(PoseStack var0, float var1, float var2, float var3, float var4, int var5) {
      a(var0.last().pose(), var1, var2, var3, var4, var5);
   }

   private static void a(Matrix4f var0, float var1, float var2, float var3, float var4, int var5) {
      if (var1 < var3) {
         float var6 = var1;
         var1 = var3;
         var3 = var6;
      }

      if (var2 < var4) {
         float var11 = var2;
         var2 = var4;
         var4 = var11;
      }

      float var12 = (float)(var5 >> 24 & 0xFF) / 255.0F;
      float var7 = (float)(var5 >> 16 & 0xFF) / 255.0F;
      float var8 = (float)(var5 >> 8 & 0xFF) / 255.0F;
      float var9 = (float)(var5 & 0xFF) / 255.0F;
      BufferBuilder var10 = Tesselator.getInstance().getBuilder();
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      RenderSystem.setShader(GameRenderer::getPositionColorShader);
      var10.begin(Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
      var10.vertex(var0, var1, var4, 0.0F).color(var7, var8, var9, var12).endVertex();
      var10.vertex(var0, var3, var4, 0.0F).color(var7, var8, var9, var12).endVertex();
      var10.vertex(var0, var3, var2, 0.0F).color(var7, var8, var9, var12).endVertex();
      var10.vertex(var0, var1, var2, 0.0F).color(var7, var8, var9, var12).endVertex();
      Tesselator.getInstance().end();
      RenderSystem.defaultBlendFunc();
      RenderSystem.disableBlend();
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public static void b(PoseStack var0, float var1, float var2, float var3, float var4, int var5) {
      Tesselator var6 = Tesselator.getInstance();
      BufferBuilder var7 = var6.getBuilder();
      Matrix4f var8 = var0.last().pose();
      float var9 = (float)(var5 >> 24 & 0xFF) / 255.0F;
      float var10 = (float)(var5 >> 16 & 0xFF) / 255.0F;
      float var11 = (float)(var5 >> 8 & 0xFF) / 255.0F;
      float var12 = (float)(var5 & 0xFF) / 255.0F;
      var7.begin(Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
      var7.vertex(var8, var1, var2 + var4, 0.0F).color(var10, var11, var12, var9).endVertex();
      var7.vertex(var8, var1 + var3, var2 + var4, 0.0F).color(var10, var11, var12, var9).endVertex();
      var7.vertex(var8, var1 + var3, var2, 0.0F).color(var10, var11, var12, var9).endVertex();
      var7.vertex(var8, var1, var2, 0.0F).color(var10, var11, var12, var9).endVertex();
      var6.end();
   }

   private static void a(BufferBuilder var0, Matrix4f var1, float var2, float var3, int var4) {
      float var5 = (float)(var4 >> 24 & 0xFF) / 255.0F;
      float var6 = (float)(var4 >> 16 & 0xFF) / 255.0F;
      float var7 = (float)(var4 >> 8 & 0xFF) / 255.0F;
      float var8 = (float)(var4 & 0xFF) / 255.0F;
      var0.vertex(var1, var2, var3, 0.0F).color(var6, var7, var8, var5).endVertex();
   }

   public static void a(PoseStack var0, float var1, float var2, float var3, float var4, float var5, float var6, int var7) {
      if (var5 < 0.0F) {
         var5 = 0.0F;
      }

      if (var5 > var3 / 2.0F) {
         var5 = var3 / 2.0F;
      }

      if (var5 > var4 / 2.0F) {
         var5 = var4 / 2.0F;
      }

      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.lineWidth(var6);
      Tesselator var8 = Tesselator.getInstance();
      BufferBuilder var9 = var8.getBuilder();
      Matrix4f var10 = var0.last().pose();
      RenderSystem.setShader(GameRenderer::getPositionColorShader);
      var9.begin(Mode.DEBUG_LINES, DefaultVertexFormat.POSITION_COLOR);
      float var11 = var1 + var5;
      float var12 = var2 + var5;
      float var13 = var1 + var3 - var5;
      float var14 = var2 + var4 - var5;
      int var15 = (int)Math.min(Math.max(var5, 10.0F), 90.0F);
      a(var9, var10, var11, var2, var13, var2, var7);
      a(var9, var10, var13, var12, var5, 180, 270, var15, var7);
      a(var9, var10, var1 + var3, var12, var1 + var3, var14, var7);
      a(var9, var10, var13, var14, var5, 90, 180, var15, var7);
      a(var9, var10, var13, var2 + var4, var11, var2 + var4, var7);
      a(var9, var10, var11, var14, var5, 0, 90, var15, var7);
      a(var9, var10, var1, var14, var1, var12, var7);
      a(var9, var10, var11, var12, var5, 270, 360, var15, var7);
      var8.end();
      RenderSystem.disableBlend();
   }

   private static void a(BufferBuilder var0, Matrix4f var1, float var2, float var3, float var4, float var5, int var6) {
      float var7 = (float)(var6 >> 24 & 0xFF) / 255.0F;
      float var8 = (float)(var6 >> 16 & 0xFF) / 255.0F;
      float var9 = (float)(var6 >> 8 & 0xFF) / 255.0F;
      float var10 = (float)(var6 & 0xFF) / 255.0F;
      var0.vertex(var1, var2, var3, 0.0F).color(var8, var9, var10, var7).endVertex();
      var0.vertex(var1, var4, var5, 0.0F).color(var8, var9, var10, var7).endVertex();
   }

   private static void a(BufferBuilder var0, Matrix4f var1, float var2, float var3, float var4, int var5, int var6, int var7, int var8) {
      float var9 = (float)(var8 >> 24 & 0xFF) / 255.0F;
      float var10 = (float)(var8 >> 16 & 0xFF) / 255.0F;
      float var11 = (float)(var8 >> 8 & 0xFF) / 255.0F;
      float var12 = (float)(var8 & 0xFF) / 255.0F;
      double var13 = (Math.PI * 2) / (double)(var7 * 4);
      double var15 = Math.toRadians((double)var5);
      double var17 = Math.toRadians((double)var6);

      for (int var19 = 0; var19 < var7; var19++) {
         double var20 = Math.toRadians((double)var5 + (double)(var6 - var5) * (double)var19 / (double)var7);
         double var22 = Math.toRadians((double)var5 + (double)(var6 - var5) * (double)(var19 + 1) / (double)var7);
         float var24 = (float)((double)var2 + Math.sin(var20) * (double)var4);
         float var25 = (float)((double)var3 + Math.cos(var20) * (double)var4);
         float var26 = (float)((double)var2 + Math.sin(var22) * (double)var4);
         float var27 = (float)((double)var3 + Math.cos(var22) * (double)var4);
         var0.vertex(var1, var24, var25, 0.0F).color(var10, var11, var12, var9).endVertex();
         var0.vertex(var1, var26, var27, 0.0F).color(var10, var11, var12, var9).endVertex();
      }
   }

   public static void b(PoseStack var0, float var1, float var2, float var3, float var4, float var5, int var6) {
      if (var6 == 16777215) {
         var6 = ARGB32.color(255, 255, 255, 255);
      }

      if (var5 < 0.0F) {
         var5 = 0.0F;
      }

      if (var5 > var3 / 2.0F) {
         var5 = var3 / 2.0F;
      }

      if (var5 > var4 / 2.0F) {
         var5 = var4 / 2.0F;
      }

      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.lineWidth(1.0F);
      Tesselator var7 = Tesselator.getInstance();
      BufferBuilder var8 = var7.getBuilder();
      Matrix4f var9 = var0.last().pose();
      RenderSystem.setShader(GameRenderer::getPositionColorShader);
      var8.begin(Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
      float var10 = var1 + var5;
      float var11 = var1 + var3 - var5;
      float var12 = var2 + var5;
      float var13 = var2 + var4 - var5;
      b(var8, var9, var10, var12, var11, var13, var6);
      b(var8, var9, var10, var2, var11, var12, var6);
      b(var8, var9, var10, var13, var11, var2 + var4, var6);
      b(var8, var9, var1, var12, var10, var13, var6);
      b(var8, var9, var11, var12, var1 + var3, var13, var6);
      var7.end();
      int var16 = (int)Math.min(Math.max(var5, 10.0F), 90.0F);
      var8.begin(Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
      float var14 = var1 + var5;
      float var15 = var2 + var5;
      a(var8, var9, var14, var15, var6);

      for (int var17 = 0; var17 <= var16; var17++) {
         double var18 = (Math.PI * 2) * (double)(var17 + 180) / (double)(var16 * 4);
         a(var8, var9, (float)((double)var14 + Math.sin(var18) * (double)var5), (float)((double)var15 + Math.cos(var18) * (double)var5), var6);
      }

      var7.end();
      var8.begin(Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
      var14 = var1 + var3 - var5;
      var15 = var2 + var5;
      a(var8, var9, var14, var15, var6);

      for (int var26 = 0; var26 <= var16; var26++) {
         double var29 = (Math.PI * 2) * (double)(var26 + 90) / (double)(var16 * 4);
         a(var8, var9, (float)((double)var14 + Math.sin(var29) * (double)var5), (float)((double)var15 + Math.cos(var29) * (double)var5), var6);
      }

      var7.end();
      var8.begin(Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
      var14 = var1 + var5;
      var15 = var2 + var4 - var5;
      a(var8, var9, var14, var15, var6);

      for (int var27 = 0; var27 <= var16; var27++) {
         double var30 = (Math.PI * 2) * (double)(var27 + 270) / (double)(var16 * 4);
         a(var8, var9, (float)((double)var14 + Math.sin(var30) * (double)var5), (float)((double)var15 + Math.cos(var30) * (double)var5), var6);
      }

      var7.end();
      var8.begin(Mode.TRIANGLE_FAN, DefaultVertexFormat.POSITION_COLOR);
      var14 = var1 + var3 - var5;
      var15 = var2 + var4 - var5;
      a(var8, var9, var14, var15, var6);

      for (int var28 = 0; var28 <= var16; var28++) {
         double var31 = (Math.PI * 2) * (double)var28 / (double)(var16 * 4);
         a(var8, var9, (float)((double)var14 + Math.sin(var31) * (double)var5), (float)((double)var15 + Math.cos(var31) * (double)var5), var6);
      }

      var7.end();
      RenderSystem.disableBlend();
   }

   private static void b(BufferBuilder var0, Matrix4f var1, float var2, float var3, float var4, float var5, int var6) {
      float var7 = (float)(var6 >> 24 & 0xFF) / 255.0F;
      float var8 = (float)(var6 >> 16 & 0xFF) / 255.0F;
      float var9 = (float)(var6 >> 8 & 0xFF) / 255.0F;
      float var10 = (float)(var6 & 0xFF) / 255.0F;
      var0.vertex(var1, var2, var5, 0.0F).color(var8, var9, var10, var7).endVertex();
      var0.vertex(var1, var4, var5, 0.0F).color(var8, var9, var10, var7).endVertex();
      var0.vertex(var1, var4, var3, 0.0F).color(var8, var9, var10, var7).endVertex();
      var0.vertex(var1, var2, var3, 0.0F).color(var8, var9, var10, var7).endVertex();
   }

   public static void b(PoseStack var0) {
      a(b, var0);
   }

   public static void a(AABB var0, PoseStack var1) {
      Tesselator var2 = RenderSystem.renderThreadTesselator();
      BufferBuilder var3 = var2.getBuilder();
      Matrix4f var4 = var1.last().pose();
      var3.begin(Mode.QUADS, DefaultVertexFormat.POSITION);
      var3.vertex(var4, (float)var0.minX, (float)var0.minY, (float)var0.minZ).endVertex();
      var3.vertex(var4, (float)var0.maxX, (float)var0.minY, (float)var0.minZ).endVertex();
      var3.vertex(var4, (float)var0.maxX, (float)var0.minY, (float)var0.maxZ).endVertex();
      var3.vertex(var4, (float)var0.minX, (float)var0.minY, (float)var0.maxZ).endVertex();
      var3.vertex(var4, (float)var0.minX, (float)var0.maxY, (float)var0.minZ).endVertex();
      var3.vertex(var4, (float)var0.minX, (float)var0.maxY, (float)var0.maxZ).endVertex();
      var3.vertex(var4, (float)var0.maxX, (float)var0.maxY, (float)var0.maxZ).endVertex();
      var3.vertex(var4, (float)var0.maxX, (float)var0.maxY, (float)var0.minZ).endVertex();
      var3.vertex(var4, (float)var0.minX, (float)var0.minY, (float)var0.minZ).endVertex();
      var3.vertex(var4, (float)var0.minX, (float)var0.maxY, (float)var0.minZ).endVertex();
      var3.vertex(var4, (float)var0.maxX, (float)var0.maxY, (float)var0.minZ).endVertex();
      var3.vertex(var4, (float)var0.maxX, (float)var0.minY, (float)var0.minZ).endVertex();
      var3.vertex(var4, (float)var0.maxX, (float)var0.minY, (float)var0.minZ).endVertex();
      var3.vertex(var4, (float)var0.maxX, (float)var0.maxY, (float)var0.minZ).endVertex();
      var3.vertex(var4, (float)var0.maxX, (float)var0.maxY, (float)var0.maxZ).endVertex();
      var3.vertex(var4, (float)var0.maxX, (float)var0.minY, (float)var0.maxZ).endVertex();
      var3.vertex(var4, (float)var0.minX, (float)var0.minY, (float)var0.maxZ).endVertex();
      var3.vertex(var4, (float)var0.maxX, (float)var0.minY, (float)var0.maxZ).endVertex();
      var3.vertex(var4, (float)var0.maxX, (float)var0.maxY, (float)var0.maxZ).endVertex();
      var3.vertex(var4, (float)var0.minX, (float)var0.maxY, (float)var0.maxZ).endVertex();
      var3.vertex(var4, (float)var0.minX, (float)var0.minY, (float)var0.minZ).endVertex();
      var3.vertex(var4, (float)var0.minX, (float)var0.minY, (float)var0.maxZ).endVertex();
      var3.vertex(var4, (float)var0.minX, (float)var0.maxY, (float)var0.maxZ).endVertex();
      var3.vertex(var4, (float)var0.minX, (float)var0.maxY, (float)var0.minZ).endVertex();
      BufferUploader.drawWithShader(var3.end());
   }

   public static void c(PoseStack var0) {
      b(b, var0);
   }

   public static void b(AABB var0, PoseStack var1) {
      Matrix4f var2 = var1.last().pose();
      BufferBuilder var3 = Tesselator.getInstance().getBuilder();
      RenderSystem.setShader(GameRenderer::getPositionShader);
      var3.begin(Mode.DEBUG_LINES, DefaultVertexFormat.POSITION);
      var3.vertex(var2, (float)var0.minX, (float)var0.minY, (float)var0.minZ).endVertex();
      var3.vertex(var2, (float)var0.maxX, (float)var0.minY, (float)var0.minZ).endVertex();
      var3.vertex(var2, (float)var0.maxX, (float)var0.minY, (float)var0.minZ).endVertex();
      var3.vertex(var2, (float)var0.maxX, (float)var0.minY, (float)var0.maxZ).endVertex();
      var3.vertex(var2, (float)var0.maxX, (float)var0.minY, (float)var0.maxZ).endVertex();
      var3.vertex(var2, (float)var0.minX, (float)var0.minY, (float)var0.maxZ).endVertex();
      var3.vertex(var2, (float)var0.minX, (float)var0.minY, (float)var0.maxZ).endVertex();
      var3.vertex(var2, (float)var0.minX, (float)var0.minY, (float)var0.minZ).endVertex();
      var3.vertex(var2, (float)var0.minX, (float)var0.minY, (float)var0.minZ).endVertex();
      var3.vertex(var2, (float)var0.minX, (float)var0.maxY, (float)var0.minZ).endVertex();
      var3.vertex(var2, (float)var0.maxX, (float)var0.minY, (float)var0.minZ).endVertex();
      var3.vertex(var2, (float)var0.maxX, (float)var0.maxY, (float)var0.minZ).endVertex();
      var3.vertex(var2, (float)var0.maxX, (float)var0.minY, (float)var0.maxZ).endVertex();
      var3.vertex(var2, (float)var0.maxX, (float)var0.maxY, (float)var0.maxZ).endVertex();
      var3.vertex(var2, (float)var0.minX, (float)var0.minY, (float)var0.maxZ).endVertex();
      var3.vertex(var2, (float)var0.minX, (float)var0.maxY, (float)var0.maxZ).endVertex();
      var3.vertex(var2, (float)var0.minX, (float)var0.maxY, (float)var0.minZ).endVertex();
      var3.vertex(var2, (float)var0.maxX, (float)var0.maxY, (float)var0.minZ).endVertex();
      var3.vertex(var2, (float)var0.maxX, (float)var0.maxY, (float)var0.minZ).endVertex();
      var3.vertex(var2, (float)var0.maxX, (float)var0.maxY, (float)var0.maxZ).endVertex();
      var3.vertex(var2, (float)var0.maxX, (float)var0.maxY, (float)var0.maxZ).endVertex();
      var3.vertex(var2, (float)var0.minX, (float)var0.maxY, (float)var0.maxZ).endVertex();
      var3.vertex(var2, (float)var0.minX, (float)var0.maxY, (float)var0.maxZ).endVertex();
      var3.vertex(var2, (float)var0.minX, (float)var0.maxY, (float)var0.minZ).endVertex();
      BufferUploader.drawWithShader(var3.end());
   }

   public static void a(AABB var0, VertexBuffer var1) {
      BufferBuilder var2 = Tesselator.getInstance().getBuilder();
      RenderSystem.setShader(GameRenderer::getPositionShader);
      var2.begin(Mode.QUADS, DefaultVertexFormat.POSITION);
      a(var0, var2);
      BufferUploader.reset();
      var1.bind();
      RenderedBuffer var3 = var2.end();
      var1.upload(var3);
      VertexBuffer.unbind();
   }

   public static void a(AABB var0, BufferBuilder var1) {
      var1.vertex(var0.minX, var0.minY, var0.minZ).endVertex();
      var1.vertex(var0.maxX, var0.minY, var0.minZ).endVertex();
      var1.vertex(var0.maxX, var0.minY, var0.maxZ).endVertex();
      var1.vertex(var0.minX, var0.minY, var0.maxZ).endVertex();
      var1.vertex(var0.minX, var0.maxY, var0.minZ).endVertex();
      var1.vertex(var0.minX, var0.maxY, var0.maxZ).endVertex();
      var1.vertex(var0.maxX, var0.maxY, var0.maxZ).endVertex();
      var1.vertex(var0.maxX, var0.maxY, var0.minZ).endVertex();
      var1.vertex(var0.minX, var0.minY, var0.minZ).endVertex();
      var1.vertex(var0.minX, var0.maxY, var0.minZ).endVertex();
      var1.vertex(var0.maxX, var0.maxY, var0.minZ).endVertex();
      var1.vertex(var0.maxX, var0.minY, var0.minZ).endVertex();
      var1.vertex(var0.maxX, var0.minY, var0.minZ).endVertex();
      var1.vertex(var0.maxX, var0.maxY, var0.minZ).endVertex();
      var1.vertex(var0.maxX, var0.maxY, var0.maxZ).endVertex();
      var1.vertex(var0.maxX, var0.minY, var0.maxZ).endVertex();
      var1.vertex(var0.minX, var0.minY, var0.maxZ).endVertex();
      var1.vertex(var0.maxX, var0.minY, var0.maxZ).endVertex();
      var1.vertex(var0.maxX, var0.maxY, var0.maxZ).endVertex();
      var1.vertex(var0.minX, var0.maxY, var0.maxZ).endVertex();
      var1.vertex(var0.minX, var0.minY, var0.minZ).endVertex();
      var1.vertex(var0.minX, var0.minY, var0.maxZ).endVertex();
      var1.vertex(var0.minX, var0.maxY, var0.maxZ).endVertex();
      var1.vertex(var0.minX, var0.maxY, var0.minZ).endVertex();
   }

   public static void b(AABB var0, VertexBuffer var1) {
      BufferBuilder var2 = Tesselator.getInstance().getBuilder();
      var2.begin(Mode.DEBUG_LINES, DefaultVertexFormat.POSITION);
      b(var0, var2);
      var1.upload(var2.end());
   }

   public static void b(AABB var0, BufferBuilder var1) {
      var1.vertex(var0.minX, var0.minY, var0.minZ).endVertex();
      var1.vertex(var0.maxX, var0.minY, var0.minZ).endVertex();
      var1.vertex(var0.maxX, var0.minY, var0.minZ).endVertex();
      var1.vertex(var0.maxX, var0.minY, var0.maxZ).endVertex();
      var1.vertex(var0.maxX, var0.minY, var0.maxZ).endVertex();
      var1.vertex(var0.minX, var0.minY, var0.maxZ).endVertex();
      var1.vertex(var0.minX, var0.minY, var0.maxZ).endVertex();
      var1.vertex(var0.minX, var0.minY, var0.minZ).endVertex();
      var1.vertex(var0.minX, var0.minY, var0.minZ).endVertex();
      var1.vertex(var0.minX, var0.maxY, var0.minZ).endVertex();
      var1.vertex(var0.maxX, var0.minY, var0.minZ).endVertex();
      var1.vertex(var0.maxX, var0.maxY, var0.minZ).endVertex();
      var1.vertex(var0.maxX, var0.minY, var0.maxZ).endVertex();
      var1.vertex(var0.maxX, var0.maxY, var0.maxZ).endVertex();
      var1.vertex(var0.minX, var0.minY, var0.maxZ).endVertex();
      var1.vertex(var0.minX, var0.maxY, var0.maxZ).endVertex();
      var1.vertex(var0.minX, var0.maxY, var0.minZ).endVertex();
      var1.vertex(var0.maxX, var0.maxY, var0.minZ).endVertex();
      var1.vertex(var0.maxX, var0.maxY, var0.minZ).endVertex();
      var1.vertex(var0.maxX, var0.maxY, var0.maxZ).endVertex();
      var1.vertex(var0.maxX, var0.maxY, var0.maxZ).endVertex();
      var1.vertex(var0.minX, var0.maxY, var0.maxZ).endVertex();
      var1.vertex(var0.minX, var0.maxY, var0.maxZ).endVertex();
      var1.vertex(var0.minX, var0.maxY, var0.minZ).endVertex();
   }

   public static boolean a(int var0, int var1, float var2, float var3, float var4, float var5) {
      return (float)var0 > var2 && (float)var0 < var4 && (float)var1 > var3 && (float)var1 < var5;
   }

   public static boolean b(int var0, int var1, float var2, float var3, float var4, float var5) {
      return (float)var0 > var2 && (float)var0 < var2 + var4 && (float)var1 > var3 && (float)var1 < var3 + var5;
   }

   public static void c(PoseStack var0, float var1, float var2, float var3, float var4, int var5) {
      float var6 = var1 + var3;
      float var7 = var2 + var4;
      a(var0, var1, var2, var6, var7, var5);
   }

   public static void a(BufferBuilder var0, Matrix4f var1, AABB var2) {
      float var3 = (float)(var2.minX - a.getEntityRenderDispatcher().camera.getPosition().x());
      float var4 = (float)(var2.minY - a.getEntityRenderDispatcher().camera.getPosition().y());
      float var5 = (float)(var2.minZ - a.getEntityRenderDispatcher().camera.getPosition().z());
      float var6 = (float)(var2.maxX - a.getEntityRenderDispatcher().camera.getPosition().x());
      float var7 = (float)(var2.maxY - a.getEntityRenderDispatcher().camera.getPosition().y());
      float var8 = (float)(var2.maxZ - a.getEntityRenderDispatcher().camera.getPosition().z());
      var0.begin(Mode.QUADS, DefaultVertexFormat.POSITION);
      var0.vertex(var1, var3, var4, var5).endVertex();
      var0.vertex(var1, var6, var4, var5).endVertex();
      var0.vertex(var1, var6, var4, var8).endVertex();
      var0.vertex(var1, var3, var4, var8).endVertex();
      var0.vertex(var1, var3, var7, var5).endVertex();
      var0.vertex(var1, var3, var7, var8).endVertex();
      var0.vertex(var1, var6, var7, var8).endVertex();
      var0.vertex(var1, var6, var7, var5).endVertex();
      var0.vertex(var1, var3, var4, var5).endVertex();
      var0.vertex(var1, var3, var7, var5).endVertex();
      var0.vertex(var1, var6, var7, var5).endVertex();
      var0.vertex(var1, var6, var4, var5).endVertex();
      var0.vertex(var1, var6, var4, var5).endVertex();
      var0.vertex(var1, var6, var7, var5).endVertex();
      var0.vertex(var1, var6, var7, var8).endVertex();
      var0.vertex(var1, var6, var4, var8).endVertex();
      var0.vertex(var1, var3, var4, var8).endVertex();
      var0.vertex(var1, var6, var4, var8).endVertex();
      var0.vertex(var1, var6, var7, var8).endVertex();
      var0.vertex(var1, var3, var7, var8).endVertex();
      var0.vertex(var1, var3, var4, var5).endVertex();
      var0.vertex(var1, var3, var4, var8).endVertex();
      var0.vertex(var1, var3, var7, var8).endVertex();
      var0.vertex(var1, var3, var7, var5).endVertex();
      BufferUploader.drawWithShader(var0.end());
   }

   public static void a(PoseStack var0, double var1, double var3, double var5, int var7) {
      a(var0, var1, var3, var5, 0.6F, 1.8F, var7);
   }

   public static void a(PoseStack var0, double var1, double var3, double var5, float var7, float var8, int var9) {
      Vec3 var10 = b();
      float var11 = (float)(var9 >> 24 & 0xFF) / 255.0F;
      float var12 = (float)(var9 >> 16 & 0xFF) / 255.0F;
      float var13 = (float)(var9 >> 8 & 0xFF) / 255.0F;
      float var14 = (float)(var9 & 0xFF) / 255.0F;
      var0.pushPose();
      var0.translate(var1 - var10.x, var3 - var10.y, var5 - var10.z);
      AABB var15 = new AABB((double)(-var7) / 2.0, 0.0, (double)(-var7) / 2.0, (double)var7 / 2.0, (double)var8, (double)var7 / 2.0);
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.disableDepthTest();
      RenderSystem.depthMask(false);
      RenderSystem.setShader(GameRenderer::getPositionShader);
      RenderSystem.setShaderColor(var12, var13, var14, var11);
      a(var15, var0);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      RenderSystem.enableDepthTest();
      RenderSystem.depthMask(true);
      RenderSystem.disableBlend();
      var0.popPose();
   }
}
