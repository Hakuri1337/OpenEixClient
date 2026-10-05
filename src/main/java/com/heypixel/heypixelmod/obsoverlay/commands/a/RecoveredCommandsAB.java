package com.heypixel.heypixelmod.obsoverlay.commands.a;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.b.RecoveredBB;
import com.heypixel.heypixelmod.obsoverlay.commands.CommandInfo;
import com.heypixel.heypixelmod.obsoverlay.commands.RecoveredCommandsA;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsF;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import net.minecraft.client.Minecraft;

@CommandInfo(
   a = "config",
   b = "Open client config folder or manage cloud configs.",
   c = {"conf"}
)
public class RecoveredCommandsAB extends RecoveredCommandsA {
   @Override
   public void a(String[] var1) {
      if (var1.length == 0) {
         this.e();
      } else {
         String var2 = var1[0].toLowerCase();
         switch (var2) {
            case "open":
            case "folder":
               this.f();
               break;
            case "save":
               this.a(var1.length > 1 ? var1[1] : "default");
               break;
            case "load":
               this.b(var1.length > 1 ? var1[1] : "default");
               break;
            case "list":
               this.g();
               break;
            default:
               RecoveredUtilsF.a("§c未知的子命令: " + var2);
               this.e();
         }
      }
   }

   @Override
   public String[] b(String[] var1) {
      if (var1.length == 1) {
         return new String[]{"open", "folder", "save", "load", "list"};
      } else {
         return var1.length != 2 || !var1[0].equals("save") && !var1[0].equals("load") ? new String[0] : this.h();
      }
   }

   private void e() {
      RecoveredUtilsF.a("§b=== 配置命令帮助 ===");
      RecoveredUtilsF.a("§7/config folder §f- 打开配置文件夹");
      RecoveredUtilsF.a("§7/config save [名称] §f- 保存当前配置");
      RecoveredUtilsF.a("§7/config load [名称] §f- 加载指定配置");
      RecoveredUtilsF.a("§7/config list §f- 列出所有配置");
   }

   private void f() {
      try {
         File var1 = new File(Minecraft.getInstance().gameDirectory, "config/Naven");
         if (!var1.exists()) {
            var1.mkdirs();
         }

         Desktop.getDesktop().browse(var1.toURI());
         RecoveredUtilsF.a("§a已打开配置文件夹");
      } catch (IOException var2) {
         RecoveredUtilsF.a("§c无法打开配置文件夹: " + var2.getMessage());
      }
   }

   private void a(String var1) {
      try {
         RecoveredBB var2 = EixClient.a().i();
         var2.b(var1);
         RecoveredUtilsF.a("§a配置已保存为: " + var1);
      } catch (Exception var3) {
         RecoveredUtilsF.a("§c保存配置失败: " + var3.getMessage());
      }
   }

   private void b(String var1) {
      try {
         RecoveredBB var2 = EixClient.a().i();
         var2.c(var1);
         RecoveredUtilsF.a("§a配置已加载: " + var1);
      } catch (Exception var3) {
         RecoveredUtilsF.a("§c加载配置失败: " + var3.getMessage());
      }
   }

   private void g() {
      try {
         File var1 = new File(Minecraft.getInstance().gameDirectory, "config/Naven");
         if (!var1.exists()) {
            RecoveredUtilsF.a("§7暂无配置文件");
            return;
         }

         File[] var2 = var1.listFiles((var0, var1x) -> var1x.endsWith(".json"));
         if (var2 == null || var2.length == 0) {
            RecoveredUtilsF.a("§7暂无配置文件");
            return;
         }

         RecoveredUtilsF.a("§b可用的配置文件:");

         for (File var6 : var2) {
            String var7 = var6.getName().replace(".json", "");
            RecoveredUtilsF.a("§7- " + var7);
         }
      } catch (Exception var8) {
         RecoveredUtilsF.a("§c列出配置文件失败: " + var8.getMessage());
      }
   }

   private String[] h() {
      try {
         File var1 = new File(Minecraft.getInstance().gameDirectory, "config/Naven");
         if (!var1.exists()) {
            return new String[0];
         } else {
            File[] var2 = var1.listFiles((var0, var1x) -> var1x.endsWith(".json"));
            if (var2 == null) {
               return new String[0];
            } else {
               String[] var3 = new String[var2.length];

               for (int var4 = 0; var4 < var2.length; var4++) {
                  var3[var4] = var2[var4].getName().replace(".json", "");
               }

               return var3;
            }
         }
      } catch (Exception var5) {
         return new String[0];
      }
   }
}
