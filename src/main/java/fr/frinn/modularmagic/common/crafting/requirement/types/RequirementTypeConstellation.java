package fr.frinn.modularmagic.common.crafting.requirement.types;

import com.google.gson.JsonObject;
import fr.frinn.modularmagic.common.crafting.requirement.RequirementConstellation;
import fr.frinn.modularmagic.common.integration.jei.ingredient.Constellation;
import fr.frinn.modularmagic.common.utils.RequirementUtils;
import hellfirepvp.astralsorcery.common.constellation.IConstellation;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import hellfirepvp.modularmachinery.common.machine.IOType;

import javax.annotation.Nullable;

public class RequirementTypeConstellation extends RequirementType<Constellation, RequirementConstellation> {

    @Override
    public ComponentRequirement<Constellation, ? extends RequirementType<Constellation, RequirementConstellation>> createRequirement(IOType type, JsonObject json) {
        IConstellation constellation = RequirementUtils.getConstellation(json, "constellation", ModularMagicRequirements.KEY_REQUIREMENT_CONSTELLATION.toString());
        return new RequirementConstellation(type, constellation);
    }

    @Nullable
    @Override
    public String requiresModid() {
        return "astralsorcery";
    }
}
