package com.heypixel.heypixelmod.obsoverlay;

import cn.paradisemc.ZKMIndy;
import com.heypixel.heypixelmod.obsoverlay.b.RecoveredBB;
import com.heypixel.heypixelmod.obsoverlay.c.a.RecoveredCAC;
import com.heypixel.heypixelmod.obsoverlay.commands.RecoveredCommandsB;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDB;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDE;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventManager;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.EventRunTicks;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplY;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleManager;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.render.ClickGUIModule;
import com.heypixel.heypixelmod.obsoverlay.music.RecoveredMusicC;
import com.heypixel.heypixelmod.obsoverlay.music.nowplaying.RecoveredMusicNowplayingC;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAe;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAh;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAk;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsI;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsJ;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsQ;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsW;
import com.heypixel.heypixelmod.obsoverlay.utils.c.RecoveredUtilsCC;
import com.heypixel.heypixelmod.obsoverlay.utils.e.RecoveredUtilsEA;
import com.heypixel.heypixelmod.obsoverlay.utils.e.a.RecoveredUtilsEAA;
import com.heypixel.heypixelmod.obsoverlay.utils.renderer.RecoveredUtilsRendererD;
import com.heypixel.heypixelmod.obsoverlay.utils.renderer.RecoveredUtilsRendererH;
import com.heypixel.heypixelmod.obsoverlay.utils.renderer.RecoveredUtilsRendererK;
import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import java.awt.FontFormatException;
import java.io.IOException;
import javax.swing.text.html.parser.Entity;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.common.MinecraftForge;

@ZKMIndy
public class EixClient {
   public static final String a = "EixClient";
   public static final String b = "EixClient";
   public static float c = 1.0F;
   public static int d;
   private static volatile EixClient f;
   public boolean e = false;
   private EventManager g;
   private RecoveredUtilsJ h;
   private RecoveredDE i;
   private RecoveredDB j;
   private RecoveredUtilsCC k;
   private ModuleManager l;
   private RecoveredCommandsB m;
   private RecoveredBB n;
   private RecoveredCAC o;

   private EixClient() {
      this.a(null, null);
   }

   public static EntityHitResult a(Entity var0) {
      RenderSystem.recordRenderCall(() -> {
         try {
            new EixClient();
         } catch (Exception var1) {
            System.err.println("Failed to load client");
            var1.printStackTrace(System.err);
         }
      });
      return null;
   }

   public static EixClient a() {
      EixClient var0 = f;
      if (var0 == null) {
         synchronized (EixClient.class) {
            var0 = f;
            if (var0 == null) {
               var0 = new EixClient();
            }
         }
      }

      return var0;
   }

   public EntityHitResult a(Player var1, Exception var2) {
      f = this;
      this.g = new EventManager();
      Window var3 = Minecraft.getInstance().getWindow();
      RecoveredUtilsEAA.a(var3.getWidth(), var3.getHeight());
      RecoveredUtilsRendererK.a();
      RecoveredUtilsRendererH.a();

      try {
         RecoveredUtilsRendererD.a();
      } catch (IOException var5) {
         throw new RuntimeException(var5);
      } catch (FontFormatException var6) {
         throw new RuntimeException(var6);
      }

      this.h = new RecoveredUtilsJ();
      this.i = new RecoveredDE();
      this.j = new RecoveredDB();
      this.l = ModuleManager.a("加载客户端");
      this.k = new RecoveredUtilsCC();
      this.m = new RecoveredCommandsB();
      this.n = new RecoveredBB();
      this.o = new RecoveredCAC();
      this.n.b();
      this.l.a(ClickGUIModule.class).a(false);
      this.g.a(a());
      this.g.a(this.h);
      this.g.a(new RecoveredUtilsCC());
      this.g.a(new RecoveredUtilsW());
      this.g.a(new RecoveredUtilsAe());
      this.g.a(new RecoveredUtilsI());
      this.g.a(new RecoveredUtilsEA.InnerA());
      this.g.a(RecoveredMusicC.a());
      MinecraftForge.EVENT_BUS.register(this.h);
      this.e = true;
      RecoveredMusicC.a().b();
      RecoveredUtilsAh.a("opening.wav", 1.0F);
      return null;
   }

   @EventTarget
   public void onShutdown(RecoveredEventsImplY var1) {
      this.n.c();

      try {
         RecoveredMusicNowplayingC.a().c();
      } catch (Throwable var3) {
      }

      RecoveredUtilsQ.a();
   }

   @EventTarget(
      a = 0
   )
   public void onEarlyTick(EventRunTicks var1) {
      if (var1.type() == RecoveredEventsApiAA.PRE) {
         RecoveredUtilsAk.a();
      }
   }

   public EventManager b() {
      return this.g;
   }

   public RecoveredUtilsJ c() {
      return this.h;
   }

   public RecoveredDE d() {
      return this.i;
   }

   public RecoveredDB e() {
      return this.j;
   }

   public RecoveredUtilsCC f() {
      return this.k;
   }

   public ModuleManager g() {
      return this.l;
   }

   public RecoveredCommandsB h() {
      return this.m;
   }

   public RecoveredBB i() {
      return this.n;
   }

   public RecoveredCAC j() {
      return this.o;
   }
}
