package com.wj44.echem.client.gui.inventory;

import com.wj44.echem.inventory.ContainerComposer;
import com.wj44.echem.inventory.ContainerComposerConnected;
import com.wj44.echem.reference.Textures;
import com.wj44.echem.tileentity.TileEntityComposer;
import net.minecraft.entity.player.InventoryPlayer;
import org.lwjgl.opengl.GL11;

/**
 * Created by Wesley "WJ44" Joosten on 6-3-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class GuiComposer extends GuiElementMachine
{
    InventoryPlayer inventoryPlayer;

    public GuiComposer(InventoryPlayer playerInventory, TileEntityComposer tileEntity)
    {
        super(tileEntity.dataBankConnected ? new ContainerComposerConnected(playerInventory, tileEntity) : new ContainerComposer(playerInventory, tileEntity), Textures.Gui.COMPOSER, tileEntity);
        this.tileEntity = tileEntity;
        this.inventoryPlayer = playerInventory;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(Textures.Gui.COMPOSER);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

        if(tileEntity.dataBankConnected)
        {
            drawTexturedModalRect(k - 125, l, 0, 166, 129, 82);
        }
        int i1;
        if (this.tileEntity.isBurning())
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
        String s = this.tileEntity.getDisplayName().getUnformattedText();
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(this.inventoryPlayer.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    private int getCookProgressScaled(int scale)
    {
        int j = this.tileEntity.getField(2);
        int k = this.tileEntity.getField(3);
        return k != 0 && j != 0 ? j * scale / k : 0;
    }

    private int getBurnTimeRemainingScaled(int scale)
    {
        int j = this.tileEntity.getField(1);

        if (j == 0)
        {
            j = 200;
        }

        return this.tileEntity.getField(0) * scale / j;
    }

}