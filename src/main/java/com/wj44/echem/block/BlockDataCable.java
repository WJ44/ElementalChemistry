package com.wj44.echem.block;

import com.wj44.echem.reference.Names;
import com.wj44.echem.tileentity.TileEntityDataCable;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * Created by Wesley "WJ44" Joosten on 17-4-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class BlockDataCable extends BlockEChem implements ITileEntityProvider
{
    public static final PropertyBool NORTH = PropertyBool.create("north");
    public static final PropertyBool EAST = PropertyBool.create("east");
    public static final PropertyBool SOUTH = PropertyBool.create("south");
    public static final PropertyBool WEST = PropertyBool.create("west");
    public static final PropertyBool UP = PropertyBool.create("up");
    public static final PropertyBool DOWN = PropertyBool.create("down");

    public BlockDataCable()
    {
        setUnlocalizedName(Names.Blocks.DATA_CABLE);
        this.setDefaultState(this.blockState.getBaseState().withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, false).withProperty(UP, false).withProperty(DOWN, false));
    }

    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta)
    {
        return new TileEntityDataCable();
    }

    /**
     * Add all collision boxes of this Block to the list that intersect with the given mask.
     *
     * @param collidingEntity the Entity colliding with this Block
     */
    public void addCollisionBoxesToList(World worldIn, BlockPos pos, IBlockState state, AxisAlignedBB mask, List list, Entity collidingEntity)
    {
        boolean north = canConnectTo(worldIn, pos.north());
        boolean east = canConnectTo(worldIn, pos.east());
        boolean south = canConnectTo(worldIn, pos.south());
        boolean west  = canConnectTo(worldIn, pos.west());
        boolean up = canConnectTo(worldIn, pos.up());
        boolean down = canConnectTo(worldIn, pos.down());

        float minX = 0.4F;
        float minY = 0.25F;
        float minZ = 0.4F;
        float maxX = 0.6F;
        float maxY = 0.75F;
        float maxZ = 0.6F;

        this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
        super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);

        if (north)
        {
            this.setBlockBounds(minX, minY, 0, maxX, maxY, 0.25F);
            super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
        }
        if (east)
        {
            this.setBlockBounds(0.75F, minY, minZ, 1, maxY, maxZ);
            super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
        }
        if (south)
        {
            this.setBlockBounds(minX, minY, 0.75F, maxX, maxY, 1);
            super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
        }
        if (west)
        {
            this.setBlockBounds(0, minY, minZ, 0.25F, maxY, maxZ);
            super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
        }
        if (up)
        {
            this.setBlockBounds(minX, 0.75F, minZ, maxX, 1, maxZ);
            super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
        }
        if (down)
        {
            this.setBlockBounds(minX, 0, minZ, maxX, 0.25F, maxZ);
            super.addCollisionBoxesToList(worldIn, pos, state, mask, list, collidingEntity);
        }
    }


    public void setBlockBoundsBasedOnState(IBlockAccess worldIn, BlockPos pos)
    {
        boolean north = canConnectTo(worldIn, pos.north());
        boolean east = canConnectTo(worldIn, pos.east());
        boolean south = canConnectTo(worldIn, pos.south());
        boolean west  = canConnectTo(worldIn, pos.west());
        boolean up = canConnectTo(worldIn, pos.up());
        boolean down = canConnectTo(worldIn, pos.down());

        float minX = 0.25F;
        float minY = 0.25F;
        float minZ = 0.25F;
        float maxX = 0.75F;
        float maxY = 0.75F;
        float maxZ = 0.75F;

        if (north)
        {
            minZ = 0;
        }
        if (east)
        {
            maxX = 1;
        }
        if (south)
        {
            maxZ = 1;
        }
        if (west)
        {
            minX = 0;
        }
        if (up)
        {
            maxY = 1;
        }
        if (down)
        {
            maxY = 1;
        }
        this.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
    }

    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean isFullCube()
    {
        return false;
    }

    public boolean isPassable(IBlockAccess worldIn, BlockPos pos)
    {
        return false;
    }

    public boolean canConnectTo(IBlockAccess worldIn, BlockPos pos)
    {
        Block block = worldIn.getBlockState(pos).getBlock();
        return (block instanceof BlockDataCable || block instanceof BlockDataBank || block instanceof BlockElementMachine);
    }

    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess worldIn, BlockPos pos, EnumFacing side)
    {
        return true;
    }


    /**
     * Convert the BlockState into the correct metadata value
     */
    public int getMetaFromState(IBlockState state)
    {
        return 0;
    }

    /**
     * Get the actual Block state of this Block at the given position. This applies properties not visible in the
     * metadata, such as fence connections.
     */
    public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        return state.withProperty(NORTH, this.canConnectTo(worldIn, pos.north())).withProperty(EAST, this.canConnectTo(worldIn, pos.east())).withProperty(SOUTH, this.canConnectTo(worldIn, pos.south())).withProperty(WEST, this.canConnectTo(worldIn, pos.west())).withProperty(UP, this.canConnectTo(worldIn, pos.up())).withProperty(DOWN, this.canConnectTo(worldIn, pos.down()));
    }

    protected BlockState createBlockState()
    {
        return new BlockState(this, NORTH, EAST, WEST, SOUTH, UP, DOWN);
    }
}
