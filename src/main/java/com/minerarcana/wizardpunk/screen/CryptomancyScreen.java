package com.minerarcana.wizardpunk.screen;

import com.hrznstudio.titanium.client.screen.container.BasicContainerScreen;
import com.minerarcana.wizardpunk.Wizardpunk;
import com.minerarcana.wizardpunk.api.WizardpunkClientAPI;
import com.minerarcana.wizardpunk.api.cryptomancy.IMiniGameRenderer;
import com.minerarcana.wizardpunk.api.cryptomancy.IMiniGameScreen;
import com.minerarcana.wizardpunk.api.cryptomancy.MiniGameInstance;
import com.minerarcana.wizardpunk.api.cryptomancy.MiniGameStatus;
import com.minerarcana.wizardpunk.container.cryptomancy.CryptomancyContainer;
import com.minerarcana.wizardpunk.content.WizardpunkMiniGames;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class CryptomancyScreen extends BasicContainerScreen<CryptomancyContainer> implements IMiniGameScreen {
    private final MiniGameInstance miniGameInstance;
    private final IMiniGameRenderer<MiniGameInstance> miniGameRenderer;

    public CryptomancyScreen(CryptomancyContainer screenContainer, PlayerInventory inv, ITextComponent title) {
        super(screenContainer, inv, title);
        miniGameInstance = WizardpunkMiniGames.NUMERAMANCY.get().createInstance();
        miniGameRenderer = WizardpunkClientAPI.getMiniGameRenderer(WizardpunkMiniGames.NUMERAMANCY.get());
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
        miniGameRenderer.drawBackgroundLayer(this, miniGameInstance, partialTicks, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        super.drawGuiContainerForegroundLayer(mouseX, mouseY);
        miniGameRenderer.drawForegroundLayer(this, miniGameInstance, mouseX, mouseY);
    }

    @Override
    public void tick() {
        super.tick();
        if (miniGameInstance.getStatus() == MiniGameStatus.NOT_STARTED) {
            miniGameInstance.setup();
            miniGameInstance.start();
        }

        miniGameInstance.tick();

        if (miniGameInstance.getStatus() == MiniGameStatus.WON) {
            Wizardpunk.instance.networkHandler.sendHackResult(true, this.getContainer().getTargetUniqueId());
        }

        if (miniGameInstance.getStatus() == MiniGameStatus.LOST) {
            Wizardpunk.instance.networkHandler.sendHackResult(false, this.getContainer().getTargetUniqueId());
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scan, int modifiers) {
        return miniGameInstance.keyPressed(keyCode, scan, modifiers) || super.keyPressed(keyCode, scan, modifiers);
    }

    @Override
    public int getTop() {
        return this.guiTop;
    }

    @Override
    public int getLeft() {
        return this.guiLeft;
    }

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public Screen getScreen() {
        return this;
    }
}
