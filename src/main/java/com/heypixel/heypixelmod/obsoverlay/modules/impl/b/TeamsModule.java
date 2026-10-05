package com.heypixel.heypixelmod.obsoverlay.modules.impl.b;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAE;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import java.util.Objects;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

@ModuleInfo(
   a = "Teams",
   b = "队伍",
   c = "Prevent attack teammates",
   d = ModuleCategory.MISC
)
public class TeamsModule extends ClientModule {
   public static TeamsModule c;
   public RecoveredDAE d = RecoveredDD.a(this, "Mode").a(0).a("Scoreboard", "Color").a().e();

   public TeamsModule() {
      c = this;
   }

   public static boolean a(Entity var0) {
      if (!EixClient.a().g().a(TeamsModule.class).m()) {
         return false;
      } else if (var0 instanceof Player) {
         if (c.d.a("Color")) {
            Integer var3 = var0.getTeamColor();
            Integer var4 = a.player.getTeamColor();
            return var3.equals(var4);
         } else {
            String var1 = b(var0);
            String var2 = b(a.player);
            return Objects.equals(var1, var2);
         }
      } else {
         return false;
      }
   }

   public static String b(Entity var0) {
      PlayerInfo var1 = a.getConnection().getPlayerInfo(var0.getUUID());
      if (var1 == null) {
         return null;
      } else {
         return var1.getTeam() != null ? var1.getTeam().getName() : null;
      }
   }
}
