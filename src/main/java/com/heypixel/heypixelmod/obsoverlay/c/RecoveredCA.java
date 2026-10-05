package com.heypixel.heypixelmod.obsoverlay.c;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDC;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDF;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAE;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAF;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.impl.EventShader;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAd;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAg;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAi;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAj;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAl;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsH;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsL;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsS;
import com.heypixel.heypixelmod.obsoverlay.utils.renderer.RecoveredUtilsRendererD;
import com.heypixel.heypixelmod.obsoverlay.utils.renderer.text.RecoveredUtilsRendererTextC;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.platform.InputConstants.Key;
import com.mojang.blaze3d.vertex.PoseStack;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class RecoveredCA extends Screen {
   private static final Minecraft U = Minecraft.getInstance();
   public static float a = 100.0F;
   public static float b = 100.0F;
   public static float c = 400.0F;
   public static float d = 250.0F;
   private final RecoveredUtilsAg V = new RecoveredUtilsAg(0.0F);
   private final RecoveredUtilsAl W = new RecoveredUtilsAl();
   private final RecoveredUtilsAg X = new RecoveredUtilsAg(0.0F);
   private final RecoveredUtilsAl Y = new RecoveredUtilsAl();
   ModuleCategory e = null;
   ClientModule f = null;
   int[] g = new int[]{-1, -1};
   boolean h = false;
   RecoveredUtilsAg i = new RecoveredUtilsAg(100.0F);
   RecoveredUtilsAg j = new RecoveredUtilsAg(140.0F);
   RecoveredUtilsAg k = new RecoveredUtilsAg(100.0F);
   RecoveredUtilsAg l = new RecoveredUtilsAg(0.0F);
   RecoveredUtilsAg m = new RecoveredUtilsAg(0.0F);
   RecoveredUtilsAg n = new RecoveredUtilsAg(0.0F);
   HashMap<ModuleCategory, RecoveredUtilsAg> o = new HashMap<ModuleCategory, RecoveredUtilsAg>() {
      {
         for (ModuleCategory var5 : ModuleCategory.values()) {
            this.put(var5, new RecoveredUtilsAg(0.0F));
         }
      }
   };
   HashMap<ModuleCategory, RecoveredUtilsAg> p = new HashMap<ModuleCategory, RecoveredUtilsAg>() {
      {
         for (ModuleCategory var5 : ModuleCategory.values()) {
            this.put(var5, new RecoveredUtilsAg(0.0F));
         }
      }
   };
   HashMap<ModuleCategory, List<ClientModule>> q = new HashMap<ModuleCategory, List<ClientModule>>() {
      {
         for (ModuleCategory var5 : ModuleCategory.values()) {
            this.put(var5, EixClient.a().g().a(var5));
         }
      }
   };
   HashMap<ClientModule, RecoveredUtilsAg> r = new HashMap<ClientModule, RecoveredUtilsAg>() {
      {
         for (ClientModule var3 : EixClient.a().g().a()) {
            this.put(var3, new RecoveredUtilsAg(0.0F, 255.0F));
         }
      }
   };
   HashMap<ClientModule, RecoveredUtilsAg> s = new HashMap<ClientModule, RecoveredUtilsAg>() {
      {
         for (ClientModule var3 : EixClient.a().g().a()) {
            this.put(var3, new RecoveredUtilsAg(0.0F));
         }
      }
   };
   HashMap<RecoveredDC, RecoveredUtilsAg> t = new HashMap<RecoveredDC, RecoveredUtilsAg>() {
      {
         for (RecoveredDC var3 : EixClient.a().d().a()) {
            this.put(var3, new RecoveredUtilsAg(0.0F));
         }
      }
   };
   String u = "";
   float v;
   float w;
   boolean x = false;
   boolean y = false;
   boolean z = false;
   boolean A = false;
   boolean B = false;
   ModuleCategory C = null;
   ClientModule D = null;
   ClientModule E = null;
   RecoveredUtilsAg F = new RecoveredUtilsAg(0.0F);
   RecoveredUtilsAg G = new RecoveredUtilsAg(0.0F);
   List<ClientModule> H;
   List<RecoveredDC> I;
   RecoveredDAA J;
   RecoveredDAC K;
   RecoveredDAC L;
   RecoveredDAE M;
   int N;
   RecoveredDAF O;
   RecoveredDAF P;
   String Q = "";
   RecoveredDAD R;
   RecoveredDAD S;
   String T;
   private boolean Z = false;
   private float aa = 0.0F;
   private float ab = 0.0F;

   public RecoveredCA() {
      super(Component.nullToEmpty("EixClient"));
   }

   public void a() {
      EixClient.a().i().c();
      EixClient.a().b().b(this);
      super.onClose();
   }

   public boolean a(double var1, double var3, int var5) {
      if (var5 == 0) {
         this.Z = true;
      }

      if (this.E == null || var5 != 3 && var5 != 4) {
         if (var5 != 2 && this.E == null) {
            if (this.D != null) {
               if (var5 == 0) {
                  this.D.f();
               } else if (var5 == 1) {
                  this.f = this.D;
                  this.I = EixClient.a().d().a(this.D);
                  this.n.a = this.n.c = 0.0F;
               }
            }

            if (var5 == 0) {
               if (this.h && !this.A && !this.B) {
                  this.e = null;
                  this.f = null;
                  this.I = null;
               }

               if (this.z && this.C != null) {
                  this.e = this.C;
                  this.m.c = this.m.a = 0.0F;
                  this.F.c = 5.0F;
                  this.F.a = 255.0F;
                  this.z = false;
               }

               boolean var6 = this.e != null
                  ? RecoveredUtilsAd.a((int)var1, (int)var3, a, b, a + c, b + 25.0F)
                  : RecoveredUtilsAd.a((int)var1, (int)var3, a, b, a + 100.0F, b + 40.0F);
               if ((this.e == null || !this.h) && var6) {
                  this.a(var1, var3);
                  this.B = true;
               }

               if (RecoveredUtilsAd.a((int)var1, (int)var3, a + c - 10.0F, b + d - 10.0F, a + c, b + d)) {
                  this.a(var1, var3);
                  this.A = true;
               }

               if (this.J != null) {
                  this.J.a(!this.J.m());
               }

               if (this.K != null) {
                  this.L = this.K;
               }

               if (this.M != null) {
                  this.M.a(this.N);
                  RecoveredUtilsAg var7 = this.t.get(this.M);
                  var7.c = 0.0F;
                  var7.a = 255.0F;
               }

               if (this.R != null) {
                  this.S = this.R;
               }

               if (this.O != null) {
                  this.P = this.O;
                  this.Q = String.valueOf(this.O.n());
               } else if (this.P != null) {
                  this.P.a(this.Q);
                  this.P = null;
               }
            }
         } else if (var5 == 2 && this.D != null) {
            this.E = this.D;
         }

         return true;
      } else {
         this.E.b(-var5);
         this.E = null;
         return true;
      }
   }

   public boolean b(double var1, double var3, int var5) {
      if (var5 == 0) {
         this.Z = false;
      }

      if (this.A) {
         this.A = false;
         this.a(-1, -1);
      }

      if (this.B) {
         this.B = false;
         this.a(-1, -1);
      }

      if (this.L != null) {
         this.L = null;
      }

      return true;
   }

   public boolean a(int var1, int var2, int var3) {
      if (this.S != null) {
         if (var1 == 256) {
            this.S.a(0);
            this.S = null;
            return true;
         } else {
            this.S.a(var1);
            this.S = null;
            return true;
         }
      } else if (this.P != null) {
         if (var1 == 256) {
            this.P = null;
            return true;
         } else if (var1 == 257 || var1 == 335) {
            this.P.a(this.Q);
            this.P = null;
            return true;
         } else if (var1 == 259) {
            if (!this.Q.isEmpty()) {
               this.Q = this.Q.substring(0, this.Q.length() - 1);
            }

            return true;
         } else if (var1 == 86 && (var3 & 2) != 0) {
            String var4 = U.keyboardHandler.getClipboard();
            if (var4 != null) {
               this.Q = this.Q + var4.replace("\n", " ").replace("\r", "");
            }

            return true;
         } else if (var1 == 65 && (var3 & 2) != 0) {
            this.Q = "";
            return true;
         } else {
            return true;
         }
      } else {
         if (this.E != null) {
            if (var1 == 256) {
               this.E.b(0);
               this.E = null;
               return true;
            }

            this.E.b(var1);
            this.E = null;
         }

         return super.keyPressed(var1, var2, var3);
      }
   }

   public boolean a(char var1, int var2) {
      if (this.P != null) {
         if (var1 >= ' ' && var1 != 127) {
            this.Q = this.Q + var1;
         }

         return true;
      } else {
         return super.charTyped(var1, var2);
      }
   }

   private static String a(int var0) {
      if (var0 == 0) {
         return "NONE";
      } else if (var0 < 0) {
         return "M" + -var0;
      } else {
         Key var1 = InputConstants.getKey(var0, 0);
         if (var1 == InputConstants.UNKNOWN) {
            return "NONE";
         } else {
            String var2 = var1.getDisplayName().getString();
            return var2 != null && !var2.isEmpty() ? var2.toUpperCase() : "NONE";
         }
      }
   }

   private static String a(RecoveredUtilsRendererTextC var0, String var1, double var2, float var4) {
      if (var1 != null && !var1.isEmpty()) {
         if (var0.a(var1, var2) <= var4) {
            return var1;
         } else {
            String var5 = var1;

            while (!var5.isEmpty() && var0.a(var5 + "...", var2) > var4) {
               var5 = var5.substring(0, var5.length() - 1);
            }

            return var5 + "...";
         }
      } else {
         return "";
      }
   }

   protected void b() {
      EixClient.a().b().a(this);
      this.t.forEach((var0, var1) -> {
         if (var0.a() == RecoveredDF.MODE) {
            var1.c = 0.0F;
            var1.a = 255.0F;
         }
      });
   }

   public boolean a(double var1, double var3, double var5) {
      if (this.E == null && RecoveredUtilsAd.b((int)var1, (int)var3, a + 5.0F, b + 20.0F, 100.0F, d - 5.0F)) {
         this.m.a = (float)((double)this.m.a + var5 * 15.0);
         this.W.a();
      }

      if (this.I != null && this.E == null && RecoveredUtilsAd.b((int)var1, (int)var3, a + 140.0F, b + 20.0F, c - 155.0F, d - 25.0F)) {
         this.n.a = (float)((double)this.n.a + var5 * 15.0);
         this.Y.a();
      }

      return true;
   }

   @EventTarget
   public void onShader(EventShader var1) {
      if (U.screen == this) {
         RecoveredUtilsAd.b(var1.stack(), a, b, this.i.c, this.j.c, 5.0F, 1073741824);
      }
   }

   public void a(GuiGraphics var1, int var2, int var3, float var4) {
      PoseStack var5 = var1.pose();
      this.D = null;
      this.x = this.y = this.z = false;
      RecoveredUtilsRendererTextC var6 = RecoveredUtilsRendererD.a;
      if (this.e == null) {
         this.i.a = 100.0F;
         this.j.a = 140.0F;
      } else {
         this.i.a = c;
         this.j.a = d;
      }

      this.i.a(true);
      this.j.a(true);
      RecoveredUtilsAd.b(var5, a, b, this.i.c, this.j.c, 5.0F, RecoveredUtilsH.a(0, 0, 0, 100));

      for (ModuleCategory var10 : ModuleCategory.values()) {
         RecoveredUtilsAg var11 = this.o.get(var10);
         RecoveredUtilsAg var12 = this.p.get(var10);
         if (this.e == null) {
            var12.a = 255.0F;
         } else {
            var12.a = 0.0F;
         }

         var11.a(true);
         var12.a(true);
         float var13 = (float)(var10.ordinal() * 25) * (var12.c / 255.0F);
         if (var12.a >= 4.0F) {
            var6.a(var12.c / 255.0F);
            RecoveredUtilsRendererD.b.a(var5, var10.b(), (double)(a + 8.0F + var11.c), (double)(b + 41.0F + var13), Color.WHITE, true, 0.4);
            var6.a(var5, var10.a(), (double)(a + 25.0F + var11.c), (double)(b + 40.0F + var13), Color.WHITE, true, 0.4);
            var6.a(1.0F);
         }

         boolean var14 = RecoveredUtilsAd.a(var2, var3, a, b + 40.0F + var13, a + 100.0F, b + 40.0F + var13 + 20.0F);
         if (var14) {
            var11.a = 5.0F;
         } else {
            var11.a = 0.0F;
         }

         if (var12.c >= 250.0F && var14) {
            this.C = var10;
            this.z = true;
         }
      }

      this.k.a(true);
      this.l.a(true);
      if (this.k.c > 5.0F) {
         var6.a(this.k.c / 255.0F);
         var6.a(var5, this.u, (double)(a + 6.0F + this.l.c), (double)(b + 3.0F), Color.WHITE, true, 0.4);
      }

      var6.a((255.0F - this.k.c) / 255.0F);
      var6.a(var5, "EixClient", (double)(a + 50.0F - var6.a("EixClient", 0.75) / 2.0F), (double)(b + 5.0F), Color.WHITE, true, 0.75);
      var6.a(1.0F);
      if (this.e != null) {
         this.k.a = 255.0F;
         this.u = "< " + this.e.a() + (this.f != null ? " / " + this.f.h() + " - " + this.f.j() : "");
         this.h = RecoveredUtilsAd.a(var2, var3, a + 8.0F, b + 5.0F, a + 5.0F + var6.a(this.u, 0.4), (float)((double)(b + 5.0F) + var6.a(true, 0.4F)));
         if (this.h && !this.A && !this.B) {
            this.l.a = -2.0F;
         } else {
            this.l.a = 0.0F;
         }

         if (this.m.a < -this.v) {
            this.m.a = -this.v;
         }

         if (this.m.a > 0.0F) {
            this.m.a = 0.0F;
         }

         this.m.a(true);
      } else {
         this.k.a = 4.0F;
      }

      RecoveredUtilsAi.a(false);
      RecoveredUtilsAd.a(var5, a, b + 20.0F, a + this.i.c, b + this.j.c - 5.0F, Integer.MIN_VALUE);
      RecoveredUtilsAi.b(true);
      List var21 = this.q.get(this.e);
      if (var21 != null) {
         this.H = var21;
         this.F.a = 255.0F;
      } else {
         this.F.a = 5.0F;
      }

      this.F.a(true);
      if (var21 == null && this.F.c < 8.0F) {
         this.H = null;
      }

      if (this.H != null) {
         float var22 = 0.0F;
         this.aa = d - 25.0F;

         for (ClientModule var32 : this.H) {
            boolean var36 = RecoveredUtilsAd.b(var2, var3, a + 5.0F, b + 20.0F, 120.0F, d - 25.0F)
               && RecoveredUtilsAd.b(var2, var3, a + 5.0F, b + 20.0F + var22 + this.m.c, 120.0F, 25.0F)
               && this.F.c > 250.0F
               && this.E == null;
            RecoveredUtilsAg var43 = this.s.get(var32);
            if (var32.m()) {
               var43.a = this.F.c;
            } else {
               var43.a = 6.0F;
            }

            var43.a(true);
            int var51 = (int)this.F.c;
            int var59 = RecoveredUtilsH.a(54, 98, 236, (int)var43.c);
            int var15 = RecoveredUtilsH.a(25, 25, 25, var51);
            RecoveredUtilsAd.b(var5, a + 5.0F, b + 20.0F + var22 + this.m.c, 120.0F, 25.0F, 5.0F, var15);
            RecoveredUtilsAd.b(var5, a + 5.0F, b + 20.0F + var22 + this.m.c, 120.0F, 25.0F, 5.0F, var59);
            RecoveredUtilsAg var16 = this.r.get(var32);
            if (var36) {
               var16.a = 150.0F;
               this.D = var32;
            } else {
               var16.a = 5.0F;
            }

            var16.a(true);
            int var17 = RecoveredUtilsH.a(255, 255, 255, (int)var16.c / 3);
            RecoveredUtilsAd.b(var5, a + 5.0F, b + 20.0F + var22 + this.m.c, 120.0F, 25.0F, 5.0F, var17);
            var6.a((float)var51 / 255.0F);
            var6.a(var5, var32.h(), (double)(a + 13.0F), (double)(b + 25.0F + var22 + this.m.c), Color.WHITE, true, 0.4);
            var6.a(1.0F);
            var22 += 30.0F;
         }

         this.v = var22 + 20.0F - d;
         this.aa -= var22 - 5.0F;
         float var27 = this.v + d;
         if (var27 > d - 25.0F) {
            this.V.a(true);
            if (this.W.a(1000.0)) {
               this.V.a = 0.0F;
            } else {
               this.V.a = 255.0F;
            }

            float var33 = d - 25.0F;
            float var37 = (float)RecoveredUtilsS.a((double)(-this.m.c / -this.aa), 0.0, 1.0);
            float var44 = var33 / var27 * var33;
            float var52 = Math.max(var44, 20.0F);
            float var60 = var37 * (var33 - var52);
            RecoveredUtilsAd.b(var5, a + 127.0F, b + 20.0F + var60, 3.0F, var52, 1.5F, RecoveredUtilsAd.a(3630060, this.V.c / 255.0F));
         }
      }

      if (this.I != null) {
         boolean var23 = RecoveredUtilsAd.b(var2, var3, a + 140.0F, b + 20.0F, c - 155.0F, d - 25.0F);
         float var28 = this.n.c;
         if (this.n.a < -this.w) {
            this.n.a = -this.w;
         }

         if (this.n.a > 0.0F) {
            this.n.a = 0.0F;
         }

         this.n.a(true);
         float var34 = 0.0F;
         float var38 = 0.0F;
         this.ab = d - 25.0F;
         this.J = null;

         for (RecoveredDC var53 : this.I) {
            if (var53.h() && var53.a() == RecoveredDF.BOOLEAN) {
               RecoveredDAA var61 = var53.b();
               RecoveredUtilsAg var67 = this.t.get(var61);
               if (var61.m()) {
                  var67.a = 255.0F;
               } else {
                  var67.a = 0.0F;
               }

               var67.a(true);
               float var73 = 0.4F;
               RecoveredUtilsRendererTextC var79 = RecoveredUtilsRendererD.a;
               byte var18 = 0;
               if (RecoveredUtilsAj.a(var53.j())) {
                  var79 = RecoveredUtilsRendererD.a;
                  var73 = 0.325F;
                  var18 = 2;
               }

               float var19 = var79.a(var53.j(), (double)var73) + 23.0F;
               if (var34 + var19 + 20.0F > c - 155.0F) {
                  var34 = 0.0F;
                  var38 += 20.0F;
               }

               if (var23 && RecoveredUtilsAd.b(var2, var3, a + 130.0F + var34, b + var38 + var28 + 20.0F, var19, 13.0F)) {
                  this.J = var61;
               }

               int var20 = RecoveredUtilsH.a(54, 98, 236, (int)var67.c);
               RecoveredUtilsAd.b(var5, a + 140.0F + var34, b + var38 + var28 + 20.0F, 12.0F, 12.0F, 2.0F, RecoveredUtilsH.a(0, 0, 0, 150));
               RecoveredUtilsAd.b(var5, a + 142.0F + var34, b + var38 + var28 + 22.0F, 8.0F, 8.0F, 2.0F, var20);
               var79.a(var5, var53.j(), (double)(a + 155.0F + var34), (double)(b + var38 + var28 + 19.0F + (float)var18), Color.WHITE, true, (double)var73);
               var34 += var19;
            }
         }

         var38 += 10.0F;
         this.K = null;

         for (RecoveredDC var54 : this.I) {
            if (var54.h() && var54.a() == RecoveredDF.FLOAT) {
               RecoveredDAC var62 = var54.c();
               RecoveredUtilsAg var68 = this.t.get(var62);
               if (var23 && RecoveredUtilsAd.b(var2, var3, a + 140.0F, b + var38 + var28 + 39.5F, c - 155.0F, 10.0F)) {
                  this.K = var62;
               }

               var6.a(var5, var54.j(), (double)(a + 140.0F), (double)(b + var38 + var28 + 25.0F), Color.WHITE, true, 0.4);
               String var74 = (float)Math.round(var62.q() * 100.0F) / 100.0F + " / " + var62.n();
               var6.a(var5, var74, (double)(a + c - var6.a(var74, 0.4) - 15.0F), (double)(b + var38 + var28 + 25.0F), Color.WHITE, true, 0.4);
               float var80 = (var62.q() - var62.m()) / (var62.n() - var62.m());
               int var85 = RecoveredUtilsH.a(54, 98, 236, 255);
               RecoveredUtilsAd.b(var5, a + 140.0F, b + var38 + var28 + 42.0F, c - 155.0F, 5.0F, 3.0F, RecoveredUtilsH.a(0, 0, 0, 150));
               var68.a = (c - 155.0F) * var80;
               var68.a(true);
               RecoveredUtilsAd.b(var5, a + 140.0F, b + var38 + var28 + 42.0F, var68.c, 5.0F, 3.0F, var85);
               RecoveredUtilsAd.b(var5, a + 135.0F + var68.c, b + var38 + var28 + 39.5F, 10.0F, 10.0F, 5.0F, RecoveredUtilsH.a(255, 255, 255, 255));
               var38 += 25.0F;
            }
         }

         this.M = null;

         for (RecoveredDC var55 : this.I) {
            if (var55.h() && var55.a() == RecoveredDF.MODE) {
               RecoveredDAE var63 = var55.e();
               RecoveredUtilsAg var69 = this.t.get(var63);
               var69.a(true);
               var6.a(var5, var55.j(), (double)(a + 140.0F), (double)(b + var38 + var28 + 25.0F), Color.WHITE, true, 0.4);
               var34 = 0.0F;
               var38 += 15.0F;

               for (int var75 = 0; var75 < var63.m().length; var75++) {
                  String var81 = var63.m()[var75];
                  float var86 = var6.a(var81, 0.4) + 20.0F;
                  if (var34 + var86 + 20.0F > c - 155.0F) {
                     var34 = 0.0F;
                     var38 += 20.0F;
                  }

                  if (var23 && RecoveredUtilsAd.b(var2, var3, a + 140.0F + var34, b + var38 + var28 + 25.0F, var86, 13.0F)) {
                     this.M = var63;
                     this.N = var75;
                  }

                  int var89 = RecoveredUtilsH.a(54, 98, 236, var63.a(var81) ? (int)var69.c : 10);
                  RecoveredUtilsAd.b(var5, a + 140.0F + var34, b + var38 + var28 + 27.0F, 10.0F, 10.0F, 5.0F, RecoveredUtilsH.a(0, 0, 0, 150));
                  RecoveredUtilsAd.b(var5, a + 141.0F + var34, b + var38 + var28 + 28.0F, 8.0F, 8.0F, 5.0F, var89);
                  var6.a(var5, var81, (double)(a + 152.0F + var34), (double)(b + var38 + var28 + 25.0F), Color.WHITE, true, 0.4);
                  var34 += var86;
               }

               var38 += 20.0F;
            }
         }

         this.O = null;

         for (RecoveredDC var56 : this.I) {
            if (var56.h() && var56.a() == RecoveredDF.STRING) {
               RecoveredDAF var64 = var56.d();
               var6.a(var5, var56.j(), (double)(a + 140.0F), (double)(b + var38 + var28 + 25.0F), Color.WHITE, true, 0.4);
               var38 += 14.0F;
               float var70 = a + 140.0F;
               float var76 = b + var38 + var28 + 25.0F;
               float var82 = c - 155.0F;
               boolean var87 = this.P == var64;
               boolean var90 = var23 && RecoveredUtilsAd.b(var2, var3, var70, var76, var82, 14.0F);
               if (var90) {
                  this.O = var64;
               }

               RecoveredUtilsAd.b(var5, var70, var76, var82, 14.0F, 3.0F, RecoveredUtilsH.a(0, 0, 0, 150));
               if (var87) {
                  RecoveredUtilsAd.b(var5, var70, var76, var82, 14.0F, 3.0F, RecoveredUtilsH.a(54, 98, 236, 90));
               } else if (var90) {
                  RecoveredUtilsAd.b(var5, var70, var76, var82, 14.0F, 3.0F, RecoveredUtilsH.a(255, 255, 255, 25));
               }

               String var92 = var87 ? this.Q : String.valueOf(var64.n());
               if (var87 && System.currentTimeMillis() / 500L % 2L == 0L) {
                  var92 = var92 + "|";
               }

               var6.a(var5, a(var6, var92, 0.4, var82 - 10.0F), (double)(var70 + 5.0F), (double)(var76 + 3.0F), Color.WHITE, true, 0.4);
               var38 += 22.0F;
            }
         }

         this.R = null;

         for (RecoveredDC var57 : this.I) {
            if (var57.h() && var57.a() == RecoveredDF.KEY) {
               RecoveredDAD var65 = var57.g();
               var6.a(var5, var57.j(), (double)(a + 140.0F), (double)(b + var38 + var28 + 25.0F), Color.WHITE, true, 0.4);
               var38 += 15.0F;
               boolean var71 = this.S == var65;
               String var77 = var71 ? "按下按键..." : a(var65.m());
               float var83 = var6.a(var77, 0.4) + 20.0F;
               float var88 = a + 140.0F;
               float var91 = b + var38 + var28 + 25.0F;
               if (var23 && RecoveredUtilsAd.b(var2, var3, var88, var91, var83, 14.0F)) {
                  this.R = var65;
               }

               RecoveredUtilsAd.b(var5, var88, var91, var83, 14.0F, 7.0F, RecoveredUtilsH.a(0, 0, 0, 150));
               RecoveredUtilsAd.b(var5, var88, var91, var83, 14.0F, 7.0F, RecoveredUtilsH.a(54, 98, 236, var71 ? 150 : 60));
               var6.a(var5, var77, (double)(var88 + 8.0F), (double)(var91 + 3.0F), Color.WHITE, true, 0.4);
               var38 += 20.0F;
            }
         }

         this.w = var38 - d + 25.0F;
         this.ab -= var38;
         float var50 = this.w + d;
         if (var50 > d - 25.0F) {
            this.X.a(true);
            if (this.Y.a(1000.0)) {
               this.X.a = 0.0F;
            } else {
               this.X.a = 255.0F;
            }

            float var58 = d - 25.0F;
            float var66 = (float)RecoveredUtilsS.a((double)(-this.n.c / -this.ab), 0.0, 1.0);
            float var72 = var58 / var50 * var58;
            float var78 = Math.max(var72, 20.0F);
            float var84 = var66 * (var58 - var78);
            RecoveredUtilsAd.b(var5, a + c - 8.0F, b + 20.0F + var84, 3.0F, var78, 1.5F, RecoveredUtilsAd.a(3630060, this.X.c / 255.0F));
         }
      }

      if (this.L != null) {
         float var24 = ((float)var2 - a - 140.0F) / (c - 160.0F);
         float var29 = this.L.m() + (this.L.n() - this.L.m()) * var24;
         if (var29 < this.L.m()) {
            var29 = this.L.m();
         }

         if (var29 > this.L.n()) {
            var29 = this.L.n();
         }

         var29 = (float)Math.round(var29 / this.L.o()) * this.L.o();
         this.L.a(var29);
      }

      if (this.B && this.Z) {
         a = a + (float)(var2 - this.g[0]);
         b = b + (float)(var3 - this.g[1]);
         this.a(var2, var3);
      }

      if (this.H != null && !this.h && this.A && this.Z) {
         c = c + (float)(var2 - this.g[0]);
         d = d + (float)(var3 - this.g[1]);
         if (c < 500.0F) {
            c = 500.0F;
         }

         if (d < 300.0F) {
            d = 300.0F;
         }

         this.a(var2, var3);
      }

      if (this.E == null && this.S == null) {
         this.G.a = 5.0F;
      } else {
         this.G.a = 250.0F;
         this.T = this.E != null ? this.E.h() : this.S.j();
      }

      this.G.a(true);
      if (this.G.c > 6.0F) {
         RecoveredUtilsAd.a(var5, a, b, a + this.i.c, b + this.j.c, RecoveredUtilsH.a(0, 0, 0, (int)this.G.c / 2));
         var6.a(this.G.c / 255.0F);
         String var25 = "Press a key to bind " + this.T;
         String var31 = "(Press ESC to remove/cancel key bind)";
         var6.a(
            var5,
            var25,
            (double)(a + this.i.c / 2.0F - var6.a(var25, 0.6) / 2.0F),
            (double)b + ((double)this.j.c - var6.a(true, 0.6)) / 2.0 - 10.0,
            Color.WHITE,
            true,
            0.6
         );
         var6.a(
            var5,
            var31,
            (double)(a + this.i.c / 2.0F - var6.a(var31, 0.4) / 2.0F),
            (double)b + ((double)this.j.c - var6.a(true, 0.4)) / 2.0 + 15.0,
            Color.WHITE,
            true,
            0.4
         );
         var6.a(1.0F);
      }

      if (this.e != null) {
         RecoveredUtilsRendererD.b.a(0.5F);
         RecoveredUtilsRendererD.b.a(var5, RecoveredUtilsL.a, (double)(a + this.i.c - 10.0F), (double)(b + this.j.c - 10.0F), Color.WHITE, false, 0.3);
         RecoveredUtilsRendererD.b.a(1.0F);
      }

      RecoveredUtilsAi.a();
   }

   public void a(double var1, double var3) {
      this.a((int)var1, (int)var3);
   }

   public void a(int var1, int var2) {
      this.g[0] = var1;
      this.g[1] = var2;
   }
}
