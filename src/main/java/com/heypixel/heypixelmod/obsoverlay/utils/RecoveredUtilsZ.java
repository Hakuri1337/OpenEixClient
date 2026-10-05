package com.heypixel.heypixelmod.obsoverlay.utils;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;
import net.minecraft.world.effect.MobEffect;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RecoveredUtilsZ {
   private static final Logger a = LogManager.getLogger(RecoveredUtilsZ.class);
   private static final Map<Integer, List<MobEffect>> b = new HashMap<>();

   public static List<MobEffect> a(int var0) {
      if (b.containsKey(var0)) {
         return b.get(var0);
      } else if (b.containsKey(var0 + 1)) {
         return b.get(var0 + 1);
      } else {
         return b.containsKey(var0 - 1) ? b.get(var0 - 1) : Collections.emptyList();
      }
   }

   static {
      InputStream var0 = RecoveredUtilsZ.class.getResourceAsStream("/assets/heypixel/vcx6svvqmet8/potion_effects.dat");
      if (var0 != null) {
         try {
            GZIPInputStream var1 = new GZIPInputStream(var0);

            for (String var3 : IOUtils.readLines(var1)) {
               String[] var4 = var3.split(":");
               if (var4.length == 2) {
                  int var5 = Integer.parseInt(var4[0]);
                  String var6 = var4[1];
                  String[] var7 = var6.split("\\+");
                  List var8 = Arrays.stream(var7).map(Integer::parseInt).map(MobEffect::byId).collect(Collectors.toList());
                  b.put(var5, var8);
               }
            }
         } catch (Exception var9) {
            a.error("Failed to load potion effects", (Throwable)var9);
         }
      }
   }
}
