package com.minerarcana.wizardpunk.renderer.minigame;

import com.minerarcana.wizardpunk.Wizardpunk;
import com.minerarcana.wizardpunk.api.cryptomancy.IMiniGameRenderer;
import com.minerarcana.wizardpunk.api.cryptomancy.IMiniGameScreen;
import com.minerarcana.wizardpunk.api.cryptomancy.MiniGameStatus;
import com.minerarcana.wizardpunk.magic.cryptomancy.numeramancy.NumeramancyMiniGameInstance;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.util.ResourceLocation;

import java.awt.*;

public class NumeramancyRenderer implements IMiniGameRenderer<NumeramancyMiniGameInstance> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Wizardpunk.ID, "textures/minigame/numeramancy.png");

    @Override
    public void drawBackgroundLayer(IMiniGameScreen screen, NumeramancyMiniGameInstance miniGameInstance, float partialTicks,
                                    int mouseX, int mouseY) {
        screen.getScreen().getMinecraft().getTextureManager().bindTexture(TEXTURE);
        screen.getScreen().blit(screen.getLeft() + 20, screen.getTop() + 20, 0, 0, 37, 37);
    }

    @Override
    public void drawForegroundLayer(IMiniGameScreen screen, NumeramancyMiniGameInstance miniGameInstance, int mouseX, int mouseY) {
        if (miniGameInstance.getStatus() != MiniGameStatus.NOT_STARTED) {
            RenderSystem.pushMatrix();
            for (int x = 0; x < miniGameInstance.getGridSize(); x++) {
                for (int y = 0; y < miniGameInstance.getGridSize(); y++) {
                    Color color = this.getBackground(miniGameInstance.valueAt(x, y));
                    AbstractGui.fill(21 + (x * 9), 21 + (y * 9), 21 + (x * 9) + 8, 21 + (y * 9) + 8, color.getRGB());
                }
            }
            RenderSystem.popMatrix();
        }
    }

    public Color getBackground(int value) {
        switch (value) {
            case 2:
                return new Color(0xEEE4DA);
            case 4:
                return new Color(0xede0c8);
            case 8:
                return new Color(0xf2b179);
            case 16:
                return new Color(0xf59563);
            case 32:
                return new Color(0xf67c5f);
            case 64:
                return new Color(0xf65e3b);
            case 128:
                return new Color(0xedcf72);
            case 256:
                return new Color(0xedcc61);
            case 512:
                return new Color(0xedc850);
            case 1024:
                return new Color(0xedc53f);
            case 2048:
                return new Color(0xedc22e);
        }
        return new Color(0xcdc1b4);
    }
}
