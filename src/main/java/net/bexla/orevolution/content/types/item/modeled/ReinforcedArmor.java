package net.bexla.orevolution.content.types.item.modeled;

import net.bexla.orevolution.content.models.ReinforcedNetheriteChestplateModel;
import net.bexla.orevolution.content.models.ReinforcedNetheriteLeggingsModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.function.Consumer;

public class ReinforcedArmor extends ArmorItem {
    public ReinforcedArmor(ArmorMaterial mat, Type type, Properties properties) {
        super(mat, type, properties);
    }

    @Nullable
    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlot slot, String type) {
        if(slot == EquipmentSlot.CHEST) {
            return ReinforcedNetheriteChestplateModel.LAYER_LOCATION;
        }
        else if(slot == EquipmentSlot.LEGS) {
            return ReinforcedNetheriteLeggingsModel.LAYER_LOCATION;
        }
        return super.getArmorTexture(stack, entity, slot, type);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        super.initializeClient(consumer);
        consumer.accept(new IClientItemExtensions() {
            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(
                    LivingEntity livingEntity, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> original) {
                if (slot == EquipmentSlot.CHEST) {
                    return new ReinforcedNetheriteChestplateModel<>(ReinforcedNetheriteChestplateModel.createBodyLayer().bakeRoot());
                }
                else if (slot == EquipmentSlot.LEGS) {
                    return new ReinforcedNetheriteLeggingsModel<>(ReinforcedNetheriteLeggingsModel.createBodyLayer().bakeRoot());
                }
                return original;
            }
        });
    }
}
