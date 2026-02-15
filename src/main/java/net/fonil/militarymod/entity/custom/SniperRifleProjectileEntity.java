package net.fonil.militarymod.entity.custom;

import net.fonil.militarymod.entity.ModEntities;
import net.fonil.militarymod.item.ModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class SniperRifleProjectileEntity extends AbstractArrow {

    public SniperRifleProjectileEntity(EntityType<? extends AbstractArrow> entityType, Level level) {
        super(entityType, level);
    }

    public SniperRifleProjectileEntity(LivingEntity shooter, Level level) {
        super(
            ModEntities.SNIPER_RIFLE_PROJECTILE.get(),
            shooter,
            level,
            new ItemStack(ModItems.SNIPER_RIFLE_AMMO.get()),
            null
        );
    }

    @Override
    protected double getDefaultGravity() {
        return 0.01; 
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItems.SNIPER_RIFLE_AMMO.get());
    }

    @Override
    protected void doPostHurtEffects(LivingEntity pLiving) {
        super.doPostHurtEffects(pLiving);
        this.pickup = Pickup.DISALLOWED;
    }

    @Override
    public void tick() {
        super.tick();
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);

        Entity entity = result.getEntity();

        entity.hurt(this.damageSources().thrown(this, this.getOwner()), 6);

        if (!this.level().isClientSide) {
            this.discard();
        }
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);

        if (!this.level().isClientSide) {
            this.discard();
        }
    }
}
