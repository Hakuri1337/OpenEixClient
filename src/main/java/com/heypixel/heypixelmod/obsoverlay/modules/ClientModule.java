package com.heypixel.heypixelmod.obsoverlay.modules;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.c.a.RecoveredCAA;
import com.heypixel.heypixelmod.obsoverlay.c.a.RecoveredCAB;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDA;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.ClickGUIModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.DynamicIslandHud;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.HUDModule;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAg;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAh;
import com.heypixel.heypixelmod.obsoverlay.utils.c.RecoveredUtilsCC;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientModule extends RecoveredDA {
   private static final Logger c = LogManager.getLogger(ClientModule.class);
   protected static final Minecraft a = Minecraft.getInstance();
   public static boolean b = true;
   private final RecoveredUtilsAg d = new RecoveredUtilsAg(100.0F);
   private String e;
   private String f;
   private String g;
   private String h;
   private String i;
   private ModuleCategory j;
   private boolean k;
   private int l = 0;
   private int m;

   public ClientModule(String var1, String var2, ModuleCategory var3) {
      this.e = var1;
      this.h = var2;
      this.j = var3;
      super.c(var1);
      this.p();
   }

   public ClientModule(String var1, String var2, String var3, ModuleCategory var4) {
      this.e = var1;
      this.f = var2;
      this.h = var3;
      this.j = var4;
      super.c(var1);
      this.p();
   }

   public ClientModule() {
   }

   public String a() {
      return this.f;
   }

   @Override
   public String b() {
      return this.e;
   }

   private void p() {
      StringBuilder var1 = new StringBuilder();
      char[] var2 = this.e.toCharArray();

      for (int var3 = 0; var3 < var2.length - 1; var3++) {
         if (Character.isLowerCase(var2[var3]) && Character.isUpperCase(var2[var3 + 1])) {
            var1.append(var2[var3]).append(" ");
         } else {
            var1.append(var2[var3]);
         }
      }

      var1.append(var2[var2.length - 1]);
      this.g = var1.toString();
   }

   protected void c() {
      if (this.getClass().isAnnotationPresent(ModuleInfo.class)) {
         ModuleInfo var1 = this.getClass().getAnnotation(ModuleInfo.class);
         this.e = var1.a();
         this.f = var1.b();
         this.h = var1.c();
         this.j = var1.d();
         super.c(this.e);
         this.p();
         EixClient.a().e().a(this);
      }
   }

   public void d() {
   }

   public void e() {
   }

   public void f() {
      this.a(!this.k);
   }

   public RecoveredUtilsAg g() {
      return this.d;
   }

   @Override
   public String h() {
      try {
         EixClient var1 = EixClient.a();
         if (var1 != null && var1.g() != null) {
            ClickGUIModule var2 = var1.g().a(ClickGUIModule.class);
            if (var2 != null && var2.c != null) {
               if (var2.c.l().equals("English")) {
                  return this.e;
               } else {
                  return this.f != null && !this.f.isEmpty() ? this.f : this.e;
               }
            } else {
               return this.e;
            }
         } else {
            return this.e;
         }
      } catch (Exception var3) {
         return this.e;
      }
   }

   public String i() {
      return this.g;
   }

   public String j() {
      return this.h;
   }

   public String k() {
      return this.i;
   }

   public void a(String var1) {
      if (var1 == null) {
         this.i = null;
         b = true;
      } else if (!var1.equals(this.i)) {
         this.i = var1;
         b = true;
      }
   }

   public ModuleCategory l() {
      return this.j;
   }

   public boolean m() {
      return this.k;
   }

   public void a(boolean var1) {
      if (this.k != var1) {
         try {
            EixClient var2 = EixClient.a();
            if (var1) {
               this.k = true;
               var2.b().a(this);
               this.d();
               DynamicIslandHud.a(this, true);
               if (!(this instanceof ClickGUIModule)) {
                  HUDModule var3 = EixClient.a().g().a(HUDModule.class);
                  if (var3 != null && var3.f.m()) {
                     RecoveredUtilsAh.a("enable.wav", 1.0F);
                  }

                  if (var2.j() != null) {
                     RecoveredCAA var4 = new RecoveredCAA(RecoveredCAB.a, this.h() + " Enabled!", 3000L);
                     var2.j().a(var4);
                  }
               }
            } else {
               this.k = false;
               var2.b().b(this);
               this.e();
               DynamicIslandHud.a(this, false);
               if (!(this instanceof ClickGUIModule)) {
                  HUDModule var6 = EixClient.a().g().a(HUDModule.class);
                  if (var6 != null && var6.f.m()) {
                     RecoveredUtilsAh.a("disable.wav", 1.0F);
                  }

                  if (var2.j() != null) {
                     RecoveredCAA var7 = new RecoveredCAA(RecoveredCAB.d, this.h() + " Disabled!", 3000L);
                     var2.j().a(var7);
                  }
               }
            }

            RecoveredUtilsCC.d();
         } catch (Exception var5) {
            c.warn("切换模块状态失败: " + this.e, (Throwable)var5);
         }
      }
   }

   public int n() {
      return this.l;
   }

   public void a(int var1) {
      this.l = var1;
   }

   public int o() {
      return this.m;
   }

   public void b(int var1) {
      this.m = var1;
   }
}
