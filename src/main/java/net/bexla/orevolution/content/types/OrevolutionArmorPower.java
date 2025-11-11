package net.bexla.orevolution.content.types;

import com.mojang.logging.LogUtils;
import net.bexla.orevolution.content.types.interfaces.ArmorPower;
import net.bexla.orevolution.content.types.interfaces.Conditional;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.slf4j.Logger;

import java.util.ArrayList;
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
    public List<Component> appendTooltip(ItemStack stack, Level level, List<Component> lines) {
        Object tooltipval = addTooltipValue();
        List<Component> tips = new ArrayList<>();
        if(tooltipval != null) {
            tips.add(Component.translatable("tooltip.orevolution." + this.tooltip_id, tooltipval).withStyle(ChatFormatting.GREEN));
        }
        else {
            tips.add(Component.translatable("tooltip.orevolution." + this.tooltip_id).withStyle(ChatFormatting.GREEN));
        }
        return tips;
    }

    public Object addTooltipValue() {
        return null;
    }

    public String getTooltipID() {
        return this.tooltip_id;
    }

    public boolean getCondition(ItemStack stack, Level level, LivingEntity player, LivingEntity possibleTarget) {
        return conditional.shouldActivate(stack, null, level, player, possibleTarget);
    }
}
