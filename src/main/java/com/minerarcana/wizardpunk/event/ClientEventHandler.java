package com.minerarcana.wizardpunk.event;

import com.minerarcana.wizardpunk.content.WizardpunkEntities;
import com.minerarcana.wizardpunk.renderer.EnforcerRenderer;
import com.minerarcana.wizardpunk.renderer.zephyrus.ZephyrusRenderer;
import com.minerarcana.wizardpunk.renderer.drmgolem.DRMGolemRenderer;
import com.minerarcana.wizardpunk.renderer.layer.MiniCatLayer;
import com.minerarcana.wizardpunk.renderer.warpig.WarPigRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.CatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientEventHandler {
    public static void clientSetup(FMLClientSetupEvent event) {
        EntityRendererManager rendererManager = Minecraft.getInstance().getRenderManager();
        rendererManager.register(WizardpunkEntities.DRM_GOLEM.get(), new DRMGolemRenderer(rendererManager));
        rendererManager.register(WizardpunkEntities.WAR_PIG.get(), new WarPigRenderer(rendererManager));
        rendererManager.register(WizardpunkEntities.MINI_CAT.get(), new CatRenderer(rendererManager));
        rendererManager.register(WizardpunkEntities.ENFORCER.get(), new EnforcerRenderer(rendererManager));
        rendererManager.register(WizardpunkEntities.ZEPHYRUS.get(), new ZephyrusRenderer(rendererManager));
        
        event.getMinecraftSupplier().get()
                .getRenderManager()
                .getSkinMap()
                .values()
                .forEach(o -> o.addLayer(new MiniCatLayer<>(o)));
    }
}
