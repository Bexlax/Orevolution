package net.bexla.orevolution.content.types.base;

import com.mojang.logging.LogUtils;
import net.bexla.orevolution.content.types.base.interfaces.Conditional;
import net.bexla.orevolution.content.types.base.interfaces.ToolPower;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.slf4j.Logger;

import java.util.List;

public class OrevolutionToolPower implements ToolPower {
    protected static final Logger LOGGER = LogUtils.getLogger();
    private final String tooltip_id;
    private final Conditional conditional;

    public OrevolutionToolPower(String tooltipId, Conditional conditional) {
        this.tooltip_id = tooltipId;
        this.conditional = conditional;
    }

    @Override
    public void appendTooltip(ItemStack stack, Level level, List<Component> lines) {
        Object tooltipval = addTooltipValue();
        if(tooltipval != null) {
            lines.add(1, Component.translatable("tooltip.orevolution." + this.tooltip_id, tooltipval).withStyle(ChatFormatting.GREEN));
        }
        else {
            lines.add(1, Component.translatable("tooltip.orevolution." + this.tooltip_id).withStyle(ChatFormatting.GREEN));
        }
    }

    public Object addTooltipValue() {
        return null;
    }

    public String getTooltipID() {
        return this.tooltip_id;
    }

    public boolean getCondition(ItemStack stack, BlockState state, Level level, LivingEntity player, LivingEntity possibleTarget) {
        return conditional.shouldActivate(stack, state, level, player, possibleTarget);
    }
}
