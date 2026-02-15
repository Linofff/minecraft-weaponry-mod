package net.fonil.militarymod.entity.client;

import net.fonil.militarymod.MilitaryMod;
import net.fonil.militarymod.entity.custom.SniperRifleProjectileEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class SniperRifleProjectileRenderer extends ArrowRenderer<SniperRifleProjectileEntity> {

  public static final ResourceLocation TEXTURE =
      ResourceLocation.fromNamespaceAndPath(
          MilitaryMod.MODID, "textures/entity/projectiles/sniper_rifle_bullet.png");

  public SniperRifleProjectileRenderer(EntityRendererProvider.Context context) {
    super(context);
  }

  @Override
  public ResourceLocation getTextureLocation(SniperRifleProjectileEntity entity) {
    return TEXTURE;
  }
}
