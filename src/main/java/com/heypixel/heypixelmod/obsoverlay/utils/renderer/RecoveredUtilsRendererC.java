package com.heypixel.heypixelmod.obsoverlay.utils.renderer;

public enum RecoveredUtilsRendererC {
   Lines(2),
   Triangles(3);

   public final int c;

   private RecoveredUtilsRendererC(int var3) {
      this.c = var3;
   }

   public int a() {
      if (this == Lines) {
         return 1;
      } else {
         return this == Triangles ? 4 : 0;
      }
   }
}
