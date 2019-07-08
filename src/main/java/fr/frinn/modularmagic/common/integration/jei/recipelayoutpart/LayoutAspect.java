package fr.frinn.modularmagic.common.integration.jei.recipelayoutpart;

import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import mezz.jei.plugins.jei.JEIInternalPlugin;
import net.minecraft.client.Minecraft;
import thaumcraft.api.aspects.AspectList;

import java.awt.*;

public class LayoutAspect extends RecipeLayoutPart<AspectList> {

    public LayoutAspect(Point offset) {
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
    public Class<AspectList> getLayoutTypeClass() {
        return AspectList.class;
    }

    @Override
    public IIngredientRenderer<AspectList> provideIngredientRenderer() {
        return JEIInternalPlugin.ingredientRegistry.getIngredientRenderer(new AspectList());
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
        return 4;
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
