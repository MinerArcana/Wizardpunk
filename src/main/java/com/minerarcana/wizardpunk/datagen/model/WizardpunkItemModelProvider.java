package com.minerarcana.wizardpunk.datagen.model;

import com.minerarcana.wizardpunk.Wizardpunk;
import com.minerarcana.wizardpunk.content.WizardpunkBlocks;
import com.minerarcana.wizardpunk.content.WizardpunkItems;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.function.Supplier;

public class WizardpunkItemModelProvider extends ModelProvider<ItemModelBuilder> {
    public WizardpunkItemModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, Wizardpunk.ID, ITEM_FOLDER, ItemModelBuilder::new, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        this.forItem(WizardpunkItems.NEKOMANTIC_AMULET);
        this.forItem(WizardpunkItems.PAMPHLET);

        this.forBlockItem(WizardpunkBlocks.OPPRESSIVE_EMITTER);
        WizardpunkBlocks.MILITARY_CRATES.values()
                .forEach(this::forBlockItem);
    }

    private void forBlockItem(Supplier<? extends Block> block) {
        Optional.ofNullable(block.get().getRegistryName())
                .ifPresent(resourceLocation -> this.withExistingParent(resourceLocation.getPath(),
                        resourceLocation.getNamespace() + ":" + "block/" + resourceLocation.getPath()));
    }

    private void forItem(RegistryObject<? extends Item> item) {
        this.singleTexture(item.getId().getPath(), mcLoc("item/generated"), "layer0",
                modLoc("item/" + item.getId().getPath()));
    }

    @Override
    @Nonnull
    public String getName() {
        return "Wizardpunk Item Models";
    }
}
