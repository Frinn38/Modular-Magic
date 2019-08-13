package fr.frinn.modularmagic.common.tile;

import WayofTime.bloodmagic.demonAura.WorldDemonWillHandler;
import WayofTime.bloodmagic.soul.EnumDemonWillType;
import fr.frinn.modularmagic.common.tile.machinecomponent.MachineComponentWillProvider;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import hellfirepvp.modularmachinery.common.tiles.base.TileColorableMachineComponent;

import javax.annotation.Nullable;

public class TileWillProvider extends TileColorableMachineComponent implements MachineComponentTile {

    public double getWill(EnumDemonWillType willType) {
        return WorldDemonWillHandler.getCurrentWill(this.world, this.pos, willType);
    }

    public void addWill(double willValue, EnumDemonWillType willType) {
        WorldDemonWillHandler.fillWill(this.world, this.pos, willType, willValue, true);
    }

    public void removeWill(double willValue, EnumDemonWillType willType) {
        WorldDemonWillHandler.drainWill(this.world, this.pos, willType, willValue, true);
    }

    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return null;
    }

    public static class Input extends TileWillProvider {

        @Nullable
        @Override
        public MachineComponent provideComponent() {
            return new MachineComponentWillProvider(this, IOType.INPUT);
        }
    }

    public static class Output extends TileWillProvider {

        @Nullable
        @Override
        public MachineComponent provideComponent() {
            return new MachineComponentWillProvider(this, IOType.OUTPUT);
        }
    }
}
