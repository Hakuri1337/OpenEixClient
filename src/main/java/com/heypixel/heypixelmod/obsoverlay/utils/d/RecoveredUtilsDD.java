package com.heypixel.heypixelmod.obsoverlay.utils.d;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.BufferUploader;
import java.nio.ByteBuffer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.opengl.GL20C;

public class RecoveredUtilsDD {
   public static int a;
   private static int b;

   private RecoveredUtilsDD() {
   }

   public static void a(int var0) {
      GlStateManager._glBindVertexArray(var0);

      try {
         BufferUploader.class.getDeclaredField("lastImmediateBuffer").set(null, null);
      } catch (Throwable var3) {
      }

      try {
         BufferUploader.class.getDeclaredField("field_38982").set(null, null);
      } catch (Throwable var2) {
      }
   }

   public static void b(int var0) {
      if (var0 != 0) {
         b = a;
      }

      GlStateManager._glBindBuffer(34963, var0 != 0 ? var0 : b);
   }

   public static String c(int var0) {
      GlStateManager.glCompileShader(var0);
      return GlStateManager.glGetShaderi(var0, 35713) == 0 ? GlStateManager.glGetShaderInfoLog(var0, 512) : null;
   }

   public static String a(int var0, int var1, int var2) {
      GlStateManager.glAttachShader(var0, var1);
      GlStateManager.glAttachShader(var0, var2);
      GlStateManager.glLinkProgram(var0);
      return GlStateManager.glGetProgrami(var0, 35714) == 0 ? GlStateManager.glGetProgramInfoLog(var0, 512) : null;
   }

   public static void d(int var0) {
      GlStateManager._glUseProgram(var0);
   }

   public static void a(int var0, int var1, int var2, int var3) {
      GlStateManager._viewport(var0, var1, var2, var3);
   }

   public static int a(int var0, String var1) {
      return GlStateManager._glGetUniformLocation(var0, var1);
   }

   public static void a(int var0, int var1) {
      GlStateManager._glUniform1i(var0, var1);
   }

   public static void a(int var0, float var1) {
      GL20C.glUniform1f(var0, var1);
   }

   public static void a(int var0, float var1, float var2) {
      GL20C.glUniform2f(var0, var1, var2);
   }

   public static void a(int var0, float var1, float var2, float var3) {
      GL20C.glUniform3f(var0, var1, var2, var3);
   }

   public static void a(int var0, float var1, float var2, float var3, float var4) {
      GL20C.glUniform4f(var0, var1, var2, var3, var4);
   }

   public static void a(int var0, float[] var1) {
      GL20C.glUniform3fv(var0, var1);
   }

   public static void b(int var0, int var1) {
      GlStateManager._pixelStore(var0, var1);
   }

   public static void b(int var0, int var1, int var2) {
      GlStateManager._texParameter(var0, var1, var2);
   }

   public static void a(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, ByteBuffer var8) {
      GL20C.glTexImage2D(var0, var1, var2, var3, var4, var5, var6, var7, var8);
   }

   public static void a() {
      b(3312, 0);
      b(3313, 0);
      b(3314, 0);
      b(32878, 0);
      b(3315, 0);
      b(3316, 0);
      b(32877, 0);
      b(3317, 4);
   }

   public static void a(int var0, int var1, int var2, int var3, int var4) {
      GlStateManager._glFramebufferTexture2D(var0, var1, var2, var3, var4);
   }

   public static void b() {
      GlStateManager._enableDepthTest();
   }

   public static void c() {
      GlStateManager._disableDepthTest();
   }

   public static void d() {
      GlStateManager._enableBlend();
      GlStateManager._blendFunc(770, 771);
   }

   public static void e() {
      GlStateManager._disableBlend();
   }

   public static void f() {
      GlStateManager._enableCull();
   }

   public static void g() {
      GlStateManager._disableCull();
   }

   public static void h() {
      GL20C.glEnable(2848);
      GL20C.glLineWidth(1.0F);
   }

   public static void i() {
      GL20C.glDisable(2848);
   }

   public static void a(ResourceLocation var0) {
      AbstractTexture var1 = Minecraft.getInstance().getTextureManager().getTexture(var0);
      c(var1.getId(), 0);
   }

   public static void c(int var0, int var1) {
      GlStateManager._activeTexture(33984 + var1);
      GlStateManager._bindTexture(var0);
   }

   public static void e(int var0) {
      c(var0, 0);
   }

   public static void j() {
      GlStateManager._activeTexture(33984);
   }
}
