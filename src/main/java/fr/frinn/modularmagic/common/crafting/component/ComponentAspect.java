package fr.frinn.modularmagic.common.crafting.component;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import fr.frinn.modularmagic.common.crafting.requirement.RequirementAspect;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import thaumcraft.api.aspects.Aspect;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ComponentAspect extends ComponentType<RequirementAspect> {

    @Nonnull
    @Override
    public String getRegistryName() {
        return "aspect";
    }

    @Nullable
    @Override
    public String requiresModid() {
        return "thaumcraft";
    }

    @Nonnull
    @Override
    public RequirementAspect provideComponent(MachineComponent.IOType ioType, JsonObject requirement) {
        if(requirement.has("amount") && requirement.get("amount").isJsonPrimitive() && requirement.get("amount").getAsJsonPrimitive().isNumber()) {
            if(requirement.has("aspect") && requirement.get("aspect").isJsonPrimitive() && requirement.get("aspect").getAsJsonPrimitive().isString()) {
                int aspectRequired = requirement.getAsJsonPrimitive("amount").getAsInt();
                if(aspectRequired < 0)
                    throw new JsonParseException("\'amount\' can not be less than 0");
                Aspect aspect = Aspect.getAspect(requirement.get("aspect").getAsString());
                if (aspect != null)
                    return new RequirementAspect(ioType, aspectRequired, aspect);
                else
                    throw new JsonParseException("Invalid aspect name : " + requirement.getAsJsonPrimitive("aspect").getAsString());
            }
            else {
                throw new JsonParseException("The ComponentType \'"+getRegistryName()+"\' expects a \'aspect\'-entry that defines the required/produced aspect");
            }
        }
        else {
            throw new JsonParseException("The ComponentType \'"+getRegistryName()+"\' expects a \'amount\'-entry that defines the required/produced aspect amount");
        }
    }
}
