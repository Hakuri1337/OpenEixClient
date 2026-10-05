package com.heypixel.heypixelmod.obsoverlay.utils.renderer;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.api.events.Event;
import com.heypixel.heypixelmod.obsoverlay.events.impl.EventRender2D;
import com.heypixel.heypixelmod.obsoverlay.events.impl.EventShader;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAl;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;

public class RecoveredUtilsRendererL {
   private static final RecoveredUtilsAl a = new RecoveredUtilsAl();
   private static RecoveredUtilsRendererE b;
   private static RecoveredUtilsRendererE c;
   private static RecoveredUtilsRendererE d;
   private static RecoveredUtilsRendererI e;

   public static void a(EventRender2D var0, float var1) {
      Window var2 = Minecraft.getInstance().getWindow();
      if (e == null) {
         e = new RecoveredUtilsRendererI("shadow.vert", "shadow.frag");
         b = new RecoveredUtilsRendererE();
         c = new RecoveredUtilsRendererE();
         d = new RecoveredUtilsRendererE();
      }

      boolean var3 = false;
      if (a.a((double)(1000.0F / var1))) {
         var3 = true;
         a.a();
      }

      if (var3) {
         d.d();
         b.d();
         c.d();
         d.a();
         RenderSystem.setShader(GameRenderer::getPositionColorShader);
         EixClient.a().b().a((Event)(new EventShader(var0.stack(), RecoveredEventsApiAA.SHADOW)));
         d.c();
      }

      RecoveredUtilsRendererF.k();
      e.a();
      e.a("u_Size", (double)var2.getWidth(), (double)var2.getHeight());
      RecoveredUtilsRendererH.a(var0.stack());
      if (var3) {
         b.a();
         RecoveredUtilsRendererF.q(d.a);
         e.a("u_Direction", 1.0, 0.0);
         RecoveredUtilsRendererH.b(var0.stack());
         c.a();
         RecoveredUtilsRendererF.q(b.a);
         e.a("u_Direction", 0.0, 1.0);
         RecoveredUtilsRendererH.b(var0.stack());
         b.a();
         RecoveredUtilsRendererF.q(c.a);
         e.a("u_Direction", 1.0, 0.0);
         RecoveredUtilsRendererH.b(var0.stack());
         c.c();
      }

      RecoveredUtilsRendererF.q(b.a);
      e.a("u_Direction", 0.0, 1.0);
      RecoveredUtilsRendererH.b(var0.stack());
      RecoveredUtilsRendererF.l();
      RecoveredUtilsRendererH.b();
   }
}
