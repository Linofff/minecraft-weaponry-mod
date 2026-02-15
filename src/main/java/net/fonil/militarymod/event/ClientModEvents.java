package net.fonil.militarymod.event;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fonil.militarymod.MilitaryMod;
import net.fonil.militarymod.entity.ModEntities;
import net.fonil.militarymod.entity.client.PistolProjectileRenderer;
import net.fonil.militarymod.entity.client.RifleProjectileRenderer;
import net.fonil.militarymod.entity.client.SniperRifleProjectileRenderer;
import net.fonil.militarymod.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.event.RenderHandEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;

@EventBusSubscriber(modid = MilitaryMod.MODID, value = Dist.CLIENT)
public class ClientModEvents {

  private static final ResourceLocation SCOPE_LOCATION =
      ResourceLocation.fromNamespaceAndPath(MilitaryMod.MODID, "textures/misc/sniper_scope.png");

  @SubscribeEvent
  public static void onComputeFovModifier(ComputeFovModifierEvent event) {
    if (event.getPlayer().isUsingItem()
        && (event.getPlayer().getUseItem().getItem() == ModItems.SNIPER_RIFLE.get()
            || event.getPlayer().getUseItem().getItem() == ModItems.GUN_SCOPE.get())) {

      float fovModifier = 0.25f;
      event.setNewFovModifier(fovModifier);
    }
  }

  @SubscribeEvent
  public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
    event.registerEntityRenderer(
        ModEntities.PISTOL_PROJECTILE.get(), PistolProjectileRenderer::new);
    event.registerEntityRenderer(ModEntities.RIFLE_PROJECTILE.get(), RifleProjectileRenderer::new);
    event.registerEntityRenderer(
        ModEntities.SNIPER_RIFLE_PROJECTILE.get(), SniperRifleProjectileRenderer::new);
  }

  @SubscribeEvent
  public static void registerGuiLayers(RegisterGuiLayersEvent event) {
    event.registerAboveAll(
        ResourceLocation.fromNamespaceAndPath(MilitaryMod.MODID, "sniper_scope"),
        (guiGraphics, deltaTracker) -> {
          Minecraft minecraft = Minecraft.getInstance();
          if (minecraft.player != null
              && minecraft.player.isUsingItem()
              && (minecraft.player.getUseItem().getItem() == ModItems.SNIPER_RIFLE.get()
                  || minecraft.player.getUseItem().getItem() == ModItems.GUN_SCOPE.get())
              && minecraft.options.getCameraType().isFirstPerson()) {

            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);

            int width = guiGraphics.guiWidth();
            int height = guiGraphics.guiHeight();

            guiGraphics.blit(SCOPE_LOCATION, 0, 0, -90, 0.0F, 0.0F, width, height, width, height);

            RenderSystem.disableBlend();
          }
        });
  }

  @SubscribeEvent
  public static void onRenderGuiLayerPre(RenderGuiLayerEvent.Pre event) {
    if (VanillaGuiLayers.CROSSHAIR.equals(event.getName())) {
      Minecraft minecraft = Minecraft.getInstance();
      if (minecraft.player != null
          && minecraft.player.isUsingItem()
          && (minecraft.player.getUseItem().getItem() == ModItems.SNIPER_RIFLE.get()
              || minecraft.player.getUseItem().getItem() == ModItems.GUN_SCOPE.get())) {
        event.setCanceled(true);
      }
    }
  }

  @SubscribeEvent
  public static void onRenderHand(RenderHandEvent event) {
    Minecraft minecraft = Minecraft.getInstance();
    if (minecraft.player != null
        && minecraft.player.isUsingItem()
        && (minecraft.player.getUseItem().getItem() == ModItems.SNIPER_RIFLE.get()
            || minecraft.player.getUseItem().getItem() == ModItems.GUN_SCOPE.get())) {
      event.setCanceled(true);
    }
  }
}
