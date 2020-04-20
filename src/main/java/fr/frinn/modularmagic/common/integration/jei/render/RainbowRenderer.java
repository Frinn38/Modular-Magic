package fr.frinn.modularmagic.common.integration.jei.render;

import fr.frinn.modularmagic.ModularMagic;
import fr.frinn.modularmagic.common.integration.JeiPlugin;
import fr.frinn.modularmagic.common.integration.jei.ingredient.Rainbow;
import mezz.jei.api.gui.IDrawableBuilder;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class RainbowRenderer implements IIngredientRenderer<Rainbow> {

    @Override
    public void render(Minecraft minecraft, int xPosition, int yPosition, @Nullable Rainbow ingredient) {
        ResourceLocation texture = new ResourceLocation(ModularMagic.MODID, "textures/blocks/overlay_rainbowprovider.png");
        IDrawableBuilder builder = JeiPlugin.GUI_HELPER.drawableBuilder(texture, 0, 0, 16, 16);
        builder.setTextureSize(16, 16);
        builder.build().draw(minecraft, xPosition, yPosition);
    }

    @Override
    public List<String> getTooltip(Minecraft minecraft, Rainbow ingredient, ITooltipFlag tooltipFlag) {
        List<String> tooltips = new ArrayList<>();
        tooltips.add(I18n.format("info.modularmagic.rainbow.required"));
        return tooltips;
    }

    @Override
    public FontRenderer getFontRenderer(Minecraft minecraft, Rainbow ingredient) {
        return minecraft.fontRenderer;
    }
}
