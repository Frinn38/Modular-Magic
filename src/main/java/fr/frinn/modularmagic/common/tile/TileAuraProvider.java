package fr.frinn.modularmagic.common.tile;

import de.ellpeck.naturesaura.api.aura.chunk.IAuraChunk;
import de.ellpeck.naturesaura.api.aura.type.IAuraType;
import fr.frinn.modularmagic.common.integration.jei.ingredient.Aura;
import fr.frinn.modularmagic.common.tile.machinecomponent.MachineComponentAuraProvider;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nullable;

public class TileAuraProvider extends TileEntity implements MachineComponentTile {

    public void addAura(Aura aura) {
        if(aura.getType() == IAuraChunk.getAuraChunk(world, pos).getType()) {
            IAuraChunk.getAuraChunk(world, pos).storeAura(pos, aura.getAmount() * 100000);
        }
    }

    public void removeAura(Aura aura) {
        if(aura.getType() == IAuraChunk.getAuraChunk(world, pos).getType()) {
            IAuraChunk.getAuraChunk(world, pos).drainAura(pos, aura.getAmount() * 100000);
        }
    }

    public Aura getAura() {
        IAuraType type = IAuraChunk.getAuraChunk(world, pos).getType();
        int amount = IAuraChunk.getAuraInArea(world, pos, 1);
        return new Aura(amount, type);
    }

    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return null;
    }

    public static class Input extends TileAuraProvider {

        @Nullable
        @Override
        public MachineComponent provideComponent() {
            return new MachineComponentAuraProvider(this, IOType.INPUT);
        }
    }

    public static class Output extends TileAuraProvider {

        @Nullable
        @Override
        public MachineComponent provideComponent() {
            return new MachineComponentAuraProvider(this, IOType.OUTPUT);
        }
    }
}
