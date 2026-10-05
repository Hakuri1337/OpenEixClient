package com.heypixel.heypixelmod.obsoverlay.utils.renderer;

public class RecoveredUtilsRendererJ extends RecoveredUtilsRendererG {
   private final RecoveredUtilsRendererI c;

   public RecoveredUtilsRendererJ(RecoveredUtilsRendererI var1, RecoveredUtilsRendererC var2, RecoveredUtilsRendererG.InnerA... var3) {
      super(var2, var3);
      this.c = var1;
   }

   @Override
   protected void f() {
      this.c.a();
   }
}
