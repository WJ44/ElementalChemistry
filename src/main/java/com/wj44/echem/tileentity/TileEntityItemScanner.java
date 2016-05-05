package com.wj44.echem.tileentity;

import com.wj44.echem.init.ModItems;
import com.wj44.echem.reference.Names;
import com.wj44.echem.util.APIHelper;
import com.wj44.elementscore.api.Element;
import com.wj44.elementscore.api.ElementAPI;
import com.wj44.elementscore.api.ItemProperties;
import com.wj44.elementscore.api.TooltipHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ITickable;

/**
 * Created by Wesley "WJ44" Joosten on 15/01/2016.
 * -
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class TileEntityItemScanner extends TileEntityEChem implements ITickable
{
    public static final int INPUT_INVENTORY_INDEX = 0;
    public static final int OUTPUT_INVENTORY_INDEX = 1;

    public TileEntityItemScanner()
    {
        inventory = new ItemStack[2];
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack)
    {
        this.inventory[index] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }
    }

    @Override
    public boolean isItemValidForSlot(int index, ItemStack stack)
    {
        return index == 0;
    }

    @Override
    public int getField(int id)
    {
        return 0;
    }

    @Override
    public void setField(int id, int value)
    {

    }

    @Override
    public int getFieldCount()
    {
        return 0;
    }

    @Override
    public String getName()
    {
        return this.hasCustomName() ? this.customName : Names.Containers.ITEM_SCANNER;
    }

    @Override
    public void update()
    {
        if (inventory[INPUT_INVENTORY_INDEX] != null && inventory[OUTPUT_INVENTORY_INDEX] != null && inventory[OUTPUT_INVENTORY_INDEX].getItem() == ModItems.dataCard &! inventory[OUTPUT_INVENTORY_INDEX].getTagCompound().getBoolean("isScanned"))
        {
            if (ElementAPI.hasElements(inventory[INPUT_INVENTORY_INDEX]))
            {
                ItemStack itemStack = inventory[INPUT_INVENTORY_INDEX];
                NBTTagCompound dataCard = inventory[OUTPUT_INVENTORY_INDEX].getTagCompound();

                itemStack.writeToNBT(dataCard);

                String contains = "Contains: ";
                for (Element element : APIHelper.getElementList(itemStack).getElements())
                {
                    int amount = APIHelper.getElementAmount(itemStack, element);
                    contains += amount + " " + element.symbol + ", ";
                }
                contains = contains.substring(0, contains.length()-2);

                ItemProperties properties = APIHelper.getProperties(itemStack);

                dataCard.setString("item", itemStack.getDisplayName());
                dataCard.setString("elements", contains);
                dataCard.setString("formula", TooltipHelper.getFormulaFromItemStack(itemStack));
                dataCard.setInteger("density", properties.density);
                dataCard.setFloat("volume", properties.volume);
                dataCard.setInteger("mass", properties.mass);
                dataCard.setBoolean("isScanned", true);

                isActive = true;
            }
            else isActive = false;
        }
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 1;
    }
}
