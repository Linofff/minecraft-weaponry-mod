package net.fonil.militarymod.item.custom;

import net.fonil.militarymod.entity.custom.RifleProjectileEntity;
import net.fonil.militarymod.item.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class RifleItem extends Item {
  public RifleItem(Properties properties) {
    super(properties);
  }

  @Override
  public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
    ItemStack itemstack = player.getItemInHand(hand);
    boolean hasAmmo =
        player.isCreative()
            || player.getInventory().countItem(ModItems.RIFLE_AMMO.get()) > 0;

    if (hasAmmo) {
      if (!level.isClientSide) {
        RifleProjectileEntity bullet = new RifleProjectileEntity(player, level);
        bullet.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 8.0F, 0F);
        bullet.setBaseDamage(5);
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
              ParticleTypes.SMOKE, x, y, z, 20, 0.1, 0.1, 0.1, 0.20);
        }

        if (!player.isCreative()) {
          removeAmmo(player, ModItems.RIFLE_AMMO.get());
        }
      }

      Vec3 lookVec = player.getLookAngle();
      double strength = 0.4;
      player.setDeltaMovement(
          player.getDeltaMovement().add(-lookVec.x * strength, 0.1, -lookVec.z * strength));

      player.getCooldowns().addCooldown(this, 8);
      return InteractionResultHolder.success(itemstack);
    } else {
      level.playSound(
          null,
          player.getX(),
          player.getY(),
          player.getZ(),
          SoundEvents.DISPENSER_FAIL,
          SoundSource.PLAYERS,
          1.0F,
          1.0F);
      return InteractionResultHolder.fail(itemstack);
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
