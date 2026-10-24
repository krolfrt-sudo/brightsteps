package com.example.brightsteps.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BrightStepsConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path PATH = FabricLoader.getInstance().getConfigDir().resolve("brightsteps.json");
    private static BrightStepsConfig instance = new BrightStepsConfig();

    /** Мод включён */
    public boolean enabled = true;
    /** true — только частицы бега/ходьбы, false — ещё и частицы ломания блоков */
    public boolean onlyRunningParticles = true;
    /** Множитель размера частиц (1.0 = ваниль) */
    public float sizeMultiplier = 2.5f;
    /** Множитель яркости (1.0 = ваниль) */
    public float brightness = 1.7f;
    /** Множитель насыщенности (1.0 = ваниль, 0 = серые) */
    public float saturation = 1.6f;
    /** Подмешивать свой цвет */
    public boolean useCustomColor = false;
    /** Свой цвет RGB, например 0xFF5500 */
    public int customColor = 0xFF5500;
    /** Сила подмешивания своего цвета, 0..1 */
    public float customColorStrength = 0.6f;

    public static BrightStepsConfig get() { return instance; }

    public static void load() {
        try {
            if (Files.exists(PATH)) {
                BrightStepsConfig loaded = GSON.fromJson(Files.readString(PATH), BrightStepsConfig.class);
                if (loaded != null) instance = loaded;
            }
        } catch (Exception e) {
            System.err.println("[BrightSteps] Не удалось прочитать конфиг, используются значения по умолчанию: " + e);
        }
        save(); // создаёт файл, если его не было
    }

    public static void save() {
        try {
            Files.writeString(PATH, GSON.toJson(instance));
        } catch (IOException e) {
            System.err.println("[BrightSteps] Не удалось сохранить конфиг: " + e);
        }
    }
}
