package com.heypixel.heypixelmod.obsoverlay.modules.impl.b;

import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.EventRunTicks;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsP;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.Items;

@ModuleInfo(
   a = "AutoSoup",
   b = "自动汤",
   c = "Automatically uses mushroom stew when health is low",
   d = ModuleCategory.MISC
)
public class AutoSoupModule extends ClientModule {
   public static Integer c;

   @Override
   public void d() {
      c = null;
   }

   @Override
   public void e() {
      c = null;
   }

   @EventTarget
   public void onTick(EventRunTicks var1) {
      if (var1.type() == RecoveredEventsApiAA.PRE) {
         Minecraft var2 = Minecraft.getInstance();
         if (c == null) {
            if (var2.player.tickCount % 10 != 0) {
               return;
            }

            if (var2.player.getHealth() < var2.player.getMaxHealth() / 2.0F) {
               Integer var3 = RecoveredUtilsP.a(Items.MUSHROOM_STEW);
               if (var3 != null) {
                  c = var2.player.getInventory().selected;
                  var2.player.getInventory().selected = var3;
                  KeyMapping.click(var2.options.keyUse.getKey());
               }
            }
         } else {
            var2.player.getInventory().selected = c;
            c = null;
         }
      }
   }
}
