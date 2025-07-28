package net.bexla.orevolution.content.data.base;

import com.mojang.logging.LogUtils;
import net.bexla.orevolution.content.data.interfaces.ArmorPower;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.slf4j.Logger;

import java.util.List;

public class OrevolutionArmorPower implements ArmorPower {
    protected static final Logger LOGGER = LogUtils.getLogger();
    private final String tooltip_id;

    public OrevolutionArmorPower(String tooltipId) {
        this.tooltip_id = tooltipId;
    }

    @Override
    public void appendTooltip(ItemStack stack, Level level, List<Component> lines) {
        lines.add(Component.translatable("tooltip.orevolution." + this.tooltip_id).withStyle(ChatFormatting.GREEN));
    }

    @Override
    public String getTooltipID() {
        return this.tooltip_id;
    }
}
