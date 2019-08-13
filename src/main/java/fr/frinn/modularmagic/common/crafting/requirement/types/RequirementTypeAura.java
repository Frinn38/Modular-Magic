package fr.frinn.modularmagic.common.crafting.requirement.types;

import com.google.gson.JsonObject;
import de.ellpeck.naturesaura.api.aura.type.IAuraType;
import fr.frinn.modularmagic.common.crafting.requirement.RequirementAura;
import fr.frinn.modularmagic.common.integration.jei.ingredient.Aura;
import fr.frinn.modularmagic.common.utils.RequirementUtils;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import hellfirepvp.modularmachinery.common.machine.IOType;

import javax.annotation.Nullable;

public class RequirementTypeAura extends RequirementType<Aura, RequirementAura> {

    @Override
    public ComponentRequirement<Aura, ? extends RequirementType<Aura, RequirementAura>> createRequirement(IOType type, JsonObject json) {
        int amount = RequirementUtils.getRequiredInt(json, "amount", ModularMagicRequirements.KEY_REQUIREMENT_AURA.toString());
        IAuraType auraType = RequirementUtils.getAuraType(json, "aura-type", ModularMagicRequirements.KEY_REQUIREMENT_AURA.toString());
        int max = RequirementUtils.getOptionalInt(json, "max-aura", Integer.MAX_VALUE);
        int min = RequirementUtils.getOptionalInt(json, "min-aura", Integer.MIN_VALUE);
        return new RequirementAura(type, new Aura(amount, auraType), max, min);
    }

    @Nullable
    @Override
    public String requiresModid() {
        return "naturesaura";
    }
}
