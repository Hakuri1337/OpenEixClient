package com.heypixel.heypixelmod.obsoverlay.events.api;

import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAB;
import com.heypixel.heypixelmod.obsoverlay.events.api.events.Event;
import com.heypixel.heypixelmod.obsoverlay.events.api.events.RecoveredEventsApiEventsB;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class EventManager {
   private static final Logger a = LogManager.getLogger(EventManager.class);
   private final Map<Class<? extends Event>, List<EventManager.MethodData>> b = new ConcurrentHashMap<>();

   public void a(Object var1) {
      for (Method var5 : var1.getClass().getDeclaredMethods()) {
         if (!this.a(var5)) {
            this.a(var5, var1);
         }
      }
   }

   public void a(Object var1, Class<? extends Event> var2) {
      for (Method var6 : var1.getClass().getDeclaredMethods()) {
         if (!this.a(var6, var2)) {
            this.a(var6, var1);
         }
      }
   }

   public void b(Object var1) {
      for (List<EventManager.MethodData> var3 : this.b.values()) {
         for (EventManager.MethodData var5 : var3) {
            if (var5.source().equals(var1)) {
               var3.remove(var5);
            }
         }
      }

      this.a(true);
   }

   public void b(Object var1, Class<? extends Event> var2) {
      if (this.b.containsKey(var2)) {
         for (EventManager.MethodData var4 : this.b.get(var2)) {
            if (var4.source().equals(var1)) {
               this.b.get(var2).remove(var4);
            }
         }

         this.a(true);
      }
   }

   private void a(Method var1, Object var2) {
      Class var3 = var1.getParameterTypes()[0];
      final EventManager.MethodData var4 = new EventManager.MethodData(var2, var1, var1.getAnnotation(EventTarget.class).a(), b(var1, var2));
      if (!var4.target().isAccessible()) {
         var4.target().setAccessible(true);
      }

      if (this.b.containsKey(var3)) {
         if (!this.b.get(var3).contains(var4)) {
            this.b.get(var3).add(var4);
            this.b(var3);
         }
      } else {
         this.b.put(var3, new CopyOnWriteArrayList<EventManager.MethodData>() {
            private static final long serialVersionUID = 666L;

            {
               this.add(var4);
            }
         });
      }
   }

   private static MethodHandle b(Method var0, Object var1) {
      try {
         Lookup var2 = MethodHandles.privateLookupIn(var0.getDeclaringClass(), MethodHandles.lookup());
         return var2.unreflect(var0).bindTo(var1).asType(MethodType.methodType(void.class, Event.class));
      } catch (Throwable var3) {
         return null;
      }
   }

   public void a(Class<? extends Event> var1) {
      Iterator var2 = this.b.entrySet().iterator();

      while (var2.hasNext()) {
         if (((Class)((Entry)var2.next()).getKey()).equals(var1)) {
            var2.remove();
            break;
         }
      }
   }

   public void a(boolean var1) {
      Iterator var2 = this.b.entrySet().iterator();

      while (var2.hasNext()) {
         if (!var1 || ((List)((Entry)var2.next()).getValue()).isEmpty()) {
            var2.remove();
         }
      }
   }

   private void b(Class<? extends Event> var1) {
      CopyOnWriteArrayList var2 = new CopyOnWriteArrayList();

      for (byte var6 : RecoveredEventsApiAB.f) {
         for (EventManager.MethodData var8 : this.b.get(var1)) {
            if (var8.priority() == var6) {
               var2.add(var8);
            }
         }
      }

      this.b.put(var1, var2);
   }

   private boolean a(Method var1) {
      return var1.getParameterTypes().length != 1 || !var1.isAnnotationPresent(EventTarget.class);
   }

   private boolean a(Method var1, Class<? extends Event> var2) {
      return this.a(var1) || !var1.getParameterTypes()[0].equals(var2);
   }

   public Event a(Event var1) {
      List<EventManager.MethodData> var2 = this.b.get(var1.getClass());
      if (var2 != null) {
         if (var1 instanceof RecoveredEventsApiEventsB var3) {
            for (EventManager.MethodData var5 : var2) {
               this.a(var5, var1);
               if (var3.b()) {
                  break;
               }
            }
         } else {
            for (EventManager.MethodData var7 : var2) {
               this.a(var7, var1);
            }
         }
      }

      return var1;
   }

   private void a(EventManager.MethodData var1, Event var2) {
      MethodHandle var3 = var1.handle();
      if (var3 != null) {
         try {
            var3.invoke((Event)var2);
         } catch (Throwable var7) {
            a.error("事件处理异常: {}.{}", var1.source().getClass().getSimpleName(), var1.target().getName(), var7 instanceof Exception ? var7 : var7.getCause());
         }
      } else {
         try {
            var1.target().invoke(var1.source(), var2);
         } catch (InvocationTargetException var5) {
            a.error("事件处理异常: {}.{}", var1.source().getClass().getSimpleName(), var1.target().getName(), var5.getCause());
         } catch (Exception var6) {
            a.error("事件调用失败: {}.{}", var1.source().getClass().getSimpleName(), var1.target().getName(), var6);
         }
      }
   }

   private static record MethodData(Object source, Method target, byte priority, MethodHandle handle) {
   }
}
