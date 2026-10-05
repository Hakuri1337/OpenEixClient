package com.heypixel.heypixelmod.obsoverlay.utils.renderer;

import com.google.common.collect.ImmutableList;
import com.heypixel.heypixelmod.mixin.O.accessors.BufferUploaderAccessor;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsO;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL32C;

public class RecoveredUtilsRendererF {
   private static final FloatBuffer b = BufferUtils.createFloatBuffer(16);
   private static final RecoveredUtilsO c = a("DEPTH");
   private static final RecoveredUtilsO d = a("BLEND");
   private static final RecoveredUtilsO e = a("CULL");
   private static final RecoveredUtilsO f = a("SCISSOR");
   private static final boolean g = true;
   public static int a;
   private static boolean h;
   private static boolean i;
   private static boolean j;
   private static boolean k;
   private static int l;

   public static int a() {
      return GlStateManager._glGenVertexArrays();
   }

   public static int b() {
      return GlStateManager._glGenBuffers();
   }

   public static int c() {
      return GlStateManager._genTexture();
   }

   public static int d() {
      return GlStateManager.glGenFramebuffers();
   }

   public static void e() {
      h = c.get();
      i = d.get();
      j = e.get();
      k = f.get();
   }

   public static void f() {
      c.set(h);
      d.set(i);
      e.set(j);
      f.set(k);
      r();
   }

   public static void a(int var0) {
      GlStateManager._glDeleteBuffers(var0);
   }

   public static void b(int var0) {
      GlStateManager._glDeleteVertexArrays(var0);
   }

   public static void c(int var0) {
      GlStateManager.glDeleteShader(var0);
   }

   public static void d(int var0) {
      GlStateManager._deleteTexture(var0);
   }

   public static void e(int var0) {
      GlStateManager._glDeleteFramebuffers(var0);
   }

   public static void f(int var0) {
      GlStateManager.glDeleteProgram(var0);
   }

   public static void g(int var0) {
      if (var0 >= 0) {
         try {
            GlStateManager._glBindVertexArray(var0);
            BufferUploaderAccessor.setCurrentVertexBuffer(null);
         } catch (Exception var2) {
            System.err.println("Error binding VAO " + var0 + ": " + var2.getMessage());
         }
      } else {
         System.err.println("WARNING: Attempted to bind invalid VAO: " + var0);
      }
   }

   public static void h(int var0) {
      GlStateManager._glBindBuffer(34962, var0);
   }

   public static void i(int var0) {
      if (var0 != 0) {
         l = a;
      }

      try {
         int var1 = var0 != 0 ? var0 : l;
         GlStateManager._glBindBuffer(34963, var1);
      } catch (Exception var2) {
         System.err.println("Error binding IBO: " + var2.getMessage());
      }
   }

   public static void j(int var0) {
      GlStateManager._glBindFramebuffer(36160, var0);
   }

   public static void a(int var0, ByteBuffer var1, int var2) {
      GlStateManager._glBufferData(var0, var1, var2);
   }

   public static void a(int var0, int var1, int var2) {
      GlStateManager._drawElements(var0, var1, var2, 0L);
   }

   public static void k(int var0) {
      RenderSystem.assertOnRenderThread();
      GL20.glEnableVertexAttribArray(var0);
   }

   public static void a(int var0, int var1, int var2, boolean var3, int var4, long var5) {
      GlStateManager._vertexAttribPointer(var0, var1, var2, var3, var4, var5);
   }

   public static int l(int var0) {
      return GlStateManager.glCreateShader(var0);
   }

   public static void a(int var0, String var1) {
      GlStateManager.glShaderSource(var0, ImmutableList.of(var1));
   }

   public static String m(int var0) {
      GlStateManager.glCompileShader(var0);
      return GlStateManager.glGetShaderi(var0, 35713) == 0 ? GlStateManager.glGetShaderInfoLog(var0, 512) : null;
   }

   public static int g() {
      return GlStateManager.glCreateProgram();
   }

   public static String b(int var0, int var1, int var2) {
      GlStateManager.glAttachShader(var0, var1);
      GlStateManager.glAttachShader(var0, var2);
      GlStateManager.glLinkProgram(var0);
      return GlStateManager.glGetProgrami(var0, 35714) == 0 ? GlStateManager.glGetProgramInfoLog(var0, 512) : null;
   }

   public static void n(int var0) {
      GlStateManager._glUseProgram(var0);
   }

   public static void a(int var0, int var1, int var2, int var3) {
      GlStateManager._viewport(var0, var1, var2, var3);
   }

   public static int b(int var0, String var1) {
      return GlStateManager._glGetUniformLocation(var0, var1);
   }

   public static void a(int var0, int var1) {
      GlStateManager._glUniform1i(var0, var1);
   }

   public static void a(int var0, float var1) {
      GL32C.glUniform1f(var0, var1);
   }

   public static void a(int var0, float var1, float var2) {
      GL32C.glUniform2f(var0, var1, var2);
   }

   public static void a(int var0, float var1, float var2, float var3) {
      GL32C.glUniform3f(var0, var1, var2, var3);
   }

   public static void a(int var0, float var1, float var2, float var3, float var4) {
      GL32C.glUniform4f(var0, var1, var2, var3, var4);
   }

   public static void a(int var0, float[] var1) {
      GL32C.glUniform3fv(var0, var1);
   }

   public static void a(int var0, Matrix4f var1) {
      var1.get(b);
      GlStateManager._glUniformMatrix4(var0, false, b);
   }

   public static void b(int var0, int var1) {
      GlStateManager._pixelStore(var0, var1);
   }

   public static void c(int var0, int var1, int var2) {
      GlStateManager._texParameter(var0, var1, var2);
   }

   public static void a(int var0, int var1, int var2, int var3, int var4, int var5, int var6, int var7, ByteBuffer var8) {
      GL32C.glTexImage2D(var0, var1, var2, var3, var4, var5, var6, var7, var8);
   }

   public static void h() {
      b(3312, 0);
      b(3313, 0);
      b(3314, 0);
      b(32878, 0);
      b(3315, 0);
      b(3316, 0);
      b(32877, 0);
      b(3317, 4);
   }

   public static void o(int var0) {
      GL32C.glGenerateMipmap(var0);
   }

   public static void a(int var0, int var1, int var2, int var3, int var4) {
      GlStateManager._glFramebufferTexture2D(var0, var1, var2, var3, var4);
   }

   public static void p(int var0) {
      GlStateManager._clearColor(0.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager._clear(var0, false);
   }

   public static void i() {
      GlStateManager._enableDepthTest();
   }

   public static void j() {
      GlStateManager._disableDepthTest();
   }

   public static void k() {
      GlStateManager._enableBlend();
      GlStateManager._blendFunc(770, 771);
   }

   public static void l() {
      GlStateManager._disableBlend();
   }

   public static void m() {
      GlStateManager._enableCull();
   }

   public static void n() {
      GlStateManager._disableCull();
   }

   public static void o() {
      GlStateManager._enableScissorTest();
   }

   public static void p() {
      GlStateManager._disableScissorTest();
   }

   public static void q() {
      GL32C.glEnable(2848);
      GL32C.glLineWidth(1.0F);
   }

   public static void r() {
      GL32C.glDisable(2848);
   }

   public static void a(ResourceLocation var0) {
      GlStateManager._activeTexture(33984);
      Minecraft.getInstance().getTextureManager().bindForSetup(var0);
   }

   public static void c(int var0, int var1) {
      GlStateManager._activeTexture(33984 + var1);
      GlStateManager._bindTexture(var0);
   }

   public static void q(int var0) {
      c(var0, 0);
   }

   public static void s() {
      GlStateManager._activeTexture(33984);
   }

   private static RecoveredUtilsO a(String var0) {
      try {
         Class<GlStateManager> var1 = GlStateManager.class;
         Field var2 = var1.getDeclaredField(var0);
         var2.setAccessible(true);
         Object var3 = var2.get(null);
         String var4 = "com.mojang.blaze3d.platform.GlStateManager$BooleanState";
         Field var5 = null;

         for (Field var9 : var3.getClass().getDeclaredFields()) {
            if (var9.getType().getName().equals(var4)) {
               var5 = var9;
               break;
            }
         }

         var5.setAccessible(true);
         return (RecoveredUtilsO)var5.get(var3);
      } catch (NoSuchFieldException | IllegalAccessException var10) {
         var10.printStackTrace();
         return null;
      }
   }
}
