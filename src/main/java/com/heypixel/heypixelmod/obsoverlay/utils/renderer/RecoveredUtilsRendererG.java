package com.heypixel.heypixelmod.obsoverlay.utils.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import java.awt.Color;
import java.nio.ByteBuffer;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.Vec3;
import org.lwjgl.BufferUtils;
import org.lwjgl.system.MemoryUtil;

public class RecoveredUtilsRendererG {
   private static final Minecraft c = Minecraft.getInstance();
   private final RecoveredUtilsRendererC d;
   private final int e;
   private final int f;
   private final int g;
   private final int h;
   public boolean a = false;
   public double b = 1.0;
   private ByteBuffer i;
   private long j;
   private int k;
   private ByteBuffer l;
   private long m;
   private int n;
   private int o;
   private boolean p;
   private boolean q;
   private double r;
   private double s;
   private boolean t;

   public RecoveredUtilsRendererG(RecoveredUtilsRendererC var1, RecoveredUtilsRendererG.InnerA... var2) {
      int var3 = 0;

      for (RecoveredUtilsRendererG.InnerA var7 : var2) {
         var3 += var7.e * 4;
      }

      this.d = var1;
      this.e = var3 * var1.c;
      this.i = BufferUtils.createByteBuffer(this.e * 256 * 4);
      this.j = MemoryUtil.memAddress0(this.i);
      this.l = BufferUtils.createByteBuffer(var1.c * 512 * 4);
      this.m = MemoryUtil.memAddress0(this.l);
      this.f = RecoveredUtilsRendererF.a();
      RecoveredUtilsRendererF.g(this.f);
      this.g = RecoveredUtilsRendererF.b();
      RecoveredUtilsRendererF.h(this.g);
      this.h = RecoveredUtilsRendererF.b();
      RecoveredUtilsRendererF.i(this.h);
      int var8 = 0;

      for (int var9 = 0; var9 < var2.length; var9++) {
         int var10 = var2[var9].e;
         RecoveredUtilsRendererF.k(var9);
         RecoveredUtilsRendererF.a(var9, var10, 5126, false, var3, (long)var8);
         var8 += var10 * 4;
      }

      RecoveredUtilsRendererF.g(0);
      RecoveredUtilsRendererF.h(0);
      RecoveredUtilsRendererF.i(0);
   }

   public void a() {
      if (this.p) {
         throw new IllegalStateException("Mesh.end() called while already building.");
      } else {
         this.k = 0;
         this.n = 0;
         this.o = 0;
         this.p = true;
         this.r = 0.0;
         this.s = 0.0;
      }
   }

   public RecoveredUtilsRendererG a(double var1, double var3, double var5) {
      long var7 = this.j + (long)this.k * 4L;
      MemoryUtil.memPutFloat(var7, (float)(var1 - this.r));
      MemoryUtil.memPutFloat(var7 + 4L, (float)var3);
      MemoryUtil.memPutFloat(var7 + 8L, (float)(var5 - this.s));
      this.k += 3;
      return this;
   }

   public RecoveredUtilsRendererG a(double var1, double var3) {
      long var5 = this.j + (long)this.k * 4L;
      MemoryUtil.memPutFloat(var5, (float)var1);
      MemoryUtil.memPutFloat(var5 + 4L, (float)var3);
      this.k += 2;
      return this;
   }

   public RecoveredUtilsRendererG a(Color var1) {
      long var2 = this.j + (long)this.k * 4L;
      MemoryUtil.memPutFloat(var2, (float)var1.getRed() / 255.0F);
      MemoryUtil.memPutFloat(var2 + 4L, (float)var1.getGreen() / 255.0F);
      MemoryUtil.memPutFloat(var2 + 8L, (float)var1.getBlue() / 255.0F);
      MemoryUtil.memPutFloat(var2 + 12L, (float)var1.getAlpha() / 255.0F * (float)this.b);
      this.k += 4;
      return this;
   }

   public int b() {
      return this.n++;
   }

   public void a(int var1, int var2) {
      long var3 = this.m + (long)this.o * 4L;
      MemoryUtil.memPutInt(var3, var1);
      MemoryUtil.memPutInt(var3 + 4L, var2);
      this.o += 2;
      this.c();
   }

   public void a(int var1, int var2, int var3, int var4) {
      long var5 = this.m + (long)this.o * 4L;
      MemoryUtil.memPutInt(var5, var1);
      MemoryUtil.memPutInt(var5 + 4L, var2);
      MemoryUtil.memPutInt(var5 + 8L, var3);
      MemoryUtil.memPutInt(var5 + 12L, var3);
      MemoryUtil.memPutInt(var5 + 16L, var4);
      MemoryUtil.memPutInt(var5 + 20L, var1);
      this.o += 6;
      this.c();
   }

   public void c() {
      if ((this.n + 1) * this.e >= this.i.capacity()) {
         int var1 = this.i.capacity() * 2;
         if (var1 % this.e != 0) {
            var1 += var1 % this.e;
         }

         ByteBuffer var2 = BufferUtils.createByteBuffer(var1);
         MemoryUtil.memCopy(MemoryUtil.memAddress0(this.i), MemoryUtil.memAddress0(var2), (long)this.k * 4L);
         this.i = var2;
         this.j = MemoryUtil.memAddress0(this.i);
      }

      if (this.o * 4 >= this.l.capacity()) {
         int var3 = this.l.capacity() * 2;
         if (var3 % this.d.c != 0) {
            var3 += var3 % (this.d.c * 4);
         }

         ByteBuffer var4 = BufferUtils.createByteBuffer(var3);
         MemoryUtil.memCopy(MemoryUtil.memAddress0(this.l), MemoryUtil.memAddress0(var4), (long)this.o * 4L);
         this.l = var4;
         this.m = MemoryUtil.memAddress0(this.l);
      }
   }

   public void d() {
      if (!this.p) {
         throw new IllegalStateException("Mesh.end() called while not building.");
      } else {
         if (this.o > 0) {
            RecoveredUtilsRendererF.h(this.g);
            RecoveredUtilsRendererF.a(34962, this.i.limit(this.k * 4), 35048);
            RecoveredUtilsRendererF.h(0);
            RecoveredUtilsRendererF.i(this.h);
            RecoveredUtilsRendererF.a(34963, this.l.limit(this.o * 4), 35048);
            RecoveredUtilsRendererF.i(0);
         }

         this.p = false;
      }
   }

   public void a(PoseStack var1) {
      if (this.a) {
         RecoveredUtilsRendererF.i();
      } else {
         RecoveredUtilsRendererF.j();
      }

      RecoveredUtilsRendererF.k();
      RecoveredUtilsRendererF.n();
      RecoveredUtilsRendererF.q();
      if (this.q) {
         PoseStack var2 = RenderSystem.getModelViewStack();
         var2.pushPose();
         if (var1 != null) {
            var2.mulPoseMatrix(var1.last().pose());
         }

         Vec3 var3 = c.gameRenderer.getMainCamera().getPosition();
         var2.translate(0.0, -var3.y, 0.0);
      }

      this.t = true;
   }

   public void b(PoseStack var1) {
      if (this.p) {
         this.d();
      }

      if (this.o > 0) {
         boolean var2 = this.t;
         if (!var2) {
            this.a(var1);
         }

         this.f();
         RecoveredUtilsRendererI.a.b();
         RecoveredUtilsRendererF.g(this.f);
         RecoveredUtilsRendererF.a(this.d.a(), this.o, 5125);
         RecoveredUtilsRendererF.g(0);
         if (!var2) {
            this.e();
         }
      }
   }

   public void e() {
      if (this.q) {
         RenderSystem.getModelViewStack().popPose();
      }

      if (this.a) {
         RecoveredUtilsRendererF.l();
      } else {
         RecoveredUtilsRendererF.k();
      }

      RecoveredUtilsRendererF.l();
      RecoveredUtilsRendererF.m();
      RecoveredUtilsRendererF.r();
      this.t = false;
   }

   protected void f() {
   }

   public static enum InnerA {
      Float(1),
      Vec2(2),
      Vec3(3),
      Color(4);

      public final int e;

      private InnerA(int var3) {
         this.e = var3;
      }
   }
}
