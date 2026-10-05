package com.heypixel.heypixelmod.obsoverlay.utils;

import com.heypixel.heypixelmod.mixin.O.accessors.LivingEntityAccessor;
import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.c.a.RecoveredCAA;
import com.heypixel.heypixelmod.obsoverlay.c.a.RecoveredCAB;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplU;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import java.util.LinkedHashSet;

public class RecoveredUtilsI {
   private static final Minecraft a = Minecraft.getInstance();
   private static final Map<Entity, Set<String>> b = new ConcurrentHashMap<>();
   private static final Map<String, RecoveredUtilsAf> c = new ConcurrentHashMap<>();

   public static Set<String> a(AbstractClientPlayer var0) {
      List<net.minecraft.world.effect.MobEffect> var1 = RecoveredUtilsZ.a(var0.getEntityData().get(LivingEntityAccessor.getEffectColorId()));
      var1.remove(MobEffects.ABSORPTION);
      LinkedHashSet<String> var2 = new LinkedHashSet();
      if (b.containsKey(var0)) {
         var2.addAll(b.get(var0));
      }

      Set<String> var3 = var1.stream().map(var0x -> "effect.minecraft." + BuiltInRegistries.MOB_EFFECT.getKey(var0x).getPath()).collect(Collectors.toSet());
      var2.addAll(var3);
      return var2;
   }

   public static Map<String, RecoveredUtilsAf> a() {
      return c;
   }

   @EventTarget
   public void onRespawn(RecoveredEventsImplU var1) {
      b.clear();
      c.clear();
   }

   @EventTarget
   public void onMotion(RecoveredEventsImplK var1) {
      if (var1.b() == RecoveredEventsApiAA.PRE && a.level != null) {
         a().forEach((var0, var1x) -> {
            if (System.currentTimeMillis() - var1x.j() > 500L) {
               a().remove(var0);
            }
         });

         for (AbstractClientPlayer var3 : new ArrayList<>(a.level.players())) {
            if (var3 != a.player) {
               if (!b.containsKey(var3)) {
                  b.put(var3, new HashSet<>());
               }

               Set var4 = b.get(var3);
               if ((RecoveredUtilsP.c(var3.getMainHandItem()) || RecoveredUtilsP.c(var3.getOffhandItem())) && !var4.contains("God Axe")) {
                  RecoveredCAA var5 = new RecoveredCAA(RecoveredCAB.c, var3.getName().getString() + " is holding god axe!", 3000L);
                  EixClient.a().j().a(var5);
                  var4.add("God Axe");
               }

               if ((RecoveredUtilsP.d(var3.getMainHandItem()) || RecoveredUtilsP.d(var3.getOffhandItem())) && !var4.contains("Enchanted Golden Apple")) {
                  RecoveredCAA var6 = new RecoveredCAA(RecoveredCAB.c, var3.getName().getString() + " is holding enchanted golden apple!", 3000L);
                  EixClient.a().j().a(var6);
                  var4.add("Enchanted Golden Apple");
               }

               if ((RecoveredUtilsP.e(var3.getMainHandItem()) || RecoveredUtilsP.e(var3.getOffhandItem())) && !var4.contains("End Crystal")) {
                  RecoveredCAA var7 = new RecoveredCAA(RecoveredCAB.c, var3.getName().getString() + " is holding end crystal!", 3000L);
                  EixClient.a().j().a(var7);
                  var4.add("End Crystal");
               }

               if ((RecoveredUtilsP.f(var3.getMainHandItem()) || RecoveredUtilsP.f(var3.getOffhandItem())) && !var4.contains("KB Ball")) {
                  RecoveredCAA var8 = new RecoveredCAA(RecoveredCAB.c, var3.getName().getString() + " is holding KB Ball!", 3000L);
                  EixClient.a().j().a(var8);
                  var4.add("KB Ball");
               }

               if ((RecoveredUtilsP.g(var3.getMainHandItem()) || RecoveredUtilsP.g(var3.getOffhandItem())) && !var4.contains("KB Stick")) {
                  RecoveredCAA var9 = new RecoveredCAA(RecoveredCAB.c, var3.getName().getString() + " is holding KB Stick!", 3000L);
                  EixClient.a().j().a(var9);
                  var4.add("KB Stick");
               }

               if ((RecoveredUtilsP.h(var3.getMainHandItem()) > 2 || RecoveredUtilsP.h(var3.getOffhandItem()) > 2) && !var4.contains("Punch Bow")) {
                  RecoveredCAA var10 = new RecoveredCAA(RecoveredCAB.c, var3.getName().getString() + " is holding Punch Bow!", 3000L);
                  EixClient.a().j().a(var10);
                  var4.add("Punch Bow");
               }

               if ((RecoveredUtilsP.i(var3.getMainHandItem()) > 3 || RecoveredUtilsP.i(var3.getOffhandItem()) > 3) && !var4.contains("Power Bow")) {
                  RecoveredCAA var11 = new RecoveredCAA(RecoveredCAB.c, var3.getName().getString() + " is holding Power Bow!", 3000L);
                  EixClient.a().j().a(var11);
                  var4.add("Power Bow");
               }
            }
         }
      }
   }
}
