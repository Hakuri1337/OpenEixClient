package com.heypixel.heypixelmod.obsoverlay.modules.impl.a;

import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAE;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAl;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsP;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsX;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket;
import net.minecraft.network.protocol.game.ServerboundUseItemPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket.Action;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

@ModuleInfo(
   a = "AutoHeal",
   b = "自动回血",
   c = "Automatically heals you when you're low on health.",
   d = ModuleCategory.COMBAT
)
public class AutoHealModule extends ClientModule {
   private final RecoveredUtilsAl c = new RecoveredUtilsAl();
   private final RecoveredDAA d = RecoveredDD.a(this, "Speed Check").a(true).a().b();
   private final RecoveredDAA e = RecoveredDD.a(this, "Regen Check").a(true).a().b();
   private final RecoveredDAC f = RecoveredDD.a(this, "Delay").a(500.0F).d(1.0F).b(300.0F).c(1000.0F).a().c();
   private final RecoveredDAC g = RecoveredDD.a(this, "Health Percent").a(0.5F).d(0.05F).b(0.0F).c(1.0F).a().c();
   private final RecoveredDAE h = RecoveredDD.a(this, "Mode").a("Soup", "Head").a(0).a().e();
   private boolean i = false;
   private boolean j = false;
   private boolean k = false;

   @EventTarget
   public void onMotion(RecoveredEventsImplK var1) {
      if (var1.b() == RecoveredEventsApiAA.PRE) {
         if (this.j) {
            RecoveredUtilsX.a(var0 -> new ServerboundUseItemPacket(InteractionHand.MAIN_HAND, var0));
            this.j = false;
            return;
         }

         if (this.k) {
            a.getConnection().send(new ServerboundPlayerActionPacket(Action.DROP_ITEM, BlockPos.ZERO, Direction.DOWN));
            this.k = false;
            return;
         }

         if (this.i) {
            a.getConnection().send(new ServerboundSetCarriedItemPacket(a.player.getInventory().selected));
            this.i = false;
            return;
         }

         if (!this.c.a((double)this.f.q())) {
            return;
         }

         if (a.player.hasEffect(MobEffects.MOVEMENT_SPEED) && this.d.m()) {
            return;
         }

         if (a.player.hasEffect(MobEffects.REGENERATION) && this.e.m()) {
            return;
         }

         if (a.player.getHealth() / a.player.getMaxHealth() < this.g.q()) {
            if (this.h.a("Soup")) {
               for (int var2 = 0; var2 < 9; var2++) {
                  ItemStack var3 = a.player.getInventory().items.get(var2);
                  if (var3.getItem() == Items.MUSHROOM_STEW) {
                     this.a(var2, true);
                     this.i = true;
                     break;
                  }
               }
            } else if (this.h.a("Head")) {
               for (int var4 = 0; var4 < 9; var4++) {
                  ItemStack var5 = a.player.getInventory().items.get(var4);
                  if (RecoveredUtilsP.a(var5)) {
                     this.a(var4, false);
                     this.i = true;
                     break;
                  }
               }
            }
         }
      }
   }

   private void a(int var1, boolean var2) {
      a.getConnection().send(new ServerboundSetCarriedItemPacket(var1));
      this.k = var2;
      this.j = true;
   }
}
