package net.bexla.orevolution.content.types.item.modeled;

import net.bexla.orevolution.events.ClientSubscriber;
import net.bexla.orevolution.models.AethersteelChestplateModel;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

import java.util.function.Consumer;

public class AethersteelChestplate extends ArmorItem {
    public AethersteelChestplate(ArmorMaterial p_40386_, Type p_266831_, Properties p_40388_) {
        super(p_40386_, p_266831_, p_40388_);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            @Override
            public HumanoidModel<?> getHumanoidArmorModel(LivingEntity entity, ItemStack stack,
                                                          EquipmentSlot slot, HumanoidModel<?> defaultModel) {
                if (slot == EquipmentSlot.CHEST) {
                    ModelPart baked = Minecraft.getInstance().getEntityModels()
                            .bakeLayer(ClientSubscriber.AETHERSTEEL_CHESPLATE_LAYER);
                    return new AethersteelChestplateModel<>(baked);
                }
                return defaultModel;
            }
        });
    }
}
