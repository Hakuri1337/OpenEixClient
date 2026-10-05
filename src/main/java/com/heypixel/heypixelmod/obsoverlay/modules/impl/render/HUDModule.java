package com.heypixel.heypixelmod.obsoverlay.modules.impl.render;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplS;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.utils.e.RecoveredUtilsEA;
import java.awt.Color;
import java.lang.reflect.Field;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;

@ModuleInfo(
   a = "HUD",
   b = "抬头显示器",
   c = "Displays information on your screen",
   d = ModuleCategory.RENDER
)
public class HUDModule extends ClientModule {
   public static final int c = new Color(150, 45, 45, 255).getRGB();
   public static final int d = new Color(0, 0, 0, 120).getRGB();
   public static final int e = new Color(0, 0, 0, 40).getRGB();
   public RecoveredDAA f = RecoveredDD.a(this, "Module Toggle Sound").a(true).a().b();
   public RecoveredDAC g = RecoveredDD.a(this, "Red 1").a(102.0F).d(1.0F).b(0.0F).c(255.0F).a().c();
   public RecoveredDAC h = RecoveredDD.a(this, "Green 1").a(255.0F).d(1.0F).b(0.0F).c(255.0F).a().c();
   public RecoveredDAC i = RecoveredDD.a(this, "Blue 1").a(209.0F).d(1.0F).b(0.0F).c(255.0F).a().c();
   public RecoveredDAC j = RecoveredDD.a(this, "Red 2").a(6.0F).d(1.0F).b(0.0F).c(255.0F).a().c();
   public RecoveredDAC k = RecoveredDD.a(this, "Green 2").a(149.0F).d(1.0F).b(0.0F).c(255.0F).a().c();
   public RecoveredDAC l = RecoveredDD.a(this, "Blue 2").a(255.0F).d(1.0F).b(0.0F).c(255.0F).a().c();
   public RecoveredDAA m = RecoveredDD.a(this, "Chat Background").a(true).a().b();
   public RecoveredDAA n = RecoveredDD.a(this, "Chat Blur").a(true).a().b();
   public RecoveredDAA o = RecoveredDD.a(this, "Chat Shadow").a(true).a().b();
   public RecoveredDAC p = RecoveredDD.a(this, "Chat Radius").a(5.0F).d(1.0F).b(0.0F).c(20.0F).a().c();
   public RecoveredDAA q = RecoveredDD.a(this, "Chat Input Background").a(true).a().b();
   public RecoveredDAA r = RecoveredDD.a(this, "Better GUI").a(true).a().b();
   public RecoveredDAA s = RecoveredDD.a(this, "Better GUI Blur").a(true).a(() -> this.r.m()).a().b();
   public RecoveredDAA t = RecoveredDD.a(this, "Better GUI Shadow").a(true).a(() -> this.r.m()).a().b();
   public RecoveredDAC u = RecoveredDD.a(this, "Better GUI Radius").a(10.0F).d(1.0F).b(0.0F).c(30.0F).a(() -> this.r.m()).a().c();
   public RecoveredDAC v = RecoveredDD.a(this, "Better GUI Opacity").a(110.0F).d(5.0F).b(0.0F).c(255.0F).a(() -> this.r.m()).a().c();
   public RecoveredDAC w = RecoveredDD.a(this, "Better GUI Slot Opacity").a(35.0F).d(5.0F).b(0.0F).c(255.0F).a(() -> this.r.m()).a().c();
   private float x = Float.MAX_VALUE;
   private float y = Float.MAX_VALUE;
   private float z = Float.MIN_VALUE;
   private float A = Float.MIN_VALUE;
   private boolean B = false;
   private int C = 0;
   private int D = -1;
   private float E = Float.MAX_VALUE;
   private float F = Float.MAX_VALUE;
   private float G = Float.MIN_VALUE;
   private float H = Float.MIN_VALUE;
   private boolean I = false;
   private int J = 0;
   private int K = -1;
   private long L = 0L;
   private long M = 0L;
   private boolean N = false;
   private static Field O;
   private static Field P;
   private static Field Q;
   private static Field R;
   private static Field S;
   private static boolean T = false;

   public static Color p() {
      HUDModule var0 = EixClient.a().g().a(HUDModule.class);
      return new Color(var0.g.q() / 255.0F, var0.h.q() / 255.0F, var0.i.q() / 255.0F);
   }

   public static Color q() {
      HUDModule var0 = EixClient.a().g().a(HUDModule.class);
      return new Color(var0.j.q() / 255.0F, var0.k.q() / 255.0F, var0.l.q() / 255.0F);
   }

   public void r() {
      if (!this.w()) {
         this.B = false;
      } else {
         this.C++;
         this.x = Float.MAX_VALUE;
         this.y = Float.MAX_VALUE;
         this.z = Float.MIN_VALUE;
         this.A = Float.MIN_VALUE;
         this.B = false;
      }
   }

   public void a(int var1, int var2, int var3, int var4) {
      if (this.w()) {
         float var5 = (float)Math.min(var1, var3);
         float var6 = (float)Math.min(var2, var4);
         float var7 = (float)Math.max(var1, var3);
         float var8 = (float)Math.max(var2, var4);
         this.x = Math.min(this.x, var5);
         this.y = Math.min(this.y, var6);
         this.z = Math.max(this.z, var7);
         this.A = Math.max(this.A, var8);
         this.B = true;
      }
   }

   public void s() {
      if (!this.x()) {
         this.I = false;
      } else {
         this.N = false;
         this.J++;
         this.E = Float.MAX_VALUE;
         this.F = Float.MAX_VALUE;
         this.G = Float.MIN_VALUE;
         this.H = Float.MIN_VALUE;
         this.I = false;
      }
   }

   public void b(int var1, int var2, int var3, int var4) {
      if (this.x()) {
         float var5 = (float)Math.min(var1, var3);
         float var6 = (float)Math.min(var2, var4);
         float var7 = (float)Math.max(var1, var3);
         float var8 = (float)Math.max(var2, var4);
         this.E = Math.min(this.E, var5);
         this.F = Math.min(this.F, var6);
         this.G = Math.max(this.G, var7);
         this.H = Math.max(this.H, var8);
         this.I = true;
      }
   }

   public void t() {
      this.L = System.currentTimeMillis();
      this.M = 0L;
      this.N = false;
      this.K = -1;
      this.I = false;
   }

   public void u() {
      if (this.x()) {
         this.N = true;
         this.M = System.currentTimeMillis();
         this.K = -1;
      }
   }

   private void v() {
      if (!T && O == null) {
         try {
            Class<AbstractContainerScreen> var1 = AbstractContainerScreen.class;

            try {
               O = var1.getDeclaredField("leftPos");
               P = var1.getDeclaredField("topPos");
               Q = var1.getDeclaredField("imageWidth");
               R = var1.getDeclaredField("imageHeight");
            } catch (NoSuchFieldException var4) {
               T = true;
               return;
            }

            O.setAccessible(true);
            P.setAccessible(true);
            Q.setAccessible(true);
            R.setAccessible(true);

            try {
               S = var1.getDeclaredField("hoveredSlot");
               S.setAccessible(true);
            } catch (NoSuchFieldException var3) {
               S = null;
            }
         } catch (Exception var5) {
            T = true;
         }
      }
   }

   @EventTarget
   public void onRenderSkia(RecoveredEventsImplS var1) {
      boolean var2 = this.w();
      boolean var3 = this.x();
      boolean var4 = this.r.m() && a.screen instanceof AbstractContainerScreen;
      if (var2 || var3 || var4) {
         if (var4) {
            this.v();
            if (!T) {
               try {
                  AbstractContainerScreen var5 = (AbstractContainerScreen)a.screen;
                  int var6 = O.getInt(var5);
                  int var7 = P.getInt(var5);
                  int var8 = Q.getInt(var5);
                  int var9 = R.getInt(var5);
                  float var10 = (float)var6;
                  float var11 = (float)var7;
                  float var12 = (float)var8;
                  float var13 = (float)var9;
                  float var14 = this.u.q();
                  if (this.t.m()) {
                     RecoveredUtilsEA.c(var10, var11, var12, var13, var14);
                  }

                  if (this.s.m()) {
                     RecoveredUtilsEA.a(var10, var11, var12, var13, var14);
                  }

                  RecoveredUtilsEA.a(var10, var11, var12, var13, var14, new Color(18, 18, 18, (int)this.v.q()));
                  AbstractContainerMenu var15 = var5.getMenu();
                  if (var15 != null) {
                     Color var16 = new Color(25, 25, 25, (int)this.w.q());

                     for (Slot var18 : var15.slots) {
                        float var19 = var10 + (float)var18.x;
                        float var20 = var11 + (float)var18.y;
                        RecoveredUtilsEA.a(var19, var20, 16.0F, 16.0F, 4.0F, var16);
                     }
                  }
               } catch (Exception var22) {
               }
            }
         }

         if (var2 && this.B && this.D != this.C) {
            float var23 = 4.0F;
            float var25 = this.x - var23;
            float var27 = this.y - var23;
            float var29 = this.z + var23;
            float var31 = this.A + var23;
            float var33 = var29 - var25;
            float var35 = var31 - var27;
            if (var33 > 0.0F && var35 > 0.0F) {
               float var37 = this.p.q();
               if (this.o.m()) {
                  RecoveredUtilsEA.c(var25, var27, var33, var35, var37);
               }

               if (this.n.m()) {
                  RecoveredUtilsEA.a(var25, var27, var33, var35, var37);
               }

               RecoveredUtilsEA.a(var25, var27, var33, var35, var37, new Color(0, 0, 0, 60));
               this.D = this.C;
            }
         }

         if (var3) {
            if (!this.I) {
               return;
            }

            if (!this.N && this.K == this.J) {
               return;
            }

            float var24 = 2.0F;
            float var26 = this.E - var24;
            float var28 = this.F - var24;
            float var30 = this.G + var24;
            float var32 = this.H + var24;
            float var34 = var30 - var26;
            float var36 = var32 - var28;
            if (var34 <= 0.0F || var36 <= 0.0F) {
               return;
            }

            long var38 = System.currentTimeMillis() - (this.N ? this.M : this.L);
            float var39 = Math.min(1.0F, (float)var38 / 300.0F);
            float var40 = (float)(1.0 - Math.pow((double)(1.0F - var39), 3.0));
            float var41 = this.N ? 1.0F - var40 : var40;
            if (this.N && var39 >= 1.0F) {
               this.N = false;
               this.I = false;
               return;
            }

            float var42 = (float)a.getWindow().getGuiScaledHeight();
            float var43 = var28 + (var42 - var28) * (1.0F - var41);
            float var44 = this.p.q();
            int var45 = Math.min(255, Math.max(0, (int)(70.0F * var41)));
            Color var21 = new Color(18, 18, 18, var45);
            if (this.o.m()) {
               RecoveredUtilsEA.c(var26, var43, var34, var36, var44);
            }

            if (this.n.m()) {
               RecoveredUtilsEA.a(var26, var43, var34, var36, var44);
            }

            RecoveredUtilsEA.a(var26, var43, var34, var36, var44, var21);
            this.K = this.J;
         }
      }
   }

   private boolean w() {
      return this.m() && this.m.m();
   }

   private boolean x() {
      return this.m() && this.q.m();
   }

   public void a(AbstractContainerScreen<?> var1, GuiGraphics var2, int var3, int var4) {
      if (this.r.m()) {
         this.v();
         if (!T) {
            try {
               int var5 = O.getInt(var1);
               int var6 = P.getInt(var1);
               AbstractContainerMenu var7 = var1.getMenu();
               if (var7 == null) {
                  return;
               }

               Slot var8 = null;
               float var9 = (float)var5;
               float var10 = (float)var6;

               for (Slot var12 : var7.slots) {
                  float var13 = var9 + (float)var12.x;
                  float var14 = var10 + (float)var12.y;
                  if ((float)var3 >= var13 && (float)var3 < var13 + 16.0F && (float)var4 >= var14 && (float)var4 < var14 + 16.0F) {
                     var8 = var12;
                  }

                  if (var12.hasItem()) {
                     ItemStack var15 = var12.getItem();
                     var2.renderItem(var15, (int)var13, (int)var14);
                     var2.renderItemDecorations(a.font, var15, (int)var13, (int)var14);
                  }
               }

               ItemStack var17 = var7.getCarried();
               if (!var17.isEmpty()) {
                  int var18 = var3 - 8;
                  int var19 = var4 - 8;
                  var2.renderItem(var17, var18, var19);
                  var2.renderItemDecorations(a.font, var17, var18, var19);
               }

               a.renderBuffers().bufferSource().endBatch();
               if (S != null) {
                  S.set(var1, var8);
               }
            } catch (Exception var16) {
            }
         }
      }
   }
}
