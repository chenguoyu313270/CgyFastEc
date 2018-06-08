package com.flj.latte.ec.main.sort.list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.flj.latte.delegates.LatteDelegate;
import com.flj.latte.ec.R;
import com.flj.latte.ec.R2;
import com.flj.latte.ec.main.sort.SortDelegate;
import com.flj.latte.net.RestClient;
import com.flj.latte.net.callback.IFailure;
import com.flj.latte.net.callback.ISuccess;
import com.flj.latte.ui.recycler.MultipleItemEntity;
import com.flj.latte.util.TestUrlData;

import java.util.List;

import butterknife.BindView;

/**
 * Created by cguyu on 2018/6/7.
 */

public class VerticalListDelegate extends LatteDelegate {
    //布局 RecyclerView
    @BindView(R2.id.rv_vertical_menu_list)
    RecyclerView mRecyclerView = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_vertical_list;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        initRecyclerView();
    }

    private void initRecyclerView() {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        //屏蔽动画效果
        mRecyclerView.setItemAnimator(null);
    }

    //加载数据
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //模拟请求数据
        RestClient.builder().url("http://news.baidu.com/").loader(getContext()).success(new ISuccess() {
            @Override
            public void onSuccess(String response) {
                Toast.makeText(getContext(), "成功", Toast.LENGTH_SHORT);
                String testJsonData = TestUrlData.STR_SORT_DELEGATE_DATA;
                final List<MultipleItemEntity> data = new VerticalListDataConverter()
                        .setJsonData(testJsonData).convert();
                final SortDelegate delegate = getParentDelegate();

                final SortRecyclerAdapter adapter = new SortRecyclerAdapter(data, delegate);
                mRecyclerView.setAdapter(adapter);


            }
        }).failure(new IFailure() {
            @Override
            public void onFailure() {
                Toast.makeText(getContext(), "失败", Toast.LENGTH_SHORT);
            }
        })
                .build().get();
    }
}
