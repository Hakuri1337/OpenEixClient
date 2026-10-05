package com.heypixel.heypixelmod.obsoverlay.modules.impl.c;

import com.heypixel.heypixelmod.mixin.O.accessors.MinecraftAccessor;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplL;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAk;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsS;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.BlockItem;

@ModuleInfo(
   a = "Eagle",
   b = "安全蹲搭",
   c = "Legit trick to build faster. Auto-sneak near edges.",
   d = ModuleCategory.MOVEMENT
)
public class EagleModule extends ClientModule {
   private final RecoveredDAC c = RecoveredDD.a(this, "MinDelay (Ticks)").a(2.0F).d(1.0F).b(0.0F).c(10.0F).a().c();
   private final RecoveredDAC d = RecoveredDD.a(this, "MaxDelay (Ticks)").a(3.0F).d(1.0F).b(0.0F).c(10.0F).a().c();
   private final RecoveredDAA e = RecoveredDD.a(this, "OnlyBackwards").a(false).a().b();
   private final RecoveredDAA f = RecoveredDD.a(this, "OnlyWithBlocks").a(false).a().b();
   private final RecoveredDAA g = RecoveredDD.a(this, "FastPlace").a(false).a().b();
   private final RecoveredDAC h = RecoveredDD.a(this, "CPS").a(10.0F).d(1.0F).b(5.0F).c(20.0F).a(this.g::m).a().c();
   private static final RecoveredUtilsAk i = new RecoveredUtilsAk();
   private float j = 0.0F;
   private boolean k;
   private float l = 0.0F;

   public static boolean a(float var0) {
      return !a.level
         .getCollisions(a.player, a.player.getBoundingBox().move(0.0, -0.5, 0.0).inflate((double)(-var0), 0.0, (double)(-var0)))
         .iterator()
         .hasNext();
   }

   @EventTarget
   public void onMoveInput(RecoveredEventsImplL var1) {
      LocalPlayer var2 = a.player;
      if (var2 != null) {
         if (var1.a() != 0.0F) {
            this.j = var1.a();
         }

         if (this.e.m() && this.j > 0.0F) {
            this.k = false;
         } else if (!this.f.m() || !a.player.getMainHandItem().isEmpty() && a.player.getMainHandItem().getItem() instanceof BlockItem) {
            this.k = true;
            if (var2.onGround()) {
               boolean var3 = a(0.3F);
               if (!var2.getAbilities().flying && var3) {
                  i.b();
               }

               if (!i.a(RecoveredUtilsS.a((int)this.d.q(), (int)this.c.q()))) {
                  var1.b(true);
               }
            }
         } else {
            this.k = false;
         }
      }
   }

   @EventTarget
   public void onMotion(RecoveredEventsImplK var1) {
      if (this.k || !i.a(RecoveredUtilsS.a((int)this.d.q(), (int)this.c.q()))) {
         if (var1.b() == RecoveredEventsApiAA.PRE) {
            MinecraftAccessor var2 = (MinecraftAccessor)a;
            if (a.options.keyUse.isDown() && a.player.getMainHandItem().getItem() instanceof BlockItem) {
               this.l = this.l + this.h.q() / 20.0F;
               if (this.l >= 1.0F / this.h.q()) {
                  var2.setRightClickDelay(0);
                  this.l--;
               }
            } else {
               this.l = 0.0F;
            }
         }
      }
   }

   @Override
   public void d() {
      this.j = 0.0F;
   }

   @Override
   public void e() {
      this.j = 0.0F;
   }
}
