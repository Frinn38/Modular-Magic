package fr.frinn.modularmagic.tile.machinecomponent;

import fr.frinn.modularmagic.tile.TileAspectProvider;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;

public class MachineComponentAspectProvider extends MachineComponent<TileAspectProvider> {

    private TileAspectProvider aspectProvider;

    public MachineComponentAspectProvider(TileAspectProvider aspectProvider, IOType ioType) {
        super(ioType);
        this.aspectProvider = aspectProvider;
    }

    @Override
    public ComponentType getComponentType() {
        return ComponentType.Registry.getComponent("aspect");
    }

    @Override
    public TileAspectProvider getContainerProvider() {
        return aspectProvider;
    }
}
