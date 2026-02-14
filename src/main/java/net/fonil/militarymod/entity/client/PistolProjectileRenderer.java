package net.fonil.militarymod.entity.client;

import net.fonil.militarymod.MilitaryMod;
import net.fonil.militarymod.entity.custom.PistolProjectileEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class PistolProjectileRenderer extends ArrowRenderer<PistolProjectileEntity> {

  public static final ResourceLocation TEXTURE =
      ResourceLocation.fromNamespaceAndPath(
          MilitaryMod.MODID, "textures/entity/projectiles/pistol_bullet.png");

  public PistolProjectileRenderer(EntityRendererProvider.Context context) {
    super(context);
  }

  @Override
  public ResourceLocation getTextureLocation(PistolProjectileEntity entity) {
    return TEXTURE;
  }
}
