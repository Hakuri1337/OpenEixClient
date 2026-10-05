package com.heypixel.heypixelmod.obsoverlay.modules.impl.render;

import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplM;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplP;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplU;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAd;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsE;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsG;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.Tesselator;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.game.ClientboundBlockEventPacket;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.minecraft.world.phys.AABB;

@ModuleInfo(
   a = "ChestESP",
   b = "箱子透视",
   c = "Highlights chests",
   d = ModuleCategory.RENDER
)
public class ChestESPModule extends ClientModule {
   private static final float[] c = new float[]{0.0F, 1.0F, 0.0F};
   private static final float[] d = new float[]{1.0F, 0.0F, 0.0F};
   private final List<BlockPos> e = new CopyOnWriteArrayList<>();
   private final List<AABB> f = new CopyOnWriteArrayList<>();

   @Override
   public void e() {
   }

   @EventTarget
   public void onRespawn(RecoveredEventsImplU var1) {
      this.e.clear();
   }

   @EventTarget
   public void onPacket(RecoveredEventsImplM var1) {
      if (var1.b() == RecoveredEventsApiAA.RECEIVE
         && var1.c() instanceof ClientboundBlockEventPacket var2
         && (var2.getBlock() == Blocks.CHEST || var2.getBlock() == Blocks.TRAPPED_CHEST)
         && var2.getB0() == 1
         && var2.getB1() == 1) {
         this.e.add(var2.getPos());
      }
   }

   @EventTarget
   public void onTick(RecoveredEventsImplK var1) {
      if (var1.b() == RecoveredEventsApiAA.PRE) {
         ArrayList<BlockEntity> var2 = RecoveredUtilsG.a().collect(Collectors.toCollection(ArrayList::new));
         this.f.clear();

         for (BlockEntity var4 : var2) {
            if (var4 instanceof ChestBlockEntity) {
               ChestBlockEntity var5 = (ChestBlockEntity)var4;
               AABB var6 = this.a(var5);
               if (var6 != null) {
                  this.f.add(var6);
               }
            }
         }
      }
   }

   private AABB a(ChestBlockEntity var1) {
      BlockState var2 = var1.getBlockState();
      if (!var2.hasProperty(ChestBlock.TYPE)) {
         return null;
      } else {
         ChestType var3 = var2.getValue(ChestBlock.TYPE);
         if (var3 == ChestType.LEFT) {
            return null;
         } else {
            BlockPos var4 = var1.getBlockPos();
            AABB var5 = RecoveredUtilsE.a(var4);
            if (var3 != ChestType.SINGLE) {
               BlockPos var6 = var4.relative(ChestBlock.getConnectedDirection(var2));
               if (RecoveredUtilsE.c(var6)) {
                  AABB var7 = RecoveredUtilsE.a(var6);
                  var5 = var5.minmax(var7);
               }
            }

            return var5;
         }
      }
   }

   @EventTarget
   public void onRender(RecoveredEventsImplP var1) {
      PoseStack var2 = var1.b();
      var2.pushPose();
      RenderSystem.disableDepthTest();
      RenderSystem.enableBlend();
      RenderSystem.defaultBlendFunc();
      RenderSystem.setShader(GameRenderer::getPositionShader);
      Tesselator var3 = RenderSystem.renderThreadTesselator();
      BufferBuilder var4 = var3.getBuilder();

      for (AABB var6 : this.f) {
         BlockPos var7 = BlockPos.containing(var6.minX, var6.minY, var6.minZ);
         float[] var8 = this.e.contains(var7) ? d : c;
         RenderSystem.setShaderColor(var8[0], var8[1], var8[2], 0.25F);
         RecoveredUtilsAd.a(var4, var2.last().pose(), var6);
      }

      RenderSystem.disableBlend();
      RenderSystem.enableDepthTest();
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      var2.popPose();
   }
}
