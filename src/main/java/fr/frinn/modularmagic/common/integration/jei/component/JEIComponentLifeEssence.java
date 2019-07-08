package fr.frinn.modularmagic.common.integration.jei.component;

import com.google.common.collect.Lists;
import fr.frinn.modularmagic.common.crafting.requirement.RequirementLifeEssence;
import fr.frinn.modularmagic.common.integration.jei.ingredient.LifeEssence;
import fr.frinn.modularmagic.common.integration.jei.recipelayoutpart.LayoutLifeEssence;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;

import java.awt.*;
import java.util.List;

public class JEIComponentLifeEssence extends ComponentRequirement.JEIComponent<LifeEssence> {

    private RequirementLifeEssence requirementLifeEssence;

    public JEIComponentLifeEssence(RequirementLifeEssence requirementLifeEssence) {
        this.requirementLifeEssence = requirementLifeEssence;
    }

    @Override
    public Class<LifeEssence> getJEIRequirementClass() {
        return LifeEssence.class;
    }

    @Override
    public List<LifeEssence> getJEIIORequirements() {
        LifeEssence lifeEssence = new LifeEssence(requirementLifeEssence.essenceAmount, requirementLifeEssence.isPerTick);
        return Lists.newArrayList(lifeEssence);
    }

    @Override
    public RecipeLayoutPart<LifeEssence> getLayoutPart(Point offset) {
        return new LayoutLifeEssence(offset);
    }

    @Override
    public void onJEIHoverTooltip(int slotIndex, boolean input, LifeEssence ingredient, List<String> tooltip) {

    }
}
