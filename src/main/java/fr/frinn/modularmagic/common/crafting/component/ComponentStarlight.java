package fr.frinn.modularmagic.common.crafting.component;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import fr.frinn.modularmagic.common.crafting.requirement.RequirementStarlight;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ComponentStarlight extends ComponentType<RequirementStarlight> {

    @Nonnull
    @Override
    public String getRegistryName() {
        return "starlight";
    }

    @Nullable
    @Override
    public String requiresModid() {
        return "astralsorcery";
    }

    @Nonnull
    @Override
    public RequirementStarlight provideComponent(MachineComponent.IOType machineIOType, JsonObject jsonObject) {
        if(jsonObject.has("amount") && jsonObject.get("amount").isJsonPrimitive() && jsonObject.get("amount").getAsJsonPrimitive().isNumber() && jsonObject.get("amount").getAsInt() >= 0) {
            float starlightAmount = jsonObject.get("amount").getAsFloat();
            return new RequirementStarlight(machineIOType, starlightAmount);
        }
        throw new JsonParseException("The \'Starlight\' requirement expects a \'amount\' entry that is a positive number !");
    }
}
