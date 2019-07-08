package fr.frinn.modularmagic.common.integration.jei.component;

import com.google.common.collect.Lists;
import fr.frinn.modularmagic.common.crafting.requirement.RequirementConstellation;
import fr.frinn.modularmagic.common.integration.jei.ingredient.Constellation;
import fr.frinn.modularmagic.common.integration.jei.recipelayoutpart.LayoutConstellation;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;

import java.awt.*;
import java.util.List;

public class JEIComponentConstellation extends ComponentRequirement.JEIComponent<Constellation> {

    private RequirementConstellation requirementConstellation;

    public JEIComponentConstellation(RequirementConstellation requirementConstellation) {
        this.requirementConstellation = requirementConstellation;
    }

    @Override
    public Class<Constellation> getJEIRequirementClass() {
        return Constellation.class;
    }

    @Override
    public List<Constellation> getJEIIORequirements() {
        return Lists.newArrayList(new Constellation(requirementConstellation.constellation));
    }

    @Override
    public RecipeLayoutPart<Constellation> getLayoutPart(Point offset) {
        return new LayoutConstellation(offset);
    }

    @Override
    public void onJEIHoverTooltip(int slotIndex, boolean input, Constellation ingredient, List<String> tooltip) {

    }
}
