package net.fonil.militarymod.event;

import net.fonil.militarymod.MilitaryMod;
import net.fonil.militarymod.entity.ModEntities;
import net.fonil.militarymod.entity.client.PistolProjectileRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(
    modid = MilitaryMod.MODID,
    value = Dist.CLIENT)
public class ClientModEvents {

  @SubscribeEvent
  public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
    event.registerEntityRenderer(
        ModEntities.PISTOL_PROJECTILE.get(), PistolProjectileRenderer::new);
  }
}
