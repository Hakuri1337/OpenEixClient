package com.heypixel.heypixelmod.obsoverlay.utils.e.b;

public enum RecoveredUtilsEBB {
   TTF("ttf"),
   OTF("otf");

   private final String c;

   private RecoveredUtilsEBB(String var3) {
      this.c = var3;
   }

   public static RecoveredUtilsEBB a(String var0) {
      String var1 = var0.toLowerCase();

      return switch (var1) {
         case "ttf" -> TTF;
         case "otf" -> OTF;
         default -> throw new IllegalArgumentException("Unsupported font type: " + var0);
      };
   }

   @Override
   public String toString() {
      return this.c;
   }
}
