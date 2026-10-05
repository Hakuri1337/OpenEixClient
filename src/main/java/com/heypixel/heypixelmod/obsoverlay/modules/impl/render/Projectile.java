package com.heypixel.heypixelmod.obsoverlay.modules.impl.render;

import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplP;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.a.RecoveredModulesImplRenderAA;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.a.a.RecoveredModulesImplRenderAAA;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.a.a.RecoveredModulesImplRenderAAB;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.a.a.RecoveredModulesImplRenderAAC;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAb;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAd;
import com.heypixel.heypixelmod.obsoverlay.utils.c.RecoveredUtilsCC;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.BufferUploader;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat.Mode;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.EggItem;
import net.minecraft.world.item.EnderpearlItem;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.LingeringPotionItem;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.item.SnowballItem;
import net.minecraft.world.item.SplashPotionItem;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.ClipContext.Block;
import net.minecraft.world.level.ClipContext.Fluid;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.HitResult.Type;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;

@ModuleInfo(
   a = "Projectiles",
   b = "投掷物落点显示",
   c = "Renders projectiles",
   d = ModuleCategory.RENDER
)
public class Projectile extends ClientModule {
   private final RecoveredModulesImplRenderAAB h = new RecoveredModulesImplRenderAAB();
   private final RecoveredModulesImplRenderAAC i = new RecoveredModulesImplRenderAAC();
   private final RecoveredModulesImplRenderAAA j = new RecoveredModulesImplRenderAAA(Collections.singleton(ThrownEnderpearl.class), new Color(173, 12, 255));
   private final RecoveredModulesImplRenderAAA k = new RecoveredModulesImplRenderAAA(Collections.singleton(ThrownEgg.class), new Color(255, 238, 154));
   private final RecoveredModulesImplRenderAAA l = new RecoveredModulesImplRenderAAA(Collections.singleton(Snowball.class), new Color(255, 255, 255));
   public RecoveredDAA c = RecoveredDD.a(this, "Show Arrows").a(true).a().b();
   public RecoveredDAA d = RecoveredDD.a(this, "Show Pearls").a(true).a().b();
   public RecoveredDAA e = RecoveredDD.a(this, "Show Potions").a(false).a().b();
   public RecoveredDAA f = RecoveredDD.a(this, "Show Eggs").a(false).a().b();
   public RecoveredDAA g = RecoveredDD.a(this, "Show Snowballs").a(false).a().b();

   @EventTarget
   public void onRender3D(RecoveredEventsImplP var1) {
      for (Entity var3 : a.level.entitiesForRendering()) {
         if (var3 instanceof net.minecraft.world.entity.projectile.Projectile) {
            RecoveredModulesImplRenderAA var4 = this.a(var3);
            if (var4 != null) {
               PoseStack var5 = var1.b();
               var5.pushPose();
               GL11.glEnable(3042);
               GL11.glBlendFunc(770, 771);
               GL11.glDisable(2929);
               GL11.glDepthMask(false);
               GL11.glEnable(2848);
               RenderSystem.setShader(GameRenderer::getPositionShader);
               Color var6 = var4.a((Object)var3);
               RenderSystem.setShaderColor((float)var6.getRed() / 255.0F, (float)var6.getGreen() / 255.0F, (float)var6.getBlue() / 255.0F, 1.0F);
               this.a(var5, var3, var4);
               RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
               GL11.glDisable(3042);
               GL11.glEnable(2929);
               GL11.glDepthMask(true);
               GL11.glDisable(2848);
               var5.popPose();
            }
         }
      }
   }

   @EventTarget
   private void onRender(RecoveredEventsImplP var1) {
      Projectile.Path var2 = this.a(var1.a());
      if (var2 != null) {
         List var3 = var2.path();
         if (var3.size() >= 2) {
            PoseStack var4 = var1.b();
            var4.pushPose();
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glDisable(2929);
            GL11.glDepthMask(false);
            GL11.glEnable(2848);
            RenderSystem.setShader(GameRenderer::getPositionShader);
            Vec3 var5 = (Vec3)var3.get(0);
            this.a(var4, var3, var5);
            if (!var3.isEmpty()) {
               Vec3 var6 = (Vec3)var3.get(var3.size() - 1);
               this.a(var4, var6, var5, var2.result, var1.a());
            }

            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(3042);
            GL11.glEnable(2929);
            GL11.glDepthMask(true);
            GL11.glDisable(2848);
            var4.popPose();
         }
      }
   }

   private void a(PoseStack var1, List<Vec3> var2, Vec3 var3) {
      Matrix4f var4 = var1.last().pose();
      BufferBuilder var5 = Tesselator.getInstance().getBuilder();
      RenderSystem.setShader(GameRenderer::getPositionShader);
      var5.begin(Mode.DEBUG_LINE_STRIP, DefaultVertexFormat.POSITION);
      float[] var6 = new float[]{1.0F, 1.0F, 1.0F};
      RenderSystem.setShaderColor(var6[0], var6[1], var6[2], 1.0F);

      for (Vec3 var8 : var2) {
         var5.vertex(var4, (float)(var8.x - var3.x), (float)(var8.y - var3.y), (float)(var8.z - var3.z)).endVertex();
      }

      BufferUploader.drawWithShader(var5.end());
   }

   private void a(PoseStack var1, Vec3 var2, Vec3 var3, HitResult var4, float var5) {
      AABB var6 = new AABB(0.15, 0.15, 0.15, 0.35, 0.35, 0.35);
      float[] var7 = new float[]{1.0F, 1.0F, 1.0F};
      if (var4 != null) {
         if (var4.getType() == Type.BLOCK) {
            BlockHitResult var8 = (BlockHitResult)var4;
            Direction var9 = var8.getDirection();
            if (var9 == Direction.SOUTH) {
               var6 = new AABB(0.0, 0.0, 0.0, 0.5, 0.5, 0.1);
            } else if (var9 == Direction.NORTH) {
               var6 = new AABB(0.0, 0.0, 0.4, 0.5, 0.5, 0.5);
            } else if (var9 == Direction.EAST) {
               var6 = new AABB(0.0, 0.0, 0.0, 0.1, 0.5, 0.5);
            } else if (var9 == Direction.WEST) {
               var6 = new AABB(0.4, 0.0, 0.0, 0.5, 0.5, 0.5);
            } else if (var9 == Direction.UP) {
               var7 = new float[]{0.0F, 1.0F, 0.0F};
               var6 = new AABB(0.0, 0.0, 0.0, 0.5, 0.1, 0.5);
            } else if (var9 == Direction.DOWN) {
               var6 = new AABB(0.0, 0.4, 0.0, 0.5, 0.5, 0.5);
            }
         } else if (var4.getType() == Type.ENTITY) {
            EntityHitResult var18 = (EntityHitResult)var4;
            var7 = new float[]{1.0F, 0.0F, 0.0F};
            RenderSystem.setShaderColor(var7[0], var7[1], var7[2], 0.5F);
            Entity var20 = var18.getEntity();
            double var10 = var20.getX() - var20.xo;
            double var12 = var20.getY() - var20.yo;
            double var14 = var20.getZ() - var20.zo;
            Vec3 var16 = RecoveredUtilsAd.b();
            AABB var17 = var20.getBoundingBox()
               .move(-var16.x, -var16.y, -var16.z)
               .move(-var10, -var12, -var14)
               .move((double)var5 * var10, (double)var5 * var12, (double)var5 * var14)
               .inflate(0.1);
            RecoveredUtilsAd.a(var17, var1);
         }
      }

      double var19 = var2.x - var3.x;
      double var21 = var2.y - var3.y;
      double var22 = var2.z - var3.z;
      var1.pushPose();
      var1.translate(var19 - 0.25, var21 - 0.25, var22 - 0.25);
      RenderSystem.setShaderColor(var7[0], var7[1], var7[2], 0.25F);
      RecoveredUtilsAd.a(var6, var1);
      RenderSystem.setShaderColor(var7[0], var7[1], var7[2], 0.75F);
      RecoveredUtilsAd.b(var6, var1);
      var1.popPose();
   }

   private Projectile.Path a(float var1) {
      LocalPlayer var2 = a.player;
      ArrayList var3 = new ArrayList();
      ItemStack var4 = var2.getMainHandItem();
      Item var5 = var4.getItem();
      if (!var4.isEmpty() && this.b(var5)) {
         double var6 = var2.xOld + (var2.getX() - var2.xOld) * (double)var1;
         double var8 = var2.yOld + (var2.getY() - var2.yOld) * (double)var1 + (double)var2.getEyeHeight() - 0.1;
         double var10 = var2.zOld + (var2.getZ() - var2.zOld) * (double)var1;
         double var12 = var5 instanceof ProjectileWeaponItem ? 1.0 : 0.4;
         double var14;
         double var16;
         if (RecoveredUtilsCC.k()) {
            if (RecoveredUtilsCC.e == null || RecoveredUtilsCC.d == null) {
               return new Projectile.Path(var3, null);
            }

            var14 = Math.toRadians((double)Mth.lerp(var1, RecoveredUtilsCC.e.a(), RecoveredUtilsCC.d.a()));
            var16 = Math.toRadians((double)Mth.lerp(var1, RecoveredUtilsCC.e.b(), RecoveredUtilsCC.d.b()));
         } else {
            var14 = Math.toRadians((double)Mth.lerp(var1, var2.yRotO, var2.getYRot()));
            var16 = Math.toRadians((double)Mth.lerp(var1, var2.xRotO, var2.getXRot()));
         }

         double var18 = -Math.sin(var14) * Math.cos(var16) * var12;
         double var20 = -Math.sin(var16) * var12;
         double var22 = Math.cos(var14) * Math.cos(var16) * var12;
         double var24 = Math.sqrt(var18 * var18 + var20 * var20 + var22 * var22);
         var18 /= var24;
         var20 /= var24;
         var22 /= var24;
         if (var5 instanceof ProjectileWeaponItem) {
            float var26 = (float)(72000 - var2.getUseItemRemainingTicks()) / 20.0F;
            var26 = (var26 * var26 + var26 * 2.0F) / 3.0F;
            if (var26 > 1.0F || var26 <= 0.1F) {
               var26 = 1.0F;
            }

            var26 *= 3.0F;
            var18 *= (double)var26;
            var20 *= (double)var26;
            var22 *= (double)var26;
         } else {
            var18 *= 1.5;
            var20 *= 1.5;
            var22 *= 1.5;
         }

         double var44 = this.a(var5);

         for (int var28 = 0; var28 < 1000; var28++) {
            Vec3 var29 = new Vec3(var6, var8, var10);
            Vec3 var30 = new Vec3(var6 + var18, var8 + var20, var10 + var22);
            var3.add(var29);
            ClipContext var31 = new ClipContext(var29, var30, Block.COLLIDER, Fluid.NONE, a.player);
            BlockHitResult var32 = a.level.clip(var31);
            if (var32.getType() != Type.MISS) {
               return new Projectile.Path(var3, var32);
            }

            Arrow var33 = new Arrow(a.level, var6, var8, var10);
            EntityHitResult var34 = ProjectileUtil.getEntityHitResult(
               a.level,
               var33,
               var29,
               var30,
               var33.getBoundingBox().expandTowards(new Vec3(var18, var20, var22)).inflate(1.0),
               var1x -> var1x != var2 && var1x instanceof LivingEntity
            );
            if (var34 != null && var34.getType() == Type.ENTITY) {
               return new Projectile.Path(var3, var34);
            }

            var6 += var18;
            var8 += var20;
            var10 += var22;
            var18 *= 0.99;
            var20 *= 0.99;
            var22 *= 0.99;
            var20 -= var44;
         }

         return new Projectile.Path(var3, null);
      } else {
         return null;
      }
   }

   private double a(Item var1) {
      if (var1 instanceof BowItem || var1 instanceof CrossbowItem) {
         return 0.05;
      } else if (var1 instanceof PotionItem) {
         return 0.4;
      } else if (var1 instanceof FishingRodItem) {
         return 0.15;
      } else {
         return var1 instanceof TridentItem ? 0.015 : 0.03;
      }
   }

   private boolean b(Item var1) {
      return var1 instanceof BowItem
         || var1 instanceof CrossbowItem
         || var1 instanceof SnowballItem
         || var1 instanceof EggItem
         || var1 instanceof EnderpearlItem
         || var1 instanceof SplashPotionItem
         || var1 instanceof LingeringPotionItem
         || var1 instanceof FishingRodItem
         || var1 instanceof TridentItem;
   }

   private void a(PoseStack var1, Entity var2, RecoveredModulesImplRenderAA var3) {
      if (var2 != null) {
         LocalPlayer var4 = a.player;
         ClientLevel var5 = a.level;
         Color var6 = var3.a((Object)var2);
         if (var6 == null) {
            var6 = new Color(255, 255, 255);
         }

         Tesselator var7 = Tesselator.getInstance();
         BufferBuilder var8 = var7.getBuilder();
         var8.begin(Mode.DEBUG_LINE_STRIP, DefaultVertexFormat.POSITION_COLOR);
         double var9 = var2.getX();
         double var11 = var2.getY();
         double var13 = var2.getZ();
         double var15 = var2.getDeltaMovement().x;
         double var17 = var2.getDeltaMovement().y;
         double var19 = var2.getDeltaMovement().z;
         this.a(var6, var8, var1, var9, var11, var13);

         while (true) {
            float var21 = var3.a();
            float var22 = var3.b();
            AABB var23 = new AABB(var9 - (double)var21, var11, var13 - (double)var21, var9 + (double)var21, var11 + (double)var22, var13 + (double)var21);
            Vec3 var24 = new Vec3(var9, var11, var13);
            Vec3 var25 = new Vec3(var9 + var15, var11 + var17, var13 + var19);
            net.minecraft.world.phys.HitResult var26 = RecoveredUtilsAb.a(var24, var25, false, var2 instanceof Arrow, false, var2);
            if (!var26.getType().equals(Type.MISS)) {
               var25 = new Vec3(var26.getLocation().x(), var26.getLocation().y(), var26.getLocation().z());
            }

            List<Entity> var27 = var5.getEntities(var4, var23.contract(var15, var17, var19).expandTowards(1.0, 1.0, 1.0));
            double var28 = 0.0;

            for (Entity var31 : var27) {
               if (var31 instanceof LivingEntity && !(var31 instanceof EnderMan) && var31.canBeCollidedWith() && !var31.equals(var4)) {
                  var23 = var31.getBoundingBox().expandTowards(0.3, 0.3, 0.3);
                  EntityHitResult var32 = RecoveredUtilsAb.a(var23, var24, var25);
                  if (var32 != null) {
                     double var33 = var24.distanceTo(var32.getLocation());
                     if (var33 < var28 || var28 == 0.0) {
                        var28 = var33;
                        var26 = var32;
                     }
                  }
               }
            }

            var9 += var15;
            var11 += var17;
            var13 += var19;
            if (!var26.getType().equals(Type.MISS)) {
               var9 = var26.getLocation().x();
               var11 = var26.getLocation().y();
               var13 = var26.getLocation().z();
               break;
            }

            if (var11 < -128.0) {
               break;
            }

            var15 *= var2.isInWater() ? 0.8 : 0.99;
            double var39 = var17 * (var2.isInWater() ? 0.8 : 0.99);
            var19 *= var2.isInWater() ? 0.8 : 0.99;
            var17 = var39 - (double)var3.c();
            this.a(var6, var8, var1, var9 + var15, var11 + var17, var13 + var19);
         }

         var7.end();
      }
   }

   private void a(Color var1, BufferBuilder var2, PoseStack var3, double var4, double var6, double var8) {
      Entity var10 = a.getCameraEntity();
      double var11 = var10.xOld + (var10.getX() - var10.xOld) * (double)a.getFrameTime();
      double var13 = var10.yOld + (var10.getY() - var10.yOld) * (double)a.getFrameTime();
      double var15 = var10.zOld + (var10.getZ() - var10.zOld) * (double)a.getFrameTime();
      var2.vertex(var3.last().pose(), (float)(var4 - var11), (float)(var6 - var13) - 1.5F, (float)(var8 - var15)).color(var1.getRGB()).endVertex();
   }

   private RecoveredModulesImplRenderAA a(Entity var1) {
      if (var1.onGround()) {
         return null;
      } else if (var1.getX() == var1.xOld && var1.getZ() == var1.zOld) {
         return null;
      } else {
         for (RecoveredModulesImplRenderAA var3 : this.p()) {
            if (var3.a(var1)) {
               return var3;
            }
         }

         return null;
      }
   }

   private List<RecoveredModulesImplRenderAA> p() {
      ArrayList var1 = new ArrayList();
      if (this.c.m()) {
         var1.add(this.h);
      }

      if (this.e.m()) {
         var1.add(this.i);
      }

      if (this.d.m()) {
         var1.add(this.j);
      }

      if (this.f.m()) {
         var1.add(this.k);
      }

      if (this.g.m()) {
         var1.add(this.l);
      }

      return var1;
   }

   public static record Path(List<Vec3> path, HitResult result) {
      @Override
      public boolean equals(Object var1) {
         if (var1 == this) {
            return true;
         } else if (var1 instanceof Projectile.Path var2) {
            if (!var2.canEqual(this)) {
               return false;
            } else {
               List var3 = this.path();
               List var4 = var2.path();
               if (Objects.equals(var3, var4)) {
                  HitResult var5 = this.result();
                  HitResult var6 = var2.result();
                  return Objects.equals(var5, var6);
               } else {
                  return false;
               }
            }
         } else {
            return false;
         }
      }

      private boolean canEqual(Object var1) {
         return var1 instanceof Projectile.Path;
      }

      @Override
      public int hashCode() {
         byte var1 = 59;
         int var2 = 1;
         List var3 = this.path();
         var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
         HitResult var4 = this.result();
         return var2 * 59 + (var4 == null ? 43 : var4.hashCode());
      }

      @Override
      public String toString() {
         return "Projectile.Path(path=" + this.path() + ", result=" + this.result() + ")";
      }
   }
}
