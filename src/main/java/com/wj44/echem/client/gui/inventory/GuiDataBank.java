package com.wj44.echem.client.gui.inventory;

import com.wj44.echem.inventory.ContainerDataBank;
import com.wj44.echem.reference.Textures;
import com.wj44.echem.tileentity.TileEntityDataBank;
import net.minecraft.entity.player.InventoryPlayer;

/**
 * Created by Wesley "WJ44" Joosten on 13-4-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class GuiDataBank extends GuiEChem
{
    private final TileEntityDataBank tileEntityDataBank;

    InventoryPlayer inventoryPlayer;

    public GuiDataBank(InventoryPlayer playerInventory, TileEntityDataBank tileEntityDataBank)
    {
        super(new ContainerDataBank(playerInventory, tileEntityDataBank), Textures.Gui.DATA_BANK, tileEntityDataBank);
        this.tileEntityDataBank = tileEntityDataBank;
        this.inventoryPlayer = playerInventory;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = this.tileEntityDataBank.getDisplayName().getUnformattedText();
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(this.inventoryPlayer.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }
}
