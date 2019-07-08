package fr.frinn.modularmagic.common.tile.machinecomponent;

import fr.frinn.modularmagic.common.tile.TileStarlightOutput;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;

public class MachineComponentStarlightProviderOutput extends MachineComponent<TileStarlightOutput> {

    private TileStarlightOutput starlightProvider;

    public MachineComponentStarlightProviderOutput(TileStarlightOutput starlightProvider, IOType ioType) {
        super(ioType);
        this.starlightProvider = starlightProvider;
    }

    @Override
    public ComponentType getComponentType() {
        return ComponentType.Registry.getComponent("starlight");
    }

    @Override
    public TileStarlightOutput getContainerProvider() {
        return starlightProvider;
    }
}
