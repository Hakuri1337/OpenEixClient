package com.heypixel.heypixelmod.obsoverlay.utils.e.c;

import com.heypixel.heypixelmod.obsoverlay.utils.e.a.RecoveredUtilsEAA;
import com.heypixel.heypixelmod.obsoverlay.utils.e.d.RecoveredUtilsEDA;
import io.github.humbleui.skija.ColorType;
import io.github.humbleui.skija.Image;
import io.github.humbleui.skija.SurfaceOrigin;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import org.apache.commons.io.IOUtils;

public class RecoveredUtilsECA {
   private final Map<String, Image> a = new HashMap<>();
   private final Map<Integer, Image> b = new HashMap<>();

   public boolean a(int var1, float var2, float var3, SurfaceOrigin var4) {
      if (!this.b.containsKey(var1)) {
         Image var5 = Image.adoptGLTextureFrom(RecoveredUtilsEAA.b(), var1, 3553, (int)var2, (int)var3, 32856, var4, ColorType.RGBA_8888);
         this.b.put(var1, var5);
      }

      return true;
   }

   public boolean a(ResourceLocation var1) {
      if (!this.a.containsKey(var1.getPath())) {
         ResourceManager var2 = Minecraft.getInstance().getResourceManager();

         try {
            Resource var3 = var2.getResourceOrThrow(var1);

            try {
               boolean var7;
               try (InputStream var4 = var3.open()) {
                  byte[] var5 = var4.readAllBytes();
                  Image var6 = Image.makeDeferredFromEncodedBytes(var5);
                  if (var6 != null) {
                     this.a.put(var1.getPath(), var6);
                     return true;
                  }

                  var7 = false;
               }

               return var7;
            } catch (IOException var10) {
               var10.printStackTrace();
            }
         } catch (FileNotFoundException var11) {
            var11.printStackTrace();
         }
      }

      return true;
   }

   public boolean a(String var1) {
      if (!this.a.containsKey(var1)) {
         Optional var2 = RecoveredUtilsEDA.a(var1);
         if (var2.isPresent()) {
            this.a.put(var1, Image.makeDeferredFromEncodedBytes((byte[])var2.get()));
            return true;
         } else {
            return false;
         }
      } else {
         return true;
      }
   }

   public boolean a(File var1) {
      if (!this.a.containsKey(var1.getName())) {
         try {
            byte[] var2 = IOUtils.toByteArray(new FileInputStream(var1));
            this.a.put(var1.getName(), Image.makeDeferredFromEncodedBytes(var2));
            return true;
         } catch (IOException var3) {
            var3.printStackTrace();
            return false;
         }
      } else {
         return true;
      }
   }

   public Image b(String var1) {
      return this.a.containsKey(var1) ? this.a.get(var1) : null;
   }

   public Image a(int var1) {
      return this.b.containsKey(var1) ? this.b.get(var1) : null;
   }
}
