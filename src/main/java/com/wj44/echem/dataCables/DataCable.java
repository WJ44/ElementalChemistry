package com.wj44.echem.dataCables;

import com.wj44.echem.tileentity.TileEntityComposer;
import com.wj44.echem.tileentity.TileEntityDataBank;
import com.wj44.echem.util.BlockPosHelper;
import com.wj44.echem.util.LogHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Wesley "WJ44" Joosten on 17-4-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class DataCable
{
    public List<DataCable> connectedCables;
    public List<TileEntityComposer> connectedTileEntities;
    public BlockPos[] neighboringPositions;
    public TileEntityDataBank connectedDataBank;
    public boolean connectedToDataBank;
    public DataCable originCable;
    public int pathCode;
    private Random random;
    public BlockPos pos;
    private World world;

    public DataCable(BlockPos position)
    {
        pos = position;
        connectedToDataBank = false;
        neighboringPositions = BlockPosHelper.getNeighboringPositions(pos);
        connectedCables = new ArrayList<DataCable>();
        connectedTileEntities = new ArrayList<TileEntityComposer>();
        random = new Random();
        world = Minecraft.getMinecraft().theWorld;

        checkConnected();

        if (connectedToDataBank)
        {
            passInformation();
        }
    }

    public void checkConnected()
    {
        checkCables();
        checkTileEntities();
    }

    public void checkCables()
    {
        connectedCables.clear();
        for (BlockPos blockPos : neighboringPositions)
        {
            DataCable dataCable = DataCables.getCableAt(blockPos);
            if (dataCable != null)
            {
                connectedCables.add(dataCable);
                LogHelper.info("found " + dataCable + " at " + blockPos);
            }
        }
    }

    public void checkTileEntities()
    {
        connectedTileEntities.clear();
        for (BlockPos blockPos : neighboringPositions)
        {
            TileEntity tileEntity = world.getTileEntity(blockPos);
            if (tileEntity instanceof TileEntityComposer)
            {
                connectedTileEntities.add((TileEntityComposer) tileEntity);
                LogHelper.info("found " + tileEntity + " at " + blockPos);
            }
            else if (tileEntity instanceof TileEntityDataBank)
            {
                connectedDataBank = (TileEntityDataBank) tileEntity;
                LogHelper.info("found " + tileEntity + " at " + blockPos);
                connectedToDataBank = true;
            }
        }

        if (connectedDataBank != null && world.getTileEntity(connectedDataBank.getPos()) == null)
        {
            connectedDataBank = null;
            connectedToDataBank = false;

            for (TileEntityComposer tileEntity : connectedTileEntities)
            {
                tileEntity.connectedDataBank = null;
                tileEntity.dataBankConnected = false;
                tileEntity.dataBankBroken = true;
            }
        }
    }

    public void passInformation()
    {
        this.pathCode = random.nextInt();
        for (DataCable cable : connectedCables)
        {
            cable.passInformation(pos, connectedDataBank, this, pathCode);
        }

        for (TileEntityComposer tileEntity : connectedTileEntities)
        {
            tileEntity.connectedDataBank = connectedDataBank;
            tileEntity.dataBankConnected = true;
        }
    }

    public void passInformation(BlockPos sourcePos, TileEntityDataBank dataBank, DataCable originCable, int pathCode)
    {
        this.originCable = originCable;
        this.pathCode = pathCode;
        for (DataCable cable : connectedCables)
        {
            if (!cable.pos.equals(sourcePos) && cable.pathCode != pathCode)
            {
                cable.passInformation(pos, dataBank, originCable, pathCode);
            }
        }

        for (TileEntityComposer tileEntity : connectedTileEntities)
        {
            tileEntity.connectedDataBank = dataBank;
            tileEntity.dataBankConnected = true;
        }
    }

    public void passDisconnect(BlockPos sourcePos, int pathCode)
    {
        originCable = null;
        this.pathCode = pathCode;

        for (DataCable cable : connectedCables)
        {
            if (!cable.pos.equals(sourcePos) && cable.pathCode != pathCode &! cable.connectedToDataBank)
            {
                 cable.passDisconnect(pos, pathCode);
            }
        }

        for (TileEntityComposer tileEntity : connectedTileEntities)
        {
            tileEntity.connectedDataBank = null;
            tileEntity.dataBankConnected = false;
            tileEntity.dataBankBroken = true;
        }
    }

    public void updateConnection()
    {
        for (DataCable cable : connectedCables)
        {
            cable.checkCables();

            cable.passDisconnect(pos, random.nextInt());
        }

        if(originCable != null)
        {
            originCable.passInformation();
        }

        for (TileEntityComposer tileEntity : connectedTileEntities)
        {
            tileEntity.connectedDataBank = null;
            tileEntity.dataBankConnected = false;
            tileEntity.dataBankBroken = true;
        }
    }
}
