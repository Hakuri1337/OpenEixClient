package com.heypixel.heypixelmod.obsoverlay.d;

import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAB;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAE;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAF;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class RecoveredDD {
   private final RecoveredDA a;
   private final String b;
   private RecoveredDF c;
   private Consumer<RecoveredDC> d;
   private Supplier<Boolean> e;
   private boolean f;
   private float g;
   private float h;
   private float i;
   private float j;
   private String[] k;
   private int l;
   private String m;
   private float n;
   private float o;
   private int p;

   private RecoveredDD(RecoveredDA var1, String var2) {
      this.a = var1;
      this.b = var2;
   }

   public static RecoveredDD a(RecoveredDA var0, String var1) {
      return new RecoveredDD(var0, var1);
   }

   public RecoveredDD a(RecoveredDF var1) {
      this.c = var1;
      return this;
   }

   public RecoveredDD a(boolean var1) {
      if (this.c == null) {
         this.a(RecoveredDF.BOOLEAN);
      }

      if (this.c != RecoveredDF.BOOLEAN) {
         throw new IllegalStateException("Value type is not boolean");
      } else {
         this.f = var1;
         return this;
      }
   }

   public RecoveredDD a(float var1) {
      if (this.c == null) {
         this.a(RecoveredDF.FLOAT);
      }

      if (this.c != RecoveredDF.FLOAT) {
         throw new IllegalStateException("Value type is not float");
      } else {
         this.g = var1;
         return this;
      }
   }

   public RecoveredDD b(float var1) {
      if (this.c == null) {
         this.a(RecoveredDF.FLOAT);
      }

      if (this.c != RecoveredDF.FLOAT) {
         throw new IllegalStateException("Value type is not float");
      } else {
         this.h = var1;
         return this;
      }
   }

   public RecoveredDD c(float var1) {
      if (this.c == null) {
         this.a(RecoveredDF.FLOAT);
      }

      if (this.c != RecoveredDF.FLOAT) {
         throw new IllegalStateException("Value type is not float");
      } else {
         this.i = var1;
         return this;
      }
   }

   public RecoveredDD d(float var1) {
      if (this.c == null) {
         this.a(RecoveredDF.FLOAT);
      }

      if (this.c != RecoveredDF.FLOAT) {
         throw new IllegalStateException("Value type is not float");
      } else {
         this.j = var1;
         return this;
      }
   }

   public RecoveredDD a(String... var1) {
      if (this.c == null) {
         this.a(RecoveredDF.MODE);
      }

      if (this.c != RecoveredDF.MODE) {
         throw new IllegalStateException("Value type is not mode");
      } else {
         this.k = var1;
         return this;
      }
   }

   public RecoveredDD a(int var1) {
      if (this.c == null) {
         this.a(RecoveredDF.MODE);
      }

      if (this.c != RecoveredDF.MODE) {
         throw new IllegalStateException("Value type is not mode");
      } else {
         this.l = var1;
         return this;
      }
   }

   public RecoveredDD a(String var1) {
      if (this.c == null) {
         this.a(RecoveredDF.STRING);
      }

      if (this.c != RecoveredDF.STRING) {
         throw new IllegalStateException("Value type is not string");
      } else {
         this.m = var1;
         return this;
      }
   }

   public RecoveredDD e(float var1) {
      if (this.c == null) {
         this.a(RecoveredDF.DRAG);
      }

      if (this.c != RecoveredDF.DRAG) {
         throw new IllegalStateException("Value type is not drag");
      } else {
         this.n = var1;
         return this;
      }
   }

   public RecoveredDD f(float var1) {
      if (this.c == null) {
         this.a(RecoveredDF.DRAG);
      }

      if (this.c != RecoveredDF.DRAG) {
         throw new IllegalStateException("Value type is not drag");
      } else {
         this.o = var1;
         return this;
      }
   }

   public RecoveredDD b(int var1) {
      if (this.c == null) {
         this.a(RecoveredDF.KEY);
      }

      if (this.c != RecoveredDF.KEY) {
         throw new IllegalStateException("Value type is not key");
      } else {
         this.p = var1;
         return this;
      }
   }

   public RecoveredDD a(Consumer<RecoveredDC> var1) {
      this.d = var1;
      return this;
   }

   public RecoveredDD a(Supplier<Boolean> var1) {
      this.e = var1;
      return this;
   }

   public RecoveredDC a() {
      if (this.c == null) {
         throw new IllegalStateException("Value type is not set");
      } else {
         switch (this.c) {
            case BOOLEAN:
               return new RecoveredDAA(this.a, this.b, this.f, this.d, this.e);
            case FLOAT:
               return new RecoveredDAC(this.a, this.b, this.g, this.h, this.i, this.j, this.d, this.e);
            case MODE:
               if (this.k == null) {
                  throw new IllegalStateException("Modes are not set");
               }

               return new RecoveredDAE(this.a, this.b, this.k, this.l, this.d, this.e);
            case STRING:
               if (this.m == null) {
                  throw new IllegalStateException("Default string value is not set");
               }

               return new RecoveredDAF(this.a, this.b, this.m, this.d, this.e);
            case DRAG:
               return new RecoveredDAB(this.a, this.b, this.n, this.o, this.d, this.e);
            case KEY:
               return new RecoveredDAD(this.a, this.b, this.p, this.d, this.e);
            default:
               throw new IllegalStateException("Unknown value type");
         }
      }
   }
}
