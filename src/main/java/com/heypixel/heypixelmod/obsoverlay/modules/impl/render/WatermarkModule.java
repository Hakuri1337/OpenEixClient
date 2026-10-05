package com.heypixel.heypixelmod.obsoverlay.modules.impl.render;

import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAB;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplS;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.utils.e.RecoveredUtilsEA;
import com.heypixel.heypixelmod.obsoverlay.utils.e.b.RecoveredUtilsEBC;
import com.heypixel.heypixelmod.obsoverlay.utils.renderer.RecoveredUtilsRendererB;
import io.github.humbleui.skija.FilterTileMode;
import io.github.humbleui.skija.Font;
import io.github.humbleui.skija.ImageFilter;
import io.github.humbleui.skija.Paint;
import io.github.humbleui.skija.Shader;
import io.github.humbleui.types.Point;
import io.github.humbleui.types.Rect;
import java.awt.Color;

@ModuleInfo(
   a = "Watermark",
   b = "客户端水印",
   c = "",
   d = ModuleCategory.RENDER
)
public class WatermarkModule extends ClientModule {
   public RecoveredDAA c = RecoveredDD.a(this, "Solid White").a(true).a().b();
   public RecoveredDAC d = RecoveredDD.a(this, "Speed").b(2.0F).c(30.0F).a(10.0F).d(1.0F).a(() -> !this.c.m()).a().c();
   public RecoveredDAC e = RecoveredDD.a(this, "Hue Shift").b(0.0F).c(360.0F).a(0.0F).d(1.0F).a(() -> !this.c.m()).a().c();
   private final RecoveredDAB f = RecoveredDD.a(this, "Position").e(10.0F).f(10.0F).a().f();

   @EventTarget
   public void onRenderSkia(RecoveredEventsImplS var1) {
      Font var2 = RecoveredUtilsEBC.d(22.0F);
      float var3 = this.f.n();
      float var4 = this.f.o();
      String var5 = "EixClient";
      float var6 = var2.measureTextWidth(var5);
      if (this.c.m()) {
         RecoveredUtilsEA.a(var5, var3, var4, new Color(255, 255, 255, 255), var2);
      } else {
         float var7 = this.d.q();
         float var8 = this.e.q();
         Color var9 = Color.getHSBColor(var8 % 360.0F / 360.0F, 0.85F, 1.0F);
         Color var10 = Color.getHSBColor((var8 + 120.0F) % 360.0F / 360.0F, 0.85F, 1.0F);
         Color var11 = RecoveredUtilsRendererB.a((int)var7, (int)(-var3), var9, var10, false);
         Color var12 = RecoveredUtilsRendererB.a((int)var7, (int)(-(var3 + var6)), var9, var10, false);

         try (Shader var13 = Shader.makeLinearGradient(
               new Point(var3, var4),
               new Point(var3 + var6, var4),
               new int[]{
                  io.github.humbleui.skija.Color.makeARGB(var11.getAlpha(), var11.getRed(), var11.getGreen(), var11.getBlue()),
                  io.github.humbleui.skija.Color.makeARGB(var12.getAlpha(), var12.getRed(), var12.getGreen(), var12.getBlue())
               },
               new float[]{0.0F, 1.0F}
            )) {
            this.a(var5, var3, var4, var2, var13);
         }
      }

      this.f.d(22.0F);
      this.f.c(var6);
   }

   private void a(String var1, float var2, float var3, Font var4, Shader var5) {
      try (
         Paint var6 = new Paint().setShader(var5);
         Paint var7 = var6.makeClone().setImageFilter(ImageFilter.makeBlur(2.5F, 2.5F, FilterTileMode.DECAL));
      ) {
         Rect var8 = var4.measureText(var1);
         float var9 = var2 - var8.getLeft();
         float var10 = var3 - var8.getTop();
         RecoveredUtilsEA.e().drawString(var1, var9, var10, var4, var7);
         RecoveredUtilsEA.e().drawString(var1, var9, var10, var4, var6);
      }
   }
}
