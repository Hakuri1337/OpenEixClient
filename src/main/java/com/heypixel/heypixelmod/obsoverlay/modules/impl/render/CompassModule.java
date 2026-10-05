package com.heypixel.heypixelmod.obsoverlay.modules.impl.render;

import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.EventRender2D;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplP;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAd;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsD;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsP;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;

@ModuleInfo(
   a = "Compass",
   b = "指南针",
   c = "Shows a compass",
   d = ModuleCategory.RENDER
)
public class CompassModule extends ClientModule {
   public RecoveredDAA c = RecoveredDD.a(this, "Compass Only").a(true).a().b();
   public RecoveredDAA d = RecoveredDD.a(this, "No Player Only").a(true).a().b();
   private boolean e = false;
   private BlockPos f;
   private float g;
   private double h;
   private double i;

   private BlockPos a(ClientLevel var1) {
      return var1.dimensionType().natural() ? var1.getSharedSpawnPos() : null;
   }

   private boolean p() {
      for (Entity var2 : a.level.entitiesForRendering()) {
         if (var2 != a.player && !(var2 instanceof RecoveredUtilsD) && var2 instanceof Player) {
            return true;
         }
      }

      return false;
   }

   @EventTarget
   public void onMotion(RecoveredEventsImplK var1) {
      if (var1.b() == RecoveredEventsApiAA.PRE) {
         this.e = RecoveredUtilsP.c(Items.COMPASS);
         this.f = this.a(a.level);
      }
   }

   @EventTarget
   public void onRender(RecoveredEventsImplP var1) {
      this.h = Mth.lerp((double)var1.a(), a.player.xOld, a.player.getX());
      this.i = Mth.lerp((double)var1.a(), a.player.zOld, a.player.getZ());
      this.g = Mth.lerp(var1.a(), a.player.yRotO, a.player.getYRot());
   }

   @EventTarget(
      a = 4
   )
   public void onRender2D(EventRender2D var1) {
      this.a(var1.stack());
   }

   private void a(PoseStack var1) {
      if ((this.e || !this.c.m()) && (!this.p() || !this.d.m()) && this.f != null) {
         float var2 = (float)(Math.toDegrees(Math.atan2((double)this.f.getZ() - this.i, (double)this.f.getX() - this.h)) - 90.0 - (double)this.g);
         float var3 = (float)a.getWindow().getGuiScaledWidth() / 2.0F;
         float var4 = (float)a.getWindow().getGuiScaledHeight() / 2.0F;
         var1.pushPose();
         var1.translate(var3, var4, 0.0F);
         var1.mulPose(Axis.ZP.rotationDegrees(var2));
         var1.translate(-var3, -var4, 0.0F);
         RecoveredUtilsAd.a(var1, var3, var4 - 45.0F, 10.0F, 2.0F, 1.0F, -1);
         var1.translate(var3, var4, 0.0F);
         var1.mulPose(Axis.ZP.rotationDegrees(-var2));
         var1.translate(-var3, -var4, 0.0F);
         var1.popPose();
      }
   }
}
