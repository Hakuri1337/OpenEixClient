package com.heypixel.heypixelmod.mixin.O;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.api.events.Event;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplR;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplS;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplX;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.NoRenderModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.PostProcessModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.ScoreboardModule;
import com.heypixel.heypixelmod.obsoverlay.utils.d.a.RecoveredUtilsDAA;
import com.heypixel.heypixelmod.obsoverlay.utils.e.RecoveredUtilsEA;
import com.heypixel.heypixelmod.obsoverlay.utils.e.a.RecoveredUtilsEAA;
import com.heypixel.heypixelmod.obsoverlay.utils.e.b.RecoveredUtilsEBC;
import io.github.humbleui.skija.FontMetrics;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.scores.Objective;
import net.minecraft.world.scores.PlayerTeam;
import net.minecraft.world.scores.Team;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(
   value = {Gui.class},
   priority = 100
)
public class MixinGui {
   @Shadow
   @Nullable
   protected Component title;
   @Shadow
   protected int titleTime;
   @Shadow
   protected int titleFadeInTime;
   @Shadow
   protected int titleStayTime;
   @Shadow
   protected int titleFadeOutTime;
   @Shadow
   @Nullable
   protected Component subtitle;

   @Inject(
      method = {"displayScoreboardSidebar"},
      at = {@At("HEAD")}
   )
   public void hookScoreboardHead(GuiGraphics var1, Objective var2, CallbackInfo var3) {
      var1.pose().pushPose();
      ScoreboardModule var4 = EixClient.a().g().a(ScoreboardModule.class);
      var4.p();
      if (var4.m()) {
         var1.pose().translate(var4.e.q(), var4.f.q(), 0.0F);
      }
   }

   @Inject(
      method = {"displayScoreboardSidebar"},
      at = {@At("RETURN")}
   )
   public void hookScoreboardReturn(GuiGraphics var1, Objective var2, CallbackInfo var3) {
      var1.pose().popPose();
   }

   @Redirect(
      method = {"displayScoreboardSidebar"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Ljava/lang/String;IIIZ)I"
      )
   )
   public int hookRenderScore(GuiGraphics var1, Font var2, String var3, int var4, int var5, int var6, boolean var7) {
      ScoreboardModule var8 = EixClient.a().g().a(ScoreboardModule.class);
      boolean var9 = var8.m() && var8.c.m();
      boolean var10 = var8.m() && var8.d.m();
      boolean var12 = var9 && isRedColor(var6);
      if (var12) {
         return 0;
      } else {
         float var13 = 9.0F;
         if (var10) {
            io.github.humbleui.skija.Font var18 = RecoveredUtilsEBC.a(var13);
            float var19 = RecoveredUtilsEA.a(var3, var18);
            FontMetrics var16 = var18.getMetrics();
            float var17 = var16.getDescent() - var16.getAscent();
            var8.a(var3, var4, var5, var6, var13);
            var8.a(var19, var17, var4, var5);
            return 0;
         } else {
            float var14 = (float)var2.width(var3);
            float var15 = 9.0F;
            var8.a(var14, var15, var4, var5);
            return var1.drawString(var2, var3, var4, var5, var6);
         }
      }
   }

   @Redirect(
      method = {"displayScoreboardSidebar"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)I"
      ),
      require = 0
   )
   public int hookRenderScoreComponent(GuiGraphics var1, Font var2, Component var3, int var4, int var5, int var6, boolean var7) {
      ScoreboardModule var8 = EixClient.a().g().a(ScoreboardModule.class);
      boolean var9 = var8.m() && var8.c.m();
      boolean var10 = var8.m() && var8.d.m();
      String var11 = var3.getString();
      int var12 = resolveColorFromStyle(var3.getStyle(), var6);
      boolean var13 = var9 && isRedColor(var12);
      if (var13) {
         return 0;
      } else {
         float var14 = 9.0F;
         if (var10) {
            io.github.humbleui.skija.Font var19 = RecoveredUtilsEBC.a(var14);
            float var20 = RecoveredUtilsEA.a(var11, var19);
            FontMetrics var17 = var19.getMetrics();
            float var18 = var17.getDescent() - var17.getAscent();
            var8.a(var11, var4, var5, var12, var14);
            var8.a(var20, var18, var4, var5);
            return 0;
         } else {
            float var15 = (float)var2.width(var3);
            float var16 = 9.0F;
            var8.a(var15, var16, var4, var5);
            return var1.drawString(var2, var3, var4, var5, var12, var7);
         }
      }
   }

   @Redirect(
      method = {"displayScoreboardSidebar"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/GuiGraphics;drawString(Lnet/minecraft/client/gui/Font;Lnet/minecraft/util/FormattedCharSequence;IIIZ)I"
      ),
      require = 0
   )
   public int hookRenderScoreFormatted(GuiGraphics var1, Font var2, FormattedCharSequence var3, int var4, int var5, int var6, boolean var7) {
      ScoreboardModule var8 = EixClient.a().g().a(ScoreboardModule.class);
      boolean var9 = var8.m() && var8.c.m();
      boolean var10 = var8.m() && var8.d.m();
      String var11 = flattenFormatted(var3);
      int var12 = resolveColorFromSequence(var3, var6);
      boolean var13 = var9 && isRedColor(var12);
      if (var13) {
         return 0;
      } else {
         float var14 = 9.0F;
         if (var10) {
            io.github.humbleui.skija.Font var19 = RecoveredUtilsEBC.a(var14);
            float var20 = RecoveredUtilsEA.a(var11, var19);
            FontMetrics var17 = var19.getMetrics();
            float var18 = var17.getDescent() - var17.getAscent();
            var8.a(var11, var4, var5, var12, var14);
            var8.a(var20, var18, var4, var5);
            return 0;
         } else {
            float var15 = (float)var2.width(var3);
            float var16 = 9.0F;
            var8.a(var15, var16, var4, var5);
            return var1.drawString(var2, var3, var4, var5, var12, var7);
         }
      }
   }

   private static String flattenFormatted(FormattedCharSequence var0) {
      StringBuilder var1 = new StringBuilder();
      var0.accept((var1x, var2, var3) -> {
         var1.appendCodePoint(var3);
         return true;
      });
      return var1.toString();
   }

   private static int resolveColorFromSequence(FormattedCharSequence var0, int var1) {
      int[] var2 = new int[]{var1};
      var0.accept((var2x, var3, var4) -> {
         if (var3 != null && var3.getColor() != null) {
            var2[0] = resolveColorFromStyle(var3, var1);
            return false;
         } else {
            return true;
         }
      });
      return var2[0];
   }

   private static int resolveColorFromStyle(Style var0, int var1) {
      if (var0 == null) {
         return var1;
      } else {
         TextColor var2 = var0.getColor();
         if (var2 == null) {
            return var1;
         } else {
            int var3 = var1 >> 24 & 0xFF;
            return var3 << 24 | var2.getValue() & 16777215;
         }
      }
   }

   private static boolean isRedColor(int var0) {
      int var1 = var0 & 16777215;
      Integer var2 = ChatFormatting.RED.getColor();
      if (var2 != null && var1 == (var2 & 16777215)) {
         return true;
      } else {
         Integer var3 = ChatFormatting.DARK_RED.getColor();
         if (var3 != null && var1 == (var3 & 16777215)) {
            return true;
         } else {
            int var4 = var1 >> 16 & 0xFF;
            int var5 = var1 >> 8 & 0xFF;
            int var6 = var1 & 0xFF;
            return var4 > 160 && var5 < 80 && var6 < 80;
         }
      }
   }

   @Redirect(
      method = {"displayScoreboardSidebar"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/gui/GuiGraphics;fill(IIIII)V"
      ),
      require = 0
   )
   public void hookScoreboardBackground(GuiGraphics var1, int var2, int var3, int var4, int var5, int var6) {
      ScoreboardModule var7 = EixClient.a().g().a(ScoreboardModule.class);
      var7.a(var2, var3, var4, var5);
      if (!var7.m()) {
         var1.fill(var2, var3, var4, var5, var6);
      }
   }

   @Redirect(
      method = {"displayScoreboardSidebar"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/scores/PlayerTeam;formatNameForTeam(Lnet/minecraft/world/scores/Team;Lnet/minecraft/network/chat/Component;)Lnet/minecraft/network/chat/MutableComponent;"
      )
   )
   public MutableComponent hookScoreboardName(Team var1, Component var2) {
      MutableComponent var3 = PlayerTeam.formatNameForTeam(var1, var2);
      RecoveredEventsImplR var4 = new RecoveredEventsImplR(var3);
      EixClient.a().b().a((Event)var4);
      return (MutableComponent)var4.a();
   }

   @Redirect(
      method = {"displayScoreboardSidebar"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/world/scores/Objective;getDisplayName()Lnet/minecraft/network/chat/Component;"
      )
   )
   public Component hookScoreboardTitle(Objective var1) {
      Component var2 = var1.getDisplayName();
      RecoveredEventsImplR var3 = new RecoveredEventsImplR(var2);
      EixClient.a().b().a((Event)var3);
      return var3.a();
   }

   @Inject(
      method = {"setTitle"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void hookTitle(Component var1, CallbackInfo var2) {
      RecoveredEventsImplX var3 = new RecoveredEventsImplX(RecoveredEventsApiAA.TITLE, var1);
      EixClient.a().b().a((Event)var3);
      if (!var3.a()) {
         this.title = var3.c();
         this.titleTime = this.titleFadeInTime + this.titleStayTime + this.titleFadeOutTime;
         var2.cancel();
      }
   }

   @Inject(
      method = {"setSubtitle"},
      at = {@At("RETURN")},
      cancellable = true
   )
   public void hookSubtitle(Component var1, CallbackInfo var2) {
      RecoveredEventsImplX var3 = new RecoveredEventsImplX(RecoveredEventsApiAA.SUBTITLE, var1);
      EixClient.a().b().a((Event)var3);
      if (!var3.a()) {
         this.subtitle = var3.c();
         var2.cancel();
      }
   }

   @Inject(
      method = {"renderEffects"},
      at = {@At("HEAD")},
      cancellable = true
   )
   public void hookRenderEffects(GuiGraphics var1, CallbackInfo var2) {
      NoRenderModule var3 = EixClient.a().g().a(NoRenderModule.class);
      if (var3.m() && var3.c.m()) {
         var2.cancel();
      }
   }

   @Inject(
      method = {"renderCrosshair"},
      at = {@At("RETURN")}
   )
   public void renderCrosshair(GuiGraphics var1, CallbackInfo var2) {
      try {
         RecoveredUtilsDAA.b.a(EixClient.a().g().a(PostProcessModule.class).p());
         RecoveredUtilsEAA.a(var0 -> {
            RecoveredUtilsEA.c();
            RecoveredUtilsEA.a((float)Minecraft.getInstance().getWindow().getGuiScale());
            EixClient.a().b().a((Event)(new RecoveredEventsImplS()));
            RecoveredUtilsEA.d();
         });
      } catch (Throwable var4) {
         var4.printStackTrace();
      }
   }
}
