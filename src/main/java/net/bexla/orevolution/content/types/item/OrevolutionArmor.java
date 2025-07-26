package net.bexla.orevolution.content.types.item;

import net.bexla.orevolution.content.data.base.OrevolutionArmorMaterial;
import net.bexla.orevolution.content.data.base.OrevolutionArmorPower;
import net.bexla.orevolution.content.data.base.OrevolutionToolMaterial;
import net.bexla.orevolution.content.data.interfaces.ArmorPower;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

// got tired of documenting, imma do it later
public class OrevolutionArmor extends ArmorItem {
    private final OrevolutionArmorMaterial material;

    public OrevolutionArmor(OrevolutionArmorMaterial material, Type slotType, Properties properties) {
        super(material.getTier(), slotType, properties);
        this.material = material;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> lines, TooltipFlag flag) {
        this.material.getArmorPowers().appendTooltip(stack, level, lines);
    }

    @SubscribeEvent
    public void onEntityTick(LivingEvent.LivingTickEvent event) {
        LivingEntity entity = event.getEntity();

        for (EquipmentSlot slot : EquipmentSlot.values()) {
            ItemStack stack = entity.getItemBySlot(slot);
            if (!(stack.getItem() instanceof OrevolutionArmor powerArmor)) continue;

            if (slot == EquipmentSlot.HEAD) {
                if (isWearingFullSet(entity, getFullSetList())) {
                    this.material.getArmorPowers().onTickWhileWorn(stack, entity, slot);
                }
            }
        }
    }

    @SubscribeEvent
    public void onLivingHurt(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player) {
            for (EquipmentSlot slot : EquipmentSlot.values()) {
                if (slot == EquipmentSlot.HEAD) {
                    if (isWearingFullSet(player, getFullSetList())) {
                        this.material.getArmorPowers().onAttacked(player, event.getSource(), event.getAmount());
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onLivingAttack(LivingAttackEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            for (EquipmentSlot slot : EquipmentSlot.values()) {
                if (slot == EquipmentSlot.HEAD) {
                    if (isWearingFullSet(player, getFullSetList())) {
                        this.material.getArmorPowers().onAttackTarget(player, event.getEntity());
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onFall(LivingFallEvent event) {
        if (event.getEntity() instanceof Player player) {
            for (EquipmentSlot slot : EquipmentSlot.values()) {
                if (slot == EquipmentSlot.HEAD) {
                    if (isWearingFullSet(player, getFullSetList())) {
                        this.material.getArmorPowers().onFall(player, event.getDistance(), event.getDamageMultiplier());
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onKnockback(LivingKnockBackEvent event) {
        if (event.getEntity() instanceof Player player) {
            for (EquipmentSlot slot : EquipmentSlot.values()) {
                if (slot == EquipmentSlot.HEAD) {
                    if (isWearingFullSet(player, getFullSetList())) {
                        this.material.getArmorPowers().onKnockback(player, event.getStrength(), event.getRatioX(), event.getRatioZ());
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public void onDeath(LivingDeathEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            for (EquipmentSlot slot : EquipmentSlot.values()) {
                if (slot == EquipmentSlot.HEAD) {
                    if (isWearingFullSet(player, getFullSetList())) {
                        this.material.getArmorPowers().onDeath(player, event.getEntity());
                    }
                }
            }
        }
    }

    private static boolean isWearingFullSet(LivingEntity entity, List<Supplier<Item>> requiredSet) {
        if (requiredSet.size() != 4) return false;

        return entity.getItemBySlot(EquipmentSlot.HEAD).getItem() == requiredSet.get(0).get()
                && entity.getItemBySlot(EquipmentSlot.CHEST).getItem() == requiredSet.get(1).get()
                && entity.getItemBySlot(EquipmentSlot.LEGS).getItem() == requiredSet.get(2).get()
                && entity.getItemBySlot(EquipmentSlot.FEET).getItem() == requiredSet.get(3).get();
    }

    public List<Supplier<Item>> getFullSetList() {
        return this.material.getSetList();
    }
}
