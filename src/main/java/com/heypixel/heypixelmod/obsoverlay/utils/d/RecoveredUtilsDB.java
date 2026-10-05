package com.heypixel.heypixelmod.obsoverlay.utils.d;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.PoseStack;
import java.nio.ByteBuffer;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryUtil;

public class RecoveredUtilsDB {
   private static final RecoveredUtilsDB.InnerA a = new RecoveredUtilsDB.InnerA();
   private static final PoseStack b = new PoseStack();

   public static void a() {
      a.a(b);
   }

   public static void b() {
      a.b(b);
   }

   public static void c() {
      a.d();
   }

   static {
      a.a();
      a.a(a.a(-1.0, -1.0).b(), a.a(-1.0, 1.0).b(), a.a(1.0, 1.0).b(), a.a(1.0, -1.0).b());
      a.c();
   }

   private static class InnerA {
      private final int a;
      private final int b;
      private final int c;
      private final int d;
      private final ByteBuffer e;
      private final long f;
      private final ByteBuffer g;
      private final long h;
      private long i;
      private int j;
      private int k;
      private boolean l;
      private boolean m;

      public InnerA() {
         byte var1 = 3;
         byte var2 = 8;
         this.a = var2 * var1;
         this.e = BufferUtils.createByteBuffer(this.a * 256 * 4);
         this.f = MemoryUtil.memAddress0(this.e);
         this.g = BufferUtils.createByteBuffer(var1 * 512 * 4);
         this.h = MemoryUtil.memAddress0(this.g);
         this.b = GlStateManager._glGenVertexArrays();
         RecoveredUtilsDD.a(this.b);
         this.c = GlStateManager._glGenBuffers();
         GlStateManager._glBindBuffer(34962, this.c);
         this.d = GlStateManager._glGenBuffers();
         RecoveredUtilsDD.b(this.d);
         GlStateManager._enableVertexAttribArray(0);
         GlStateManager._vertexAttribPointer(0, 2, 5126, false, var2, 0L);
         RecoveredUtilsDD.a(0);
         GlStateManager._glBindBuffer(34962, 0);
         RecoveredUtilsDD.b(0);
      }

      public void a() {
         if (!this.l) {
            this.i = this.f;
            this.j = 0;
            this.k = 0;
            this.l = true;
         }
      }

      public RecoveredUtilsDB.InnerA a(double var1, double var3) {
         long var5 = this.i;
         MemoryUtil.memPutFloat(var5, (float)var1);
         MemoryUtil.memPutFloat(var5 + 4L, (float)var3);
         this.i += 8L;
         return this;
      }

      public int b() {
         return this.j++;
      }

      public void a(int var1, int var2, int var3, int var4) {
         long var5 = this.h + (long)this.k * 4L;
         MemoryUtil.memPutInt(var5, var1);
         MemoryUtil.memPutInt(var5 + 4L, var2);
         MemoryUtil.memPutInt(var5 + 8L, var3);
         MemoryUtil.memPutInt(var5 + 12L, var3);
         MemoryUtil.memPutInt(var5 + 16L, var4);
         MemoryUtil.memPutInt(var5 + 20L, var1);
         this.k += 6;
      }

      public void c() {
         if (this.l) {
            if (this.k > 0) {
               GlStateManager._glBindBuffer(34962, this.c);
               GlStateManager._glBufferData(34962, this.e.limit(this.e()), 35048);
               GlStateManager._glBindBuffer(34962, 0);
               RecoveredUtilsDD.b(this.d);
               GlStateManager._glBufferData(34963, this.g.limit(this.k * 4), 35048);
               RecoveredUtilsDD.b(0);
            }

            this.l = false;
         }
      }

      public void a(PoseStack var1) {
         RecoveredUtilsDD.g();
         this.m = true;
      }

      public void b(PoseStack var1) {
         if (this.l) {
            this.c();
         }

         if (this.k > 0) {
            boolean var2 = this.m;
            if (!var2) {
               this.a(var1);
            }

            RecoveredUtilsDC.a.b();
            RecoveredUtilsDD.a(this.b);
            GlStateManager._drawElements(4, this.k, 5125, 0L);
            RecoveredUtilsDD.a(0);
            if (!var2) {
               this.d();
            }
         }
      }

      public void d() {
         this.m = false;
      }

      private int e() {
         return (int)(this.i - this.f);
      }
   }
}
