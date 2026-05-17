package com.example.addon.modules;

import meteordevelopment.meteorclient.systems.modules.Category;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.events.world.TickEvent;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.OtherClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.text.Text;

public class BetaFreecam extends Module {
    private OtherClientPlayerEntity fakePlayer;
    private double oldX, oldY, oldZ;
    private final MinecraftClient client = MinecraftClient.getInstance();

    public BetaFreecam(Category category) {
        super(category, "beta-freecam", "Fly out of your body to look around!");
    }

    @Override
    public void onActivate() {
        if (client.player == null || client.world == null) {
            this.toggle();
            return;
        }

        oldX = client.player.getX();
        oldY = client.player.getY();
        oldZ = client.player.getZ();

        fakePlayer = new OtherClientPlayerEntity(client.world, client.player.getGameProfile());
        fakePlayer.copyFrom(client.player);
        fakePlayer.refreshPositionAndAngles(oldX, oldY, oldZ, client.player.getYaw(), client.player.getPitch());
        fakePlayer.resetPosition();
        client.world.addEntity(-1337, fakePlayer);

        if (client.player != null) {
            client.player.sendMessage(Text.literal("[Beta-Freecam] Freecam active. Your body is safe!"), false);
        }
    }

    @EventHandler
    private void onTick(TickEvent.Post event) {
        if (client.player == null) return;

        client.player.getAbilities().flying = true;
        client.player.noClip = true;
    }

    @Override
    public void onDeactivate() {
        if (client.player == null || client.world == null) return;

        if (fakePlayer != null) {
            client.world.removeEntity(-1337, Entity.RemovalReason.DISCARDED);
        }

        client.player.setPosition(oldX, oldY, oldZ);
        client.player.getAbilities().flying = false;
        client.player.noClip = false;
        client.player.setVelocity(0, 0, 0);

        client.player.sendMessage(Text.literal("[Beta-Freecam] Returned to your body."), false);
    }
}