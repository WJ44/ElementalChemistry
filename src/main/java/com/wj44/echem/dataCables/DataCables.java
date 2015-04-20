package com.wj44.echem.dataCables;

import net.minecraft.util.BlockPos;

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
public class DataCables
{
    static List<DataCable> dataCables = new ArrayList<DataCable>();

    public static void createDataCable(BlockPos pos)
    {
        dataCables.add(new DataCable(pos));
    }

    public static DataCable getCableAt(BlockPos pos)
    {
        for (DataCable dataCable : dataCables)
        {
            if (dataCable.getPos().equals(pos))
            {
                return dataCable;
            }
        }
        return null;
    }

    public static void removeDataCable(BlockPos pos)
    {
        DataCable cable = getCableAt(pos);

        if (cable.connectedToDataBank)
        {
            cable.passDisconnect(pos, new Random().nextInt());
        }

        dataCables.remove(cable);

        cable.updateConnection();
    }


}
