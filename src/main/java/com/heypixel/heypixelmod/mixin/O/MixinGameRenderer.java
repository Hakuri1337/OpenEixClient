package com.heypixel.heypixelmod.mixin.O;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.events.api.events.Event;
import com.heypixel.heypixelmod.obsoverlay.events.impl.EventRender2D;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplP;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplQ;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.FullBrightModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.MotionBlurModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.NoHurtCamModule;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderBuffers;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({GameRenderer.class})
public class MixinGameRenderer {
   @Shadow
   @Final
   private Minecraft minecraft;
   @Shadow
   @Final
   private RenderBuffers renderBuffers;

   @Inject(
      method = {"getNightVisionScale"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private static void getNightVisionScale(LivingEntity var0, float var1, CallbackInfoReturnable<Float> var2) {
      FullBrightModule var3 = EixClient.a().g().a(FullBrightModule.class);
      if (var3.m()) {
         var2.setReturnValue(var3.c.q());
         var2.cancel();
      }
   }

   @Inject(
      method = {"renderLevel"},
      at = {@At(
         value = "FIELD",
         target = "Lnet/minecraft/client/renderer/GameRenderer;renderHand:Z",
         opcode = 180,
         ordinal = 0
      )}
   )
   private void renderLevel(float var1, long var2, PoseStack var4, CallbackInfo var5) {
      EixClient.a().b().a((Event)(new RecoveredEventsImplP(var1, var4)));
   }

   @Inject(
      method = {"renderLevel"},
      at = {@At("TAIL")}
   )
   private void onRenderWorldTail(CallbackInfo var1) {
      EixClient.a().b().a((Event)(new RecoveredEventsImplQ()));
   }

   @Inject(
      method = {"render"},
      at = {@At("TAIL")}
   )
   public void render(float var1, long var2, boolean var4, CallbackInfo var5) {
      MotionBlurModule var6 = MotionBlurModule.c;
      if (var6 != null && var6.m() && this.minecraft.player != null && var6.d != null) {
         var6.d.process(var1);
      }
   }

   @Inject(
      method = {"render"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/Gui;render(Lnet/minecraft/client/gui/GuiGraphics;F)V"
      )}
   )
   public void injectRender2DEvent(float var1, long var2, boolean var4, CallbackInfo var5) {
      GuiGraphics var6 = new GuiGraphics(this.minecraft, this.renderBuffers.bufferSource());
      EventRender2D var7 = new EventRender2D(var6.pose(), var6);
      EixClient.a().b().a((Event)var7);
   }

   @Inject(
      method = {"bobHurt"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void bobHurt(PoseStack var1, float var2, CallbackInfo var3) {
      NoHurtCamModule var4 = EixClient.a().g().a(NoHurtCamModule.class);
      if (var4.m()) {
         var3.cancel();
      }
   }
}
