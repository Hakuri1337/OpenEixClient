package com.heypixel.heypixelmod.obsoverlay.b;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.c.RecoveredCA;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDC;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDF;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAB;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAE;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleManager;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.ScoreboardModule;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsM;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RecoveredBB {
   public static final Logger logger = LogManager.getLogger(RecoveredBB.class);
   public static final File clientFolder = new File(System.getenv("APPDATA"), "EixClient");
   public static final File configFolder = new File(Minecraft.getInstance().gameDirectory, "config/Naven");
   private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
   private final File configFile;
   private RecoveredBA config;

   public RecoveredBB() {
      if (!clientFolder.exists()) {
         clientFolder.mkdirs();
      }

      if (!configFolder.exists()) {
         configFolder.mkdirs();
      }

      this.configFile = new File(clientFolder, "config.json");
      this.config = new RecoveredBA();
   }

   public RecoveredBA a() {
      return this.config;
   }

   public void b() {
      if (this.configFile.exists()) {
         try (InputStreamReader var1 = new InputStreamReader(Files.newInputStream(this.configFile.toPath()), StandardCharsets.UTF_8)) {
            this.config = this.gson.fromJson(var1, RecoveredBA.class);
            if (this.config == null) {
               this.config = new RecoveredBA();
            }

            logger.info("Loaded config from JSON.");
         } catch (Exception var6) {
            logger.error("Failed to load JSON config!", (Throwable)var6);
            this.config = new RecoveredBA();
         }
      } else {
         logger.info("No config found. Loading defaults.");
      }

      this.e();
   }

   public void c() {
      this.f();

      try (OutputStreamWriter var1 = new OutputStreamWriter(Files.newOutputStream(this.configFile.toPath()), StandardCharsets.UTF_8)) {
         this.gson.toJson(this.config, var1);
         logger.info("Saved config to JSON.");
      } catch (Exception var6) {
         logger.error("Failed to save JSON config!", (Throwable)var6);
      }
   }

   public void a(String var1) {
      try {
         this.config = this.gson.fromJson(var1, RecoveredBA.class);
         if (this.config == null) {
            this.config = new RecoveredBA();
         }

         this.e();
         logger.info("Loaded config from Cloud String.");
      } catch (Exception var3) {
         logger.error("Failed to load Cloud config!", (Throwable)var3);
      }
   }

   public String d() {
      this.f();
      return this.gson.toJson(this.config);
   }

   public void b(String var1) throws IOException {
      this.f();
      File var2 = new File(configFolder, var1 + ".json");

      try (OutputStreamWriter var3 = new OutputStreamWriter(Files.newOutputStream(var2.toPath()), StandardCharsets.UTF_8)) {
         this.gson.toJson(this.config, var3);
         logger.info("Saved config to: " + var2.getAbsolutePath());
      }
   }

   public void c(String var1) throws IOException {
      File var2 = new File(configFolder, var1 + ".json");
      if (!var2.exists()) {
         throw new FileNotFoundException("配置文件不存在: " + var1);
      } else {
         try (InputStreamReader var3 = new InputStreamReader(Files.newInputStream(var2.toPath()), StandardCharsets.UTF_8)) {
            this.config = this.gson.fromJson(var3, RecoveredBA.class);
            if (this.config == null) {
               this.config = new RecoveredBA();
            }

            this.e();
            logger.info("Loaded config from: " + var2.getAbsolutePath());
         }
      }
   }

   private void e() {
      ModuleManager var1 = EixClient.a().g();
      if (this.config == null) {
         this.config = new RecoveredBA();
      }

      if (this.config.modules == null) {
         this.config.modules = new HashMap<>();
      }

      if (this.config.friends == null) {
         this.config.friends = new ArrayList<>();
      }

      if (this.config.gui == null) {
         this.config.gui = new RecoveredBA.InnerA();
      }

      for (Entry var3 : this.config.modules.entrySet()) {
         try {
            ClientModule var4 = var1.d((String)var3.getKey());
            RecoveredBA.InnerB var5 = (RecoveredBA.InnerB)var3.getValue();
            if (var4 != null && var5 != null) {
               var4.b(var5.key);
               var4.a(var5.enabled);
               if (var5.values != null) {
                  for (Entry var7 : var5.values.entrySet()) {
                     RecoveredDC var8 = EixClient.a().d().a(var4, (String)var7.getKey());
                     if (var8 == null && "Scoreboard".equals(var4.b()) && "ArrayList Font".equals(var7.getKey())) {
                        var8 = EixClient.a().d().a(var4, "Font");
                     }

                     if ((var8 == null || !(var4 instanceof ScoreboardModule) || var8.a() != RecoveredDF.DRAG) && var8 != null) {
                        this.a(var8, var7.getValue());
                     }
                  }
               }
            }
         } catch (Exception var9) {
            logger.warn("Module or value not found: " + (String)var3.getKey());
         }
      }

      RecoveredUtilsM.a().clear();

      for (String var11 : this.config.friends) {
         RecoveredUtilsM.b(var11);
      }

      RecoveredCA.a = this.config.gui.x;
      RecoveredCA.b = this.config.gui.y;
      RecoveredCA.c = this.config.gui.width;
      RecoveredCA.d = this.config.gui.height;
   }

   private void a(RecoveredDC var1, Object var2) {
      if (var2 != null) {
         switch (var1.a()) {
            case BOOLEAN:
               if (var2 instanceof Boolean) {
                  var1.b().a((Boolean)var2);
               }
               break;
            case FLOAT:
               if (var2 instanceof Number) {
                  var1.c().a(((Number)var2).floatValue());
               }
               break;
            case STRING:
               if (var2 instanceof String) {
                  var1.d().a((String)var2);
               }
               break;
            case MODE:
               if (var2 instanceof Number) {
                  int var7 = ((Number)var2).intValue();
                  RecoveredDAE var8 = var1.e();
                  if (var7 >= 0 && var7 < var8.m().length) {
                     var8.a(var7);
                  }
               }
               break;
            case DRAG:
               if (var2 instanceof Map var6) {
                  RecoveredDAB var4 = var1.f();
                  if (var6.containsKey("x") && var6.get("x") instanceof Number) {
                     var4.a(((Number)var6.get("x")).floatValue());
                  }

                  if (var6.containsKey("y") && var6.get("y") instanceof Number) {
                     var4.b(((Number)var6.get("y")).floatValue());
                  }
               }
               break;
            case KEY:
               if (var2 instanceof Number) {
                  var1.g().a(((Number)var2).intValue());
               } else if (var2 instanceof String var3) {
                  try {
                     var1.g().a(Integer.parseInt(var3.trim()));
                  } catch (NumberFormatException var5) {
                  }
               }
         }
      }
   }

   private void f() {
      ModuleManager var1 = EixClient.a().g();
      this.config.modules.clear();

      for (ClientModule var3 : var1.a()) {
         RecoveredBA.InnerB var4 = new RecoveredBA.InnerB();
         var4.key = var3.o();
         var4.enabled = var3.m();

         for (RecoveredDC var6 : EixClient.a().d().a()) {
            if (var6.i() == var3) {
               switch (var6.a()) {
                  case BOOLEAN:
                     var4.values.put(var6.j(), var6.b().m());
                     break;
                  case FLOAT:
                     var4.values.put(var6.j(), var6.c().q());
                     break;
                  case STRING:
                     var4.values.put(var6.j(), var6.d().n());
                     break;
                  case MODE:
                     var4.values.put(var6.j(), var6.e().o());
                     break;
                  case DRAG:
                     if (!(var3 instanceof ScoreboardModule)) {
                        HashMap var7 = new HashMap();
                        var7.put("x", var6.f().n());
                        var7.put("y", var6.f().o());
                        var4.values.put(var6.j(), var7);
                     }
                     break;
                  case KEY:
                     var4.values.put(var6.j(), var6.g().m());
               }
            }
         }

         this.config.modules.put(var3.b(), var4);
      }

      this.config.friends = new ArrayList<>(RecoveredUtilsM.a());
      this.config.gui.x = RecoveredCA.a;
      this.config.gui.y = RecoveredCA.b;
      this.config.gui.width = RecoveredCA.c;
      this.config.gui.height = RecoveredCA.d;
   }
}
