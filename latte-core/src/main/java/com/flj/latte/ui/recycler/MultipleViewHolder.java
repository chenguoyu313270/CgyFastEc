package com.flj.latte.ui.recycler;

import android.view.View;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * Created by Administrator on 2018\6\7 0007.
 */

public class MultipleViewHolder extends BaseViewHolder {

    public MultipleViewHolder(View view) {
        super(view);
    }

    //创建者模式
    public static MultipleViewHolder create(View view) {
        return new MultipleViewHolder(view);
    }


}
