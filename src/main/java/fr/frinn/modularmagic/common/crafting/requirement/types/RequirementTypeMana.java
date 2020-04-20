package fr.frinn.modularmagic.common.crafting.requirement.types;

import javax.annotation.Nullable;

import com.google.gson.JsonObject;

import fr.frinn.modularmagic.common.crafting.requirement.RequirementMana;
import fr.frinn.modularmagic.common.integration.jei.ingredient.Mana;
import fr.frinn.modularmagic.common.utils.RequirementUtils;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import hellfirepvp.modularmachinery.common.machine.IOType;

public class RequirementTypeMana extends RequirementType<Mana, RequirementMana> {

    @Override
    public ComponentRequirement<Mana, ? extends RequirementType<Mana, RequirementMana>> createRequirement(IOType type, JsonObject json) {
        int amount = RequirementUtils.getRequiredInt(json, "amount", ModularMagicRequirements.KEY_REQUIREMENT_MANA.toString());
        return new RequirementMana(type, amount);
    }

    @Nullable
    @Override
    public String requiresModid() {
        return "botania";
    }
}
