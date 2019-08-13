package fr.frinn.modularmagic.common.crafting.requirement.types;

import com.google.gson.JsonObject;
import fr.frinn.modularmagic.common.crafting.requirement.RequirementGrid;
import fr.frinn.modularmagic.common.integration.jei.ingredient.Grid;
import fr.frinn.modularmagic.common.utils.RequirementUtils;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import hellfirepvp.modularmachinery.common.machine.IOType;

import javax.annotation.Nullable;

public class RequirementTypeGrid extends RequirementType<Grid, RequirementGrid> {

    @Override
    public ComponentRequirement<Grid, ? extends RequirementType<Grid, RequirementGrid>> createRequirement(IOType type, JsonObject json) {
        float power = RequirementUtils.getRequiredPositiveFloat(json, "power", ModularMagicRequirements.KEY_REQUIREMENT_GRID.toString());
        return new RequirementGrid(type, power);
    }

    @Nullable
    @Override
    public String requiresModid() {
        return "extrautils2";
    }
}
