package com.heypixel.heypixelmod.obsoverlay.modules.impl.b;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.c.a.RecoveredCAA;
import com.heypixel.heypixelmod.obsoverlay.c.a.RecoveredCAB;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.EventMouseClick;
import com.heypixel.heypixelmod.obsoverlay.events.impl.EventRunTicks;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsM;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.EntityHitResult;

@ModuleInfo(
   a = "Middle Click",
   b = "中键点击",
   c = "",
   d = ModuleCategory.MISC
)
public class MiddleClickModule extends ClientModule {
   private boolean c;
   private boolean d;
   private int e;

   @EventTarget
   public void onPreTick(EventRunTicks var1) {
      if (var1.type() == RecoveredEventsApiAA.PRE) {
         label40:
         if (this.c) {
            this.c = false;
            if (!(a.hitResult instanceof EntityHitResult var2) || !(var2.getEntity() instanceof Player var3)) {
               this.e = a.player.getInventory().selected;
               int var8 = -1;

               for (int var5 = 0; var5 < 9; var5++) {
                  ItemStack var6 = a.player.getInventory().getItem(var5);
                  if (var6.getItem() == Items.ENDER_PEARL) {
                     var8 = var5;
                     break;
                  }
               }

               if (var8 != -1) {
                  a.player.getInventory().selected = var8;
                  a.gameMode.useItem(a.player, InteractionHand.MAIN_HAND);
                  a.player.swing(InteractionHand.MAIN_HAND);
                  this.d = true;
               } else {
                  RecoveredCAA var11 = new RecoveredCAA(RecoveredCAB.d, "No pearls found.", 3000L);
                  EixClient.a().j().a(var11);
               }
               break label40;
            }

            if (RecoveredUtilsM.a((Entity)var3)) {
               RecoveredCAA var9 = new RecoveredCAA(RecoveredCAB.d, "Removed " + var3.getName().getString() + " from friends!", 3000L);
               EixClient.a().j().a(var9);
               RecoveredUtilsM.b(var3);
            } else {
               RecoveredCAA var10 = new RecoveredCAA(RecoveredCAB.a, "Added " + var3.getName().getString() + " as friends!", 3000L);
               EixClient.a().j().a(var10);
               RecoveredUtilsM.a(var3);
            }
         }

         if (this.d) {
            this.d = false;
            a.player.getInventory().selected = this.e;
         }
      }
   }

   @EventTarget
   public void onMouseKey(EventMouseClick var1) {
      if (var1.key() == 2 && !var1.state()) {
         this.c = true;
      }
   }
}
