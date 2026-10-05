package com.heypixel.heypixelmod.obsoverlay.utils.c;

import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.EventRunTicks;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplAb;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplAg;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplC;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplF;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplI;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplL;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplN;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplO;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplU;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplV;
import com.heypixel.heypixelmod.obsoverlay.utils.a.RecoveredUtilsAC;
import java.util.function.Function;
import lombok.Generated;
import net.minecraft.client.Minecraft;
import net.minecraft.network.protocol.game.ServerboundMovePlayerPacket.PosRot;
import net.minecraft.util.Mth;

public class RecoveredUtilsCC {
   private static final Minecraft f = Minecraft.getInstance();
   public static RecoveredUtilsCB a = new RecoveredUtilsCB(0.0F, 0.0F);
   public static RecoveredUtilsCB b = new RecoveredUtilsCB(0.0F, 0.0F);
   public static RecoveredUtilsCB c = new RecoveredUtilsCB(0.0F, 0.0F);
   public static RecoveredUtilsCB d;
   public static RecoveredUtilsCB e;
   private static boolean g;
   private static boolean h;
   private static boolean i = false;
   private static boolean j = false;
   private static double k;
   private static Function<RecoveredUtilsCB, Boolean> l;
   private static final float m = 60.0F;
   private static Object n = null;
   private static float o = Float.NaN;
   private static float p = Float.NaN;

   public static Object a() {
      return n;
   }

   public static boolean a(Object var0) {
      return g && n != null && n == var0;
   }

   public static void a(boolean var0) {
      j = var0;
   }

   public static boolean b() {
      return j;
   }

   public static void c() {
      g = false;
      h = false;
      j = false;
      k = 0.0;
      l = null;
      n = null;
      if (f.player != null) {
         float var0 = f.player.getYRot();
         float var1 = f.player.getXRot();
         a = new RecoveredUtilsCB(var0, var1);
         b = new RecoveredUtilsCB(var0, var1);
         c = new RecoveredUtilsCB(var0, var1);
      }

      d = null;
      e = null;
      e();
   }

   public static boolean b(Object var0) {
      if (!a(var0)) {
         return false;
      } else {
         c();
         return true;
      }
   }

   public static void b(boolean var0) {
      if (!var0) {
         c();
      } else {
         g = true;
      }
   }

   public static void a(RecoveredUtilsCB var0, double var1) {
      c(var0, var1, null);
   }

   public static void a(RecoveredUtilsCB var0, double var1, Function<RecoveredUtilsCB, Boolean> var3) {
      c(var0, var1, var3);
   }

   public static void a(Object var0, RecoveredUtilsCB var1, double var2) {
      b(var0, var1, var2, null);
   }

   public static void a(Object var0, RecoveredUtilsCB var1, double var2, Function<RecoveredUtilsCB, Boolean> var4) {
      b(var0, var1, var2, var4);
   }

   public static void b(RecoveredUtilsCB var0, double var1) {
      j = true;
      c(var0, var1, null);
   }

   public static void b(RecoveredUtilsCB var0, double var1, Function<RecoveredUtilsCB, Boolean> var3) {
      j = true;
      c(var0, var1, var3);
   }

   private static void c(RecoveredUtilsCB var0, double var1, Function<RecoveredUtilsCB, Boolean> var3) {
      b(null, var0, var1, var3);
   }

   private static void b(Object var0, RecoveredUtilsCB var1, double var2, Function<RecoveredUtilsCB, Boolean> var4) {
      if (f.player != null && !i) {
         if (var1 != null) {
            if (a == null) {
               a = new RecoveredUtilsCB(f.player.getYRot(), f.player.getXRot());
            }

            if (b == null) {
               b = new RecoveredUtilsCB(a.a(), a.b());
            }

            if (c == null) {
               c = new RecoveredUtilsCB(var1.a(), var1.b());
            }

            c = var1;
            k = var2;
            l = var4;
            g = true;
            if (var0 != null) {
               n = var0;
            }

            i();
            if (var0 != null) {
               m();
            }

            n();
         }
      }
   }

   public static void d() {
      c();
      if (f.player == null) {
         a = new RecoveredUtilsCB(0.0F, 0.0F);
         b = new RecoveredUtilsCB(0.0F, 0.0F);
         c = new RecoveredUtilsCB(0.0F, 0.0F);
      }
   }

   public static void e() {
      o = Float.NaN;
      p = Float.NaN;
   }

   public static RecoveredUtilsCB a(float var0, float var1) {
      if (!Float.isNaN(o) && !Float.isNaN(p)) {
         float var2 = RecoveredUtilsAC.a(o, var0);
         float var3 = RecoveredUtilsAC.b(p, var1);
         o = var2;
         p = var3;
         return new RecoveredUtilsCB(var2, var3);
      } else {
         o = var0;
         p = var1;
         return new RecoveredUtilsCB(var0, var1);
      }
   }

   public static void f() {
      i = true;
      d();
   }

   public static void g() {
      i = false;
   }

   public static void h() {
      if (!j) {
         if (g && a != null && f.player != null && f.getConnection() != null) {
            f.getConnection().send(new PosRot(f.player.getX(), f.player.getY(), f.player.getZ(), a.a(), a.b(), f.player.onGround()));
         }
      }
   }

   public static void i() {
      if (!h && !i) {
         if (f.player == null) {
            h = true;
         } else {
            if (a == null) {
               a = new RecoveredUtilsCB(f.player.getYRot(), f.player.getXRot());
            }

            if (c == null) {
               c = new RecoveredUtilsCB(a.a(), a.b());
            }

            Function var0 = l;
            if (var0 != null && Boolean.TRUE.equals(var0.apply(c))) {
               a = new RecoveredUtilsCB(c.a(), c.b());
               h = true;
            } else {
               double var1 = k * (1.0 + (Math.random() - 0.5) * 0.06);
               a = RecoveredUtilsCD.b(new RecoveredUtilsCB(c.a(), c.b()), var1);
               h = true;
            }
         }
      }
   }

   private static void m() {
      RecoveredUtilsCB var0 = a;
      if (var0 != null && f.player != null) {
         float var1 = var0.a();
         float var2 = var0.b();
         if (!Float.isNaN(var1) && !Float.isNaN(var2) && !Float.isInfinite(var1) && !Float.isInfinite(var2)) {
            float var3 = Float.isNaN(o) ? f.player.getYRot() : o;
            float var4 = Float.isNaN(p) ? f.player.getXRot() : p;
            double var5 = (double)Mth.wrapDegrees(var1 - var3);
            double var7 = (double)(var2 - var4);
            if (var5 != 0.0 || var7 != 0.0) {
               double var9 = k <= 0.0 ? 60.0 : k;
               double var11 = Math.min(var9, 60.0);
               RecoveredUtilsCB var13 = new RecoveredUtilsCB(var3, var4);
               RecoveredUtilsCB var14 = RecoveredUtilsCD.a(var13, new RecoveredUtilsCB(var1, var2), var11);
               a = new RecoveredUtilsCB(var13.a() + var14.a(), Mth.clamp(var13.b() + var14.b(), -90.0F, 90.0F));
            }
         }
      }
   }

   private static void n() {
      RecoveredUtilsCB var0 = a;
      if (var0 != null) {
         float var1 = var0.a();
         float var2 = var0.b();
         if (!Float.isNaN(var1) && !Float.isNaN(var2) && !Float.isInfinite(var1) && !Float.isInfinite(var2)) {
            a = a(var1, var2);
         }
      }
   }

   public static RecoveredUtilsCB j() {
      if (g && a != null) {
         return a;
      } else {
         return f.player == null ? new RecoveredUtilsCB(0.0F, 0.0F) : new RecoveredUtilsCB(f.player.getYRot(), f.player.getXRot());
      }
   }

   @EventTarget
   public void onRespawn(RecoveredEventsImplU var1) {
      d();
   }

   @EventTarget(
      a = 4
   )
   public void updateGlobalYaw(EventRunTicks var1) {
      if (!i) {
         if (var1.type() == RecoveredEventsApiAA.PRE && f.player != null) {
            if (g) {
               if (a == null || b == null || c == null) {
                  float var2 = f.player.getYRot();
                  float var3 = f.player.getXRot();
                  a = new RecoveredUtilsCB(var2, var3);
                  b = new RecoveredUtilsCB(var2, var3);
                  c = new RecoveredUtilsCB(var2, var3);
               }
            }
         }
      }
   }

   @EventTarget
   public void onAnimation(RecoveredEventsImplV var1) {
      if (!i) {
         if (g && d != null && e != null) {
            var1.a(d.a());
            var1.b(e.a());
            var1.c(d.b());
            var1.d(e.b());
         }
      }
   }

   @EventTarget(
      a = 4
   )
   public void onPre(RecoveredEventsImplK var1) {
      if (!i) {
         if (var1.b() == RecoveredEventsApiAA.PRE) {
            if (f.player != null) {
               if (!g) {
                  h = false;
                  e();
               } else {
                  if (a != null) {
                     n();
                     float var2 = a.a();
                     float var3 = a.b();
                     if (!Float.isNaN(var2) && !Float.isNaN(var3)) {
                        var1.a(var2);
                        var1.b(var3);
                     }

                     b = a;
                  }

                  e = d;
                  d = new RecoveredUtilsCB(var1.f(), var1.g());
                  h = false;
               }
            }
         }
      }
   }

   @EventTarget(
      a = 1
   )
   public void onMove(RecoveredEventsImplL var1) {
      if (!i) {
         if (!g || a == null || j) {
            ;
         }
      }
   }

   @EventTarget
   public void onMove(RecoveredEventsImplO var1) {
      if (!i) {
         if (a != null && var1.a == f.player && g && !j) {
            var1.a(a.a());
            var1.b(a.b());
         }
      }
   }

   @EventTarget
   public void onItemRayTrace(RecoveredEventsImplAg var1) {
      if (!i) {
         if (a != null && g && !j) {
            var1.a(a.a());
            var1.b(a.b());
         }
      }
   }

   @EventTarget
   public void onStrafe(RecoveredEventsImplAb var1) {
      if (!i) {
         if (g && a != null && !j) {
            var1.a(a.a());
         }
      }
   }

   @EventTarget
   public void onJump(RecoveredEventsImplI var1) {
      if (!i) {
         if (g && a != null && !j) {
            var1.a(a.a());
         }
      }
   }

   @EventTarget(
      a = 0
   )
   public void onPositionItem(RecoveredEventsImplN var1) {
      if (!i) {
         if (g && a != null) {
            if (var1.b() instanceof PosRot var2) {
               RecoveredUtilsCB var5 = a(a.a(), a.b());
               PosRot var4 = new PosRot(var2.getX(0.0), var2.getY(0.0), var2.getZ(0.0), var5.a(), var5.b(), var2.isOnGround());
               var1.a(var4);
            }
         }
      }
   }

   @EventTarget
   public void onFallFlying(RecoveredEventsImplF var1) {
      if (!i) {
         if (g && a != null && !j) {
            var1.a(a.b());
         }
      }
   }

   @EventTarget
   public void onAttack(RecoveredEventsImplC var1) {
      if (!i) {
         if (g && a != null && !j) {
            var1.a(a.a());
         }
      }
   }

   @Generated
   public static boolean k() {
      return g;
   }

   @Generated
   public static boolean l() {
      return h;
   }

   @Generated
   public static void c(boolean var0) {
      h = var0;
   }
}
