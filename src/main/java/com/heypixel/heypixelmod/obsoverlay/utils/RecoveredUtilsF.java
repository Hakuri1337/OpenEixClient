package com.heypixel.heypixelmod.obsoverlay.utils;

import cn.paradisemc.ZKMIndy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.network.chat.Component;

@ZKMIndy
public class RecoveredUtilsF {
   private static final String a = "§7[§bN§7] ";

   public static void a(Component var0) {
      ChatComponent var1 = Minecraft.getInstance().gui.getChat();
      var1.addMessage(var0);
   }

   public static void a(String var0) {
      a(true, var0);
   }

   public static void a(boolean var0, String var1) {
      a(Component.nullToEmpty((var0 ? "§7[§bN§7] " : "") + var1));
   }
}
