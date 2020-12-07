package io.github.cow53612.sodiummod;

import io.github.cow53612.sodiummod.block.SodiumBlock;
import io.github.cow53612.sodiummod.item.ItemEmptyWashBottle;
import io.github.cow53612.sodiummod.item.ItemSodium;
import io.github.cow53612.sodiummod.item.ItemWaterWashBottle;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLConstructionEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Logger;

@Mod(modid = SodiumMod.MODID, name = SodiumMod.NAME, version = SodiumMod.VERSION)
public class SodiumMod {

    public static final String MODID = "sodiummod";
    public static final String NAME = "Sodium Mod";
    public static final String VERSION = "1.0";

    public static Logger logger;

    @Mod.EventHandler
    public void construct(FMLConstructionEvent event) {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        // some example code
        logger.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
    }

    @SubscribeEvent
    protected void registryBlocks(RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(BLOCKS.SODIUM_BLOCK);
    }

    @SubscribeEvent
    protected void registerItems(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(ITEMS.SODIUM_BLOCK);
        event.getRegistry().register(ITEMS.ITEM_SODIUM);
        event.getRegistry().register(ITEMS.ITEM_EMPTY_WASH_BOTTLE);
        event.getRegistry().register(ITEMS.ITEM_WATER_WASH_BOTTLE);
    }

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void registerModels(ModelRegistryEvent event) {
        ModelLoader.setCustomModelResourceLocation(ITEMS.ITEM_SODIUM, 0, new ModelResourceLocation(new ResourceLocation("sodiummod", "sodium"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(BLOCKS.SODIUM_BLOCK), 0, new ModelResourceLocation(new ResourceLocation("sodiummod", "sodium_block"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(ITEMS.ITEM_EMPTY_WASH_BOTTLE, 0, new ModelResourceLocation(new ResourceLocation("sodiummod", "empty_wash_bottle"), "inventory"));
        ModelLoader.setCustomModelResourceLocation(ITEMS.ITEM_WATER_WASH_BOTTLE, 0, new ModelResourceLocation(new ResourceLocation("sodiummod", "water_wash_bottle"), "inventory"));
    }

    @GameRegistry.ObjectHolder(MODID)
    public static class BLOCKS {
        public static final SodiumBlock SODIUM_BLOCK = new SodiumBlock(Material.ROCK);
    }

    @GameRegistry.ObjectHolder(MODID)
    public static class ITEMS {
        public static final ItemBlock SODIUM_BLOCK = (ItemBlock) new ItemBlock(BLOCKS.SODIUM_BLOCK)
                .setRegistryName("sodium_block");
        public static final ItemSodium ITEM_SODIUM = new ItemSodium();
        public static final ItemEmptyWashBottle ITEM_EMPTY_WASH_BOTTLE = new ItemEmptyWashBottle();
        public static final ItemWaterWashBottle ITEM_WATER_WASH_BOTTLE = new ItemWaterWashBottle();
    }

}
