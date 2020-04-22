package com.minerarcana.wizardpunk.event;

import com.minerarcana.wizardpunk.api.WizardpunkAPI;
import com.minerarcana.wizardpunk.content.WizardpunkEntities;
import com.minerarcana.wizardpunk.renderer.EnforcerRenderer;
import com.minerarcana.wizardpunk.renderer.drmgolem.DRMGolemRenderer;
import com.minerarcana.wizardpunk.renderer.layer.MiniCatLayer;
import com.minerarcana.wizardpunk.renderer.warpig.WarPigRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.toasts.IToast;
import net.minecraft.client.gui.toasts.ToastGui;
import net.minecraft.client.renderer.entity.CatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.EntityEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientEventHandler {
    public static void clientSetup(FMLClientSetupEvent event) {
        EntityRendererManager rendererManager = Minecraft.getInstance().getRenderManager();
        rendererManager.register(WizardpunkEntities.DRM_GOLEM.get(), new DRMGolemRenderer(rendererManager));
        rendererManager.register(WizardpunkEntities.WAR_PIG.get(), new WarPigRenderer(rendererManager));
        rendererManager.register(WizardpunkEntities.MINI_CAT.get(), new CatRenderer(rendererManager));
        rendererManager.register(WizardpunkEntities.ENFORCER.get(), new EnforcerRenderer(rendererManager));
        
        event.getMinecraftSupplier().get()
                .getRenderManager()
                .getSkinMap()
                .values()
                .forEach(o -> o.addLayer(new MiniCatLayer<>(o)));
    }
}
