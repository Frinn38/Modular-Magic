package fr.frinn.modularmagic.component;

import WayofTime.bloodmagic.soul.EnumDemonWillType;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import fr.frinn.modularmagic.component.requirement.RequirementWill;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ComponentWill extends ComponentType<RequirementWill> {

    @Nonnull
    @Override
    public String getRegistryName() {
        return "will";
    }

    @Nullable
    @Override
    public String requiresModid() {
        return "bloodmagic";
    }

    @Nonnull
    @Override
    public RequirementWill provideComponent(MachineComponent.IOType ioType, JsonObject requirement) {
        if(requirement.has("willamount") && requirement.get("willamount").isJsonPrimitive() && requirement.get("willamount").getAsJsonPrimitive().isNumber()) {
            if(requirement.has("willtype") && requirement.get("willtype").isJsonPrimitive() && requirement.get("willtype").getAsJsonPrimitive().isString()) {
                double willRequired = requirement.getAsJsonPrimitive("willamount").getAsDouble();
                EnumDemonWillType willtype = getWillType(requirement.getAsJsonPrimitive("willtype").getAsString());
                if (willtype != null)
                    return new RequirementWill(ioType, willRequired, willtype);
                else
                    throw new JsonParseException("Invalid demon will type : " + requirement.getAsJsonPrimitive("willtype").getAsString());
            }
            else {
                throw new JsonParseException("The ComponentType \'"+getRegistryName()+"\' expects a \'willtype\'-entry that defines the required/produced demon will type");
            }
        }
        else {
            throw new JsonParseException("The ComponentType \'"+getRegistryName()+"\' expects a \'willamount\'-entry that defines the required/produced demon will amount");
        }
    }

    private EnumDemonWillType getWillType(String stringWillType) {
        switch (stringWillType) {
            case "corrosive" : return EnumDemonWillType.CORROSIVE;
            case "destructive" : return EnumDemonWillType.DESTRUCTIVE;
            case "steadfast" : return EnumDemonWillType.STEADFAST;
            case "vengeful" : return EnumDemonWillType.VENGEFUL;
            case "raw" : return EnumDemonWillType.DEFAULT;
            default: return null;
        }
    }
}
