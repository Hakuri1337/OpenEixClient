package com.heypixel.heypixelmod.obsoverlay.commands;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.commands.a.RecoveredCommandsAA;
import com.heypixel.heypixelmod.obsoverlay.commands.a.RecoveredCommandsAB;
import com.heypixel.heypixelmod.obsoverlay.commands.a.RecoveredCommandsAC;
import com.heypixel.heypixelmod.obsoverlay.commands.a.RecoveredCommandsAD;
import com.heypixel.heypixelmod.obsoverlay.commands.a.RecoveredCommandsAE;
import com.heypixel.heypixelmod.obsoverlay.commands.a.RecoveredCommandsAF;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplE;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsF;
import java.util.HashMap;
import java.util.Map;

public class RecoveredCommandsB {
   public static final String a = ".";
   public final Map<String, RecoveredCommandsA> b = new HashMap<>();

   public RecoveredCommandsB() {
      try {
         this.a();
      } catch (Exception var2) {
         throw new RuntimeException(var2);
      }

      EixClient.a().b().a(this);
   }

   private void a() {
      this.a(new RecoveredCommandsAA());
      this.a(new RecoveredCommandsAF());
      this.a(new RecoveredCommandsAB());
      this.a(new RecoveredCommandsAC());
      this.a(new RecoveredCommandsAE());
      this.a(new RecoveredCommandsAD());
   }

   private void a(RecoveredCommandsA var1) {
      var1.a();
      this.b.put(var1.b().toLowerCase(), var1);

      for (String var5 : var1.d()) {
         this.b.put(var5.toLowerCase(), var1);
      }
   }

   @EventTarget
   public void onChat(RecoveredEventsImplE var1) {
      if (var1.b().startsWith(".")) {
         var1.a(true);
         String var2 = var1.b().substring(".".length());
         String[] var3 = var2.split(" ");
         if (var3.length < 1) {
            RecoveredUtilsF.a("Invalid command.");
            return;
         }

         String var4 = var3[0].toLowerCase();
         RecoveredCommandsA var5 = this.b.get(var4);
         if (var5 == null) {
            RecoveredUtilsF.a("Invalid command.");
            return;
         }

         String[] var6 = new String[var3.length - 1];
         System.arraycopy(var3, 1, var6, 0, var6.length);
         var5.a(var6);
      }
   }
}
