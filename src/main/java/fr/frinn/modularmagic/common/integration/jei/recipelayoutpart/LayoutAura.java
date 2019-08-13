package fr.frinn.modularmagic.common.integration.jei.recipelayoutpart;

import fr.frinn.modularmagic.common.integration.jei.ingredient.Aura;
import fr.frinn.modularmagic.common.integration.jei.render.AuraRenderer;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.Minecraft;

import java.awt.*;

public class LayoutAura extends RecipeLayoutPart<Aura> {

    public LayoutAura(Point offset) {
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
    public Class<Aura> getLayoutTypeClass() {
        return Aura.class;
    }

    @Override
    public IIngredientRenderer<Aura> provideIngredientRenderer() {
        return new AuraRenderer();
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
