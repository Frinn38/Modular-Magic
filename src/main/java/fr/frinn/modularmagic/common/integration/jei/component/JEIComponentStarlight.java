package fr.frinn.modularmagic.common.integration.jei.component;

import com.google.common.collect.Lists;
import fr.frinn.modularmagic.common.crafting.requirement.RequirementStarlight;
import fr.frinn.modularmagic.common.integration.jei.ingredient.Starlight;
import fr.frinn.modularmagic.common.integration.jei.recipelayoutpart.LayoutStarlight;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;

import java.awt.*;
import java.util.List;

public class JEIComponentStarlight extends ComponentRequirement.JEIComponent<Starlight> {

    private RequirementStarlight requirementStarlight;

    public JEIComponentStarlight(RequirementStarlight requirementStarlight) {
        this.requirementStarlight = requirementStarlight;
    }

    @Override
    public Class<Starlight> getJEIRequirementClass() {
        return Starlight.class;
    }

    @Override
    public List<Starlight> getJEIIORequirements() {
        return Lists.newArrayList(new Starlight(requirementStarlight.starlightAmount));
    }

    @Override
    public RecipeLayoutPart<Starlight> getLayoutPart(Point offset) {
        return new LayoutStarlight(offset);
    }

    @Override
    public void onJEIHoverTooltip(int slotIndex, boolean input, Starlight ingredient, List<String> tooltip) {

    }
}
