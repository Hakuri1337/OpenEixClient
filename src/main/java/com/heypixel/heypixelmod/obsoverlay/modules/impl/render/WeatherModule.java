package com.heypixel.heypixelmod.obsoverlay.modules.impl.render;

import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAE;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.core.particles.ParticleTypes;

@ModuleInfo(
   a = "Weather",
   b = "天气",
   c = "Customize world weather",
   d = ModuleCategory.RENDER
)
public class WeatherModule extends ClientModule {
   private final RecoveredDAE c = RecoveredDD.a(this, "Mode").a("Clear", "Rain", "Snow").a(0).a().e();
   private float d;
   private float e;
   private boolean f;

   @Override
   public void d() {
      if (a.level == null) {
         this.f = false;
      } else {
         this.d = a.level.getRainLevel(1.0F);
         this.e = a.level.getThunderLevel(1.0F);
         this.f = true;
      }
   }

   @Override
   public void e() {
      if (a.level != null && this.f) {
         a.level.setRainLevel(this.d);
         a.level.setThunderLevel(this.e);
      }
   }

   @EventTarget
   public void onMotion(RecoveredEventsImplK var1) {
      if (var1.b() == RecoveredEventsApiAA.PRE && a.level != null) {
         this.a(this.c.l());
         String var2 = this.c.l();
         switch (var2) {
            case "Clear":
               this.p();
               break;
            case "Rain":
               this.q();
               break;
            case "Snow":
               this.r();
         }
      }
   }

   private void p() {
      a.level.setRainLevel(0.0F);
      a.level.setThunderLevel(0.0F);
   }

   private void q() {
      a.level.setRainLevel(1.0F);
      a.level.setThunderLevel(0.0F);
   }

   private void r() {
      a.level.setRainLevel(1.0F);
      a.level.setThunderLevel(0.0F);
      if (a.player != null) {
         ThreadLocalRandom var1 = ThreadLocalRandom.current();
         double var2 = a.player.getX();
         double var4 = a.player.getY() + 2.0;
         double var6 = a.player.getZ();

         for (int var8 = 0; var8 < 20; var8++) {
            double var9 = var2 + var1.nextDouble(-8.0, 8.0);
            double var11 = var4 + var1.nextDouble(0.0, 6.0);
            double var13 = var6 + var1.nextDouble(-8.0, 8.0);
            a.level.addParticle(ParticleTypes.SNOWFLAKE, var9, var11, var13, 0.0, -0.01, 0.0);
         }
      }
   }
}
