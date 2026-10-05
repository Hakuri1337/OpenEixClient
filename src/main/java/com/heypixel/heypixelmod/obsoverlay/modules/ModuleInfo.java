package com.heypixel.heypixelmod.obsoverlay.modules;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ModuleInfo {
   String a();

   String b();

   String c();

   ModuleCategory d();
}
