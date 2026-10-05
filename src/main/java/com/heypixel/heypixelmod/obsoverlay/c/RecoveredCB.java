package com.heypixel.heypixelmod.obsoverlay.c;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDC;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDF;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAB;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleManager;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.PostProcessModule;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAd;
import com.heypixel.heypixelmod.obsoverlay.utils.d.a.RecoveredUtilsDAA;
import com.heypixel.heypixelmod.obsoverlay.utils.e.RecoveredUtilsEA;
import com.heypixel.heypixelmod.obsoverlay.utils.e.a.RecoveredUtilsEAA;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class RecoveredCB extends Screen {
   private final List<RecoveredDAB> a = new ArrayList<>();
   private RecoveredDAB b = null;
   private float c = 0.0F;
   private float d = 0.0F;

   public RecoveredCB() {
      super(Component.literal("HUD Editor"));
   }

   protected void a() {
      this.a.clear();
      ModuleManager var1 = EixClient.a().g();

      for (ClientModule var3 : var1.a()) {
         for (RecoveredDC var5 : EixClient.a().d().a()) {
            if (var5.i() == var3 && var5.a() == RecoveredDF.DRAG) {
               this.a.add(var5.f());
            }
         }
      }
   }

   public void a(GuiGraphics var1, int var2, int var3, float var4) {
      RecoveredUtilsDAA.a.a(EixClient.a().g().a(PostProcessModule.class).p());
      RecoveredUtilsEAA.a(var3x -> {
         RecoveredUtilsEA.c();
         RecoveredUtilsEA.a((float)Minecraft.getInstance().getWindow().getGuiScale());

         for (RecoveredDAB var5 : this.a) {
            float var6 = var5.n();
            float var7 = var5.o();
            float var8 = var5.p();
            float var9 = var5.q();
            float var10 = var8 <= 0.0F ? 80.0F : var8;
            float var11 = var9 <= 0.0F ? 20.0F : var9;
            if (this.b == var5) {
               var5.a((float)var2 - this.c);
               var5.b((float)var3 - this.d);
               var6 = var5.n();
               var7 = var5.o();
            }

            boolean var12 = RecoveredUtilsAd.b(var2, var3, var6, var7, var10, var11);
            if (var12) {
               RecoveredUtilsEA.a(var6 - 5.0F, var7 - 5.0F, var10 + 10.0F, var11 + 10.0F, 5.0F, 2.0F, new Color(255, 255, 255));
            }
         }

         RecoveredUtilsEA.d();
      });
      super.render(var1, var2, var3, var4);
   }

   public boolean a(double var1, double var3, int var5) {
      if (var5 == 0) {
         for (int var6 = this.a.size() - 1; var6 >= 0; var6--) {
            RecoveredDAB var7 = this.a.get(var6);
            float var8 = var7.p();
            float var9 = var7.q();
            float var10 = var8 <= 0.0F ? 80.0F : var8;
            float var11 = var9 <= 0.0F ? 20.0F : var9;
            if (RecoveredUtilsAd.b((int)var1, (int)var3, var7.n(), var7.o(), var10, var11)) {
               this.b = var7;
               this.c = (float)var1 - var7.n();
               this.d = (float)var3 - var7.o();
               return true;
            }
         }
      }

      return super.mouseClicked(var1, var3, var5);
   }

   public boolean b(double var1, double var3, int var5) {
      if (var5 == 0 && this.b != null) {
         this.b = null;
         return true;
      } else {
         return super.mouseReleased(var1, var3, var5);
      }
   }

   public boolean a(double var1, double var3, int var5, double var6, double var8) {
      if (var5 == 0 && this.b != null) {
         this.b.a((float)var1 - this.c);
         this.b.b((float)var3 - this.d);
         return true;
      } else {
         return super.mouseDragged(var1, var3, var5, var6, var8);
      }
   }

   public void b() {
      EixClient.a().i().c();
      super.onClose();
   }

   public boolean c() {
      return false;
   }
}
