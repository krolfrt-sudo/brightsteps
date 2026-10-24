package com.example.brightsteps;

import com.example.brightsteps.config.BrightStepsConfig;
import net.fabricmc.api.ClientModInitializer;

public class BrightStepsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BrightStepsConfig.load();
    }
}
