package com.heypixel.heypixelmod.obsoverlay.utils;

import com.heypixel.heypixelmod.obsoverlay.b.RecoveredBB;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RecoveredUtilsQ {
   private static final SimpleDateFormat a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   private static final BufferedWriter b;
   private static final BufferedWriter c;

   public static void a(String var0) {
      try {
         b.write("[%s] %s\n".formatted(a.format(new Date()), var0));
         b.flush();
      } catch (IOException var2) {
         throw new RuntimeException(var2);
      }
   }

   public static void b(String var0) {
      try {
         c.write("[%s] %s\n".formatted(a.format(new Date()), var0));
         c.flush();
      } catch (IOException var2) {
         throw new RuntimeException(var2);
      }
   }

   public static void a() {
      try {
         b.close();
         c.close();
      } catch (IOException var1) {
         throw new RuntimeException(var1);
      }
   }

   static {
      try {
         b = new BufferedWriter(new FileWriter(new File(RecoveredBB.clientFolder, "antibots.log")));
         c = new BufferedWriter(new FileWriter(new File(RecoveredBB.clientFolder, "playerinfo.log")));
      } catch (IOException var1) {
         throw new RuntimeException(var1);
      }
   }
}
