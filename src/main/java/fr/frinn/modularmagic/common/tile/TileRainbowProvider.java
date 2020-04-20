package fr.frinn.modularmagic.common.tile;

import com.rwtema.extrautils2.power.PowerManager;
import com.rwtema.extrautils2.tile.TileRainbowGenerator;
import fr.frinn.modularmagic.common.tile.machinecomponent.MachineComponentRainbowProvider;
import hellfirepvp.modularmachinery.common.data.Config;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.ColorableMachineTile;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;
import net.minecraft.block.state.IBlockState;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.Iterator;

public class TileRainbowProvider extends TileColorableMachineComponent implements MachineComponentTile {

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
    public void writeCustomNBT(NBTTagCompound compound) {
        super.writeCustomNBT(compound);

        compound.setInteger("frequency", this.frequency);
    }

    @Override
    public void readCustomNBT(NBTTagCompound compound) {
        super.readCustomNBT(compound);

        this.frequency = compound.getInteger("frequency");
    }
}
