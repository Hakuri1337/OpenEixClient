package com.heypixel.heypixelmod.obsoverlay.utils;

import com.heypixel.heypixelmod.mixin.O.accessors.RenderTargetAccessor;
import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;

public class RecoveredUtilsAi {
   private static final Minecraft a = Minecraft.getInstance();

   public static void a(boolean var0) {
      b();
      GL11.glClear(1024);
      GL11.glEnable(2960);
      GL11.glStencilFunc(519, 1, 65535);
      GL11.glStencilOp(7680, 7680, 7681);
      if (!var0) {
         RenderSystem.colorMask(false, false, false, false);
      }
   }

   public static void b(boolean var0) {
      RenderSystem.colorMask(true, true, true, true);
      GL11.glStencilFunc(var0 ? 514 : 517, 1, 65535);
      GL11.glStencilOp(7680, 7680, 7681);
   }

   public static void a() {
      GL11.glDisable(2960);
   }

   public static void b() {
      if (a.getMainRenderTarget().getDepthTextureId() > -1) {
         a(a.getMainRenderTarget());
         ((RenderTargetAccessor)a.getMainRenderTarget()).setDepthBufferId(-1);
      }
   }

   public static void a(RenderTarget var0) {
      EXTFramebufferObject.glDeleteRenderbuffersEXT(var0.getDepthTextureId());
      int var1 = EXTFramebufferObject.glGenRenderbuffersEXT();
      EXTFramebufferObject.glBindRenderbufferEXT(36161, var1);
      EXTFramebufferObject.glRenderbufferStorageEXT(36161, 34041, a.getWindow().getWidth(), a.getWindow().getHeight());
      EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36128, 36161, var1);
      EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36096, 36161, var1);
   }
}
