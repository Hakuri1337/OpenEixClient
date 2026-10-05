package com.heypixel.heypixelmod.obsoverlay.events.impl;

import com.heypixel.heypixelmod.obsoverlay.events.api.events.Event;
import java.util.Objects;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;

public class RecoveredEventsImplAf implements Event {
   private final InteractionHand a;
   private ItemStack b;

   public RecoveredEventsImplAf(InteractionHand var1, ItemStack var2) {
      this.a = var1;
      this.b = var2;
   }

   public InteractionHand a() {
      return this.a;
   }

   public ItemStack b() {
      return this.b;
   }

   public void a(ItemStack var1) {
      this.b = var1;
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (var1 instanceof RecoveredEventsImplAf var2) {
         if (!var2.a(this)) {
            return false;
         } else {
            InteractionHand var3 = this.a();
            InteractionHand var4 = var2.a();
            if (Objects.equals(var3, var4)) {
               ItemStack var5 = this.b();
               ItemStack var6 = var2.b();
               return Objects.equals(var5, var6);
            } else {
               return false;
            }
         }
      } else {
         return false;
      }
   }

   protected boolean a(Object var1) {
      return var1 instanceof RecoveredEventsImplAf;
   }

   @Override
   public int hashCode() {
      byte var1 = 59;
      int var2 = 1;
      InteractionHand var3 = this.a();
      var2 = var2 * 59 + (var3 == null ? 43 : var3.hashCode());
      ItemStack var4 = this.b();
      return var2 * 59 + (var4 == null ? 43 : var4.hashCode());
   }

   @Override
   public String toString() {
      return "EventUpdateHeldItem(hand=" + this.a() + ", item=" + this.b() + ")";
   }
}
