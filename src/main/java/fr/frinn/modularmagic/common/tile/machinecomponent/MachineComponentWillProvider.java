package fr.frinn.modularmagic.common.tile.machinecomponent;

import fr.frinn.modularmagic.common.crafting.component.ModularMagicComponents;
import fr.frinn.modularmagic.common.tile.TileWillProvider;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.lib.RegistriesMM;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;

public class MachineComponentWillProvider extends MachineComponent<TileWillProvider> {

    private TileWillProvider willProvider;

    public MachineComponentWillProvider(TileWillProvider willProvider, IOType ioType) {
        super(ioType);
        this.willProvider = willProvider;
    }

    @Override
    public ComponentType getComponentType() {
        return RegistriesMM.COMPONENT_TYPE_REGISTRY.getValue(ModularMagicComponents.KEY_COMPONENT_WILL);
    }

    @Override
    public TileWillProvider getContainerProvider() {
        return willProvider;
    }
}
