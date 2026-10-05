package com.heypixel.heypixelmod.obsoverlay.modules.impl.b;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.c.RecoveredCA;
import com.heypixel.heypixelmod.obsoverlay.c.a.RecoveredCAA;
import com.heypixel.heypixelmod.obsoverlay.c.a.RecoveredCAB;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAE;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplM;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.c.ScaffoldModule;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAk;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsP;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsS;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsU;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.network.protocol.game.ServerboundContainerClosePacket;
import net.minecraft.network.protocol.game.ServerboundInteractPacket;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.network.protocol.game.ServerboundPlayerActionPacket;
import net.minecraft.network.protocol.game.ServerboundUseItemOnPacket;
import net.minecraft.network.protocol.game.ServerboundUseItemPacket;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import org.apache.commons.lang3.tuple.Pair;

@ModuleInfo(
   a = "InventoryManager",
   b = "背包整理",
   c = "Automatically manage your inventory",
   d = ModuleCategory.MISC
)
public class InventoryManagerModule extends ClientModule {
   private static final RecoveredUtilsAk K = new RecoveredUtilsAk();
   private final RecoveredDAC L = RecoveredDD.a(this, "Min Delay (Ticks)").a(2.0F).d(1.0F).b(0.0F).c(10.0F).a().c();
   private final RecoveredDAC M = RecoveredDD.a(this, "Max Delay (Ticks)").a(6.0F).d(1.0F).b(0.0F).c(10.0F).a().c();
   RecoveredDAE c = RecoveredDD.a(this, "Offhand Items").a("None", "Golden Apple", "Projectile", "Fishing Rod", "Block").a().e();
   RecoveredDAA d = RecoveredDD.a(this, "Auto Armor").a(true).a().b();
   RecoveredDAA e = RecoveredDD.a(this, "Inventory Only").a(true).a().b();
   RecoveredDAA f = RecoveredDD.a(this, "Switch Sword").a(true).a().b();
   RecoveredDAC g = RecoveredDD.a(this, "Sword Slot").a(1.0F).d(1.0F).b(1.0F).c(9.0F).a(() -> this.f.m()).a().c();
   RecoveredDAA h = RecoveredDD.a(this, "Switch Block").a(() -> !this.c.a("Block")).a(true).a().b();
   RecoveredDAC i = RecoveredDD.a(this, "Block Slot").a(2.0F).d(1.0F).b(1.0F).c(9.0F).a(() -> this.h.m() && !this.c.a("Block")).a().c();
   RecoveredDAC j = RecoveredDD.a(this, "Max Block Size").a(256.0F).d(64.0F).b(64.0F).c(512.0F).a(() -> this.h.m()).a().c();
   RecoveredDAA k = RecoveredDD.a(this, "Switch Pickaxe").a(true).a().b();
   RecoveredDAC l = RecoveredDD.a(this, "Pickaxe Slot").a(3.0F).d(1.0F).b(1.0F).c(9.0F).a(() -> this.k.m()).a().c();
   RecoveredDAA m = RecoveredDD.a(this, "Switch Axe").a(true).a().b();
   RecoveredDAC n = RecoveredDD.a(this, "Axe Slot").a(4.0F).d(1.0F).b(1.0F).c(9.0F).a(() -> this.m.m()).a().c();
   RecoveredDAA o = RecoveredDD.a(this, "Switch Bow or Crossbow").a(true).a().b();
   RecoveredDAC p = RecoveredDD.a(this, "Bow Slot").a(5.0F).d(1.0F).b(1.0F).c(9.0F).a(() -> this.o.m()).a().c();
   RecoveredDAE q = RecoveredDD.a(this, "Bow Priority").a("Crossbow", "Power Bow", "Punch Bow").a(() -> this.o.m()).a().e();
   RecoveredDAC r = RecoveredDD.a(this, "Max Arrow Size").a(256.0F).d(64.0F).b(64.0F).c(512.0F).a(() -> this.o.m()).a().c();
   RecoveredDAA s = RecoveredDD.a(this, "Switch Water Bucket").a(true).a().b();
   RecoveredDAC t = RecoveredDD.a(this, "Water Bucket Slot").a(6.0F).d(1.0F).b(1.0F).c(9.0F).a(() -> this.s.m()).a().c();
   RecoveredDAA u = RecoveredDD.a(this, "Switch Ender Pearl").a(true).a().b();
   RecoveredDAC v = RecoveredDD.a(this, "Ender Pearl Slot").a(7.0F).d(1.0F).b(1.0F).c(9.0F).a(() -> this.u.m()).a().c();
   RecoveredDAA w = RecoveredDD.a(this, "Switch Fireball").a(true).a().b();
   RecoveredDAC x = RecoveredDD.a(this, "Fireball Slot").a(8.0F).d(1.0F).b(1.0F).c(9.0F).a(() -> this.w.m()).a().c();
   RecoveredDAA y = RecoveredDD.a(this, "Switch Golden Apple").a(() -> !this.c.a("Golden Apple")).a(true).a().b();
   RecoveredDAC z = RecoveredDD.a(this, "Golden Apple Slot").a(9.0F).d(1.0F).b(1.0F).c(9.0F).a(() -> this.y.m() && !this.c.a("Golden Apple")).a().c();
   RecoveredDAA A = RecoveredDD.a(this, "Throw Items").a(true).a().b();
   RecoveredDAC B = RecoveredDD.a(this, "Keep Water Buckets").a(1.0F).d(1.0F).b(0.0F).c(5.0F).a(() -> this.A.m()).a().c();
   RecoveredDAC C = RecoveredDD.a(this, "Keep Lava Buckets").a(1.0F).d(1.0F).b(0.0F).c(5.0F).a(() -> this.A.m()).a().c();
   RecoveredDAA D = RecoveredDD.a(this, "Keep Eggs & Snowballs").a(true).a().b();
   RecoveredDAA E = RecoveredDD.a(this, "Switch Eggs & Snowballs").a(false).a(() -> this.D.m() && !this.c.a("Projectile")).a().b();
   RecoveredDAC F = RecoveredDD.a(this, "Eggs & Snowballs Slot")
      .a(9.0F)
      .d(1.0F)
      .b(1.0F)
      .c(9.0F)
      .a(() -> this.E.m() && this.D.m() && !this.c.a("Projectile"))
      .a()
      .c();
   RecoveredDAC G = RecoveredDD.a(this, "Max Eggs & Snowballs Size").a(64.0F).d(16.0F).b(16.0F).c(256.0F).a(() -> this.D.m()).a().c();
   RecoveredDAA H = RecoveredDD.a(this, "Switch Rod").a(() -> !this.c.a("Fishing Rod")).a(false).a().b();
   RecoveredDAC I = RecoveredDD.a(this, "Rod Slot").a(9.0F).d(1.0F).b(1.0F).c(9.0F).a(() -> this.H.m() && !this.c.a("Fishing Rod")).a().c();
   int J = 0;
   private boolean N = false;
   private boolean O = false;

   public static int p() {
      return (int)EixClient.a().g().a(InventoryManagerModule.class).j.q();
   }

   public static boolean q() {
      return EixClient.a().g().a(InventoryManagerModule.class).D.m();
   }

   public static int r() {
      return (int)EixClient.a().g().a(InventoryManagerModule.class).G.q();
   }

   public static int s() {
      return (int)EixClient.a().g().a(InventoryManagerModule.class).r.q();
   }

   public static int t() {
      return (int)EixClient.a().g().a(InventoryManagerModule.class).B.q();
   }

   public static int u() {
      return (int)EixClient.a().g().a(InventoryManagerModule.class).C.q();
   }

   public boolean a(ItemStack var1) {
      if (var1.isEmpty()) {
         return false;
      } else if (RecoveredUtilsP.u(var1)) {
         return true;
      } else if (var1.getDisplayName().getString().contains("点击使用")) {
         return true;
      } else if (var1.getItem() instanceof ArmorItem var2) {
         float var5 = RecoveredUtilsP.s(var1);
         if (RecoveredUtilsP.b(var2.getEquipmentSlot()) >= var5) {
            return false;
         } else {
            float var4 = RecoveredUtilsP.a(var2.getEquipmentSlot());
            return !(var5 < var4);
         }
      } else if (var1.getItem() instanceof SwordItem) {
         return RecoveredUtilsP.f() == var1;
      } else if (var1.getItem() instanceof PickaxeItem) {
         return RecoveredUtilsP.o() == var1;
      } else if (var1.getItem() instanceof AxeItem && !RecoveredUtilsP.b(var1)) {
         return RecoveredUtilsP.q() == var1;
      } else if (var1.getItem() instanceof ShovelItem) {
         return RecoveredUtilsP.t() == var1;
      } else if (var1.getItem() instanceof CrossbowItem) {
         return RecoveredUtilsP.v() == var1;
      } else if (var1.getItem() instanceof BowItem && RecoveredUtilsP.l(var1)) {
         return RecoveredUtilsP.x() == var1;
      } else if (var1.getItem() instanceof BowItem && RecoveredUtilsP.m(var1)) {
         return RecoveredUtilsP.z() == var1;
      } else if (var1.getItem() instanceof BowItem && RecoveredUtilsP.d(Items.BOW) > 1) {
         return false;
      } else if (var1.getItem() == Items.WATER_BUCKET && RecoveredUtilsP.d(Items.WATER_BUCKET) > t()) {
         return false;
      } else if (var1.getItem() == Items.LAVA_BUCKET && RecoveredUtilsP.d(Items.LAVA_BUCKET) > u()) {
         return false;
      } else if (var1.getItem() instanceof FishingRodItem && RecoveredUtilsP.d(Items.FISHING_ROD) > 1) {
         return false;
      } else {
         return (var1.getItem() == Items.SNOWBALL || var1.getItem() == Items.EGG) && !q()
            ? false
            : !(var1.getItem() instanceof ItemNameBlockItem) && RecoveredUtilsP.v(var1);
      }
   }

   @EventTarget
   public void onPacket(RecoveredEventsImplM var1) {
      if (var1.b() == RecoveredEventsApiAA.SEND) {
         if (var1.c() instanceof ServerboundContainerClosePacket) {
            this.O = false;
         }

         if (this.O && !this.e.m()) {
            if (var1.c() instanceof ServerboundMovePlayerPacket) {
               if (RecoveredUtilsU.a()) {
                  a.getConnection().send(new ServerboundContainerClosePacket(a.player.inventoryMenu.containerId));
               }
            } else if (var1.c() instanceof ServerboundUseItemOnPacket
               || var1.c() instanceof ServerboundUseItemPacket
               || var1.c() instanceof ServerboundInteractPacket
               || var1.c() instanceof ServerboundPlayerActionPacket) {
               a.getConnection().send(new ServerboundContainerClosePacket(a.player.inventoryMenu.containerId));
            }
         }
      }
   }

   private boolean v() {
      ArrayList<Pair> var1 = new ArrayList();
      if (!this.D.m()) {
         this.E.a(false);
      }

      var1.add(Pair.of(this.f, this.g));
      var1.add(Pair.of(this.k, this.l));
      var1.add(Pair.of(this.m, this.n));
      var1.add(Pair.of(this.o, this.p));
      var1.add(Pair.of(this.s, this.t));
      var1.add(Pair.of(this.u, this.v));
      var1.add(Pair.of(this.w, this.x));
      if (!this.c.a("Golden Apple")) {
         var1.add(Pair.of(this.y, this.z));
      }

      if (!this.c.a("Projectile")) {
         var1.add(Pair.of(this.E, this.F));
      }

      if (!this.c.a("Fishing Rod")) {
         var1.add(Pair.of(this.H, this.I));
      }

      if (!this.c.a("Block")) {
         var1.add(Pair.of(this.h, this.i));
      }

      HashSet var2 = new HashSet();

      for (Pair var4 : var1) {
         if (((RecoveredDAA)var4.getKey()).m()) {
            int var5 = (int)(((RecoveredDAC)var4.getValue()).q() - 1.0F);
            if (var2.contains(var5)) {
               return false;
            }

            var2.add(var5);
         }
      }

      return true;
   }

   @EventTarget
   public void onMotion(RecoveredEventsImplK var1) {
      if (var1.b() == RecoveredEventsApiAA.PRE) {
         if (!(a.screen instanceof RecoveredCA) && !this.v()) {
            RecoveredCAA var23 = new RecoveredCAA(RecoveredCAB.d, "Duplicate slot config in Inventory Manager! Please check your config!", 8000L);
            EixClient.a().j().a(var23);
            this.f();
            return;
         }

         if (RecoveredUtilsP.a()) {
            return;
         }

         if (RecoveredUtilsU.a()) {
            this.J = 0;
         } else {
            this.J++;
         }

         if (ChestStealerModule.p() || EixClient.a().g().a(ScaffoldModule.class).m() || (this.e.m() ? !(a.screen instanceof InventoryScreen) : this.J <= 1)) {
            this.N = false;
            return;
         }

         if (a.screen instanceof AbstractContainerScreen var2 && var2.getMenu().containerId != a.player.inventoryMenu.containerId) {
            return;
         }

         if (this.d.m()) {
            for (int var8 = 0; var8 < a.player.getInventory().armor.size(); var8++) {
               ItemStack var24 = a.player.getInventory().armor.get(var8);
               Item var5 = var24.getItem();
               if (var5 instanceof ArmorItem) {
                  ArmorItem var4 = (ArmorItem)var5;
                  if (!var24.isEmpty()
                     && K.a(RecoveredUtilsS.b((int)this.L.q(), (int)this.M.q()))
                     && RecoveredUtilsP.a(var4.getEquipmentSlot()) > RecoveredUtilsP.s(var24)) {
                     a.gameMode.handleInventoryMouseClick(a.player.inventoryMenu.containerId, 4 + (4 - var8), 1, ClickType.THROW, a.player);
                     this.O = true;
                     K.b();
                  }
               }
            }

            for (int var9 = 0; var9 < a.player.getInventory().items.size(); var9++) {
               ItemStack var25 = a.player.getInventory().items.get(var9);
               if (!var25.isEmpty()) {
                  Item var48 = var25.getItem();
                  if (var48 instanceof ArmorItem) {
                     ArmorItem var37 = (ArmorItem)var48;
                     float var49 = RecoveredUtilsP.s(var25);
                     boolean var6 = RecoveredUtilsP.a(var37.getEquipmentSlot()) == var49;
                     boolean var7 = RecoveredUtilsP.b(var37.getEquipmentSlot()) < var49;
                     if (var6 && var7 && K.a(RecoveredUtilsS.b((int)this.L.q(), (int)this.M.q()))) {
                        if (var9 < 9) {
                           a.gameMode.handleInventoryMouseClick(a.player.inventoryMenu.containerId, var9 + 36, 0, ClickType.QUICK_MOVE, a.player);
                        } else {
                           a.gameMode.handleInventoryMouseClick(a.player.inventoryMenu.containerId, var9, 0, ClickType.QUICK_MOVE, a.player);
                        }

                        this.O = true;
                        K.b();
                     }
                  }
               }
            }
         }

         if (this.N && K.a(RecoveredUtilsS.b((int)this.L.q(), (int)this.M.q()))) {
            a.gameMode.handleInventoryMouseClick(a.player.inventoryMenu.containerId, 45, 0, ClickType.PICKUP, a.player);
            this.O = true;
            this.N = false;
            K.b();
         }

         if (this.c.a("Golden Apple")) {
            ItemStack var13 = a.player.getInventory().offhand.get(0);
            int var29 = RecoveredUtilsP.b(Items.GOLDEN_APPLE);
            if (var29 != -1 && K.a(RecoveredUtilsS.b((int)this.L.q(), (int)this.M.q()))) {
               if (var13.getItem() == Items.GOLDEN_APPLE) {
                  ItemStack var40 = a.player.getInventory().items.get(var29);
                  if (var13.getCount() + var40.getCount() <= 64) {
                     if (var29 < 9) {
                        a.gameMode.handleInventoryMouseClick(a.player.inventoryMenu.containerId, var29 + 36, 0, ClickType.PICKUP, a.player);
                     } else {
                        a.gameMode.handleInventoryMouseClick(a.player.inventoryMenu.containerId, var29, 0, ClickType.PICKUP, a.player);
                     }

                     this.O = true;
                     this.N = true;
                     K.b();
                  }
               } else {
                  this.c(var29);
               }
            }
         } else if (this.c.a("Projectile")) {
            ItemStack var12 = a.player.getInventory().offhand.get(0);
            ItemStack var28 = RecoveredUtilsP.g();
            if (var28 != null) {
               int var39 = RecoveredUtilsP.j(var28);
               boolean var51 = false;
               if (var12.getItem() != Items.EGG && var12.getItem() != Items.SNOWBALL) {
                  var51 = true;
               } else if (var12.getCount() < var28.getCount()) {
                  var51 = true;
               }

               if (var51 && var39 != -1 && K.a(RecoveredUtilsS.b((int)this.L.q(), (int)this.M.q()))) {
                  this.c(var39);
               }
            }
         } else if (this.c.a("Fishing Rod")) {
            ItemStack var10 = a.player.getInventory().offhand.get(0);
            int var26 = RecoveredUtilsP.b(Items.FISHING_ROD);
            if (var26 != -1 && K.a(RecoveredUtilsS.b((int)this.L.q(), (int)this.M.q())) && var10.getItem() != Items.FISHING_ROD) {
               this.c(var26);
            }
         } else if (this.c.a("Block")) {
            ItemStack var11 = a.player.getInventory().offhand.get(0);
            ItemStack var27 = RecoveredUtilsP.m();
            if (var27 != null) {
               int var38 = RecoveredUtilsP.j(var27);
               boolean var50 = false;
               if (ScaffoldModule.a(var11)) {
                  if (var11.getCount() < var27.getCount()) {
                     var50 = true;
                  }
               } else {
                  var50 = true;
               }

               if (var50 && var38 != -1 && K.a(RecoveredUtilsS.b((int)this.L.q(), (int)this.M.q()))) {
                  this.c(var38);
               }
            }
         }

         if (this.y.m() && !this.c.a("Golden Apple")) {
            this.a((int)(this.z.q() - 1.0F), Items.GOLDEN_APPLE);
         }

         if (this.h.m()) {
            int var14 = (int)(this.i.q() - 1.0F);
            ItemStack var30 = a.player.getInventory().items.get(var14);
            ItemStack var41 = RecoveredUtilsP.m();
            if (var41 != null && (var41.getCount() > var30.getCount() || !ScaffoldModule.a(var30)) && !this.c.a("Block")) {
               this.a(var14, var41);
            }

            if ((float)RecoveredUtilsP.i() > this.j.q()) {
               ItemStack var52 = RecoveredUtilsP.l();
               this.b(var52);
            }
         }

         if (this.f.m()) {
            int var15 = (int)(this.g.q() - 1.0F);
            ItemStack var31 = a.player.getInventory().items.get(var15);
            ItemStack var42 = RecoveredUtilsP.f();
            ItemStack var53 = RecoveredUtilsP.r();
            if (RecoveredUtilsP.q(var53) > RecoveredUtilsP.r(var42)) {
               var42 = var53;
            }

            if (var42 != null) {
               float var56 = var31.getItem() instanceof SwordItem ? RecoveredUtilsP.r(var31) : RecoveredUtilsP.q(var31);
               float var58 = var42.getItem() instanceof SwordItem ? RecoveredUtilsP.r(var42) : RecoveredUtilsP.q(var42);
               if (var58 > var56) {
                  this.a(var15, var42);
               }
            }
         }

         if (this.k.m()) {
            int var16 = (int)(this.l.q() - 1.0F);
            ItemStack var32 = RecoveredUtilsP.o();
            ItemStack var43 = a.player.getInventory().items.get(var16);
            if (var32 != null
               && var32.getItem() instanceof PickaxeItem
               && (RecoveredUtilsP.p(var32) > RecoveredUtilsP.p(var43) || !(var43.getItem() instanceof PickaxeItem))) {
               this.a(var16, var32);
            }
         }

         if (this.m.m()) {
            int var17 = (int)(this.n.q() - 1.0F);
            ItemStack var33 = RecoveredUtilsP.q();
            ItemStack var44 = a.player.getInventory().items.get(var17);
            if (var33 != null
               && var33.getItem() instanceof AxeItem
               && (RecoveredUtilsP.p(var33) > RecoveredUtilsP.p(var44) || !(var44.getItem() instanceof AxeItem))) {
               this.a(var17, var33);
            }
         }

         if (this.H.m() && !this.c.a("Fishing Rod")) {
            int var18 = (int)(this.I.q() - 1.0F);
            ItemStack var34 = RecoveredUtilsP.h();
            ItemStack var45 = a.player.getInventory().items.get(var18);
            if (!(var45.getItem() instanceof FishingRodItem)) {
               this.a(var18, var34);
            }
         }

         if (this.o.m()) {
            int var19 = (int)(this.p.q() - 1.0F);
            ItemStack var35 = a.player.getInventory().items.get(var19);
            ItemStack var46;
            float var54;
            float var57;
            if (this.q.a("Crossbow")) {
               var46 = RecoveredUtilsP.v();
               var54 = RecoveredUtilsP.t(var46);
               var57 = RecoveredUtilsP.t(var35);
            } else if (this.q.a("Power Bow")) {
               var46 = RecoveredUtilsP.z();
               var54 = RecoveredUtilsP.o(var46);
               var57 = RecoveredUtilsP.o(var35);
            } else {
               var46 = RecoveredUtilsP.x();
               var54 = RecoveredUtilsP.n(var46);
               var57 = RecoveredUtilsP.n(var35);
            }

            if (var46 == null) {
               var46 = RecoveredUtilsP.v();
               var54 = RecoveredUtilsP.t(var46);
               var57 = RecoveredUtilsP.t(var35);
            }

            if (var46 == null) {
               var46 = RecoveredUtilsP.z();
               var54 = RecoveredUtilsP.o(var46);
               var57 = RecoveredUtilsP.o(var35);
            }

            if (var46 == null) {
               var46 = RecoveredUtilsP.x();
               var54 = RecoveredUtilsP.n(var46);
               var57 = RecoveredUtilsP.n(var35);
            }

            if (var46 != null && var54 > var57) {
               this.a(var19, var46);
            }

            if ((float)RecoveredUtilsP.d(Items.ARROW) > this.r.q()) {
               ItemStack var59 = RecoveredUtilsP.k();
               this.b(var59);
            }
         }

         if (this.u.m()) {
            this.a((int)(this.v.q() - 1.0F), Items.ENDER_PEARL);
         }

         if (this.s.m()) {
            this.a((int)(this.t.q() - 1.0F), Items.WATER_BUCKET);
         }

         if (this.w.m()) {
            this.a((int)(this.x.q() - 1.0F), Items.FIRE_CHARGE);
         }

         if (this.D.m()) {
            if ((float)(RecoveredUtilsP.d(Items.EGG) + RecoveredUtilsP.d(Items.SNOWBALL)) > this.G.q()) {
               ItemStack var20 = RecoveredUtilsP.j();
               this.b(var20);
            }

            if (this.E.m() && !this.c.a("Projectile")) {
               int var21 = (int)(this.F.q() - 1.0F);
               if (RecoveredUtilsP.d(Items.EGG) > 0) {
                  this.a(var21, Items.EGG);
               } else if (RecoveredUtilsP.d(Items.SNOWBALL) > 0) {
                  this.a(var21, Items.SNOWBALL);
               }
            }
         }

         if (this.A.m()) {
            List<Integer> var22 = IntStream.range(0, a.player.getInventory().items.size()).boxed().collect(Collectors.toList());
            Collections.shuffle(var22);

            for (Integer var47 : var22) {
               ItemStack var55 = a.player.getInventory().items.get(var47);
               if (!var55.isEmpty() && !this.a(var55)) {
                  this.b(var55);
               }
            }
         }
      }
   }

   private void c(int var1) {
      if (var1 < 9) {
         a.gameMode.handleInventoryMouseClick(a.player.inventoryMenu.containerId, var1 + 36, 40, ClickType.SWAP, a.player);
      } else {
         a.gameMode.handleInventoryMouseClick(a.player.inventoryMenu.containerId, var1, 40, ClickType.SWAP, a.player);
      }

      this.O = true;
      K.b();
   }

   private void b(ItemStack var1) {
      if (RecoveredUtilsP.k(var1) && K.a(RecoveredUtilsS.b((int)this.L.q(), (int)this.M.q()))) {
         int var2 = RecoveredUtilsP.j(var1);
         if (var2 != -1) {
            if (var2 < 9) {
               a.gameMode.handleInventoryMouseClick(a.player.inventoryMenu.containerId, var2 + 36, 1, ClickType.THROW, a.player);
            } else {
               a.gameMode.handleInventoryMouseClick(a.player.inventoryMenu.containerId, var2, 1, ClickType.THROW, a.player);
            }

            this.O = true;
            K.b();
         }
      }
   }

   private void a(int var1, ItemStack var2) {
      ItemStack var3 = a.player.getInventory().items.get(var1);
      if (RecoveredUtilsP.k(var3) && var2 != var3 && K.a(RecoveredUtilsS.b((int)this.L.q(), (int)this.M.q()))) {
         int var4 = RecoveredUtilsP.j(var2);
         if (var4 != -1) {
            if (var4 < 9) {
               a.gameMode.handleInventoryMouseClick(a.player.inventoryMenu.containerId, var4 + 36, var1, ClickType.SWAP, a.player);
            } else {
               a.gameMode.handleInventoryMouseClick(a.player.inventoryMenu.containerId, var4, var1, ClickType.SWAP, a.player);
            }

            this.O = true;
            K.b();
         }
      }
   }

   private void a(int var1, Item var2) {
      ItemStack var3 = a.player.getInventory().items.get(var1);
      if (RecoveredUtilsP.k(var3) && K.a(RecoveredUtilsS.b((int)this.L.q(), (int)this.M.q()))) {
         int var4 = RecoveredUtilsP.b(var2);
         if (var4 != -1) {
            ItemStack var5 = a.player.getInventory().items.get(var4);
            if (var3.getItem() != var2 || var3.getItem() == var2 && var3.getCount() < var5.getCount()) {
               if (var4 < 9) {
                  a.gameMode.handleInventoryMouseClick(a.player.inventoryMenu.containerId, var4 + 36, var1, ClickType.SWAP, a.player);
               } else {
                  a.gameMode.handleInventoryMouseClick(a.player.inventoryMenu.containerId, var4, var1, ClickType.SWAP, a.player);
               }

               this.O = true;
               K.b();
            }
         }
      }
   }
}
