package fr.frinn.modularmagic.common.crafting.component;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import fr.frinn.modularmagic.common.crafting.requirement.RequirementLifeEssence;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ComponentLifeEssence extends ComponentType<RequirementLifeEssence> {

    @Nonnull
    @Override
    public String getRegistryName() {
        return "lifeessence";
    }

    @Nullable
    @Override
    public String requiresModid() {
        return "bloodmagic";
    }

    @Nonnull
    @Override
    public RequirementLifeEssence provideComponent(MachineComponent.IOType ioType, JsonObject requirement) {
        if(requirement.has("essenceamount") && requirement.has("essencepertick"))
            throw  new JsonParseException("You can't set \'essenceamount\' and \'essencepertick\' in the same requirement");
        else if(!requirement.has("essenceamount") && !requirement.has("essencepertick"))
            throw new JsonParseException("You need to specify either \'essenceamount\' nor \'essencepertick\'");
        else if(requirement.has("essenceamount"))
            return new RequirementLifeEssence(ioType, requirement.get("essenceamount").getAsInt(), false);
        else if (requirement.has("essencepertick"))
            return new RequirementLifeEssence(ioType, requirement.get("essencepertick").getAsInt(), true);
        else {
            throw new JsonParseException("The ComponentType \'"+getRegistryName()+"\' expects either an \'essenceamount\'-entry that defines the required/produced LP amount" +
                    "nor an \'essencepertick\' entry that defines the required/produced Life Essence amount per Tick");
        }
    }
}
