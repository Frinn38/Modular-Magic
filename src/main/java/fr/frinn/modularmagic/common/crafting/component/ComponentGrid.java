package fr.frinn.modularmagic.common.crafting.component;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import fr.frinn.modularmagic.common.crafting.requirement.RequirementGrid;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ComponentGrid extends ComponentType<RequirementGrid> {

    @Nonnull
    @Override
    public String getRegistryName() {
        return "grid";
    }

    @Nullable
    @Override
    public String requiresModid() {
        return "extrautils2";
    }

    @Nonnull
    @Override
    public RequirementGrid provideComponent(MachineComponent.IOType machineIOType, JsonObject jsonObject) {
        if(jsonObject.has("power") && jsonObject.get("power").isJsonPrimitive() && jsonObject.get("power").getAsJsonPrimitive().isNumber()) {
            float power = jsonObject.get("power").getAsFloat();
            if(power > 0)
                return new RequirementGrid(machineIOType, power);
            else
                throw new JsonParseException("\'power\' can not be 0 or less");
        }
        throw new JsonParseException("Component \'grid\' need a \'power\' entry.");
    }
}
