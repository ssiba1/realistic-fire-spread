package moriyashiine.realisticfirespread.mixin;

import moriyashiine.realisticfirespread.accessor.IsFireFromSunAccessor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZombieEntity.class)
public abstract class ZombieEntityMixin extends Entity implements IsFireFromSunAccessor {
	public ZombieEntityMixin(EntityType<?> type, World world) {
		super(type, world);
	}
	
	@Inject(method = "tickMovement", at = @At(value = "INVOKE", shift = At.Shift.BEFORE, ordinal = 0, target = "Lnet/minecraft/entity/mob/ZombieEntity;setOnFireFor(I)V"))
	private void tickMovement(CallbackInfo callbackInfo) {
		if (!world.isClient && !isOnFire()) {
			setIsFireFromSun(true);
		}
	}
}
