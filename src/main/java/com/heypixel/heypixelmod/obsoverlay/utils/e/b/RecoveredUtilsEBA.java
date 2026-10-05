package com.heypixel.heypixelmod.obsoverlay.utils.e.b;

import com.heypixel.heypixelmod.obsoverlay.utils.e.d.RecoveredUtilsEDA;
import io.github.humbleui.skija.Font;
import io.github.humbleui.skija.Typeface;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class RecoveredUtilsEBA {
   private static final Map<String, Typeface> a = new ConcurrentHashMap<>();
   private static final Map<String, Font> b = new ConcurrentHashMap<>();

   private static Typeface a(String var0, RecoveredUtilsEBB var1) {
      return a.computeIfAbsent(var0, var1x -> b(var1x, var1));
   }

   private static Typeface b(String var0, RecoveredUtilsEBB var1) {
      Optional<io.github.humbleui.skija.Data> var2 = RecoveredUtilsEDA.b("/assets/heypixel/vcx6svvqmet8/fonts/" + var0);
      return var2.map(Typeface::makeFromData).orElseThrow(() -> new IllegalArgumentException("Font not found: " + var0));
   }

   public static Font a(String var0, float var1, RecoveredUtilsEBB var2) {
      String var3 = var0 + "@" + var1;
      Font var4 = b.get(var3);
      if (var4 != null) {
         return var4;
      } else {
         Typeface var5 = a(var0, var2);
         Font var6 = new Font(var5, var1);
         b.put(var3, var6);
         return var6;
      }
   }

   public static Font a(String var0, float var1) {
      return a(var0, var1, a(var0));
   }

   private static RecoveredUtilsEBB a(String var0) {
      String var1 = var0.substring(var0.lastIndexOf(46) + 1).toLowerCase();
      return RecoveredUtilsEBB.a(var1);
   }

   public static void a() {
      a.clear();
      b.clear();
   }

   public static void a(String... var0) {
      for (String var4 : var0) {
         a(var4, a(var4));
      }
   }
}
