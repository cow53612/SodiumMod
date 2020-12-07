package io.github.cow53612.sodiummod.block;

import io.github.cow53612.sodiummod.SodiumMod;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

public class SodiumBlock extends Block {

    public SodiumBlock(Material materialIn) {
        super(materialIn);

        this.setHardness(1.0f);
        this.setHarvestLevel("pickaxe", 0);
        this.setResistance(1.0f);
        this.setCreativeTab(CreativeTabs.BUILDING_BLOCKS);
        this.setSoundType(SoundType.WOOD);
        this.setRegistryName("sodium_block");
        this.setUnlocalizedName("sodium_block");
    }

    @Override
    @ParametersAreNonnullByDefault
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        ItemStack heldItemStack = playerIn.getHeldItemMainhand();

        if (!worldIn.isRemote) {
            if (heldItemStack.getItem().equals(SodiumMod.ITEMS.ITEM_WATER_WASH_BOTTLE)) {
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 17);
                worldIn.createExplosion(null, pos.getX(), pos.getY(), pos.getZ(), 10, false);

                return true;
            }
        }

        return false;
    }
}
