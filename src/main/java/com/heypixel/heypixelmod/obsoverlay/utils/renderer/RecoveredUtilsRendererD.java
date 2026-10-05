package com.heypixel.heypixelmod.obsoverlay.utils.renderer;

import com.heypixel.heypixelmod.obsoverlay.utils.renderer.text.RecoveredUtilsRendererTextC;
import java.awt.FontFormatException;
import java.io.IOException;

public class RecoveredUtilsRendererD {
   public static RecoveredUtilsRendererTextC a;
   public static RecoveredUtilsRendererTextC b;

   public static void a() throws IOException, FontFormatException {
      a = new RecoveredUtilsRendererTextC("MiSans-Regular", 32, 0, 65535, 16384);
      b = new RecoveredUtilsRendererTextC("icon", 32, 59648, 59652, 512);
   }
}
