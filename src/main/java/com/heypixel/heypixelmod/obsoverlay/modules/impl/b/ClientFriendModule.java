package com.heypixel.heypixelmod.obsoverlay.modules.impl.b;

import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import net.minecraft.world.entity.Entity;

@ModuleInfo(
   a = "ClientFriend",
   b = "客户端朋友",
   c = "Treat other users as friend!",
   d = ModuleCategory.MISC
)
public class ClientFriendModule extends ClientModule {
   public static boolean a(Entity var0) {
      return false;
   }
}
