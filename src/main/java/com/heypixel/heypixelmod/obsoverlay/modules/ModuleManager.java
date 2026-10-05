package com.heypixel.heypixelmod.obsoverlay.modules;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.a.RecoveredAB;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.impl.EventMouseClick;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplJ;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.a.AimAssistModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.a.AntiBotsModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.a.AutoClickerModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.a.AutoThrowModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.a.CrystalAuraModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.a.KeepSprintModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.a.KillAuraModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.a.TickBaseModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.a.VelocityModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.b.AntiFireballModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.b.AutoHeypixelModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.b.AutoSoupModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.b.AutoToolsModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.b.ChestStealerModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.b.ClientFriendModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.b.EffectTagsModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.b.FastPlaceModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.b.GhostBedMineModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.b.GhostChestOpenModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.b.GhostHandModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.b.HelperModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.b.InventoryManagerModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.b.MiddleClickModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.b.MusicPlayerModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.b.TargetModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.b.TeamsModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.c.AutoStuckModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.c.BlinkModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.c.EagleModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.c.FastWebModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.c.FlyModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.c.GrimFlyModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.c.GrimLowHopModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.c.LongJumpModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.c.NoJumpDelayModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.c.NoSlowModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.c.SafeWalkModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.c.ScaffoldModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.c.SprintModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.c.StuckModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.AnimationsModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.AntiBlindnessModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.AntiNauseaModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.ArrayListModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.BedESPModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.ChestESPModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.ClickGUIModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.CompassModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.DynamicIslandHud;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.EffectHUDModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.FovModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.FullBrightModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.GlowModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.HUDEditorModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.HUDModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.ItemTagsModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.KeyBindsModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.MotionBlurModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.NameProtectModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.NameTagsModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.NoHurtCamModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.NoRenderModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.NotificationModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.PostProcessModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.Projectile;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.ScoreboardModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.ServerNameSpoofModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.TargetESPModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.TargetHUDModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.TimeChangerModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.ViewClipModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.WatermarkModule;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.WeatherModule;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ModuleManager {
   private static final Logger a = LogManager.getLogger(ModuleManager.class);
   private final List<ClientModule> b = new ArrayList<>();
   private final Map<Class<? extends ClientModule>, ClientModule> c = new HashMap<>();
   private final Map<String, ClientModule> d = new HashMap<>();

   public ModuleManager() {
      try {
         EixClient var1 = EixClient.a();
         if (var1 != null && var1.b() != null) {
            var1.b().a(this);
         }

         this.b();
         this.b.sort((var0, var1x) -> var0.b().compareToIgnoreCase(var1x.b()));
      } catch (Exception var2) {
         a.error("Failed to initialize modules", (Throwable)var2);
         throw new RuntimeException(var2);
      }
   }

   public static ModuleManager a(String var0) {
      return new ModuleManager();
   }

   public static String b(String var0) {
      return var0;
   }

   private void b() {
      this.a(
         new KillAuraModule(),
         new AutoThrowModule(),
         new HUDModule(),
         new DynamicIslandHud(),
         new VelocityModule(),
         new TickBaseModule(),
         new NameTagsModule(),
         new TargetESPModule(),
         new ChestStealerModule(),
         new InventoryManagerModule(),
         new ScaffoldModule(),
         new AntiBotsModule(),
         new SprintModule(),
         new ChestESPModule(),
         new BedESPModule(),
         new ClickGUIModule(),
         new TeamsModule(),
         new GlowModule(),
         new EffectTagsModule(),
         new ClientFriendModule(),
         new GhostBedMineModule(),
         new GhostChestOpenModule(),
         new NoJumpDelayModule(),
         new FastPlaceModule(),
         new AntiFireballModule(),
         new StuckModule(),
         new AutoStuckModule(),
         new ServerNameSpoofModule(),
         new AutoSoupModule(),
         new AutoToolsModule(),
         new ViewClipModule(),
         new Projectile(),
         new TimeChangerModule(),
         new WeatherModule(),
         new FullBrightModule(),
         new NameProtectModule(),
         new NoHurtCamModule(),
         new AutoClickerModule(),
         new AntiBlindnessModule(),
         new AntiNauseaModule(),
         new ScoreboardModule(),
         new CompassModule(),
         new BlinkModule(),
         new GrimFlyModule(),
         new FlyModule(),
         new FastWebModule(),
         new PostProcessModule(),
         new CrystalAuraModule(),
         new EffectHUDModule(),
         new NoRenderModule(),
         new ItemTagsModule(),
         new SafeWalkModule(),
         new MotionBlurModule(),
         new HelperModule(),
         new NoSlowModule(),
         new LongJumpModule(),
         new TargetModule(),
         new KeepSprintModule(),
         new AnimationsModule(),
         new FovModule(),
         new GhostHandModule(),
         new EagleModule(),
         new HUDEditorModule(),
         new WatermarkModule(),
         new TargetHUDModule(),
         new NotificationModule(),
         new AutoHeypixelModule(),
         new KeyBindsModule(),
         new ArrayListModule(),
         new GrimLowHopModule(),
         new AimAssistModule(),
         new MiddleClickModule(),
         new MusicPlayerModule()
      );
   }

   private void a(ClientModule... var1) {
      for (ClientModule var5 : var1) {
         this.a(var5);
      }
   }

   private void a(ClientModule var1) {
      var1.c();
      this.b.add(var1);
      this.c.put((Class<? extends ClientModule>)var1.getClass(), var1);
      this.d.put(var1.h().toLowerCase(), var1);
   }

   public List<ClientModule> a(ModuleCategory var1) {
      ArrayList var2 = new ArrayList();

      for (ClientModule var4 : this.b) {
         if (var4.l() == var1) {
            var2.add(var4);
         }
      }

      return var2;
   }

   public <T extends ClientModule> T a(Class<T> var1) {
      ClientModule var2 = this.c.get(var1);
      return (T)(var2 == null ? null : var1.cast(var2));
   }

   public ClientModule c(String var1) {
      ClientModule var2 = this.d.get(var1.toLowerCase());
      if (var2 == null) {
         throw new RecoveredAB();
      } else {
         return var2;
      }
   }

   public ClientModule d(String var1) {
      return var1 == null ? null : this.d.get(var1.toLowerCase());
   }

   @EventTarget
   public void onKey(RecoveredEventsImplJ var1) {
      if (var1.c() && Minecraft.getInstance().screen == null) {
         for (ClientModule var3 : this.b) {
            if (var3.o() == var1.b()) {
               var3.f();
            }
         }
      }
   }

   @EventTarget
   public void onKey(EventMouseClick var1) {
      if (!var1.state() && (var1.key() == 3 || var1.key() == 4)) {
         for (ClientModule var3 : this.b) {
            if (var3.o() == -var1.key()) {
               var3.f();
            }
         }
      }
   }

   public List<ClientModule> a() {
      return this.b;
   }
}
