package com.heypixel.heypixelmod.obsoverlay.modules.impl.b;

import com.heypixel.heypixelmod.mixin.O.accessors.MinecraftAccessor;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import net.minecraft.world.item.BlockItem;

@ModuleInfo(
   a = "FastPlace",
   b = "快速放置",
   c = "Place blocks faster",
   d = ModuleCategory.MISC
)
public class FastPlaceModule extends ClientModule {
   private final RecoveredDAC c = RecoveredDD.a(this, "CPS").a(10.0F).d(1.0F).b(5.0F).c(20.0F).a().c();
   private float d = 0.0F;

   @EventTarget
   public void onMotion(RecoveredEventsImplK var1) {
      if (var1.b() == RecoveredEventsApiAA.PRE) {
         MinecraftAccessor var2 = (MinecraftAccessor)a;
         if (a.options.keyUse.isDown() && a.player.getMainHandItem().getItem() instanceof BlockItem) {
            this.d = this.d + this.c.q() / 20.0F;
            if (this.d >= 1.0F / this.c.q()) {
               var2.setRightClickDelay(0);
               this.d--;
            }
         } else {
            this.d = 0.0F;
         }
      }
   }
}
