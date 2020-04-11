package com.minerarcana.wizardpunk.item;

import com.minerarcana.wizardpunk.Wizardpunk;
import net.minecraft.item.Item;

public class PamphletItem extends Item {
    public PamphletItem() {
        this(new Properties().group(Wizardpunk.ITEM_GROUP));
    }

    public PamphletItem(Properties properties) {
        super(properties);
    }
}
