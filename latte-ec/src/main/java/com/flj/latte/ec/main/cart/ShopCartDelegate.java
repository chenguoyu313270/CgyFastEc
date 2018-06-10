package com.flj.latte.ec.main.cart;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.flj.latte.delegates.bottom.BottomItemDelegate;
import com.flj.latte.ec.R;

/**
 * Created by cguyu on 2018/6/9.
 */

public class ShopCartDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_shop_cart;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
//        mIconSelectAll.setTag(0);
    }
}
