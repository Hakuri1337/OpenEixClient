package com.heypixel.heypixelmod.obsoverlay.modules.impl.render.a.a;

import java.awt.Color;
import java.util.Collections;
import java.util.HashSet;
import net.minecraft.world.entity.projectile.Arrow;

public class RecoveredModulesImplRenderAAB extends RecoveredModulesImplRenderAAA {
   public RecoveredModulesImplRenderAAB() {
      super(new HashSet<>(Collections.singletonList(Arrow.class)), new Color(255, 0, 0));
   }

   @Override
   public float a() {
      return 0.25F;
   }

   @Override
   public float b() {
      return 0.5F;
   }

   @Override
   public float c() {
      return 0.05F;
   }
}
