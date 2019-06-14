package fr.frinn.modularmagic.jei.helper;

import fr.frinn.modularmagic.ModularMagic;
import fr.frinn.modularmagic.jei.ingredient.LifeEssence;
import mezz.jei.api.ingredients.IIngredientHelper;

public class LifeEssenceHelper<T extends LifeEssence> implements IIngredientHelper<T> {

    @Override
    public T getMatch(Iterable<T> ingredients, T ingredientToMatch) {
        return null;
    }

    @Override
    public String getDisplayName(T ingredient) {
        return "Life Essence";
    }

    @Override
    public String getUniqueId(T ingredient) {
        return "lifeessence";
    }

    @Override
    public String getWildcardId(T ingredient) {
        return "lifeessence";
    }

    @Override
    public String getModId(T ingredient) {
        return ModularMagic.MODID;
    }

    @Override
    public String getResourceId(T ingredient) {
        return null;
    }

    @Override
    public T copyIngredient(T ingredient) {
        return ingredient;
    }

    @Override
    public String getErrorInfo( T ingredient) {
        return null;
    }
}
