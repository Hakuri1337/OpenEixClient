package com.heypixel.heypixelmod.obsoverlay.utils.e.d;

import io.github.humbleui.skija.Data;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class RecoveredUtilsEDA {
   public static Optional<byte[]> a(String var0) {
      try {
         Optional var2;
         try (InputStream var1 = c(var0)) {
            var2 = Optional.of(var1.readAllBytes());
         }

         return var2;
      } catch (IOException var6) {
         return Optional.empty();
      }
   }

   public static Optional<Data> b(String var0) {
      return a(var0).map(Data::makeFromBytes);
   }

   public static InputStream c(String var0) {
      InputStream var1 = RecoveredUtilsEDA.class.getResourceAsStream(var0);
      if (var1 == null) {
         throw new IllegalArgumentException("Resource not found: " + var0);
      } else {
         return var1;
      }
   }
}
