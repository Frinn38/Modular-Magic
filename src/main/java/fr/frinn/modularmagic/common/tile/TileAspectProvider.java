package fr.frinn.modularmagic.common.tile;

import fr.frinn.modularmagic.common.tile.machinecomponent.MachineComponentAspectProvider;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.IEssentiaTransport;
import thaumcraft.common.tiles.essentia.TileJarFillable;

import javax.annotation.Nullable;

public class TileAspectProvider extends TileJarFillable implements MachineComponentTile {

    private int maxAmount = 250;

    @Override
    public boolean canInputFrom(EnumFacing face) {
        return true;
    }

    @Override
    public boolean canOutputTo(EnumFacing face) {
        return true;
    }

    @Override
    public boolean isConnectable(EnumFacing face) {
        return true;
    }

    public void update() {
        if (!this.world.isRemote && this.amount < this.maxAmount) {
            for(EnumFacing face : EnumFacing.VALUES) {
                this.fillJar(face);
            }
        }

    }

    void fillJar(EnumFacing face) {
        TileEntity te = ThaumcraftApiHelper.getConnectableTile(this.world, this.pos, face);
        if (te != null) {
            IEssentiaTransport ic = (IEssentiaTransport)te;
            if (!ic.canOutputTo(face.getOpposite())) {
                return;
            }

            Aspect ta = null;
            if (this.aspect != null && this.amount > 0) {
                ta = this.aspect;
            } else if (ic.getEssentiaAmount(face.getOpposite()) > 0 && ic.getSuctionAmount(face.getOpposite()) < this.getSuctionAmount(face) && this.getSuctionAmount(face) >= ic.getMinimumSuction()) {
                ta = ic.getEssentiaType(face.getOpposite());
            }

            if (ta != null && ic.getSuctionAmount(face.getOpposite()) < this.getSuctionAmount(face)) {
                this.addToContainer(ta, ic.takeEssentia(ta, 1, face.getOpposite()));
            }
        }

    }

    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return null;
    }

    public static class Input extends TileAspectProvider {

        @Nullable
        @Override
        public MachineComponent provideComponent() {
            return new MachineComponentAspectProvider(this, IOType.INPUT);
        }
    }

    public static class Output extends TileAspectProvider {

        @Nullable
        @Override
        public MachineComponent provideComponent() {
            return new MachineComponentAspectProvider(this, IOType.OUTPUT);
        }
    }
}
