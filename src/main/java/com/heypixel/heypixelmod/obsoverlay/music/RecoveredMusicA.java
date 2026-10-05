package com.heypixel.heypixelmod.obsoverlay.music;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Locale;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioFormat.Encoding;

public final class RecoveredMusicA {
   private static final String MP3_READER = "javazoom.spi.mpeg.sampled.file.MpegAudioFileReader";
   private static final String VORBIS_READER = "javazoom.spi.vorbis.sampled.file.VorbisAudioFileReader";
   private static Boolean mp3Available;
   private static Boolean vorbisAvailable;

   private RecoveredMusicA() {
   }

   public static boolean a(File var0) {
      return var0 != null && a(var0.getName());
   }

   public static boolean a(String var0) {
      String var1 = var0.toLowerCase(Locale.ROOT);
      return var1.endsWith(".mp3")
         || var1.endsWith(".wav")
         || var1.endsWith(".wave")
         || var1.endsWith(".ogg")
         || var1.endsWith(".oga")
         || var1.endsWith(".aif")
         || var1.endsWith(".aiff")
         || var1.endsWith(".au");
   }

   public static boolean a() {
      if (mp3Available == null) {
         mp3Available = b("javazoom.spi.mpeg.sampled.file.MpegAudioFileReader");
      }

      return mp3Available;
   }

   public static boolean b() {
      if (vorbisAvailable == null) {
         vorbisAvailable = b("javazoom.spi.vorbis.sampled.file.VorbisAudioFileReader");
      }

      return vorbisAvailable;
   }

   private static boolean b(String var0) {
      try {
         Class.forName(var0);
         return true;
      } catch (Throwable var2) {
         return false;
      }
   }

   private static Object a(String var0, String var1, File var2) {
      try {
         Class var3 = Class.forName(var0);
         Object var4 = var3.getDeclaredConstructor().newInstance();
         Method var5 = var3.getMethod(var1, File.class);
         return var5.invoke(var4, var2);
      } catch (Throwable var6) {
         return null;
      }
   }

   public static AudioFileFormat b(File var0) {
      String var1 = var0.getName().toLowerCase(Locale.ROOT);
      if (var1.endsWith(".mp3")) {
         Object var2 = a("javazoom.spi.mpeg.sampled.file.MpegAudioFileReader", "getAudioFileFormat", var0);
         if (var2 instanceof AudioFileFormat) {
            return (AudioFileFormat)var2;
         }
      } else if (var1.endsWith(".ogg") || var1.endsWith(".oga")) {
         Object var5 = a("javazoom.spi.vorbis.sampled.file.VorbisAudioFileReader", "getAudioFileFormat", var0);
         if (var5 instanceof AudioFileFormat) {
            return (AudioFileFormat)var5;
         }
      }

      try {
         return AudioSystem.getAudioFileFormat(var0);
      } catch (Throwable var4) {
         return null;
      }
   }

   public static AudioInputStream c(File var0) throws Exception {
      AudioInputStream var1 = d(var0);
      return a(var1);
   }

   public static AudioInputStream d(File var0) throws Exception {
      String var1 = var0.getName().toLowerCase(Locale.ROOT);
      if (var1.endsWith(".mp3")) {
         Object var4 = a("javazoom.spi.mpeg.sampled.file.MpegAudioFileReader", "getAudioInputStream", var0);
         if (var4 instanceof AudioInputStream) {
            return (AudioInputStream)var4;
         } else {
            throw new UnsupportedOperationException("MP3 解码器不可用（mp3spi 未加载）");
         }
      } else if (!var1.endsWith(".ogg") && !var1.endsWith(".oga")) {
         return AudioSystem.getAudioInputStream(var0);
      } else {
         Object var2 = a("javazoom.spi.vorbis.sampled.file.VorbisAudioFileReader", "getAudioInputStream", var0);
         if (var2 instanceof AudioInputStream) {
            return (AudioInputStream)var2;
         } else {
            throw new UnsupportedOperationException("OGG 解码器不可用（vorbisspi 未加载）");
         }
      }
   }

   public static AudioInputStream a(InputStream var0, String var1) throws Exception {
      return a(b(var0, var1));
   }

   public static AudioInputStream b(InputStream var0, String var1) throws Exception {
      BufferedInputStream var2 = var0 instanceof BufferedInputStream var3 ? var3 : new BufferedInputStream(var0, 65536);
      RecoveredMusicA.InnerA var6 = c(var2, var1);
      switch (var6) {
         case MP3:
            Object var7 = a("javazoom.spi.mpeg.sampled.file.MpegAudioFileReader", "getAudioInputStream", var2);
            if (var7 instanceof AudioInputStream) {
               return (AudioInputStream)var7;
            }

            throw new UnsupportedOperationException("MP3 解码器不可用（mp3spi 未加载）");
         case OGG:
            Object var4 = a("javazoom.spi.vorbis.sampled.file.VorbisAudioFileReader", "getAudioInputStream", var2);
            if (var4 instanceof AudioInputStream) {
               return (AudioInputStream)var4;
            }

            throw new UnsupportedOperationException("OGG 解码器不可用（vorbisspi 未加载）");
         case FLAC:
            throw new UnsupportedOperationException("FLAC 无损流无法直接播放，请下载后再播（或用 standard/exhigh 档位）");
         case MP4:
            throw new UnsupportedOperationException("MP4/AAC 流无法直接播放，请下载后再播");
         default:
            return AudioSystem.getAudioInputStream(var2);
      }
   }

   public static RecoveredMusicA.InnerA c(InputStream var0, String var1) {
      try {
         if (var0 instanceof BufferedInputStream var2) {
            var2.mark(16);
            byte[] var3 = new byte[12];
            int var4 = var2.read(var3);
            var2.reset();
            if (var4 >= 4) {
               if (var3[0] == 73 && var3[1] == 68 && var3[2] == 51) {
                  return RecoveredMusicA.InnerA.MP3;
               }

               if ((var3[0] & 255) == 255 && (var3[1] & 224) == 224) {
                  return RecoveredMusicA.InnerA.MP3;
               }

               if (var3[0] == 79 && var3[1] == 103 && var3[2] == 103 && var3[3] == 83) {
                  return RecoveredMusicA.InnerA.OGG;
               }

               if (var3[0] == 82 && var3[1] == 73 && var3[2] == 70 && var3[3] == 70) {
                  return RecoveredMusicA.InnerA.WAV;
               }

               if (var3[0] == 70 && var3[1] == 79 && var3[2] == 82 && var3[3] == 77) {
                  return RecoveredMusicA.InnerA.AIFF;
               }

               if (var3[0] == 102 && var3[1] == 76 && var3[2] == 97 && var3[3] == 67) {
                  return RecoveredMusicA.InnerA.FLAC;
               }

               if (var4 >= 8 && var3[4] == 102 && var3[5] == 116 && var3[6] == 121 && var3[7] == 112) {
                  return RecoveredMusicA.InnerA.MP4;
               }

               if (var3[0] == 46 && var3[1] == 115 && var3[2] == 110 && var3[3] == 100) {
                  return RecoveredMusicA.InnerA.AU;
               }
            }
         }
      } catch (Throwable var5) {
      }

      if (var1 != null) {
         String var6 = var1.toLowerCase(Locale.ROOT);
         int var7 = var6.indexOf(63);
         if (var7 >= 0) {
            var6 = var6.substring(0, var7);
         }

         if (var6.endsWith(".mp3")) {
            return RecoveredMusicA.InnerA.MP3;
         }

         if (var6.endsWith(".ogg") || var6.endsWith(".oga")) {
            return RecoveredMusicA.InnerA.OGG;
         }

         if (var6.endsWith(".wav")) {
            return RecoveredMusicA.InnerA.WAV;
         }

         if (var6.endsWith(".aiff") || var6.endsWith(".aif")) {
            return RecoveredMusicA.InnerA.AIFF;
         }

         if (var6.endsWith(".flac")) {
            return RecoveredMusicA.InnerA.FLAC;
         }

         if (var6.endsWith(".mp4") || var6.endsWith(".m4a")) {
            return RecoveredMusicA.InnerA.MP4;
         }
      }

      return RecoveredMusicA.InnerA.UNKNOWN;
   }

   public static boolean a(RecoveredMusicA.InnerA var0) {
      return switch (var0) {
         case MP3 -> a();
         case OGG -> b();
         default -> false;
         case WAV, AIFF, AU -> true;
      };
   }

   private static Object a(String var0, String var1, InputStream var2) {
      try {
         Class var3 = Class.forName(var0);
         Object var4 = var3.getDeclaredConstructor().newInstance();
         Method var5 = var3.getMethod(var1, InputStream.class);
         return var5.invoke(var4, var2);
      } catch (Throwable var6) {
         return null;
      }
   }

   private static AudioInputStream a(AudioInputStream var0) {
      AudioFormat var1 = var0.getFormat();
      if (var1.getEncoding() == Encoding.PCM_SIGNED && var1.getSampleSizeInBits() == 16 && !var1.isBigEndian()) {
         return var0;
      } else {
         float var2 = var1.getSampleRate() > 0.0F ? var1.getSampleRate() : 44100.0F;
         int var3 = Math.max(1, var1.getChannels());
         AudioFormat var4 = new AudioFormat(Encoding.PCM_SIGNED, var2, 16, var3, var3 * 2, var2, false);
         if (AudioSystem.isConversionSupported(var4, var1)) {
            try {
               return AudioSystem.getAudioInputStream(var4, var0);
            } catch (Throwable var6) {
            }
         }

         return var0;
      }
   }

   public static long a(AudioFileFormat var0) {
      if (var0 == null) {
         return -1L;
      } else {
         try {
            if (var0.properties().get("duration") instanceof Number var2) {
               long var3 = var2.longValue();
               if (var3 > 0L) {
                  return var3 / 1000L;
               }
            }
         } catch (Throwable var6) {
         }

         try {
            long var7 = (long)var0.getFrameLength();
            float var8 = var0.getFormat().getSampleRate();
            if (var7 > 0L && var8 > 0.0F) {
               return (long)((double)((float)var7 / var8) * 1000.0);
            }
         } catch (Throwable var5) {
         }

         return -1L;
      }
   }

   public static String a(AudioFileFormat var0, String var1) {
      if (var0 == null) {
         return null;
      } else {
         try {
            Object var2 = var0.properties().get(var1);
            if (var2 == null) {
               return null;
            } else {
               String var3 = String.valueOf(var2).trim();
               return var3.isEmpty() ? null : var3;
            }
         } catch (Throwable var4) {
            return null;
         }
      }
   }

   public static enum InnerA {
      MP3,
      OGG,
      WAV,
      AIFF,
      AU,
      FLAC,
      MP4,
      UNKNOWN;
   }
}
