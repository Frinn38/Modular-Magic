package fr.frinn.modularmagic.client.gui;

import fr.frinn.modularmagic.common.container.ContainerLifeEssence;
import fr.frinn.modularmagic.common.tile.TileLifeEssenceProvider;
import hellfirepvp.modularmachinery.ModularMachinery;
import hellfirepvp.modularmachinery.client.gui.GuiContainerBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class GuiContainerLifeEssence extends GuiContainerBase<ContainerLifeEssence> {

    public GuiContainerLifeEssence(TileLifeEssenceProvider tile, EntityPlayer opening) {
        super(new ContainerLifeEssence(tile, opening));
    }

    @Override
    protected void setWidthHeight() {}

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(new ResourceLocation(ModularMachinery.MODID, "textures/gui/inventory_tiny.png"));
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
    }
}
