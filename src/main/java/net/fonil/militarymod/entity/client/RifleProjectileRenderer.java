package net.fonil.militarymod.entity.client;

import net.fonil.militarymod.MilitaryMod;
import net.fonil.militarymod.entity.custom.RifleProjectileEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class RifleProjectileRenderer extends ArrowRenderer<RifleProjectileEntity> {

  public static final ResourceLocation TEXTURE =
      ResourceLocation.fromNamespaceAndPath(
          MilitaryMod.MODID, "textures/entity/projectiles/rifle_bullet.png");

  public RifleProjectileRenderer(EntityRendererProvider.Context context) {
    super(context);
  }

  @Override
  public ResourceLocation getTextureLocation(RifleProjectileEntity entity) {
    return TEXTURE;
  }
}
