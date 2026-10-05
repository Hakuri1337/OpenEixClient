package com.heypixel.heypixelmod.obsoverlay.modules.impl.b;

import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.AmbientCreature;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.boss.EnderDragonPart;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;

@ModuleInfo(
   a = "Target",
   b = "目标",
   c = "Prevent attack teammates",
   d = ModuleCategory.MISC
)
public class TargetModule extends ClientModule {
   RecoveredDAA c = RecoveredDD.a(this, "Player").a(true).a().b();
   RecoveredDAA d = RecoveredDD.a(this, "Invisibles").a(true).a().b();
   RecoveredDAA e = RecoveredDD.a(this, "Animals").a(true).a().b();
   RecoveredDAA f = RecoveredDD.a(this, "Mobs").a(true).a().b();
   RecoveredDAA g = RecoveredDD.a(this, "Villager").a(true).a().b();

   @Override
   public void d() {
      this.a(false);
   }

   public boolean a(Entity var1) {
      if (!(var1 instanceof LivingEntity)) {
         return false;
      } else if (var1 == a.player) {
         return false;
      } else if (var1 instanceof Player && var1.isSpectator()) {
         return false;
      } else if (!var1.isAlive()) {
         return false;
      } else if (var1.isInvisible() && !this.d.m()) {
         return false;
      } else if (var1 instanceof Player) {
         return this.c.m();
      } else if (var1 instanceof AbstractVillager) {
         return this.g.m();
      } else if (var1 instanceof Monster || var1 instanceof Slime || var1 instanceof EnderDragon || var1 instanceof EnderDragonPart) {
         return this.f.m();
      } else {
         return !(var1 instanceof Animal) && !(var1 instanceof AmbientCreature) && !(var1 instanceof WaterAnimal) && !(var1 instanceof AbstractHorse)
            ? false
            : this.e.m();
      }
   }
}
