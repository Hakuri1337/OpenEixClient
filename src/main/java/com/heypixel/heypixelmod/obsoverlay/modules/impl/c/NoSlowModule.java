package com.heypixel.heypixelmod.obsoverlay.modules.impl.c;

import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAE;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplAd;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplL;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplZ;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

@ModuleInfo(
   a = "NoSlow",
   b = "无减速",
   c = "NoSlowDown",
   d = ModuleCategory.MOVEMENT
)
public class NoSlowModule extends ClientModule {
   public RecoveredDAE c = RecoveredDD.a(this, "Mode").a(0).a("None", "Heypixel 2/3", "Grim50%", "Grim 1/3", "Jump").a().e();
   public RecoveredDAA d = RecoveredDD.a(this, "Food").a(true).a().b();
   public RecoveredDAA e = RecoveredDD.a(this, "Bow").a(true).a().b();
   public RecoveredDAA f = RecoveredDD.a(this, "Crossbow").a(true).a().b();
   private int g = 0;

   @EventTarget
   public void onSlow(RecoveredEventsImplZ var1) {
      if (a.player != null && (!this.p() || a.player.getUseItemRemainingTicks() <= 30)) {
         if (this.d.m() || !this.p()) {
            if (this.e.m() || !this.a(Items.BOW)) {
               if (this.f.m() || !this.a(Items.CROSSBOW)) {
                  String var2 = this.c.l();
                  switch (var2) {
                     case "Jump":
                        this.a(var1);
                        break;
                     case "Grim50%":
                        this.b(var1);
                        break;
                     case "None":
                        this.c(var1);
                        break;
                     case "Grim 1/3":
                        this.d(var1);
                        break;
                     case "Heypixel 2/3":
                        this.e(var1);
                  }
               }
            }
         }
      }
   }

   private void a(RecoveredEventsImplZ var1) {
      if (this.g == 1 && a.player.getUseItemRemainingTicks() <= 30) {
         var1.a(false);
         if (!a.player.isSprinting()) {
            a.player.setSprinting(true);
         }
      }
   }

   private void b(RecoveredEventsImplZ var1) {
      if (a.player.getUseItemRemainingTicks() % 2 == 0 && a.player.getUseItemRemainingTicks() <= 30) {
         var1.a(false);
         if (!a.player.isSprinting()) {
            a.player.setSprinting(true);
         }
      }
   }

   private void c(RecoveredEventsImplZ var1) {
      var1.a(false);
      if (!a.player.isSprinting()) {
         a.player.setSprinting(true);
      }
   }

   private void d(RecoveredEventsImplZ var1) {
      if (a.player.getUseItemRemainingTicks() % 3 == 0 && (!this.p() || a.player.getUseItemRemainingTicks() <= 30)) {
         var1.a(false);
         if (!a.player.isSprinting()) {
            a.player.setSprinting(true);
         }
      }
   }

   private void e(RecoveredEventsImplZ var1) {
      if (a.player.getUseItemRemainingTicks() % 3 != 0 && (!this.p() || a.player.getUseItemRemainingTicks() <= 30)) {
         var1.a(false);
         if (!a.player.isSprinting()) {
            a.player.setSprinting(true);
         }
      }
   }

   @EventTarget
   public void onUpdate(RecoveredEventsImplAd var1) {
      this.a(this.c.l());
      if (a.player.onGround()) {
         this.g++;
      } else {
         this.g = 0;
      }
   }

   @Override
   public void d() {
      this.g = 0;
   }

   @Override
   public void e() {
      this.g = 0;
   }

   private boolean p() {
      ItemStack var1 = a.player.getMainHandItem();
      ItemStack var2 = a.player.getOffhandItem();
      return var1.is(Items.GOLDEN_APPLE)
         || var2.is(Items.GOLDEN_APPLE)
         || var1.is(Items.ENCHANTED_GOLDEN_APPLE)
         || var2.is(Items.ENCHANTED_GOLDEN_APPLE)
         || var1.is(Items.POTION)
         || var2.is(Items.POTION);
   }

   private boolean a(Item var1) {
      ItemStack var2 = a.player.getMainHandItem();
      ItemStack var3 = a.player.getOffhandItem();
      return var2.is(var1) || var3.is(var1);
   }

   @EventTarget
   public void onMoveInput(RecoveredEventsImplL var1) {
      if (a.player.onGround() && a.player.isUsingItem() && (var1.a() != 0.0F || var1.b() != 0.0F) && this.c.a("Jump")) {
         var1.a(true);
      }
   }
}
