package fr.frinn.modularmagic.common.crafting.component;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;

import javax.annotation.Nullable;

public class ComponentWill extends ComponentType {

    @Nullable
    @Override
    public String requiresModid() {
        return "bloodmagic";
    }
}
