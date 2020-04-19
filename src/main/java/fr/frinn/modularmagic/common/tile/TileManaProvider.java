package fr.frinn.modularmagic.common.tile;

import fr.frinn.modularmagic.common.tile.machinecomponent.MachineComponentManaProvider;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.MathHelper;
import vazkii.botania.api.mana.IManaPool;
import vazkii.botania.api.mana.IManaReceiver;
import vazkii.botania.api.mana.ManaNetworkEvent;
import vazkii.botania.common.core.handler.ManaNetworkHandler;

public abstract class TileManaProvider extends TileColorableMachineComponent implements ITickable, IManaReceiver, MachineComponentTile {

	int mana = 0;

	public TileManaProvider() {
		this.mana = 0;
	}

	@Override
	public void update() {

	}

	@Override
	public void readCustomNBT(NBTTagCompound compound) {
		super.readCustomNBT(compound);
		mana = compound.getInteger("mana");
	}

	@Override
	public void writeCustomNBT(NBTTagCompound compound) {
		super.writeCustomNBT(compound);
		compound.setInteger("mana", mana);
	}

	@Override
	public int getCurrentMana() {
		return mana;
	}

	@Override
	public boolean isFull() {
         return getCurrentMana() >= getManaCapacity();
	}

	@Override
	public void recieveMana(int amount) {
        setCurrentMana(MathHelper.clamp(getCurrentMana() + amount,0,getManaCapacity()));
	}

	public void reduceMana(int amount) {
        setCurrentMana(MathHelper.clamp(getCurrentMana() - amount,0,getManaCapacity()));
	}

	public int getManaCapacity() {
		return 100000;
	}

	public void setCurrentMana(int amount) {
		this.mana = amount;
	}

	@Override
	public boolean canRecieveManaFromBursts() {
		return false;
	}


	public static class Input extends TileManaProvider {

		@Override
		public MachineComponent provideComponent() {
			return new MachineComponentManaProvider(IOType.INPUT, this);
		}

		@Override
		public boolean canRecieveManaFromBursts() {
			return true;
		}
	}

	public static class Output extends TileManaProvider implements IManaPool {

		@Override
		public void update() {
			if(!ManaNetworkHandler.instance.isPoolIn(this) && !isInvalid())
				ManaNetworkEvent.addPool(this);
		}

		@Override
		public void invalidate() {
			super.invalidate();
			ManaNetworkEvent.removePool(this);
		}

		@Override
		public void onChunkUnload() {
			super.onChunkUnload();
			ManaNetworkEvent.removePool(this);
		}

		@Override
		public boolean isOutputtingPower() {
			return true;
		}

		@Override
		public EnumDyeColor getColor() {
			return EnumDyeColor.CYAN;
		}

		@Override
		public void setColor(EnumDyeColor arg0) {
		}

		@Override
		public MachineComponent provideComponent() {
			return new MachineComponentManaProvider(IOType.OUTPUT, this);
		}
	}

}
