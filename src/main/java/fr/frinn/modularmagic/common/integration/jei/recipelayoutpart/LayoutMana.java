package fr.frinn.modularmagic.common.integration.jei.recipelayoutpart;

import java.awt.Point;

import fr.frinn.modularmagic.common.integration.jei.ingredient.Mana;
import fr.frinn.modularmagic.common.integration.jei.render.DemonWillRenderer;
import fr.frinn.modularmagic.common.integration.jei.render.ManaRenderer;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.Minecraft;

public class LayoutMana extends RecipeLayoutPart<Mana> {

    public LayoutMana(Point offset) {
        super(offset);
    }

    @Override
    public int getComponentWidth() {
        return 5;
    }

    @Override
    public int getComponentHeight() {
        return 63;
    }

    @Override
    public Class<Mana> getLayoutTypeClass() {
        return Mana.class;
    }

    @Override
    public IIngredientRenderer provideIngredientRenderer() {
        return new ManaRenderer();
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
        return 4;
    }

    @Override
    public int getComponentVerticalGap() {
        return 4;
    }

    @Override
    public int getComponentHorizontalSortingOrder() {
        return 900;
    }

    @Override
    public boolean canBeScaled() {
        return true;
    }

    @Override
    public void drawBackground(Minecraft mc) {

    }
}
