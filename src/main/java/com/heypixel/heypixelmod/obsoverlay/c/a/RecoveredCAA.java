package com.heypixel.heypixelmod.obsoverlay.c.a;

import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAg;
import com.heypixel.heypixelmod.obsoverlay.utils.e.RecoveredUtilsEA;
import com.heypixel.heypixelmod.obsoverlay.utils.e.b.RecoveredUtilsEBC;
import java.awt.Color;
import java.util.Objects;

public class RecoveredCAA {
   public static byte[] a;
   private RecoveredCAB b;
   private String c;
   private long d;
   private long e = System.currentTimeMillis();
   private RecoveredUtilsAg f = new RecoveredUtilsAg(0.0F);
   private RecoveredUtilsAg g = new RecoveredUtilsAg(0.0F);

   public RecoveredCAA(RecoveredCAB var1, String var2, long var3) {
      this.b = var1;
      this.c = var2;
      this.d = var3;
   }

   public void a(float var1, float var2) {
      RecoveredUtilsEA.b(var1 + 2.0F, var2 + 4.0F, this.a(), 20.0F, 5.0F, this.b.a());
      RecoveredUtilsEA.a(var1 + 2.0F, var2 + 4.0F, this.a(), 20.0F, 5.0F);
      RecoveredUtilsEA.a(var1 + 2.0F, var2 + 4.0F, this.a(), 20.0F, 5.0F, new Color(0, 0, 0, 100));
      RecoveredUtilsEA.a(this.c, var1 + 6.0F, var2 + 9.0F, Color.WHITE, RecoveredUtilsEBC.a(12.0F));
   }

   public float a() {
      float var1 = RecoveredUtilsEA.a(this.c, RecoveredUtilsEBC.a(12.0F));
      return var1 + 12.0F;
   }

   public float b() {
      return 24.0F;
   }

   public RecoveredCAB c() {
      return this.b;
   }

   public void a(RecoveredCAB var1) {
      this.b = var1;
   }

   public String d() {
      return this.c;
   }

   public void a(String var1) {
      this.c = var1;
   }

   public long e() {
      return this.d;
   }

   public void a(long var1) {
      this.d = var1;
   }

   public long f() {
      return this.e;
   }

   public void b(long var1) {
      this.e = var1;
   }

   public RecoveredUtilsAg g() {
      return this.f;
   }

   public void a(RecoveredUtilsAg var1) {
      this.f = var1;
   }

   public RecoveredUtilsAg h() {
      return this.g;
   }

   public void b(RecoveredUtilsAg var1) {
      this.g = var1;
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else {
         return !(var1 instanceof RecoveredCAA var2)
            ? false
            : this.d == var2.d && this.e == var2.e && Objects.equals(this.b, var2.b) && Objects.equals(this.c, var2.c);
      }
   }

   protected boolean a(Object var1) {
      return var1 instanceof RecoveredCAA;
   }

   @Override
   public int hashCode() {
      return Objects.hash(this.b, this.c, this.d, this.e);
   }

   @Override
   public String toString() {
      return "Notification(level="
         + this.c()
         + ", message="
         + this.d()
         + ", maxAge="
         + this.e()
         + ", createTime="
         + this.f()
         + ", widthTimer="
         + this.g()
         + ", heightTimer="
         + this.h()
         + ")";
   }
}
