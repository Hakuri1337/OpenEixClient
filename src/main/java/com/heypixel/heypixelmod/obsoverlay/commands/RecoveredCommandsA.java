package com.heypixel.heypixelmod.obsoverlay.commands;

public abstract class RecoveredCommandsA {
   private String a;
   private String b;
   private String[] c;

   public RecoveredCommandsA(String var1, String var2, String[] var3) {
      this.a = var1;
      this.b = var2;
      this.c = var3;
   }

   public RecoveredCommandsA() {
   }

   protected void a() {
      if (this.getClass().isAnnotationPresent(CommandInfo.class)) {
         CommandInfo var1 = this.getClass().getAnnotation(CommandInfo.class);
         this.a = var1.a();
         this.b = var1.b();
         this.c = var1.c();
      }
   }

   public abstract void a(String[] var1);

   public abstract String[] b(String[] var1);

   public String b() {
      return this.a;
   }

   public String c() {
      return this.b;
   }

   public String[] d() {
      return this.c;
   }
}
