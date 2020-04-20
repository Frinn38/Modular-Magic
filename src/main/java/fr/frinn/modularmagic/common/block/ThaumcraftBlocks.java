package fr.frinn.modularmagic.common.block;

import hellfirepvp.modularmachinery.common.item.ItemBlockMachineComponent;

public class ThaumcraftBlocks {

    public static void registerBlocks() {
        BlockAspectProviderInput blockAspectProviderInput = new BlockAspectProviderInput();
        BlockAspectProviderOutput blockAspectProviderOutput = new BlockAspectProviderOutput();

        ModularMagicBlocks.registerBlock("blockaspectproviderinput", blockAspectProviderInput, new ItemBlockMachineComponent(blockAspectProviderInput));
        ModularMagicBlocks.registerBlock("blockaspectprovideroutput", blockAspectProviderOutput, new ItemBlockMachineComponent(blockAspectProviderOutput));
    }
}
