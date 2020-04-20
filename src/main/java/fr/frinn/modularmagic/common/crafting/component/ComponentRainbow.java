package fr.frinn.modularmagic.common.crafting.component;

import hellfirepvp.modularmachinery.common.crafting.ComponentType;

import javax.annotation.Nullable;

public class ComponentRainbow extends ComponentType {

    @Nullable
    @Override
    public String requiresModid() {
        return "extrautils2";
    }
}
