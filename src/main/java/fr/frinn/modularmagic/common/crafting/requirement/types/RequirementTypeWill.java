package fr.frinn.modularmagic.common.crafting.requirement.types;

import WayofTime.bloodmagic.soul.EnumDemonWillType;
import com.google.gson.JsonObject;
import fr.frinn.modularmagic.common.crafting.requirement.RequirementWill;
import fr.frinn.modularmagic.common.integration.jei.ingredient.DemonWill;
import fr.frinn.modularmagic.common.utils.RequirementUtils;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import hellfirepvp.modularmachinery.common.machine.IOType;

import javax.annotation.Nullable;

public class RequirementTypeWill extends RequirementType<DemonWill, RequirementWill> {

    @Override
    public ComponentRequirement<DemonWill, ? extends RequirementType<DemonWill, RequirementWill>> createRequirement(IOType type, JsonObject json) {
        double amount = RequirementUtils.getRequiredDouble(json, "amount", ModularMagicRequirements.KEY_REQUIREMENT_WILL.toString());
        EnumDemonWillType willType = RequirementUtils.getWillType(json, "will-type", ModularMagicRequirements.KEY_REQUIREMENT_WILL.toString());
        double min = RequirementUtils.getOptionalDouble(json, "min", 0.0D);
        double max = RequirementUtils.getOptionalDouble(json, "max", 100.0D);
        return new RequirementWill(type, amount, willType, min, max);
    }

    @Nullable
    @Override
    public String requiresModid() {
        return "bloodmagic";
    }
}
