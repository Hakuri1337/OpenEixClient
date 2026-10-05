package com.heypixel.heypixelmod.obsoverlay.modules.impl.a;

import com.heypixel.heypixelmod.obsoverlay.c.RecoveredCB;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAB;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAE;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.EventRunTicks;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplL;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplM;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplP;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplS;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplU;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAd;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAg;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsU;
import com.heypixel.heypixelmod.obsoverlay.utils.e.RecoveredUtilsEA;
import com.heypixel.heypixelmod.obsoverlay.utils.e.b.RecoveredUtilsEBC;
import com.heypixel.heypixelmod.obsoverlay.utils.f.RecoveredUtilsFD;
import com.mojang.blaze3d.vertex.PoseStack;
import io.github.humbleui.skija.ClipMode;
import io.github.humbleui.skija.Font;
import io.github.humbleui.skija.Path;
import io.github.humbleui.types.RRect;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundDisconnectPacket;
import net.minecraft.network.protocol.game.ClientboundMoveEntityPacket;
import net.minecraft.network.protocol.game.ClientboundPingPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerLookAtPacket;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.network.protocol.game.ClientboundRespawnPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.network.protocol.game.ClientboundSetHealthPacket;
import net.minecraft.network.protocol.game.ClientboundTeleportEntityPacket;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

@ModuleInfo(
   a = "Velocity",
   b = "反击退",
   c = "Reduces knockback.",
   d = ModuleCategory.COMBAT
)
public class VelocityModule extends ClientModule {
   private final RecoveredDAE d = RecoveredDD.a(this, "Mode").a("Legit", "Reduce", "NoXZ").a(0).a().e();
   private final Queue<Packet<? super ClientPacketListener>> e = new ConcurrentLinkedQueue<>();
   private final RecoveredDAC f = RecoveredDD.a(this, "AttackCounts").a(3.0F).d(1.0F).b(1.0F).c(5.0F).a(() -> this.d.a("NoXZ")).a().c();
   private final RecoveredDAC g = RecoveredDD.a(this, "MaxAlinkTime (ms)").a(5000.0F).d(50.0F).b(50.0F).c(10000.0F).a(() -> this.d.a("NoXZ")).a().c();
   private final RecoveredDAC h = RecoveredDD.a(this, "Horizontal %").a(35.0F).d(5.0F).b(0.0F).c(100.0F).a(() -> this.d.a("Reduce")).a().c();
   private final RecoveredDAC i = RecoveredDD.a(this, "Vertical %").a(100.0F).d(5.0F).b(0.0F).c(100.0F).a(() -> this.d.a("Reduce")).a().c();
   private final RecoveredDAC j = RecoveredDD.a(this, "Jump Window (ms)").a(280.0F).d(20.0F).b(60.0F).c(600.0F).a(() -> this.d.a("Legit")).a().c();
   private final RecoveredDAB k = RecoveredDD.a(this, "AlinkHUD Position").e(0.0F).f(0.0F).a().f();
   private final RecoveredUtilsAg l = new RecoveredUtilsAg(1.0F, 0.0F, 0.2F);
   private final RecoveredUtilsAg m = new RecoveredUtilsAg(0.0F, 0.0F, 0.12F);
   private final Map<Entity, RecoveredUtilsFD> n = new HashMap<>();
   public boolean c;
   private boolean o;
   private long p;
   private Vec3 q;
   private long r;
   private long s;
   private Player t;
   private VelocityModule.InnerA u;
   private boolean v;
   private static final int w = 64;

   private void a(VelocityModule.InnerA var1) {
      this.u = var1;
      this.s = System.currentTimeMillis();
   }

   private boolean a(long var1) {
      return System.currentTimeMillis() - this.s > var1;
   }

   @EventTarget
   public void onPacket(RecoveredEventsImplM var1) {
      String var2 = this.d.l();
      switch (var2) {
         case "Legit":
            if (var1.c() instanceof ClientboundSetEntityMotionPacket var16 && var16.getId() == a.player.getId()) {
               this.o = true;
               this.p = System.currentTimeMillis();
            }
            break;
         case "Reduce":
            if (var1.c() instanceof ClientboundSetEntityMotionPacket var15 && var15.getId() == a.player.getId()) {
               double var21 = (double)this.h.q() / 100.0;
               double var24 = (double)this.i.q() / 100.0;
               if (var21 >= 0.999 && var24 >= 0.999) {
                  return;
               }

               Vec3 var9 = new Vec3((double)var15.getXa() / 8000.0 * var21, (double)var15.getYa() / 8000.0 * var24, (double)var15.getZa() / 8000.0 * var21);
               var1.a(new ClientboundSetEntityMotionPacket(var15.getId(), var9));
            }
            break;
         case "NoXZ":
            if (var1.c() instanceof ClientboundPlayerPositionPacket && this.u == VelocityModule.InnerA.NONE) {
               this.c = true;
               return;
            }

            if (var1.c() instanceof ClientboundSetEntityMotionPacket var4 && var4.getId() == a.player.getId()) {
               if (this.u == VelocityModule.InnerA.NONE) {
                  if (!this.c) {
                     this.a(VelocityModule.InnerA.DELAY);
                     this.r = System.currentTimeMillis();
                     var1.a(true);
                     this.q = new Vec3((double)var4.getXa() / 8000.0, (double)var4.getYa() / 8000.0, (double)var4.getZa() / 8000.0);
                  } else {
                     this.c = false;
                  }

                  return;
               }

               this.q = new Vec3((double)var4.getXa() / 8000.0, (double)var4.getYa() / 8000.0, (double)var4.getZa() / 8000.0);
               this.a(VelocityModule.InnerA.LAG);
               var1.a(true);
               return;
            }

            if (this.u != VelocityModule.InnerA.NONE && var1.b() == RecoveredEventsApiAA.RECEIVE) {
               Packet var14 = var1.c();
               if (var14 instanceof ClientboundPlayerPositionPacket) {
                  while (!this.e.isEmpty()) {
                     Packet var19 = this.e.poll();
                     if (var19 != null && a.getConnection() != null) {
                        var19.handle(a.getConnection());
                     }
                  }

                  this.b(false);
                  return;
               }

               if (var14 instanceof ClientboundPlayerLookAtPacket) {
                  this.a(VelocityModule.InnerA.LAG);
                  return;
               }

               if (var14 instanceof ClientboundDisconnectPacket || var14 instanceof ClientboundRespawnPacket) {
                  this.b(false);
                  return;
               }

               if (!(var14 instanceof ClientboundPingPacket)
                  && !(var14 instanceof ClientboundMoveEntityPacket)
                  && !(var14 instanceof ClientboundTeleportEntityPacket)
                  && !(var14 instanceof ClientboundSetEntityMotionPacket)
                  && !(var14 instanceof ClientboundSetHealthPacket)) {
                  return;
               }

               if (var14 instanceof ClientboundMoveEntityPacket var17) {
                  Entity var6 = var17.getEntity(a.level);
                  if (var6 != null) {
                     RecoveredUtilsFD var7 = this.n.getOrDefault(var6, new RecoveredUtilsFD(var6.getX(), var6.getY(), var6.getZ()));
                     if (var17.hasPosition()) {
                        double var8 = (double)var17.getXa() / 4096.0;
                        double var10 = (double)var17.getYa() / 4096.0;
                        double var12 = (double)var17.getZa() / 4096.0;
                        this.n.put(var6, new RecoveredUtilsFD(var7.b() + var8, var7.c() + var10, var7.d() + var12));
                     }
                  }
               }

               if (var14 instanceof ClientboundTeleportEntityPacket var18) {
                  Entity var23 = a.level.getEntity(var18.getId());
                  if (var23 != null) {
                     this.n.put(var23, new RecoveredUtilsFD(var18.getX(), var18.getY(), var18.getZ()));
                  }
               }

               if (this.e.size() >= 64) {
                  this.b(false);
                  return;
               }

               this.e.add(var14);
               var1.a(true);
            }
      }
   }

   @EventTarget
   public void onRespawn(RecoveredEventsImplU var1) {
      this.b(true);
      if (this.m()) {
         this.a(false);
      }
   }

   @EventTarget
   public void onPreTick(EventRunTicks var1) {
      if (a.player != null) {
         if (var1.type() == RecoveredEventsApiAA.POST && this.u == VelocityModule.InnerA.CLEAR) {
            this.b(true);
         }

         if (var1.type() == RecoveredEventsApiAA.POST && this.u == VelocityModule.InnerA.LAG) {
            a.player.setDeltaMovement(this.q.x, this.q.y, this.q.z);
            this.b(true);
         }

         if (var1.type() == RecoveredEventsApiAA.PRE) {
            String var2 = this.d.l();
            switch (var2) {
               case "NoXZ":
                  if (this.u != VelocityModule.InnerA.ATTACK) {
                     if (this.u == VelocityModule.InnerA.DELAY && (float)(System.currentTimeMillis() - this.r) >= this.g.q()) {
                        a.player.setDeltaMovement(this.q.x, this.q.y, this.q.z);
                        this.a(VelocityModule.InnerA.CLEAR);
                     }
                  } else {
                     label94: {
                        if (a.hitResult instanceof EntityHitResult var4 && var4.getEntity() instanceof Player var5 && !AntiBotsModule.b(var5)) {
                           double var10 = 1.0;

                           for (int var8 = 0; var8 < (int)this.f.q(); var8++) {
                              if (a.player.isSprinting()) {
                                 a.player.setSprinting(false);
                              }

                              a.gameMode.attack(a.player, this.t);
                              a.player.swing(InteractionHand.MAIN_HAND);
                              var10 *= 0.6;
                           }

                           a.player.setDeltaMovement(this.q.x * var10, this.q.y, this.q.z * var10);
                           this.a(VelocityModule.InnerA.CLEAR);
                           break label94;
                        }

                        if (this.a(500L)) {
                           a.player.setDeltaMovement(this.q.x, this.q.y, this.q.z);
                           this.a(VelocityModule.InnerA.CLEAR);
                        }
                     }
                  }

                  if (this.c && a.player.hurtTime == 0) {
                     this.c = false;
                  }
                  break;
               case "Legit":
                  if (this.o && System.currentTimeMillis() - this.p > (long)this.j.q()) {
                     this.o = false;
                  }
            }

            this.a(this.d.l() + (this.u == VelocityModule.InnerA.DELAY ? " Alink " + (System.currentTimeMillis() - this.r) / 50L + "Ticks" : ""));
         }
      }
   }

   public void b(boolean var1) {
      this.c = false;
      this.u = VelocityModule.InnerA.NONE;
      this.n.clear();
      this.t = null;
      if (!var1) {
         this.e.clear();
      } else {
         while (!this.e.isEmpty()) {
            Packet var2 = this.e.poll();
            if (var2 != null && a.getConnection() != null) {
               var2.handle(a.getConnection());
            }
         }
      }
   }

   public boolean p() {
      return this.d.a("NoXZ") && this.u != VelocityModule.InnerA.NONE;
   }

   @Override
   public void d() {
      this.o = false;
      this.p = 0L;
      this.c = false;
      this.n.clear();
      this.t = null;
      this.e.clear();
      this.q = null;
      this.a(VelocityModule.InnerA.NONE);
   }

   @EventTarget
   public void onMoveInput(RecoveredEventsImplL var1) {
      String var2 = this.d.l();
      switch (var2) {
         case "NoXZ":
            if (this.u == VelocityModule.InnerA.DELAY
               && this.q != null
               && a.hitResult instanceof EntityHitResult var4
               && var4.getEntity() instanceof Player var5
               && !AntiBotsModule.b(var5)) {
               var1.a(1.0F);
               var1.b(0.0F);
               this.a(VelocityModule.InnerA.ATTACK);
               this.t = var5;
            }
            break;
         case "Legit":
            if (this.o) {
               if (a.player.onGround() && RecoveredUtilsU.a()) {
                  var1.a(true);
               }

               this.o = false;
            }
      }
   }

   @Override
   public void e() {
      this.o = false;
      this.c = false;
      this.n.clear();
      this.t = null;
      this.q = null;
      this.b(true);
      this.a(VelocityModule.InnerA.NONE);
   }

   @EventTarget
   public void onRender(RecoveredEventsImplP var1) {
      if (this.u != VelocityModule.InnerA.NONE) {
         PoseStack var2 = var1.b();

         for (Entity var4 : this.n.keySet()) {
            if (var4 instanceof Player) {
               RecoveredUtilsFD var5 = this.n.get(var4);
               if (var4.equals(this.t)) {
                  RecoveredUtilsAd.a(var2, var5.b(), var5.c(), var5.d(), var4.getBbWidth(), var4.getBbHeight(), new Color(200, 0, 0, 60).getRGB());
               } else {
                  RecoveredUtilsAd.a(var2, var5.b(), var5.c(), var5.d(), var4.getBbWidth(), var4.getBbHeight(), new Color(0, 200, 0, 60).getRGB());
               }
            }
         }
      }
   }

   @EventTarget
   public void onRenderSkia(RecoveredEventsImplS var1) {
      boolean var2 = a.screen instanceof RecoveredCB;
      boolean var3 = this.d.a("NoXZ");
      boolean var4 = this.u != null && this.u != VelocityModule.InnerA.NONE;
      boolean var5 = var2 || this.m() && var3;
      this.l.a = 1.0F;
      this.l.a(var5);
      if (!(this.l.c <= 0.01F)) {
         Font var6 = RecoveredUtilsEBC.a(10.0F);
         long var7 = System.currentTimeMillis();
         long var9 = (long)this.g.q();
         long var11 = var4 && this.u == VelocityModule.InnerA.DELAY ? Math.max(0L, var7 - this.r) : 0L;
         float var13;
         if (var2) {
            var13 = 0.6F;
         } else if (var9 <= 0L) {
            var13 = 0.0F;
         } else if (var4 && this.u == VelocityModule.InnerA.DELAY) {
            var13 = Math.min(1.0F, (float)var11 / (float)var9);
         } else if (var4 && this.u == VelocityModule.InnerA.ATTACK) {
            var13 = 1.0F;
         } else {
            var13 = 0.0F;
         }

         this.m.a = var13;
         this.m.a(true);
         String var14 = "Alink";
         float var15 = RecoveredUtilsEA.a(var14, var6);
         float var16 = 32.0F;
         float var17 = 120.0F;
         float var18 = Math.max(var17, var15 + 20.0F);
         float var19 = 10.0F;
         if (!this.v && this.k.n() == this.k.l() && this.k.o() == this.k.m()) {
            float var20 = (float)a.getWindow().getGuiScaledWidth();
            float var21 = (float)a.getWindow().getGuiScaledHeight();
            float var22 = var20 / 2.0F - var18 / 2.0F;
            float var23 = var21 - 55.0F;
            this.k.a(var22, var23);
            this.v = true;
         }

         float var36 = this.k.n();
         float var37 = this.k.o();
         int var38 = Math.min(255, Math.max(0, (int)(80.0F * this.l.c)));
         Color var39 = new Color(0, 0, 0, var38);
         RecoveredUtilsEA.c(var36, var37, var18, var16, var19);
         RecoveredUtilsEA.a(var36, var37, var18, var16, var19);
         RecoveredUtilsEA.a(var36, var37, var18, var16, var19, var39);
         float var24 = 5.0F;
         float var25 = 6.0F;
         float var26 = var36 + var24;
         float var27 = var37 + var16 - var24 - var25;
         float var28 = var18 - var24 * 2.0F;
         float var29 = var28 * this.m.c;
         float var30 = var25 / 2.0F;
         Path var31 = new Path();
         var31.addRRect(RRect.makeXYWH(var26, var27, var28, var25, var30));
         RecoveredUtilsEA.c();
         RecoveredUtilsEA.e().clipPath(var31, ClipMode.INTERSECT, true);
         RecoveredUtilsEA.a(var26, var27, var28, var25, var30, new Color(255, 255, 255, 50));
         if (var29 > 0.5F) {
            RecoveredUtilsEA.a(var26, var27, var29, var25, var30, new Color(196, 128, 224, 220));
         }

         RecoveredUtilsEA.d();
         var31.close();
         float var32 = var36 + var18 / 2.0F;
         float var33 = var37 + 7.0F;
         RecoveredUtilsEA.d(var14, var32, var33, new Color(255, 255, 255, (int)(220.0F * this.l.c)), var6);
         String var34 = Math.round(this.m.c * 100.0F) + "%";
         float var35 = var37 + 17.0F;
         RecoveredUtilsEA.d(var34, var36 + var18 / 2.0F, var35, new Color(255, 255, 255, (int)(230.0F * this.l.c)), var6);
         this.k.c(var18);
         this.k.d(var16);
      }
   }

   private static enum InnerA {
      NONE,
      DELAY,
      ATTACK,
      CLEAR,
      LAG;
   }
}
