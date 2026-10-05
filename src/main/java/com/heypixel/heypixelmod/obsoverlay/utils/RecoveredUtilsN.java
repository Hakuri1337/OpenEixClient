package com.heypixel.heypixelmod.obsoverlay.utils;

import cn.paradisemc.ZKMIndy;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import org.apache.commons.io.FileUtils;

@ZKMIndy
public class RecoveredUtilsN {
   public static final String a = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0";

   public RecoveredUtilsN() {
      HttpURLConnection.setFollowRedirects(true);
   }

   public static HttpURLConnection a(String var0, String var1, String var2) throws IOException {
      HttpURLConnection var3 = (HttpURLConnection)new URL(var0).openConnection();
      var3.setRequestMethod(var1);
      var3.setConnectTimeout(5000);
      var3.setReadTimeout(10000);
      var3.setRequestProperty("User-Agent", var2);
      var3.setInstanceFollowRedirects(true);
      var3.setDoOutput(true);
      return var3;
   }

   public static String b(String var0, String var1, String var2) {
      try {
         HttpURLConnection var3 = a(var0, var1, var2);
         BufferedReader var4 = new BufferedReader(new InputStreamReader(var3.getInputStream()));
         StringBuilder var5 = new StringBuilder();

         String var6;
         while ((var6 = var4.readLine()) != null) {
            var5.append(var6).append("\n");
         }

         var4.close();
         return var5.toString();
      } catch (SocketTimeoutException var7) {
         System.err.println("Read timed out");
         return null;
      } catch (IOException var8) {
         System.err.println("Error while making request");
         return null;
      }
   }

   public static String c(String var0, String var1, String var2) {
      try {
         HttpURLConnection var3 = a(var0, var1, var2);
         BufferedReader var4 = new BufferedReader(new InputStreamReader(var3.getInputStream()));
         StringBuilder var5 = new StringBuilder();

         String var6;
         while ((var6 = var4.readLine()) != null) {
            var5.append(var6);
         }

         var4.close();
         return var5.toString();
      } catch (SocketTimeoutException var7) {
         System.err.println("Read timed out");
         return null;
      } catch (IOException var8) {
         System.err.println("Error while making request");
         return null;
      }
   }

   public static String a(String var0) throws IOException {
      return b(var0, "GET", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
   }

   public static String b(String var0) throws IOException {
      return c(var0, "GET", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
   }

   public static void a(String var0, File var1) throws IOException {
      FileUtils.copyInputStreamToFile(a(var0, "GET", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0").getInputStream(), var1);
   }

   public static String a(InputStream var0) throws IOException {
      BufferedReader var1 = new BufferedReader(new InputStreamReader(var0));
      StringBuilder var2 = new StringBuilder();

      String var3;
      while ((var3 = var1.readLine()) != null) {
         var2.append(var3);
      }

      return var2.toString();
   }
}
