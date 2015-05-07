package com.wj44.echem.api;

import java.util.LinkedHashMap;

/**
 * Created by Wesley "WJ44" Joosten on 7-5-2015.
 * <p/>
 * Part of the ElementalChemistry Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class PropertyList
{
    public LinkedHashMap<String, Object> properties = new LinkedHashMap<String, Object>();

    public PropertyList add(String property, Object value)
    {
        properties.put(property, value);
        return this;
    }

    public PropertyList add(PropertyList propertyList)
    {
        for (String property : propertyList.getProperties())
        {
            add(property, propertyList.getValue(property));
        }
        return this;
    }

    public Object getValue(String property)
    {
        return properties.get(property);
    }

    public String[] getProperties()
    {
        String[] tmp = {};
        return properties.keySet().toArray(tmp);
    }
}
