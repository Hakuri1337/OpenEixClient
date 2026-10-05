package com.heypixel.heypixelmod.obsoverlay.events.impl;

import net.minecraft.client.Options;
import net.minecraft.client.player.Input;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RecoveredEventsImplA extends Input {
   private final Options a;
   private boolean b;

   public RecoveredEventsImplA(Options var1) {
      this.a = var1;
      this.b = false;
   }

   public void a(boolean var1) {
      this.up = this.a.keyUp.isDown();
      this.down = this.a.keyDown.isDown();
      this.left = this.a.keyLeft.isDown();
      this.right = this.a.keyRight.isDown();
      this.forwardImpulse = this.up == this.down ? 0.0F : (this.up ? 1.0F : -1.0F);
      this.leftImpulse = this.left == this.right ? 0.0F : (this.left ? 1.0F : -1.0F);
      this.jumping = this.a.keyJump.isDown();
      this.shiftKeyDown = this.a.keyShift.isDown();
      if (var1) {
         this.leftImpulse = (float)((double)this.leftImpulse * 0.3);
         this.forwardImpulse = (float)((double)this.forwardImpulse * 0.3);
      }

      if (this.b) {
         super.leftImpulse *= 5.0F;
         super.forwardImpulse *= 5.0F;
      }
   }

   public void b(boolean var1) {
      this.b = var1;
   }
}
