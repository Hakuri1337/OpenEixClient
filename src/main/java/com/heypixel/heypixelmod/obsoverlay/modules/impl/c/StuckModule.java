package com.heypixel.heypixelmod.obsoverlay.modules.impl.c;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplL;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplM;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplU;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsW;
import com.heypixel.heypixelmod.obsoverlay.utils.c.RecoveredUtilsCC;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.network.protocol.game.ServerboundPongPacket;
import net.minecraft.network.protocol.game.ServerboundUseItemPacket;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket.Pos;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket.Rot;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket.Action;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.BowlFoodItem;
import net.minecraft.world.item.ItemStack;

@ModuleInfo(
   a = "Stuck",
   b = "卡空",
   c = "Stuck in air!",
   d = ModuleCategory.MOVEMENT
)
public class StuckModule extends ClientModule {
   int c = 0;
   Packet<?> d;
   float e;
   float f;
   boolean g = false;
   Queue<ServerboundPongPacket> h = new ConcurrentLinkedQueue<>();

   @Override
   public void d() {
      this.c = 0;
      this.d = null;
      this.e = RecoveredUtilsCC.a.a();
      this.f = RecoveredUtilsCC.a.b();
      this.g = false;
   }

   @Override
   public void a(boolean var1) {
      if (a.player != null) {
         if (var1) {
            super.a(true);
         } else if (this.c == 3) {
            super.a(false);
         } else {
            this.g = true;
         }
      }
   }

   @EventTarget
   public void onMotion(RecoveredEventsImplK var1) {
      ClientModule var2 = EixClient.a().g().a(ScaffoldModule.class);
      if (var2.m()) {
         var2.f();
      } else if (var1.b() == RecoveredEventsApiAA.PRE) {
         a.player.setDeltaMovement(0.0, 0.0, 0.0);
         if (this.c == 1) {
            this.c = 2;
            float var3 = a.player.getYRot();
            float var4 = a.player.getXRot();
            if (this.p() && (this.e != var3 || this.f != var4)) {
               RecoveredUtilsW.a(new Rot(var3, var4, a.player.onGround()));

               while (!this.h.isEmpty()) {
                  RecoveredUtilsW.a(this.h.poll());
               }

               this.e = var3;
               this.f = var4;
            }

            RecoveredUtilsW.a(this.d);
         }

         if (this.g) {
            RecoveredUtilsW.a(new Pos(a.player.getX() + 1337.0, a.player.getY(), a.player.getZ() + 1337.0, a.player.onGround()));

            while (!this.h.isEmpty()) {
               RecoveredUtilsW.a(this.h.poll());
            }

            this.g = false;
         }
      }
   }

   private boolean p() {
      if (this.d instanceof ServerboundUseItemPacket var1) {
         ItemStack var5 = a.player.getItemInHand(var1.getHand());
         return !(var5.getItem() instanceof BowlFoodItem) && !(var5.getItem() instanceof BowItem);
      } else {
         if (this.d instanceof ServerboundPlayerActionPacket var4
            && var4.getAction() == Action.RELEASE_USE_ITEM
            && a.player.getUseItem().getItem() instanceof BowItem) {
            return true;
         }

         return false;
      }
   }

   @EventTarget
   public void onMoveInput(RecoveredEventsImplL var1) {
      var1.a(0.0F);
      var1.b(0.0F);
      var1.a(false);
      var1.b(false);
   }

   @EventTarget
   public void onRespawn(RecoveredEventsImplU var1) {
      this.c = 3;
      this.d = null;
      this.f();
   }

   @EventTarget(
      a = 1
   )
   public void onPacket(RecoveredEventsImplM var1) {
      if (var1.c() instanceof ServerboundMovePlayerPacket) {
         if (this.c == 1) {
            var1.a(true);
         }
      } else if (var1.c() instanceof ServerboundPongPacket) {
         this.h.offer((ServerboundPongPacket)var1.c());
         var1.a(true);
      } else if (var1.c() instanceof ServerboundUseItemPacket || var1.c() instanceof ServerboundPlayerActionPacket) {
         this.d = var1.c();
         this.c = 1;
         var1.a(true);
      } else if (var1.c() instanceof ClientboundPlayerPositionPacket) {
         while (!this.h.isEmpty()) {
            RecoveredUtilsW.a(this.h.poll());
         }

         this.c = 3;
         this.f();
      }
   }
}
