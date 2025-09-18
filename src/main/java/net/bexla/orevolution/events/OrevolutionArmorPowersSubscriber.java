package net.bexla.orevolution.events;

import com.mojang.logging.LogUtils;
import net.bexla.orevolution.content.data.interfaces.ArmorPower;
import net.bexla.orevolution.content.types.ArmorPowerRegistry;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

import java.util.List;

import static net.bexla.orevolution.OrevolutionConfig.armorsPowers;
import static net.bexla.orevolution.OrevolutionConfigClient.armorsPowersTip;
import static net.bexla.orevolution.content.data.utility.OrevolutionUtils.isWearingFullSet;

@Mod.EventBusSubscriber
public class OrevolutionArmorPowersSubscriber {
    private static final Logger LOGGER = LogUtils.getLogger();

    @OnlyIn(Dist.CLIENT)
    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        if(!armorsPowersTip) return;

        LivingEntity entity = event.getEntity();
        List<Component> tooltip = event.getToolTip();

        if (tooltip == null) return;
        if (entity == null) return;

        ItemStack helmet = entity.getItemBySlot(EquipmentSlot.HEAD);

        if (!(helmet.getItem() instanceof ArmorItem armorItem)) return;

        ArmorMaterial material = armorItem.getMaterial();

        ArmorPower power = ArmorPowerRegistry.getPower(material);

        if(power == null) return;

        power.appendTooltip(helmet, event.getEntity().level(), tooltip);
    }

    @SubscribeEvent
    public static void onEntityTick(LivingEvent.LivingTickEvent event) {
        if(!armorsPowers) return;

        LivingEntity entity = event.getEntity();
        ItemStack helmet = entity.getItemBySlot(EquipmentSlot.HEAD);

        if (!(helmet.getItem() instanceof ArmorItem armorItem)) return;

        ArmorMaterial material = armorItem.getMaterial();

        if (!isWearingFullSet(entity, material)) return;

        ArmorPower power = ArmorPowerRegistry.getPower(material);

        if(power == null) return;

        power.onTickWhileWorn(entity.getItemBySlot(EquipmentSlot.HEAD), entity, EquipmentSlot.HEAD);
    }

    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent event) {
        if(!armorsPowers) return;

        LivingEntity entity = event.getEntity();
        ItemStack helmet = entity.getItemBySlot(EquipmentSlot.HEAD);

        if (!(helmet.getItem() instanceof ArmorItem armorItem)) return;

        ArmorMaterial material = armorItem.getMaterial();

        if (!isWearingFullSet(entity, material)) return;

        ArmorPower power = ArmorPowerRegistry.getPower(material);

        if(power == null) return;

        power.onAttacked(entity, event.getSource(), event.getAmount());
    }

    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event) {
        if(!armorsPowers) return;

        LivingEntity entity = event.getEntity();
        ItemStack helmet = entity.getItemBySlot(EquipmentSlot.HEAD);

        if (!(helmet.getItem() instanceof ArmorItem armorItem)) return;

        ArmorMaterial material = armorItem.getMaterial();

        if (!isWearingFullSet(entity, material)) return;

        ArmorPower power = ArmorPowerRegistry.getPower(material);

        if(power == null) return;

        power.onAttackTarget(entity, (LivingEntity)event.getSource().getEntity());
    }

    @SubscribeEvent
    public static void onFall(LivingFallEvent event) {
        if(!armorsPowers) return;

        LivingEntity entity = event.getEntity();
        ItemStack helmet = entity.getItemBySlot(EquipmentSlot.HEAD);

        if (!(helmet.getItem() instanceof ArmorItem armorItem)) return;

        ArmorMaterial material = armorItem.getMaterial();

        if (!isWearingFullSet(entity, material)) return;

        ArmorPower power = ArmorPowerRegistry.getPower(material);

        if(power == null) return;

        power.onFall(entity, event.getDistance(), event.getDamageMultiplier());
    }

    @SubscribeEvent
    public static void onKnockback(LivingKnockBackEvent event) {
        if(!armorsPowers) return;

        LivingEntity entity = event.getEntity();
        ItemStack helmet = entity.getItemBySlot(EquipmentSlot.HEAD);

        if (!(helmet.getItem() instanceof ArmorItem armorItem)) return;

        ArmorMaterial material = armorItem.getMaterial();

        if (!isWearingFullSet(entity, material)) return;

        ArmorPower power = ArmorPowerRegistry.getPower(material);

        if(power == null) return;

        power.onKnockback(entity, event.getStrength(), event.getRatioX(), event.getRatioZ());
    }

    @SubscribeEvent
    public static void onDeath(LivingDeathEvent event) {
        if(!armorsPowers) return;

        LivingEntity entity = event.getEntity();
        ItemStack helmet = entity.getItemBySlot(EquipmentSlot.HEAD);

        if (!(helmet.getItem() instanceof ArmorItem armorItem)) return;

        ArmorMaterial material = armorItem.getMaterial();

        if (!isWearingFullSet(entity, material)) return;

        ArmorPower power = ArmorPowerRegistry.getPower(material);

        if(power == null) return;

        power.onDeath(entity, (LivingEntity)event.getSource().getEntity());
    }
}
