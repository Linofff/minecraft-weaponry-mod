package net.fonil.militarymod.entity.client;

import net.fonil.militarymod.MilitaryMod;
import net.fonil.militarymod.entity.custom.ExplosiveSniperRifleProjectileEntity;
import net.minecraft.client.renderer.entity.ArrowRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ExplosiveSniperRifleProjectileRenderer extends ArrowRenderer<ExplosiveSniperRifleProjectileEntity> {

  public static final ResourceLocation TEXTURE =
      ResourceLocation.fromNamespaceAndPath(
          MilitaryMod.MODID, "textures/entity/projectiles/explosive_sniper_rifle_bullet.png");

  public ExplosiveSniperRifleProjectileRenderer(EntityRendererProvider.Context context) {
    super(context);
  }

  @Override
  public ResourceLocation getTextureLocation(ExplosiveSniperRifleProjectileEntity entity) {
    return TEXTURE;
  }
}
