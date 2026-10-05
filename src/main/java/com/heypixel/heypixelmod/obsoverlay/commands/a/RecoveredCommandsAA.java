package com.heypixel.heypixelmod.obsoverlay.commands.a;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.a.RecoveredAB;
import com.heypixel.heypixelmod.obsoverlay.commands.CommandInfo;
import com.heypixel.heypixelmod.obsoverlay.commands.RecoveredCommandsA;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplJ;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsF;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.platform.InputConstants.Key;

@CommandInfo(
   a = "bind",
   b = "Bind a command to a key",
   c = {"b"}
)
public class RecoveredCommandsAA extends RecoveredCommandsA {
   @Override
   public void a(String[] var1) {
      if (var1.length == 1) {
         final String var2 = var1[0];

         try {
            final ClientModule var3 = EixClient.a().g().c(var2);
            if (var3 != null) {
               RecoveredUtilsF.a("Press a key to bind " + var2 + " to.");
               EixClient.a().b().a(new Object() {
                  @EventTarget
                  public void onKey(RecoveredEventsImplJ var1) {
                     if (var1.c()) {
                        var3.b(var1.b());
                        Key var2x = InputConstants.getKey(var1.b(), 0);
                        String var3x = var2x.getDisplayName().getString().toUpperCase();
                        RecoveredUtilsF.a("Bound " + var2 + " to " + var3x + ".");
                        EixClient.a().b().b(this);
                        EixClient.a().i().c();
                     }
                  }
               });
            } else {
               RecoveredUtilsF.a("Invalid module.");
            }
         } catch (RecoveredAB var7) {
            RecoveredUtilsF.a("Invalid module.");
         }
      } else if (var1.length == 2) {
         String var8 = var1[0];
         String var9 = var1[1];

         try {
            ClientModule var4 = EixClient.a().g().c(var8);
            if (var4 != null) {
               if (var9.equalsIgnoreCase("none")) {
                  var4.b(InputConstants.UNKNOWN.getValue());
                  RecoveredUtilsF.a("Unbound " + var8 + ".");
                  EixClient.a().i().c();
               } else {
                  Key var5 = InputConstants.getKey("key.keyboard." + var9.toLowerCase());
                  if (var5 != InputConstants.UNKNOWN) {
                     var4.b(var5.getValue());
                     RecoveredUtilsF.a("Bound " + var8 + " to " + var9.toUpperCase() + ".");
                     EixClient.a().i().c();
                  } else {
                     RecoveredUtilsF.a("Invalid key.");
                  }
               }
            } else {
               RecoveredUtilsF.a("Invalid module.");
            }
         } catch (RecoveredAB var6) {
            RecoveredUtilsF.a("Invalid module.");
         }
      } else {
         RecoveredUtilsF.a("Usage: .bind <module> [key]");
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
