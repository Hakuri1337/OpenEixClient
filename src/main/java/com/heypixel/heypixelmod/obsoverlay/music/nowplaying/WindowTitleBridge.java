package com.heypixel.heypixelmod.obsoverlay.music.nowplaying;

import com.sun.jna.Callback;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.IntByReference;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIOptions;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class WindowTitleBridge {
   private static final String[] KNOWN_PROCESSES = new String[]{
      "cloudmusic.exe",
      "qqmusic.exe",
      "kugou.exe",
      "kwmusic.exe",
      "kuwo.exe",
      "spotify.exe",
      "foobar2000.exe",
      "aimp.exe",
      "music.ui.exe",
      "musicbee.exe",
      "potplayer.exe",
      "vlc.exe"
   };
   private static final String[] TITLE_SUFFIXES = new String[]{
      " - 网易云音乐", " - QQ音乐", " - 酷狗音乐", " - 酷我音乐", " - Spotify", " - foobar2000", " - AIMP", " - MusicBee", " - PotPlayer", " - VLC media player"
   };
   private static final String[] IDLE_TITLES = new String[]{"网易云音乐", "qq音乐", "酷狗音乐", "酷我音乐", "spotify", "musicbee", "aimp", "foobar2000"};
   private static Boolean available;
   private static long lastPollAt;
   private static List<WindowTitleBridge.InnerB> lastResult = List.of();
   private static final Map<Integer, String> processNameCache = new ConcurrentHashMap<>();

   private WindowTitleBridge() {
   }

   public static boolean a() {
      if (available == null) {
         try {
            available = WindowTitleBridge.InnerC.INSTANCE != null && WindowTitleBridge.InnerA.INSTANCE != null;
         } catch (Throwable var1) {
            available = false;
         }
      }

      return available;
   }

   public static List<WindowTitleBridge.InnerB> b() {
      long var0 = System.currentTimeMillis();
      if (var0 - lastPollAt < 1000L) {
         return lastResult;
      } else {
         lastPollAt = var0;
         if (!a()) {
            return lastResult = List.of();
         } else {
            try {
               List<WindowTitleBridge.WindowInfo> var2 = d();
               ArrayList var3 = new ArrayList(2);
               ArrayList var4 = new ArrayList(4);

               for (WindowTitleBridge.WindowInfo var6 : var2) {
                  WindowTitleBridge.InnerB var7 = a(var6.title, var6.processName);
                  if (var7 != null) {
                     if (var6.processName.contains("cloudmusic")) {
                        var3.add(var7);
                     } else {
                        var4.add(var7);
                     }
                  }
               }

               var3.addAll(var4);
               lastResult = var3;
               return var3;
            } catch (Throwable var8) {
               return lastResult = List.of();
            }
         }
      }
   }

   public static boolean c() {
      if (!a()) {
         return false;
      } else {
         try {
            for (WindowTitleBridge.WindowInfo var1 : d()) {
               if (var1.processName.contains("cloudmusic")) {
                  return true;
               }
            }
         } catch (Throwable var2) {
         }

         return false;
      }
   }

   private static List<WindowTitleBridge.WindowInfo> d() {
      ArrayList var0 = new ArrayList(24);
      WindowTitleBridge.InnerC.INSTANCE.EnumWindows((var1, var2) -> {
         try {
            if (!WindowTitleBridge.InnerC.INSTANCE.IsWindowVisible(var1)) {
               return true;
            }

            int var3 = WindowTitleBridge.InnerC.INSTANCE.GetWindowTextLengthW(var1);
            if (var3 <= 0 || var3 > 512) {
               return true;
            }

            char[] var4 = new char[var3 + 2];
            int var5 = WindowTitleBridge.InnerC.INSTANCE.GetWindowTextW(var1, var4, var4.length);
            if (var5 <= 0) {
               return true;
            }

            String var6 = Native.toString(var4).trim();
            if (var6.isEmpty()) {
               return true;
            }

            IntByReference var7 = new IntByReference();
            WindowTitleBridge.InnerC.INSTANCE.GetWindowThreadProcessId(var1, var7);
            String var8 = a(var7.getValue());
            if (var8.isEmpty() || !a(var8)) {
               return true;
            }

            var0.add(new WindowTitleBridge.WindowInfo(var6, var8));
         } catch (Throwable var9) {
         }

         return true;
      }, Pointer.NULL);
      return var0;
   }

   private static String a(int var0) {
      if (var0 <= 0) {
         return "";
      } else {
         String var1 = processNameCache.get(var0);
         if (var1 != null) {
            return var1;
         } else {
            String var2 = "";
            Pointer var3 = null;

            try {
               var3 = WindowTitleBridge.InnerA.INSTANCE.OpenProcess(4096, false, var0);
               if (var3 != null) {
                  char[] var4 = new char[1024];
                  IntByReference var5 = new IntByReference(var4.length);
                  if (WindowTitleBridge.InnerA.INSTANCE.QueryFullProcessImageNameW(var3, 0, var4, var5)) {
                     String var6 = Native.toString(var4);
                     int var7 = Math.max(var6.lastIndexOf(92), var6.lastIndexOf(47));
                     var2 = (var7 >= 0 ? var6.substring(var7 + 1) : var6).toLowerCase(Locale.ROOT);
                  }
               }
            } catch (Throwable var16) {
            } finally {
               if (var3 != null) {
                  try {
                     WindowTitleBridge.InnerA.INSTANCE.CloseHandle(var3);
                  } catch (Throwable var15) {
                  }
               }
            }

            if (processNameCache.size() > 256) {
               processNameCache.clear();
            }

            processNameCache.put(var0, var2);
            return var2;
         }
      }
   }

   private static boolean a(String var0) {
      for (String var4 : KNOWN_PROCESSES) {
         if (var4.equals(var0)) {
            return true;
         }
      }

      return false;
   }

   static WindowTitleBridge.InnerB a(String var0, String var1) {
      String var2 = var0.trim();
      String var3 = var2.toLowerCase(Locale.ROOT);

      for (String var7 : TITLE_SUFFIXES) {
         if (var3.endsWith(var7.toLowerCase(Locale.ROOT))) {
            var2 = var2.substring(0, var2.length() - var7.length()).trim();
            break;
         }
      }

      var2 = var2.replaceFirst("^[▶►⏸]\\s*", "");
      var2 = var2.replaceFirst("^(正在播放|Now Playing|暂停中)[:：]\\s*", "");
      var2 = var2.trim();
      if (var2.isEmpty()) {
         return null;
      } else {
         String var12 = var2.toLowerCase(Locale.ROOT);

         for (String var8 : IDLE_TITLES) {
            if (var12.equals(var8)) {
               return null;
            }
         }

         String[] var14 = var2.split("\\s+-\\s+");
         String var18 = "";
         String var16;
         if (var14.length >= 2) {
            var16 = var14[0].trim();
            var18 = var14[1].trim();
         } else {
            var16 = var2.trim();
         }

         if (b(var16) && var16.length() <= 120) {
            if (!var18.isEmpty() && !b(var18)) {
               var18 = "";
            }

            if (var16.equalsIgnoreCase(var18)) {
               var18 = "";
            }

            return new WindowTitleBridge.InnerB(var1, var16, var18, var0);
         } else {
            return null;
         }
      }
   }

   private static boolean b(String var0) {
      for (int var1 = 0; var1 < var0.length(); var1++) {
         if (Character.isLetterOrDigit(var0.charAt(var1))) {
            return true;
         }
      }

      return false;
   }

   private static record WindowInfo(String title, String processName) {
   }

   public interface InnerA extends StdCallLibrary {
      WindowTitleBridge.InnerA INSTANCE = Native.load("kernel32", WindowTitleBridge.InnerA.class, W32APIOptions.DEFAULT_OPTIONS);

      Pointer OpenProcess(int var1, boolean var2, int var3);

      boolean QueryFullProcessImageNameW(Pointer var1, int var2, char[] var3, IntByReference var4);

      boolean CloseHandle(Pointer var1);
   }

   public static final class InnerB {
      private final String processName;
      private final String song;
      private final String artist;
      private final String rawTitle;

      InnerB(String var1, String var2, String var3, String var4) {
         this.processName = var1;
         this.song = var2;
         this.artist = var3;
         this.rawTitle = var4;
      }

      public String a() {
         return this.processName;
      }

      public String b() {
         return this.song;
      }

      public String c() {
         return this.artist;
      }

      public String d() {
         return this.rawTitle;
      }

      public boolean e() {
         return this.processName != null && this.processName.contains("cloudmusic");
      }

      @Override
      public String toString() {
         return this.processName + " | " + this.song + " - " + this.artist + " | title=" + this.rawTitle;
      }
   }

   public interface InnerC extends StdCallLibrary {
      WindowTitleBridge.InnerC INSTANCE = Native.load("user32", WindowTitleBridge.InnerC.class, W32APIOptions.DEFAULT_OPTIONS);

      boolean EnumWindows(WindowTitleBridge.InnerD var1, Pointer var2);

      int GetWindowTextLengthW(Pointer var1);

      int GetWindowTextW(Pointer var1, char[] var2, int var3);

      boolean IsWindowVisible(Pointer var1);

      int GetWindowThreadProcessId(Pointer var1, IntByReference var2);
   }

   public interface InnerD extends Callback {
      boolean invoke(Pointer var1, Pointer var2);
   }
}
