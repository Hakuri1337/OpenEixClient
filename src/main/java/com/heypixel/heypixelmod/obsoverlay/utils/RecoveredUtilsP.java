package com.heypixel.heypixelmod.obsoverlay.utils;

import com.heypixel.heypixelmod.obsoverlay.modules.impl.c.ScaffoldModule;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BookItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ExperienceBottleItem;
import net.minecraft.world.item.FireworkRocketItem;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.PlayerHeadItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SkullBlock;

public class RecoveredUtilsP {
   private static final Minecraft a = Minecraft.getInstance();

   public static boolean a() {
      return d().stream().anyMatch(var0 -> {
         if (var0.isEmpty()) {
            return false;
         } else {
            String var1 = var0.getDisplayName().getString();
            return var1.contains("长按点击") || var1.contains("点击使用") || var1.contains("离开游戏") || var1.contains("选择一个队伍") || var1.contains("再来一局");
         }
      });
   }

   public static boolean a(ItemStack var0) {
      if (var0.isEmpty()) {
         return false;
      } else {
         return var0.getItem() instanceof BlockItem var1 ? var1.getBlock() instanceof SkullBlock : false;
      }
   }

   public static boolean b(ItemStack var0) {
      if (var0.isEmpty()) {
         return false;
      } else if (!(var0.getItem() instanceof AxeItem)) {
         return false;
      } else {
         int var1 = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, var0);
         return var1 >= 8 && var1 < 50;
      }
   }

   public static boolean c(ItemStack var0) {
      return var0.isEmpty() ? false : var0.getItem() == Items.GOLDEN_AXE && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, var0) > 100;
   }

   public static boolean d(ItemStack var0) {
      return !var0.isEmpty() && var0.getItem() == Items.ENCHANTED_GOLDEN_APPLE;
   }

   public static boolean e(ItemStack var0) {
      return !var0.isEmpty() && var0.getItem() == Items.END_CRYSTAL;
   }

   public static boolean f(ItemStack var0) {
      return var0.isEmpty() ? false : var0.getItem() == Items.SLIME_BALL && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.KNOCKBACK, var0) > 1;
   }

   public static boolean g(ItemStack var0) {
      return var0.isEmpty() ? false : var0.getItem() == Items.STICK && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.KNOCKBACK, var0) > 1;
   }

   public static int b() {
      for (int var0 = 9; var0 < a.player.getInventory().items.size(); var0++) {
         if (a.player.getInventory().items.get(var0).isEmpty()) {
            return var0;
         }
      }

      return -1;
   }

   public static int c() {
      for (int var0 = 0; var0 < 9; var0++) {
         if (a.player.getInventory().items.get(var0).isEmpty()) {
            return var0;
         }
      }

      return -1;
   }

   public static Integer a(Item var0) {
      for (int var1 = 0; var1 < 9; var1++) {
         ItemStack var2 = a.player.getInventory().items.get(var1);
         if (var2.getItem() == var0) {
            return var1;
         }
      }

      return null;
   }

   public static int h(ItemStack var0) {
      return EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, var0);
   }

   public static int i(ItemStack var0) {
      return EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, var0);
   }

   public static List<ItemStack> d() {
      ArrayList var0 = new ArrayList(40);
      var0.addAll(a.player.getInventory().items);
      var0.addAll(a.player.getInventory().armor);
      return var0;
   }

   public static float a(EquipmentSlot var0) {
      return d()
         .stream()
         .filter(var1 -> !var1.isEmpty() && var1.getItem() instanceof ArmorItem && ((ArmorItem)var1.getItem()).getEquipmentSlot() == var0)
         .map(RecoveredUtilsP::s)
         .max(Float::compareTo)
         .orElse(0.0F);
   }

   public static float b(EquipmentSlot var0) {
      if (var0 == EquipmentSlot.HEAD) {
         return s(a.player.getInventory().armor.get(3));
      } else if (var0 == EquipmentSlot.CHEST) {
         return s(a.player.getInventory().armor.get(2));
      } else if (var0 == EquipmentSlot.LEGS) {
         return s(a.player.getInventory().armor.get(1));
      } else {
         return var0 == EquipmentSlot.FEET ? s(a.player.getInventory().armor.get(0)) : 0.0F;
      }
   }

   public static float e() {
      return d().stream().filter(var0 -> !var0.isEmpty() && var0.getItem() instanceof SwordItem).map(RecoveredUtilsP::r).max(Float::compareTo).orElse(0.0F);
   }

   public static ItemStack f() {
      return d()
         .stream()
         .filter(var0 -> !var0.isEmpty() && var0.getItem() instanceof SwordItem)
         .max(Comparator.comparingInt(var0 -> (int)(r(var0) * 100.0F)))
         .orElse(null);
   }

   public static int j(ItemStack var0) {
      if (var0 == null) {
         return -1;
      } else {
         for (int var1 = 0; var1 < a.player.getInventory().items.size(); var1++) {
            if (a.player.getInventory().items.get(var1) == var0) {
               return var1;
            }
         }

         return -1;
      }
   }

   public static boolean k(ItemStack var0) {
      if (!var0.isEmpty()) {
         if (var0.getItem() instanceof PlayerHeadItem) {
            return false;
         } else {
            String var1 = var0.getDisplayName().getString();
            if (var1.contains("Click")) {
               return false;
            } else if (var1.contains("Right")) {
               return false;
            } else if (var1.contains("点击")) {
               return false;
            } else if (var1.contains("Teleport")) {
               return false;
            } else if (var1.contains("使用")) {
               return false;
            } else {
               return var1.contains("传送") ? false : !var1.contains("再来");
            }
         }
      } else {
         return true;
      }
   }

   public static int b(Item var0) {
      for (int var1 = 0; var1 < a.player.getInventory().items.size(); var1++) {
         ItemStack var2 = a.player.getInventory().items.get(var1);
         if (var2.getItem() == var0) {
            return var1;
         }
      }

      return -1;
   }

   public static ItemStack g() {
      return d()
         .stream()
         .filter(var0 -> !var0.isEmpty() && (var0.getItem() == Items.EGG || var0.getItem() == Items.SNOWBALL) && k(var0))
         .max(Comparator.comparingInt(ItemStack::getCount))
         .orElse(null);
   }

   public static ItemStack h() {
      return d().stream().filter(var0 -> !var0.isEmpty() && var0.getItem() instanceof FishingRodItem && k(var0)).findAny().orElse(null);
   }

   public static int i() {
      return d()
         .stream()
         .filter(var0 -> !var0.isEmpty() && var0.getItem() instanceof BlockItem && ScaffoldModule.a(var0) && k(var0))
         .mapToInt(ItemStack::getCount)
         .sum();
   }

   public static ItemStack j() {
      return d()
         .stream()
         .filter(var0 -> !var0.isEmpty() && (var0.getItem() == Items.EGG || var0.getItem() == Items.SNOWBALL))
         .min(Comparator.comparingInt(ItemStack::getCount))
         .orElse(null);
   }

   public static ItemStack k() {
      return d()
         .stream()
         .filter(var0 -> !var0.isEmpty() && var0.getItem() instanceof ArrowItem && k(var0))
         .min(Comparator.comparingInt(ItemStack::getCount))
         .orElse(null);
   }

   public static ItemStack l() {
      return d()
         .stream()
         .filter(var0 -> !var0.isEmpty() && var0.getItem() instanceof BlockItem && ScaffoldModule.a(var0) && k(var0))
         .min(Comparator.comparingInt(ItemStack::getCount))
         .orElse(null);
   }

   public static ItemStack m() {
      return d()
         .stream()
         .filter(var0 -> !var0.isEmpty() && var0.getItem() instanceof BlockItem && ScaffoldModule.a(var0) && k(var0))
         .max(Comparator.comparingInt(ItemStack::getCount))
         .orElse(null);
   }

   public static float n() {
      return d()
         .stream()
         .filter(var0 -> !var0.isEmpty() && var0.getItem() instanceof PickaxeItem && k(var0))
         .map(RecoveredUtilsP::p)
         .max(Float::compareTo)
         .orElse(0.0F);
   }

   public static ItemStack o() {
      return d()
         .stream()
         .filter(var0 -> !var0.isEmpty() && var0.getItem() instanceof PickaxeItem && k(var0))
         .max(Comparator.comparingInt(var0 -> (int)(p(var0) * 100.0F)))
         .orElse(null);
   }

   public static float p() {
      return d()
         .stream()
         .filter(var0 -> !var0.isEmpty() && var0.getItem() instanceof AxeItem && !b(var0) && k(var0))
         .map(RecoveredUtilsP::p)
         .max(Float::compareTo)
         .orElse(0.0F);
   }

   public static ItemStack q() {
      return d()
         .stream()
         .filter(var0 -> !var0.isEmpty() && var0.getItem() instanceof AxeItem && !b(var0) && k(var0))
         .max(Comparator.comparingInt(var0 -> (int)(p(var0) * 100.0F)))
         .orElse(null);
   }

   public static ItemStack r() {
      return d()
         .stream()
         .filter(var0 -> !var0.isEmpty() && var0.getItem() instanceof AxeItem && b(var0) && k(var0) && !c(var0))
         .max(Comparator.comparingInt(var0 -> (int)(q(var0) * 100.0F)))
         .orElse(null);
   }

   public static float s() {
      return d()
         .stream()
         .filter(var0 -> !var0.isEmpty() && var0.getItem() instanceof ShovelItem && k(var0))
         .map(RecoveredUtilsP::p)
         .max(Float::compareTo)
         .orElse(0.0F);
   }

   public static ItemStack t() {
      return d()
         .stream()
         .filter(var0 -> !var0.isEmpty() && var0.getItem() instanceof ShovelItem && k(var0))
         .max(Comparator.comparingInt(var0 -> (int)(p(var0) * 100.0F)))
         .orElse(null);
   }

   public static float u() {
      return d()
         .stream()
         .filter(var0 -> !var0.isEmpty() && var0.getItem() instanceof CrossbowItem && k(var0))
         .map(RecoveredUtilsP::t)
         .max(Float::compareTo)
         .orElse(0.0F);
   }

   public static ItemStack v() {
      return d()
         .stream()
         .filter(var0 -> !var0.isEmpty() && var0.getItem() instanceof CrossbowItem && k(var0))
         .max(Comparator.comparingInt(var0 -> (int)(t(var0) * 100.0F)))
         .orElse(null);
   }

   public static float w() {
      return d()
         .stream()
         .filter(var0 -> !var0.isEmpty() && var0.getItem() instanceof BowItem && k(var0))
         .map(RecoveredUtilsP::n)
         .max(Float::compareTo)
         .orElse(0.0F);
   }

   public static ItemStack x() {
      return d()
         .stream()
         .filter(var0 -> !var0.isEmpty() && var0.getItem() instanceof BowItem && k(var0))
         .max(Comparator.comparingInt(var0 -> (int)(n(var0) * 100.0F)))
         .orElse(null);
   }

   public static float y() {
      return d()
         .stream()
         .filter(var0 -> !var0.isEmpty() && var0.getItem() instanceof BowItem && k(var0))
         .map(RecoveredUtilsP::o)
         .max(Float::compareTo)
         .orElse(0.0F);
   }

   public static ItemStack z() {
      return d()
         .stream()
         .filter(var0 -> !var0.isEmpty() && var0.getItem() instanceof BowItem && k(var0))
         .max(Comparator.comparingInt(var0 -> (int)(o(var0) * 100.0F)))
         .orElse(null);
   }

   public static boolean l(ItemStack var0) {
      return n(var0) > 10.0F && k(var0);
   }

   public static boolean m(ItemStack var0) {
      return o(var0) > 10.0F && k(var0);
   }

   public static boolean c(Item var0) {
      return d().stream().anyMatch(var1 -> !var1.isEmpty() && var1.getItem() == var0);
   }

   public static int d(Item var0) {
      return d().stream().filter(var1 -> !var1.isEmpty() && var1.getItem() == var0).mapToInt(ItemStack::getCount).sum();
   }

   public static float n(ItemStack var0) {
      if (var0 == null) {
         return 0.0F;
      } else if (var0.isEmpty()) {
         return 0.0F;
      } else if (var0.getItem() instanceof BowItem) {
         float var1 = 10.0F;
         var1 += (float)EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, var0);
         var1 += (float)EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, var0);
         var1 += (float)EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, var0);
         var1 += (float)EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, var0) / 10.0F;
         return var1 + (float)var0.getDamageValue() / (float)var0.getMaxDamage();
      } else {
         return 0.0F;
      }
   }

   public static float o(ItemStack var0) {
      if (var0 == null) {
         return 0.0F;
      } else if (var0.isEmpty()) {
         return 0.0F;
      } else if (var0.getItem() instanceof BowItem) {
         float var1 = 10.0F;
         var1 += (float)EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PUNCH_ARROWS, var0) / 10.0F;
         var1 += (float)EnchantmentHelper.getItemEnchantmentLevel(Enchantments.INFINITY_ARROWS, var0);
         var1 += (float)EnchantmentHelper.getItemEnchantmentLevel(Enchantments.FLAMING_ARROWS, var0);
         var1 += (float)EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, var0);
         return var1 + (float)var0.getDamageValue() / (float)var0.getMaxDamage();
      } else {
         return 0.0F;
      }
   }

   public static float p(ItemStack var0) {
      float var1 = 0.0F;
      if (var0 == null) {
         return 0.0F;
      } else if (var0.isEmpty()) {
         return 0.0F;
      } else if (u(var0)) {
         return 0.0F;
      } else if (b(var0)) {
         return 0.0F;
      } else {
         if (var0.getItem() instanceof PickaxeItem) {
            var1 += var0.getDestroySpeed(Blocks.STONE.defaultBlockState());
         } else if (var0.getItem() instanceof AxeItem) {
            var1 += var0.getDestroySpeed(Blocks.OAK_LOG.defaultBlockState());
         } else {
            if (!(var0.getItem() instanceof ShovelItem)) {
               return 0.0F;
            }

            var1 += var0.getDestroySpeed(Blocks.DIRT.defaultBlockState());
         }

         int var2 = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BLOCK_EFFICIENCY, var0);
         if (var2 > 0) {
            var1 += (float)var2 * 0.0075F;
         }

         return var1;
      }
   }

   public static float q(ItemStack var0) {
      float var1 = 0.0F;
      if (var0 == null) {
         return 0.0F;
      } else if (var0.isEmpty()) {
         return 0.0F;
      } else {
         if (var0.getItem() instanceof AxeItem var2 && b(var0)) {
            if (var2 == Items.WOODEN_AXE) {
               var1 += 4.0F;
            } else if (var2 == Items.STONE_AXE) {
               var1 += 5.0F;
            } else if (var2 == Items.IRON_AXE) {
               var1 += 6.0F;
            } else if (var2 == Items.GOLDEN_AXE) {
               var1 += 4.0F;
            } else if (var2 == Items.DIAMOND_AXE) {
               var1 += 7.0F;
            }
         }

         int var4 = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, var0);
         if (var4 > 0) {
            float var5 = Enchantments.SHARPNESS.getDamageBonus(var4, MobType.UNDEFINED);
            var1 += var5;
         }

         return var1;
      }
   }

   public static float r(ItemStack var0) {
      float var1 = 0.0F;
      if (var0 == null) {
         return 0.0F;
      } else if (var0.isEmpty()) {
         return 0.0F;
      } else {
         if (var0.getItem() instanceof SwordItem var2) {
            var1 += var2.getDamage() + 1.0F;
         }

         int var4 = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, var0);
         if (var4 > 0) {
            float var5 = Enchantments.SHARPNESS.getDamageBonus(var4, MobType.UNDEFINED);
            var1 += var5;
         }

         return var1;
      }
   }

   public static float s(ItemStack var0) {
      int var1 = 0;
      if (var0 == null) {
         return 0.0F;
      } else if (var0.isEmpty()) {
         return 0.0F;
      } else {
         if (var0.getItem() instanceof ArmorItem var2) {
            ArmorMaterial var5 = var2.getMaterial();
            if (var5 == ArmorMaterials.LEATHER) {
               var1 += 100;
            } else if (var5 == ArmorMaterials.CHAIN) {
               var1 += 200;
            } else if (var5 == ArmorMaterials.IRON) {
               var1 += 400;
            } else if (var5 == ArmorMaterials.GOLD) {
               var1 += 300;
            } else if (var5 == ArmorMaterials.DIAMOND) {
               var1 += 500;
            } else if (var5 == ArmorMaterials.NETHERITE) {
               var1 += 600;
            }
         }

         var1 += EnchantmentHelper.getItemEnchantmentLevel(Enchantments.ALL_DAMAGE_PROTECTION, var0);
         return (float)var1;
      }
   }

   public static float t(ItemStack var0) {
      int var1 = 0;
      if (var0 == null) {
         return 0.0F;
      } else if (var0.isEmpty()) {
         return 0.0F;
      } else {
         if (var0.getItem() instanceof CrossbowItem) {
            var1 += EnchantmentHelper.getItemEnchantmentLevel(Enchantments.QUICK_CHARGE, var0);
            var1 += EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MULTISHOT, var0);
            var1 += EnchantmentHelper.getItemEnchantmentLevel(Enchantments.PIERCING, var0);
         }

         return (float)var1;
      }
   }

   public static boolean u(ItemStack var0) {
      if (var0.isEmpty()) {
         return false;
      } else if (var0.getItem() instanceof AxeItem
         && var0.getItem() == Items.GOLDEN_AXE
         && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.SHARPNESS, var0) > 100) {
         return true;
      } else {
         return var0.getItem() == Items.SLIME_BALL && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.KNOCKBACK, var0) > 1
            ? true
            : var0.getItem() == Items.TOTEM_OF_UNDYING || var0.getItem() == Items.END_CRYSTAL;
      }
   }

   public static boolean v(ItemStack var0) {
      if (var0.isEmpty()) {
         return true;
      } else {
         Item var1 = var0.getItem();
         if (var1 instanceof BlockItem var2) {
            return var2.getBlock() == Blocks.ENCHANTING_TABLE ? false : var2.getBlock() != Blocks.COBWEB;
         } else if (var1 instanceof BookItem) {
            return false;
         } else if (var1 instanceof ExperienceBottleItem) {
            return false;
         } else if (var1 instanceof FireworkRocketItem) {
            return false;
         } else {
            return var1 != Items.WHEAT_SEEDS && var1 != Items.BEETROOT_SEEDS && var1 != Items.MELON_SEEDS && var1 != Items.PUMPKIN_SEEDS
               ? var1 != Items.FLINT_AND_STEEL
               : false;
         }
      }
   }
}
