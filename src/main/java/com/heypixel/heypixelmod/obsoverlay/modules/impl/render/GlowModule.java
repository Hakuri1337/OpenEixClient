package com.heypixel.heypixelmod.obsoverlay.modules.impl.render;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;

@ModuleInfo(
   a = "Glow",
   b = "透视",
   c = "Glow effect for entities",
   d = ModuleCategory.RENDER
)
public class GlowModule extends ClientModule {
   RecoveredDAA c = RecoveredDD.a(this, "Player").a(true).a().b();
   RecoveredDAA d = RecoveredDD.a(this, "Items").a(false).a().b();
   RecoveredDAA e = RecoveredDD.a(this, "Mobs").a(false).a().b();
   RecoveredDAA f = RecoveredDD.a(this, "Animals").a(false).a().b();
   RecoveredDAA g = RecoveredDD.a(this, "Arrows").a(false).a().b();

   public static boolean a(Entity var0) {
      GlowModule var1 = EixClient.a().g().a(GlowModule.class);
      if (!var1.m()) {
         return false;
      } else if (var0 instanceof Player && var1.c.m()) {
         return true;
      } else if (var0 instanceof ItemEntity && var1.d.m()) {
         return true;
      } else {
         return var0 instanceof Mob && var1.e.m() ? true : var0 instanceof Animal && var1.f.m() || var0 instanceof Arrow && var1.g.m();
      }
   }
}
