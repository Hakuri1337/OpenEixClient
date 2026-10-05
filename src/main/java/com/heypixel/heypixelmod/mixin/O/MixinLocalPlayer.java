package com.heypixel.heypixelmod.mixin.O;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.api.events.Event;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplAd;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplZ;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket.Pos;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket.PosRot;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket.Rot;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket.StatusOnly;
import net.minecraft.network.protocol.game.ServerboundPlayerCommandPacket.Action;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.At.Shift;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({LocalPlayer.class})
public abstract class MixinLocalPlayer extends AbstractClientPlayer {
   @Shadow
   @Final
   public ClientPacketListener connection;
   @Shadow
   @Final
   protected Minecraft minecraft;
   @Shadow
   private boolean wasSprinting;
   @Shadow
   private boolean wasShiftKeyDown;
   @Shadow
   private double xLast;
   @Shadow
   private double yLast1;
   @Shadow
   private double zLast;
   @Shadow
   private float yRotLast;
   @Shadow
   private float xRotLast;
   @Shadow
   private int positionReminder;
   @Shadow
   private boolean lastOnGround;
   @Shadow
   private boolean autoJumpEnabled;

   public MixinLocalPlayer(ClientLevel var1, GameProfile var2) {
      super(var1, var2);
   }

   @Shadow
   protected abstract boolean isControlledCamera();

   @Shadow
   protected abstract void sendIsSprintingIfNeeded();

   @Inject(
      method = {"tick"},
      at = {@At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/player/AbstractClientPlayer;tick()V",
         shift = Shift.BEFORE
      )}
   )
   public void injectUpdateEvent(CallbackInfo var1) {
      EixClient.a().b().a((Event)(new RecoveredEventsImplAd()));
   }

   @Overwrite
   private void sendPosition() {
      RecoveredEventsImplK var1 = new RecoveredEventsImplK(
         RecoveredEventsApiAA.PRE, this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot(), this.onGround()
      );
      EixClient.a().b().a((Event)var1);
      if (var1.a()) {
         EixClient.a().b().a((Event)(new RecoveredEventsImplK(RecoveredEventsApiAA.POST, var1.f(), var1.g())));
      } else {
         this.sendIsSprintingIfNeeded();
         boolean var2 = this.isShiftKeyDown();
         if (var2 != this.wasShiftKeyDown) {
            Action var3 = var2 ? Action.PRESS_SHIFT_KEY : Action.RELEASE_SHIFT_KEY;
            this.connection.send(new ServerboundPlayerCommandPacket(this, var3));
            this.wasShiftKeyDown = var2;
         }

         if (this.isControlledCamera()) {
            double var16 = var1.c() - this.xLast;
            double var5 = var1.d() - this.yLast1;
            double var7 = var1.e() - this.zLast;
            double var9 = (double)(var1.f() - this.yRotLast);
            double var11 = (double)(var1.g() - this.xRotLast);
            this.positionReminder++;
            boolean var13 = Mth.lengthSquared(var16, var5, var7) > Mth.square(2.0E-4) || this.positionReminder >= 20;
            boolean var14 = var9 != 0.0 || var11 != 0.0;
            if (this.isPassenger()) {
               Vec3 var15 = this.getDeltaMovement();
               this.connection.send(new PosRot(var15.x, -999.0, var15.z, var1.f(), var1.g(), var1.h()));
               var13 = false;
            } else if (var13 && var14) {
               this.connection.send(new PosRot(var1.c(), var1.d(), var1.e(), var1.f(), var1.g(), var1.h()));
            } else if (var13) {
               this.connection.send(new Pos(var1.c(), var1.d(), var1.e(), var1.h()));
            } else if (var14) {
               this.connection.send(new Rot(var1.f(), var1.g(), var1.h()));
            } else if (this.lastOnGround != var1.h()) {
               this.connection.send(new StatusOnly(var1.h()));
            }

            if (var13) {
               this.xLast = var1.c();
               this.yLast1 = var1.d();
               this.zLast = var1.e();
               this.positionReminder = 0;
            }

            if (var14) {
               this.yRotLast = var1.f();
               this.xRotLast = var1.g();
            }

            this.lastOnGround = var1.h();
            this.autoJumpEnabled = this.minecraft.options.autoJump().get();
         }

         EixClient.a().b().a((Event)(new RecoveredEventsImplK(RecoveredEventsApiAA.POST, var1.f(), var1.g())));
      }
   }

   @Redirect(
      method = {"aiStep"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/client/player/LocalPlayer;isUsingItem()Z",
         ordinal = 0
      )
   )
   public boolean onSlowdown(LocalPlayer var1) {
      RecoveredEventsImplZ var2 = new RecoveredEventsImplZ(var1.isUsingItem());
      EixClient.a().b().a((Event)var2);
      return var2.a();
   }
}
