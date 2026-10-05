package com.heypixel.heypixelmod.obsoverlay.modules.impl.c;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.EventRunTicks;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplL;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.a.KillAuraModule;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsF;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsS;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsU;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsW;
import com.heypixel.heypixelmod.obsoverlay.utils.c.RecoveredUtilsCB;
import com.heypixel.heypixelmod.obsoverlay.utils.c.RecoveredUtilsCC;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket.Action;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

@ModuleInfo(
   a = "GrimSpeed",
   b = "严峻的嘻嘻哈哈",
   c = "Movement speed adjustments with Grim modes",
   d = ModuleCategory.MOVEMENT
)
public class GrimSpeedModule extends ClientModule {
   private final RecoveredDAA c = RecoveredDD.a(this, "Logging").a(false).a().b();
   private final RecoveredDAA d = RecoveredDD.a(this, "Fast Fall").a(true).a().b();
   private final RecoveredDAA e = RecoveredDD.a(this, "Fast Fall On 1 Tick").a(false).a(this.d::m).a().b();
   private final RecoveredDAA f = RecoveredDD.a(this, "Fast Fall On 2 Tick").a(false).a(this.d::m).a().b();
   private final RecoveredDAA g = RecoveredDD.a(this, "Fast Fall On 3 Tick").a(false).a(this.d::m).a().b();
   private final RecoveredDAA h = RecoveredDD.a(this, "Fast Fall On 4 Tick").a(false).a(this.d::m).a().b();
   private final RecoveredDAA i = RecoveredDD.a(this, "Fast Fall On 5 Tick").a(false).a(this.d::m).a().b();
   private final RecoveredDAA j = RecoveredDD.a(this, "Rotation").a(true).a().b();
   private final RecoveredDAC k = RecoveredDD.a(this, "Speed").a(0.1F).b(0.0F).c(10.0F).d(0.01F).a().c();
   private final RecoveredDAC l = RecoveredDD.a(this, "Start Fast Fall").a(2.0F).b(0.0F).c(10.0F).d(1.0F).a(this.d::m).a().c();
   private final RecoveredDAC m = RecoveredDD.a(this, "Packet Count").a(1.0F).b(1.0F).c(10.0F).d(1.0F).a(this.d::m).a().c();
   private final RecoveredDAC n = RecoveredDD.a(this, "Skip Ticks").a(2.0F).b(1.0F).c(10.0F).d(1.0F).a(this.d::m).a().c();
   private final RecoveredDAC o = RecoveredDD.a(this, "Ticks").a(3.0F).b(1.0F).c(10.0F).d(1.0F).a(this.d::m).a().c();
   private int p;

   private void b(String var1) {
      if (this.c.m()) {
         RecoveredUtilsF.a(var1);
      }
   }

   @Override
   public void d() {
      this.p = 0;
   }

   @Override
   public void e() {
      this.p = 0;
   }

   private double p() {
      float var1 = a.player.input.forwardImpulse;
      float var2 = a.player.input.leftImpulse;
      float var3 = a.player.getYRot();
      if (var1 < 0.0F) {
         var3 += 180.0F;
      }

      float var4 = 1.0F;
      if (var1 < 0.0F) {
         var4 = -0.5F;
      } else if (var1 > 0.0F) {
         var4 = 0.5F;
      }

      if (var2 > 0.0F) {
         var3 -= 90.0F * var4;
      }

      if (var2 < 0.0F) {
         var3 += 90.0F * var4;
      }

      return Math.toRadians((double)var3);
   }

   private void a(double var1) {
      if (RecoveredUtilsU.a()) {
         double var3 = this.p();
         Vec3 var5 = a.player.getDeltaMovement();
         a.player.setDeltaMovement(var5.x + -Math.sin(var3) * var1, var5.y, var5.z + Math.cos(var3) * var1);
      }
   }

   @EventTarget
   public void onMotion(RecoveredEventsImplK var1) {
      if (a.player != null) {
         if (this.d.m()) {
            if (var1.b() == RecoveredEventsApiAA.PRE) {
               if (a.player.onGround()) {
                  this.p = 0;
               } else {
                  this.p++;
               }
            } else if (var1.b() == RecoveredEventsApiAA.POST && !a.player.onGround() && (float)this.p > this.l.q()) {
               this.b("Air ticks: " + this.p);
               switch (this.p - (int)this.l.q()) {
                  case 1:
                     if (this.e.m()) {
                        this.q();
                     }
                     break;
                  case 2:
                     if (this.f.m()) {
                        this.q();
                     }
                     break;
                  case 3:
                     if (this.g.m()) {
                        this.q();
                     }
                     break;
                  case 4:
                     if (this.h.m()) {
                        this.q();
                     }
                     break;
                  case 5:
                     if (this.i.m()) {
                     }
               }
            }
         }
      }
   }

   private void q() {
      for (int var1 = 0; var1 < (int)this.m.q(); var1++) {
         RecoveredUtilsW.a(new ServerboundPlayerCommandPacket(a.player, Action.START_FALL_FLYING));
      }

      a.options.keyShift.setDown(false);
      a.player.jumpFromGround();
      this.a((double)this.k.q() / 10.0);

      for (int var2 = 0; var2 < (int)this.o.q(); var2++) {
         a.player.tick();
      }

      EixClient.d = (int)((float)EixClient.d + this.n.q());
      this.b("fall");
   }

   @EventTarget
   public void onPreTick(EventRunTicks var1) {
      if (var1.type() == RecoveredEventsApiAA.PRE && this.j.m()) {
         ScaffoldModule var2 = EixClient.a().g().a(ScaffoldModule.class);
         KillAuraModule var3 = EixClient.a().g().a(KillAuraModule.class);
         float var4 = RecoveredUtilsS.a(-1.0F, 1.0F);
         if (!a.player.onGround() && !var2.m() && var3.c == null) {
            RecoveredUtilsCC.a(new RecoveredUtilsCB(Mth.wrapDegrees(a.player.getYRot() - 45.0F) + var4, a.player.getXRot() + var4), 360.0);
         }
      }
   }

   @EventTarget
   public void onMoveInput(RecoveredEventsImplL var1) {
      if (a.player != null && a.level != null) {
         var1.a(true);
      }
   }
}
