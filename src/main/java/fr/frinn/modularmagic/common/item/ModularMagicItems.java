package fr.frinn.modularmagic.common.item;

import fr.frinn.modularmagic.ModularMagic;
import hellfirepvp.modularmachinery.common.item.ItemDynamicColor;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

public class ModularMagicItems {

    private static ArrayList<Item> ITEMS = new ArrayList<>();
    public static ArrayList<ItemDynamicColor> COLOR_ITEMS = new ArrayList<>();

    public static void initItems() {

    }

    public static void registerItem(String id, Item item) {
        item.setRegistryName(ModularMagic.MODID, id);
        item.setUnlocalizedName(id);
        ITEMS.add(item);
    }

    @SubscribeEvent
    public static void onItemRegister(RegistryEvent.Register<Item> event) {
        for(Item block : ITEMS)
            event.getRegistry().register(block);
    }

    @SubscribeEvent
    public static void registerItemModels(ModelRegistryEvent event) {
        for(Item item : ITEMS) {
            if(item instanceof ItemDynamicColor)
                COLOR_ITEMS.add((ItemDynamicColor) item);

            if(item instanceof ItemBlock)
            {
                ItemBlock itemBlock = (ItemBlock) item;
                Block block = itemBlock.getBlock();
                ResourceLocation resloc = block.getRegistryName();
                ModelLoader.setCustomModelResourceLocation(item,0,new ModelResourceLocation(resloc, "inventory"));
            }
        }
    }
}
