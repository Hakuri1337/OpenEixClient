package com.heypixel.heypixelmod.obsoverlay.modules.impl.c;

import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.EventRender2D;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplAd;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplM;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsF;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsU;
import com.heypixel.heypixelmod.obsoverlay.utils.c.RecoveredUtilsCB;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundPlayerPositionPacket;
import net.minecraft.network.protocol.game.ClientboundSetEntityMotionPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.lwjgl.glfw.GLFW;

@ModuleInfo(
   a = "LongJump",
   b = "跳远冠军",
   d = ModuleCategory.MOVEMENT,
   c = "Allows you to use fireball longjump"
)
public class LongJumpModule extends ClientModule {
   public static RecoveredUtilsCB c = null;
   private final List<Integer> d = new ArrayList<>();
   private final LinkedBlockingQueue<Packet<?>> e = new LinkedBlockingQueue<>();
   private boolean f = false;
   private boolean g = false;
   private int h = 0;
   private int i = -1;
   private boolean j = false;
   private boolean k = false;
   private boolean l = false;
   private boolean m = false;
   private boolean n = false;
   private long o = 0L;
   private int p = 0;
   private int q = 0;
   private int r = 0;
   private int s = 0;

   private void p() {
      while (!this.e.isEmpty()) {
         try {
            Packet var1 = this.e.poll();
            if (var1 != null && a.getConnection() != null) {
               var1.handle(a.getConnection());
            }
         } catch (Exception var2) {
            var2.printStackTrace();
         }
      }
   }

   private void c(int var1) {
      if (var1 < this.d.size()) {
         int var2 = this.d.get(var1);
         int var3 = 0;

         while (!this.e.isEmpty() && var3 <= var2) {
            try {
               Packet var4 = this.e.poll();
               if (var4 != null && a.getConnection() != null) {
                  var4.handle(a.getConnection());
               }

               var3++;
            } catch (Exception var5) {
               var5.printStackTrace();
            }
         }

         for (int var6 = var1 + 1; var6 < this.d.size(); var6++) {
            this.d.set(var6, this.d.get(var6) - (var2 + 1));
         }
      }
   }

   private int q() {
      for (int var1 = 0; var1 < 9; var1++) {
         ItemStack var2 = a.player.getInventory().getItem(var1);
         if (!var2.isEmpty() && var2.getItem() == Items.FIRE_CHARGE) {
            return var1;
         }
      }

      return -1;
   }

   private int r() {
      int var1 = 0;

      for (int var2 = 0; var2 < 9; var2++) {
         ItemStack var3 = a.player.getInventory().getItem(var2);
         if (var3.getItem() == Items.FIRE_CHARGE) {
            var1 += var3.getCount();
         }
      }

      return var1;
   }

   private int s() {
      int var1 = this.q();
      if (var1 == -1) {
         RecoveredUtilsF.a("§cNo FireBall!");
         this.a(false);
      }

      return var1;
   }

   @Override
   public void d() {
      this.p();
      this.h = 0;
      this.g = true;
      this.i = -1;
      this.f = false;
      this.j = false;
      this.l = false;
      c = null;
      this.k = false;
      this.m = false;
      this.n = false;
      this.o = 0L;
      this.p = 0;
      this.q = 0;
      this.r = 0;
      this.s = 0;
      this.d.clear();
      RecoveredUtilsF.a("§aLongJump enabled! Press Mouse4 to jump & use fireball, Mouse5 to release each knockback");
   }

   @Override
   public void e() {
      this.p();
      if (this.i != -1 && a.player != null) {
         a.player.getInventory().selected = this.i;
      }

      a.options.keyUse.setDown(false);
      a.options.keyJump.setDown(false);
      c = null;
      this.l = false;
      this.k = false;
      this.m = false;
      this.n = false;
      this.o = 0L;
      this.p = 0;
      this.q = 0;
      this.r = 0;
      this.s = 0;
      this.d.clear();
      super.e();
   }

   @EventTarget
   public void onUpdate(RecoveredEventsImplAd var1) {
      if (this.m()) {
         if (this.k) {
            this.a(false);
         } else {
            if (this.g) {
               if (!RecoveredUtilsU.a()) {
                  this.f = true;
               }

               this.g = false;
            }

            boolean var2 = GLFW.glfwGetMouseButton(a.getWindow().getWindow(), 3) == 1;
            if (var2 && !this.m) {
               this.m = true;
               if (!this.l && this.h == 0) {
                  int var3 = this.s();
                  if (var3 != -1) {
                     this.i = a.player.getInventory().selected;
                     a.player.getInventory().selected = var3;
                     this.h = 1;
                     RecoveredUtilsF.a("§eStarting fireball usage #" + (this.p + 1));
                  }
               }
            } else if (!var2) {
               this.m = false;
            }

            boolean var4 = GLFW.glfwGetMouseButton(a.getWindow().getWindow(), 4) == 1;
            if (var4 && !this.n) {
               this.n = true;
               if (this.j && this.s < this.q) {
                  RecoveredUtilsF.a("§aReleasing " + (this.s + 1) + "/" + this.q);
                  this.c(this.s);
                  this.s++;
                  if (this.s >= this.q) {
                     RecoveredUtilsF.a("§aAll released! Stopping LongJump.");
                     this.j = false;
                     this.a(false);
                  }
               } else if (!this.j) {
                  RecoveredUtilsF.a("§cNo intercepted packets");
                  this.a(false);
               } else {
                  RecoveredUtilsF.a("§cAll already released");
               }
            } else if (!var4) {
               this.n = false;
            }
         }
      }
   }

   @EventTarget
   public void onRender2D(EventRender2D var1) {
      if (this.m()) {
         int var2 = a.getWindow().getGuiScaledWidth();
         int var3 = a.getWindow().getGuiScaledHeight();
         String var4;
         if (this.j) {
            int var5 = this.e.size();
            long var6 = System.currentTimeMillis();
            long var8 = var6 - this.o;
            var4 = String.format("§eIntercepting: %d packets | Time: %.1fs | Press Mouse5 (%d/%d)", var5, (float)var8 / 1000.0F, this.s, this.q);
         } else if (this.l) {
            var4 = "§aUsing fireball #" + this.p;
         } else {
            var4 = "§bWaiting for input | Mouse4: Jump & use fireball | Mouse5: Release";
         }

         float var10 = (float)var2 / 2.0F - (float)a.font.width(var4) / 2.0F;
         float var11 = (float)var3 / 2.0F + 20.0F;
         var1.guiGraphics().drawString(a.font, var4, (int)var10, (int)var11, -1);
      }
   }

   @EventTarget
   public void onPacket(RecoveredEventsImplM var1) {
      if (this.m() && a.level != null) {
         if (this.j && var1.b() == RecoveredEventsApiAA.RECEIVE) {
            Packet var4 = var1.c();
            if (var4 instanceof ClientboundPlayerPositionPacket) {
               this.k = true;
               var1.a(true);
            } else {
               if (var4 instanceof ClientboundSetEntityMotionPacket var5 && var5.getId() == a.player.getId()) {
                  this.q++;
                  this.d.add(this.e.size());
                  a.execute(() -> RecoveredUtilsF.a("§e" + this.q + " received"));
               }

               var1.a(true);
               this.e.add(var4);
            }
         } else if (var1.c() instanceof ClientboundSetEntityMotionPacket var2
            && var1.b() == RecoveredEventsApiAA.RECEIVE
            && var2.getId() == a.player.getId()
            && this.p > 0
            && !this.j) {
            this.q++;
            this.d.add(this.e.size());
            a.execute(() -> RecoveredUtilsF.a("§eReceived #" + this.q + ", starting packet interception"));
            var1.a(true);
            this.e.add(var1.c());
            this.j = true;
            this.o = System.currentTimeMillis();
            a.execute(() -> RecoveredUtilsF.a("§ePacket interception started, press Mouse5 to release each"));
         }
      } else if (this.j) {
         a.execute(() -> {
            this.p();
            this.j = false;
         });
      }
   }

   @EventTarget
   public void onMotion(RecoveredEventsImplK var1) {
      if (this.m()) {
         if (var1.b() == RecoveredEventsApiAA.PRE) {
            if (this.h > 0) {
               if (this.h == 1) {
                  this.p++;
                  RecoveredUtilsF.a("§aJumping for fireball #" + this.p);
                  a.options.keyJump.setDown(true);
                  float var2;
                  float var3;
                  if (!this.f) {
                     var2 = a.player.getYRot() - 180.0F;
                     var3 = 88.0F;
                  } else {
                     var2 = a.player.getYRot();
                     var3 = 90.0F;
                  }

                  c = new RecoveredUtilsCB(var2, var3);
               }

               if (this.h >= 2) {
                  this.h = 0;
                  int var4 = this.s();
                  if (var4 != -1) {
                     a.player.getInventory().selected = var4;
                     this.r = this.r();
                     a.options.keyUse.setDown(true);
                     this.l = true;
                     RecoveredUtilsF.a("§eFireball #" + this.p + " started, initial count: " + this.r);
                  } else {
                     this.a(false);
                  }
               }

               if (this.h != 0) {
                  this.h++;
               }
            }
         } else if (this.l) {
            int var5 = this.r();
            if (var5 < this.r) {
               a.options.keyUse.setDown(false);
               a.options.keyJump.setDown(false);
               c = null;
               this.l = false;
               RecoveredUtilsF.a("§eFireball #" + this.p + " used! Count: " + this.r + " -> " + var5 + ", waiting for next input");
            } else if (this.q() == -1) {
               a.options.keyUse.setDown(false);
               a.options.keyJump.setDown(false);
               c = null;
               this.l = false;
               RecoveredUtilsF.a("§cNo more fireballs available!");
            }
         }
      }
   }
}
