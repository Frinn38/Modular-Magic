package fr.frinn.modularmagic.common.integration.jei.helper;

import fr.frinn.modularmagic.ModularMagic;
import fr.frinn.modularmagic.common.integration.jei.ingredient.Mana;
import mezz.jei.api.ingredients.IIngredientHelper;

import javax.annotation.Nullable;

public class ManaHelper<T extends Mana> implements IIngredientHelper<Mana> {

    @Nullable
    @Override
    public Mana getMatch(Iterable<Mana> ingredients, Mana ingredientToMatch) {
        return ingredients.iterator().next();
    }

    @Override
    public String getDisplayName(Mana ingredient) {
        return "Mana";
    }

    @Override
    public String getUniqueId(Mana ingredient) {
        return "mana";
    }

    @Override
    public String getWildcardId(Mana ingredient) {
        return "mana";
    }

    @Override
    public String getModId(Mana ingredient) {
        return ModularMagic.MODID;
    }

    @Override
    public String getResourceId(Mana ingredient) {
        return "mana";
    }

    @Override
    public Mana copyIngredient(Mana ingredient) {
        return ingredient;
    }

    @Override
    public String getErrorInfo(@Nullable Mana ingredient) {
        return null;
    }
}
