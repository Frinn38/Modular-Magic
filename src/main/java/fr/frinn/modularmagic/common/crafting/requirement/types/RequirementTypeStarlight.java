package fr.frinn.modularmagic.common.crafting.requirement.types;

import com.google.gson.JsonObject;
import fr.frinn.modularmagic.common.crafting.requirement.RequirementStarlight;
import fr.frinn.modularmagic.common.integration.jei.ingredient.Starlight;
import fr.frinn.modularmagic.common.utils.RequirementUtils;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import hellfirepvp.modularmachinery.common.machine.IOType;

import javax.annotation.Nullable;

public class RequirementTypeStarlight extends RequirementType<Starlight, RequirementStarlight> {

    @Override
    public ComponentRequirement<Starlight, ? extends RequirementType<Starlight, RequirementStarlight>> createRequirement(IOType type, JsonObject json) {
        float amount = RequirementUtils.getRequiredFloat(json, "amount", ModularMagicRequirements.KEY_REQUIREMENT_STARLIGHT.toString());
        return new RequirementStarlight(type, amount);
    }

    @Nullable
    @Override
    public String requiresModid() {
        return "astralsorcery";
    }
}
