package com.heypixel.heypixelmod.obsoverlay.music.a;

import java.io.IOException;

public class RecoveredMusicAE extends IOException {
   private final int code;

   public RecoveredMusicAE(int var1, String var2) {
      super(var2);
      this.code = var1;
   }

   public int a() {
      return this.code;
   }

   public boolean b() {
      return this.code == 429;
   }
}
