package com.heypixel.heypixelmod.obsoverlay.modules.impl.c;

import com.heypixel.heypixelmod.obsoverlay.d.RecoveredDD;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAA;
import com.heypixel.heypixelmod.obsoverlay.d.a.RecoveredDAC;
import com.heypixel.heypixelmod.obsoverlay.events.api.EventTarget;
import com.heypixel.heypixelmod.obsoverlay.events.api.a.RecoveredEventsApiAA;
import com.heypixel.heypixelmod.obsoverlay.events.impl.EventRunTicks;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplK;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplL;
import com.heypixel.heypixelmod.obsoverlay.events.impl.RecoveredEventsImplP;
import com.heypixel.heypixelmod.obsoverlay.modules.ClientModule;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleCategory;
import com.heypixel.heypixelmod.obsoverlay.modules.ModuleInfo;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsAd;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsP;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsS;
import com.heypixel.heypixelmod.obsoverlay.utils.RecoveredUtilsU;
import com.heypixel.heypixelmod.obsoverlay.utils.c.RecoveredUtilsCA;
import com.heypixel.heypixelmod.obsoverlay.utils.c.RecoveredUtilsCB;
import com.heypixel.heypixelmod.obsoverlay.utils.c.RecoveredUtilsCC;
import com.heypixel.heypixelmod.obsoverlay.utils.c.RecoveredUtilsCD;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AirBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.FungusBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.WaterlilyBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

@ModuleInfo(
   a = "Scaffold",
   b = "自动搭路",
   c = "Automatically places blocks under you",
   d = ModuleCategory.MOVEMENT
)
public class ScaffoldModule extends ClientModule {
   public static final List<Block> c = Arrays.asList(
      Blocks.AIR,
      Blocks.WATER,
      Blocks.LAVA,
      Blocks.ENCHANTING_TABLE,
      Blocks.GLASS_PANE,
      Blocks.GLASS_PANE,
      Blocks.IRON_BARS,
      Blocks.SNOW,
      Blocks.COAL_ORE,
      Blocks.DIAMOND_ORE,
      Blocks.EMERALD_ORE,
      Blocks.CHEST,
      Blocks.TRAPPED_CHEST,
      Blocks.TORCH,
      Blocks.ANVIL,
      Blocks.TRAPPED_CHEST,
      Blocks.NOTE_BLOCK,
      Blocks.JUKEBOX,
      Blocks.TNT,
      Blocks.GOLD_ORE,
      Blocks.IRON_ORE,
      Blocks.LAPIS_ORE,
      Blocks.STONE_PRESSURE_PLATE,
      Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE,
      Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE,
      Blocks.STONE_BUTTON,
      Blocks.LEVER,
      Blocks.TALL_GRASS,
      Blocks.TRIPWIRE,
      Blocks.TRIPWIRE_HOOK,
      Blocks.RAIL,
      Blocks.CORNFLOWER,
      Blocks.RED_MUSHROOM,
      Blocks.BROWN_MUSHROOM,
      Blocks.VINE,
      Blocks.SUNFLOWER,
      Blocks.LADDER,
      Blocks.FURNACE,
      Blocks.SAND,
      Blocks.CACTUS,
      Blocks.DISPENSER,
      Blocks.DROPPER,
      Blocks.CRAFTING_TABLE,
      Blocks.COBWEB,
      Blocks.PUMPKIN,
      Blocks.COBBLESTONE_WALL,
      Blocks.OAK_FENCE,
      Blocks.REDSTONE_TORCH,
      Blocks.FLOWER_POT
   );
   RecoveredDAA d = RecoveredDD.a(this, "Telly").a(true).a().b();
   RecoveredDAA e = RecoveredDD.a(this, "Snap").a(false).a(() -> !this.d.m()).a().b();
   RecoveredDAC f = RecoveredDD.a(this, "Rotation Speed").a(180.0F).d(1.0F).b(1.0F).c(180.0F).a().c();
   RecoveredDAC g = RecoveredDD.a(this, "Rotation Back Speed").a(180.0F).d(1.0F).b(1.0F).c(180.0F).a(this.d::m).a().c();
   RecoveredDAC h = RecoveredDD.a(this, "Telly Ticks").a(1.0F).d(1.0F).b(0.0F).c(6.0F).a(this.d::m).a().c();
   RecoveredDAA i = RecoveredDD.a(this, "SafeWalk").a(true).a(() -> !this.d.m()).a().b();
   RecoveredDAA j = RecoveredDD.a(this, "ESP").a(true).a().b();
   private int k;
   private int l;
   private BlockPos m;
   private Direction n;
   private int o = -1;

   public static Vec3 a(BlockPos var0, Direction var1) {
      double var2 = (double)var0.getX() + 0.5;
      double var4 = (double)var0.getY() + 0.5;
      double var6 = (double)var0.getZ() + 0.5;
      if (var1 != Direction.UP && var1 != Direction.DOWN) {
         var4 += 0.08;
      } else {
         var2 += RecoveredUtilsS.a(0.3, -0.3);
         var6 += RecoveredUtilsS.a(0.3, -0.3);
      }

      if (var1 == Direction.WEST || var1 == Direction.EAST) {
         var6 += RecoveredUtilsS.a(0.3, -0.3);
      }

      if (var1 == Direction.SOUTH || var1 == Direction.NORTH) {
         var2 += RecoveredUtilsS.a(0.3, -0.3);
      }

      return new Vec3(var2, var4, var6);
   }

   public static boolean a(ItemStack var0) {
      if (var0 == null || !(var0.getItem() instanceof BlockItem) || var0.getCount() <= 1) {
         return false;
      } else if (!RecoveredUtilsP.k(var0)) {
         return false;
      } else {
         String var1 = var0.getDisplayName().getString();
         if (var1.contains("Click") || var1.contains("点击")) {
            return false;
         } else if (var0.getItem() instanceof ItemNameBlockItem) {
            return false;
         } else {
            Block var2 = ((BlockItem)var0.getItem()).getBlock();
            if (var2 instanceof FlowerBlock) {
               return false;
            } else if (var2 instanceof BushBlock) {
               return false;
            } else if (var2 instanceof FungusBlock) {
               return false;
            } else {
               return var2 instanceof CropBlock ? false : !(var2 instanceof SlabBlock) && !c.contains(var2);
            }
         }
      }
   }

   @EventTarget
   public void onPreTick(EventRunTicks var1) {
      if (a.player != null && a.level != null && var1.type() == RecoveredEventsApiAA.PRE) {
         int var2 = -1;

         for (int var3 = 0; var3 < 9; var3++) {
            ItemStack var4 = a.player.getInventory().getItem(var3);
            if (a(var4)) {
               var2 = var3;
               break;
            }
         }

         if (var2 != -1 && a.player.getInventory().selected != var2) {
            a.player.getInventory().selected = var2;
         }

         if (a.player.onGround()) {
            this.l = (int)Math.floor(a.player.getY()) - 1;
         }

         this.r();
         if (this.d.m()) {
            if (a.player.onGround()) {
               this.k = 0;
               this.m = null;
               this.n = null;
               RecoveredUtilsCB var5 = new RecoveredUtilsCB(a.player.getYRot(), a.player.getXRot());
               RecoveredUtilsCC.a(var5, (double)this.g.q());
            } else {
               if ((float)this.k >= this.h.q()) {
                  RecoveredUtilsCB var6 = this.b(this.m, this.n);
                  RecoveredUtilsCC.a(var6, (double)this.f.q());
                  this.p();
               }

               this.k++;
            }

            this.a("Telly");
         } else {
            if (this.m == null) {
               RecoveredUtilsCC.a(new RecoveredUtilsCB(Mth.wrapDegrees(a.player.getYRot() - 180.0F), 89.64F), (double)this.f.q());
            }

            if (this.s() || !this.e.m()) {
               RecoveredUtilsCB var7 = this.b(this.m, this.n);
               RecoveredUtilsCC.a(var7, (double)this.f.q());
            }

            this.p();
            this.a(this.e.m() ? "Snap" : "Normal");
         }
      }
   }

   public void p() {
      if (this.s()) {
         boolean var1 = RecoveredUtilsCA.a(RecoveredUtilsCC.j(), this.m);
         if (var1) {
            InteractionResult var2 = a.gameMode.useItemOn(a.player, InteractionHand.MAIN_HAND, new BlockHitResult(a(this.m, this.n), this.n, this.m, false));
            if (var2 == InteractionResult.SUCCESS) {
               a.player.swing(InteractionHand.MAIN_HAND);
            }
         }
      }
   }

   @EventTarget
   public void onMoveInput(RecoveredEventsImplL var1) {
      if (a.player.onGround() && !a.options.keyJump.isDown() && RecoveredUtilsU.a() && this.d.m()) {
         var1.a(true);
      }
   }

   public int q() {
      return !a.options.keyJump.isDown() && RecoveredUtilsU.a() && (double)a.player.fallDistance <= 0.25 && this.d.m()
         ? this.l
         : (int)Math.floor(a.player.getY()) - 1;
   }

   public void r() {
      Vec3 var1 = a.player.getEyePosition();
      BlockPos var2 = BlockPos.containing(var1.x, (double)this.q(), var1.z);
      int var3 = var2.getX();
      int var4 = var2.getZ();
      if (!this.a(a.level.getBlockState(var2), a.level, var2)) {
         if (!this.a(var1, var2)) {
            for (int var5 = 1; var5 <= 6; var5++) {
               if (this.a(var1, new BlockPos(var3, this.q() - var5, var4))) {
                  return;
               }

               for (int var6 = 0; var6 <= var5; var6++) {
                  for (int var7 = 0; var7 <= var5 - var6; var7++) {
                     int var8 = var5 - var6 - var7;

                     for (int var9 = 0; var9 <= 1; var9++) {
                        for (int var10 = 0; var10 <= 1; var10++) {
                           if (this.a(var1, new BlockPos(var3 + (var9 == 0 ? var6 : -var6), this.q() - var8, var4 + (var10 == 0 ? var7 : -var7)))) {
                              return;
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public boolean a(BlockState var1, Level var2, BlockPos var3) {
      boolean var4 = !var1.getCollisionShape(var2, var3).isEmpty();
      boolean var5 = var1.getMenuProvider(var2, var3) == null;
      return var4 && var5;
   }

   private boolean a(Vec3 var1, BlockPos var2) {
      if (!(a.level.getBlockState(var2).getBlock() instanceof AirBlock) && !(a.level.getBlockState(var2).getBlock() instanceof WaterlilyBlock)) {
         return false;
      } else if (var2.getY() > this.q()) {
         return false;
      } else {
         Vec3 var3 = new Vec3((double)var2.getX() + 0.5, (double)var2.getY(), (double)var2.getZ() + 0.5);

         for (Direction var7 : Direction.values()) {
            Vec3 var8 = var3.add(new Vec3((double)var7.getNormal().getX(), (double)var7.getNormal().getY(), (double)var7.getNormal().getZ()).scale(0.5));
            BlockPos var9 = var2.offset(var7.getNormal());
            BlockPos var10 = new BlockPos(var9.getX(), var9.getY(), var9.getZ());
            if (this.a(a.level.getBlockState(var10), a.level, var10)) {
               Vec3 var11 = var8.subtract(var1);
               if (var11.lengthSqr() <= 20.25
                  && var11.dot(new Vec3((double)var7.getNormal().getX(), (double)var7.getNormal().getY(), (double)var7.getNormal().getZ())) >= 0.0
                  && (var7.getOpposite() != Direction.UP || !RecoveredUtilsU.a() || a.options.keyJump.isDown())) {
                  this.m = new BlockPos(var9);
                  this.n = var7.getOpposite();
                  return true;
               }
            }
         }

         return false;
      }
   }

   public BlockPos a(double var1, double var3, double var5) {
      return new BlockPos((int)Math.floor(var1), (int)Math.floor(var3), (int)Math.floor(var5));
   }

   @EventTarget
   public void onMotion(RecoveredEventsImplK var1) {
      if (var1.b() == RecoveredEventsApiAA.PRE && this.i.m() && !this.d.m()) {
         a.options.keyShift.setDown(a.player.onGround() && SafeWalkModule.a(0.3F));
      }
   }

   @EventTarget
   public void onRender(RecoveredEventsImplP var1) {
      if (this.m != null && this.j.m()) {
         PoseStack var2 = var1.b();
         var2.pushPose();
         Vec3 var3 = RecoveredUtilsAd.b();
         var2.translate(-var3.x, -var3.y, -var3.z);
         RenderSystem.enableBlend();
         RenderSystem.defaultBlendFunc();
         RenderSystem.disableDepthTest();
         RenderSystem.depthMask(false);
         RenderSystem.setShader(GameRenderer::getPositionShader);
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 0.4F);
         AABB var4 = new AABB(this.m);
         RecoveredUtilsAd.a(var4, var2);
         RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
         RenderSystem.depthMask(true);
         RenderSystem.enableDepthTest();
         RenderSystem.disableBlend();
         var2.popPose();
      }
   }

   @Override
   public void d() {
      if (a.player != null) {
         this.o = a.player.getInventory().selected;
      }

      this.k = 0;
      this.m = null;
      this.n = null;
   }

   @Override
   public void e() {
      boolean var1 = InputConstants.isKeyDown(a.getWindow().getWindow(), a.options.keyShift.getKey().getValue());
      a.options.keyShift.setDown(var1);
      if (a.player != null && this.o != -1) {
         a.player.getInventory().selected = this.o;
      }
   }

   public RecoveredUtilsCB b(BlockPos var1, Direction var2) {
      RecoveredUtilsCB var3 = this.s() ? RecoveredUtilsCD.a(var1, var2) : RecoveredUtilsCD.a(var1.getCenter());
      RecoveredUtilsCB var4 = new RecoveredUtilsCB(Mth.wrapDegrees(a.player.getYRot() - 180.0F), var3.b());
      boolean var5 = RecoveredUtilsCA.a(var4, var1);
      return var5 ? var4 : var3;
   }

   private boolean s() {
      Vec3 var1 = a.player.getEyePosition();
      BlockPos var2 = BlockPos.containing(var1.x, (double)this.q(), var1.z);
      return a.level.getBlockState(var2).getBlock() instanceof AirBlock || a.level.getBlockState(var2).getBlock() instanceof WaterlilyBlock;
   }
}
