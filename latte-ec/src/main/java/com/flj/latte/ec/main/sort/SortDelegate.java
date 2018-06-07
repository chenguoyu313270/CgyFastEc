package com.flj.latte.ec.main.sort;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.flj.latte.delegates.bottom.BottomItemDelegate;
import com.flj.latte.ec.R;
import com.flj.latte.ec.main.sort.content.ContentDelegate;
import com.flj.latte.ec.main.sort.list.VerticalListDelegate;

//import example.fastec.diabin.com.latte_ec.R;

/**
 * Created by Administrator on 2018\6\6 0006.
 */

public class SortDelegate extends BottomItemDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        super.onBindView(savedInstanceState, rootView);

    }
    /**
     * Lazy initial，Called when fragment is first called.
     * <p>
     * 同级下的 懒加载 ＋ ViewPager下的懒加载  的结合回调方法
     */
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);

        final VerticalListDelegate listDelegate=new VerticalListDelegate();
        loadRootFragment(R.id.vertical_list_container,listDelegate);
        loadRootFragment(R.id.sort_content_container, ContentDelegate.newInstance(1));

    }
}
