package fr.frinn.modularmagic.common.block;

import fr.frinn.modularmagic.ModularMagic;
import fr.frinn.modularmagic.common.item.ModularMagicItems;
import hellfirepvp.modularmachinery.common.block.BlockDynamicColor;
import hellfirepvp.modularmachinery.common.item.ItemBlockMachineComponent;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

import java.util.ArrayList;

public class ModularMagicBlocks {

    public static ArrayList<Block> BLOCKS = new ArrayList<>();
    public static ArrayList<BlockDynamicColor> COLOR_BLOCKS = new ArrayList<>();

    public static void initBlocks() {
        if (ModularMagic.bloodmagicLoaded) {
            BlockWillProviderInput blockWillProviderInput = new BlockWillProviderInput();
            BlockWillProviderOutput blockWillProviderOutput = new BlockWillProviderOutput();
            BlockLifeEssenceProviderInput blockLifeEssenceProviderInput = new BlockLifeEssenceProviderInput();
            BlockLifeEssenceProviderOutput blockLifeEssenceProviderOutput = new BlockLifeEssenceProviderOutput();

            registerBlock("blockwillproviderinput", blockWillProviderInput, new ItemBlockMachineComponent(blockWillProviderInput));
            registerBlock("blockwillprovideroutput", blockWillProviderOutput, new ItemBlockMachineComponent(blockWillProviderOutput));
            registerBlock("blocklifeessenceproviderinput", blockLifeEssenceProviderInput, new ItemBlockMachineComponent(blockLifeEssenceProviderInput));
            registerBlock("blocklifeessenceprovideroutput", blockLifeEssenceProviderOutput, new ItemBlockMachineComponent(blockLifeEssenceProviderOutput));
        }

        if (ModularMagic.thaumcraftLoaded) {
            ThaumcraftBlocks.registerBlocks();
        }

        if (ModularMagic.extraUtils2Loaded) {
            BlockGridProviderInput blockGridProviderInput = new BlockGridProviderInput();
            BlockGridProviderOutput blockGridProviderOutput = new BlockGridProviderOutput();
            BlockRainbowProvider blockRainbowProvider = new BlockRainbowProvider();

            registerBlock("blockgridproviderinput", blockGridProviderInput, new ItemBlockMachineComponent(blockGridProviderInput));
            registerBlock("blockgridprovideroutput", blockGridProviderOutput, new ItemBlockMachineComponent(blockGridProviderOutput));
            registerBlock("blockrainbowprovider", blockRainbowProvider, new ItemBlockMachineComponent(blockRainbowProvider));
        }

        if (ModularMagic.astralLoaded) {
            BlockStarlightProviderInput blockStarlightProviderInput = new BlockStarlightProviderInput();
            BlockStarlightProviderOutput blockStarlightProviderOutput = new BlockStarlightProviderOutput();
            BlockConstellationProvider blockConstellationProvider = new BlockConstellationProvider();

            registerBlock("blockstarlightproviderinput", blockStarlightProviderInput, new ItemBlockMachineComponent(blockStarlightProviderInput));
            registerBlock("blockstarlightprovideroutput", blockStarlightProviderOutput, new ItemBlockMachineComponent(blockStarlightProviderOutput));
            registerBlock("blockconstellationprovider", blockConstellationProvider, new ItemBlockMachineComponent(blockConstellationProvider));
        }

        if (ModularMagic.naturesauraLoaded) {
            BlockAuraProviderInput blockAuraProviderInput = new BlockAuraProviderInput();
            BlockAuraProviderOutput blockAuraProviderOutput = new BlockAuraProviderOutput();

            registerBlock("blockauraproviderinput", blockAuraProviderInput, new ItemBlockMachineComponent(blockAuraProviderInput));
            registerBlock("blockauraprovideroutput", blockAuraProviderOutput, new ItemBlockMachineComponent(blockAuraProviderOutput));
        }
    }

    protected static void registerBlock(String id, Block block, ItemBlock itemBlock) {
        block.setRegistryName(ModularMagic.MODID, id);
        block.setUnlocalizedName(id);
        BLOCKS.add(block);
        ModularMagicItems.registerItem(id, itemBlock);
    }
}
