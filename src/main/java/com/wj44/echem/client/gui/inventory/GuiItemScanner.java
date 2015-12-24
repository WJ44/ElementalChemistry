package com.wj44.echem.client.gui.inventory;

import com.wj44.echem.inventory.ContainerItemScanner;
import com.wj44.echem.inventory.ContainerItemScanner;
import com.wj44.echem.reference.Textures;
import com.wj44.echem.tileentity.TileEntityItemScanner;
import com.wj44.echem.tileentity.TileEntityItemScanner;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

/**
 * Created by Wesley "WJ44" Joosten on 24-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class GuiItemScanner extends GuiEChem
{
    private final TileEntityItemScanner tileEntityItemScanner;

    InventoryPlayer inventoryPlayer;

    public GuiItemScanner(InventoryPlayer playerInventory, TileEntityItemScanner tileEntityItemScanner)
    {
        super(new ContainerItemScanner(playerInventory, tileEntityItemScanner), Textures.Gui.ITEM_SCANNER, tileEntityItemScanner);
        this.tileEntityItemScanner = tileEntityItemScanner;
        this.inventoryPlayer = playerInventory;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(Textures.Gui.ITEM_SCANNER);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

        int i1;
        if (this.tileEntityItemScanner.isBurning())
        {
            i1 = getBurnTimeRemainingScaled(13);
            this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 1);
        }

        i1 = getCookProgressScaled(24);
        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = this.tileEntityItemScanner.getDisplayName().getUnformattedText();
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(this.inventoryPlayer.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    private int getCookProgressScaled(int scale)
    {
        int j = this.tileEntityItemScanner.getField(2);
        int k = this.tileEntityItemScanner.getField(3);
        return k != 0 && j != 0 ? j * scale / k : 0;
    }

    private int getBurnTimeRemainingScaled(int scale)
    {
        int j = this.tileEntityItemScanner.getField(1);

        if (j == 0)
        {
            j = 200;
        }

        return this.tileEntityItemScanner.getField(0) * scale / j;
    }
}
