package net.bexla.orevolution.content.data.powers.armors.hardcoded;

import net.bexla.orevolution.content.data.Conditionals;
import net.bexla.orevolution.content.types.power.armor.OrevolutionArmorPower;
import net.bexla.orevolution.init.RegMobEffects;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

import java.util.Random;

public class ArmorNecromium extends OrevolutionArmorPower {
    public ArmorNecromium(String tooltip_id) {
        super(tooltip_id, Conditionals.always());
    }

    @Override
    public boolean onDeath(LivingEntity wearer, DamageSource source) {
        if (wearer.hasEffect(RegMobEffects.WEAK_SOUL.get())) {
            return super.onDeath(wearer, source);
        }

        Level level = wearer.level();
        if (level instanceof ServerLevel serverLevel) {
            LightningBolt lightningBolt = EntityType.LIGHTNING_BOLT.create(level);
            if (lightningBolt != null) {
                lightningBolt.setPos(wearer.getX(), wearer.getY(), wearer.getZ());
                lightningBolt.setVisualOnly(true);
                level.addFreshEntity(lightningBolt);
            }

            Random random = new Random();
            for (int i = 0; i < 10; i++) {
                double x = wearer.getX() + (random.nextDouble() - 0.5) * 4;
                double y = wearer.getY() + random.nextDouble() * 2.5;
                double z = wearer.getZ() + (random.nextDouble() - 0.5) * 4;
                serverLevel.sendParticles(ParticleTypes.SOUL, x, y, z, 1, 0, 0, 0, 0);
            }
        }

        wearer.setHealth(1.0F);
        wearer.removeAllEffects();
        wearer.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 140, 0));
        wearer.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 800, 0));
        wearer.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 200, 0));
        wearer.addEffect(new MobEffectInstance(RegMobEffects.WEAK_SOUL.get(), 6000));

        return true;
    }
}
