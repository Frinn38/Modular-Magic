package fr.frinn.modularmagic.common.crafting.requirement.types;

import com.google.gson.JsonObject;
import fr.frinn.modularmagic.common.crafting.requirement.RequirementLifeEssence;
import fr.frinn.modularmagic.common.integration.jei.ingredient.LifeEssence;
import fr.frinn.modularmagic.common.utils.RequirementUtils;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import hellfirepvp.modularmachinery.common.machine.IOType;

import javax.annotation.Nullable;

public class RequirementTypeLifeEssence extends RequirementType<LifeEssence, RequirementLifeEssence> {

    @Override
    public ComponentRequirement<LifeEssence, ? extends RequirementType<LifeEssence, RequirementLifeEssence>> createRequirement(IOType type, JsonObject json) {
        int amount = RequirementUtils.getRequiredInt(json, "amount", ModularMagicRequirements.KEY_REQUIREMENT_LIFE_ESSENCE.toString());
        boolean perTick = RequirementUtils.getOptionalBoolean(json, "perTick", false);
        return new RequirementLifeEssence(type, amount, perTick);
    }

    @Nullable
    @Override
    public String requiresModid() {
        return "bloodmagic";
    }
}
