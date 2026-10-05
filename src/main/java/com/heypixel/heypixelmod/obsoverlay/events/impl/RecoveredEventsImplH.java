package com.heypixel.heypixelmod.obsoverlay.events.impl;

import com.heypixel.heypixelmod.obsoverlay.events.api.events.a.RecoveredEventsApiEventsAA;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;

public class RecoveredEventsImplH extends RecoveredEventsApiEventsAA {
   private Packet<ClientGamePacketListener> b;

   public RecoveredEventsImplH(Packet<ClientGamePacketListener> var1) {
      this.b = var1;
   }

   public Packet<ClientGamePacketListener> b() {
      return this.b;
   }

   public void a(Packet<ClientGamePacketListener> var1) {
      this.b = var1;
   }
}
