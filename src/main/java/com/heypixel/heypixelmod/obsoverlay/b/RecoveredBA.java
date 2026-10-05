package com.heypixel.heypixelmod.obsoverlay.b;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecoveredBA {
   public Map<String, RecoveredBA.InnerB> modules = new HashMap<>();
   public List<String> friends = new ArrayList<>();
   public List<String> killSays = new ArrayList<>();
   public List<String> spammerMessages = new ArrayList<>();
   public RecoveredBA.InnerC proxy = new RecoveredBA.InnerC();
   public RecoveredBA.InnerA gui = new RecoveredBA.InnerA();

   public static class InnerA {
      public float x = 0.0F;
      public float y = 0.0F;
      public float width = 600.0F;
      public float height = 400.0F;
   }

   public static class InnerB {
      public int key;
      public boolean enabled;
      public Map<String, Object> values = new HashMap<>();
   }

   public static class InnerC {
      public String host;
      public int port;
   }
}
