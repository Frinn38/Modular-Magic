package fr.frinn.modularmagic.common.tile.machinecomponent;

import fr.frinn.modularmagic.common.tile.TileLifeEssenceProvider;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;

public class MachineComponentLifeEssenceProvider extends MachineComponent<TileLifeEssenceProvider> {

    private TileLifeEssenceProvider lifeEssenceProvider;

    public MachineComponentLifeEssenceProvider(TileLifeEssenceProvider lifeEssenceProvider, IOType ioType) {
        super(ioType);
        this.lifeEssenceProvider = lifeEssenceProvider;
    }

    @Override
    public ComponentType getComponentType() {
        return ComponentType.Registry.getComponent("lifeessence");
    }

    @Override
    public TileLifeEssenceProvider getContainerProvider() {
        return lifeEssenceProvider;
    }
}
