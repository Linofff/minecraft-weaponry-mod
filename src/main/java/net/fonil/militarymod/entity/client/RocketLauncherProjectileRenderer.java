package net.fonil.militarymod.entity.client;

import net.fonil.militarymod.MilitaryMod;
import net.fonil.militarymod.entity.custom.RocketLauncherProjectileEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class RocketLauncherProjectileRenderer extends ArrowRenderer<RocketLauncherProjectileEntity> {

  public static final ResourceLocation TEXTURE =
      ResourceLocation.fromNamespaceAndPath(
          MilitaryMod.MODID, "textures/entity/projectiles/rocket.png");

  public RocketLauncherProjectileRenderer(EntityRendererProvider.Context context) {
    super(context);
  }

  @Override
  public ResourceLocation getTextureLocation(RocketLauncherProjectileEntity entity) {
    return TEXTURE;
  }
}
