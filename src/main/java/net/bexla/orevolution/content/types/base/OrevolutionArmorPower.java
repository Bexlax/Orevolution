package net.bexla.orevolution.content.types.base;

import com.mojang.logging.LogUtils;
import net.bexla.orevolution.content.types.base.interfaces.ArmorPower;
import net.bexla.orevolution.content.types.base.interfaces.Conditional;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.slf4j.Logger;

import java.util.List;

public class OrevolutionArmorPower implements ArmorPower {
    protected static final Logger LOGGER = LogUtils.getLogger();
    private final String tooltip_id;
    private final Conditional conditional;

    public OrevolutionArmorPower(String tooltipId, Conditional conditional) {
        this.tooltip_id = tooltipId;
        this.conditional = conditional;
    }

    @Override
    public void appendTooltip(ItemStack stack, Level level, List<Component> lines) {
        lines.add(1, Component.translatable("tooltip.orevolution." + this.tooltip_id).withStyle(ChatFormatting.GREEN));
    }

    public String getTooltipID() {
        return this.tooltip_id + "_armor";
    }

    public boolean getCondition(ItemStack stack, Level level, LivingEntity player, LivingEntity possibleTarget) {
        return conditional.shouldActivate(stack, null, level, player, possibleTarget);
    }
}
