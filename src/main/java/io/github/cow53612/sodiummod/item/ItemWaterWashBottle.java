package io.github.cow53612.sodiummod.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ItemWaterWashBottle extends Item {

    public ItemWaterWashBottle() {
        super();

        this.setCreativeTab(CreativeTabs.MISC);
        this.setMaxStackSize(1);
        this.setRegistryName("water_wash_bottle");
        this.setUnlocalizedName("water_wash_bottle");
    }

}
