package com.heypixel.heypixelmod.obsoverlay.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.NonNull;

public class RecoveredUtilsV {
   private static final ScheduledExecutorService b = Executors.newScheduledThreadPool(3, new ThreadFactory() {
      private final AtomicInteger a = new AtomicInteger(0);

      @Override
      public Thread newThread(@NonNull Runnable var1) {
         if (var1 == null) {
            throw new NullPointerException("r is marked non-null but is null");
         } else {
            return new Thread(var1, "Multithreading Thread " + this.a.incrementAndGet());
         }
      }
   });
   public static ExecutorService a = Executors.newCachedThreadPool(new ThreadFactory() {
      private final AtomicInteger a = new AtomicInteger(0);

      @Override
      public Thread newThread(@NonNull Runnable var1) {
         if (var1 == null) {
            throw new NullPointerException("r is marked non-null but is null");
         } else {
            return new Thread(var1, "Multithreading Thread " + this.a.incrementAndGet());
         }
      }
   });

   public static void a(Runnable var0, long var1, long var3, TimeUnit var5) {
      b.scheduleAtFixedRate(var0, var1, var3, var5);
   }

   public static ScheduledFuture<?> b(Runnable var0, long var1, long var3, TimeUnit var5) {
      return b.scheduleAtFixedRate(var0, var1, var3, var5);
   }

   public static ScheduledFuture<?> a(Runnable var0, long var1, TimeUnit var3) {
      return b.schedule(var0, var1, var3);
   }

   public static int a() {
      return ((ThreadPoolExecutor)a).getActiveCount();
   }

   public static void a(Runnable var0) {
      a.execute(var0);
   }
}
