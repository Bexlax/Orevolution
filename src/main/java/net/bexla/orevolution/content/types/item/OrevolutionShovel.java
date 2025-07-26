package net.bexla.orevolution.content.types.item;

import net.bexla.orevolution.content.data.base.OrevolutionToolMaterial;
import net.bexla.orevolution.content.data.interfaces.ToolPower;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class OrevolutionShovel extends ShovelItem {
    private final OrevolutionToolMaterial material;

    public OrevolutionShovel(OrevolutionToolMaterial material, float damage, float speed, Properties properties) {
        super(material.getTier(), damage, speed, properties);
        this.material = material;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> lines, TooltipFlag flag) {
        super.appendHoverText(stack, level, lines, flag);

        this.material.getToolPowers().appendTooltip(stack, level, lines);
    }

    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        this.material.getToolPowers().onMineBlock(stack, level, pos, (Player) entity, state);

        return this.material.getToolPowers().onUseOverride() || super.mineBlock(stack, level, state, pos, entity);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return this.material.getToolPowers().onUseOverride() || super.hurtEnemy(stack, target, attacker);
    }

    public OrevolutionToolMaterial getMaterial() { return this.material; }
}
