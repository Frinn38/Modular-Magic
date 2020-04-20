package fr.frinn.modularmagic.client.Renderer;

import fr.frinn.modularmagic.common.tile.TileAspectProvider;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.client.lib.UtilsFX;
import thaumcraft.common.config.ModConfig;
import thaumcraft.common.tiles.devices.TileJarBrain;
import thaumcraft.common.tiles.essentia.TileJarFillable;

public class TileAspectProviderRenderer extends TileEntitySpecialRenderer<TileAspectProvider> {

    private static final ResourceLocation TEX_LABEL = new ResourceLocation("thaumcraft", "textures/models/label.png");

    @Override
    public void render(TileAspectProvider tile, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
        super.render(tile, x, y, z, partialTicks, destroyStage, alpha);
        GlStateManager.pushMatrix();
        GlStateManager.disableCull();
        GlStateManager.translate((float) x + 0.5F, (float) y + 0.01F, (float) z + 0.5F);
        GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        if (tile instanceof TileJarFillable) {
            GlStateManager.disableLighting();

            if (((TileJarFillable) tile).aspectFilter != null) {
                GlStateManager.pushMatrix();
                GlStateManager.blendFunc(770, 771);
                switch (((TileJarFillable) tile).facing) {
                    case 3:
                        GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
                        break;
                    case 4:
                        GlStateManager.rotate(270.0F, 0.0F, 1.0F, 0.0F);
                        break;
                    case 5:
                        GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
                }

                float rot = (float) ((((TileJarFillable) tile).aspectFilter.getTag().hashCode() + tile.getPos().getX() + ((TileJarFillable) tile).facing) % 4 - 2);
                GlStateManager.pushMatrix();
                GlStateManager.translate(0.0F, -0.5F, 0.501F);
                if (ModConfig.CONFIG_GRAPHICS.crooked) {
                    GlStateManager.rotate(rot, 0.0F, 0.0F, 1.0F);
                }

                UtilsFX.renderQuadCentered(TEX_LABEL, 0.5F, 1.0F, 1.0F, 1.0F, -99, 771, 1.0F);
                GlStateManager.popMatrix();
                GlStateManager.pushMatrix();
                GlStateManager.translate(0.0F, -0.5F, 0.502F);
                if (ModConfig.CONFIG_GRAPHICS.crooked) {
                    GlStateManager.rotate(rot, 0.0F, 0.0F, 1.0F);
                }

                GlStateManager.scale(0.021D, 0.021D, 0.021D);
                GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
                UtilsFX.drawTag(-8, -8, ((TileJarFillable) tile).aspectFilter);
                GlStateManager.popMatrix();
                GlStateManager.popMatrix();
            }
            GlStateManager.enableLighting();
        }
        GlStateManager.enableCull();
        GlStateManager.popMatrix();
    }
}
