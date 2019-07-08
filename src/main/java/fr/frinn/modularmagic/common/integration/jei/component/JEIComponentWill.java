package fr.frinn.modularmagic.common.integration.jei.component;

import com.google.common.collect.Lists;
import fr.frinn.modularmagic.common.crafting.requirement.RequirementWill;
import fr.frinn.modularmagic.common.integration.jei.ingredient.DemonWill;
import fr.frinn.modularmagic.common.integration.jei.recipelayoutpart.LayoutWill;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;

import java.awt.*;
import java.util.List;

public class JEIComponentWill extends ComponentRequirement.JEIComponent<DemonWill> {

    private RequirementWill requirementWill;

    public JEIComponentWill(RequirementWill requirementWill) {
        this.requirementWill = requirementWill;
    }

    @Override
    public Class<DemonWill> getJEIRequirementClass() {
        return DemonWill.class;
    }

    @Override
    public List<DemonWill> getJEIIORequirements() {
        DemonWill demonWill = new DemonWill(requirementWill.willType, requirementWill.willAmount);
        return Lists.newArrayList(demonWill);
    }

    @Override
    public RecipeLayoutPart<DemonWill> getLayoutPart(Point offset) {
        return new LayoutWill(offset);
    }

    @Override
    public void onJEIHoverTooltip(int slotIndex, boolean input, DemonWill ingredient, List<String> tooltip) {

    }
}
