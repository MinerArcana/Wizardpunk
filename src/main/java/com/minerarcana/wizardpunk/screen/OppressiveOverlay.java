package com.minerarcana.wizardpunk.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.AbstractGui;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static com.minerarcana.wizardpunk.content.WizardpunkBlocks.OPPRESSIVE_ATMOSPHERE;

public class OppressiveOverlay extends AbstractGui {

    private Minecraft mc = Minecraft.getInstance();

    private final int height = mc.getMainWindow().getHeight();
    private final int width = mc.getMainWindow().getWidth();

    public static final OppressiveOverlay INSTANCE = new OppressiveOverlay();

    public void renderOverlay() {
        PlayerEntity player = mc.player;
        World world = mc.world;
        BlockPos pos = player.getPosition();
        if (isPlayerInOppressiveAtmosphere(world, pos)) {
            this.fillGradient(0, 0, this.width, this.height, -1072689136, -804253680);
        }
    }


    private boolean isPlayerInOppressiveAtmosphere(World world, BlockPos pos) {
        return world.getBlockState(new BlockPos(pos.getX(),pos.getY() + 2.5, pos.getZ())).getBlock().equals(OPPRESSIVE_ATMOSPHERE.get());
    }

}
