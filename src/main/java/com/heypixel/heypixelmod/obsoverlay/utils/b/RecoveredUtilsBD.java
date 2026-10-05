package com.heypixel.heypixelmod.obsoverlay.utils.b;

import cn.paradisemc.ZKMIndy;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@ZKMIndy
public class RecoveredUtilsBD {
   private static final byte[] a = new byte[]{-1, -40, -1, -32, 0, 16, 74, 70, 73, 70};

   public static Set<String> a() throws IOException {
      HashSet var0 = new HashSet();
      if (System.getProperty("os.name").toLowerCase().contains("windows")) {
         File var1 = new File(System.getenv("APPDATA") + "\\Tencent\\QQ\\Misc");
         if (var1.exists() && var1.isDirectory()) {
            File[] var2 = var1.listFiles();
            if (var2 != null) {
               for (File var6 : var2) {
                  if (!var6.isDirectory()) {
                     String var7 = var6.getName();
                     if (var7.matches("[0-9]+") && var7.length() >= 5 && var7.length() <= 10 && a(var6)) {
                        var0.add(var7);
                     }
                  }
               }
            }
         }

         File var10 = new File(System.getenv("PUBLIC") + "\\Documents\\Tencent\\QQ\\UserDataInfo.ini");
         if (var10.exists() && var10.isFile()) {
            BufferedReader var11 = new BufferedReader(new InputStreamReader(Files.newInputStream(var10.toPath())));

            String var12;
            while ((var12 = var11.readLine()) != null && !var12.isEmpty()) {
               if (var12.startsWith("UserDataSavePath=")) {
                  File var13 = new File(var12.split("=")[1]);
                  if (var13.exists() && var13.isDirectory()) {
                     for (File var9 : Objects.requireNonNull(var13.listFiles())) {
                        if (var9.isDirectory() && var9.getName().length() >= 6 && var9.getName().length() <= 10 && var9.getName().matches("^[0-9]*$")) {
                           var0.add(var9.getName());
                        }
                     }
                  }
               }
            }
         }
      }

      return var0;
   }

   private static boolean a(File var0) {
      try {
         boolean var1;
         try (FileInputStream var2 = new FileInputStream(var0)) {
            byte[] var3 = new byte[10];
            if (var2.read(var3) != 0) {
               return Arrays.equals(var3, a);
            }

            var1 = false;
         }

         return var1;
      } catch (Exception var7) {
         return false;
      }
   }
}
