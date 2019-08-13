package fr.frinn.modularmagic.common.item;

import fr.frinn.modularmagic.ModularMagic;
import hellfirepvp.modularmachinery.common.item.ItemDynamicColor;
import net.minecraft.item.Item;

import java.util.ArrayList;

public class ModularMagicItems {

    public static ArrayList<Item> ITEMS = new ArrayList<>();
    public static ArrayList<ItemDynamicColor> COLOR_ITEMS = new ArrayList<>();

    public static void initItems() {

    }

    public static void registerItem(String id, Item item) {
        item.setRegistryName(ModularMagic.MODID, id);
        item.setUnlocalizedName(id);
        ITEMS.add(item);
    }
}
