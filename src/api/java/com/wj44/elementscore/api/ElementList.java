package com.wj44.elementscore.api;

import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * Created by Wesley "WJ44" Joosten on 2-5-2015.
 *
 * Part of the ElementsCore Mod, distributed under a
 * Creative Commons Attribution-NonCommercial-ShareAlike 3.0 License
 * (https://creativecommons.org/licenses/by-nc-sa/3.0/)
 */
public class ElementList implements Serializable
{
    public LinkedHashMap<Element, Integer> elements = new LinkedHashMap<Element, Integer>();

    public ElementList add(Element element, int amount)
    {
        elements.put(element, amount);
        return this;
    }

    public ElementList add(ElementList elementList)
    {
        for (Element element : elementList.getElements())
        {
            add(element, elementList.getAmount(element));
        }
        return this;
    }

    public int getAmount(Element element)
    {
        return elements.get(element);
    }

    public Element[] getElements()
    {
        Element[] tmp = {};
        return elements.keySet().toArray(tmp);
    }
}
