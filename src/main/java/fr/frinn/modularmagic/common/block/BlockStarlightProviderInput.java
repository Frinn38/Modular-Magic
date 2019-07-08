package fr.frinn.modularmagic.common.block;

import fr.frinn.modularmagic.ModularMagic;
import fr.frinn.modularmagic.common.network.StarlightMessage;
import fr.frinn.modularmagic.common.tile.TileStarlightInput;
import hellfirepvp.modularmachinery.common.CommonProxy;
import hellfirepvp.modularmachinery.common.block.BlockMachineComponent;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockStarlightProviderInput extends BlockMachineComponent {

    public BlockStarlightProviderInput() {
        super(Material.IRON);
        setHardness(2F);
        setResistance(10F);
        setSoundType(SoundType.METAL);
        setHarvestLevel("pickaxe", 1);
        setCreativeTab(CommonProxy.creativeTabModularMachinery);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileStarlightInput();
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return null;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if(worldIn.isRemote) {
            TileEntity te = worldIn.getTileEntity(pos);
            if(te != null && te instanceof TileStarlightInput) {
                ModularMagic.NETWORK.sendToServer(new StarlightMessage(0, te.getPos()));

                Minecraft.getMinecraft().ingameGUI.setOverlayMessage("Starlight : " + ((TileStarlightInput) te).getStarlightStored(), true);
            }
        }
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        if(worldIn.getTileEntity(pos) != null && worldIn.getTileEntity(pos) instanceof TileStarlightInput) {
            TileStarlightInput tile = (TileStarlightInput) worldIn.getTileEntity(pos);
            tile.onBreak();
        }
        super.breakBlock(worldIn, pos, state);
    }
}
