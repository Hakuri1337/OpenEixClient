package com.heypixel.heypixelmod.mixin.O;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.events.api.events.Event;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplAf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({ItemInHandLayer.class})
public class MixinItemInHandLayer {
   @Redirect(
      method = {"render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/entity/LivingEntity;getMainHandItem()Lnet/minecraft/world/item/ItemStack;"
      )
   )
   private ItemStack hookMainHand(LivingEntity var1) {
      RecoveredEventsImplAf var2 = new RecoveredEventsImplAf(InteractionHand.MAIN_HAND, var1.getMainHandItem());
      if (var1 == Minecraft.getInstance().player) {
         EixClient.a().b().a((Event)var2);
      }

      return var2.b();
   }

   @Redirect(
      method = {"render(Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/entity/LivingEntity;getOffhandItem()Lnet/minecraft/world/item/ItemStack;"
      )
   )
   private ItemStack hookOffHand(LivingEntity var1) {
      RecoveredEventsImplAf var2 = new RecoveredEventsImplAf(InteractionHand.OFF_HAND, var1.getOffhandItem());
      if (var1 == Minecraft.getInstance().player) {
         EixClient.a().b().a((Event)var2);
      }

      return var2.b();
   }
}
