package com.heypixel.heypixelmod.obsoverlay.utils.renderer;

import com.mojang.blaze3d.systems.RenderSystem;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.joml.Matrix4f;

public class RecoveredUtilsRendererI {
   public static RecoveredUtilsRendererI a;
   private final int b;
   private final Object2IntMap<String> c = new Object2IntOpenHashMap<>();

   public RecoveredUtilsRendererI(String var1, String var2) {
      int var3 = RecoveredUtilsRendererF.l(35633);
      RecoveredUtilsRendererF.a(var3, this.a(var1));
      String var4 = RecoveredUtilsRendererF.m(var3);
      if (var4 != null) {
         throw new RuntimeException("Failed to compile vertex shader (" + var1 + "): " + var4);
      } else {
         int var5 = RecoveredUtilsRendererF.l(35632);
         RecoveredUtilsRendererF.a(var5, this.a(var2));
         String var6 = RecoveredUtilsRendererF.m(var5);
         if (var6 != null) {
            throw new RuntimeException("Failed to compile fragment shader (" + var2 + "): " + var6);
         } else {
            this.b = RecoveredUtilsRendererF.g();
            String var7 = RecoveredUtilsRendererF.b(this.b, var3, var5);
            if (var7 != null) {
               throw new RuntimeException("Failed to link program: " + var7);
            } else {
               RecoveredUtilsRendererF.c(var3);
               RecoveredUtilsRendererF.c(var5);
            }
         }
      }
   }

   private String a(String var1) {
      try {
         return IOUtils.toString(this.getClass().getResourceAsStream("/assets/heypixel/vcx6svvqmet8/shader/" + var1), StandardCharsets.UTF_8);
      } catch (IOException var3) {
         var3.printStackTrace();
         return "";
      }
   }

   public void a() {
      RecoveredUtilsRendererF.n(this.b);
      a = this;
   }

   private int b(String var1) {
      if (this.c.containsKey(var1)) {
         return this.c.getInt(var1);
      } else {
         int var2 = RecoveredUtilsRendererF.b(this.b, var1);
         this.c.put(var1, var2);
         return var2;
      }
   }

   public void a(String var1, boolean var2) {
      RecoveredUtilsRendererF.a(this.b(var1), var2 ? 1 : 0);
   }

   public void a(String var1, int var2) {
      RecoveredUtilsRendererF.a(this.b(var1), var2);
   }

   public void a(String var1, double var2) {
      RecoveredUtilsRendererF.a(this.b(var1), (float)var2);
   }

   public void a(String var1, double var2, double var4) {
      RecoveredUtilsRendererF.a(this.b(var1), (float)var2, (float)var4);
   }

   public void a(String var1, Matrix4f var2) {
      RecoveredUtilsRendererF.a(this.b(var1), var2);
   }

   public void a(String var1, float... var2) {
      int var3 = this.b(var1);
      if (var2.length == 1) {
         RecoveredUtilsRendererF.a(var3, var2[0]);
      } else if (var2.length == 2) {
         RecoveredUtilsRendererF.a(var3, var2[0], var2[1]);
      } else if (var2.length == 3) {
         RecoveredUtilsRendererF.a(var3, var2[0], var2[1], var2[2]);
      } else {
         if (var2.length != 4) {
            throw new IllegalArgumentException("Invalid number of arguments for uniform '" + var1 + "'");
         }

         RecoveredUtilsRendererF.a(var3, var2[0], var2[1], var2[2], var2[3]);
      }
   }

   public void b() {
      this.a("u_Proj", RenderSystem.getProjectionMatrix());
      this.a("u_ModelView", RenderSystem.getModelViewStack().last().pose());
   }
}
