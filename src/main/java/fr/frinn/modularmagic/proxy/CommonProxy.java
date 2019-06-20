package fr.frinn.modularmagic.proxy;

import fr.frinn.modularmagic.ModularMagic;
import fr.frinn.modularmagic.block.ModularMagicBlocks;
import fr.frinn.modularmagic.client.gui.GuiHandler;
import fr.frinn.modularmagic.item.ModularMagicItems;
import fr.frinn.modularmagic.tile.TileAspectProvider;
import fr.frinn.modularmagic.tile.TileGridProvider;
import fr.frinn.modularmagic.tile.TileLifeEssenceProvider;
import fr.frinn.modularmagic.tile.TileWillProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class CommonProxy {

    public void preInit() {
        ModularMagicBlocks.initBlocks();
        ModularMagicItems.initItems();

        if(ModularMagic.bloodmagicLoaded) {
            GameRegistry.registerTileEntity(TileWillProvider.Input.class, new ResourceLocation(ModularMagic.MODID, "tilewillproviderinput"));
            GameRegistry.registerTileEntity(TileWillProvider.Output.class, new ResourceLocation(ModularMagic.MODID, "tilewillprovideroutput"));
            GameRegistry.registerTileEntity(TileLifeEssenceProvider.Input.class, new ResourceLocation(ModularMagic.MODID, "tilelifeessenceproviderinput"));
            GameRegistry.registerTileEntity(TileLifeEssenceProvider.Output.class, new ResourceLocation(ModularMagic.MODID, "tilelifeessenceprovideroutput"));
        }
        if(ModularMagic.thaumcraftLoaded) {
            GameRegistry.registerTileEntity(TileAspectProvider.Input.class, new ResourceLocation(ModularMagic.MODID, "tileaspectproviderinput"));
            GameRegistry.registerTileEntity(TileAspectProvider.Output.class, new ResourceLocation(ModularMagic.MODID, "tileaspectprovideroutput"));
        }
        if(ModularMagic.extraUtils2Loaded) {
            GameRegistry.registerTileEntity(TileGridProvider.Input.class, new ResourceLocation(ModularMagic.MODID, "tilegridproviderinput"));
            GameRegistry.registerTileEntity(TileGridProvider.Output.class, new ResourceLocation(ModularMagic.MODID, "tilegridprovideroutput"));
        }
        NetworkRegistry.INSTANCE.registerGuiHandler(ModularMagic.INSTANCE, new GuiHandler());
    }

    public void init() {

    }

    public void postInit() {

    }
}
