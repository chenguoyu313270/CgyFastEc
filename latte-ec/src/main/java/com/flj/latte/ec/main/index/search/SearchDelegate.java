package com.flj.latte.ec.main.index.search;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.blankj.utilcode.util.StringUtils;
import com.choices.divider.Divider;
import com.choices.divider.DividerItemDecoration;
import com.flj.latte.delegates.LatteDelegate;
import com.flj.latte.ec.R;
import com.flj.latte.ec.R2;
import com.flj.latte.net.RestClient;
import com.flj.latte.net.callback.ISuccess;
import com.flj.latte.ui.recycler.MultipleItemEntity;
import com.flj.latte.util.storage.LattePreference;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018\7\9 0009.
 */

public class SearchDelegate extends LatteDelegate {

    @BindView(R2.id.rv_search)
    RecyclerView mRecyclerView = null;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchEdit = null;

    @Override
    public Object setLayout() {
        return R.layout.delegate_search;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);

        final List<MultipleItemEntity> data = new SearchDataConverter().convert();
        final SearchAdapter adapter = new SearchAdapter(data);
        mRecyclerView.setAdapter(adapter);

        final DividerItemDecoration itemDecoration = new DividerItemDecoration();
        //设置分割线
        itemDecoration.setDividerLookup(new DividerItemDecoration.DividerLookup() {
            @Override
            public Divider getVerticalDivider(int position) {
                return null;
            }

            @Override
            public Divider getHorizontalDivider(int position) {
                return new Divider.Builder()
                        .size(2)
                        .margin(20, 20)
                        .color(Color.GRAY)
                        .build();
            }
        });

        mRecyclerView.addItemDecoration(itemDecoration);
    }

    //查找
    @OnClick(R2.id.tv_top_search)
    void onCliclSearch() {

        final String searchItemText = mSearchEdit.getText().toString();
        saveItem(searchItemText);
        mSearchEdit.setText("");

//        RestClient.builder()
//                .url("search.php?key=")
//                .loader(getContext())
//                .success(new ISuccess() {
//                    @Override
//                    public void onSuccess(String response) {
//                        final String searchItemText = mSearchEdit.getText().toString();
//                        saveItem(searchItemText);
//                        mSearchEdit.setText("");
//                        //展示一些东西
//                        //弹出一段话
//                    }
//                })
//                .build()
//                .get();
    }

    //返回
    @OnClick(R2.id.icon_top_search_back)
    void onClickBack() {
        getSupportDelegate().pop();
    }
//保存历史搜索记录的
    @SuppressWarnings("unchecked")
    private void saveItem(String item) {
//

        if(!StringUtils.isEmpty(item)&& !StringUtils.isSpace(item)){
            List<String> history;
            final String historyStr =LattePreference.getCustomAppProfile(SearchDataConverter.TAG_SEARCH_HISTORY);

            if(StringUtils.isEmpty(historyStr)){
                history = new ArrayList<>();
            }else{
                history=JSON.parseObject(historyStr,ArrayList.class);
            }
            history.add(item);
            final String json = JSON.toJSONString(history);
            LattePreference.addCustomAppProfile(SearchDataConverter.TAG_SEARCH_HISTORY, json);
        }
    }

}
