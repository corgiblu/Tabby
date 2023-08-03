package com.corgiblu.tabby.mixin;

import com.corgiblu.tabby.Config;
import com.mojang.authlib.minecraft.client.MinecraftClient;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.PlayerTabOverlay;
import net.minecraft.client.multiplayer.PlayerInfo;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(PlayerTabOverlay.class)
public class TabSizeMixin {
    @Shadow @Final private Minecraft minecraft;

    @ModifyVariable(method = "render", at = @At("STORE"), ordinal = 1)
    private List<PlayerInfo> modifyCount(List<PlayerInfo> list) {
        return list.subList(0, (int) Math.min(list.size(), Config.maxCount.get()));
    }

    @ModifyConstant(constant = @Constant(intValue = 20), method = "render")
    private int modifyMaxRows(int MAX_ROWS) {
        if (Config.adaptive.get()) {
            if (Config.maxCount.get() <= 0) {
                int onlinePlayers = minecraft.player.connection.getOnlinePlayers().size();
                return Math.max(1, onlinePlayers / Config.adaptiveDivisor.get());
            }
            return (int) Math.max(1, Config.maxCount.get() / Config.adaptiveDivisor.get());
        }
        return Math.max(1, Config.maxRows.get());
    }
}
