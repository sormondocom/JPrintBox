package com.simtechsystems;

import java.util.List;
import java.util.Map;

public interface PrintBox {
    public void addTitle(String title);

    public void addItem(String key, String value);

    public void removeItem(String key);

    public void addFooter(String footer);

    public int getTitleSize();

    public int getFooterSize();

    public int getMaxKeySize();

    public int getMaxValueSize();

    public String getItemValue(String key);

    public int getItemCount();

    public boolean hasItemKey(String key);

    public boolean hasItemValue(String value);

    public Map<String,String> getItems();

    public String getTitle();

    public String getFooter();

    public int getCurrentMaxWidth();

    public int getCurrentMaxLength();

    public void removeTitle();

    public void removeFooter();

    public int getMaxItemSize();

    public void resize();

    public String getInfoString();

    public String boxTitleToString();

    public String boxItemsToString();

    public void removeAllItems();

    public int getTotalBoxWidth();

    public String boxFooterToString();
}
