package io.github.cow53612.sodiummod.item;

import io.github.cow53612.sodiummod.SodiumMod;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;

public class ItemEmptyWashBottle extends Item {

    public ItemEmptyWashBottle() {
        super();

        this.setCreativeTab(CreativeTabs.MISC);
        this.setMaxStackSize(1);
        this.setRegistryName("empty_wash_bottle");
        this.setUnlocalizedName("empty_wash_bottle");
    }

    @Override
    @ParametersAreNonnullByDefault
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ItemStack itemStack = playerIn.getHeldItem(handIn);
        RayTraceResult traceResult = this.rayTrace(worldIn, playerIn, true);
        ActionResult<ItemStack> actionResult = net.minecraftforge.event.ForgeEventFactory.onBucketUse(playerIn, worldIn, itemStack, traceResult);

        if (actionResult != null) {
            return actionResult;
        }

        if (traceResult.typeOfHit != RayTraceResult.Type.BLOCK) {
            return new ActionResult<>(EnumActionResult.PASS, itemStack);
        } else {
            BlockPos pos = traceResult.getBlockPos();

            if (!worldIn.isBlockModifiable(playerIn, pos)) {
                return new ActionResult<>(EnumActionResult.FAIL, itemStack);
            }

            if (!playerIn.canPlayerEdit(pos.offset(traceResult.sideHit), traceResult.sideHit, itemStack)) {
                return new ActionResult<>(EnumActionResult.FAIL, itemStack);
            } else {
                IBlockState blockState = worldIn.getBlockState(pos);
                Material material = blockState.getMaterial();

                if (material == Material.WATER && blockState.getValue(BlockLiquid.LEVEL) == 0) {
                    playerIn.addStat(Objects.requireNonNull(StatList.getObjectUseStats(this)));
                    playerIn.playSound(SoundEvents.ITEM_BUCKET_FILL, 1.0F, 1.0F);
                    return new ActionResult<>(EnumActionResult.SUCCESS, new ItemStack(SodiumMod.ITEMS.ITEM_WATER_WASH_BOTTLE));
                }
            }
        }

        return new ActionResult<>(EnumActionResult.FAIL, itemStack);
    }
}
