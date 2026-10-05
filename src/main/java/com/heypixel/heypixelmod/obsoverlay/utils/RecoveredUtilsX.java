package com.heypixel.heypixelmod.obsoverlay.utils;

import com.heypixel.heypixelmod.mixin.O.accessors.ClientLevelAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.prediction.BlockStatePredictionHandler;
import net.minecraft.client.multiplayer.prediction.PredictiveAction;

public class RecoveredUtilsX {
   private static final Minecraft a = Minecraft.getInstance();

   public static void a(PredictiveAction var0) {
      if (a.getConnection() != null && a.level != null) {
         try (BlockStatePredictionHandler var1 = ((ClientLevelAccessor)a.level).getBlockStatePredictionHandler().startPredicting()) {
            int var2 = var1.currentSequence();
            a.getConnection().send(var0.predict(var2));
         }
      }
   }
}
