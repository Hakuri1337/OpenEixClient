package com.heypixel.heypixelmod.obsoverlay.utils;

import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.EventRender2D;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplG;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplH;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplU;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.network.protocol.game.ClientboundPingPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.network.protocol.game.ClientboundSetHealthPacket;
import net.minecraft.network.protocol.game.ClientboundSetScorePacket;
import net.minecraft.network.protocol.game.ClientboundSystemChatPacket;

public class RecoveredUtilsAe {
   public static final Map<String, AtomicInteger> a = new HashMap<>();
   private static int b = 0;
   private static int c = 0;
   private static final double d = 0.0625;
   private static long e = 0L;
   private static final long f = 1000L;
   private static final long g = 500L;
   private static long h = 0L;

   public static int a() {
      return b;
   }

   public static int b() {
      return c;
   }

   @EventTarget(
      a = 0
   )
   public void onAllPackets(RecoveredEventsImplG var1) {
      if (var1.b() == RecoveredEventsApiAA.RECEIVE) {
         if (var1.c() instanceof ClientboundPingPacket) {
            b++;
         }

         if (var1.c() instanceof ClientboundPlayerPositionPacket var2) {
            a(var2);
         }

         if (var1.c() instanceof ClientboundSetScorePacket var4
            && Minecraft.getInstance().level != null
            && ("belowHealth".equals(var4.getObjectiveName()) || "health".equals(var4.getObjectiveName()))
            && Minecraft.getInstance().player != null
            && !var4.getOwner().equals(Minecraft.getInstance().player.getGameProfile().getName())) {
            if (!a.containsKey(var4.getOwner())) {
               AtomicInteger var7 = new AtomicInteger();
               a.put(var4.getOwner(), var7);
            }

            a.get(var4.getOwner()).set(var4.getScore());
         }

         if (var1.c() instanceof ClientboundSetHealthPacket var5 && var5.getHealth() > 20.0F) {
            var1.a(true);
         }
      }
   }

   private static void a(ClientboundPlayerPositionPacket var0) {
      Minecraft var1 = Minecraft.getInstance();
      if (var1.player != null && var1.level != null) {
         if (var0.getRelativeArguments() == null || var0.getRelativeArguments().isEmpty()) {
            double var2 = var0.getX() - var1.player.getX();
            double var4 = var0.getY() - var1.player.getY();
            double var6 = var0.getZ() - var1.player.getZ();
            double var8 = var2 * var2 + var4 * var4 + var6 * var6;
            if (!(var8 < 0.00390625)) {
               long var10 = System.currentTimeMillis();
               if (var10 - e >= 1000L) {
                  e = var10;
                  c++;
                  if (var10 - h >= 500L) {
                     h = var10;
                     RecoveredUtilsF.a("§bLagback detected. Count: " + c);
                  }
               }
            }
         }
      }
   }

   @EventTarget
   public void onUpdate(EventRender2D var1) {
      if (Minecraft.getInstance().level != null) {
         for (AbstractClientPlayer var3 : Minecraft.getInstance().level.players()) {
            if (var3 != Minecraft.getInstance().player && a.containsKey(var3.getName().getString())) {
               var3.setHealth((float)Math.max(1, a.get(var3.getName().getString()).get()));
            }
         }
      }
   }

   @EventTarget
   public void onRespawn(RecoveredEventsImplU var1) {
      b = 0;
      c = 0;
      e = 0L;
      h = 0L;
   }

   @EventTarget(
      a = 0
   )
   public void onPacket(RecoveredEventsImplH var1) {
      if (var1.b() instanceof ClientboundSystemChatPacket var2) {
         String var4 = var2.content().getString();
         if (var4.contains("游戏准备开始") || var4.contains("游戏结束")) {
            c = 0;
            e = 0L;
            h = 0L;
         }
      }
   }
}
