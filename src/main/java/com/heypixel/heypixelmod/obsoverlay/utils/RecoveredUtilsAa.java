package com.heypixel.heypixelmod.obsoverlay.utils;

import com.heypixel.heypixelmod.mixin.O.accessors.GameRendererAccessor;
import com.heypixel.heypixelmod.obsoverlay.utils.f.RecoveredUtilsFB;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class RecoveredUtilsAa {
   private static final Minecraft a = Minecraft.getInstance();

   public static RecoveredUtilsFB a(double var0, double var2, double var4, float var6) {
      Vec3 var7 = a.getEntityRenderDispatcher().camera.getPosition();
      Quaternionf var8 = new Quaternionf(a.getEntityRenderDispatcher().cameraOrientation());
      var8.conjugate();
      Vector3f var9 = new Vector3f((float)(var7.x - var0), (float)(var7.y - var2), (float)(var7.z - var4));
      var9.rotate(var8);
      if (a.options.bobView().get() && a.getCameraEntity() instanceof Player var10) {
         a(var10, var9, var6);
      }

      double var12 = ((GameRendererAccessor)a.gameRenderer).invokeGetFov(a.getEntityRenderDispatcher().camera, var6, true);
      return a(var9, var12);
   }

   private static void a(Player var0, Vector3f var1, float var2) {
      float var3 = var0.walkDist;
      float var4 = var3 - var0.walkDistO;
      float var5 = -(var3 + var4 * var2);
      float var6 = Mth.lerp(var2, var0.oBob, var0.bob);
      Quaternionf var7 = new Quaternionf().rotationX(Math.abs(Mth.cos(var5 * (float) Math.PI - 0.2F) * var6) * 5.0F * (float) (Math.PI / 180.0));
      var7.conjugate();
      var1.rotate(var7);
      Quaternionf var8 = new Quaternionf().rotationZ(Mth.sin(var5 * (float) Math.PI) * var6 * 3.0F * (float) (Math.PI / 180.0));
      var8.conjugate();
      var1.rotate(var8);
      Vector3f var9 = new Vector3f(Mth.sin(var5 * (float) Math.PI) * var6 * 0.5F, -Math.abs(Mth.cos(var5 * (float) Math.PI) * var6), 0.0F);
      var9.y = -var9.y;
      var1.add(var9);
   }

   private static RecoveredUtilsFB a(Vector3f var0, double var1) {
      float var3 = (float)a.getWindow().getGuiScaledHeight() / 2.0F;
      float var4 = var3 / (var0.z() * (float)Math.tan(Math.toRadians(var1 / 2.0)));
      return var0.z() < 0.0F
         ? new RecoveredUtilsFB(
            -var0.x() * var4 + (float)a.getWindow().getGuiScaledWidth() / 2.0F, (float)a.getWindow().getGuiScaledHeight() / 2.0F - var0.y() * var4
         )
         : new RecoveredUtilsFB(Float.MAX_VALUE, Float.MAX_VALUE);
   }
}
