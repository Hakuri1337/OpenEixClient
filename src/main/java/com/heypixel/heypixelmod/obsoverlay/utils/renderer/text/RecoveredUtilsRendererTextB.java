package com.heypixel.heypixelmod.obsoverlay.utils.renderer.text;

import com.mojang.blaze3d.systems.RenderSystem;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.server.packs.resources.ResourceManager;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL30C;

public class RecoveredUtilsRendererTextB extends AbstractTexture {
   public RecoveredUtilsRendererTextB(
      int var1,
      int var2,
      byte[] var3,
      RecoveredUtilsRendererTextB.InnerB var4,
      RecoveredUtilsRendererTextB.InnerA var5,
      RecoveredUtilsRendererTextB.InnerA var6
   ) {
      if (!RenderSystem.isOnRenderThread()) {
         RenderSystem.recordRenderCall(() -> this.a(var1, var2, var3, var4, var5, var6));
      } else {
         this.a(var1, var2, var3, var4, var5, var6);
      }
   }

   public RecoveredUtilsRendererTextB(
      int var1,
      int var2,
      ByteBuffer var3,
      RecoveredUtilsRendererTextB.InnerB var4,
      RecoveredUtilsRendererTextB.InnerA var5,
      RecoveredUtilsRendererTextB.InnerA var6
   ) {
      if (!RenderSystem.isOnRenderThread()) {
         RenderSystem.recordRenderCall(() -> this.a(var1, var2, var3, var4, var5, var6));
      } else {
         this.a(var1, var2, var3, var4, var5, var6);
      }
   }

   private void a(
      int var1,
      int var2,
      byte[] var3,
      RecoveredUtilsRendererTextB.InnerB var4,
      RecoveredUtilsRendererTextB.InnerA var5,
      RecoveredUtilsRendererTextB.InnerA var6
   ) {
      ByteBuffer var7 = BufferUtils.createByteBuffer(var3.length).put(var3);
      this.a(var1, var2, var7, var4, var5, var6);
   }

   private void a(
      int var1,
      int var2,
      ByteBuffer var3,
      RecoveredUtilsRendererTextB.InnerB var4,
      RecoveredUtilsRendererTextB.InnerA var5,
      RecoveredUtilsRendererTextB.InnerA var6
   ) {
      this.bind();
      GL30C.glPixelStorei(3312, 0);
      GL30C.glPixelStorei(3313, 0);
      GL30C.glPixelStorei(3314, 0);
      GL30C.glPixelStorei(32878, 0);
      GL30C.glPixelStorei(3315, 0);
      GL30C.glPixelStorei(3316, 0);
      GL30C.glPixelStorei(32877, 0);
      GL30C.glPixelStorei(3317, 4);
      GL30C.glTexParameteri(3553, 10242, 10497);
      GL30C.glTexParameteri(3553, 10243, 10497);
      GL30C.glTexParameteri(3553, 10241, var5.a());
      GL30C.glTexParameteri(3553, 10240, var6.a());
      ((Buffer)var3).rewind();
      GL30C.glTexImage2D(3553, 0, var4.a(), var1, var2, 0, var4.a(), 5121, var3);
   }

   @Override
   public void load(ResourceManager var1) throws IOException {
   }

   public static enum InnerA {
      Nearest,
      Linear;

      public int a() {
         return this == Nearest ? 9728 : 9729;
      }
   }

   public static enum InnerB {
      A,
      RGB,
      RGBA;

      public int a() {
         if (this == A) {
            return 6403;
         } else if (this == RGB) {
            return 6407;
         } else {
            return this == RGBA ? 6408 : 0;
         }
      }
   }
}
