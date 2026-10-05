package com.heypixel.heypixelmod.obsoverlay.music.nowplaying;

final class RecoveredMusicNowplayingD {
   private String trackKey = "";
   private long anchorAt;
   private long basePosition;
   private boolean playing;

   long a() {
      return !this.playing ? this.basePosition : this.basePosition + (System.currentTimeMillis() - this.anchorAt);
   }

   boolean b() {
      return !this.trackKey.isEmpty();
   }

   void c() {
      this.trackKey = "";
      this.anchorAt = 0L;
      this.basePosition = 0L;
      this.playing = false;
   }

   RecoveredMusicNowplayingB a(RecoveredMusicNowplayingB var1) {
      if (var1 != null && !var1.k()) {
         if (var1.l() && var1.e() > 0L) {
            this.c();
            return var1;
         } else {
            long var2 = System.currentTimeMillis();
            if (!var1.n().equals(this.trackKey)) {
               this.trackKey = var1.n();
               this.basePosition = 0L;
               this.anchorAt = var2;
               this.playing = var1.g();
               return var1.a(0L);
            } else {
               if (var1.g() != this.playing) {
                  if (var1.g()) {
                     this.anchorAt = var2;
                  } else {
                     this.basePosition = this.basePosition + (var2 - this.anchorAt);
                  }

                  this.playing = var1.g();
               }

               long var4 = this.a();
               long var6 = var1.f();
               if (var6 > 0L && var4 > var6) {
                  var4 = var6;
               }

               if (var4 < 0L) {
                  var4 = 0L;
               }

               return var1.a(var4);
            }
         }
      } else {
         this.c();
         return var1 == null ? RecoveredMusicNowplayingB.NONE : var1;
      }
   }
}
