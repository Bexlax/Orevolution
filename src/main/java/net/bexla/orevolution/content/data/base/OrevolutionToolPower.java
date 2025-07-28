package net.bexla.orevolution.content.data.base;

import com.mojang.logging.LogUtils;
import net.bexla.orevolution.content.data.interfaces.OrevolutionConditional;
import net.bexla.orevolution.content.data.interfaces.ToolPower;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.slf4j.Logger;

import java.util.List;

public class OrevolutionToolPower implements ToolPower {
    protected static final Logger LOGGER = LogUtils.getLogger();
    private final String tooltip_id;
    private final OrevolutionConditional conditional;

    public OrevolutionToolPower(String tooltipId, OrevolutionConditional conditional) {
        this.tooltip_id = tooltipId;
        this.conditional = conditional;
    }

    @Override
    public void appendTooltip(ItemStack stack, Level level, List<Component> lines) {
        lines.add(Component.translatable("tooltip.orevolution." + this.tooltip_id).withStyle(ChatFormatting.GREEN));
    }

    @Override
    public String getTooltipID() {
        return this.tooltip_id;
    }

    public boolean getCondition(ItemStack stack, Level level, LivingEntity player, LivingEntity possibleTarget) {
        return conditional.shouldActivate(stack, level, player, possibleTarget);
    }
}
