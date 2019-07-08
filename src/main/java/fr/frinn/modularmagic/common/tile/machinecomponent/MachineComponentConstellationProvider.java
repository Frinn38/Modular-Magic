package fr.frinn.modularmagic.common.tile.machinecomponent;

import fr.frinn.modularmagic.common.tile.TileConstellationProvider;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;

public class MachineComponentConstellationProvider extends MachineComponent<TileConstellationProvider> {

    private TileConstellationProvider provider;

    public MachineComponentConstellationProvider(TileConstellationProvider provider, IOType ioType) {
        super(ioType);
        this.provider = provider;
    }

    @Override
    public ComponentType getComponentType() {
        return ComponentType.Registry.getComponent("constellation");
    }

    @Override
    public TileConstellationProvider getContainerProvider() {
        return this.provider;
    }
}
