package fr.frinn.modularmagic.common.integration.jei.recipelayoutpart;

import fr.frinn.modularmagic.common.integration.jei.ingredient.LifeEssence;
import fr.frinn.modularmagic.common.integration.jei.render.LifeEssenceRenderer;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.Minecraft;

import java.awt.*;

public class LayoutLifeEssence extends RecipeLayoutPart<LifeEssence> {

    public LayoutLifeEssence(Point offset) {
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
    public Class<LifeEssence> getLayoutTypeClass() {
        return LifeEssence.class;
    }

    @Override
    public IIngredientRenderer<LifeEssence> provideIngredientRenderer() {
        return new LifeEssenceRenderer();
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
