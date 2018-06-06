package com.flj.latte.delegates.bottom;

import java.util.LinkedHashMap;

/**
 * Created by Administrator on 2018\6\5 0005.
 */

public final class ItemBuilder {
    final LinkedHashMap<BottomTabBean, BottomItemDelegate> ITEMS = new LinkedHashMap<>();
    static ItemBuilder builder(){
        return new ItemBuilder();
    }
    public final ItemBuilder addItem(BottomTabBean bean,BottomItemDelegate delegate){
        ITEMS.put(bean,delegate);

        return this;

    }
    public final ItemBuilder addItems(LinkedHashMap<BottomTabBean,BottomItemDelegate> items){
        ITEMS.putAll(items);

        return this;

    }
    public final LinkedHashMap<BottomTabBean,BottomItemDelegate> build(){

        return ITEMS;
    }

}
