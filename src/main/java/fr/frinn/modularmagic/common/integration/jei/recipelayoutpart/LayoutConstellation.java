package fr.frinn.modularmagic.common.integration.jei.recipelayoutpart;

import fr.frinn.modularmagic.common.integration.jei.ingredient.Constellation;
import fr.frinn.modularmagic.common.integration.jei.render.ConstellationRenderer;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.Minecraft;

import java.awt.*;

public class LayoutConstellation extends RecipeLayoutPart<Constellation> {

    public LayoutConstellation(Point offset) {
        super(offset);
    }

    @Override
    public int getComponentWidth() {
        return 58;
    }

    @Override
    public int getComponentHeight() {
        return 58;
    }

    @Override
    public Class<Constellation> getLayoutTypeClass() {
        return Constellation.class;
    }

    @Override
    public IIngredientRenderer<Constellation> provideIngredientRenderer() {
        return new ConstellationRenderer();
    }

    @Override
    public int getRendererPaddingX() {
        return 0;
    }

    @Override
    public int getRendererPaddingY() {
        return 0;
    }

    @Override
    public int getMaxHorizontalCount() {
        return 1;
    }

    @Override
    public int getComponentHorizontalGap() {
        return 0;
    }

    @Override
    public int getComponentVerticalGap() {
        return 0;
    }

    @Override
    public int getComponentHorizontalSortingOrder() {
        return 0;
    }

    @Override
    public boolean canBeScaled() {
        return false;
    }

    @Override
    public void drawBackground(Minecraft mc) {

    }
}
