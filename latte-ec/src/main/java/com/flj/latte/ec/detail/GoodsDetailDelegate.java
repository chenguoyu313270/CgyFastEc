package com.flj.latte.ec.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.flj.latte.delegates.BaseDelegate;
import com.flj.latte.delegates.LatteDelegate;
import com.flj.latte.ec.R;

/**
 * Created by Administrator on 2018\6\7 0007.
 */

public class GoodsDetailDelegate extends LatteDelegate {
    public static GoodsDetailDelegate create() {

        return new GoodsDetailDelegate();
    }
    @Override
    public Object setLayout() {
        return R.layout.delegate_goods_detail;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }
}
