package com.heypixel.heypixelmod.obsoverlay.utils;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.c.a.RecoveredCAA;
import com.heypixel.heypixelmod.obsoverlay.c.a.RecoveredCAB;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.api.events.Event;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplG;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplM;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;
import net.minecraft.network.protocol.game.ClientboundPingPacket;
import net.minecraft.network.protocol.game.ClientboundSetPlayerTeamPacket;
import net.minecraft.network.protocol.game.ClientboundSetTimePacket;
import net.minecraft.network.protocol.game.ServerboundCustomPayloadPacket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RecoveredUtilsW {
   public static final Logger a = LogManager.getLogger("PacketUtil");
   private static final RecoveredUtilsAl c = new RecoveredUtilsAl();
   private static final RecoveredCAA d = new RecoveredCAA(RecoveredCAB.c, "Server lagging!", 2000L);
   public static Set<Packet<?>> b = new HashSet<>();
   private static long e = 0L;

   public static boolean a() {
      return c.a(500.0);
   }

   public static void a(Packet<?> var0) {
      a.info("Sending: " + var0.getClass().getName());
      if (var0 instanceof ServerboundCustomPayloadPacket var1) {
         a.info("RE custompayload, {}", var1.getIdentifier().toString());
         if (var1.getIdentifier().toString().equals("heypixelmod:s2cevent")) {
            FriendlyByteBuf var2 = var1.getData();
            var2.markReaderIndex();
            int var3 = var2.readVarInt();
            a.info("after packet ({}", var3);
            if (var3 == 2) {
               a.info("after packet");
               a.info(Arrays.toString(RecoveredUtilsT.a(var2, var2.readableBytes())));
            }

            var2.resetReaderIndex();
         }
      }

      b.add(var0);
      Minecraft.getInstance().getConnection().send(var0);
   }

   @EventTarget
   public void onMotion(RecoveredEventsImplK var1) {
      if (var1.b() == RecoveredEventsApiAA.PRE) {
         if (a()) {
            EixClient.a().j().a(d);
            d.b(System.currentTimeMillis());
            d.a(RecoveredCAB.c);
            e = Math.round(c.d());
            d.a("Server lagging. Aura disabled! (" + e + "ms)");
         } else {
            d.a(RecoveredCAB.a);
            d.a("Server currently online! (" + e + "ms)");
         }
      }
   }

   @EventTarget(
      a = 4
   )
   public void onGlobalPacket(RecoveredEventsImplG var1) {
      if (var1.c() instanceof ClientboundPingPacket
         || var1.c() instanceof ClientboundMoveEntityPacket
         || var1.c() instanceof ClientboundSetTimePacket
         || var1.c() instanceof ClientboundSetPlayerTeamPacket) {
         c.a();
      }

      if (!var1.a()) {
         Packet var2 = var1.c();
         RecoveredEventsImplM var3 = new RecoveredEventsImplM(var1.b(), var2);
         EixClient.a().b().a((Event)var3);
         if (var3.a()) {
            var1.a(true);
         }

         var1.a(var3.c());
      }
   }
}
