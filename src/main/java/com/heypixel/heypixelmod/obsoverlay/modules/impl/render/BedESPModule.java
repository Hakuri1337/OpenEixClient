package com.heypixel.heypixelmod.obsoverlay.modules.impl.render;

import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
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
import net.minecraft.world.level.block.entity.BedBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;

@ModuleInfo(
   a = "BedESP",
   b = "床透视",
   c = "Highlights beds",
   d = ModuleCategory.RENDER
)
public class BedESPModule extends ClientModule {
   private static final float[] c = new float[]{1.0F, 0.0F, 0.0F};
   private final List<AABB> d = new CopyOnWriteArrayList<>();

   @EventTarget
   public void onRespawn(RecoveredEventsImplU var1) {
      this.d.clear();
   }

   @EventTarget
   public void onTick(RecoveredEventsImplK var1) {
      if (var1.b() == RecoveredEventsApiAA.PRE) {
         ArrayList<BlockEntity> var2 = RecoveredUtilsG.a().collect(Collectors.toCollection(ArrayList::new));
         this.d.clear();

         for (BlockEntity var4 : var2) {
            if (var4 instanceof BedBlockEntity var5) {
               AABB var6 = RecoveredUtilsE.a(var5.getBlockPos());
               this.d.add(var6);
            }
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
      RenderSystem.setShaderColor(c[0], c[1], c[2], 0.25F);

      for (AABB var6 : this.d) {
         RecoveredUtilsAd.a(var4, var2.last().pose(), var6);
      }

      RenderSystem.disableBlend();
      RenderSystem.enableDepthTest();
      RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
      var2.popPose();
   }
}
