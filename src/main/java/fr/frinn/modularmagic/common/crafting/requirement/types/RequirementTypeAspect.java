package fr.frinn.modularmagic.common.crafting.requirement.types;

import com.google.gson.JsonObject;
import fr.frinn.modularmagic.common.crafting.requirement.RequirementAspect;
import fr.frinn.modularmagic.common.utils.RequirementUtils;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import hellfirepvp.modularmachinery.common.machine.IOType;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import javax.annotation.Nullable;

public class RequirementTypeAspect extends RequirementType<AspectList, RequirementAspect> {

    @Override
    public ComponentRequirement<AspectList, ? extends RequirementType<AspectList, RequirementAspect>> createRequirement(IOType type, JsonObject json) {
        int amount = RequirementUtils.getRequiredInt(json, "amount", ModularMagicRequirements.KEY_REQUIREMENT_ASPECT.toString());
        Aspect aspect = RequirementUtils.getAspect(json, "aspect", ModularMagicRequirements.KEY_REQUIREMENT_ASPECT.toString());
        return new RequirementAspect(type, amount, aspect);
    }

    @Nullable
    @Override
    public String requiresModid() {
        return "thaumcraft";
    }
}
