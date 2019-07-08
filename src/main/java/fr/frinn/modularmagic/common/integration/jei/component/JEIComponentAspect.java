package fr.frinn.modularmagic.common.integration.jei.component;

import com.google.common.collect.Lists;
import fr.frinn.modularmagic.common.crafting.requirement.RequirementAspect;
import fr.frinn.modularmagic.common.integration.jei.recipelayoutpart.LayoutAspect;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;
import thaumcraft.api.aspects.AspectList;

import java.awt.*;
import java.util.List;

public class JEIComponentAspect extends ComponentRequirement.JEIComponent<AspectList> {

    private RequirementAspect requirementAspect;

    public JEIComponentAspect(RequirementAspect requirementAspect) {
        this.requirementAspect = requirementAspect;
    }

    @Override
    public Class<AspectList> getJEIRequirementClass() {
        return AspectList.class;
    }

    @Override
    public List<AspectList> getJEIIORequirements() {
        AspectList list = new AspectList();
        list.add(requirementAspect.aspect, requirementAspect.amount);
        return Lists.newArrayList(list);
    }

    @Override
    public RecipeLayoutPart<AspectList> getLayoutPart(Point offset) {
        return new LayoutAspect(offset);
    }

    @Override
    public void onJEIHoverTooltip(int slotIndex, boolean input, AspectList ingredient, List<String> tooltip) {

    }
}
