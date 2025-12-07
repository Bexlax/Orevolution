package net.bexla.orevolution.content.types.effects;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.ItemStack;

import java.util.Collections;
import java.util.List;

public class DebuffEffect extends MobEffect {
    public DebuffEffect() {
        super(MobEffectCategory.HARMFUL, 0x809696);
    }

    @Override
    public List<ItemStack> getCurativeItems() {
        return Collections.emptyList();
    }
}
