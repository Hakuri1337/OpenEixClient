package com.heypixel.heypixelmod.obsoverlay.modules.impl.render;

import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAE;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplAd;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplP;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAd;
import com.heypixel.heypixelmod.obsoverlay.utils.renderer.RecoveredUtilsRendererB;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat.Mode;
import com.mojang.math.Axis;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix4f;

@ModuleInfo(
   a = "Particles",
   b = "粒子",
   c = "Renders world particles",
   d = ModuleCategory.RENDER
)
public class Particles extends ClientModule {
   private static final ResourceLocation c = new ResourceLocation("heypixel", "vcx6svvqmet8/particles/firefly.png");
   private static final ResourceLocation d = new ResourceLocation("heypixel", "vcx6svvqmet8/particles/snowflake.png");
   private static final ResourceLocation e = new ResourceLocation("heypixel", "vcx6svvqmet8/particles/star.png");
   private static final ResourceLocation f = new ResourceLocation("heypixel", "vcx6svvqmet8/particles/heart.png");
   private static final ResourceLocation g = new ResourceLocation("heypixel", "vcx6svvqmet8/particles/dollar.png");
   private final RecoveredDAA h = RecoveredDD.a(this, "FireFlies").a(true).a().b();
   private final RecoveredDAC i = RecoveredDD.a(this, "FFCount").a(30.0F).b(20.0F).c(200.0F).d(1.0F).a(() -> this.h.m()).a().c();
   private final RecoveredDAC j = RecoveredDD.a(this, "FFSize").a(1.0F).b(0.1F).c(2.0F).d(0.05F).a(() -> this.h.m()).a().c();
   private final RecoveredDAE k = RecoveredDD.a(this, "Mode").a("Off", "SnowFlake", "Stars", "Hearts", "Dollars", "Bloom").a(1).a().e();
   private final RecoveredDAC l = RecoveredDD.a(this, "Count").a(100.0F).b(20.0F).c(800.0F).d(1.0F).a(() -> !this.k.a("Off")).a().c();
   private final RecoveredDAC m = RecoveredDD.a(this, "Size").a(1.0F).b(0.1F).c(6.0F).d(0.1F).a(() -> !this.k.a("Off")).a().c();
   private final RecoveredDAE n = RecoveredDD.a(this, "Physics").a("Drop", "Fly").a(1).a(() -> !this.k.a("Off")).a().e();
   private final RecoveredDAA o = RecoveredDD.a(this, "Sync Color").a(true).a().b();
   private final RecoveredDAC p = RecoveredDD.a(this, "Color Speed").a(10.0F).b(2.0F).c(30.0F).d(1.0F).a(() -> this.o.m()).a().c();
   private final Random q = new Random();
   private final ArrayList<Particles.InnerB> r = new ArrayList<>();
   private final ArrayList<Particles.InnerB> s = new ArrayList<>();

   @Override
   public void d() {
      this.r.clear();
      this.s.clear();
   }

   @Override
   public void e() {
      this.r.clear();
      this.s.clear();
   }

   @EventTarget
   public void onUpdate(RecoveredEventsImplAd var1) {
      if (a.player != null && a.level != null) {
         this.r.removeIf(Particles.InnerB::a);
         this.s.removeIf(Particles.InnerB::a);
         this.B();
         this.C();
      }
   }

   @EventTarget
   public void onRender3D(RecoveredEventsImplP var1) {
      if (a.player != null && a.level != null) {
         PoseStack var2 = var1.b();
         float var3 = var1.a();
         if (this.h.m() && !this.r.isEmpty()) {
            this.a(var2, var3, c, this.r, this.j.q(), true);
         }

         if (!this.k.a("Off") && !this.s.isEmpty()) {
            ResourceLocation var4 = this.A();
            if (var4 != null) {
               this.a(var2, var3, var4, this.s, this.m.q(), false);
            }
         }
      }
   }

   private void a(PoseStack var1, float var2, ResourceLocation var3, List<Particles.InnerB> var4, float var5, boolean var6) {
      var1.pushPose();
      RenderSystem.enableBlend();
      RenderSystem.disableCull();
      RenderSystem.enableDepthTest();
      RenderSystem.depthMask(false);
      RenderSystem.setShader(GameRenderer::getPositionTexColorShader);
      RenderSystem.setShaderTexture(0, var3);
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      RenderSystem.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE);
      BufferBuilder var7 = Tesselator.getInstance().getBuilder();
      var7.begin(Mode.QUADS, DefaultVertexFormat.POSITION_TEX_COLOR);

      for (Particles.InnerB var9 : var4) {
         var9.a(var1, var7, var2, var5, this.c(var9.k * (var6 ? 10 : 2)), var6);
      }

      BufferUploader.drawWithShader(var7.end());
      RenderSystem.depthMask(true);
      RenderSystem.defaultBlendFunc();
      RenderSystem.enableDepthTest();
      RenderSystem.enableCull();
      RenderSystem.disableBlend();
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      var1.popPose();
   }

   private ResourceLocation A() {
      if (this.k.a("Bloom")) {
         return c;
      } else if (this.k.a("SnowFlake")) {
         return d;
      } else if (this.k.a("Stars")) {
         return e;
      } else if (this.k.a("Hearts")) {
         return f;
      } else {
         return this.k.a("Dollars") ? g : null;
      }
   }

   private int c(int var1) {
      return this.o.m() ? RecoveredUtilsRendererB.a((int)this.p.q(), -var1, HUDModule.p(), HUDModule.q(), false).getRGB() : -1;
   }

   private void B() {
      if (this.h.m()) {
         int var1 = (int)this.i.q();

         for (int var2 = this.r.size(); var2 < var1; var2++) {
            this.r
               .add(
                  new Particles.InnerA(
                     (float)(a.player.getX() + (double)this.a(-25.0F, 25.0F)),
                     (float)(a.player.getY() + (double)this.a(2.0F, 15.0F)),
                     (float)(a.player.getZ() + (double)this.a(-25.0F, 25.0F)),
                     this.a(-0.2F, 0.2F),
                     this.a(-0.1F, 0.1F),
                     this.a(-0.2F, 0.2F)
                  )
               );
         }
      }
   }

   private void C() {
      if (!this.k.a("Off")) {
         int var1 = (int)this.l.q();
         boolean var2 = this.n.a("Drop");

         for (int var3 = this.s.size(); var3 < var1; var3++) {
            this.s
               .add(
                  new Particles.InnerB(
                     (float)(a.player.getX() + (double)this.a(-48.0F, 48.0F)),
                     (float)(a.player.getY() + (double)this.a(2.0F, 48.0F)),
                     (float)(a.player.getZ() + (double)this.a(-48.0F, 48.0F)),
                     var2 ? 0.0F : this.a(-0.4F, 0.4F),
                     var2 ? this.a(-0.2F, -0.05F) : this.a(-0.1F, 0.1F),
                     var2 ? 0.0F : this.a(-0.4F, 0.4F)
                  )
               );
         }
      }
   }

   private float a(float var1, float var2) {
      return var1 + (var2 - var1) * this.q.nextFloat();
   }

   private static int a(int var0, int var1) {
      var1 = Mth.clamp(var1, 0, 255);
      return var1 << 24 | var0 & 16777215;
   }

   private static record TrailPoint(float x, float y, float z, int age) {
   }

   private class InnerA extends Particles.InnerB {
      private final ArrayList<Particles.TrailPoint> n = new ArrayList<>();

      private InnerA(float var2, float var3, float var4, float var5, float var6, float var7) {
         super(var2, var3, var4, var5, var6, var7);
      }

      @Override
      public boolean a() {
         if (Particles.a.player != null && Particles.a.level != null) {
            double var1 = Particles.a.player.getX() - (double)this.e;
            double var3 = Particles.a.player.getY() - (double)this.f;
            double var5 = Particles.a.player.getZ() - (double)this.g;
            double var7 = var1 * var1 + var3 * var3 + var5 * var5;
            if (var7 > 100.0) {
               this.k -= 4;
            } else {
               double var10001 = (double)this.e;
               if (!Particles.a.level.getBlockState(BlockPos.containing(var10001, (double)this.f, (double)this.g)).isAir()) {
                  this.k -= 8;
               } else {
                  this.k--;
               }
            }

            if (this.k < 0) {
               return true;
            } else {
               this.b = this.e;
               this.c = this.f;
               this.d = this.g;
               this.e = this.e + this.h;
               this.f = this.f + this.i;
               this.g = this.g + this.j;
               this.n.add(new Particles.TrailPoint(this.e, this.f, this.g, this.k));
               if (this.n.size() > 20) {
                  this.n.remove(0);
               }

               this.h *= 0.99F;
               this.i *= 0.99F;
               this.j *= 0.99F;
               return false;
            }
         } else {
            return true;
         }
      }

      @Override
      public void a(PoseStack var1, BufferBuilder var2, float var3, float var4, int var5, boolean var6) {
         int var7 = this.n.size();
         if (var7 > 1) {
            for (int var8 = 0; var8 < var7; var8++) {
               Particles.TrailPoint var9 = this.n.get(var8);
               float var10 = (float)var8 / (float)var7;
               int var11 = (int)(255.0F * ((float)this.k / (float)this.l) * var10);
               this.a(var1, var2, var3, var9.x, var9.y, var9.z, var4, Particles.a(var5, var11));
            }
         }

         super.a(var1, var2, var3, var4, var5, true);
      }
   }

   private class InnerB {
      protected float b;
      protected float c;
      protected float d;
      protected float e;
      protected float f;
      protected float g;
      protected float h;
      protected float i;
      protected float j;
      protected int k;
      protected int l;

      private InnerB(float var2, float var3, float var4, float var5, float var6, float var7) {
         this.e = var2;
         this.f = var3;
         this.g = var4;
         this.b = var2;
         this.c = var3;
         this.d = var4;
         this.h = var5;
         this.i = var6;
         this.j = var7;
         this.k = (int)Particles.this.a(100.0F, 300.0F);
         this.l = this.k;
      }

      public boolean a() {
         if (Particles.a.player == null) {
            return true;
         } else {
            double var1 = Particles.a.player.getX() - (double)this.e;
            double var3 = Particles.a.player.getY() - (double)this.f;
            double var5 = Particles.a.player.getZ() - (double)this.g;
            double var7 = var1 * var1 + var3 * var3 + var5 * var5;
            if (var7 > 4096.0) {
               this.k -= 8;
            } else {
               this.k--;
            }

            if (this.k < 0) {
               return true;
            } else {
               this.b = this.e;
               this.c = this.f;
               this.d = this.g;
               this.e = this.e + this.h;
               this.f = this.f + this.i;
               this.g = this.g + this.j;
               this.h *= 0.9F;
               if (Particles.this.n.a("Fly")) {
                  this.i *= 0.9F;
               }

               this.j *= 0.9F;
               this.i -= 0.001F;
               return false;
            }
         }
      }

      public void a(PoseStack var1, BufferBuilder var2, float var3, float var4, int var5, boolean var6) {
         float var7 = Mth.lerp(var3, this.b, this.e);
         float var8 = Mth.lerp(var3, this.c, this.f);
         float var9 = Mth.lerp(var3, this.d, this.g);
         int var10 = (int)(255.0F * ((float)this.k / (float)this.l));
         this.a(var1, var2, var3, var7, var8, var9, var4, Particles.a(var5, var10));
      }

      protected void a(PoseStack var1, BufferBuilder var2, float var3, float var4, float var5, float var6, float var7, int var8) {
         Vec3 var9 = RecoveredUtilsAd.b();
         Camera var10 = Particles.a.gameRenderer.getMainCamera();
         var1.pushPose();
         var1.translate((double)var4 - var9.x, (double)var5 - var9.y, (double)var6 - var9.z);
         var1.mulPose(Axis.YP.rotationDegrees(-var10.getYRot()));
         var1.mulPose(Axis.XP.rotationDegrees(var10.getXRot()));
         float var11 = (float)(var8 >> 24 & 0xFF) / 255.0F;
         float var12 = (float)(var8 >> 16 & 0xFF) / 255.0F;
         float var13 = (float)(var8 >> 8 & 0xFF) / 255.0F;
         float var14 = (float)(var8 & 0xFF) / 255.0F;
         Matrix4f var15 = var1.last().pose();
         var2.vertex(var15, -var7, -var7, 0.0F).uv(0.0F, 1.0F).color(var12, var13, var14, var11).endVertex();
         var2.vertex(var15, var7, -var7, 0.0F).uv(1.0F, 1.0F).color(var12, var13, var14, var11).endVertex();
         var2.vertex(var15, var7, var7, 0.0F).uv(1.0F, 0.0F).color(var12, var13, var14, var11).endVertex();
         var2.vertex(var15, -var7, var7, 0.0F).uv(0.0F, 0.0F).color(var12, var13, var14, var11).endVertex();
         var1.popPose();
      }
   }
}
