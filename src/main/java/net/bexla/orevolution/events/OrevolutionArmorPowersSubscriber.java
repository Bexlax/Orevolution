package net.bexla.orevolution.events;

import com.mojang.logging.LogUtils;
import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.types.ArmorPowerRegistry;
import net.bexla.orevolution.content.types.interfaces.ArmorPower;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import static net.bexla.orevolution.content.data.utility.OrevolutionUtils.isWearingFullSet;

@Mod.EventBusSubscriber
public class OrevolutionArmorPowersSubscriber {
    private static final Logger LOGGER = LogUtils.getLogger();

    private static void withArmorPower(LivingEntity entity, Consumer<ArmorPower> action) {
        if (!OrevolutionConfig.COMMON.armorsPowers.get()) return;

        ItemStack helmet = entity.getItemBySlot(EquipmentSlot.HEAD);

        Item helItem = helmet.getItem();

        ArmorPower singularPower = ArmorPowerRegistry.getItemPower(() -> helItem);
        if (singularPower != ArmorPower.EMPTY) {
            action.accept(singularPower);
        }

        if (!(helItem instanceof ArmorItem armorItem)) return;

        ArmorMaterial material = armorItem.getMaterial();
        if (!isWearingFullSet(entity, material)) return;

        ArmorPower power = ArmorPowerRegistry.getPower(material);
        if (power != ArmorPower.EMPTY) {
            action.accept(power);
        }
    }

    @SubscribeEvent
    public static void onEntityTick(LivingEvent.LivingTickEvent event) {
        withArmorPower(event.getEntity(), power ->
                power.onTickWhileWorn(event.getEntity().getItemBySlot(EquipmentSlot.HEAD), event.getEntity(), EquipmentSlot.HEAD)
        );
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        withArmorPower(event.getEntity(), power ->
                power.onAttacked(event.getEntity(), event.getSource(), event.getAmount())
        );
    }

    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event) {
        withArmorPower(event.getEntity(), power -> {
            if (event.getSource().getEntity() instanceof LivingEntity attacker)
                power.onAttackTarget(event.getEntity(), attacker);
        });
    }

    @SubscribeEvent
    public static void onFall(LivingFallEvent event) {
        withArmorPower(event.getEntity(), power ->
                power.onFall(event.getEntity(), event.getDistance(), event.getDamageMultiplier())
        );
    }

    @SubscribeEvent
    public static void onKnockback(LivingKnockBackEvent event) {
        withArmorPower(event.getEntity(), power ->
                power.onKnockback(event.getEntity(), event.getStrength(), event.getRatioX(), event.getRatioZ())
        );
    }

    @SubscribeEvent
    public static void onDeath(LivingDeathEvent event) {
        withArmorPower(event.getEntity(), power -> {
            if (event.getSource().getEntity() instanceof LivingEntity killer)
                event.setCanceled(power.onDeath(event.getEntity(), killer));
        });
    }

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        if(!OrevolutionConfig.CLIENT.armorsPowersTip.get()) return;

        List<Component> tip = new ArrayList<>();

        LivingEntity entity = event.getEntity();

        if (entity == null) return;

        List<Component> tooltip = event.getToolTip();
        ItemStack stack = event.getItemStack();

        if(!(stack.getItem() instanceof ArmorItem item)) return;

        Item helmet = entity.getItemBySlot(EquipmentSlot.HEAD).getItem();

        ArmorPower singularPower = ArmorPowerRegistry.getItemPower(() -> helmet);
        if (singularPower != ArmorPower.EMPTY) {
            tip.addAll(singularPower.appendTooltip(stack, entity.level(), tooltip));
        }

        ArmorMaterial material = item.getMaterial();

        if (!(item.getType() == ArmorItem.Type.HELMET
                || item.getType() == ArmorItem.Type.CHESTPLATE
                || item.getType() == ArmorItem.Type.LEGGINGS
                || item.getType() == ArmorItem.Type.BOOTS)) return;

        ArmorPower power = ArmorPowerRegistry.getPower(material);

        if(power != ArmorPower.EMPTY) {
            tip.add(0, Component.translatable("tooltip.orevolution.full_set_bonus"));
            tip.addAll(power.appendTooltip(stack, entity.level(), tooltip));
        }

        tooltip.addAll(1, tip);
    }
}
