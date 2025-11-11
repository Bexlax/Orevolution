package net.bexla.orevolution.content.types;

import com.mojang.logging.LogUtils;
import net.bexla.orevolution.content.types.interfaces.Conditional;
import net.bexla.orevolution.content.types.interfaces.ToolPower;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.slf4j.Logger;

import java.util.ArrayList;
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
    public List<Component> appendTooltip(ItemStack stack, Level level, List<Component> lines) {
        Object tooltipval = addTooltipValue();
        MutableComponent shiftComponent = shiftTooltip();
        List<Component> tips = new ArrayList<>();
        if(tooltipval != null) {
            tips.add(Component.translatable("tooltip.orevolution." + this.tooltip_id, tooltipval).withStyle(ChatFormatting.GREEN));
        }
        else {
            tips.add(Component.translatable("tooltip.orevolution." + this.tooltip_id).withStyle(ChatFormatting.GREEN));
        }

        if(Screen.hasShiftDown() && shiftComponent != null) {
            tips.add(shiftComponent.withStyle(ChatFormatting.DARK_GRAY));
        }
        else if(shiftComponent != null) {
            tips.add(Component.translatable("tooltip.orevolution.press_key", Component.translatable("key.keyboard.left.shift").getString()).withStyle(ChatFormatting.DARK_GRAY));
        }

        return tips;
    }

    public MutableComponent shiftTooltip() {
        return null;
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
