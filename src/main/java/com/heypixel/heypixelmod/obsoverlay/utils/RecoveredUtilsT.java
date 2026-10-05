package com.heypixel.heypixelmod.obsoverlay.utils;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.events.api.events.Event;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplH;
import io.netty.handler.codec.DecoderException;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.PacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.RunningOnDifferentThreadException;
import net.minecraft.util.thread.BlockableEventLoop;
import org.slf4j.Logger;

public class RecoveredUtilsT {
   public static <T extends PacketListener> void a(Logger var0, Packet<T> var1, T var2, BlockableEventLoop<?> var3) throws RunningOnDifferentThreadException {
      if (!var3.isSameThread()) {
         var3.executeIfPossible(() -> {
            if (var2.isAcceptingMessages()) {
               try {
                  RecoveredEventsImplH var4 = new RecoveredEventsImplH((Packet)var1);
                  if (var3.isSameThread()) {
                     EixClient.a().b().a((Event)var4);
                     if (var4.a()) {
                        return;
                     }
                  }

                  var1.handle(var2);
               } catch (Exception var5) {
                  if (var2.shouldPropagateHandlingExceptions()) {
                     throw var5;
                  }

                  var0.error("Failed to handle packet {}, suppressing error", var1, var5);
               }
            } else {
               var0.debug("Ignoring packet due to disconnection: {}", var1);
            }
         });
         throw RunningOnDifferentThreadException.RUNNING_ON_DIFFERENT_THREAD;
      }
   }

   public static byte[] a(FriendlyByteBuf var0, int var1) {
      int var2 = var0.readVarInt() - 1;
      if (var2 > var1) {
         throw new DecoderException("ByteArray with size " + var2 + " is bigger than allowed " + var1);
      } else {
         byte[] var3 = new byte[var2];
         var0.readBytes(var3);
         return var3;
      }
   }
}
