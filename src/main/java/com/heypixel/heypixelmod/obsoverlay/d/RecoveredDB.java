package com.heypixel.heypixelmod.obsoverlay.d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecoveredDB {
   private final List<RecoveredDA> a = new ArrayList<>();
   private final Map<String, RecoveredDA> b = new HashMap<>();

   public void a(RecoveredDA var1) {
      this.a.add(var1);
      this.b.put(var1.h().toLowerCase(), var1);
   }

   public RecoveredDA a(String var1) {
      return this.b.get(var1.toLowerCase());
   }

   public List<RecoveredDA> a() {
      return this.a;
   }
}
