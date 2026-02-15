package net.fonil.militarymod.item.custom;

import net.fonil.militarymod.entity.custom.SniperRifleProjectileEntity;
import net.fonil.militarymod.item.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class SniperRifleItem extends Item {
  public SniperRifleItem(Properties properties) {
    super(properties);
  }

  @Override
  public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
    ItemStack itemstack = player.getItemInHand(hand);
    player.playSound(SoundEvents.SPYGLASS_USE, 1.0F, 1.0F);

    boolean hasAmmo =
        player.isCreative()
            || player.getInventory().countItem(ModItems.SNIPER_RIFLE_AMMO.get()) > 0;

    if (!hasAmmo) {
      return InteractionResultHolder.fail(itemstack);
    }

    player.startUsingItem(hand);
    return InteractionResultHolder.consume(itemstack);
  }

  @Override
  public int getUseDuration(ItemStack stack, LivingEntity entity) {
    return 72000;
  }

  @Override
  public UseAnim getUseAnimation(ItemStack stack) {
    return UseAnim.SPYGLASS;
  }

  @Override
  public void releaseUsing(ItemStack stack, Level level, LivingEntity entityLiving, int timeLeft) {
    if (!(entityLiving instanceof Player player)) return;

    int duration = this.getUseDuration(stack, entityLiving) - timeLeft;
    if (duration < 5) return;

    boolean hasAmmo =
        player.isCreative()
            || player.getInventory().countItem(ModItems.SNIPER_RIFLE_AMMO.get()) > 0;

    if (hasAmmo) {
      if (!level.isClientSide) {
        SniperRifleProjectileEntity bullet = new SniperRifleProjectileEntity(player, level);
        bullet.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 8.0F, 0F);
        bullet.setBaseDamage(12);
        level.addFreshEntity(bullet);

        level.playSound(
            null,
            player.getX(),
            player.getY(),
            player.getZ(),
            SoundEvents.GENERIC_EXPLODE,
            SoundSource.PLAYERS,
            1.0F,
            2.0F);

        if (level instanceof ServerLevel serverLevel) {
          Vec3 lookVec = player.getLookAngle();
          double x = player.getX() + lookVec.x * 0.8;
          double y = player.getEyeY() + lookVec.y * 0.8;
          double z = player.getZ() + lookVec.z * 0.8;
          serverLevel.sendParticles(
              ParticleTypes.CAMPFIRE_COSY_SMOKE, x, y, z, 10, 0.1, 0.1, 0.1, 0.20);
        }

        if (!player.isCreative()) {
          removeAmmo(player, ModItems.SNIPER_RIFLE_AMMO.get());
        }
      }

      Vec3 lookVec = player.getLookAngle();
      double strength = 1.5;
      player.setDeltaMovement(
          player.getDeltaMovement().add(-lookVec.x * strength, 0.1, -lookVec.z * strength));

      player.getCooldowns().addCooldown(this, 100);
    }
  }

  private void removeAmmo(Player player, Item item) {
    for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
      ItemStack stack = player.getInventory().getItem(i);
      if (stack.getItem() == item) {
        stack.shrink(1);
        return;
      }
    }
  }
}
