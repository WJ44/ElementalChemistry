package com.wj44.echem.client.gui.inventory;

import com.wj44.echem.inventory.ContainerDataBank;
import com.wj44.echem.reference.Textures;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/**
 * Created by Wesley "WJ44" Joosten on 15/01/2016.
 * -
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
@SideOnly(Side.CLIENT)
public class GuiDataBank extends GuiContainer
{
    private final InventoryPlayer playerInventory;
    public IInventory dataBankInventory;

    public GuiDataBank(InventoryPlayer playerInventory, IInventory dataBankInventory)
    {
        super(new ContainerDataBank(playerInventory, dataBankInventory));
        this.playerInventory = playerInventory;
        this.dataBankInventory = dataBankInventory;
    }

    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = this.dataBankInventory.getDisplayName().getUnformattedText();
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(this.playerInventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
    {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(Textures.Gui.DATA_BANK);
        int i = (this.width - this.xSize) / 2;
        int j = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(i, j, 0, 0, this.xSize, this.ySize);
    }
}
