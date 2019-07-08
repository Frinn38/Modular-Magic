package fr.frinn.modularmagic.common.integration.jei.recipelayoutpart;

import fr.frinn.modularmagic.common.integration.jei.ingredient.DemonWill;
import fr.frinn.modularmagic.common.integration.jei.render.DemonWillRenderer;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.Minecraft;

import java.awt.*;

public class LayoutWill extends RecipeLayoutPart<DemonWill> {

    public LayoutWill(Point offset) {
        super(offset);
    }

    @Override
    public int getComponentWidth() {
        return 18;
    }

    @Override
    public int getComponentHeight() {
        return 18;
    }

    @Override
    public Class<DemonWill> getLayoutTypeClass() {
        return DemonWill.class;
    }

    @Override
    public IIngredientRenderer provideIngredientRenderer() {
        return new DemonWillRenderer();
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
        return 2;
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
        return 10;
    }

    @Override
    public boolean canBeScaled() {
        return false;
    }

    @Override
    public void drawBackground(Minecraft mc) {

    }
}
