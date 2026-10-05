package com.heypixel.heypixelmod.mixin.O;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.events.api.events.Event;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplL;
import net.minecraft.client.player.Input;
import net.minecraft.client.player.KeyboardInput;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({KeyboardInput.class})
public class MixinKeyboardInput extends Input {
   @Inject(
      at = {@At("TAIL")},
      method = {"tick"}
   )
   private void onTickTail(boolean var1, float var2, CallbackInfo var3) {
      this.forwardImpulse = this.up == this.down ? 0.0F : (this.up ? 1.0F : -1.0F);
      this.leftImpulse = this.left == this.right ? 0.0F : (this.left ? 1.0F : -1.0F);
      RecoveredEventsImplL var4 = new RecoveredEventsImplL(this.forwardImpulse, this.leftImpulse, this.jumping, this.shiftKeyDown, 0.3);
      EixClient.a().b().a((Event)var4);
      double var5 = var4.e();
      this.forwardImpulse = var4.a();
      this.leftImpulse = var4.b();
      this.jumping = var4.c();
      this.shiftKeyDown = var4.d();
      if (var1) {
         this.leftImpulse = (float)((double)this.leftImpulse * var5);
         this.forwardImpulse = (float)((double)this.forwardImpulse * var5);
      }
   }
}
