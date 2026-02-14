package net.fonil.militarymod.item.custom;

import net.fonil.militarymod.entity.custom.PistolProjectileEntity;
import net.fonil.militarymod.item.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PistolItem extends Item {
  public PistolItem(Properties properties) {
    super(properties);
  }

  @Override
  public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
    ItemStack itemstack = player.getItemInHand(hand);

    if (level.isClientSide) {
      return InteractionResultHolder.pass(itemstack);
    }

    boolean hasAmmo =
        player.isCreative() || player.getInventory().countItem(ModItems.PISTOL_AMMO.get()) > 0;

    if (hasAmmo) {
      PistolProjectileEntity bullet = new PistolProjectileEntity(player, level);

      bullet.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 5.0F, 1.0F);
			bullet.setBaseDamage(8);

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

      player.getCooldowns().addCooldown(this, 20);

      if (!player.isCreative()) {
        removeAmmo(player, ModItems.PISTOL_AMMO.get());
      }

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
