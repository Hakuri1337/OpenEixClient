package com.heypixel.heypixelmod.obsoverlay.modules.impl.render.a;

import java.awt.Color;
import net.minecraft.world.entity.Entity;

public interface RecoveredModulesImplRenderAA {
   Color a(Object var1);

   default float a() {
      return 0.125F;
   }

   boolean a(Entity var1);

   default float b() {
      return 0.25F;
   }

   default float c() {
      return 0.03F;
   }
}
