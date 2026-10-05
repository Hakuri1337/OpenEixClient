package com.heypixel.heypixelmod.obsoverlay.commands.a;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.a.RecoveredAB;
import com.heypixel.heypixelmod.obsoverlay.commands.CommandInfo;
import com.heypixel.heypixelmod.obsoverlay.commands.RecoveredCommandsA;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsF;

@CommandInfo(
   a = "toggle",
   b = "Toggle a module",
   c = {"t"}
)
public class RecoveredCommandsAF extends RecoveredCommandsA {
   @Override
   public void a(String[] var1) {
      if (var1.length == 1) {
         String var2 = var1[0];

         try {
            ClientModule var3 = EixClient.a().g().c(var2);
            if (var3 != null) {
               var3.f();
            } else {
               RecoveredUtilsF.a("Invalid module.");
            }
         } catch (RecoveredAB var4) {
            RecoveredUtilsF.a("Invalid module.");
         }
      }
   }

   @Override
   public String[] b(String[] var1) {
      return EixClient.a()
         .g()
         .a()
         .stream()
         .map(ClientModule::h)
         .filter(var1x -> var1x.toLowerCase().startsWith(var1.length == 0 ? "" : var1[0].toLowerCase()))
         .toArray(String[]::new);
   }
}
