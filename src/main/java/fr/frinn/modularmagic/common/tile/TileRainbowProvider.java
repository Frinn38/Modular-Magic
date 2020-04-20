package fr.frinn.modularmagic.common.tile;

import com.rwtema.extrautils2.power.PowerManager;
import com.rwtema.extrautils2.tile.TileRainbowGenerator;
import fr.frinn.modularmagic.common.tile.machinecomponent.MachineComponentRainbowProvider;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Iterator;

public class TileRainbowProvider extends TileEntity implements MachineComponentTile {

    private int frequency;

    public TileRainbowProvider(int frequency) {
        this.frequency = frequency;
    }

    public boolean rainbow() {
        PowerManager.PowerFreq freq = PowerManager.instance.getPowerFreqRaw(this.frequency);
        if (freq != null) {
            Collection<TileRainbowGenerator> c = freq.getSubTypes(TileRainbowGenerator.rainbowGenerators);
            if (c != null) {
                Iterator i = c.iterator();

                while(i.hasNext()) {
                    TileRainbowGenerator power = (TileRainbowGenerator)i.next();
                    if (power.providing) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return new MachineComponentRainbowProvider(this, IOType.INPUT);
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setInteger("frequency", this.frequency);
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        this.frequency = compound.getInteger("frequency");
        super.readFromNBT(compound);
    }
}
