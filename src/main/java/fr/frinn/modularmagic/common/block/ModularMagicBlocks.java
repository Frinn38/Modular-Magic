package fr.frinn.modularmagic.common.block;

import fr.frinn.modularmagic.ModularMagic;
import fr.frinn.modularmagic.common.item.ModularMagicItems;
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

    public static void initBlocks() {
        if(ModularMagic.bloodmagicLoaded) {
            BlockWillProviderInput blockWillProviderInput = new BlockWillProviderInput();
            BlockWillProviderOutput blockWillProviderOutput = new BlockWillProviderOutput();
            BlockLifeEssenceProviderInput blockLifeEssenceProviderInput = new BlockLifeEssenceProviderInput();
            BlockLifeEssenceProviderOutput blockLifeEssenceProviderOutput = new BlockLifeEssenceProviderOutput();

            registerBlock("blockwillproviderinput", blockWillProviderInput, new ItemBlockMachineComponent(blockWillProviderInput));
            registerBlock("blockwillprovideroutput", blockWillProviderOutput, new ItemBlockMachineComponent(blockWillProviderOutput));
            registerBlock("blocklifeessenceproviderinput", blockLifeEssenceProviderInput, new ItemBlockMachineComponent(blockLifeEssenceProviderInput));
            registerBlock("blocklifeessenceprovideroutput", blockLifeEssenceProviderOutput, new ItemBlockMachineComponent(blockLifeEssenceProviderOutput));
        }

        if(ModularMagic.thaumcraftLoaded) {
            BlockAspectProviderInput blockAspectProviderInput = new BlockAspectProviderInput();
            BlockAspectProviderOutput blockAspectProviderOutput = new BlockAspectProviderOutput();

            registerBlock("blockaspectproviderinput", blockAspectProviderInput, new ItemBlockMachineComponent(blockAspectProviderInput));
            registerBlock("blockaspectprovideroutput", blockAspectProviderOutput, new ItemBlockMachineComponent(blockAspectProviderOutput));
        }

        if(ModularMagic.extraUtils2Loaded) {
            BlockGridProviderInput blockGridProviderInput = new BlockGridProviderInput();
            BlockGridProviderOutput blockGridProviderOutput = new BlockGridProviderOutput();

            registerBlock("blockgridproviderinput", blockGridProviderInput, new ItemBlockMachineComponent(blockGridProviderInput));
            registerBlock("blockgridprovideroutput", blockGridProviderOutput, new ItemBlockMachineComponent(blockGridProviderOutput));
        }

        if(ModularMagic.astralLoaded) {
            BlockStarlightProviderInput blockStarlightProviderInput = new BlockStarlightProviderInput();
            BlockStarlightProviderOutput blockStarlightProviderOutput = new BlockStarlightProviderOutput();
            BlockConstellationProvider blockConstellationProvider = new BlockConstellationProvider();

            registerBlock("blockstarlightproviderinput", blockStarlightProviderInput, new ItemBlockMachineComponent(blockStarlightProviderInput));
            registerBlock("blockstarlightprovideroutput", blockStarlightProviderOutput, new ItemBlockMachineComponent(blockStarlightProviderOutput));
            registerBlock("blockconstellationprovider", blockConstellationProvider, new ItemBlockMachineComponent(blockConstellationProvider));
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
