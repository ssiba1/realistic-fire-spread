/*
 * Copyright (c) MoriyaShiine. All Rights Reserved.
 */
package moriyashiine.realisticfirespread.mixin;

import moriyashiine.realisticfirespread.common.init.ModEntityComponents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends Entity {
	public PlayerEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(method = "attack", at = @At(value = "INVOKE", ordinal = 1, target = "Lnet/minecraft/entity/Entity;setOnFireFor(I)V"))
	private void realisticfirespread$allowFireSpread(Entity target, CallbackInfo ci) {
		ModEntityComponents.ALLOW_FIRE_SPREAD.get(target).setAllowFireSpread(true);
	}
}
