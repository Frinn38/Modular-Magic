package fr.frinn.modularmagic.common.tile.machinecomponent;

import fr.frinn.modularmagic.common.tile.TileStarlightInput;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;

public class MachineComponentStarlightProviderInput extends MachineComponent<TileStarlightInput> {

    private TileStarlightInput starlightProvider;

    public MachineComponentStarlightProviderInput(TileStarlightInput starlightProvider, IOType ioType) {
        super(ioType);
        this.starlightProvider = starlightProvider;
    }

    @Override
    public ComponentType getComponentType() {
        return ComponentType.Registry.getComponent("starlight");
    }

    @Override
    public TileStarlightInput getContainerProvider() {
        return starlightProvider;
    }
}
