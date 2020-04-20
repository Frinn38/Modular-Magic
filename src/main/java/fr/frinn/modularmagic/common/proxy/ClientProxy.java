package fr.frinn.modularmagic.common.proxy;

import fr.frinn.modularmagic.ModularMagic;
import fr.frinn.modularmagic.client.Renderer.TileAspectProviderRenderer;
import fr.frinn.modularmagic.common.block.ModularMagicBlocks;
import fr.frinn.modularmagic.common.item.ModularMagicItems;
import fr.frinn.modularmagic.common.tile.TileAspectProvider;
import hellfirepvp.modularmachinery.common.block.BlockDynamicColor;
import hellfirepvp.modularmachinery.common.item.ItemDynamicColor;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.BlockColors;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

    @Override
    public void preInit() {
        super.preInit();

        if(ModularMagic.thaumcraftLoaded) {
            ClientRegistry.bindTileEntitySpecialRenderer(TileAspectProvider.Input.class, new TileAspectProviderRenderer());
            ClientRegistry.bindTileEntitySpecialRenderer(TileAspectProvider.Output.class, new TileAspectProviderRenderer());
        }
    }

    @Override
    public void init() {
        super.init();

        BlockColors blockColors = Minecraft.getMinecraft().getBlockColors();
        ItemColors itemColors = Minecraft.getMinecraft().getItemColors();

        for (BlockDynamicColor block : ModularMagicBlocks.COLOR_BLOCKS) {
            blockColors.registerBlockColorHandler(block::getColorMultiplier,(Block)block);
        }

        for (ItemDynamicColor item : ModularMagicItems.COLOR_ITEMS) {
            itemColors.registerItemColorHandler(item::getColorFromItemstack,(Item)item);
        }
    }

    @Override
    public void postInit() {
        super.postInit();
    }
}
