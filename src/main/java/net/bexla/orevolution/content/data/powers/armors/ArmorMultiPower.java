package net.bexla.orevolution.content.data.powers.armors;

import net.bexla.orevolution.content.data.Conditionals;
import net.bexla.orevolution.content.data.base.OrevolutionArmorPower;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class ArmorMultiPower extends OrevolutionArmorPower {
    private final List<OrevolutionArmorPower> powers;

    public ArmorMultiPower(List<OrevolutionArmorPower> powers) {
        super("", Conditionals.always());
        this.powers = powers;
    }

    @Override
    public void appendTooltip(ItemStack stack, Level level, List<Component> lines) {
        for(OrevolutionArmorPower p : this.powers) {
            p.appendTooltip(stack, level, lines);
        }
    }

    public void onTickWhileWorn(ItemStack stack, LivingEntity wearer, EquipmentSlot slot) {
        for(OrevolutionArmorPower p : this.powers) {
            p.onTickWhileWorn(stack, wearer, slot);
        }
    }

    public void onAttacked(LivingEntity wearer, DamageSource source, float amount) {
        for(OrevolutionArmorPower p : this.powers) {
            p.onAttacked(wearer, source, amount);
        }
    }

    @Override
    public void onAttackTarget(LivingEntity wearer, LivingEntity target) {
        for(OrevolutionArmorPower p : this.powers) {
            p.onAttackTarget(wearer, target);
        }
    }

    @Override
    public void onDeath(LivingEntity wearer, LivingEntity killer) {
        for(OrevolutionArmorPower p : this.powers) {
            p.onDeath(wearer, killer);
        }
    }

    @Override
    public void onFall(LivingEntity wearer, float distance, float damageMultiplier) {
        for(OrevolutionArmorPower p : this.powers) {
            p.onFall(wearer, distance, damageMultiplier);
        }
    }

    @Override
    public void onKnockback(LivingEntity wearer, float strength, double ratioX, double ratioZ) {
        for(OrevolutionArmorPower p : this.powers) {
            p.onKnockback(wearer, strength, ratioX, ratioZ);
        }
    }
}
