package com.heypixel.heypixelmod.obsoverlay.commands.a;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.b.RecoveredBA;
import com.heypixel.heypixelmod.obsoverlay.commands.CommandInfo;
import com.heypixel.heypixelmod.obsoverlay.commands.RecoveredCommandsA;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsF;

@CommandInfo(
   a = "proxy",
   b = "Set client proxy",
   c = {"prox"}
)
public class RecoveredCommandsAE extends RecoveredCommandsA {
   @Override
   public void a(String[] var1) {
      RecoveredBA.InnerC var2 = EixClient.a().i().a().proxy;
      if (var1.length == 0) {
         if (var2.host == null) {
            RecoveredUtilsF.a("No proxy set.");
         } else {
            RecoveredUtilsF.a("Current Proxy: " + var2.host + ":" + var2.port);
         }
      } else if (var1.length == 1) {
         if (var1[0].equals("cancel")) {
            var2.host = null;
            var2.port = 0;
            RecoveredUtilsF.a("Proxy cancelled.");
         } else {
            try {
               String[] var3 = var1[0].split(":");
               var2.host = var3[0];
               var2.port = Integer.parseInt(var3[1]);
               RecoveredUtilsF.a("Proxy set to " + var2.host + ":" + var2.port);
            } catch (Exception var4) {
               RecoveredUtilsF.a("Invalid proxy.");
            }
         }
      }
   }

   @Override
   public String[] b(String[] var1) {
      return new String[0];
   }
}
