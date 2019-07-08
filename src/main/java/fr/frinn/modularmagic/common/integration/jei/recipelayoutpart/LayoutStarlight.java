package fr.frinn.modularmagic.common.integration.jei.recipelayoutpart;

import fr.frinn.modularmagic.common.integration.jei.ingredient.Starlight;
import fr.frinn.modularmagic.common.integration.jei.render.StarlightRenderer;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.Minecraft;

import java.awt.*;

public class LayoutStarlight extends RecipeLayoutPart<Starlight> {

    public LayoutStarlight(Point offset) {
        super(offset);
    }

    @Override
    public int getComponentWidth() {
        return 16;
    }

    @Override
    public int getComponentHeight() {
        return 16;
    }

    @Override
    public Class<Starlight> getLayoutTypeClass() {
        return Starlight.class;
    }

    @Override
    public IIngredientRenderer<Starlight> provideIngredientRenderer() {
        return new StarlightRenderer();
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
