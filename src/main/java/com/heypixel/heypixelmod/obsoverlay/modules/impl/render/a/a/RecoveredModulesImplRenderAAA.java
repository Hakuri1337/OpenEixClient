package com.heypixel.heypixelmod.obsoverlay.modules.impl.render.a.a;

import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.a.RecoveredModulesImplRenderAA;
import java.awt.Color;
import java.util.Set;
import net.minecraft.world.entity.Entity;

public class RecoveredModulesImplRenderAAA implements RecoveredModulesImplRenderAA {
   private final Color a;
   private final Set<Class<?>> b;

   public RecoveredModulesImplRenderAAA(Set<Class<?>> var1) {
      this(var1, new Color(255, 255, 255));
   }

   public RecoveredModulesImplRenderAAA(Set<Class<?>> var1, Color var2) {
      this.b = var1;
      this.a = var2;
   }

   @Override
   public Color a(Object var1) {
      return this.a;
   }

   @Override
   public boolean a(Entity var1) {
      for (Class var3 : this.b) {
         if (var3.isInstance(var1)) {
            return true;
         }
      }

      return false;
   }
}
