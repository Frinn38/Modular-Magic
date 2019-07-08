package fr.frinn.modularmagic.common.integration.jei.component;

import fr.frinn.modularmagic.common.crafting.requirement.RequirementGrid;
import fr.frinn.modularmagic.common.integration.jei.ingredient.Grid;
import fr.frinn.modularmagic.common.integration.jei.recipelayoutpart.LayoutGrid;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.integration.recipe.RecipeLayoutPart;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class JEIComponentGrid extends ComponentRequirement.JEIComponent<Grid> {

    private RequirementGrid requirementGrid;

    public JEIComponentGrid(RequirementGrid requirementGrid) {
        this.requirementGrid = requirementGrid;
    }

    @Override
    public Class<Grid> getJEIRequirementClass() {
        return Grid.class;
    }

    @Override
    public List<Grid> getJEIIORequirements() {
        List<Grid> list = new ArrayList<>();
        list.add(new Grid(this.requirementGrid.power));
        return list;
    }

    @Override
    public RecipeLayoutPart<Grid> getLayoutPart(Point offset) {
        return new LayoutGrid(offset);
    }

    @Override
    public void onJEIHoverTooltip(int slotIndex, boolean input, Grid ingredient, List<String> tooltip) {

    }
}
