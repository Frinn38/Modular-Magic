package fr.frinn.modularmagic.tile;

import com.rwtema.extrautils2.backend.XUBlock;
import com.rwtema.extrautils2.power.Freq;
import com.rwtema.extrautils2.power.IWorldPowerMultiplier;
import com.rwtema.extrautils2.power.PowerManager;
import com.rwtema.extrautils2.tile.TilePower;
import fr.frinn.modularmagic.tile.machinecomponent.MachineComponentGridProvider;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class TileGridProvider extends TilePower implements IWorldPowerMultiplier, MachineComponentTile {

    private float power = 0.0F;

    @Override
    public float multiplier(@Nullable World world) {
        return 1.0F;
    }

    @Override
    public void onPowerChanged() {
    }

    @Override
    public float getPower() {
        return this.power;
    }

    public PowerManager.PowerFreq getFreq() {
        return PowerManager.instance.getPowerFreq(this.frequency);
    }

    public void setPower(float power) {this.power = power;}

    @Override
    public void onLoad() {
        if(!this.world.isRemote)
            PowerManager.instance.addPowerHandler(this);
    }

    @Override
    public void onChunkUnload() {
        if(!world.isRemote)
            PowerManager.instance.removePowerHandler(this);
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack, XUBlock xuBlock) {
        if(!worldIn.isRemote && placer instanceof EntityPlayerMP) {
            EntityPlayerMP player = (EntityPlayerMP) placer;
            this.frequency = Freq.getBasePlayerFreq(player);
            PowerManager.instance.addPowerHandler(this);
        }
    }

    @Nullable
    @Override
    public MachineComponent provideComponent() {
        return null;
    }

    public static class Input extends TileGridProvider{
        @Override
        public IWorldPowerMultiplier getMultiplier() {
            return this;
        }

        @Nullable
        @Override
        public MachineComponent provideComponent() {
            return new MachineComponentGridProvider(this, MachineComponent.IOType.INPUT);
        }
    }

    public static class Output extends TileGridProvider implements MachineComponentTile {
        @Override
        public IWorldPowerMultiplier getMultiplier() {
            return this;
        }

        @Nullable
        @Override
        public MachineComponent provideComponent() {
            return new MachineComponentGridProvider(this, MachineComponent.IOType.OUTPUT);
        }
    }
}
