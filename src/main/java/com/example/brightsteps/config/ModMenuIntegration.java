package com.example.brightsteps.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.loader.api.FabricLoader;

/** Загружается только если установлен Mod Menu. */
public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (!FabricLoader.getInstance().isModLoaded("cloth-config")) {
            return parent -> null; // без Cloth Config правьте config/brightsteps.json
        }
        return ClothScreen::create;
    }
}
