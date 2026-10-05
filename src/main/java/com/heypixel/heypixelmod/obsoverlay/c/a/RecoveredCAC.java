package com.heypixel.heypixelmod.obsoverlay.c.a;

import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAB;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplS;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAg;
import com.heypixel.heypixelmod.obsoverlay.utils.e.RecoveredUtilsEA;
import io.github.humbleui.skija.ClipMode;
import io.github.humbleui.skija.Path;
import io.github.humbleui.types.Rect;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class RecoveredCAC {
   private final List<RecoveredCAA> a = new CopyOnWriteArrayList<>();

   public void a(RecoveredCAA var1) {
      if (!this.a.contains(var1)) {
         this.a.add(var1);
      }
   }

   public void a(RecoveredEventsImplS var1, RecoveredDAB var2) {
      float var3 = 5.0F;
      Path var4 = new Path();
      var4.addRect(Rect.makeXYWH(var2.n(), var2.o() + 5.0F, var2.p() + 10.0F, var2.q()));
      RecoveredUtilsEA.c();
      RecoveredUtilsEA.e().clipPath(var4, ClipMode.INTERSECT, true);

      for (RecoveredCAA var6 : this.a) {
         float var7 = var6.a();
         var3 += var6.b();
         RecoveredUtilsAg var8 = var6.g();
         RecoveredUtilsAg var9 = var6.h();
         float var10 = (float)(System.currentTimeMillis() - var6.f());
         if (var10 > (float)var6.e()) {
            var8.a = 0.0F;
            var9.a = 0.0F;
            if (var8.b(true)) {
               this.a.remove(var6);
            }
         } else {
            var8.a = var7;
            var9.a = var3;
         }

         var8.a(true);
         var9.a(true);
         var2.c(Math.max(var7, var2.p()));
         var2.d(var3);
         var6.a(var2.n() - var6.a() + var8.c + 2.0F, var2.o() - var6.b() + var9.c);
      }

      RecoveredUtilsEA.d();
   }
}
