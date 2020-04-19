package fr.frinn.modularmagic.common.integration.jei.component;

import java.awt.Point;
import java.util.List;

import com.google.common.collect.Lists;

import fr.frinn.modularmagic.common.crafting.requirement.RequirementMana;
import fr.frinn.modularmagic.common.crafting.requirement.RequirementWill;
import fr.frinn.modularmagic.common.integration.jei.ingredient.DemonWill;
import fr.frinn.modularmagic.common.integration.jei.ingredient.Mana;
import fr.frinn.modularmagic.common.integration.jei.recipelayoutpart.LayoutMana;
import fr.frinn.modularmagic.common.integration.jei.recipelayoutpart.LayoutWill;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;

public class JEIComponentMana extends ComponentRequirement.JEIComponent<Mana> {

    private RequirementMana requirementMana;

    public JEIComponentMana(RequirementMana requirementMana) {
        this.requirementMana = requirementMana;
    }

    @Override
    public Class<Mana> getJEIRequirementClass() {
        return Mana.class;
    }

    @Override
    public List<Mana> getJEIIORequirements() {
        Mana mana = new Mana(requirementMana.manaAmount);
        return Lists.newArrayList(mana);
    }

    @Override
    public RecipeLayoutPart<Mana> getLayoutPart(Point offset) {
        return new LayoutMana(offset);
    }

    @Override
    public void onJEIHoverTooltip(int slotIndex, boolean input, Mana ingredient, List<String> tooltip) {

    }
    
    
}
