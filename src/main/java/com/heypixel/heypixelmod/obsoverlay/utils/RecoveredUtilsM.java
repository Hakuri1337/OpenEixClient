package com.heypixel.heypixelmod.obsoverlay.utils;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

public class RecoveredUtilsM {
   private static final List<String> a = new CopyOnWriteArrayList<>();

   public static boolean a(Entity var0) {
      return var0 instanceof Player && a.contains(var0.getName().getString());
   }

   public static boolean a(String var0) {
      return a.contains(var0);
   }

   public static void a(Player var0) {
      a.add(var0.getName().getString());
   }

   public static void b(String var0) {
      a.add(var0);
   }

   public static void b(Player var0) {
      a.remove(var0.getName().getString());
   }

   public static List<String> a() {
      return a;
   }
}
