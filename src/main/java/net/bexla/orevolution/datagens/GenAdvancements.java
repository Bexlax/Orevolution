package net.bexla.orevolution.datagens;

import net.bexla.orevolution.Orevolution;
import net.bexla.orevolution.content.data.utility.OrevolutionTags;
import net.bexla.orevolution.datagens.langs.GENLangENUS;
import net.bexla.orevolution.init.RegBlocks;
import net.bexla.orevolution.init.RegItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class GenAdvancements extends ForgeAdvancementProvider {
    public GenAdvancements(PackOutput output, CompletableFuture<HolderLookup.Provider> future, ExistingFileHelper helper, GENLangENUS lang) {
        super(output, future, helper, List.of(new OrevolutionAdvancements(lang)));
    }

    static class OrevolutionAdvancements implements ForgeAdvancementProvider.AdvancementGenerator {
        private final GENLangENUS lang;

        OrevolutionAdvancements(GENLangENUS lang) {
            this.lang = lang;
            lang.addSubProvider(() -> this.generate(null, $ -> {
            }, null));
        }

        @Override
        public void generate(@Nullable HolderLookup.Provider provider, Consumer<Advancement> consumer, @Nullable ExistingFileHelper helper) {
            Advancement.Builder.advancement()
                    .parent(getAdv("minecraft:story/upgrade_tools"))
                    .display(info(RegItems.TIN_PICKAXE.get(), "tin_upgrade", FrameType.TASK,
                            "A Really Tin Pickaxe",
                            "Craft a tin pickaxe")
                    )
                    .addCriterion("has_tin_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(RegItems.TIN_PICKAXE.get()).build()))
                    .save(consumer, "orevolution:story/tin_upgrade");

            Advancement obtainPlatinum = Advancement.Builder.advancement()
                    .parent(getAdv("minecraft:story/iron_tools"))
                    .display(info(RegItems.PLATINUM_INGOT.get(), "obtain_platinum", FrameType.TASK,
                            "Hardest Metal in the World",
                            "Smelt a Platinum Ingot")
                    )
                    .addCriterion("has_platinum_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(OrevolutionTags.Items.platIngots).build()))
                    .save(consumer, "orevolution:story/obtain_platinum");

            Advancement.Builder.advancement()
                    .parent(obtainPlatinum)
                    .display(info(RegItems.PLATINUM_CHESTPLATE.get(), "platinum_armor", FrameType.TASK,
                            "The King in Blue",
                            "Craft a full set of Platinum Armor")
                    )
                    .addCriterion("has_all_platinum_armor", InventoryChangeTrigger.TriggerInstance.hasItems(
                            RegItems.PLATINUM_HELMET.get(), RegItems.PLATINUM_CHESTPLATE.get(), RegItems.PLATINUM_LEGGINGS.get(), RegItems.PLATINUM_BOOTS.get()
                    ))
                    .save(consumer, "orevolution:story/platinum_armor");

            Advancement.Builder.advancement()
                    .parent(obtainPlatinum)
                    .display(info(RegItems.PLATINUM_PICKAXE.get(), "platinum_gear", FrameType.TASK,
                            "I'll Be Able To Dig Pretty Fast",
                            "Craft a Platinum Pickaxe")
                    )
                    .addCriterion("has_platinum_pickaxe", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(RegItems.PLATINUM_PICKAXE.get()).build()))
                    .save(consumer, "orevolution:story/platinum_gear");

            Advancement obtainTungsten = Advancement.Builder.advancement()
                    .parent(getAdv("minecraft:nether/root"))
                    .display(info(RegItems.TUNGSTEN_INGOT.get(), "obtain_tungsten", FrameType.TASK,
                            "Hardest Metal in the Nether",
                            "Smelt a Tungsten Ingot")
                    )
                    .addCriterion("has_tungsten_ingot", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(OrevolutionTags.Items.tungsIngots).build()))
                    .save(consumer, "orevolution:nether/obtain_tungsten");

            Advancement reinforcedArmor = Advancement.Builder.advancement()
                    .parent(obtainTungsten)
                    .display(info(RegItems.REINFORCED_NETHERITE_CHESTPLATE.get(), "reinforced_armor", FrameType.CHALLENGE,
                            "Unmovable Object",
                            "Obtain a full set of reinforced netherite armor")
                    )
                    .addCriterion("has_all_reinforced_armor", InventoryChangeTrigger.TriggerInstance.hasItems(
                            RegItems.REINFORCED_NETHERITE_HELMET.get(), RegItems.REINFORCED_NETHERITE_CHESTPLATE.get(), RegItems.REINFORCED_NETHERITE_LEGGINGS.get(), RegItems.REINFORCED_NETHERITE_BOOTS.get()
                    ))
                    .save(consumer, "orevolution:nether/reinforced_armor");

            Advancement.Builder.advancement()
                    .parent(reinforcedArmor)
                    .display(info(RegItems.REINFORCED_NETHERITE_HELMET.get(), "submerge_in_lava", FrameType.CHALLENGE,
                            "This Heat, an Evil Heat",
                            "Submerge in lava with a full reinforced netherite armor")
                    )
                    .addCriterion("in_lava", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.location().setFluid(FluidPredicate.Builder.fluid().of(Fluids.LAVA).build()).build()))
                    .addCriterion("has_all_reinforced_armor", InventoryChangeTrigger.TriggerInstance.hasItems(
                            RegItems.REINFORCED_NETHERITE_HELMET.get(), RegItems.REINFORCED_NETHERITE_CHESTPLATE.get(), RegItems.REINFORCED_NETHERITE_LEGGINGS.get(), RegItems.REINFORCED_NETHERITE_BOOTS.get()
                    ))
                    .save(consumer, "orevolution:nether/submerge_in_lava");

            Advancement primordialAetherrock = Advancement.Builder.advancement()
                    .parent(getAdv("minecraft:end/root"))
                    .display(info(RegBlocks.PRIMITIVE_AETHERROCK.get(), "obtain_primitive_aetherrock", FrameType.TASK,
                            "Found in the Void",
                            "Obtain primitive aetherrock")
                    )
                    .addCriterion("has_primitive_aetherrock", InventoryChangeTrigger.TriggerInstance.hasItems(RegBlocks.PRIMITIVE_AETHERROCK.get()))
                    .save(consumer, "orevolution:end/obtain_primitive_aetherrock");

            Advancement.Builder.advancement()
                    .parent(primordialAetherrock)
                    .display(info(RegItems.AETHERSTEEL_CHESTPLATE.get(), "aethersteel_armor", FrameType.CHALLENGE,
                            "Unstoppable Force",
                            "Obtain a full set of aethersteel armor")
                    )
                    .addCriterion("has_all_aethersteel_armor", InventoryChangeTrigger.TriggerInstance.hasItems(
                            RegItems.AETHERSTEEL_HELMET.get(), RegItems.AETHERSTEEL_CHESTPLATE.get(), RegItems.AETHERSTEEL_LEGGINGS.get(), RegItems.AETHERSTEEL_BOOTS.get()
                    ))
                    .save(consumer, "orevolution:end/aethersteel_armor");

            Advancement.Builder.advancement()
                    .parent(getAdv("minecraft:husbandry/obtain_netherite_hoe"))
                    .display(info(RegItems.AETHERSTEEL_HOE.get(), "aethersteel_hoe", FrameType.CHALLENGE,
                            "Tool Without Reason",
                            "Waste 1 Aethersteel Ingot to upgrade your Netherite Hoe for absolutely no reason at all")
                    )
                    .addCriterion("has_all_aethersteel_armor", InventoryChangeTrigger.TriggerInstance.hasItems(
                            RegItems.AETHERSTEEL_HOE.get()
                    ))
                    .save(consumer, "orevolution:husbandry/aethersteel_hoe");
        }

        protected Advancement getAdv(String loc) {
            return Advancement.Builder.advancement().build(new ResourceLocation(loc));
        }

        protected DisplayInfo info(ItemLike icon, String id, FrameType type, String title, String description) {
            return info(new ItemStack(icon), id, type, title, description);
        }

        protected DisplayInfo info(ItemStack icon, String id, FrameType type, String title, String description) {
            var advancementId = Orevolution.MODID + "." + id;
            lang.addAdvTitle(advancementId, title);
            lang.addAdvDesc(advancementId, description);
            return new DisplayInfo(
                    icon,
                    Component.translatable("advancements.%s.title".formatted(advancementId)),
                    Component.translatable("advancements.%s.description".formatted(advancementId)),
                    null,
                    type,
                    true,
                    true,
                    false
            );
        }
    }
}
