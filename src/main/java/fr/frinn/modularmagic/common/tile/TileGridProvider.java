package fr.frinn.modularmagic.common.tile;

import com.rwtema.extrautils2.backend.XUBlock;
import com.rwtema.extrautils2.power.Freq;
import com.rwtema.extrautils2.power.IWorldPowerMultiplier;
import com.rwtema.extrautils2.power.PowerManager;
import com.rwtema.extrautils2.tile.TilePower;
import fr.frinn.modularmagic.common.tile.machinecomponent.MachineComponentGridProvider;
import hellfirepvp.modularmachinery.common.data.Config;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.tiles.base.ColorableMachineTile;
import hellfirepvp.modularmachinery.common.tiles.base.MachineComponentTile;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class TileGridProvider extends TilePower implements IWorldPowerMultiplier, MachineComponentTile, ITickable {

    private float power;
    private int tick;
    private int color = Config.machineColor;


    public int getMachineColor() {
        return this.color;
    }


    public void setMachineColor(int newColor) {
        this.color = newColor;
        IBlockState thisState = world.getBlockState(pos);
        world.notifyBlockUpdate(pos, thisState, thisState, 3);
        markDirty();
    }

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

    public void setPower(float power) {
        this.tick = 2;
        this.power = power;
    }

    @Override
    public void update() {
        if(this.tick > 0)
            this.tick--;
        else if(this.power != 0)
            this.power = 0;
    }

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

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);

        if(!compound.hasKey("casingColor")) {
            color = Config.machineColor;
        } else {
            color = compound.getInteger("casingColor");
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound = super.writeToNBT(compound);

        compound.setInteger("casingColor", this.color);

        return compound;
    }

    @Override
    public final SPacketUpdateTileEntity getUpdatePacket() {
        NBTTagCompound compound = new NBTTagCompound();
        super.writeToNBT(compound);
        compound = writeToNBT(compound);
        return new SPacketUpdateTileEntity(getPos(), 255, compound);
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
            return new MachineComponentGridProvider(this, IOType.INPUT);
        }
    }

    public static class Output extends TileGridProvider {
        @Override
        public IWorldPowerMultiplier getMultiplier() {
            return this;
        }

        @Nullable
        @Override
        public MachineComponent provideComponent() {
            return new MachineComponentGridProvider(this, IOType.OUTPUT);
        }
    }
}
