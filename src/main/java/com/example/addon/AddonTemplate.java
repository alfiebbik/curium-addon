package com.example.addon;

import com.example.addon.modules.BetaFreecam;
import com.mojang.logging.LogUtils;
import minecraft.meteorclient.addons.MeteorAddon;
import minecraft.meteorclient.systems.modules.Modules;
import minecraft.meteorclient.systems.modules.categories.Render;
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
        // If you want to make your own custom GUI tab later, you do it here.
        // For now, we are putting Freecam into Meteor's standard RENDER tab.
    }

    @Override
    public String getPackage() {
        return "com.example.addon";
    }
}