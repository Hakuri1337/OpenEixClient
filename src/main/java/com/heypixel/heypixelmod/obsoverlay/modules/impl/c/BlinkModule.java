package com.heypixel.heypixelmod.obsoverlay.modules.impl.c;

import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.EventRender2D;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplM;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.a.AntiBotsModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.b.TeamsModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.a.RecoveredModulesImplRenderAA;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.a.a.RecoveredModulesImplRenderAAA;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.a.a.RecoveredModulesImplRenderAAB;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAb;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAg;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsD;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsM;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsW;
import com.heypixel.heypixelmod.obsoverlay.utils.c.RecoveredUtilsCD;
import java.awt.Color;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.player.RemotePlayer;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket;
import net.minecraft.network.protocol.handshake.ClientIntentionPacket;
import net.minecraft.network.protocol.login.ServerboundHelloPacket;
import net.minecraft.network.protocol.login.ServerboundKeyPacket;
import net.minecraft.network.protocol.status.ServerboundPingRequestPacket;
import net.minecraft.network.protocol.status.ServerboundStatusRequestPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Entity.RemovalReason;
import net.minecraft.world.entity.item.PrimedTnt;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.entity.projectile.ThrownEgg;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.HitResult.Type;

@ModuleInfo(
   a = "Blink",
   b = "瞬移",
   d = ModuleCategory.MOVEMENT,
   c = "Suspends all movement packets for teleporting!"
)
public class BlinkModule extends ClientModule {
   public static final Set<Class<?>> c = new HashSet<Class<?>>() {
      {
         this.add(ClientIntentionPacket.class);
         this.add(ServerboundStatusRequestPacket.class);
         this.add(ServerboundPingRequestPacket.class);
         this.add(ServerboundHelloPacket.class);
         this.add(ServerboundKeyPacket.class);
      }
   };
   private static final int j = new Color(150, 45, 45, 255).getRGB();
   private final RecoveredModulesImplRenderAAB k = new RecoveredModulesImplRenderAAB();
   private final RecoveredModulesImplRenderAAA l = new RecoveredModulesImplRenderAAA(Collections.singleton(ThrownEgg.class), new Color(255, 238, 154));
   private final RecoveredModulesImplRenderAAA m = new RecoveredModulesImplRenderAAA(Collections.singleton(Snowball.class), new Color(255, 255, 255));
   private final Queue<Packet<?>> n = new ConcurrentLinkedQueue<>();
   private final RecoveredUtilsAg o = new RecoveredUtilsAg(0.0F, 0.2F);
   public RecoveredDAC d = RecoveredDD.a(this, "Release Ticks on Damage").b(0.0F).c(50.0F).a(20.0F).d(1.0F).a().c();
   public RecoveredDAC e = RecoveredDD.a(this, "Release Speed (Tick)").b(3.0F).c(20.0F).a(10.0F).d(1.0F).a().c();
   public RecoveredDAC f = RecoveredDD.a(this, "Max Ticks").b(10.0F).c(200.0F).a(20.0F).d(1.0F).a().c();
   public RecoveredDAC g = RecoveredDD.a(this, "Player Distance").b(3.0F).c(10.0F).a(4.0F).d(0.1F).a().c();
   public RecoveredDAC h = RecoveredDD.a(this, "TNT Distance").b(3.0F).c(10.0F).a(5.0F).d(0.1F).a().c();
   public RecoveredDAC i = RecoveredDD.a(this, "Fake Player HitBoxes").b(0.0F).c(3.0F).a(0.2F).d(0.01F).a().c();
   private boolean p = false;
   private RemotePlayer q;
   private int r = 0;
   private int s = 0;

   public long p() {
      return this.n.stream().filter(var0 -> var0 instanceof ServerboundMovePlayerPacket).count();
   }

   private void a(ServerboundMovePlayerPacket var1) {
      this.q
         .lerpTo(
            var1.getX(this.q.getX()),
            var1.getY(this.q.getY()),
            var1.getZ(this.q.getZ()),
            var1.getYRot(this.q.getYRot()),
            var1.getXRot(this.q.getXRot()),
            3,
            false
         );
      if (var1.hasRotation()) {
         this.q.setYRot(var1.getYRot(this.q.getYRot()));
         this.q.setYHeadRot(var1.getYRot(this.q.getYRot()));
         this.q.setXRot(var1.getXRot(this.q.getXRot()));
      }
   }

   private void q() {
      while (!this.n.isEmpty()) {
         Packet var1 = this.n.poll();
         RecoveredUtilsW.a(var1);
         if (var1 instanceof ServerboundMovePlayerPacket) {
            this.s++;
            this.a((ServerboundMovePlayerPacket)var1);
            break;
         }
      }
   }

   @Override
   public void d() {
      this.n.clear();
      this.r = 0;
      this.p = false;
      this.q = new RecoveredUtilsD(a.player);
      this.q.setSprinting(a.player.isSprinting());
      a.level.addPlayer(-1337, this.q);
   }

   @Override
   public void e() {
      if (this.q != null) {
         a.level.removeEntity(this.q.getId(), RemovalReason.DISCARDED);
         this.q = null;
      }
   }

   @EventTarget
   public void onRender(EventRender2D var1) {
   }

   private boolean a(double var1) {
      long var3 = a.level.players().stream().filter(var3x -> {
         if (var3x == a.player) {
            return false;
         } else if (var3x instanceof RecoveredUtilsD) {
            return false;
         } else if (TeamsModule.a(var3x)) {
            return false;
         } else if (RecoveredUtilsM.a((Entity)var3x)) {
            return false;
         } else {
            return AntiBotsModule.b(var3x) ? false : RecoveredUtilsCD.a(this.q, var3x) < var1;
         }
      }).count();
      return var3 > 0L;
   }

   private boolean b(double var1) {
      Stream<Entity> var3 = StreamSupport.stream(a.level.entitiesForRendering().spliterator(), true);
      long var4 = var3.filter(var3x -> var3x instanceof PrimedTnt && (double)this.q.distanceTo(var3x) <= var1).count();
      return var4 > 0L;
   }

   private boolean c(double var1) {
      for (Entity var4 : a.level.entitiesForRendering()) {
         Object var5;
         if (var4 instanceof Arrow) {
            var5 = this.k;
         } else if (var4 instanceof ThrownEgg) {
            var5 = this.l;
         } else {
            if (!(var4 instanceof Snowball)) {
               continue;
            }

            var5 = this.m;
         }

         if (var5 != null && this.a(var4, (RecoveredModulesImplRenderAA)var5, var1)) {
            return true;
         }
      }

      return false;
   }

   private boolean a(Entity var1, RecoveredModulesImplRenderAA var2, double var3) {
      LocalPlayer var5 = a.player;
      ClientLevel var6 = a.level;
      double var7 = var1.getX();
      double var9 = var1.getY();
      double var11 = var1.getZ();
      double var13 = var1.getDeltaMovement().x;
      double var15 = var1.getDeltaMovement().y;
      double var17 = var1.getDeltaMovement().z;

      while (true) {
         float var19 = var2.a();
         float var20 = var2.b();
         AABB var21 = new AABB(var7 - (double)var19, var9, var11 - (double)var19, var7 + (double)var19, var9 + (double)var20, var11 + (double)var19);
         Vec3 var22 = new Vec3(var7, var9, var11);
         Vec3 var23 = new Vec3(var7 + var13, var9 + var15, var11 + var17);
         HitResult var24 = RecoveredUtilsAb.a(var22, var23, false, var1 instanceof Arrow, false, var1);
         List var25 = var6.getEntities(var5, var21.contract(var13, var15, var17).expandTowards(1.0, 1.0, 1.0).inflate(var3, var3, var3));
         if (var25.contains(this.q)) {
            return true;
         }

         var7 += var13;
         var9 += var15;
         var11 += var17;
         if (!var24.getType().equals(Type.MISS) || var9 < -128.0) {
            return false;
         }

         var13 *= var1.isInWater() ? 0.8 : 0.99;
         double var26 = var15 * (var1.isInWater() ? 0.8 : 0.99);
         var17 *= var1.isInWater() ? 0.8 : 0.99;
         var15 = var26 - (double)var2.c();
      }
   }

   private boolean r() {
      return this.b((double)this.h.q()) || this.a((double)this.g.q()) || this.c((double)this.i.q());
   }

   @Override
   public void a(boolean var1) {
      if (a.player != null) {
         if (var1) {
            super.a(true);
         } else if (!this.p) {
            this.p = true;
         } else if (this.n.isEmpty()) {
            super.a(false);
         }
      }
   }

   @EventTarget
   public void onMotion(RecoveredEventsImplK var1) {
      if (var1.b() == RecoveredEventsApiAA.PRE && a.player != null) {
         this.a(this.p() + " Ticks Behind");
         this.o.a = Mth.clamp((float)this.p() / this.f.q() * 100.0F, 0.0F, 100.0F);
         this.s = 0;
         if (a.player.hurtTime == 10) {
            this.r = this.r + (int)this.d.q();
         }

         while ((float)this.s < this.e.q() && this.r > 0 && !this.n.isEmpty()) {
            this.q();
            this.r--;
         }

         while ((float)this.s < this.e.q() && this.r() && !this.n.isEmpty()) {
            this.q();
         }

         while ((float)this.s < this.e.q() && (float)this.p() >= this.f.q() && !this.n.isEmpty()) {
            this.q();
         }

         if (this.p) {
            while ((float)this.s < this.e.q() && !this.n.isEmpty()) {
               this.q();
            }

            if (this.n.isEmpty()) {
               this.a(false);
            }
         }
      }
   }

   @EventTarget(
      a = 4
   )
   public void onPacket(RecoveredEventsImplM var1) {
      if (var1.b() == RecoveredEventsApiAA.SEND && a.player != null && !var1.a()) {
         if (c.contains(var1.c().getClass())) {
            return;
         }

         var1.a(true);
         this.n.offer(var1.c());
      }
   }
}
