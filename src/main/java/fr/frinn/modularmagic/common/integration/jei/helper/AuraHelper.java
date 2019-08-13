package fr.frinn.modularmagic.common.integration.jei.helper;

import fr.frinn.modularmagic.ModularMagic;
import fr.frinn.modularmagic.common.integration.jei.ingredient.Aura;
import mezz.jei.api.ingredients.IIngredientHelper;

import javax.annotation.Nullable;

public class AuraHelper<T extends Aura> implements IIngredientHelper<T> {

    @Nullable
    @Override
    public T getMatch(Iterable<T> ingredients, T ingredientToMatch) {
        for(T ingredient : ingredients) {
            if(ingredient.getType() == ingredientToMatch.getType())
                return ingredient;
        }
        return null;
    }

    @Override
    public String getDisplayName(T ingredient) {
        return ingredient.getType().getName().getResourcePath() + "Aura";
    }

    @Override
    public String getUniqueId(T ingredient) {
        return ingredient.getType().getName().toString();
    }

    @Override
    public String getWildcardId(T ingredient) {
        return ingredient.getType().getName().toString();
    }

    @Override
    public String getModId(T ingredient) {
        return ModularMagic.MODID;
    }

    @Override
    public String getResourceId(T ingredient) {
        return ingredient.getType().getName().getResourcePath();
    }

    @Override
    public T copyIngredient(T ingredient) {
        return ingredient;
    }

    @Override
    public String getErrorInfo(@Nullable T ingredient) {
        return null;
    }
}
