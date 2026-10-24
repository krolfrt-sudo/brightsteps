package com.example.brightsteps.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

class ClothScreen {
    static Screen create(Screen parent) {
        BrightStepsConfig c = BrightStepsConfig.get();
        BrightStepsConfig d = new BrightStepsConfig();

        ConfigBuilder b = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.literal("Bright Steps"))
                .setSavingRunnable(BrightStepsConfig::save);
        ConfigEntryBuilder e = b.entryBuilder();
        ConfigCategory cat = b.getOrCreateCategory(Text.literal("Частицы"));

        cat.addEntry(e.startBooleanToggle(Text.literal("Мод включён"), c.enabled)
                .setDefaultValue(d.enabled).setSaveConsumer(v -> c.enabled = v).build());
        cat.addEntry(e.startBooleanToggle(Text.literal("Только частицы бега"), c.onlyRunningParticles)
                .setTooltip(Text.literal("Выключено — изменятся и частицы ломания блоков"))
                .setDefaultValue(d.onlyRunningParticles).setSaveConsumer(v -> c.onlyRunningParticles = v).build());
        cat.addEntry(e.startFloatField(Text.literal("Размер (x)"), c.sizeMultiplier)
                .setMin(0.1f).setMax(10f)
                .setDefaultValue(d.sizeMultiplier).setSaveConsumer(v -> c.sizeMultiplier = v).build());
        cat.addEntry(e.startFloatField(Text.literal("Яркость (x)"), c.brightness)
                .setMin(0f).setMax(5f)
                .setDefaultValue(d.brightness).setSaveConsumer(v -> c.brightness = v).build());
        cat.addEntry(e.startFloatField(Text.literal("Насыщенность (x)"), c.saturation)
                .setMin(0f).setMax(5f)
                .setDefaultValue(d.saturation).setSaveConsumer(v -> c.saturation = v).build());
        cat.addEntry(e.startBooleanToggle(Text.literal("Свой цвет"), c.useCustomColor)
                .setDefaultValue(d.useCustomColor).setSaveConsumer(v -> c.useCustomColor = v).build());
        cat.addEntry(e.startColorField(Text.literal("Цвет"), c.customColor)
                .setDefaultValue(d.customColor).setSaveConsumer(v -> c.customColor = v & 0xFFFFFF).build());
        cat.addEntry(e.startFloatField(Text.literal("Сила своего цвета (0–1)"), c.customColorStrength)
                .setMin(0f).setMax(1f)
                .setDefaultValue(d.customColorStrength).setSaveConsumer(v -> c.customColorStrength = v).build());

        return b.build();
    }
}
