package fr.frinn.modularmagic.common.tile;

import fr.frinn.modularmagic.ModularMagic;
import fr.frinn.modularmagic.common.tile.machinecomponent.MachineComponentStarlightProviderInput;
import hellfirepvp.astralsorcery.common.constellation.IWeakConstellation;
import hellfirepvp.astralsorcery.common.constellation.distribution.ConstellationSkyHandler;
import hellfirepvp.astralsorcery.common.constellation.distribution.WorldSkyHandler;
import hellfirepvp.astralsorcery.common.starlight.transmission.ITransmissionReceiver;
import hellfirepvp.astralsorcery.common.starlight.transmission.base.SimpleTransmissionReceiver;
import hellfirepvp.astralsorcery.common.starlight.transmission.registry.TransmissionClassRegistry;
import hellfirepvp.astralsorcery.common.tile.base.TileReceiverBase;
import hellfirepvp.astralsorcery.common.util.MiscUtils;
import hellfirepvp.astralsorcery.common.util.SkyCollectionHelper;
import hellfirepvp.modularmachinery.common.data.Config;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.ColorableMachineTile;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileStarlightInput extends TileReceiverBase implements MachineComponentTile, ColorableMachineTile {

    private int starlightAmount = 0;
    private int color = Config.machineColor;

    @Override
    public int getMachineColor() {
        return this.color;
    }

    @Override
    public void setMachineColor(int newColor) {
        this.color = newColor;
        this.markForUpdate();
    }

    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return new MachineComponentStarlightProviderInput(this, IOType.INPUT);
    }

    public int getStarlightStored() {
        return starlightAmount;
    }

    public void setStarlight(int starlight) {
        starlightAmount = starlight;
    }

    @Override
    public void update() {
        super.update();

        if(!world.isRemote) {
            boolean needUpdate = false;

            needUpdate = getPassiveStarlight(needUpdate);

            if(needUpdate)
                this.markDirty();
        }
    }

    public boolean getPassiveStarlight(boolean needUpdate) {
        if(starlightAmount > 0) needUpdate = true;
        starlightAmount *= 0.95;

        WorldSkyHandler handle = ConstellationSkyHandler.getInstance().getWorldHandler(getWorld());
        if(world.canSeeSky(getPos()) && handle != null) {
            int yLevel = getPos().getY();
            if(yLevel > 40) {
                float collect = 160;

                float dstr;
                if(yLevel > 120) {
                    dstr = 1F + ((yLevel - 120) / 272F);
                } else {
                    dstr = (yLevel - 20) / 100F;
                }

                float posDistribution = SkyCollectionHelper.getSkyNoiseDistribution(world, pos);

                collect *= dstr;
                collect *= (0.6 + (0.4 * posDistribution));
                collect *= 0.2 + (0.8 * ConstellationSkyHandler.getInstance().getCurrentDaytimeDistribution(getWorld()));

                starlightAmount =  Math.min(10000, (int) (starlightAmount + collect));
                return true;
            }
        }
        return needUpdate;
    }

    public void receiveStarlight(@Nullable IWeakConstellation type, double amount) {
        if(amount <= 0.001)
            return;

        this.starlightAmount = Math.min(10000, (int)(starlightAmount + (amount * 200.0D)));
        this.markDirty();
    }

    @Nullable
    @Override
    public String getUnLocalizedDisplayName() {
        return "tile.blockstarlightproviderinput.name";
    }

    @Nonnull
    @Override
    public ITransmissionReceiver provideEndpoint(BlockPos at) {
        return new TransmissionReceiverStarlightProvider(at);
    }

    @Override
    public void writeCustomNBT(NBTTagCompound compound) {
        super.writeCustomNBT(compound);

        compound.setInteger("starlight", starlightAmount);
        compound.setInteger("casingColor", color);
    }

    @Override
    public void readCustomNBT(NBTTagCompound compound) {
        super.readCustomNBT(compound);

        starlightAmount = compound.getInteger("starlight");
        color = compound.getInteger("casingColor");
    }

    public static class TransmissionReceiverStarlightProvider extends SimpleTransmissionReceiver {

        public TransmissionReceiverStarlightProvider(BlockPos thisPos) {
            super(thisPos);
        }

        @Override
        public void onStarlightReceive(World world, boolean isChunkLoaded, IWeakConstellation type, double amount) {
            if(isChunkLoaded) {
                TileStarlightInput te = MiscUtils.getTileAt(world, getLocationPos(), TileStarlightInput.class, false);
                if(te != null) {
                    te.receiveStarlight(type, amount);
                }
            }
        }

        @Override
        public TransmissionClassRegistry.TransmissionProvider getProvider() {
            return new TileStarlightInput.StarlightProviderReceiverProvider();
        }

    }

    public static class StarlightProviderReceiverProvider implements TransmissionClassRegistry.TransmissionProvider {

        @Override
        public TileStarlightInput.TransmissionReceiverStarlightProvider provideEmptyNode() {
            return new TileStarlightInput.TransmissionReceiverStarlightProvider(null);
        }

        @Override
        public String getIdentifier() {
            return ModularMagic.MODID + ":TransmissionReceiverStarlightProvider";
        }
    }
}
