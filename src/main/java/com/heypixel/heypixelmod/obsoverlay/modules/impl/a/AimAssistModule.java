package com.heypixel.heypixelmod.obsoverlay.modules.impl.a;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplQ;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.b.ClientFriendModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.b.TargetModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.b.TeamsModule;
import com.heypixel.heypixelmod.obsoverlay.utils.c.RecoveredUtilsCB;
import com.heypixel.heypixelmod.obsoverlay.utils.c.RecoveredUtilsCD;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.phys.HitResult.Type;

@ModuleInfo(
   a = "AimAssist",
   b = "瞄准辅助",
   c = "Automatically aims at targets",
   d = ModuleCategory.COMBAT
)
public class AimAssistModule extends ClientModule {
   private final RecoveredDAC c = RecoveredDD.a(this, "Speed").a(2.0F).b(1.0F).c(10.0F).d(0.1F).a().c();
   private final RecoveredDAA d = RecoveredDD.a(this, "Aim Pitch").a(false).a().b();
   private final RecoveredDAA e = RecoveredDD.a(this, "Require Swinging").a(true).a().b();
   private final RecoveredDAA f = RecoveredDD.a(this, "Sticky").a(false).a().b();
   private final RecoveredDAA g = RecoveredDD.a(this, "Require Mouse Movement").a(false).a().b();
   private final RecoveredDAA h = RecoveredDD.a(this, "Limit Items").a(false).a().b();
   private final RecoveredDAA i = RecoveredDD.a(this, "Aim Whilst on Target").a(false).a().b();
   private final RecoveredDAC j = RecoveredDD.a(this, "On Target Speed").a(1.0F).b(1.0F).c(10.0F).d(0.1F).a(this.i::m).a().c();
   private final RecoveredDAC k = RecoveredDD.a(this, "FOV").a(90.0F).b(0.0F).c(180.0F).d(1.0F).a().c();
   private float l;
   private float m;
   private LivingEntity n;
   private double o;
   private double p;

   @EventTarget
   public void onMotion(RecoveredEventsImplK var1) {
      if (var1.b() == RecoveredEventsApiAA.PRE) {
         double var2 = 4.0;
         this.l = 0.0F;
         this.m = 0.0F;
         this.n = null;
         List var4 = this.a(var2);
         if (!var4.isEmpty()) {
            this.n = (LivingEntity)var4.get(0);
            RecoveredUtilsCB var5 = RecoveredUtilsCD.a(this.n);
            float var6 = Math.abs(Mth.wrapDegrees(var5.a() - a.player.getYRot()));
            if (var6 > this.k.q()) {
               this.n = null;
            } else if (!this.h.m() || !a.player.getMainHandItem().isEmpty() && a.player.getMainHandItem().getItem() instanceof SwordItem) {
               float var7 = this.c.q();
               if (a.hitResult != null && a.hitResult.getType() == Type.ENTITY) {
                  var7 = this.j.q();
               }

               if (this.f.m()) {
                  var7 *= 10.0F;
               }

               float var8 = var7 / (float)Minecraft.getInstance().getFps() * 100.0F;
               RecoveredUtilsCB var9 = new RecoveredUtilsCB(a.player.getYRot(), a.player.getXRot());
               RecoveredUtilsCB var10 = RecoveredUtilsCD.a(var9, var5, (double)var8);
               this.l = var10.a();
               this.m = var10.b();
            } else {
               this.n = null;
            }
         }
      }
   }

   @EventTarget
   public void onRender3D(RecoveredEventsImplQ var1) {
      if (this.n != null && (this.l != 0.0F || this.m != 0.0F)) {
         double var2 = a.mouseHandler.xpos();
         double var4 = a.mouseHandler.ypos();
         boolean var6 = var2 != this.o || var4 != this.p;
         this.o = var2;
         this.p = var4;
         if (!this.g.m() || var6) {
            if (a.hitResult == null || a.hitResult.getType() != Type.ENTITY || this.i.m()) {
               if (!this.e.m() || a.options.keyAttack.isDown()) {
                  double var7 = a.options.sensitivity().get() * 0.6 + 0.2;
                  double var9 = var7 * var7 * var7 * 8.0;
                  double var11 = var9 * 0.15;
                  float var13 = (float)((double)Math.round((double)this.l / var11) * var11);
                  float var14 = (float)((double)Math.round((double)this.m / var11) * var11);
                  boolean var15 = (double)Math.abs(var13) >= var11;
                  boolean var16 = this.d.m() && (double)Math.abs(var14) >= var11;
                  if (var15 || var16) {
                     a.player.turn(var15 ? (double)var13 : 0.0, var16 ? (double)var14 : 0.0);
                  }
               }
            }
         }
      }
   }

   private List<LivingEntity> a(double var1) {
      ArrayList<LivingEntity> var3 = new ArrayList<>();
      if (a.level == null) {
         return var3;
      } else {
         for (Entity var5 : a.level.entitiesForRendering()) {
            if (var5 instanceof LivingEntity) {
               LivingEntity var6 = (LivingEntity)var5;
               if (this.a(var6) && (double)a.player.distanceTo(var6) <= var1) {
                  RecoveredUtilsCB var7 = RecoveredUtilsCD.a(var6);
                  float var8 = Math.abs(Mth.wrapDegrees(var7.a() - a.player.getYRot()));
                  if (var8 <= this.k.q()) {
                     var3.add(var6);
                  }
               }
            }
         }

         var3.sort(Comparator.comparingDouble(var0 -> {
            RecoveredUtilsCB var1x = RecoveredUtilsCD.a(var0);
            float var2 = Math.abs(Mth.wrapDegrees(var1x.a() - a.player.getYRot()));
            float var3x = Math.abs(var1x.b() - a.player.getXRot());
            return Math.sqrt((double)(var2 * var2 + var3x * var3x));
         }));
         return var3;
      }
   }

   private boolean a(LivingEntity var1) {
      if (var1 == a.player) {
         return false;
      } else if (!var1.isAlive()) {
         return false;
      } else if (!a.player.hasLineOfSight(var1)) {
         return false;
      } else if (AntiBotsModule.b(var1)) {
         return false;
      } else if (TeamsModule.a(var1)) {
         return false;
      } else if (ClientFriendModule.a(var1)) {
         return false;
      } else {
         TargetModule var2 = EixClient.a().g().a(TargetModule.class);
         return var2 != null && var2.m() ? var2.a(var1) : var1 instanceof Player;
      }
   }
}
