package com.wj44.echem.client.gui.inventory;

import com.wj44.echem.inventory.ContainerDataBank;
import com.wj44.echem.inventory.InventoryDataBank;
import com.wj44.echem.reference.Textures;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

/**
 * Created by Wesley "WJ44" Joosten on 12-4-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class GuiDataBank extends GuiEChem
{
    private final ItemStack parentItemStack;
    private final InventoryDataBank inventoryDataBank;
    private EntityPlayer player;

    public GuiDataBank(EntityPlayer player, InventoryDataBank inventoryDataBank)
    {
        super(new ContainerDataBank(player, inventoryDataBank), Textures.Gui.DECOMPOSER, inventoryDataBank);

        this.parentItemStack = inventoryDataBank.parentItemStack;
        this.inventoryDataBank = inventoryDataBank;
        this.player = player;
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY)
    {
        String s = inventoryDataBank.getInventoryName();
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(player.inventory.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float opacity, int x, int y)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(Textures.Gui.DATA_BANK);

        int xStart = (width - xSize) / 2;
        int yStart = (height - ySize) / 2;
        this.drawTexturedModalRect(xStart, yStart, 0, 0, xSize, ySize);
    }
}
