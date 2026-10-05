package com.heypixel.heypixelmod.mixin.O;

import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.NameProtectModule;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Component.Serializer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin({FriendlyByteBuf.class})
public class MixinFriendlyByteBuf {
   @Redirect(
      method = {"readComponent"},
      at = @At(
         value = "INVOKE",
         target = "Lnet/minecraft/network/chat/Component$Serializer;fromJson(Ljava/lang/String;)Lnet/minecraft/network/chat/MutableComponent;"
      )
   )
   public MutableComponent readUtf(String var1) {
      return Serializer.fromJson(NameProtectModule.b(var1));
   }
}
