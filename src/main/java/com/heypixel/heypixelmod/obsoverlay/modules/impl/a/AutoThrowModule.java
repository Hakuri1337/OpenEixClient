package com.heypixel.heypixelmod.obsoverlay.modules.impl.a;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.b.ClientFriendModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.b.TargetModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.b.TeamsModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.c.BlinkModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.c.ScaffoldModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.c.StuckModule;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAl;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsM;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsX;
import com.heypixel.heypixelmod.obsoverlay.utils.c.RecoveredUtilsCB;
import com.heypixel.heypixelmod.obsoverlay.utils.c.RecoveredUtilsCC;
import java.util.Comparator;
import java.util.Optional;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.network.protocol.game.ServerboundSetCarriedItemPacket;
import net.minecraft.network.protocol.game.ServerboundSwingPacket;
import net.minecraft.network.protocol.game.ServerboundUseItemPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.EnderpearlItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.LingeringPotionItem;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.SplashPotionItem;
import net.minecraft.world.phys.Vec3;

@ModuleInfo(
   a = "AutoThrow",
   b = "自动投掷",
   c = "Automatically throw snowballs and eggs.",
   d = ModuleCategory.COMBAT
)
public class AutoThrowModule extends ClientModule {
   private static final double c = 0.6;
   private static final double d = 0.006;
   private final RecoveredDAC e = RecoveredDD.a(this, "Min Distance").a(5.0F).d(1.0F).b(3.0F).c(30.0F).a().c();
   private final RecoveredDAC f = RecoveredDD.a(this, "Max Distance").a(10.0F).d(1.0F).b(3.0F).c(30.0F).a().c();
   private final RecoveredDAC g = RecoveredDD.a(this, "Delay").a(500.0F).d(50.0F).b(50.0F).c(2000.0F).a().c();
   private final RecoveredUtilsAl h = new RecoveredUtilsAl();
   private RecoveredUtilsCB i;
   private int j;
   private int k = -1;
   private AutoThrowModule.InnerA l;

   @EventTarget
   public void onMotion(RecoveredEventsImplK var1) {
      if (var1.b() != RecoveredEventsApiAA.PRE) {
         if (this.k != -1) {
            a.getConnection().send(new ServerboundSetCarriedItemPacket(this.k));
            this.k = -1;
         }
      } else if (a.player != null && a.level != null) {
         if (!EixClient.a().g().a(ScaffoldModule.class).m() && !EixClient.a().g().a(StuckModule.class).m() && !EixClient.a().g().a(BlinkModule.class).m()) {
            this.i = null;
            AutoThrowModule.InnerA var2 = this.p();
            if (var2 != null) {
               if (this.j > 0) {
                  this.j--;
                  if (this.j == 0 && this.l != null) {
                     this.a(this.l);
                     this.l = null;
                  }
               } else {
                  Optional var3 = this.q();
                  if (var3.isPresent() && this.h.a((double)this.g.q()) && this.a(var2.a)) {
                     this.i = this.a((LivingEntity)var3.get());
                     RecoveredUtilsCC.a(this.i, 180.0);
                     this.j = 2;
                     this.l = var2;
                     this.h.a();
                  }
               }
            }
         } else {
            this.j = 0;
            this.l = null;
         }
      }
   }

   private void a(AutoThrowModule.InnerA var1) {
      if (var1.a == InteractionHand.MAIN_HAND) {
         int var2 = a.player.getInventory().selected;
         boolean var3 = var2 != var1.b;
         if (var3) {
            a.getConnection().send(new ServerboundSetCarriedItemPacket(var1.b));
            this.k = var2;
         }
      }

      RecoveredUtilsX.a(var1x -> new ServerboundUseItemPacket(var1.a, var1x));
      a.getConnection().send(new ServerboundSwingPacket(var1.a));
   }

   private AutoThrowModule.InnerA p() {
      ItemStack var1 = a.player.getOffhandItem();
      if (this.a(var1)) {
         return new AutoThrowModule.InnerA(InteractionHand.OFF_HAND, -1);
      } else {
         int var2 = a.player.getInventory().selected;
         ItemStack var3 = a.player.getInventory().items.get(var2);
         if (this.a(var3)) {
            return new AutoThrowModule.InnerA(InteractionHand.MAIN_HAND, var2);
         } else {
            for (int var4 = 0; var4 < 9; var4++) {
               ItemStack var5 = a.player.getInventory().items.get(var4);
               if (this.a(var5)) {
                  return new AutoThrowModule.InnerA(InteractionHand.MAIN_HAND, var4);
               }
            }

            return null;
         }
      }
   }

   private boolean a(InteractionHand var1) {
      if (a.player.isUsingItem()) {
         return false;
      } else {
         ItemStack var2 = var1 == InteractionHand.MAIN_HAND ? a.player.getMainHandItem() : a.player.getOffhandItem();
         if (var2.isEmpty()) {
            return true;
         } else {
            Item var3 = var2.getItem();
            if (var3 instanceof EnderpearlItem) {
               return false;
            } else if (var3 instanceof BowItem) {
               return false;
            } else {
               return !(var3 instanceof PotionItem) && !(var3 instanceof SplashPotionItem) && !(var3 instanceof LingeringPotionItem) ? !var3.isEdible() : false;
            }
         }
      }
   }

   private RecoveredUtilsCB a(LivingEntity var1) {
      Vec3 var2 = var1.getDeltaMovement();
      double var3 = var1.getX();
      double var5 = var1.getY() + (double)var1.getBbHeight() * 0.6;
      double var7 = var1.getZ();
      double var9 = 0.0;

      for (int var11 = 0; var11 < 3; var11++) {
         double var12 = var3 + var2.x * var9;
         double var14 = var7 + var2.z * var9;
         double var16 = var12 - a.player.getX();
         double var18 = var14 - a.player.getZ();
         double var20 = Math.sqrt(var16 * var16 + var18 * var18);
         var9 = var20 / 0.6;
      }

      double var27 = var3 + var2.x * var9;
      double var13 = var5 + var2.y * var9;
      double var15 = var7 + var2.z * var9;
      double var17 = var27 - a.player.getX();
      double var19 = var15 - a.player.getZ();
      double var21 = var13 - (a.player.getY() + (double)a.player.getEyeHeight());
      double var23 = Math.sqrt(var17 * var17 + var19 * var19);
      float var25 = (float)(Math.toDegrees(Math.atan2(var19, var17)) - 90.0);
      float var26 = -this.a((float)var23, (float)var21, 0.6F, 0.006F);
      return new RecoveredUtilsCB(var25, Mth.clamp(var26, -90.0F, 90.0F));
   }

   private float a(float var1, float var2, float var3, float var4) {
      float var5 = var3 * var3;
      float var6 = var5 * var5 - var4 * (var4 * var1 * var1 + 2.0F * var2 * var5);
      return var6 <= 0.0F
         ? (float)Math.toDegrees(Math.atan2((double)var2, (double)var1))
         : (float)Math.toDegrees(Math.atan(((double)var5 - Math.sqrt((double)var6)) / (double)(var4 * var1)));
   }

   private Optional<AbstractClientPlayer> q() {
      TargetModule var1 = EixClient.a().g().a(TargetModule.class);
      return a.level
         .players()
         .stream()
         .filter(var0 -> var0 != a.player)
         .filter(LivingEntity::isAlive)
         .filter(var0 -> !var0.isSpectator())
         .filter(var0 -> !AntiBotsModule.b(var0))
         .filter(var1x -> var1 == null || var1.a(var1x))
         .filter(var0 -> !TeamsModule.a(var0))
         .filter(var0 -> !RecoveredUtilsM.a((Entity)var0))
         .filter(var0 -> !ClientFriendModule.a(var0))
         .filter(a.player::hasLineOfSight)
         .filter(var0 -> !var0.isInvisibleTo(a.player))
         .filter(var1x -> {
            double var2 = this.b((LivingEntity)var1x);
            return var2 <= (double)this.f.q() && var2 >= (double)this.e.q();
         })
         .min(Comparator.comparingDouble(var0 -> (double)a.player.distanceTo(var0)));
   }

   private double b(LivingEntity var1) {
      double var2 = var1.getX() - a.player.getX();
      double var4 = var1.getZ() - a.player.getZ();
      return Math.sqrt(var2 * var2 + var4 * var4);
   }

   private boolean a(ItemStack var1) {
      return !var1.isEmpty() && (var1.getItem() == Items.EGG || var1.getItem() == Items.SNOWBALL);
   }

   private static class InnerA {
      private final InteractionHand a;
      private final int b;

      private InnerA(InteractionHand var1, int var2) {
         this.a = var1;
         this.b = var2;
      }
   }
}
