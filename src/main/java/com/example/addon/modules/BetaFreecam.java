package com.example.addon.modules;

import minegame.meteorclient.systems.modules.Category;
import minegame.meteorclient.systems.modules.Module;
import minegame.meteorclient.events.world.TickEvent;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.entity.Entity;

public class BetaFreecam extends Module {
    private OtherClientPlayerEntity fakePlayer;
    private double oldX, oldY, oldZ;

    public BetaFreecam(Category category) {
        super(category, "beta-freecam", "Fly out of your body to look around!");
    }

    @Override
    public void onActivate() {
        if (mc.player == null || mc.world == null) {
            this.toggle();
            return;
        }

        oldX = mc.player.getX();
        oldY = mc.player.getY();
        oldZ = mc.player.getZ();

        fakePlayer = new OtherClientPlayerEntity(mc.world, mc.player.getGameProfile());
        fakePlayer.copyFrom(mc.player);
        fakePlayer.refreshPositionAndAngles(oldX, oldY, oldZ, mc.player.getYaw(), mc.player.getPitch());
        fakePlayer.resetPosition();
        mc.world.addEntity(-1337, fakePlayer);

        info("Freecam active. Your body is safe!");
    }

    @EventHandler
    private void onTick(TickEvent.Post event) {
        if (mc.player == null) return;

        mc.player.getAbilities().flying = true;
        mc.player.noClip = true;
    }

    @Override
    public void onDeactivate() {
        if (mc.player == null || mc.world == null) return;

        if (fakePlayer != null) {
            mc.world.removeEntity(-1337, Entity.RemovalReason.DISCARDED);
        }

        mc.player.setPosition(oldX, oldY, oldZ);
        mc.player.getAbilities().flying = false;
        mc.player.noClip = false;
        mc.player.setVelocity(0, 0, 0);

        info("Returned to your body.");
    }
}