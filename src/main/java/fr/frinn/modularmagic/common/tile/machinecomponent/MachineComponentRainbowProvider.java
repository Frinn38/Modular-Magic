package fr.frinn.modularmagic.common.tile.machinecomponent;

import fr.frinn.modularmagic.common.crafting.component.ModularMagicComponents;
import fr.frinn.modularmagic.common.tile.TileRainbowProvider;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.lib.RegistriesMM;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;

public class MachineComponentRainbowProvider extends MachineComponent<TileRainbowProvider> {

    private TileRainbowProvider rainbowProvider;

    public MachineComponentRainbowProvider(TileRainbowProvider rainbowProvider, IOType ioType) {
        super(ioType);
        this.rainbowProvider = rainbowProvider;
    }

    @Override
    public ComponentType getComponentType() {
        return RegistriesMM.COMPONENT_TYPE_REGISTRY.getValue(ModularMagicComponents.KEY_COMPONENT_RAINBOW);
    }

    @Override
    public TileRainbowProvider getContainerProvider() {
        return this.rainbowProvider;
    }
}
