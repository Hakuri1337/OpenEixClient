package com.heypixel.heypixelmod.obsoverlay.modules.impl.c;

import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplM;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplU;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsW;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket;
import net.minecraft.network.protocol.game.ServerboundPongPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket.Action;

@ModuleInfo(
   a = "GrimFly",
   b = "浪子闲话",
   c = "Attempts to desync brief keepalive timing after knockback",
   d = ModuleCategory.MOVEMENT
)
public class GrimFlyModule extends ClientModule {
   private final Queue<Packet<?>> c = new ConcurrentLinkedQueue<>();
   private boolean d;
   private boolean e;
   private int f;

   @Override
   public void d() {
      this.b(false);
      super.d();
   }

   @Override
   public void e() {
      this.q();
      this.b(false);
      super.e();
   }

   @EventTarget
   public void onRespawn(RecoveredEventsImplU var1) {
      if (this.m()) {
         this.a(false);
      }

      this.b(true);
   }

   @EventTarget
   public void onMotion(RecoveredEventsImplK var1) {
      if (var1.b() == RecoveredEventsApiAA.PRE) {
         if (this.e && a.player != null) {
            this.f++;
            if (this.f >= 8) {
               RecoveredUtilsW.a(new ServerboundPlayerCommandPacket(a.player, Action.START_FALL_FLYING));
               this.e = false;
               this.f = 0;
            }
         }
      }
   }

   @EventTarget
   public void onPacket(RecoveredEventsImplM var1) {
      if (a.player != null) {
         Packet var2 = var1.c();
         if (var1.b() == RecoveredEventsApiAA.SEND) {
            if (this.d && var2 instanceof ServerboundPongPacket) {
               var1.a(true);
               if (this.c.isEmpty()) {
                  this.e = true;
                  this.f = 0;
               }

               this.c.add(var2);
               if (this.c.size() > 200) {
                  this.p();
               }

               return;
            }

            if (var2 instanceof ServerboundInteractPacket) {
               if (this.d && !this.c.isEmpty()) {
                  this.p();
               }

               return;
            }
         }

         if (var1.b() == RecoveredEventsApiAA.RECEIVE) {
            if (var2 instanceof ClientboundPlayerPositionPacket) {
               if (this.d && !this.c.isEmpty()) {
                  this.p();
               }

               return;
            }

            if (var2 instanceof ClientboundSetEntityMotionPacket var3 && var3.getId() == a.player.getId()) {
               if (this.d || !this.c.isEmpty()) {
                  return;
               }

               this.d = true;
               this.e = false;
               this.f = 0;
               var1.a(true);
            }
         }
      }
   }

   private void p() {
      this.q();
      this.d = false;
      this.e = false;
      this.f = 0;
   }

   private void q() {
      while (!this.c.isEmpty()) {
         RecoveredUtilsW.a(this.c.poll());
      }
   }

   private void b(boolean var1) {
      this.d = false;
      this.e = false;
      this.f = 0;
      if (var1) {
         this.c.clear();
      }
   }
}
