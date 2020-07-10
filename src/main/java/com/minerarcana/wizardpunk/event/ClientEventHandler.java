package com.minerarcana.wizardpunk.event;

import com.minerarcana.wizardpunk.Wizardpunk;
import com.minerarcana.wizardpunk.api.WizardpunkClientAPI;
import com.minerarcana.wizardpunk.content.WizardpunkContainers;
import com.minerarcana.wizardpunk.content.WizardpunkEntities;
import com.minerarcana.wizardpunk.content.WizardpunkMiniGames;
import com.minerarcana.wizardpunk.renderer.drmgolem.DRMGolemRenderer;
import com.minerarcana.wizardpunk.renderer.layer.MiniCatLayer;
import com.minerarcana.wizardpunk.renderer.minigame.NumeramancyRenderer;
import com.minerarcana.wizardpunk.renderer.warpig.WarPigRenderer;
import com.minerarcana.wizardpunk.renderer.zephyrus.ZephyrusRenderer;
import com.minerarcana.wizardpunk.screen.CryptomancyScreen;
import com.minerarcana.wizardpunk.screen.OppressiveOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.entity.CatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.VillagerRenderer;
import net.minecraft.client.renderer.entity.VindicatorRenderer;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import static com.minerarcana.wizardpunk.content.WizardpunkBlocks.OPPRESSIVE_ATMOSPHERE;
@Mod.EventBusSubscriber(modid = Wizardpunk.ID,value = Dist.CLIENT)
public class ClientEventHandler {

    public static void clientSetup(FMLClientSetupEvent event) {
        EntityRendererManager rendererManager = Minecraft.getInstance().getRenderManager();
        rendererManager.register(WizardpunkEntities.DRM_GOLEM.get(), new DRMGolemRenderer(rendererManager));
        rendererManager.register(WizardpunkEntities.WAR_PIG.get(), new WarPigRenderer(rendererManager));
        rendererManager.register(WizardpunkEntities.MINI_CAT.get(), new CatRenderer(rendererManager));
        rendererManager.register(WizardpunkEntities.ENFORCER.get(), new VindicatorRenderer(rendererManager));
        rendererManager.register(WizardpunkEntities.ZEPHYRUS.get(), new ZephyrusRenderer(rendererManager));
        rendererManager.register(WizardpunkEntities.OPPRESSED_VILLAGER.get(), new VillagerRenderer(rendererManager,
                (IReloadableResourceManager) Minecraft.getInstance().getResourceManager()));
        
        event.getMinecraftSupplier().get()
                .getRenderManager()
                .getSkinMap()
                .values()
                .forEach(o -> o.addLayer(new MiniCatLayer<>(o)));

        ScreenManager.registerFactory(WizardpunkContainers.CRYPTOMANCY.get(), CryptomancyScreen::new);

        WizardpunkClientAPI.registerMiniGameRenderer(WizardpunkMiniGames.NUMERAMANCY.get(), new NumeramancyRenderer());
        RenderTypeLookup.setRenderLayer(OPPRESSIVE_ATMOSPHERE.get(), RenderType.getTranslucent());

    }

    @SubscribeEvent
    public static void onRenderHud(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.BOSSHEALTH) {
            OppressiveOverlay.INSTANCE.renderOverlay();
        }
    }
}
