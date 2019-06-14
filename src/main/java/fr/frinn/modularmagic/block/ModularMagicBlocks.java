package fr.frinn.modularmagic.block;

import fr.frinn.modularmagic.ModularMagic;
import fr.frinn.modularmagic.item.ModularMagicItems;
import hellfirepvp.modularmachinery.common.block.BlockDynamicColor;
import hellfirepvp.modularmachinery.common.item.ItemBlockMachineComponent;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.ArrayList;

public class ModularMagicBlocks {

    private static ArrayList<Block> BLOCKS = new ArrayList<Block>();
    public static  ArrayList<BlockDynamicColor> COLOR_BLOCKS = new ArrayList<>();

    public static BlockWillProviderInput blockWillProviderInput = new BlockWillProviderInput();
    public static BlockWillProviderOutput blockWillProviderOutput = new BlockWillProviderOutput();

    public static BlockLifeEssenceProviderInput blockLifeEssenceProviderInput = new BlockLifeEssenceProviderInput();
    public static BlockLifeEssenceProviderOutput blockLifeEssenceProviderOutput = new BlockLifeEssenceProviderOutput();

    public static void initBlocks() {
        if(ModularMagic.bloodmagicLoaded) {
            registerBlock("blockwillproviderinput", blockWillProviderInput, new ItemBlockMachineComponent(blockWillProviderInput));
            registerBlock("blockwillprovideroutput", blockWillProviderOutput, new ItemBlockMachineComponent(blockWillProviderOutput));
            registerBlock("blocklifeessenceproviderinput", blockLifeEssenceProviderInput, new ItemBlockMachineComponent(blockLifeEssenceProviderInput));
            registerBlock("blocklifeessenceprovideroutput", blockLifeEssenceProviderOutput, new ItemBlockMachineComponent(blockLifeEssenceProviderOutput));
        }
    }

    private static void registerBlock(String id, Block block, ItemBlock itemBlock) {
        block.setRegistryName(ModularMagic.MODID, id);
        block.setUnlocalizedName(id);
        BLOCKS.add(block);
        ModularMagicItems.registerItem(id, itemBlock);
    }

    @SubscribeEvent
    public static void onBlockRegister(RegistryEvent.Register<Block> event) {
        for(Block block : BLOCKS)
            event.getRegistry().register(block);
    }

    @SubscribeEvent
    public static void registerBlockModels(ModelRegistryEvent event) {
        BlockColors blockColors = Minecraft.getMinecraft().getBlockColors();
        for(Block block : BLOCKS) {
            if(block instanceof BlockDynamicColor)
                COLOR_BLOCKS.add((BlockDynamicColor)block);
        }

    }
}
