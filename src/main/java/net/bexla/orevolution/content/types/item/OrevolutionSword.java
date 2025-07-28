package net.bexla.orevolution.content.types.item;

import net.bexla.orevolution.OrevolutionConfig;
import net.bexla.orevolution.content.data.base.OrevolutionToolMaterial;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
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
        if(!OrevolutionConfig.weaponsPowers) return;
        this.material.getSwordPowers().appendTooltip(stack, level, lines);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        if(!OrevolutionConfig.weaponsPowers) return super.mineBlock(stack, level, state, pos, entity);
        return this.material.getSwordPowers().onUseOverride(stack, level, entity) || super.mineBlock(stack, level, state, pos, entity);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if(!OrevolutionConfig.weaponsPowers) return super.hurtEnemy(stack, target, attacker);
        this.material.getSwordPowers().onHitEntity(stack, target, attacker);

        return this.material.getSwordPowers().onUseOverride(stack, attacker.level(), attacker) || super.hurtEnemy(stack, target, attacker);
    }

    public OrevolutionToolMaterial getMaterial() { return this.material; }
}
