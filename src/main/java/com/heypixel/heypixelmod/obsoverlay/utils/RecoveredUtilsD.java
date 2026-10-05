package com.heypixel.heypixelmod.obsoverlay.utils;

import com.mojang.authlib.GameProfile;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.RemotePlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class RecoveredUtilsD extends RemotePlayer {
   private final AbstractClientPlayer a;

   public RecoveredUtilsD(AbstractClientPlayer var1) {
      super(Minecraft.getInstance().level, new GameProfile(UUID.randomUUID(), "Real Position"));
      this.a = var1;
      this.copyPosition(var1);
      this.noPhysics = true;
      this.yRotO = this.getYRot();
      this.xRotO = this.getXRot();
      this.yHeadRot = var1.yHeadRot;
      this.yBodyRot = var1.yBodyRot;
      this.yHeadRotO = this.yHeadRot;
      this.yBodyRotO = this.yBodyRot;
      Byte var2 = var1.getEntityData().get(Player.DATA_PLAYER_MODE_CUSTOMISATION);
      this.entityData.set(Player.DATA_PLAYER_MODE_CUSTOMISATION, var2);
   }

   public boolean a() {
      return this.a.isSkinLoaded();
   }

   @NotNull
   public ResourceLocation b() {
      return this.a.getSkinTextureLocation();
   }

   public boolean c() {
      return this.a.isCapeLoaded();
   }

   @Nullable
   public ResourceLocation d() {
      return this.a.getCloakTextureLocation();
   }
}
