package com.heypixel.heypixelmod.obsoverlay.modules.impl.c;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplM;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAl;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.ClipContext.Block;
import net.minecraft.world.level.ClipContext.Fluid;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.HitResult.Type;

@ModuleInfo(
   a = "AutoStuck",
   b = "自动卡空",
   c = "Automatically enable stuck when you over void",
   d = ModuleCategory.MOVEMENT
)
public class AutoStuckModule extends ClientModule {
   private final RecoveredDAC c = RecoveredDD.a(this, "Fall Distance").a(10.0F).d(0.1F).b(3.0F).c(15.0F).a().c();
   private final RecoveredUtilsAl d = new RecoveredUtilsAl();

   @EventTarget
   public void onMotion(RecoveredEventsImplK var1) {
      if (var1.b() == RecoveredEventsApiAA.PRE && a.player != null && a.level != null) {
         StuckModule var2 = EixClient.a().g().a(StuckModule.class);
         if (var2 != null) {
            if (var2.m()) {
               this.d.a();
            }

            int var3 = -1;

            for (int var4 = 0; var4 < 9; var4++) {
               if (!a.player.getInventory().getItem(var4).isEmpty() && a.player.getInventory().getItem(var4).getItem() == Items.ENDER_PEARL) {
                  var3 = var4;
                  break;
               }
            }

            boolean var5 = (var3 != -1 && a.player.fallDistance > this.c.q() || a.player.getY() + a.player.getDeltaMovement().y < -50.0)
               && this.p()
               && !a.player.onGround()
               && this.d.a(1000.0);
            if (var5 && !var2.m()) {
               var2.f();
            }
         }
      }
   }

   @EventTarget
   public void onPacket(RecoveredEventsImplM var1) {
      if (var1.c() instanceof ClientboundPlayerPositionPacket) {
         this.d.a();
      }
   }

   private boolean p() {
      Vec3 var1 = a.player.position();
      Vec3 var2 = new Vec3(var1.x, (double)a.level.getMinBuildHeight() - 2.0, var1.z);
      BlockHitResult var3 = a.level.clip(new ClipContext(var1, var2, Block.COLLIDER, Fluid.NONE, a.player));
      return var3.getType() == Type.MISS;
   }
}
