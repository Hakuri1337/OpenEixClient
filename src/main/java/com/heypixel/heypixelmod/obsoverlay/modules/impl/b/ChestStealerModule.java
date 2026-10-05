package com.heypixel.heypixelmod.obsoverlay.modules.impl.b;

import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.c.ScaffoldModule;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAk;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsP;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsS;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.ContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;

@ModuleInfo(
   a = "ChestStealer",
   b = "箱子小偷",
   c = "Automatically steals items from chests",
   d = ModuleCategory.MISC
)
public class ChestStealerModule extends ClientModule {
   private static final RecoveredUtilsAk c = new RecoveredUtilsAk();
   private final RecoveredDAC d = RecoveredDD.a(this, "Min Delay (Ticks)").a(2.0F).d(1.0F).b(0.0F).c(10.0F).a().c();
   private final RecoveredDAC e = RecoveredDD.a(this, "Max Delay (Ticks)").a(6.0F).d(1.0F).b(0.0F).c(10.0F).a().c();
   private final RecoveredDAA f = RecoveredDD.a(this, "Ender Chest").a(false).a().b();
   private Screen g;

   public static boolean p() {
      return !c.a(3);
   }

   public static boolean a(ItemStack var0) {
      if (var0.isEmpty()) {
         return false;
      } else if (!RecoveredUtilsP.u(var0) && !RecoveredUtilsP.b(var0)) {
         if (var0.getItem() instanceof ArmorItem var1) {
            float var11 = RecoveredUtilsP.s(var0);
            float var18 = RecoveredUtilsP.a(var1.getEquipmentSlot());
            return !(var11 <= var18);
         } else if (var0.getItem() instanceof SwordItem) {
            float var10 = RecoveredUtilsP.r(var0);
            float var17 = RecoveredUtilsP.e();
            return !(var10 <= var17);
         } else if (var0.getItem() instanceof PickaxeItem) {
            float var9 = RecoveredUtilsP.p(var0);
            float var16 = RecoveredUtilsP.n();
            return !(var9 <= var16);
         } else if (var0.getItem() instanceof AxeItem) {
            float var8 = RecoveredUtilsP.p(var0);
            float var15 = RecoveredUtilsP.p();
            return !(var8 <= var15);
         } else if (var0.getItem() instanceof ShovelItem) {
            float var7 = RecoveredUtilsP.p(var0);
            float var14 = RecoveredUtilsP.s();
            return !(var7 <= var14);
         } else if (var0.getItem() instanceof CrossbowItem) {
            float var6 = RecoveredUtilsP.t(var0);
            float var13 = RecoveredUtilsP.u();
            return !(var6 <= var13);
         } else if (var0.getItem() instanceof BowItem && RecoveredUtilsP.l(var0)) {
            float var5 = RecoveredUtilsP.n(var0);
            float var12 = RecoveredUtilsP.w();
            return !(var5 <= var12);
         } else if (var0.getItem() instanceof BowItem && RecoveredUtilsP.m(var0)) {
            float var4 = RecoveredUtilsP.o(var0);
            float var3 = RecoveredUtilsP.y();
            return !(var4 <= var3);
         } else if (var0.getItem() == Items.COMPASS) {
            return !RecoveredUtilsP.c(var0.getItem());
         } else if (var0.getItem() == Items.WATER_BUCKET && RecoveredUtilsP.d(Items.WATER_BUCKET) >= InventoryManagerModule.t()) {
            return false;
         } else if (var0.getItem() == Items.LAVA_BUCKET && RecoveredUtilsP.d(Items.LAVA_BUCKET) >= InventoryManagerModule.u()) {
            return false;
         } else if (var0.getItem() instanceof BlockItem && ScaffoldModule.a(var0) && RecoveredUtilsP.i() + var0.getCount() >= InventoryManagerModule.p()) {
            return false;
         } else if (var0.getItem() == Items.ARROW && RecoveredUtilsP.d(Items.ARROW) + var0.getCount() >= InventoryManagerModule.s()) {
            return false;
         } else if (var0.getItem() instanceof FishingRodItem && RecoveredUtilsP.d(Items.FISHING_ROD) >= 1) {
            return false;
         } else {
            return var0.getItem() != Items.SNOWBALL && var0.getItem() != Items.EGG
                  || RecoveredUtilsP.d(Items.SNOWBALL) + RecoveredUtilsP.d(Items.EGG) + var0.getCount() < InventoryManagerModule.r()
                     && InventoryManagerModule.q()
               ? !(var0.getItem() instanceof ItemNameBlockItem) && RecoveredUtilsP.v(var0)
               : false;
         }
      } else {
         return true;
      }
   }

   @EventTarget(
      a = 1
   )
   public void onMotion(RecoveredEventsImplK var1) {
      if (var1.b() == RecoveredEventsApiAA.PRE) {
         Screen var2 = a.screen;
         if (var2 instanceof ContainerScreen var3) {
            ChestMenu var4 = var3.getMenu();
            if (var2 != this.g) {
               c.b();
            } else {
               String var5 = var3.getTitle().getString();
               String var6 = Component.translatable("container.chest").getString();
               String var7 = Component.translatable("container.chestDouble").getString();
               String var8 = Component.translatable("container.enderchest").getString();
               if (var5.equals(var6) || var5.equals(var7) || var5.equals("Chest") || this.f.m() && var5.equals(var8)) {
                  if (this.a(var4) && c.a(RecoveredUtilsS.b((int)this.d.q(), (int)this.e.q()))) {
                     a.player.closeContainer();
                  } else {
                     List<Integer> var9 = IntStream.range(0, var4.getRowCount() * 9).boxed().collect(Collectors.toList());
                     Collections.shuffle(var9);

                     for (Integer var11 : var9) {
                        ItemStack var12 = var4.getSlot(var11).getItem();
                        if (a(var12) && this.a(var4, var12) && c.a(RecoveredUtilsS.b((int)this.d.q(), (int)this.e.q()))) {
                           a.gameMode.handleInventoryMouseClick(var4.containerId, var11, 0, ClickType.QUICK_MOVE, a.player);
                           c.b();
                           break;
                        }
                     }
                  }
               }
            }
         }

         this.g = var2;
      }
   }

   private boolean a(ChestMenu var1, ItemStack var2) {
      if (!RecoveredUtilsP.u(var2) && !RecoveredUtilsP.b(var2)) {
         for (int var3 = 0; var3 < var1.getRowCount() * 9; var3++) {
            ItemStack var4 = var1.getSlot(var3).getItem();
            if (var2.getItem() instanceof ArmorItem var5 && var4.getItem() instanceof ArmorItem var6) {
               if (var5.getEquipmentSlot() == var6.getEquipmentSlot() && RecoveredUtilsP.s(var4) > RecoveredUtilsP.s(var2)) {
                  return false;
               }
               continue;
            }

            if (var2.getItem() instanceof SwordItem && var4.getItem() instanceof SwordItem) {
               if (RecoveredUtilsP.r(var4) > RecoveredUtilsP.r(var2)) {
                  return false;
               }
            } else if (var2.getItem() instanceof PickaxeItem && var4.getItem() instanceof PickaxeItem) {
               if (RecoveredUtilsP.p(var4) > RecoveredUtilsP.p(var2)) {
                  return false;
               }
            } else if (var2.getItem() instanceof AxeItem && var4.getItem() instanceof AxeItem) {
               if (RecoveredUtilsP.p(var4) > RecoveredUtilsP.p(var2)) {
                  return false;
               }
            } else if (var2.getItem() instanceof ShovelItem && var4.getItem() instanceof ShovelItem && RecoveredUtilsP.p(var4) > RecoveredUtilsP.p(var2)) {
               return false;
            }
         }

         return true;
      } else {
         return true;
      }
   }

   private boolean a(ChestMenu var1) {
      for (int var2 = 0; var2 < var1.getRowCount() * 9; var2++) {
         ItemStack var3 = var1.getSlot(var2).getItem();
         if (!var3.isEmpty() && a(var3) && this.a(var1, var3)) {
            return false;
         }
      }

      return true;
   }
}
