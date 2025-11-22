package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.types.interfaces.Conditional;
import net.bexla.orevolution.content.types.power.armor.ArmorPowerMobEffects;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.function.Supplier;

public class ArmorGrantEffects extends ArmorPowerMobEffects {
    public ArmorGrantEffects(String tooltipId, Conditional conditional, int duration, int amplifier, List<Supplier<MobEffect>> effect) {
        super("", tooltipId, conditional, duration, amplifier, null, effect);
    }

    public ArmorGrantEffects(String tooltipId, Conditional conditional, int duration, int amplifier, Supplier<MobEffect> effect) {
        super("", tooltipId, conditional, duration, amplifier, null, effect != null? List.of(effect) : null);
    }

    @Override
    public void onTickWhileWorn(ItemStack stack, LivingEntity wearer, EquipmentSlot slot) {
        if(!getCBoolean(stack, wearer.level(), wearer, null)) return;

        for(Supplier<MobEffect> p : this.effectsPlayer) {
            if(wearer.hasEffect(p.get())) {
                wearer.getEffect(p.get()).update(new MobEffectInstance(p.get(), this.duration, this.amplifier));
            }
            else {
                wearer.addEffect(new MobEffectInstance(p.get(), this.duration, this.amplifier));
            }
        }
    }
}
