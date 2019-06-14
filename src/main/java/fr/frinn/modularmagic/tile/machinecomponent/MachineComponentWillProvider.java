package fr.frinn.modularmagic.tile.machinecomponent;

import fr.frinn.modularmagic.tile.TileWillProvider;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;

public class MachineComponentWillProvider extends MachineComponent<TileWillProvider> {

    private TileWillProvider willProvider;

    public MachineComponentWillProvider(TileWillProvider willProvider, IOType ioType) {
        super(ioType);
        this.willProvider = willProvider;
    }

    @Override
    public ComponentType getComponentType() {
        return ComponentType.Registry.getComponent("will");
    }

    @Override
    public TileWillProvider getContainerProvider() {
        return willProvider;
    }
}
