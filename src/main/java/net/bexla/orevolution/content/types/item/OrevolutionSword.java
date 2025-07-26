package net.bexla.orevolution.content.types.item;

import net.bexla.orevolution.content.data.base.OrevolutionToolMaterial;
import net.bexla.orevolution.content.data.interfaces.ToolPower;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;

public class OrevolutionSword extends SwordItem {
    private final OrevolutionToolMaterial material;

    public OrevolutionSword(OrevolutionToolMaterial material, int damage, float speed, Properties properties) {
        super(material.getTier(), damage, speed, properties);
        this.material = material;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> lines, TooltipFlag flag) {
        super.appendHoverText(stack, level, lines, flag);
        this.material.getSwordPowers().appendTooltip(stack, level, lines);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        return this.material.getSwordPowers().onUseOverride() || super.mineBlock(stack, level, state, pos, entity);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        this.material.getSwordPowers().onHitEntity(stack, target, attacker);

        return this.material.getSwordPowers().onUseOverride() || super.hurtEnemy(stack, target, attacker);
    }

    public OrevolutionToolMaterial getMaterial() { return this.material; }
}
