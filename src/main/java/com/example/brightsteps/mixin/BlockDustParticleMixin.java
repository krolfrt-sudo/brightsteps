package com.example.brightsteps.mixin;

import com.example.brightsteps.config.BrightStepsConfig;
import net.minecraft.client.particle.BillboardParticle;
import net.minecraft.client.particle.BlockDustParticle;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.world.ClientWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockDustParticle.class)
public abstract class BlockDustParticleMixin extends BillboardParticle {
    // защита от двойного срабатывания: у частицы два конструктора, один может вызывать другой
    @Unique
    private boolean brightsteps$boosted;

    // фиктивный конструктор, нужен только чтобы класс компилировался
    protected BlockDustParticleMixin(ClientWorld world, double x, double y, double z,
                                     double vx, double vy, double vz, Sprite sprite) {
        super(world, x, y, z, vx, vy, vz, sprite);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void brightsteps$boost(CallbackInfo ci) {
        if (this.brightsteps$boosted) return;
        this.brightsteps$boosted = true;

        BrightStepsConfig cfg = BrightStepsConfig.get();
        if (!cfg.enabled) return;
        if (cfg.onlyRunningParticles && !RunningContext.ACTIVE.get()) return;

        // размер
        this.scale *= cfg.sizeMultiplier;

        // насыщенность
        float r = this.red, g = this.green, b = this.blue;
        float gray = (r + g + b) / 3f;
        r = gray + (r - gray) * cfg.saturation;
        g = gray + (g - gray) * cfg.saturation;
        b = gray + (b - gray) * cfg.saturation;

        // свой цвет
        if (cfg.useCustomColor) {
            float cr = ((cfg.customColor >> 16) & 0xFF) / 255f;
            float cg = ((cfg.customColor >> 8) & 0xFF) / 255f;
            float cb = (cfg.customColor & 0xFF) / 255f;
            float s = cfg.customColorStrength;
            r += (cr - r) * s;
            g += (cg - g) * s;
            b += (cb - b) * s;
        }

        // яркость
        this.red = brightsteps$clamp(r * cfg.brightness);
        this.green = brightsteps$clamp(g * cfg.brightness);
        this.blue = brightsteps$clamp(b * cfg.brightness);
    }

    @Unique
    private static float brightsteps$clamp(float v) {
        return Math.max(0f, Math.min(1f, v));
    }
}
