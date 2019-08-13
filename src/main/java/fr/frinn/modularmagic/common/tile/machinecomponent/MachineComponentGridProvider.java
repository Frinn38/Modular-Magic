package fr.frinn.modularmagic.common.tile.machinecomponent;

import fr.frinn.modularmagic.common.crafting.component.ModularMagicComponents;
import fr.frinn.modularmagic.common.tile.TileGridProvider;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.lib.RegistriesMM;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;

public class MachineComponentGridProvider extends MachineComponent<TileGridProvider> {

    private TileGridProvider gridProvider;

    public MachineComponentGridProvider(TileGridProvider gridProvider, IOType ioType) {
        super(ioType);

        this.gridProvider = gridProvider;
    }

    @Override
    public ComponentType getComponentType() {
        return RegistriesMM.COMPONENT_TYPE_REGISTRY.getValue(ModularMagicComponents.KEY_COMPONENT_GRID);
    }

    @Override
    public TileGridProvider getContainerProvider() {
        return this.gridProvider;
    }
}
