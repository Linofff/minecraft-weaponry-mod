package net.fonil.militarymod.event;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fonil.militarymod.MilitaryMod;
import net.fonil.militarymod.entity.ModEntities;
import net.fonil.militarymod.entity.client.ExplosiveSniperRifleProjectileRenderer;
import net.fonil.militarymod.entity.client.PistolProjectileRenderer;
import net.fonil.militarymod.entity.client.RifleProjectileRenderer;
import net.fonil.militarymod.entity.client.RocketLauncherProjectileRenderer;
import net.fonil.militarymod.entity.client.SniperRifleProjectileRenderer;
import net.fonil.militarymod.item.ModItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.client.event.RenderGuiLayerEvent;
import net.neoforged.neoforge.client.event.RenderHandEvent;
import net.neoforged.neoforge.client.gui.VanillaGuiLayers;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber(modid = MilitaryMod.MODID, value = Dist.CLIENT)
public class ClientModEvents {

  private static final ResourceLocation SCOPE_LOCATION =
      ResourceLocation.fromNamespaceAndPath(MilitaryMod.MODID, "textures/misc/sniper_scope.png");

  private static double originalSensitivity = -1.0;

  @SubscribeEvent
  public static void onComputeFovModifier(ComputeFovModifierEvent event) {
    if (event.getPlayer().isUsingItem()
        && (event.getPlayer().getUseItem().getItem() == ModItems.SNIPER_RIFLE.get()
            || event.getPlayer().getUseItem().getItem() == ModItems.EXPLOSIVE_SNIPER_RIFLE.get()
            || event.getPlayer().getUseItem().getItem() == ModItems.ROCKET_LAUNCHER.get()
            || event.getPlayer().getUseItem().getItem() == ModItems.GUN_SCOPE.get())) {

      float fovModifier = 0.25f;
      event.setNewFovModifier(fovModifier);
    }
  }

  @SubscribeEvent
  public static void onClientTick(ClientTickEvent.Post event) {
    Minecraft mc = Minecraft.getInstance();

    if (mc.player == null) {
      restoreSensitivity(mc);
      return;
    }

    boolean isScoping =
        mc.player.isUsingItem()
            && (mc.player.getUseItem().getItem() == ModItems.SNIPER_RIFLE.get()
                || mc.player.getUseItem().getItem() == ModItems.EXPLOSIVE_SNIPER_RIFLE.get()
                || mc.player.getUseItem().getItem() == ModItems.ROCKET_LAUNCHER.get()
                || mc.player.getUseItem().getItem() == ModItems.GUN_SCOPE.get());

    if (isScoping) {
      if (originalSensitivity == -1.0) {
        originalSensitivity = mc.options.sensitivity().get();
        mc.options.sensitivity().set(originalSensitivity * 0.25);
      }
    } else {
      restoreSensitivity(mc);
    }
  }

  private static void restoreSensitivity(Minecraft mc) {
    if (originalSensitivity != -1.0) {
      mc.options.sensitivity().set(originalSensitivity);
      originalSensitivity = -1.0;
    }
  }

  @SubscribeEvent
  public static void onLivingIncomingDamage(LivingIncomingDamageEvent event) {
    if (event.getEntity() instanceof Player player) {
      if (event.getSource().is(DamageTypeTags.IS_PROJECTILE) || event.getSource().is(DamageTypeTags.IS_EXPLOSION)) {
        int protectionLevel = 0;

        if (player.getItemBySlot(EquipmentSlot.CHEST).is(ModItems.BULLETPROOF_VEST.get())) {
          protectionLevel++;
        }
        if (player.getItemBySlot(EquipmentSlot.HEAD).is(ModItems.BULLETPROOF_HELMET.get())) {
          protectionLevel++;
        }

        if (protectionLevel > 0) {
          float currentDamage = event.getAmount();
          float damageCap =
              switch (protectionLevel) {
                case 1 -> 2.5f;
                case 2 -> 1.0f;
                default -> currentDamage;
              };

          float newDamage = Math.min(currentDamage, damageCap);
          event.setAmount(newDamage);
        }
      }
    }
  }

  @SubscribeEvent
  public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
    event.registerEntityRenderer(
        ModEntities.PISTOL_PROJECTILE.get(), PistolProjectileRenderer::new);
    event.registerEntityRenderer(ModEntities.RIFLE_PROJECTILE.get(), RifleProjectileRenderer::new);
    event.registerEntityRenderer(
        ModEntities.SNIPER_RIFLE_PROJECTILE.get(), SniperRifleProjectileRenderer::new);
    event.registerEntityRenderer(
        ModEntities.EXPLOSIVE_SNIPER_RIFLE_PROJECTILE.get(),
        ExplosiveSniperRifleProjectileRenderer::new);
    event.registerEntityRenderer(
        ModEntities.ROCKET_LANUCHER_PROJECTILE.get(), RocketLauncherProjectileRenderer::new);
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
                  || minecraft.player.getUseItem().getItem()
                      == ModItems.EXPLOSIVE_SNIPER_RIFLE.get()
                  || minecraft.player.getUseItem().getItem() == ModItems.ROCKET_LAUNCHER.get()
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
              || minecraft.player.getUseItem().getItem() == ModItems.EXPLOSIVE_SNIPER_RIFLE.get()
              || minecraft.player.getUseItem().getItem() == ModItems.ROCKET_LAUNCHER.get()
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
            || minecraft.player.getUseItem().getItem() == ModItems.EXPLOSIVE_SNIPER_RIFLE.get()
            || minecraft.player.getUseItem().getItem() == ModItems.ROCKET_LAUNCHER.get()
            || minecraft.player.getUseItem().getItem() == ModItems.GUN_SCOPE.get())) {
      event.setCanceled(true);
    }
  }
}
