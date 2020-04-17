package com.minerarcana.wizardpunk.item;

import com.minerarcana.wizardpunk.Wizardpunk;
import net.minecraft.item.Item;

public class DiceItem extends Item {
    private final int sides;

    public DiceItem(int sides) {
        this(sides, new Item.Properties()
                .group(Wizardpunk.ITEM_GROUP));
    }

    public DiceItem(int sides, Properties properties) {
        super(properties);
        this.sides = sides;
    }

    public int getSides() {
        return sides;
    }
}
