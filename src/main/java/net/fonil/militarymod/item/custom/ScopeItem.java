package net.fonil.militarymod.item.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

public class ScopeItem extends Item {
  public ScopeItem(Properties properties) {
    super(properties);
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
  public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
    player.playSound(SoundEvents.SPYGLASS_USE, 1.0F, 1.0F);
    player.startUsingItem(hand);
    return InteractionResultHolder.consume(player.getItemInHand(hand));
  }

  @Override
  public void releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
    if (entity instanceof Player player) {
      player.playSound(SoundEvents.SPYGLASS_STOP_USING, 1.0F, 1.0F);
    }
    super.releaseUsing(stack, level, entity, timeLeft);
  }
}
