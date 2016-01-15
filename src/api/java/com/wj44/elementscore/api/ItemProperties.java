package com.wj44.elementscore.api;

/**
 * Created by Wesley "WJ44" Joosten on 8-5-2015.
 * <
 * Part of the ElementsCore Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class ItemProperties
{
    public int density;
    public float volume;
    public int mass;

    public ItemProperties(int density, float volume)
    {
        this.density = density;
        this.volume = volume;
        mass = (int)(density * volume);
    }
}
