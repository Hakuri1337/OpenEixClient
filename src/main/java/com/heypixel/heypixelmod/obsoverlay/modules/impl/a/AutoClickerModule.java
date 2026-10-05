package com.heypixel.heypixelmod.obsoverlay.modules.impl.a;

import com.heypixel.heypixelmod.mixin.O.accessors.MinecraftAccessor;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsS;
import net.minecraft.client.KeyMapping;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.phys.HitResult.Type;

@ModuleInfo(
   a = "AutoClicker",
   b = "自动点击",
   c = "Automatically clicks for you",
   d = ModuleCategory.COMBAT
)
public class AutoClickerModule extends ClientModule {
   private final RecoveredDAC c = RecoveredDD.a(this, "Min CPS").a(12.0F).d(1.0F).b(1.0F).c(20.0F).a().c();
   private final RecoveredDAC d = RecoveredDD.a(this, "Max CPS").a(18.0F).d(1.0F).b(1.0F).c(20.0F).a().c();
   private final RecoveredDAA e = RecoveredDD.a(this, "Item Check").a(false).a().b();
   private long f = 0L;

   @EventTarget
   public void onMotion(RecoveredEventsImplK var1) {
      if (var1.b() == RecoveredEventsApiAA.PRE) {
         MinecraftAccessor var2 = (MinecraftAccessor)a;
         Item var3 = a.player.getMainHandItem().getItem();
         if (a.options.keyAttack.isDown() && (var3 instanceof SwordItem || var3 instanceof AxeItem || !this.e.m()) && a.hitResult.getType() != Type.BLOCK) {
            long var4 = System.currentTimeMillis();
            double var6 = 1000.0 / (double)RecoveredUtilsS.a((int)this.c.q(), (int)this.d.q());
            long var8 = (long)(var6 + (Math.random() - 0.5) * var6 * 0.4);
            if (var4 - this.f >= var8) {
               var2.setMissTime(0);
               KeyMapping.click(a.options.keyAttack.getKey());
               this.f = var4;
            }
         }
      }
   }
}
