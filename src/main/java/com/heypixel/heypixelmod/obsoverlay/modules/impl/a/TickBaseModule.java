package com.heypixel.heypixelmod.obsoverlay.modules.impl.a;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.EventRunTicks;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplQ;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.b.ClientFriendModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.b.TargetModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.b.TeamsModule;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsF;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsM;
import com.heypixel.heypixelmod.obsoverlay.utils.c.RecoveredUtilsCD;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

@ModuleInfo(
   a = "TickBase",
   b = "Tick基",
   c = "Manipulates client ticks for combat timing",
   d = ModuleCategory.COMBAT
)
public class TickBaseModule extends ClientModule {
   private final RecoveredDAC c = RecoveredDD.a(this, "Range").a(8.0F).d(0.1F).b(3.0F).c(15.0F).a().c();
   private final RecoveredDAA d = RecoveredDD.a(this, "Debug").a(false).a().b();
   private TickBaseModule.InnerA e = TickBaseModule.InnerA.NONE;
   private long f;
   private long g;
   private double h;
   private double i;
   private boolean j;
   private Entity k;

   @Override
   public void d() {
      this.p();
   }

   @Override
   public void e() {
      this.p();
   }

   @EventTarget
   public void onPreTick(EventRunTicks var1) {
      if (var1.type() == RecoveredEventsApiAA.PRE && a.player != null && a.level != null) {
         if (this.e != TickBaseModule.InnerA.REDUCING) {
            this.k = this.a(20.0);
            if (this.k != null) {
               this.i = RecoveredUtilsCD.b(this.k);
               double var2 = this.i;
               double var4 = Math.max(2.0, (double)this.c.q() - 1.0);
               if (var2 > var4 && this.g >= 50L && this.e == TickBaseModule.InnerA.BASING) {
                  this.g -= 50L;
                  this.j = true;
               } else {
                  this.g = 0L;
                  this.e = TickBaseModule.InnerA.NONE;
                  this.j = false;
               }

               if (var2 <= (double)this.c.q() && this.h > (double)this.c.q() && this.e == TickBaseModule.InnerA.NONE) {
                  this.e = TickBaseModule.InnerA.REDUCING;
                  this.f = System.currentTimeMillis();
                  this.g = 0L;
                  this.j = false;
                  this.b(String.format("TickBase REDUCING range=%.2f prev=%.2f limit=%.2f", var2, this.h, this.c.q()));
               }

               this.h = var2;
            }
         }
      }
   }

   @EventTarget
   public void onRenderAfterWorld(RecoveredEventsImplQ var1) {
      if (a.player != null && a.level != null) {
         if (this.e != TickBaseModule.InnerA.REDUCING) {
            EixClient.c = this.j ? 2.0F : 1.0F;
            this.j = false;
         } else if (this.k != null && this.k.isAlive()) {
            this.i = RecoveredUtilsCD.b(this.k);
            double var2 = a.player.hasEffect(MobEffects.MOVEMENT_SPEED) ? 0.36 : 0.25;
            double var4 = Math.max(2.0, (double)this.c.q() - 1.0);
            if (!(this.i <= var4) && !((double)(System.currentTimeMillis() - this.f) >= this.h / var2 * 25.0 + 25.0)) {
               EixClient.c = 0.0F;
            } else {
               EixClient.c = 1.0F;
               this.e = TickBaseModule.InnerA.BASING;
               this.g = System.currentTimeMillis() - this.f;
               this.b(String.format("TickBase BASING balance=%d", this.g));
            }
         } else {
            this.e = TickBaseModule.InnerA.NONE;
            this.g = 0L;
            EixClient.c = 1.0F;
            this.j = false;
            this.b("TickBase target lost");
         }
      }
   }

   private void p() {
      this.e = TickBaseModule.InnerA.NONE;
      this.f = 0L;
      this.g = 0L;
      this.h = Double.MAX_VALUE;
      this.i = 0.0;
      this.j = false;
      this.k = null;
      EixClient.c = 1.0F;
   }

   private void b(String var1) {
      if (this.d.m()) {
         RecoveredUtilsF.a(var1);
      }
   }

   private Entity a(double var1) {
      if (a.player != null && a.level != null) {
         double var3 = var1 * var1;
         Entity var5 = null;
         double var6 = Double.MAX_VALUE;
         TargetModule var8 = EixClient.a().g().a(TargetModule.class);

         for (Entity var10 : a.level.entitiesForRendering()) {
            if (var10 instanceof LivingEntity
               && var10 != a.player
               && var10.isAlive()
               && !var10.isSpectator()
               && !AntiBotsModule.b(var10)
               && (var8 == null || var8.a(var10))
               && !TeamsModule.a(var10)
               && !RecoveredUtilsM.a(var10)
               && !ClientFriendModule.a(var10)) {
               double var11 = a.player.distanceToSqr(var10);
               if (var11 <= var3 && var11 < var6) {
                  var6 = var11;
                  var5 = var10;
               }
            }
         }

         return var5;
      } else {
         return null;
      }
   }

   private static enum InnerA {
      REDUCING,
      BASING,
      NONE;
   }
}
