package com.heypixel.heypixelmod.obsoverlay.utils;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import java.io.BufferedInputStream;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.FloatControl.Type;

public class RecoveredUtilsAh {
   public static void a(String var0, float var1) {
      if (EixClient.a().e) {
         RecoveredUtilsV.a(() -> {
            try {
               BufferedInputStream var2 = new BufferedInputStream(RecoveredUtilsAh.class.getResourceAsStream("/assets/heypixel/vcx6svvqmet8/sounds/" + var0));
               AudioInputStream var3 = AudioSystem.getAudioInputStream(var2);
               Clip var4 = AudioSystem.getClip();
               var4.open(var3);
               FloatControl var5 = (FloatControl)var4.getControl(Type.MASTER_GAIN);
               float var6 = var5.getMaximum() - var5.getMinimum();
               float var7 = var6 * var1 + var5.getMinimum();
               var5.setValue(var7);
               var4.start();
            } catch (IOException | LineUnavailableException | UnsupportedAudioFileException var8) {
               var8.printStackTrace();
            }
         });
      }
   }
}
