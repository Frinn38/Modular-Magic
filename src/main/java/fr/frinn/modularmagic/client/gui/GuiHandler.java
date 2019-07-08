package fr.frinn.modularmagic.client.gui;

import fr.frinn.modularmagic.common.container.ContainerLifeEssence;
import fr.frinn.modularmagic.common.tile.TileLifeEssenceProvider;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class GuiHandler implements IGuiHandler {

    public static final int GUI_ESSENCE_PROVIDER = 0;
    public static final int GUI_STARLIGHT_PROVIDER = 1;

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GUI_ESSENCE_PROVIDER :
                return new ContainerLifeEssence((TileLifeEssenceProvider) world.getTileEntity(new BlockPos(x, y, z)), player);
        }
        return null;
    }

    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        BlockPos pos = new BlockPos(x, y, z);
        switch (ID) {
            case GUI_ESSENCE_PROVIDER :
                return new GuiContainerLifeEssence((TileLifeEssenceProvider) world.getTileEntity(new BlockPos(x, y, z)), player);
        }
        return null;
    }
}
