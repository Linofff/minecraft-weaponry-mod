package net.fonil.militarymod.entity; // Updated to match your military mod package

import net.fonil.militarymod.MilitaryMod; // Assuming your main class is MilitaryMod
import net.fonil.militarymod.entity.custom.PistolProjectileEntity;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, MilitaryMod.MODID);

    public static final Supplier<EntityType<PistolProjectileEntity>> PISTOL_PROJECTILE =
            ENTITY_TYPES.register("pistol_projectile", 
                    () -> EntityType.Builder.<PistolProjectileEntity>of(PistolProjectileEntity::new, MobCategory.MISC)
                    .sized(0.1f, 0.1f)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build("pistol_projectile"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
