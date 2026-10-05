package com.heypixel.heypixelmod.obsoverlay.modules.impl.render;

import com.heypixel.heypixelmod.obsoverlay.EixClient;
import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAE;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplM;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.modules.impl.a.KillAuraModule;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.network.protocol.game.ServerboundSwingPacket;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.UseAnim;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@ModuleInfo(
   a = "Animations",
   b = "动画",
   c = "Customizes item animations and block animations",
   d = ModuleCategory.RENDER
)
public class AnimationsModule extends ClientModule {
   public static boolean c = false;
   public final RecoveredDAE d = RecoveredDD.a(this, "Block Mods").a("None", "1.7", "Push").a(1).a().e();
   public final RecoveredDAA e = RecoveredDD.a(this, "Aura Auto Block").a(true).a().b();
   public final RecoveredDAC f = RecoveredDD.a(this, "Blocking-X").a(0.5F).b(-2.0F).c(2.0F).d(0.05F).a().c();
   public final RecoveredDAC g = RecoveredDD.a(this, "Blocking-Y").a(-0.5F).b(-2.0F).c(2.0F).d(0.05F).a().c();
   private final Minecraft i = Minecraft.getInstance();
   public RecoveredDAA h = RecoveredDD.a(this, "Only Aura").a(false).a().b();
   private boolean j;
   private float k = 0.0F;
   private float l = 0.0F;
   private float m = 0.0F;
   private float n = 0.0F;
   private ItemStack o = ItemStack.EMPTY;
   private ItemStack p = ItemStack.EMPTY;

   @Override
   public void d() {
      super.d();
      MinecraftForge.EVENT_BUS.register(this);
   }

   @Override
   public void e() {
      super.e();
      MinecraftForge.EVENT_BUS.unregister(this);
   }

   @SubscribeEvent
   public void a(RenderHandEvent var1) {
      if (this.m() && !this.d.l().equals("None")) {
         if (var1.getHand() == InteractionHand.MAIN_HAND && var1.getItemStack().getItem() instanceof SwordItem) {
            ItemStack var2 = this.i.player.getMainHandItem();
            if (var2.getItem() instanceof SwordItem) {
               boolean var3 = false;
               if (this.i.player.isUsingItem() && this.i.player.getUsedItemHand() == InteractionHand.OFF_HAND) {
                  ItemStack var4 = this.i.player.getOffhandItem();
                  UseAnim var5 = var4.getUseAnimation();
                  if (var5 != UseAnim.BLOCK) {
                     var3 = true;
                  }
               }

               boolean var6 = this.e.m() && this.r() != null;
               if (!this.h.m() || var6) {
                  if (!var3 || var6) {
                     if (this.i.options.keyUse.isDown() || var6) {
                        var1.setCanceled(true);
                        this.a(
                           this.i.player,
                           var1.getPartialTick(),
                           var1.getEquipProgress(),
                           var1.getHand(),
                           var1.getSwingProgress(),
                           var1.getItemStack(),
                           var1.getEquipProgress(),
                           var1.getPoseStack(),
                           var1.getMultiBufferSource(),
                           var1.getPackedLight()
                        );
                     }
                  }
               }
            }
         }
      }
   }

   @EventTarget
   public void onPacket(RecoveredEventsImplM var1) {
      if (var1.b() == RecoveredEventsApiAA.SEND && var1.c() instanceof ServerboundSwingPacket) {
         this.j = !this.j;
      }
   }

   @EventTarget
   public void onMotion(RecoveredEventsImplK var1) {
      if (var1.b() == RecoveredEventsApiAA.PRE && this.i.player != null) {
         this.p();
      }
   }

   private void p() {
      this.m = this.k;
      this.n = this.l;
      LocalPlayer var1 = this.i.player;
      ItemStack var2 = var1.getMainHandItem();
      ItemStack var3 = var1.getOffhandItem();
      boolean var4 = this.q();
      if (var4) {
         this.k = 1.0F;
         if (ItemStack.matches(this.o, var2)) {
            this.o = var2;
         }

         if (ItemStack.matches(this.p, var3)) {
            this.p = var3;
         }
      } else {
         if (var1.isHandsBusy()) {
            this.k = Mth.clamp(this.k - 0.4F, 0.0F, 1.0F);
            this.l = Mth.clamp(this.l - 0.4F, 0.0F, 1.0F);
         } else {
            float var5 = var1.getAttackStrengthScale(1.0F);
            boolean var6 = ForgeHooksClient.shouldCauseReequipAnimation(this.o, var2, var1.getInventory().selected);
            boolean var7 = ForgeHooksClient.shouldCauseReequipAnimation(this.p, var3, -1);
            if (!var6 && this.o != var2) {
               this.o = var2;
            }

            if (!var7 && this.p != var3) {
               this.p = var3;
            }

            float var8 = !var6 ? var5 * var5 * var5 : 0.0F;
            float var9 = !var7 ? 1.0F : 0.0F;
            this.k = this.k + Mth.clamp(var8 - this.k, -0.2F, 0.2F);
            this.l = this.l + Mth.clamp(var9 - this.l, -0.2F, 0.2F);
         }

         if (this.k < 0.1F) {
            this.o = var2;
         }

         if (this.l < 0.1F) {
            this.p = var3;
         }
      }
   }

   private boolean q() {
      if (this.m() && !this.d.l().equals("None")) {
         LocalPlayer var1 = this.i.player;
         if (var1 == null) {
            return false;
         } else {
            ItemStack var2 = var1.getMainHandItem();
            if (!(var2.getItem() instanceof SwordItem)) {
               return false;
            } else {
               ItemStack var3 = var1.getMainHandItem();
               if (!(var3.getItem() instanceof SwordItem)) {
                  return false;
               } else {
                  boolean var4 = false;
                  if (var1.isUsingItem() && var1.getUsedItemHand() == InteractionHand.OFF_HAND) {
                     ItemStack var5 = var1.getOffhandItem();
                     UseAnim var6 = var5.getUseAnimation();
                     if (var6 != UseAnim.BLOCK) {
                        var4 = true;
                     }
                  }

                  boolean var7 = this.e.m() && this.r() != null;
                  if (this.h.m()) {
                     return var7;
                  } else if (var7) {
                     return true;
                  } else {
                     return var4 ? false : this.i.options.keyUse.isDown();
                  }
               }
            }
         }
      } else {
         return false;
      }
   }

   private void a(
      AbstractClientPlayer var1,
      float var2,
      float var3,
      InteractionHand var4,
      float var5,
      ItemStack var6,
      float var7,
      PoseStack var8,
      MultiBufferSource var9,
      int var10
   ) {
      if (!var1.isScoping()) {
         boolean var11 = var4 == InteractionHand.MAIN_HAND;
         HumanoidArm var12 = var11 ? var1.getMainArm() : var1.getMainArm().getOpposite();
         var8.pushPose();
         if (var6.isEmpty()) {
            if (var11 && !var1.isInvisible()) {
               this.a(var8, var9, var10, var7, var5, var12);
            }
         } else if (var6.is(Items.FILLED_MAP)) {
            if (var11 && this.p.isEmpty()) {
               this.a(var8, var9, var10, var3, var7, var5);
            } else {
               this.a(var8, var9, var10, var7, var12, var5, var6);
            }
         } else {
            boolean var14 = var6.is(Items.CROSSBOW) && CrossbowItem.isCharged(var6);
            int var15 = var12 == HumanoidArm.RIGHT ? 1 : -1;
            if (var6.is(Items.CROSSBOW)) {
               if (var1.isUsingItem() && var1.getUseItemRemainingTicks() > 0 && var1.getUsedItemHand() == var4) {
                  this.a(var8, var12, var7);
                  var8.translate((double)((float)var15 * -0.4785682F), -0.094387F, 0.0573153F);
                  var8.mulPose(Axis.XP.rotation(-0.20830506F));
                  var8.mulPose(Axis.YP.rotation((float)var15 * 65.3F * (float) Math.PI / 180.0F));
                  var8.mulPose(Axis.ZP.rotation((float)var15 * -9.785F * (float) Math.PI / 180.0F));
                  float var25 = (float)var6.getUseDuration() - ((float)var1.getUseItemRemainingTicks() - var2 + 1.0F);
                  float var29 = var25 / (float)CrossbowItem.getChargeDuration(var6);
                  var29 = Math.min(var29, 1.0F);
                  if (var29 > 0.1F) {
                     float var34 = Mth.sin((var25 - 0.1F) * 1.3F);
                     float var38 = var29 - 0.1F;
                     float var43 = var34 * var38;
                     var8.translate((double)(var43 * 0.0F), (double)(var43 * 0.004F), (double)(var43 * 0.0F));
                  }

                  var8.translate((double)(var29 * 0.0F), (double)(var29 * 0.0F), (double)(var29 * 0.04F));
                  var8.scale(1.0F, 1.0F, 1.0F + var29 * 0.2F);
                  var8.mulPose(Axis.YP.rotation((float)var15 * -45.0F * (float) Math.PI / 180.0F));
               } else {
                  float var24 = -0.4F * Mth.sin(Mth.sqrt(var5) * (float) Math.PI);
                  float var28 = 0.2F * Mth.sin(Mth.sqrt(var5) * (float) (Math.PI * 2));
                  float var33 = -0.2F * Mth.sin(var5 * (float) Math.PI);
                  var8.translate((double)((float)var15 * var24), (double)var28, (double)var33);
                  this.a(var8, var12, var7);
                  this.b(var8, var12, var5);
                  if (var14 && var5 < 0.001F && var11) {
                     var8.translate((double)((float)var15 * -0.641864F), 0.0, 0.0);
                     var8.mulPose(Axis.YP.rotation((float)var15 * 10.0F * (float) Math.PI / 180.0F));
                  }
               }

               this.a(
                  var1,
                  var6,
                  var15 == 1 ? ItemDisplayContext.FIRST_PERSON_RIGHT_HAND : ItemDisplayContext.FIRST_PERSON_LEFT_HAND,
                  var15 != 1,
                  var8,
                  var9,
                  var10
               );
            } else {
               boolean var16 = var12 == HumanoidArm.RIGHT;
               if (var1.isUsingItem() && var1.getUseItemRemainingTicks() > 0 && var1.getUsedItemHand() == var4) {
                  switch (var6.getUseAnimation()) {
                     case NONE:
                     case BLOCK:
                        this.a(var8, var12, var7);
                        break;
                     case EAT:
                     case DRINK:
                        this.a(var8, var2, var12, var6);
                        this.a(var8, var12, var7);
                        break;
                     case BOW:
                        this.a(var8, var12, var7);
                        var8.translate((double)((float)var15 * -0.2785682F), 0.183444F, 0.1573153F);
                        var8.mulPose(Axis.XP.rotation(-0.24321164F));
                        var8.mulPose(Axis.YP.rotation((float)var15 * 35.3F * (float) Math.PI / 180.0F));
                        var8.mulPose(Axis.ZP.rotation((float)var15 * -9.785F * (float) Math.PI / 180.0F));
                        float var27 = (float)var6.getUseDuration() - ((float)var1.getUseItemRemainingTicks() - var2 + 1.0F);
                        float var18 = var27 / 20.0F;
                        var18 = (var18 * var18 + var18 * 2.0F) / 3.0F;
                        var18 = Math.min(var18, 1.0F);
                        if (var18 > 0.1F) {
                           float var37 = Mth.sin((var27 - 0.1F) * 1.3F);
                           float var42 = var18 - 0.1F;
                           float var46 = var37 * var42;
                           var8.translate((double)(var46 * 0.0F), (double)(var46 * 0.004F), (double)(var46 * 0.0F));
                        }

                        var8.translate((double)(var18 * 0.0F), (double)(var18 * 0.0F), (double)(var18 * 0.04F));
                        var8.scale(1.0F, 1.0F, 1.0F + var18 * 0.2F);
                        var8.mulPose(Axis.YP.rotation((float)var15 * -45.0F * (float) Math.PI / 180.0F));
                        break;
                     case SPEAR:
                        this.a(var8, var12, var7);
                        var8.translate((double)((float)var15 * -0.5F), 0.7F, 0.1F);
                        var8.mulPose(Axis.XP.rotation(-0.9599311F));
                        var8.mulPose(Axis.YP.rotation((float)var15 * 35.3F * (float) Math.PI / 180.0F));
                        var8.mulPose(Axis.ZP.rotation((float)var15 * -9.785F * (float) Math.PI / 180.0F));
                        float var36 = (float)var6.getUseDuration() - ((float)var1.getUseItemRemainingTicks() - var2 + 1.0F);
                        float var40 = var36 / 10.0F;
                        var40 = Math.min(var40, 1.0F);
                        if (var40 > 0.1F) {
                           float var45 = Mth.sin((var36 - 0.1F) * 1.3F);
                           float var48 = var40 - 0.1F;
                           float var50 = var45 * var48;
                           var8.translate((double)(var50 * 0.0F), (double)(var50 * 0.004F), (double)(var50 * 0.0F));
                        }

                        var8.translate(0.0, 0.0, (double)(var40 * 0.2F));
                        var8.scale(1.0F, 1.0F, 1.0F + var40 * 0.2F);
                        var8.mulPose(Axis.YP.rotation((float)var15 * -45.0F * (float) Math.PI / 180.0F));
                  }
               } else if ((var1.isUsingItem() || Minecraft.getInstance().options.keyUse.isDown() || this.e.m() && this.r() != null)
                  && var1.getMainHandItem().getItem() instanceof SwordItem
                  && !this.d.l().equals("None")) {
                  String var26 = this.d.l().toLowerCase();
                  switch (var26) {
                     case "1.7":
                        var8.translate((double)((float)var15 * this.f.q()), (double)this.g.q(), -0.72F);
                        float var39 = Mth.sin(var5 * var5 * (float) Math.PI);
                        float var44 = Mth.sin(Mth.sqrt(var5) * (float) Math.PI);
                        var8.mulPose(Axis.YP.rotation((float)var15 * (45.0F + var39 * -20.0F) * (float) Math.PI / 180.0F));
                        var8.mulPose(Axis.ZP.rotation((float)var15 * var44 * -10.0F * (float) Math.PI / 180.0F));
                        var8.mulPose(Axis.XP.rotation(var44 * -80.0F * (float) Math.PI / 180.0F));
                        var8.mulPose(Axis.YP.rotation((float)var15 * -45.0F * (float) Math.PI / 180.0F));
                        var8.scale(0.9F, 0.9F, 0.9F);
                        var8.translate(-0.2F, 0.126F, 0.2F);
                        var8.mulPose(Axis.XP.rotation(-1.7845992F));
                        var8.mulPose(Axis.YP.rotation((float)var15 * 15.0F * (float) Math.PI / 180.0F));
                        var8.mulPose(Axis.ZP.rotation((float)var15 * 80.0F * (float) Math.PI / 180.0F));
                        break;
                     case "push":
                        var8.translate((double)((float)var15 * this.f.q()), (double)this.g.q(), -0.82F);
                        var8.translate((double)((float)var15 * -0.1414214F), 0.08F, 0.1414214F);
                        var8.mulPose(Axis.XP.rotation(-1.7845992F));
                        var8.mulPose(Axis.YP.rotation((float)var15 * 13.365F * (float) Math.PI / 180.0F));
                        var8.mulPose(Axis.ZP.rotation((float)var15 * 78.05F * (float) Math.PI / 180.0F));
                        float var47 = Mth.sin(var5 * var5 * (float) Math.PI);
                        float var49 = Mth.sin(Mth.sqrt(var5) * (float) Math.PI);
                        var8.mulPose(Axis.XP.rotation(var47 * -10.0F * (float) Math.PI / 180.0F));
                        var8.mulPose(Axis.YP.rotation(var47 * -10.0F * (float) Math.PI / 180.0F));
                        var8.mulPose(Axis.ZP.rotation(var47 * -10.0F * (float) Math.PI / 180.0F));
                        var8.mulPose(Axis.XP.rotation(var49 * -10.0F * (float) Math.PI / 180.0F));
                        var8.mulPose(Axis.YP.rotation(var49 * -10.0F * (float) Math.PI / 180.0F));
                        var8.mulPose(Axis.ZP.rotation(var49 * -10.0F * (float) Math.PI / 180.0F));
                  }
               } else if (var1.isAutoSpinAttack()) {
                  this.a(var8, var12, var7);
                  var8.translate((double)((float)var15 * -0.4F), 0.8F, 0.3F);
                  var8.mulPose(Axis.YP.rotation((float)var15 * 65.0F * (float) Math.PI / 180.0F));
                  var8.mulPose(Axis.ZP.rotation((float)var15 * -85.0F * (float) Math.PI / 180.0F));
               } else {
                  this.a(var8, var12, var7);
                  if (var6.getItem() instanceof SwordItem
                     && (this.i.options.keyUse.isDown() || this.e.m() && this.r() != null && this.r() instanceof LivingEntity)) {
                     String var17 = this.d.l().toLowerCase();
                     switch (var17) {
                        case "1.7":
                           var8.translate((double)((float)var15 * 0.56F), -0.52F, -0.72F);
                           float var20 = Mth.sin(var5 * var5 * (float) Math.PI);
                           float var21 = Mth.sin(Mth.sqrt(var5) * (float) Math.PI);
                           var8.mulPose(Axis.YP.rotation((float)var15 * (45.0F + var20 * -20.0F) * (float) Math.PI / 180.0F));
                           var8.mulPose(Axis.ZP.rotation((float)var15 * var21 * -20.0F * (float) Math.PI / 180.0F));
                           var8.mulPose(Axis.XP.rotation(var21 * -80.0F * (float) Math.PI / 180.0F));
                           var8.mulPose(Axis.YP.rotation((float)var15 * -45.0F * (float) Math.PI / 180.0F));
                           var8.scale(0.9F, 0.9F, 0.9F);
                           var8.translate(-0.2F, 0.126F, 0.2F);
                           var8.mulPose(Axis.XP.rotation(-1.7845992F));
                           var8.mulPose(Axis.YP.rotation((float)var15 * 15.0F * (float) Math.PI / 180.0F));
                           var8.mulPose(Axis.ZP.rotation((float)var15 * 80.0F * (float) Math.PI / 180.0F));
                           break;
                        case "push":
                           var8.translate((double)((float)var15 * 0.56F), -0.52F, -0.72F);
                           var8.translate((double)((float)var15 * -0.1414214F), 0.08F, 0.1414214F);
                           var8.mulPose(Axis.XP.rotation(-1.7845992F));
                           var8.mulPose(Axis.YP.rotation((float)var15 * 13.365F * (float) Math.PI / 180.0F));
                           var8.mulPose(Axis.ZP.rotation((float)var15 * 78.05F * (float) Math.PI / 180.0F));
                           float var22 = Mth.sin(var5 * var5 * (float) Math.PI);
                           float var23 = Mth.sin(Mth.sqrt(var5) * (float) Math.PI);
                           var8.mulPose(Axis.XP.rotation(var22 * -10.0F * (float) Math.PI / 180.0F));
                           var8.mulPose(Axis.YP.rotation(var22 * -10.0F * (float) Math.PI / 180.0F));
                           var8.mulPose(Axis.ZP.rotation(var22 * -10.0F * (float) Math.PI / 180.0F));
                           var8.mulPose(Axis.XP.rotation(var23 * -10.0F * (float) Math.PI / 180.0F));
                           var8.mulPose(Axis.YP.rotation(var23 * -10.0F * (float) Math.PI / 180.0F));
                           var8.mulPose(Axis.ZP.rotation(var23 * -10.0F * (float) Math.PI / 180.0F));
                           break;
                        default:
                           this.b(var8, var12, var5);
                     }
                  } else {
                     this.b(var8, var12, var5);
                  }
               }

               this.a(var1, var6, var16 ? ItemDisplayContext.FIRST_PERSON_RIGHT_HAND : ItemDisplayContext.FIRST_PERSON_LEFT_HAND, !var16, var8, var9, var10);
            }
         }

         var8.popPose();
      }
   }

   private LivingEntity r() {
      KillAuraModule var1 = EixClient.a().g().a(KillAuraModule.class);
      if (var1 != null && var1.m()) {
         try {
            return (LivingEntity)var1.c;
         } catch (Exception var3) {
            return null;
         }
      } else {
         return null;
      }
   }

   private void a(PoseStack var1, MultiBufferSource var2, int var3, float var4, float var5, HumanoidArm var6) {
      boolean var7 = var6 == HumanoidArm.RIGHT;
      float var8 = var7 ? 1.0F : -1.0F;
      float var9 = Mth.sqrt(var5);
      float var10 = -0.3F * Mth.sin(var9 * (float) Math.PI);
      float var11 = 0.4F * Mth.sin(var9 * (float) (Math.PI * 2));
      float var12 = -0.4F * Mth.sin(var5 * (float) Math.PI);
      var1.translate((double)(var8 * (0.644764F + var10)), (double)(0.644764F + var11), (double)(0.644764F + var12));
      var1.mulPose(Axis.XP.rotation(-0.3F * Mth.sin(var9 * (float) (Math.PI * 2))));
      var1.mulPose(Axis.YP.rotation(var8 * 0.4F * Mth.sin(var9 * (float) Math.PI)));
      var1.mulPose(Axis.ZP.rotation(var8 * -0.4F * Mth.sin(var5 * (float) Math.PI)));
      float var13 = Mth.lerp(var4, this.m, this.k);
      float var14 = Mth.lerp(var4, this.n, this.l);
      this.a(
         this.i.player,
         var7 ? this.o : this.p,
         var7 ? ItemDisplayContext.FIRST_PERSON_RIGHT_HAND : ItemDisplayContext.FIRST_PERSON_LEFT_HAND,
         !var7,
         var1,
         var2,
         var3
      );
   }

   private void a(PoseStack var1, MultiBufferSource var2, int var3, float var4, float var5, float var6) {
      float var7 = Mth.sqrt(var6);
      float var8 = -0.2F * Mth.sin(var6 * (float) Math.PI);
      float var9 = -0.4F * Mth.sin(var7 * (float) Math.PI);
      var1.translate(0.0, (double)(-var8 / 2.0F), (double)var9);
      float var10 = Mth.lerp(var5, this.m, this.k);
      float var11 = Mth.lerp(var5, this.n, this.l);
      this.a(this.i.player, this.o, ItemDisplayContext.FIRST_PERSON_RIGHT_HAND, false, var1, var2, var3);
      this.a(this.i.player, this.p, ItemDisplayContext.FIRST_PERSON_LEFT_HAND, true, var1, var2, var3);
   }

   private void a(PoseStack var1, MultiBufferSource var2, int var3, float var4, HumanoidArm var5, float var6, ItemStack var7) {
      float var8 = var5 == HumanoidArm.RIGHT ? 1.0F : -1.0F;
      var1.translate((double)(var8 * 0.125F), 0.0, 0.0);
      float var9 = Mth.sqrt(var6);
      float var10 = -0.1F * Mth.sin(var9 * (float) Math.PI);
      float var11 = -0.3F * Mth.sin(var9 * (float) (Math.PI * 2));
      float var12 = -0.4F * Mth.sin(var6 * (float) Math.PI);
      var1.translate(0.0, (double)(-var10 / 2.0F), (double)var12);
      var1.mulPose(Axis.XP.rotation(var11 * (float) Math.PI / 180.0F));
      var1.mulPose(Axis.YP.rotation(var8 * var9 * (float) Math.PI / 180.0F));
      var1.mulPose(Axis.ZP.rotation(var8 * var10 * (float) Math.PI / 180.0F));
      float var13 = Mth.lerp(var4, this.m, this.k);
      float var14 = Mth.lerp(var4, this.n, this.l);
      this.a(
         this.i.player,
         var7,
         var5 == HumanoidArm.RIGHT ? ItemDisplayContext.FIRST_PERSON_RIGHT_HAND : ItemDisplayContext.FIRST_PERSON_LEFT_HAND,
         var5 != HumanoidArm.RIGHT,
         var1,
         var2,
         var3
      );
   }

   private void a(PoseStack var1, HumanoidArm var2, float var3) {
      int var4 = var2 == HumanoidArm.RIGHT ? 1 : -1;
      float var5 = Mth.lerp(var3, this.m, this.k);
      float var6 = Mth.lerp(var3, this.n, this.l);
      var1.translate((double)((float)var4 * 0.56F), (double)(-0.52F + var5 * -0.6F), -0.72F);
   }

   private void b(PoseStack var1, HumanoidArm var2, float var3) {
      int var4 = var2 == HumanoidArm.RIGHT ? 1 : -1;
      float var5 = Mth.sin(var3 * var3 * (float) Math.PI);
      float var6 = Mth.sin(Mth.sqrt(var3) * (float) Math.PI);
      var1.translate((double)((float)var4 * 0.56F), -0.52F, -0.72F);
      var1.mulPose(Axis.XP.rotation(-1.7845992F));
      var1.mulPose(Axis.YP.rotation((float)var4 * 13.365F * (float) Math.PI / 180.0F));
      var1.mulPose(Axis.ZP.rotation((float)var4 * 78.05F * (float) Math.PI / 180.0F));
      float var7 = Mth.clamp(var3, 0.0F, 1.0F);
      var1.mulPose(Axis.XP.rotation(var5 * -15.0F * var7 * (float) Math.PI / 180.0F));
      var1.mulPose(Axis.YP.rotation(var6 * -15.0F * var7 * (float) Math.PI / 180.0F));
      var1.mulPose(Axis.ZP.rotation(var6 * -70.0F * var7 * (float) Math.PI / 180.0F));
   }

   private void a(PoseStack var1, float var2, HumanoidArm var3, ItemStack var4) {
      float var5 = (float)var4.getUseDuration() - ((float)this.i.player.getUseItemRemainingTicks() - var2 + 1.0F);
      float var6 = var5 / (float)var4.getUseDuration();
      if (var6 < 0.8F) {
         float var7 = Mth.abs(Mth.cos(var5 / 4.0F * (float) Math.PI) * 0.1F);
         var1.translate(0.0, (double)var7, 0.0);
      }

      float var9 = 1.0F - (float)Math.pow((double)(1.0F - var6), 27.0);
      int var8 = var3 == HumanoidArm.RIGHT ? 1 : -1;
      var1.translate((double)(var9 * 0.6F * (float)var8), (double)(var9 * -0.5F), (double)(var9 * 0.0F));
      var1.mulPose(Axis.YP.rotation((float)var8 * var9 * 90.0F * (float) Math.PI / 180.0F));
      var1.mulPose(Axis.XP.rotation(var9 * 10.0F * (float) Math.PI / 180.0F));
      var1.mulPose(Axis.ZP.rotation((float)var8 * var9 * 30.0F * (float) Math.PI / 180.0F));
   }

   private void a(LivingEntity var1, ItemStack var2, ItemDisplayContext var3, boolean var4, PoseStack var5, MultiBufferSource var6, int var7) {
      if (!var2.isEmpty()) {
         ItemRenderer var8 = this.i.getItemRenderer();
         var8.renderStatic(var1, var2, var3, var4, var5, var6, var1.level(), var7, 0, 0);
      }
   }
}
