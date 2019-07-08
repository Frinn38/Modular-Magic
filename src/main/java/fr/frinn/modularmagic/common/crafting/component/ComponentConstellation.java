package fr.frinn.modularmagic.common.crafting.component;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import fr.frinn.modularmagic.common.crafting.requirement.RequirementConstellation;
import hellfirepvp.astralsorcery.common.constellation.ConstellationRegistry;
import hellfirepvp.astralsorcery.common.constellation.IConstellation;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ComponentConstellation extends ComponentType<RequirementConstellation> {

    @Nonnull
    @Override
    public String getRegistryName() {
        return "constellation";
    }

    @Nullable
    @Override
    public String requiresModid() {
        return "astralsorcery";
    }

    @Nonnull
    @Override
    public RequirementConstellation provideComponent(MachineComponent.IOType machineIOType, JsonObject jsonObject) {
        if(jsonObject.has("constellation") && jsonObject.get("constellation").isJsonPrimitive() && jsonObject.get("constellation").getAsJsonPrimitive().isString()) {
            IConstellation constellation = ConstellationRegistry.getConstellationByName("astralsorcery.constellation." + jsonObject.get("constellation").getAsString());
            if(constellation != null)
                return new RequirementConstellation(machineIOType, constellation);
            else
                throw new JsonParseException("Constellation name \'" + jsonObject.get("constellation").getAsString() + "\' is invalid !");
        }
        throw new JsonParseException("Component type \'constellation\' need a \'constellation\' string entry !");
    }
}
