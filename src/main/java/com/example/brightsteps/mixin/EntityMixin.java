package com.example.brightsteps.mixin;

import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Inject(method = "spawnSprintingParticles", at = @At("HEAD"), require = 0)
    private void brightsteps$begin(CallbackInfo ci) {
        RunningContext.ACTIVE.set(true);
    }

    @Inject(method = "spawnSprintingParticles", at = @At("RETURN"), require = 0)
    private void brightsteps$end(CallbackInfo ci) {
        RunningContext.ACTIVE.set(false);
    }
}
