package net.bexla.orevolution.content.data.utility;

import com.mojang.logging.LogUtils;
import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.Conditionals;
import net.bexla.orevolution.content.types.interfaces.Conditional;
import net.minecraft.ChatFormatting;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Rarity;
import org.slf4j.Logger;

public class OrevolutionUtils {
    protected static final Logger LOGGER = LogUtils.getLogger();

    public static boolean isWearingFullSet(LivingEntity entity, ArmorMaterial material) {
        return entity.getItemBySlot(EquipmentSlot.HEAD).getItem() instanceof ArmorItem head &&
                entity.getItemBySlot(EquipmentSlot.CHEST).getItem() instanceof ArmorItem chest &&
                entity.getItemBySlot(EquipmentSlot.LEGS).getItem() instanceof ArmorItem legs &&
                entity.getItemBySlot(EquipmentSlot.FEET).getItem() instanceof ArmorItem feet &&
                head.getMaterial() == material &&
                chest.getMaterial() == material &&
                legs.getMaterial() == material &&
                feet.getMaterial() == material;
    }

    public static ResourceLocation modLocat(String id) {
        return new ResourceLocation(Orevolution.MODID, id);
    }

    public static class Conditions {
        public static Conditional IronConditionals = Conditionals.listConditionals(
                Conditionals.isBlockstateTaggedAs(OrevolutionTags.Blocks.deepslates),
                Conditionals.isBlockstateTaggedAs(BlockTags.PLANKS),
                Conditionals.isBlockstateTaggedAs(BlockTags.DIRT)
        );
    }

    public static class Rarities {
        public static Rarity Aether = Rarity.create("aether", style -> style.withColor(ChatFormatting.RED).withBold(true));
    }
}
