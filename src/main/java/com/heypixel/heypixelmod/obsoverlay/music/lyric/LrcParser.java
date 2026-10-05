package com.heypixel.heypixelmod.obsoverlay.music.lyric;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class LrcParser {
   private static final Pattern TIME_TAG = Pattern.compile("\\[(\\d{1,3}):(\\d{1,2})(?:[.:](\\d{1,3}))?]");
   private static final Pattern OFFSET_TAG = Pattern.compile("\\[offset:\\s*([+-]?\\d+)\\s*]", 2);
   private static final Pattern CREDIT_LINE = Pattern.compile(
      "^\\s*(作词|作曲|编曲|制作人|混音|录音|吉他|贝斯|鼓|和声|母带|监制|出品|策划|词|曲|OP|SP|lyricist|composer|arranger|producer|mixing|recording|guitar|bass|drums|mastering)[^:：]{0,8}[:：]",
      2
   );
   private static final long TRANSLATION_TOLERANCE_MS = 2000L;

   private LrcParser() {
   }

   public static RecoveredMusicLyricC a(String var0, String var1, String var2, String var3) {
      return a(var0, var1, var2, var3, true);
   }

   public static RecoveredMusicLyricC a(String var0, String var1, String var2, String var3, boolean var4) {
      if (var0 != null && !var0.isBlank()) {
         long var5 = c(var0);
         List var7 = a(var0, var5, var4);
         if (var7.isEmpty()) {
            return RecoveredMusicLyricC.EMPTY;
         } else {
            List var8 = a(var1, var5, false);
            List var9 = a(var7, var8);
            if (var9.isEmpty()) {
               return RecoveredMusicLyricC.EMPTY;
            } else {
               var9.sort(Comparator.comparingLong(RecoveredMusicLyricA::a));
               return new RecoveredMusicLyricC(var9, var2, var3);
            }
         }
      } else {
         return RecoveredMusicLyricC.EMPTY;
      }
   }

   private static long c(String var0) {
      Matcher var1 = OFFSET_TAG.matcher(var0);
      if (var1.find()) {
         try {
            return Long.parseLong(var1.group(1));
         } catch (NumberFormatException var3) {
         }
      }

      return 0L;
   }

   private static List<LrcParser.RawLine> a(String var0, long var1, boolean var3) {
      ArrayList var4 = new ArrayList(128);
      if (var0 != null && !var0.isBlank()) {
         String var5 = var0.replace("\r\n", "\n").replace('\r', '\n');

         for (String var9 : var5.split("\n")) {
            if (!var9.isBlank()) {
               Matcher var10 = TIME_TAG.matcher(var9);
               ArrayList<Long> var11 = new ArrayList(2);
               int var12 = 0;

               boolean var13;
               for (var13 = true; var10.find(); var12 = var10.end()) {
                  if (var10.start() > var12 && !var9.substring(var12, var10.start()).isBlank()) {
                     var13 = false;
                     break;
                  }

                  var11.add(a(var10.group(1), var10.group(2), var10.group(3)));
               }

               if (var13 && !var11.isEmpty()) {
                  String var14 = var9.substring(var12).trim();
                  if (!var3 || !CREDIT_LINE.matcher(var14).find()) {
                     for (Long var16 : var11) {
                        var4.add(new LrcParser.RawLine(Math.max(0L, var16 + var1), var14));
                     }
                  }
               }
            }
         }

         var4.sort(Comparator.comparingLong(LrcParser.RawLine::timeMs));
         return var4;
      } else {
         return var4;
      }
   }

   private static List<RecoveredMusicLyricA> a(List<LrcParser.RawLine> var0, List<LrcParser.RawLine> var1) {
      ArrayList var2 = new ArrayList(var1.size());

      for (LrcParser.RawLine var4 : var1) {
         if (!var4.text().isEmpty()) {
            var2.add(var4);
         }
      }

      String[] var8 = new String[var0.size()];
      int var9 = 0;
      if (!var2.isEmpty()) {
         for (int var5 = 0; var5 < var0.size(); var5++) {
            LrcParser.RawLine var6 = (LrcParser.RawLine)var0.get(var5);
            if (!var6.text().isEmpty()) {
               int var7 = a(var2, var6.timeMs());
               if (var7 >= 0 && Math.abs(((LrcParser.RawLine)var2.get(var7)).timeMs() - var6.timeMs()) <= 2000L) {
                  var8[var5] = ((LrcParser.RawLine)var2.get(var7)).text();
                  var9++;
               }
            }
         }

         int var10 = 0;

         for (LrcParser.RawLine var15 : var0) {
            if (!var15.text().isEmpty()) {
               var10++;
            }
         }

         if (var10 > 0 && var9 < var10 / 2) {
            Arrays.fill(var8, null);
            int var13 = 0;

            for (int var16 = 0; var16 < var0.size() && var13 < var2.size(); var16++) {
               if (!((LrcParser.RawLine)var0.get(var16)).text().isEmpty()) {
                  var8[var16] = ((LrcParser.RawLine)var2.get(var13++)).text();
               }
            }
         }
      }

      ArrayList var11 = new ArrayList(var0.size());

      for (int var14 = 0; var14 < var0.size(); var14++) {
         LrcParser.RawLine var17 = (LrcParser.RawLine)var0.get(var14);
         var11.add(new RecoveredMusicLyricA(var17.timeMs(), var17.text(), var8[var14] == null ? "" : var8[var14]));
      }

      return var11;
   }

   private static int a(List<LrcParser.RawLine> var0, long var1) {
      if (var0.isEmpty()) {
         return -1;
      } else {
         int var3 = 0;
         int var4 = var0.size() - 1;

         while (var3 < var4) {
            int var5 = var3 + var4 >>> 1;
            if (((LrcParser.RawLine)var0.get(var5)).timeMs() < var1) {
               var3 = var5 + 1;
            } else {
               var4 = var5;
            }
         }

         int var10 = var3;
         if (var3 > 0) {
            long var6 = Math.abs(((LrcParser.RawLine)var0.get(var3)).timeMs() - var1);
            long var8 = Math.abs(((LrcParser.RawLine)var0.get(var3 - 1)).timeMs() - var1);
            if (var8 < var6) {
               var10 = var3 - 1;
            }
         }

         return var10;
      }
   }

   private static long a(String var0, String var1, String var2) {
      long var3 = d(var0);
      long var5 = d(var1);
      long var7 = 0L;
      if (var2 != null && !var2.isEmpty()) {
         var7 = var2.length() == 1 ? d(var2) * 100L : (var2.length() == 2 ? d(var2) * 10L : d(var2.substring(0, 3)));
      }

      return var3 * 60000L + var5 * 1000L + var7;
   }

   private static long d(String var0) {
      try {
         return Long.parseLong(var0);
      } catch (NumberFormatException var2) {
         return 0L;
      }
   }

   public static boolean a(String var0) {
      return var0 != null && !var0.isBlank() ? TIME_TAG.matcher(var0).find() : false;
   }

   public static String b(String var0) {
      if (var0 == null) {
         return "";
      } else {
         String var1 = var0.replaceAll("[（(【\\[][^）)】\\]]*[）)】\\]]", " ").replaceAll("\\s+", " ").trim();
         String var2 = var1.toLowerCase(Locale.ROOT);

         for (String var6 : new String[]{"official", "mv", "audio", "lyric", "hd", "hq", "完整版", "无损", "高音质"}) {
            if (var2.endsWith(var6)) {
               var1 = var1.substring(0, var1.length() - var6.length()).trim();
               var2 = var1.toLowerCase(Locale.ROOT);
            }
         }

         return var1.isEmpty() ? var0.trim() : var1;
      }
   }

   private static record RawLine(long timeMs, String text) {
   }
}
