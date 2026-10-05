package com.heypixel.heypixelmod.mixin.O;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.api.events.Event;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplT;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.DynamicIslandHud;
import java.util.List;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.PlayerTabOverlay;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.Scoreboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({PlayerTabOverlay.class})
public abstract class MixinPlayerTabOverlay {
   @Shadow
   public abstract Component getNameForDisplay(PlayerInfo var1);

   @Inject(
      method = {"render"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void cancelRender(GuiGraphics var1, int var2, Scoreboard var3, Objective var4, CallbackInfo var5) {
      DynamicIslandHud var6 = EixClient.a().g().a(DynamicIslandHud.class);
      if (var6 != null && var6.m()) {
         var5.cancel();
      }
   }

   @Redirect(
      method = {"render"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/Font;split(Lnet/minecraft/network/chat/FormattedText;I)Ljava/util/List;",
         ordinal = 0
      )
   )
   public List<FormattedCharSequence> hookHeader(Font var1, FormattedText var2, int var3) {
      Component var4 = (Component)var2;
      RecoveredEventsImplT var5 = new RecoveredEventsImplT(RecoveredEventsApiAA.HEADER, var4);
      EixClient.a().b().a((Event)var5);
      return var1.split(var5.b(), var3);
   }

   @Redirect(
      method = {"render"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/Font;split(Lnet/minecraft/network/chat/FormattedText;I)Ljava/util/List;",
         ordinal = 1
      )
   )
   public List<FormattedCharSequence> hookFooter(Font var1, FormattedText var2, int var3) {
      Component var4 = (Component)var2;
      RecoveredEventsImplT var5 = new RecoveredEventsImplT(RecoveredEventsApiAA.FOOTER, var4);
      EixClient.a().b().a((Event)var5);
      return var1.split(var5.b(), var3);
   }

   @Redirect(
      method = {"render"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/components/PlayerTabOverlay;getNameForDisplay(Lnet/minecraft/client/multiplayer/PlayerInfo;)Lnet/minecraft/network/chat/Component;"
      )
   )
   public Component hookName(PlayerTabOverlay var1, PlayerInfo var2) {
      Component var3 = this.getNameForDisplay(var2);
      RecoveredEventsImplT var4 = new RecoveredEventsImplT(RecoveredEventsApiAA.NAME, var3);
      EixClient.a().b().a((Event)var4);
      return var4.b();
   }
}
