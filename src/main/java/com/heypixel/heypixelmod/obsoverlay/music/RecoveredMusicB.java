package com.heypixel.heypixelmod.obsoverlay.music;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.FloatControl.Type;

public class RecoveredMusicB {
   private static final int READ_BUFFER = 8192;
   private volatile RecoveredMusicB.InnerA state = RecoveredMusicB.InnerA.STOPPED;
   private volatile File currentFile;
   private volatile String currentLabel = "";
   private volatile RecoveredMusicB.InnerB streamOpener;
   private volatile boolean networkStream;
   private volatile Thread worker;
   private volatile SourceDataLine line;
   private volatile AudioInputStream stream;
   private volatile boolean abort;
   private volatile boolean paused;
   private volatile float volume = 0.7F;
   private volatile long pendingSeek = -1L;
   private volatile long positionOffset = 0L;
   private volatile long totalFrames = -1L;
   private volatile float sampleRate = 44100.0F;
   private volatile int frameSize = 4;
   private volatile long durationHintMillis = -1L;
   private volatile String lastError;
   private volatile Runnable endListener;
   private volatile long startedAt = 0L;

   public void a(Runnable var1) {
      this.endListener = var1;
   }

   public String a() {
      return this.lastError;
   }

   public void b() {
      this.lastError = null;
   }

   public RecoveredMusicB.InnerA c() {
      return this.state;
   }

   public boolean d() {
      return this.state != RecoveredMusicB.InnerA.STOPPED;
   }

   public boolean e() {
      return this.state == RecoveredMusicB.InnerA.PAUSED;
   }

   public File f() {
      return this.currentFile;
   }

   public String g() {
      return this.currentLabel;
   }

   public boolean h() {
      return this.networkStream;
   }

   public float i() {
      return this.volume;
   }

   public void a(float var1) {
      this.volume = Math.max(0.0F, Math.min(1.0F, var1));
      this.q();
   }

   public long j() {
      if (this.durationHintMillis > 0L) {
         return this.durationHintMillis;
      } else {
         return this.totalFrames > 0L && this.sampleRate > 0.0F ? (long)((double)((float)this.totalFrames / this.sampleRate) * 1000.0) : -1L;
      }
   }

   public long k() {
      SourceDataLine var1 = this.line;
      if (var1 == null) {
         return 0L;
      } else {
         long var2 = this.positionOffset + var1.getLongFramePosition();
         if (var2 < 0L) {
            var2 = 0L;
         }

         return this.sampleRate <= 0.0F ? 0L : (long)((double)((float)var2 / this.sampleRate) * 1000.0);
      }
   }

   public synchronized void a(File var1, long var2) {
      if (var1 != null && var1.isFile()) {
         this.a(() -> RecoveredMusicA.c(var1), var1.getName(), var2, false, var1);
      } else {
         this.p();
         this.lastError = "文件不存在";
      }
   }

   public synchronized void a(RecoveredMusicB.InnerB var1, String var2, long var3) {
      if (var1 == null) {
         this.p();
         this.lastError = "无效的音频源";
      } else {
         this.a(var1, var2 == null ? "网络音频" : var2, var3, true, null);
      }
   }

   private void a(RecoveredMusicB.InnerB var1, String var2, long var3, boolean var5, File var6) {
      this.p();
      this.currentFile = var6;
      this.currentLabel = var2;
      this.streamOpener = var1;
      this.networkStream = var5;
      this.durationHintMillis = var3;
      this.abort = false;
      this.paused = false;
      this.pendingSeek = -1L;
      this.positionOffset = 0L;
      this.totalFrames = -1L;
      this.state = RecoveredMusicB.InnerA.PLAYING;
      this.startedAt = System.currentTimeMillis();
      Thread var7 = new Thread(() -> this.a(var1), "EixClient-MusicPlayer");
      var7.setDaemon(true);
      this.worker = var7;
      var7.start();
   }

   public synchronized void l() {
      if (this.state == RecoveredMusicB.InnerA.PLAYING) {
         this.m();
      } else if (this.state == RecoveredMusicB.InnerA.PAUSED) {
         this.n();
      }
   }

   public synchronized void m() {
      if (this.state == RecoveredMusicB.InnerA.PLAYING) {
         this.paused = true;
         this.state = RecoveredMusicB.InnerA.PAUSED;
      }
   }

   public synchronized void n() {
      if (this.state == RecoveredMusicB.InnerA.PAUSED) {
         this.paused = false;
         this.state = RecoveredMusicB.InnerA.PLAYING;
      }
   }

   public synchronized void o() {
      this.p();
   }

   private void p() {
      this.abort = true;
      SourceDataLine var1 = this.line;
      if (var1 != null) {
         try {
            var1.flush();
         } catch (Throwable var5) {
         }
      }

      Thread var2 = this.worker;
      if (var2 != null && var2.isAlive() && var2 != Thread.currentThread()) {
         try {
            var2.join(1200L);
         } catch (InterruptedException var4) {
            Thread.currentThread().interrupt();
         }
      }

      this.worker = null;
      this.state = RecoveredMusicB.InnerA.STOPPED;
      this.paused = false;
      this.pendingSeek = -1L;
      this.streamOpener = null;
      this.networkStream = false;
   }

   public void a(long var1) {
      if (!(this.sampleRate <= 0.0F)) {
         long var3 = (long)((double)var1 / 1000.0 * (double)this.sampleRate);
         if (var3 < 0L) {
            var3 = 0L;
         }

         long var5 = this.totalFrames;
         if (var5 > 0L && var3 > var5) {
            var3 = var5;
         }

         this.pendingSeek = var3;
      }
   }

   public void b(long var1) {
      this.a(this.k() + var1);
   }

   private void a(RecoveredMusicB.InnerB var1) {
      AudioInputStream var2 = null;
      SourceDataLine var3 = null;
      boolean var4 = false;

      try {
         var2 = var1.open();
         AudioFormat var5 = var2.getFormat();
         this.sampleRate = var5.getSampleRate() > 0.0F ? var5.getSampleRate() : 44100.0F;
         this.frameSize = Math.max(1, var5.getFrameSize());
         this.totalFrames = var2.getFrameLength();
         var3 = AudioSystem.getSourceDataLine(var5);
         int var6 = Math.max(8192, (int)(this.sampleRate * (float)this.frameSize * 0.2F));
         var6 -= var6 % this.frameSize;
         var3.open(var5, var6);
         this.stream = var2;
         this.line = var3;
         this.a(var3);
         var3.start();
         int var7 = Math.max(8192, this.frameSize * 256);
         byte[] var8 = new byte[var7 - var7 % this.frameSize];

         while (!this.abort) {
            if (this.paused) {
               if (var3.isRunning()) {
                  var3.stop();
               }

               c(15L);
            } else {
               if (!var3.isRunning()) {
                  var3.start();
               }

               long var9 = this.pendingSeek;
               if (var9 >= 0L) {
                  this.pendingSeek = -1L;
                  this.a(var9, var3);
               } else {
                  int var11 = var2.read(var8, 0, var8.length);
                  if (var11 < 0) {
                     var4 = true;
                     break;
                  }

                  if (var11 != 0) {
                     var3.write(var8, 0, var11);
                  }
               }
            }
         }

         if (!this.abort) {
            try {
               var3.drain();
            } catch (Throwable var25) {
            }
         } else {
            try {
               var3.flush();
            } catch (Throwable var24) {
            }
         }
      } catch (Throwable var26) {
         if (!this.abort) {
            this.lastError = var26.getMessage() == null ? var26.getClass().getSimpleName() : var26.getMessage();
         }
      } finally {
         this.stream = null;
         this.line = null;
         a((AutoCloseable)var3);
         a(var2);
         boolean var13 = this.abort;
         if (!var13) {
            this.state = RecoveredMusicB.InnerA.STOPPED;
            this.paused = false;
         }

         Runnable var14 = this.endListener;
         if (var4 && !var13 && var14 != null) {
            try {
               var14.run();
            } catch (Throwable var23) {
            }
         }
      }
   }

   private void a(long var1, SourceDataLine var3) {
      try {
         try {
            var3.stop();
            var3.flush();
            AudioInputStream var4 = this.stream;
            if (var4 == null) {
               return;
            }

            long var5 = this.positionOffset + var3.getLongFramePosition();
            if (var1 < var5) {
               a(var4);
               RecoveredMusicB.InnerB var7 = this.streamOpener;
               if (var7 == null) {
                  return;
               }

               AudioInputStream var8 = var7.open();
               this.stream = var8;
               var4 = var8;
               var5 = 0L;
            }

            long var23 = var1 - var5;

            while (var23 > 0L && !this.abort) {
               long var9 = var4.skip(var23);
               if (var9 <= 0L) {
                  break;
               }

               var23 -= var9;
            }

            this.positionOffset = var1 - var3.getLongFramePosition();
            if (this.positionOffset < 0L) {
               this.positionOffset = 0L;
               return;
            }
         } catch (Throwable var21) {
            this.lastError = "跳转失败: " + var21.getMessage();
         }
      } finally {
         if (!this.paused) {
            try {
               var3.start();
            } catch (Throwable var20) {
            }
         }
      }
   }

   private void q() {
      SourceDataLine var1 = this.line;
      if (var1 != null) {
         this.a(var1);
      }
   }

   private void a(SourceDataLine var1) {
      try {
         if (!var1.isControlSupported(Type.MASTER_GAIN)) {
            return;
         }

         FloatControl var2 = (FloatControl)var1.getControl(Type.MASTER_GAIN);
         var2.setValue(a(var2, this.volume));
      } catch (Throwable var3) {
      }
   }

   private static float a(FloatControl var0, float var1) {
      if (var1 <= 1.0E-4F) {
         return var0.getMinimum();
      } else {
         float var2 = (float)(20.0 * Math.log10((double)var1));
         return Math.max(var0.getMinimum(), Math.min(var0.getMaximum(), var2));
      }
   }

   private static void c(long var0) {
      try {
         Thread.sleep(var0);
      } catch (InterruptedException var3) {
         Thread.currentThread().interrupt();
      }
   }

   private static void a(AutoCloseable var0) {
      if (var0 != null) {
         try {
            var0.close();
         } catch (Throwable var2) {
         }
      }
   }

   public static enum InnerA {
      STOPPED,
      PLAYING,
      PAUSED;
   }

   public interface InnerB {
      AudioInputStream open() throws Exception;
   }
}
