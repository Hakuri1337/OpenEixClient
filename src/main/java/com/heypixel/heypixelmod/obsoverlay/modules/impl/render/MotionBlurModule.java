package com.heypixel.heypixelmod.obsoverlay.modules.impl.render;

import com.google.gson.JsonSyntaxException;
import com.heypixel.heypixelmod.mixin.O.accessors.PostChainAccessor;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.EventRunTicks;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.mojang.blaze3d.shaders.Uniform;
import java.io.IOException;
import net.minecraft.client.renderer.PostChain;
import net.minecraft.resources.ResourceLocation;

@ModuleInfo(
   a = "MotionBlur",
   b = "动态模糊",
   c = "Make your game smoother.",
   d = ModuleCategory.RENDER
)
public class MotionBlurModule extends ClientModule {
   public static MotionBlurModule c;
   private final RecoveredDAC e = RecoveredDD.a(this, "Strength").d(0.1F).a(7.0F).b(0.0F).c(10.0F).a().c();
   private final ResourceLocation f = new ResourceLocation("minecraft", "shaders/post/motion_blur.json");
   public PostChain d;
   private int g;
   private float h;
   private int i;

   public MotionBlurModule() {
      c = this;
   }

   @EventTarget
   public void onTick(EventRunTicks var1) {
      if (var1.type() != RecoveredEventsApiAA.POST && a.player != null && a.level != null && a.player.tickCount > 10) {
         if ((this.d == null || a.getWindow().getWidth() != this.g || a.getWindow().getHeight() != this.i)
            && a.getWindow().getWidth() > 0
            && a.getWindow().getHeight() > 0) {
            this.h = Float.NaN;

            try {
               this.d = new PostChain(a.getTextureManager(), a.getResourceManager(), a.getMainRenderTarget(), this.f);
               this.d.resize(a.getWindow().getWidth(), a.getWindow().getHeight());
            } catch (JsonSyntaxException | IOException var3) {
               var3.printStackTrace();
            }
         }

         float var2 = 1.0F - Math.min(this.e.q() / 10.0F, 0.9F);
         if (this.h != var2 && this.d != null) {
            ((PostChainAccessor)this.d).getPasses().forEach(var1x -> {
               Uniform var2x = var1x.getEffect().getUniform("BlurFactor");
               if (var2x != null) {
                  var2x.set(var2, 0.0F, 0.0F);
               }
            });
            this.h = var2;
         }

         this.g = a.getWindow().getWidth();
         this.i = a.getWindow().getHeight();
      }
   }
}
