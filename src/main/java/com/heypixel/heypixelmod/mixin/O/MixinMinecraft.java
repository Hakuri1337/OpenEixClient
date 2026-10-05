package com.heypixel.heypixelmod.mixin.O;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.RecoveredB;
import com.heypixel.heypixelmod.obsoverlay.c.RecoveredCC;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.api.events.Event;
import com.heypixel.heypixelmod.obsoverlay.events.impl.EventRunTicks;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplD;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplY;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.GlowModule;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsA;
import com.heypixel.heypixelmod.obsoverlay.utils.d.a.RecoveredUtilsDAA;
import com.heypixel.heypixelmod.obsoverlay.utils.e.a.RecoveredUtilsEAA;
import com.mojang.blaze3d.platform.Window;
import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.main.GameConfig;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.forgespi.language.IModFileInfo;
import net.minecraftforge.forgespi.language.IModInfo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({Minecraft.class})
public class MixinMinecraft {
   @Unique
   private int skipTicks;
   @Unique
   private long naven_Modern$lastFrame;

   @Inject(
      method = {"<init>"},
      at = {@At("RETURN")}
   )
   public void onInit(GameConfig var1, CallbackInfo var2) {
      System.setProperty("java.awt.headless", "false");
      ModList.get().getMods().removeIf(var0 -> var0.getModId().contains("naven"));
      ArrayList var3 = new ArrayList();

      for (IModFileInfo var5 : ModList.get().getModFiles()) {
         for (IModInfo var7 : var5.getMods()) {
            if (var7.getModId().contains("naven")) {
               var3.add(var5);
            }
         }
      }

      ModList.get().getModFiles().removeAll(var3);
   }

   @Inject(
      method = {"close"},
      at = {@At("HEAD")},
      remap = false
   )
   private void shutdown(CallbackInfo var1) {
      if (EixClient.a() != null && EixClient.a().b() != null) {
         EixClient.a().b().a((Event)(new RecoveredEventsImplY()));
      }
   }

   @Inject(
      method = {"tick"},
      at = {@At("HEAD")}
   )
   private void tickPre(CallbackInfo var1) {
      if (EixClient.a() != null && EixClient.a().b() != null) {
         EixClient.a().b().a((Event)(new EventRunTicks(RecoveredEventsApiAA.PRE)));
      }
   }

   @Inject(
      method = {"tick"},
      at = {@At("TAIL")}
   )
   private void tickPost(CallbackInfo var1) {
      if (EixClient.a() != null && EixClient.a().b() != null) {
         EixClient.a().b().a((Event)(new EventRunTicks(RecoveredEventsApiAA.POST)));
      }
   }

   @Inject(
      method = {"shouldEntityAppearGlowing"},
      at = {@At("RETURN")},
      cancellable = true
   )
   private void shouldEntityAppearGlowing(Entity var1, CallbackInfoReturnable<Boolean> var2) {
      if (GlowModule.a(var1)) {
         var2.setReturnValue(true);
      }
   }

   @Inject(
      method = {"runTick"},
      at = {@At("HEAD")}
   )
   private void runTick(CallbackInfo var1) {
      long var2 = System.nanoTime() / 1000000L;
      int var4 = (int)(var2 - this.naven_Modern$lastFrame);
      this.naven_Modern$lastFrame = var2;
      RecoveredUtilsA.a = var4;
   }

   @ModifyArg(
      method = {"runTick"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/renderer/GameRenderer;render(FJZ)V"
      )
   )
   private float fixSkipTicks(float var1) {
      if (this.skipTicks > 0) {
         var1 = 0.0F;
      }

      return var1;
   }

   @ModifyVariable(
      method = {"setScreen"},
      at = @At("HEAD"),
      argsOnly = true
   )
   private Screen replaceTitleScreen(Screen var1) {
      return (Screen)(var1 instanceof TitleScreen ? new RecoveredCC() : var1);
   }

   @Inject(
      method = {"handleKeybinds"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/player/LocalPlayer;isUsingItem()Z",
         ordinal = 0,
         shift = Shift.BEFORE
      )},
      cancellable = true
   )
   private void clickEvent(CallbackInfo var1) {
      if (EixClient.a() != null && EixClient.a().b() != null) {
         RecoveredEventsImplD var2 = new RecoveredEventsImplD();
         EixClient.a().b().a((Event)var2);
         if (var2.a()) {
            var1.cancel();
         }
      }
   }

   @Inject(
      method = {"resizeDisplay"},
      at = {@At("HEAD")}
   )
   private void onResizeDisplay(CallbackInfo var1) {
      Window var2 = Minecraft.getInstance().getWindow();
      RecoveredUtilsEAA.a(var2.getWidth(), var2.getHeight());
      RecoveredUtilsDAA.a.a();
      RecoveredUtilsDAA.b.a();
   }

   @Inject(
      method = {"createTitle"},
      at = {@At("HEAD")},
      cancellable = true
   )
   private void hookCreateTitle(CallbackInfoReturnable<String> var1) {
      var1.setReturnValue("EixClient | Eixfun | " + RecoveredB.a());
   }
}
