package net.bexla.orevolution.content.data.powers.tools;

import net.bexla.orevolution.content.data.Conditionals;
import net.bexla.orevolution.content.data.base.OrevolutionToolPower;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class ToolMultiPower extends OrevolutionToolPower {
    private final List<OrevolutionToolPower> powers;

    public ToolMultiPower(List<OrevolutionToolPower> powers) {
        super("", Conditionals.always());
        this.powers = powers;
    }

    public void onHitEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        for(OrevolutionToolPower p : this.powers) {
            p.onHitEntity(stack, target, attacker);
        }
    }

    public void onMineBlock(ItemStack stack, Level level, BlockPos pos, Player player, BlockState state) {
        for(OrevolutionToolPower p : this.powers) {
            p.onMineBlock(stack, level, pos, player, state);
        }
    }

    public boolean onUseOverride(ItemStack stack, Level level, LivingEntity player) {
        for(OrevolutionToolPower p : this.powers) {
            p.onUseOverride(stack, level, player);
        }
        return false;
    }

    public void onInventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        for(OrevolutionToolPower p : this.powers) {
            p.onInventoryTick(stack, level, entity, slot, selected);
        }
    }

    public void appendTooltip(ItemStack stack, Level level, List<Component> lines) {
        for(OrevolutionToolPower p : this.powers) {
            p.appendTooltip(stack, level, lines);
        }
    }
}
