/*
 * All Rights Reserved (c) 2022 MoriyaShiine
 */

package moriyashiine.realisticfirespread.mixin;

import moriyashiine.realisticfirespread.common.registry.ModEntityComponents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZombieEntity.class)
public abstract class ZombieEntityMixin extends Entity {
	public ZombieEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}

	@Inject(method = "tickMovement", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/ZombieEntity;setOnFireFor(I)V"))
	private void realisticfirespread$setFireFromSun(CallbackInfo ci) {
		if (!world.isClient && !isOnFire()) {
			getComponent(ModEntityComponents.FIRE_FROM_SUN).setFireFromSun(true);
		}
	}
}
