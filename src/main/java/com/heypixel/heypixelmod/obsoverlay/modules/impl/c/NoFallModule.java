package com.heypixel.heypixelmod.obsoverlay.modules.impl.c;

import com.heypixel.heypixelmod.mixin.O.accessors.MultiPlayerGameModeAccessor;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAE;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplD;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsP;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsX;
import com.heypixel.heypixelmod.obsoverlay.utils.c.RecoveredUtilsCA;
import com.heypixel.heypixelmod.obsoverlay.utils.c.RecoveredUtilsCB;
import com.heypixel.heypixelmod.obsoverlay.utils.c.RecoveredUtilsCC;
import com.heypixel.heypixelmod.obsoverlay.utils.c.RecoveredUtilsCD;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.protocol.game.ServerboundUseItemPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.HitResult.Type;

@ModuleInfo(
   a = "NoFall",
   b = "免摔伤害",
   c = "Prevents fall damage",
   d = ModuleCategory.MOVEMENT
)
public class NoFallModule extends ClientModule {
   private final RecoveredDAE c = RecoveredDD.a(this, "FallDistance Mode").a("Calc", "Custom").a().e();
   private final RecoveredDAC d = RecoveredDD.a(this, "FallDistance").a(10.0F).b(2.0F).c(100.0F).d(1.0F).a(() -> "Custom".equals(this.c.l())).a().c();
   private final RecoveredDAC e = RecoveredDD.a(this, "Rotation Speed").a(180.0F).d(1.0F).b(1.0F).c(180.0F).a().c();
   private final RecoveredDAA f = RecoveredDD.a(this, "Retrieve Water").a(true).a().b();
   private static final long g = 50L;
   private boolean h = false;
   private boolean i = false;
   private boolean j = false;
   private boolean k = false;
   private int l = 0;
   private int m = -1;
   private long n = 0L;
   private BlockPos o = null;
   private RecoveredUtilsCB p = null;

   @EventTarget
   public void onMotion(RecoveredEventsImplK var1) {
      if (var1.b() == RecoveredEventsApiAA.PRE) {
         if (a.player == null || a.level == null) {
            return;
         }

         if (this.h && a.player.onGround()) {
            this.i = true;
            if (this.o != null) {
               this.p = RecoveredUtilsCD.a(this.o, Direction.UP);
            }
         }

         if (this.j || this.k || this.i) {
            RecoveredUtilsCB var2 = this.p == null ? new RecoveredUtilsCB(a.player.getYRot(), 90.0F) : this.p;
            RecoveredUtilsCC.a(var2, (double)this.e.q());
         }

         if (this.j) {
            if (this.r()) {
               this.k = true;
            }

            if (this.l > 0) {
               this.l--;
            } else {
               this.b(true);
            }

            return;
         }

         if (this.p()) {
            this.q();
         }
      }
   }

   @Override
   public void d() {
      this.b(true);
      this.n = 0L;
   }

   @Override
   public void e() {
      this.b(true);
   }

   @EventTarget
   public void onClick(RecoveredEventsImplD var1) {
      if (a.player != null && a.level != null) {
         if (this.k) {
            long var8 = System.currentTimeMillis();
            if (var8 - this.n >= 50L) {
               this.n = var8;
               this.k = false;
               HitResult var4 = this.s();
               if (var4 != null && var4.getType() == Type.BLOCK) {
                  Direction var5 = ((BlockHitResult)var4).getDirection();
                  boolean var6 = a.player.fallDistance >= 2.0F && a.player.fallDistance < 4.0F;
                  BlockPos var7 = ((BlockHitResult)var4).getBlockPos();
                  if (var5 == Direction.UP || var6 && var5 != Direction.DOWN) {
                     this.o = var7.above();
                     this.a(InteractionHand.MAIN_HAND);
                     this.h = this.f.m();
                     this.j = false;
                     return;
                  }
               }

               this.j = false;
            }
         } else {
            if (this.i && this.o != null) {
               HitResult var2 = this.s();
               if (var2 != null && var2.getType() == Type.BLOCK) {
                  BlockPos var3 = ((BlockHitResult)var2).getBlockPos().above();
                  if (var3.equals(this.o)) {
                     this.a(InteractionHand.MAIN_HAND);
                     this.i = false;
                     this.h = false;
                     this.o = null;
                     this.j = false;
                     if (this.m != -1) {
                        a.player.getInventory().selected = this.m;
                     }

                     this.m = -1;
                  }
               }
            }
         }
      }
   }

   private boolean p() {
      if (a == null || a.player == null || a.level == null) {
         return false;
      } else if (a.player.isFallFlying()) {
         return false;
      } else {
         String var1 = this.c.l();
         return "Custom".equals(var1) ? a.player.fallDistance > this.d.q() : (a.player.fallDistance - 3.0F) / 2.0F + 3.5F > a.player.getHealth() / 3.0F;
      }
   }

   private void q() {
      Integer var1 = RecoveredUtilsP.a(Items.WATER_BUCKET);
      if (var1 != null) {
         float var2 = a.player.fallDistance;
         boolean var3 = var2 >= 2.0F && var2 < 4.0F;
         double var4 = var3 ? 1.5 : 2.0;
         double var6 = a.player.getDeltaMovement().y;
         if (a(var6 * var4) || var3 && a(var6 * 1.2)) {
            if (this.m == -1) {
               this.m = a.player.getInventory().selected;
            }

            a.player.getInventory().selected = var1;
            BlockPos var8 = a.player.blockPosition().below();
            this.p = RecoveredUtilsCD.a(var8, Direction.UP);
            this.j = true;
            this.l = var3 ? 8 : 5;
         }
      }
   }

   private boolean r() {
      float var1 = a.player.fallDistance;
      boolean var2 = var1 >= 2.0F && var1 < 4.0F;
      double var3 = a.player.getDeltaMovement().y;
      return a(var3) || var2 && a(var3 * 0.8);
   }

   private static boolean a(double var0) {
      Iterable var2 = a.level.getCollisions(a.player, a.player.getBoundingBox().move(0.0, var0, 0.0));
      return var2.iterator().hasNext();
   }

   private void b(boolean var1) {
      this.j = false;
      this.k = false;
      this.l = 0;
      this.h = false;
      this.i = false;
      this.o = null;
      this.p = null;
      if (var1 && this.m != -1 && a.player != null) {
         a.player.getInventory().selected = this.m;
      }

      this.m = -1;
   }

   private void a(InteractionHand var1) {
      MultiPlayerGameModeAccessor var2 = (MultiPlayerGameModeAccessor)a.gameMode;
      if (var2 != null) {
         var2.invokeEnsureHasSentCarriedItem();
      }

      RecoveredUtilsX.a(var1x -> new ServerboundUseItemPacket(var1, var1x));
   }

   private HitResult s() {
      if (this.p != null) {
         HitResult var1 = RecoveredUtilsCA.a(this.p, 4.5);
         if (var1 != null) {
            return var1;
         }
      }

      return a.hitResult;
   }
}
