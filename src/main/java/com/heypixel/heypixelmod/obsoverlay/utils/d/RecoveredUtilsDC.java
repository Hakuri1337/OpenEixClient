package com.heypixel.heypixelmod.obsoverlay.utils.d;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.nio.FloatBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import org.apache.commons.io.IOUtils;
import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

public class RecoveredUtilsDC {
   private static final FloatBuffer b = BufferUtils.createFloatBuffer(16);
   public static RecoveredUtilsDC a;
   private final int c;
   private final Object2IntMap<String> d = new Object2IntOpenHashMap<>();

   public RecoveredUtilsDC(String var1, String var2) {
      int var3 = GlStateManager.glCreateShader(35633);
      GlStateManager.glShaderSource(var3, Collections.singletonList(this.a(var1)));
      String var4 = RecoveredUtilsDD.c(var3);
      if (var4 != null) {
         throw new RuntimeException("Failed to compile vertex shader: " + var4);
      } else {
         int var5 = GlStateManager.glCreateShader(35632);
         GlStateManager.glShaderSource(var5, Collections.singletonList(this.a(var2)));
         String var6 = RecoveredUtilsDD.c(var5);
         if (var6 != null) {
            throw new RuntimeException("Failed to compile fragment shader: " + var6);
         } else {
            this.c = GlStateManager.glCreateProgram();
            String var7 = RecoveredUtilsDD.a(this.c, var3, var5);
            if (var7 != null) {
               throw new RuntimeException("Failed to link program: " + var7);
            } else {
               GlStateManager.glDeleteShader(var3);
               GlStateManager.glDeleteShader(var5);
            }
         }
      }
   }

   private String a(String var1) {
      try {
         return IOUtils.toString(RecoveredUtilsDC.class.getResourceAsStream("/assets/heypixel/vcx6svvqmet8/shaders/" + var1), StandardCharsets.UTF_8);
      } catch (Exception var3) {
         throw new IllegalStateException("Could not read shader '" + var1 + "'", var3);
      }
   }

   public void a() {
      RecoveredUtilsDD.d(this.c);
      a = this;
   }

   private int b(String var1) {
      if (this.d.containsKey(var1)) {
         return this.d.getInt(var1);
      } else {
         int var2 = RecoveredUtilsDD.a(this.c, var1);
         this.d.put(var1, var2);
         return var2;
      }
   }

   public void a(String var1, boolean var2) {
      RecoveredUtilsDD.a(this.b(var1), var2 ? 1 : 0);
   }

   public void a(String var1, int var2) {
      RecoveredUtilsDD.a(this.b(var1), var2);
   }

   public void a(String var1, double var2) {
      RecoveredUtilsDD.a(this.b(var1), (float)var2);
   }

   public void a(String var1, double var2, double var4) {
      RecoveredUtilsDD.a(this.b(var1), (float)var2, (float)var4);
   }

   public void a(String var1, Matrix4f var2) {
      var2.get(b);
      GlStateManager._glUniformMatrix4(this.b(var1), false, b);
   }

   public void b() {
      this.a("u_Proj", RenderSystem.getProjectionMatrix());
      this.a("u_ModelView", RenderSystem.getModelViewMatrix());
   }
}
