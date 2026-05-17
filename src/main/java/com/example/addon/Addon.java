package com.example.addon;

import com.example.addon.modules.BetaFreecam;
import com.mojang.logging.LogUtils;
import minegame.meteorclient.addons.MeteorAddon;
import minegame.meteorclient.systems.modules.Modules;
import minegame.meteorclient.systems.modules.categories.Render;
import org.slf4j.Logger;

public class Addon extends MeteorAddon {
    public static final Logger LOG = LogUtils.getLogger();

    @Override
    public void onInitialize() {
        LOG.info("Initializing My Custom Addon!");

        // Modules
        Modules.get().add(new BetaFreecam(Modules.get().getGroup(Render.class)));
    }

    @Override
    public void onRegisterCategories() {
    }

    @Override
    public String getPackage() {
        return "com.example.addon";
    }
}