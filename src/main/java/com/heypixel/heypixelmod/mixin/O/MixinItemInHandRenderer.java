package com.heypixel.heypixelmod.mixin.O;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.events.api.events.Event;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplAf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({ItemInHandRenderer.class})
public class MixinItemInHandRenderer {
   @Redirect(
      method = {"tick"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/player/LocalPlayer;getMainHandItem()Lnet/minecraft/world/item/ItemStack;"
      )
   )
   public ItemStack hookMainHand(LocalPlayer var1) {
      RecoveredEventsImplAf var2 = new RecoveredEventsImplAf(InteractionHand.MAIN_HAND, var1.getMainHandItem());
      if (var1 == Minecraft.getInstance().player) {
         EixClient.a().b().a((Event)var2);
      }

      return var2.b();
   }

   @Redirect(
      method = {"tick"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/player/LocalPlayer;getOffhandItem()Lnet/minecraft/world/item/ItemStack;"
      )
   )
   public ItemStack hookOffHand(LocalPlayer var1) {
      RecoveredEventsImplAf var2 = new RecoveredEventsImplAf(InteractionHand.OFF_HAND, var1.getOffhandItem());
      if (var1 == Minecraft.getInstance().player) {
         EixClient.a().b().a((Event)var2);
      }

      return var2.b();
   }
}
