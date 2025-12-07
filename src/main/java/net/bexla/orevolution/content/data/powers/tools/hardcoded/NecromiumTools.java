package net.bexla.orevolution.content.data.powers.tools.hardcoded;

import net.bexla.orevolution.content.data.Conditionals;
import net.bexla.orevolution.content.types.power.tool.OrevolutionToolPower;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.List;

public class NecromiumTools extends OrevolutionToolPower {
    public NecromiumTools(String tooltipId) {
        super(tooltipId, Conditionals.always());
    }

    @Override
    public List<Component> appendTooltip(ItemStack stack, Level level, List<Component> lines) {
        List<Component> tips = new ArrayList<>();
        tips.add(Component.translatable("tooltip.orevolution." + getTooltipID()).withStyle(ChatFormatting.GREEN));

        if(Screen.hasControlDown()) {
            tips.add(Component.translatable("tooltip.orevolution." + getTooltipID() + "_explanation", MobEffects.MOVEMENT_SPEED.getDisplayName().getString()).withStyle(ChatFormatting.DARK_GRAY));
        }
        else {
            tips.add(Component.translatable("tooltip.orevolution.press_key", Component.translatable("key.keyboard.left.control").getString()).withStyle(ChatFormatting.DARK_GRAY));
        }

        tips.add(Component.literal(""));

        return tips;
    }

    @Override
    public float setDestroySpeed(ItemStack stack, BlockState state, float defaultSpeed) {
        if(!getCBoolean(stack, state, null, null, null)) return defaultSpeed;

        float remaining = stack.getMaxDamage() - stack.getDamageValue();
        float normalized = remaining / (float) stack.getMaxDamage();

        return defaultSpeed * (1.0F + normalized);

    }
}
