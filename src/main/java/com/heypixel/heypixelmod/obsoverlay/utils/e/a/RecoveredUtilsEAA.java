package com.heypixel.heypixelmod.obsoverlay.utils.e.a;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferUploader;
import io.github.humbleui.skija.BackendRenderTarget;
import io.github.humbleui.skija.Canvas;
import io.github.humbleui.skija.ColorSpace;
import io.github.humbleui.skija.DirectContext;
import io.github.humbleui.skija.Surface;
import io.github.humbleui.skija.SurfaceColorFormat;
import io.github.humbleui.skija.SurfaceOrigin;
import java.util.function.Consumer;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL33;

public class RecoveredUtilsEAA {
   private static DirectContext a = null;
   private static Surface b;
   private static BackendRenderTarget c;

   public static Canvas a() {
      return b.getCanvas();
   }

   public static void a(int var0, int var1) {
      System.out.println("createSurface");
      if (a == null) {
         a = DirectContext.makeGL();
      }

      if (b != null) {
         b.close();
         b = null;
      }

      if (c != null) {
         c.close();
         c = null;
      }

      c = BackendRenderTarget.makeGL(var0, var1, 0, 8, Minecraft.getInstance().getMainRenderTarget().frameBufferId, 32856);
      b = Surface.wrapBackendRenderTarget(a, c, SurfaceOrigin.BOTTOM_LEFT, SurfaceColorFormat.RGBA_8888, ColorSpace.getSRGB());
   }

   public static void a(Consumer<Canvas> var0) {
      RenderSystem.pixelStore(3314, 0);
      RenderSystem.pixelStore(3316, 0);
      RenderSystem.pixelStore(3315, 0);
      RenderSystem.pixelStore(3317, 4);
      RenderSystem.clearColor(0.0F, 0.0F, 0.0F, 0.0F);
      a.resetGLAll();
      Canvas var1 = a();
      var0.accept(var1);
      a.flush();
      BufferUploader.reset();
      GL33.glBindSampler(0, 0);
      RenderSystem.disableBlend();
      GL11.glDisable(3042);
      RenderSystem.blendFunc(770, 1);
      GL11.glBlendFunc(770, 1);
      RenderSystem.blendEquation(32774);
      GL33.glBlendEquation(32774);
      RenderSystem.colorMask(true, true, true, true);
      GL11.glColorMask(true, true, true, true);
      RenderSystem.depthMask(true);
      GL11.glDepthMask(true);
      RenderSystem.disableScissor();
      GL11.glDisable(3089);
      GL11.glDisable(2960);
      RenderSystem.disableDepthTest();
      GL11.glDisable(2929);
      GL13.glActiveTexture(33984);
      RenderSystem.activeTexture(33984);
      RenderSystem.disableCull();
   }

   public static DirectContext b() {
      return a;
   }
}
