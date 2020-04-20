package fr.frinn.modularmagic.common.tile.machinecomponent;

import fr.frinn.modularmagic.common.crafting.component.ModularMagicComponents;
import fr.frinn.modularmagic.common.tile.TileManaProvider;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.lib.RegistriesMM;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;

public class MachineComponentManaProvider extends MachineComponent<TileManaProvider> {

	TileManaProvider manaProvider;

	public MachineComponentManaProvider(IOType io, TileManaProvider manaProvider) {
		super(io);
		this.manaProvider = manaProvider;
	}

	@Override
	public ComponentType getComponentType() {
		return RegistriesMM.COMPONENT_TYPE_REGISTRY.getValue(ModularMagicComponents.KEY_COMPONENT_MANA);
	}

	@Override
	public TileManaProvider getContainerProvider() {
		return manaProvider;
	}

}
