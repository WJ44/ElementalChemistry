package com.wj44.echem.client.gui.inventory;

import com.wj44.echem.inventory.ContainerDecomposer;
import com.wj44.echem.reference.Textures;
import com.wj44.echem.tileentity.TileEntityDecomposer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

/**
 * Created by Wesley "WJ44" Joosten on 20-2-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class GuiDecomposer extends GuiEChem
{
    private final TileEntityDecomposer tileEntityDecomposer;

    InventoryPlayer inventoryPlayer;

    public GuiDecomposer(InventoryPlayer playerInventory, TileEntityDecomposer tileEntityDecomposer)
    {
        super(new ContainerDecomposer(playerInventory, tileEntityDecomposer), Textures.Gui.DECOMPOSER, tileEntityDecomposer);
        this.tileEntityDecomposer = tileEntityDecomposer;
        this.inventoryPlayer = playerInventory;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(Textures.Gui.DECOMPOSER);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);

        int i1;
        if (this.tileEntityDecomposer.isBurning())
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
        String s = this.tileEntityDecomposer.getDisplayName().getUnformattedText();
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(this.inventoryPlayer.getDisplayName().getUnformattedText(), 8, this.ySize - 96 + 2, 4210752);
    }

    private int getCookProgressScaled(int scale)
    {
        int j = this.tileEntityDecomposer.getField(2);
        int k = this.tileEntityDecomposer.getField(3);
        return k != 0 && j != 0 ? j * scale / k : 0;
    }

    private int getBurnTimeRemainingScaled(int scale)
    {
        int j = this.tileEntityDecomposer.getField(1);

        if (j == 0)
        {
            j = 200;
        }

        return this.tileEntityDecomposer.getField(0) * scale / j;
    }
}
